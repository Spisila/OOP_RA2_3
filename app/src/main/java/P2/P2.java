package P2;

import static com.raylib.Raylib.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Supplier;

import static com.raylib.Colors.*;
import static com.raylib.Helpers.newVector2;

import core.Alien;
import core.Asteroid;
import core.Circle;
import core.EDirection;
import core.EGameState;
import core.Enemy;
import core.GameObject;
import core.Player;
import core.Projectile;
import core.Rect;

public class P2 {

    static int screen_width = 1300;
    static int screen_height = 850;

    static int player_y = 800;

    public static void main(String[] args) {

        Player player = new Player(screen_width / 2, player_y, 1.5f, null);

        ArrayList<Enemy> active_enemies = new ArrayList<>();
        ArrayList<Projectile> active_projectiles = new ArrayList<>();

        float spawn_enemies_timer = 1.5f;

        int score = 0;
        int lives = 10;

        Random ran = new Random();

        EGameState current_state = EGameState.START_MENU;

        InitWindow(screen_width, screen_height, "Programa P2");

        String main_menu_message = "Aperte Enter para começar";
        int main_menu_font_size = 50;

        int text_width = MeasureText(main_menu_message, main_menu_font_size);

        int main_menu_text_pos_x = (screen_width / 2) - (text_width / 2);
        int main_menu_text_pos_y = (screen_height / 2) - (main_menu_font_size / 2);

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

                // Get player input
                if (IsKeyDown(KEY_LEFT)) {
                    player.move(EDirection.LEFT);
                } else if (IsKeyDown(KEY_RIGHT)) {
                    player.move(EDirection.RIGHT);
                }

                if (IsKeyDown(KEY_SPACE)) {
                    player.shoot(active_projectiles);
                }

                // Update player
                player.update(GetFrameTime());

                if (score <= 4000) {
                    float new_speed_mod = 1 + (float) score / 1000f;
                    player.set_speed_modifier(new_speed_mod);

                    float new_shoot_cooldown_mod = 0.5f - (float) score / 10000f;
                    player.set_shooting_cooldow(new_shoot_cooldown_mod);

                }

                // Spawn new enemies
                spawn_enemies_timer -= GetFrameTime();
                if (spawn_enemies_timer <= 0) {

                    float random_x = ran.nextFloat(50, screen_width - 50);

                    Rect r = new Rect(newVector2(random_x, -50), 50, 50);

                    Enemy e = new Asteroid(random_x, -50, 1, 10, r);

                    random_x = ran.nextFloat(50, screen_width - 50);

                    Circle c = new Circle(newVector2(random_x, -50), 25);
                    Enemy e_1 = new Alien(random_x, -50, 1, 20, c);

                    active_enemies.add(e);
                    active_enemies.add(e_1);

                    spawn_enemies_timer = 1.5f;
                }

                // Update enemies
                for (int i = 0; i < active_enemies.size(); i++) {
                    Enemy e = active_enemies.get(i);
                    e.draw();
                    e.move(EDirection.DOWN);
                    e.update();

                    if (e.is_alive() == false) {
                        active_enemies.remove(e);
                    }

                    if (e.get_position().y() > screen_height + 25) {
                        lives -= 1;
                        active_enemies.remove(e);
                    }

                    for (int j = 0; j < active_projectiles.size(); j++) {
                        if (e.get_collider().check_collision(active_projectiles.get(j).get_collider())) {
                            e.takeDamage(10);
                            active_projectiles.remove(j);
                        }
                    }

                }

                // Update projectiles
                for (int i = 0; i < active_projectiles.size(); i++) {
                    Projectile p = active_projectiles.get(i);

                    p.draw();
                    p.move(EDirection.UP);
                    p.update();

                    if (p.get_position().y() < -25) {
                        active_projectiles.remove(p);
                    }
                }

                // Update score
                score += 1;

                // Update lives
                if (lives <= 0) {
                    current_state = EGameState.DEFEAT;
                }

                // Drawing loop
                BeginDrawing();

                DrawText(String.valueOf(score), 0, 0, 25, BEIGE);
                DrawText(String.valueOf(lives), 0, 30, 25, BEIGE);

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

        CloseWindow();
    }

}
