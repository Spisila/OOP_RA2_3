package core;

import com.raylib.Raylib.Vector2;

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

  public abstract Vector2 get_position();
}
