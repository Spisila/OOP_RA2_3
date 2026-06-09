package P2;

import static com.raylib.Raylib.*;

import java.util.ArrayList;
import java.util.Random;

import static com.raylib.Colors.*;
import static com.raylib.Helpers.newVector2;

import engine.Circle;
import engine.EDirection;
import engine.EGameState;
import engine.GameTimer;
import engine.Rect;
import entities.Player;
import entities.Projectile;
import entities.enemy.Alien;
import entities.enemy.Asteroid;
import entities.enemy.Enemy;

public class P2 {

    static int screen_width;
    static int screen_height;
    static int player_y;
    static int lives;
    static float player_speed;
    static float enemy_spawn_rate;
    static int asteroid_chance;
    static int asteriod_life;
    static int alien_life;

    public static void carregarConfiguracaoBinaria() {
        try {

            java.io.FileInputStream arquivo = new java.io.FileInputStream("app\\src\\main\\java\\P1\\config_jogo.dat");
            java.io.ObjectInputStream leitor = new java.io.ObjectInputStream(arquivo);

            engine.GameConfig config = (engine.GameConfig) leitor.readObject();

            leitor.close();
            arquivo.close();

            screen_width = config.get_window_width();
            screen_height = config.get_window_height();
            lives = config.get_player_life_config();
            player_speed = config.get_player_speed_config();
            enemy_spawn_rate = config.get_enemy_spawn_rate_config();
            asteroid_chance = config.get_asteroid_chance_config();
            asteriod_life = config.get_asteriod_life_config();
            alien_life = config.get_alien_life_config();

            System.out.println("Configs P1 carregadas");

        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo binario. Usando configs padrão");

            screen_width = 1300;
            screen_height = 850;
            lives = 10;
            player_speed = 5f;
            enemy_spawn_rate = 1.0f;
            asteroid_chance = 90;
            asteriod_life = 10;
            alien_life = 20;

        }

        player_y = screen_height - 50;
    }

