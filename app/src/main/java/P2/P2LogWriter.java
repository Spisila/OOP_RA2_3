package P2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class P2LogWriter {

  private String file_path;

  public P2LogWriter(String _file_path) {

    this.file_path = _file_path;

  }

  public void append_to_csv(String[] new_row) {

    try (FileWriter file_writer = new FileWriter(file_path, true);
        BufferedWriter buffered_writer = new BufferedWriter(file_writer);
        PrintWriter print_writer = new PrintWriter(buffered_writer)) {

      print_writer.println(String.join(",", new_row));

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public void write_csv(String header, String[] data) {

    try (FileWriter file_writer = new FileWriter(file_path, true);
        BufferedWriter buffered_writer = new BufferedWriter(file_writer);
        PrintWriter print_writer = new PrintWriter(buffered_writer)) {

      print_writer.println(header);

      for (int i = 0; i < data.length; i++) {

        print_writer.println(data[i]);

      }

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
