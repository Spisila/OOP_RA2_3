package core;

import static com.raylib.Raylib.*;

import com.raylib.Raylib.Vector2;

import static com.raylib.Helpers.newVector2;
import static com.raylib.Colors.*;

public class Player extends GameObject {

  private Vector2 point1;
  private Vector2 point2;
  private Vector2 point3;

  public Player(float x, float y, float scale) {
    super(x, y, scale);

    this.point1 = newVector2(x, y - 10 * scale);
    this.point2 = newVector2(x - 10 * scale, y + 10 * scale);
    this.point3 = newVector2(x + 10 * scale, y + 10 * scale);
  }

  public void draw_player() {
    this.point1 = newVector2(x, y - 10 * scale);
    this.point2 = newVector2(x - 10 * scale, y + 10 * scale);
    this.point3 = newVector2(x + 10 * scale, y + 10 * scale);
    DrawTriangle(point2, point3, point1, RAYWHITE);
  }

  public void move(int direction) {

    if (direction == -1) {
      this.x -= 5;
    } else if (direction == 1) {
      this.x += 5;
    }

  }

}
