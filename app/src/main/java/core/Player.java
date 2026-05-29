package core;

import java.util.ArrayList;

import static com.raylib.Raylib.*;

import com.raylib.Raylib.Vector2;

import static com.raylib.Helpers.newVector2;
import static com.raylib.Colors.*;

public class Player extends GameObject {

  private Vector2 point1;
  private Vector2 point2;
  private Vector2 point3;

  private Vector2 shoot_point;

  public Player(int x, int y, float scale) {
    super(x, y, scale);

    this.point1 = newVector2(x, y - 10 * scale);
    this.point2 = newVector2(x - 10 * scale, y + 10 * scale);
    this.point3 = newVector2(x + 10 * scale, y + 10 * scale);

    this.shoot_point = newVector2(x - 3, y - 11 * scale);
  }

  public void draw() {
    this.point1 = newVector2(x, y - 10 * scale);
    this.point2 = newVector2(x - 10 * scale, y + 10 * scale);
    this.point3 = newVector2(x + 10 * scale, y + 10 * scale);
    DrawTriangle(point2, point3, point1, RAYWHITE);
  }

  public void move(EDirection direction) {

    if (direction == EDirection.LEFT) {
      this.x -= 5;
    } else if (direction == EDirection.RIGHT) {
      this.x += 5;
    }

    shoot_point = newVector2(x - 3, y - 11 * scale);
  }

  public void shoot(ArrayList<Projectile> active_projectiles) {

    Projectile p = new Projectile(shoot_point.x(), shoot_point.y(), 1);

    active_projectiles.add(p);

  }

}
