package core;

import com.raylib.Raylib.Vector2;

import static com.raylib.Helpers.newVector2;

public abstract class GameObject {

  protected float x;
  protected float y;

  protected float scale;

  public GameObject(float x, float y, float scale) {
    this.x = x;
    this.y = y;
    this.scale = scale;
  }

  public abstract void move(EDirection direction);

  public abstract void draw();

  public Vector2 get_position() {
    return newVector2(x, y);
  }

  public void set_scale(float new_scale) {
    scale = new_scale;
  }

  public GameObject get_game_object() {
    return this;
  }

}
