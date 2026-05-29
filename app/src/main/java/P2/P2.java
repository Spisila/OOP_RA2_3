package P2;

import static com.raylib.Raylib.*;

import java.util.ArrayList;
import java.util.Random;

import static com.raylib.Colors.*;

import core.Asteroid;
import core.EDirection;
import core.Enemy;
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

        Random ran = new Random();

        InitWindow(screen_width, screen_height, "Programa P2");

        SetTargetFPS(60);

        while (!WindowShouldClose()) {

            if (IsKeyDown(KEY_LEFT)) {
                player.move(EDirection.LEFT);
            } else if (IsKeyDown(KEY_RIGHT)) {
                player.move(EDirection.RIGHT);
            }

            if (IsKeyDown(KEY_SPACE)) {
                player.shoot(active_projectiles);
            }

            player.update(GetFrameTime());

            spawn_enemies_timer -= GetFrameTime();

            if (spawn_enemies_timer <= 0) {
                Enemy new_Enemy = new Asteroid(ran.nextInt(0, screen_width), -50, ran.nextFloat(0.8f, 1.5f),
                        ran.nextInt(50, 100));
                active_enemies.add(new_Enemy);
                spawn_enemies_timer = 1.5f;
            }

            for (int i = 0; i < active_enemies.size(); i++) {
                Enemy e = active_enemies.get(i);

                e.move(EDirection.DOWN);

                if (e.get_position().y() >= screen_height) {
                    active_enemies.remove(e);
                }
            }

            for (Projectile p : active_projectiles) {
                p.draw();
                p.move(EDirection.UP);
            }

            BeginDrawing();

            ClearBackground(BLACK);
            player.draw();

            for (Enemy e : active_enemies) {
                e.draw();
            }

            EndDrawing();
        }

        CloseWindow();
    }
}
