package entities.enemy;

import static com.raylib.Colors.BLUE;
import static com.raylib.Helpers.newVector2;
import static com.raylib.Raylib.DrawCircleV;


import com.raylib.Raylib.Vector2;

import engine.EDirection;
import engine.Shape;

public class Alien extends Enemy  {

  public Alien(float _x, float _y, float _scale, int health, Shape _collision_shape) {

    super(_x, _y, _scale, health, _collision_shape);

  }

  public void move(EDirection direction) {

    y += 3.5f;

  }

  public void draw() {

    Vector2 pos = newVector2(x, y);

    DrawCircleV(pos, 25 * scale, BLUE);

  }

}
