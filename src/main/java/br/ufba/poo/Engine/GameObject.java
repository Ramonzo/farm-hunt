package br.ufba.poo.Engine;

public class GameObject {

    private SpriteRenderer spriteRenderer;
    private BoxCollider boxCollider;

    public GameObject(){
        this.spriteRenderer = new SpriteRenderer();
        this.boxCollider = new BoxCollider();
    }

}
