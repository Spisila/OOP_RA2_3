package core;

import static com.raylib.Colors.RAYWHITE;
import static com.raylib.Helpers.newRectangle;
import static com.raylib.Helpers.newVector2;
import static com.raylib.Raylib.*;

import com.raylib.Raylib.Rectangle;
import com.raylib.Raylib.Vector2;

public class Projectile extends GameObject {

  private Vector2 size; 

  private Collider collider;

  
  public Projectile(float _x, float _y, float _scale) {
    super(_x, _y, _scale);
    
    this.size = newVector2(6 * scale, 10 * scale);
    
    set_collider_rect(6 * _scale, 10 * _scale);
  }


  public void draw() {

    DrawRectangleV(get_position(), size, RAYWHITE);

  }

  public void move(EDirection direction) {

    y -= 5;

  }
  
  public void update() {
    collider.get_collision_shape().set_center(x, y);
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
