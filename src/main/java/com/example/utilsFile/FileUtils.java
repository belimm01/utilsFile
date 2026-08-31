package com.example.utilsFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedHashSet;
import java.util.stream.Stream;

class FileUtils {

  private FileUtils() {}

  static void saveText(String fileName, String content) throws IOException {
    Files.write(
        Path.of(fileName),
        content.getBytes(StandardCharsets.UTF_8),
        StandardOpenOption.CREATE,
        StandardOpenOption.TRUNCATE_EXISTING);
  }

  static LinkedHashSet<String> readTxt(String fileName) {
    var lineSet = new LinkedHashSet<String>();
    try (Stream<String> lines = Files.lines(Path.of(fileName), StandardCharsets.UTF_8)) {
      lines.forEach(lineSet::add);
    } catch (IOException ignored) {
    }
    return lineSet;
  }

  static void analyzeData(String directory, String needle) {
    try (Stream<Path> paths = Files.walk(Path.of(directory))) {
      paths
          .filter(Files::isRegularFile)
          .forEach(
              file -> {
                try {
                  Files.readAllLines(file, StandardCharsets.UTF_8).stream()
                      .filter(line -> line.contains(needle))
                      .forEach(line -> System.out.println(file));
                } catch (IOException e) {
                  e.printStackTrace();
                }
              });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
