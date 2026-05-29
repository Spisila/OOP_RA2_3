package core;

public class Timer {

  private float wait_time;
  private float timer;

  private boolean counting_down;

  public Timer(float _wait_time) {
    this.wait_time = _wait_time;

    timer = wait_time;
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

}
