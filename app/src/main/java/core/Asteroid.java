package core;

import static com.raylib.Raylib.*;

import com.raylib.Raylib.Vector2;

import static com.raylib.Colors.*;

import static com.raylib.Helpers.newVector2;

public class Asteroid extends Enemy implements IDamageable {

  public Asteroid(float _x, float _y, float _scale, int health) {
    super(_x, _y, _scale, health);

  }

  public void move(EDirection direction) {

    y += 2.5f;

  }

  public void draw() {

    Vector2 pos = newVector2(x, y);
    Vector2 size = newVector2(50 * scale, 50 * scale);
    DrawRectangleV(pos, size, RED);

  }

  public void takeDamage(int damage) {

  }

}
