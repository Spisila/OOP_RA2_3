package P2;

import static com.raylib.Raylib.*;

import java.util.ArrayList;
import java.util.Random;

import static com.raylib.Colors.*;

import core.Asteroid;
import core.EDirection;
import core.Enemy;
import core.Player;

public class P2 {

    static int screen_width = 1300;
    static int screen_height = 850;

    static int player_y = 800;

    public static void main(String[] args) {

        Player player = new Player(screen_width / 2, player_y, 1.5f);

        ArrayList<Enemy> active_enemies = new ArrayList<>();

        float spawn_enemies_timer = 0.5f;

        Random ran = new Random();

        InitWindow(screen_width, screen_height, "Programa P2");

        SetTargetFPS(60);

        while (!WindowShouldClose()) {

            if (IsKeyDown(KEY_LEFT)) {
                player.move(EDirection.LEFT);
            } else if (IsKeyDown(KEY_RIGHT)) {
                player.move(EDirection.RIGHT);
            }

            spawn_enemies_timer -= GetFrameTime();

            if (spawn_enemies_timer <= 0) {
                for (int i = 0; i < 10; i++) {
                    Enemy new_Enemy = new Asteroid(ran.nextInt(0, screen_width), -50, ran.nextFloat(0.8f, 1.5f),
                            ran.nextInt(50, 100));
                    active_enemies.add(new_Enemy);
                }
                spawn_enemies_timer = 0.5f;
            }

            for (int i = 0; i < active_enemies.size(); i++) {
                Enemy e = active_enemies.get(i);

                e.move(EDirection.DOWN);

                if (e.get_position().y() >= screen_height) {
                    active_enemies.remove(e);
                }
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
