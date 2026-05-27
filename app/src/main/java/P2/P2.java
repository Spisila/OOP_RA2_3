package P2;
import static com.raylib.Raylib.*;
import static com.raylib.Colors.*; 

public class P2 {
    
    public static void main(String[] args) {
        
        // Configuração da janela
        InitWindow(800, 450, "Raylib Java - PUCPR RA2/RA3");
        SetTargetFPS(60);

        // Game Loop
        while (!WindowShouldClose()) {
            
            // 1. Atualização de lógica (Inputs aqui)
            // ex: if (IsKeyPressed(KEY_SPACE)) { ... }

            // 2. Renderização
            BeginDrawing();
                ClearBackground(RAYWHITE);
                
                DrawText("Raylib funcionando no Java!", 190, 200, 20, LIGHTGRAY);
                DrawRectangle(10, 10, 50, 50, RED);
                
            EndDrawing();
        }

        // Fecha a janela ao sair do loop
        CloseWindow();
    }
}
