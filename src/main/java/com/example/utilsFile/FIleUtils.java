package com.example.utilsFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Stream;

class FIleUtils {

    static void saveText(String fileName, String content) throws IOException {
        Files.write(Paths.get(fileName),
                content.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }

    static LinkedHashSet<String> readTxt(String fileName) {
        LinkedHashSet<String> hashSet = new LinkedHashSet<>();
        Path path = Paths.get(fileName);
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(hashSet::add);

        } catch (IOException ignored) {
        }
        return hashSet;
    }

    static void anylizeData(String fileName){
        try (Stream<Path> paths = Files.walk(Paths.get(fileName))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(file -> {
                        try {
                            List<String> stream = Files.readAllLines(Paths.get(file.toString()));
                            stream
                                    .stream()
                                    .filter(line -> line.contains("420604764033"))
                                    .forEach(line -> System.out.println(file.toString()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
