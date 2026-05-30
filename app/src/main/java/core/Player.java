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

  private Timer shooting_cooldown;
  private float shooting_cooldown_modifier;

  private float base_speed = 5;
  private float speed_modifier = 1;

  public Player(int x, int y, float scale) {
    super(x, y, scale);

    this.point1 = newVector2(x, y - 10 * scale);
    this.point2 = newVector2(x - 10 * scale, y + 10 * scale);
    this.point3 = newVector2(x + 10 * scale, y + 10 * scale);

    this.shoot_point = newVector2(x - 3, y - 11 * scale);

    this.shooting_cooldown = new Timer(0.5f);
  }

  public void draw() {
    this.point1 = newVector2(x, y - 10 * scale);
    this.point2 = newVector2(x - 10 * scale, y + 10 * scale);
    this.point3 = newVector2(x + 10 * scale, y + 10 * scale);
    DrawTriangle(point2, point3, point1, RAYWHITE);
  }

  public void move(EDirection direction) {

    float speed = base_speed + speed_modifier;

    if (direction == EDirection.LEFT) {
      this.x -= speed;
    } else if (direction == EDirection.RIGHT) {
      this.x += speed;
    }

    shoot_point = newVector2(x - 3, y - 11 * scale);
  }

  public void set_speed_modifier(float modifier) {
    speed_modifier = modifier;
  }

  public void set_shooting_cooldow(float modifier) {
    shooting_cooldown.set_wait_timer(modifier);
  }

  public void shoot(ArrayList<Projectile> active_projectiles) {

    if (shooting_cooldown.is_counting_down() == false) {
      Projectile p = new Projectile(shoot_point.x(), shoot_point.y(), 1);
      active_projectiles.add(p);

      shooting_cooldown.start();
    }

  }

  public void update(float game_time) {
    shooting_cooldown.update(game_time);
  }

}
