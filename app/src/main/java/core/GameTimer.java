package core;

public class GameTimer {

  private float wait_time;
  private float timer;

  private boolean counting_down;

  public GameTimer(float _wait_time) {
    this.wait_time = _wait_time;

    this.timer = wait_time;
    this.counting_down = false;
  }

  public void start() {

    counting_down = true;

  }

  public void update(float game_time) {
    if (counting_down) {

      timer -= game_time;

    }

    if (timer <= 0) {
      counting_down = false;
      timer = wait_time;
    }

  }

  public boolean is_counting_down() {
    return counting_down;
  }

  public void set_wait_timer(float new_time) {

    if (new_time > 0) {
      wait_time = new_time;
    }
  }

}
