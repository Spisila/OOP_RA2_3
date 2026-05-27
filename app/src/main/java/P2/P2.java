package P2;

import static com.raylib.Raylib.*;
import static com.raylib.Colors.*;

import core.Player;

public class P2 {

    static int screen_width = 1300;
    static int screen_height = 850;

    static float player_y = 800;

    public static void main(String[] args) {

        Player player = new Player(screen_width / 2, player_y, 1.5f);

        InitWindow(screen_width, screen_height, "Programa P2");
        SetTargetFPS(60);

        while (!WindowShouldClose()) {

            if (IsKeyDown(KEY_LEFT)) {
                player.move(-1);
            } else if (IsKeyDown(KEY_RIGHT)) {
                player.move(1);
            }

            BeginDrawing();

            ClearBackground(BLACK);
            player.draw_player();

            EndDrawing();
        }

        CloseWindow();
    }
}
