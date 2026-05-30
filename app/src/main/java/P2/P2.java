package P2;

import static com.raylib.Raylib.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Supplier;

import static com.raylib.Colors.*;
import static com.raylib.Helpers.newVector2;

import core.Alien;
import core.Asteroid;
import core.EDirection;
import core.Enemy;
import core.GameObject;
import core.Player;
import core.Projectile;

public class P2 {

    static int screen_width = 1300;
    static int screen_height = 850;

    static int player_y = 800;

    public static void main(String[] args) {

        Player player = new Player(screen_width / 2, player_y, 1.5f);

        ArrayList<Enemy> active_enemies = new ArrayList<>();
        ArrayList<Projectile> active_projectiles = new ArrayList<>();

        float spawn_enemies_timer = 1.5f;

        int score = 0;

        Random ran = new Random();

        InitWindow(screen_width, screen_height, "Programa P2");

        SetTargetFPS(60);

        while (!WindowShouldClose()) {

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

                Enemy e = new Asteroid(random_x, -50, 1, 10);

                random_x = ran.nextFloat(50, screen_width - 50);

                Enemy e_1 = new Alien(random_x, -50, 1, 20);

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

            // Drawing loop
            BeginDrawing();

            DrawText(String.valueOf(score), 0, 0, 25, BEIGE);

            ClearBackground(BLACK);
            player.draw();

            EndDrawing();
        }

        CloseWindow();
    }

}
