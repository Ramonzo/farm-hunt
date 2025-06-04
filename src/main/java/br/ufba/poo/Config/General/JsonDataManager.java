package br.ufba.poo.Config.General;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.ufba.poo.Engine.Constants;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonDataManager {
    private final File dataFile;
    private final ObjectMapper objectMapper;
    private final Map<String, Object> dataMap;
    private final String filePath;

    public JsonDataManager(String filePath) {
        this.filePath = Constants.USER_DIR + Constants.DATA_PATH + filePath;
        this.dataFile = new File(this.filePath);
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        this.dataMap = loadData();
    }

    private Map<String, Object> loadData() {

        if (!dataFile.exists()) {
            return new HashMap<>();
        }

        try {

            if (dataFile.length() == 0) {
                return new HashMap<>();
            }

            return objectMapper.readValue(
                    dataFile,
                    new TypeReference<Map<String, Object>>() {
                    });
        } catch (IOException e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
            renameCorruptedFile();
            return new HashMap<>();
        }
    }

    private void renameCorruptedFile() {
        try {
            Path source = Paths.get(filePath);
            Path target = source.resolveSibling("corrupted_" + System.currentTimeMillis() + "_" + source.getFileName());
            Files.move(source, target);
            System.err.println("Arquivo corrompido renomeado para: " + target);
        } catch (IOException ex) {
            System.err.println("Falha ao renomear arquivo corrompido: " + ex.getMessage());
        }
    }

    private void saveData() {
        try {
            File parentDir = dataFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                if (!parentDir.mkdirs()) {
                    System.err.println("Falha ao criar diretório: " + parentDir.getAbsolutePath());
                }
            }
            
            objectMapper.writeValue(dataFile, dataMap);

            if (!dataFile.exists()) {
                System.err.println("ERRO: Arquivo não foi criado após salvamento!");
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void upsert(String key, Object data) {
        dataMap.put(key, data);
        saveData();
    }

    public <T> T get(String key, Class<T> type) {
        try {
            Object obj = dataMap.get(key);
            return objectMapper.convertValue(obj, type);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao converter objeto: " + e.getMessage());
            return null;
        }
    }

    public <T> List<T> getList(String key, Class<T> elementType) {
        try {
            Object obj = dataMap.get(key);
            return objectMapper.convertValue(
                    obj,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, elementType));
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao converter lista: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public <K, V> Map<K, V> getMap(String key, Class<K> keyType, Class<V> valueType) {
        try {
            Object obj = dataMap.get(key);
            return objectMapper.convertValue(
                    obj,
                    objectMapper.getTypeFactory().constructMapType(Map.class, keyType, valueType));
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao converter mapa: " + e.getMessage());
            return Collections.emptyMap();
        }
    }

    public void delete(String key) {
        if (dataMap.containsKey(key)) {
            dataMap.remove(key);
            saveData();
        }
    }
}