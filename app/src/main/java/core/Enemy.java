package core;

import static com.raylib.Helpers.newVector2;
import com.raylib.Raylib.Vector2;

public abstract class Enemy extends GameObject implements IDamageable {

  private int health;

  public Enemy(float x, float y, float scale, int health) {
    super(x, y, scale);

    this.health = health;
  }

  public void takeDamage(int damage) {

  }

}
