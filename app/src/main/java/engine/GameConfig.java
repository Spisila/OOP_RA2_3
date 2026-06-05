package engine;

import java.io.Serializable;

import com.raylib.Raylib.float16;

public class GameConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    private int largura;
    private int altura;
    private int vidasPlayer;
    private float velocidadePlayer;
    private float taxaSpawnInimigos;
    private int chanceAsteroide;
    private int vidaAsteroide;
    private int vidaAlien;

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

    public int get_window_width() {
        return largura;
    }

    public int get_window_height() {
        return altura;
    }

    public int get_player_life_config() {
        return vidasPlayer;
    }

    public float get_player_speed_config() {
        return velocidadePlayer;
    }

    public float get_enemy_spawn_rate_config() {
        return taxaSpawnInimigos;
    }

    public int get_asteroid_chance_config() {
        return chanceAsteroide;
    }

    public int get_asteriod_life_config() {
        return vidaAsteroide;
    }

    public int get_alien_life_config() {
        return vidaAlien;
    }

}