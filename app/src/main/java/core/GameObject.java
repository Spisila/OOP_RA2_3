package core;

import static com.raylib.Helpers.newVector2;

public class GameObject {
  
  protected float x;
  protected float y;

  protected float scale;

  public GameObject(float x, float y, float scale) {
    this.x = x;
    this.y = y;
    this.scale = scale;
  }

}
