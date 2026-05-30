package entities.enemy;

import com.raylib.Raylib.Vector2;

import engine.Collider;
import engine.GameObject;
import engine.IDamageable;
import engine.Shape;

public abstract class Enemy extends GameObject implements IDamageable {

  private int health;

  private boolean alive;

  private Collider collider;

  public Enemy(float x, float y, float scale, int health, Shape _shape) {
    super(x, y, scale, _shape);

    this.health = health;
    this.alive = true;

    this.collider = new Collider(shape);
  }

  public void takeDamage(int damage) {

    health -= damage;

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

}
