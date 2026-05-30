package core;

import static com.raylib.Colors.BLUE;
import static com.raylib.Helpers.newRectangle;
import static com.raylib.Helpers.newVector2;
import static com.raylib.Raylib.CheckCollisionCircleRec;
import static com.raylib.Raylib.CheckCollisionCircles;
import static com.raylib.Raylib.CheckCollisionRecs;
import static com.raylib.Raylib.DrawCircleV;
import static com.raylib.Raylib.DrawRectangleV;

import com.raylib.Raylib.Rectangle;
import com.raylib.Raylib.Vector2;

public class Collider {

  private Shape collision_shape;

  public Collider(Shape _collision_shape) {
    this.collision_shape = _collision_shape;
  }

  public Shape get_collision_shape() {
    return collision_shape;
  }

  public void draw_collision_shape() {

    if (collision_shape instanceof Circle circle_collider) {

      DrawCircleV(circle_collider.get_center(), circle_collider.radius, BLUE);

    } else if (collision_shape instanceof Rect rect_collider) {

      DrawRectangleV(rect_collider.get_center(), newVector2(rect_collider.width, rect_collider.height), BLUE);

    }

  }

  public boolean check_collision(Collider checking_against) {

    Shape versus_shape = checking_against.get_collision_shape();

    if (collision_shape instanceof Circle circle_collider) {

      Vector2 circle_center = circle_collider.get_center();
      float circle_radius = circle_collider.get_radius();

      if (versus_shape instanceof Circle versus_circle) {

        Vector2 versus_center = versus_circle.get_center();
        float versus_radius = versus_circle.get_radius();

        return CheckCollisionCircles(circle_center, circle_radius, versus_center, versus_radius);

      }

      if (versus_shape instanceof Rect versus_rect) {

        Vector2 versus_corner = versus_rect.get_center();
        Rectangle versus_rectangle = newRectangle(versus_corner.x(), versus_corner.y(), versus_rect.get_width(),
            versus_rect.get_height());

        return CheckCollisionCircleRec(circle_center, circle_radius, versus_rectangle);

      }

    } else if (collision_shape instanceof Rect rect_collider) {

      Vector2 rect_corner = rect_collider.get_center();
      float rect_width = rect_collider.get_width();
      float rect_height = rect_collider.get_height();

      Rectangle rectangle = newRectangle(rect_corner.x(), rect_corner.y(), rect_width, rect_height);

      if (versus_shape instanceof Circle versus_circle) {

        Vector2 versus_center = versus_circle.get_center();
        float versus_radius = versus_circle.get_radius();

        return CheckCollisionCircleRec(versus_center, versus_radius, rectangle);

      }

      if (versus_shape instanceof Rect versus_rect) {

        Vector2 versus_corner = versus_rect.get_center();
        Rectangle versus_rectangle = newRectangle(versus_corner.x(), versus_corner.y(), versus_rect.get_width(),
            versus_rect.get_height());

        return CheckCollisionRecs(rectangle, versus_rectangle);

      }

    } else {
      return false;
    }

    return false;

  }
}
