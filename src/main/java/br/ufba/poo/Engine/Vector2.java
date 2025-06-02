package br.ufba.poo.Engine;

public class Vector2 {
    public final float x;
    public final float y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 newVector2) {
        this.x = newVector2.x;
        this.y = newVector2.y;
    }

    // Calcula a distância euclidiana até outro vetor
    public double distance(Vector2 other) {
        float dx = this.x - other.x;
        float dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Soma com outro vetor
    public Vector2 add(Vector2 other) {
        return new Vector2(this.x + other.x, this.y + other.y);
    }

    // Subtrai outro vetor
    public Vector2 subtract(Vector2 other) {
        return new Vector2(this.x - other.x, this.y - other.y);
    }

    // Multiplica por um escalar
    public Vector2 multiply(float scalar) {
        return new Vector2(this.x * scalar, this.y * scalar);
    }

    // Produto escalar (dot product)
    public float dotProduct(Vector2 other) {
        return this.x * other.x + this.y * other.y;
    }

    // Magnitude (comprimento) do vetor
    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    // Distância de Manhattan (soma das diferenças absolutas)
    public float manhattanDistance(Vector2 other) {
        return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
    }

    // Override para comparar conteúdo ao invés de referência
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Vector2 other = (Vector2) obj;
        return x == other.x && y == other.y;
    }

    // Mantém consistente com o equals()
    @Override
    public int hashCode() {
        return (int) (31 * x + y);
    }

    // Representação amigável do vetor
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
