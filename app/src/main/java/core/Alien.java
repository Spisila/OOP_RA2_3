package core;

import static com.raylib.Colors.BLUE;
import static com.raylib.Helpers.newVector2;
import static com.raylib.Raylib.DrawCircleV;
import static com.raylib.Raylib.DrawRectangleV;

import com.raylib.Raylib.Vector2;

public class Alien extends Enemy implements IDamageable {

  public Alien(float _x, float _y, float _scale, int health) {
    super(_x, _y, _scale, health);

    set_collider_circle(25 * scale);
  }

  public void move(EDirection direction) {

    y += 2.5f;

  }

  public void draw() {

    Vector2 pos = newVector2(x, y);

    DrawCircleV(pos, 25 * scale, BLUE);

  }

}
