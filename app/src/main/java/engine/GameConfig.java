package engine;

import java.io.Serializable;

public class GameConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    public int largura;
    public int altura;
    public int vidasPlayer;
    public float velocidadePlayer;
    public float taxaSpawnInimigos;
    public int chanceAsteroide;
    public int vidaAsteroide;
    public int vidaAlien;

    public GameConfig(int largura, int altura, int vidasPlayer, float velocidadePlayer, 
                      float taxaSpawnInimigos, int chanceAsteroide, int vidaAsteroide, int vidaAlien) {

        this.largura = largura;
        this.altura = altura;
        this.vidasPlayer = vidasPlayer;
        this.velocidadePlayer = velocidadePlayer;
        this.taxaSpawnInimigos = taxaSpawnInimigos;
        this.chanceAsteroide = chanceAsteroide;
        this.vidaAsteroide = vidaAsteroide;
        this.vidaAlien = vidaAlien;

    }
    
}