package P2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class P2LogWriter {

  private String file_path;

  public P2LogWriter(String _file_path) {

    this.file_path = _file_path;

  }

  public void append_to_csv(String new_row) {

    try (FileWriter file_writer = new FileWriter(file_path, true);
        BufferedWriter buffered_writer = new BufferedWriter(file_writer);
        PrintWriter print_writer = new PrintWriter(buffered_writer)) {

      print_writer.println(String.join(",", new_row));

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public void clear_csv() {

    try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(file_path))) {
      writer.write("");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
