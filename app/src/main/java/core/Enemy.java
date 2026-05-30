package core;

import com.raylib.Raylib.Vector2;

public abstract class Enemy extends GameObject implements IDamageable {

  private int health;

  private boolean alive;

  private Collider collider;

  public Enemy(float x, float y, float scale, int health) {
    super(x, y, scale);

    this.health = health;
    this.alive = true;
  }

  public void takeDamage(int damage) {

    health -= damage;

    System.out.println("MORTYE");
    if (health <= 0) {
      alive = false;
    }

  }

  public boolean is_alive() {
    return alive;
  }

  public void update() {
    Vector2 t_position = get_position();
    this.get_collider().get_collision_shape().set_center(t_position.x(), t_position.y());
  }

  public Collider get_collider() {
    return collider;
  }

  public void set_collider_circle(float radius) {

    Circle c = new Circle(get_position(), radius);

    collider = new Collider(c);

  }

  public void set_collider_rect(float width, float height) {

    Rect r = new Rect(get_position(), width, height);

    collider = new Collider(r);

  }

}
