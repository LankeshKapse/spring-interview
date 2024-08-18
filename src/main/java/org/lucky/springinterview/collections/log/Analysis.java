package org.lucky.springinterview.collections.log;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Analysis {

    public static void main(String[] args) throws IOException {
        String folder = "C:\\Users\\a818517\\Downloads\\vm3\\vm3";
        Files.list(Path.of(folder))
                .flatMap(Analysis::getStream)
                .forEach(System.out::println);


    }

    private static Stream<String> getStream(Path path) {
        try {
            return Files.lines(path, StandardCharsets.ISO_8859_1).filter(line -> line.toLowerCase().contains("entering poi") && line.contains("alertid=3289"));
        } catch (IOException e) {
            return Stream.empty();
        }
    }
}
