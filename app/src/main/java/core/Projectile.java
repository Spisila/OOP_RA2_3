package core;

import static com.raylib.Raylib.DrawRectangleV;
import static com.raylib.Colors.RAYWHITE;
import static com.raylib.Helpers.newVector2;
import static com.raylib.Raylib.*;

import com.raylib.Raylib.Vector2;

public class Projectile extends GameObject {

  Vector2 size; 

  public Projectile(float _x, float _y, float _scale) {
    super(_x, _y, _scale);

    this.size = newVector2(6 * scale, 10 * scale);
  }

  public void draw() {

    DrawRectangleV(get_position(), size, RAYWHITE);

  }

  public void move(EDirection direction) {

    y -= 5;

  }



}
