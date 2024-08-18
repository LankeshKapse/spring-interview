package org.lucky.springinterview;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

public class FetchTrip {

    private static final String str = "Message transfer to raw bkp topic";

    private static Stream<String> getLInes(Path path){
        try {
            System.out.println("Extracting trip for >> "+path);
            return Files.lines(path).parallel()
                    .filter(s -> s.contains(str))
                   .filter(line -> line.contains("2024-06-22"))
                   .map(s -> s.substring(s.indexOf(str)+str.length()+1))
                   .map(String::strip)
                   .map(s -> s.replace("#",","));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        String folder = "C:\\Users\\a818517\\Downloads\\test";
        Stream<Path> list = Files
                .list(Path.of(folder))
                .filter(file -> !Files.isDirectory(file));

        Path outPath = Path.of(folder+"/bosch-trip-2024-06-22.csv");
        Files.deleteIfExists(outPath);
        Files.createFile(outPath);


        long count = list.map(FetchTrip::getLInes)
                .flatMap(stringStream -> stringStream)
                .parallel()
                .map(line -> writeData(line + "\n", outPath))
                .count();
        System.out.println("total number of trip >> "+count);

    }

    private static Path writeData(String line, Path filePath)  {
        try {
            return Files.write(filePath, line.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