    public static void main(String[] args) {

        carregarConfiguracaoBinaria();

        //Cria player
        Player player = new Player(screen_width / 2, player_y, 1.5f, null, player_speed);

        //Lista de inimigos e projeteis
        ArrayList<Enemy> active_enemies = new ArrayList<>();
        ArrayList<Projectile> active_projectiles = new ArrayList<>();

        GameTimer spawn_enemies_timer = new GameTimer(enemy_spawn_rate);

        // Variaveis do jogo
        int score = 0;
        int projectiles_fired = 0;
        int projectiles_hit = 0;
        float accuracy = 0;

        // Caminhos dos arquivos de log
        String partial_logs_path = "app\\src\\main\\java\\P2\\resultado_parcial.csv";
        String final_logs_path = "app\\src\\main\\java\\P2\\resultado_final.csv";
        
        P2LogWriter partial_logs = new P2LogWriter(partial_logs_path);
        P2LogWriter final_logs = new P2LogWriter(final_logs_path);

        GameTimer take_partial_logs_timer = new GameTimer(2f);

        // Limpa arquivo antigo e adiciona header
        partial_logs.clear_csv();
        partial_logs.append_to_csv("score, vidas, precisão");

        partial_logs.clear_csv();
        partial_logs.append_to_csv("score,vidas,precisão");

        Random ran = new Random();


        EGameState current_state = EGameState.START_MENU;

        InitWindow(screen_width, screen_height, "Programa P2");

        // Texto tela de começo
        String main_menu_message = "Aperte Enter para começar";
        int main_menu_font_size = 50;
        int text_width = MeasureText(main_menu_message, main_menu_font_size);
        int main_menu_text_pos_x = (screen_width / 2) - (text_width / 2);
        int main_menu_text_pos_y = (screen_height / 2) - (main_menu_font_size / 2);

        // Texto tela de derrota
        String defeat_message = "Voce perdeu, aperte Enter para recomeçar";
        int defeat_message_font_size = 50;
        int defeat_text_width = MeasureText(defeat_message, defeat_message_font_size);
        int defeat_message_text_pos_x = (screen_width / 2) - (defeat_text_width / 2);
        int defeat_message_text_pos_y = (screen_height / 2) - (defeat_message_font_size / 2);

        SetTargetFPS(60);

        while (!WindowShouldClose()) {

            if (current_state == EGameState.START_MENU) {

                if (IsKeyDown(KEY_ENTER)) {
                    current_state = EGameState.GAMEPLAY;
                }

                BeginDrawing();

                DrawText(main_menu_message, main_menu_text_pos_x, main_menu_text_pos_y, main_menu_font_size, BEIGE);

                ClearBackground(BLACK);

                EndDrawing();

            } else if (current_state == EGameState.GAMEPLAY) {

                BeginDrawing();

                // Get player input
                if (IsKeyDown(KEY_LEFT)) {
                    player.move(EDirection.LEFT);
                } else if (IsKeyDown(KEY_RIGHT)) {
                    player.move(EDirection.RIGHT);
                }

                if (IsKeyDown(KEY_SPACE)) {

                    if (player.shoot(active_projectiles)) {
                        projectiles_fired += 1;
                    }
                }

                // Update player
                player.update(GetFrameTime());

                // Spawn new enemies
                spawn_enemies_timer.update(GetFrameTime());

                if (spawn_enemies_timer.is_counting_down() == false) {

                    int ran_percentage = ran.nextInt(100);

                    float random_x = ran.nextFloat(50, screen_width - 50);

                    if (ran_percentage <= asteroid_chance) {

                        Rect r = new Rect(newVector2(random_x, -50), 50, 50);

                        Enemy e = new Asteroid(random_x, -50, 1, asteriod_life, r);

                        active_enemies.add(e);
                    } else {

                        Circle c = new Circle(newVector2(random_x, -50), 25);
                        Enemy e = new Alien(random_x, -50, 1, alien_life, c);

                        active_enemies.add(e);

                    }

                    spawn_enemies_timer.start();

                }

                // Update enemies
                for (int i = 0; i < active_enemies.size(); i++) {
                    Enemy e = active_enemies.get(i);
                    e.draw();
                    e.move(EDirection.DOWN);
                    e.update();

                    // Se inimigo morto remove da lista
                    if (e.is_alive() == false) {
                        active_enemies.remove(e);
                        i--;
                    }

                    // Se inimigo fora da tela remove da lista
                    if (e.get_position().y() > screen_height + 25) {
                        lives -= 1;
                        active_enemies.remove(e);
                        i--;
                    }

                    // Checa collisao de inimigos com projeteis
                    for (int j = 0; j < active_projectiles.size(); j++) {
                        if (e.get_collider().check_collision(active_projectiles.get(j).get_collider())) {
                            e.takeDamage(10);
                            active_projectiles.remove(j);
                            j--;
                            projectiles_hit += 1;
                        }
                    }

                }

                // Update projeteis
                for (int i = 0; i < active_projectiles.size(); i++) {
                    Projectile p = active_projectiles.get(i);

                    p.draw();
                    p.move(EDirection.UP);
                    p.update();

                    // Se projetil fora da rela remove da lista
                    if (p.get_position().y() < -25) {
                        active_projectiles.remove(p);
                        i--;
                    }
                }

                // Update score
                score += 1;

                // Checa se perdeu
                if (lives <= 0) {
                    current_state = EGameState.DEFEAT;
                }

                // Update precisão
                if (projectiles_fired > 0) {
                    accuracy = ((float) projectiles_hit / (float) projectiles_fired) * 100f;
                }

                // Logs parciais
                take_partial_logs_timer.update(GetFrameTime());
                if (take_partial_logs_timer.is_counting_down() == false) {

                    String logs_string = String.valueOf(score) + "," + String.valueOf(lives) + ","
                            + String.valueOf(accuracy);
                    partial_logs.append_to_csv(logs_string);

                    take_partial_logs_timer.start();
                }

                // Drawing loop

                DrawText("SCORE = " + String.valueOf(score), 0, 0, 25, BEIGE);
                DrawText("VIDAS = " + String.valueOf(lives), 0, 30, 25, BEIGE);
                DrawText("PRECISÃO = " + String.valueOf(accuracy), 0, 60, 25, BEIGE);

                ClearBackground(BLACK);
                player.draw();

                EndDrawing();

            } else if (current_state == EGameState.DEFEAT) {

                if (IsKeyDown(KEY_ENTER)) {
                    current_state = EGameState.GAMEPLAY;
                    active_projectiles.clear();
                    active_enemies.clear();

                    score = 0;
                    lives = 10;
                }

                BeginDrawing();

                DrawText(defeat_message, defeat_message_text_pos_x, defeat_message_text_pos_y, defeat_message_font_size,
                        BEIGE);

                ClearBackground(BLACK);

                EndDrawing();

            }

        }

        final_logs.clear_csv();
        final_logs.append_to_csv("score final,precisao");
        final_logs.append_to_csv(String.valueOf(score) + "," + String.valueOf(accuracy));

        CloseWindow();
    }

}
