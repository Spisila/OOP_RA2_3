package engine;

import com.raylib.Raylib.Vector2;

public class Circle extends Shape {

  float radius;

  public Circle(Vector2 _center, float _radius) {
    super(_center);

    radius = _radius;

  }

  public float get_radius() {
    return radius;
  }

}
