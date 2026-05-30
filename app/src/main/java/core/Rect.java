package core;

import com.raylib.Raylib.Vector2;

public class Rect extends Shape {

  float width;
  float height;

  public Rect(Vector2 _corner, float _width, float _height) {

    super(_corner);

    this.width = _width;
    this.height = _height;

  }

  public float get_width() {
    return width;
  }

  public float get_height() {
    return height;
  }

}
