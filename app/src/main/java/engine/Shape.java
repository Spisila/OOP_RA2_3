package engine;

import static com.raylib.Helpers.newVector2;

import com.raylib.Raylib.Vector2;

public abstract class Shape {

  Vector2 center;

  public Shape(Vector2 _center) {
    center = _center;
  }

  public Vector2 get_center() {
    return center;
  }

  public void set_center(float x, float y) {
    center = newVector2(x, y);
  }

}
