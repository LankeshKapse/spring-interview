package org.lucky.springinterview;

import lombok.SneakyThrows;
import org.springframework.util.LinkedMultiValueMap;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindDuplicateTrips {
    private static final String str = "Message transfer to raw bkp topic";

    public static void main(String[] args) throws Exception{
        String folder = "C:\\Users\\a818517\\Downloads\\test";
        Path outPath = Path.of(folder+"/duplicate-trips/duplicates.csv");
        Files.deleteIfExists(outPath);
        Files.createFile(outPath);
         Files
                .list(Path.of(folder))
                .filter(file -> !Files.isDirectory(file))
                .flatMap(path -> {
                    try {
                        return Files.lines(path).filter(s -> s.contains(str) && !s.contains("UNKNOWN_KEY"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(s -> s.substring(s.indexOf(str) + str.length() + 1))
                .map(String::strip)
                .map(s -> {
                    String[] split = s.split("#");
                    return String.join(",", split[0],split[1], split[2]);
                })
                .collect(Collectors.toMap(line -> line.split(",")[2],
                        line -> {
                            String[] split = line.split(",");
                            return new LinkedMultiValueMap<String, String>(Map.of(split[0], new ArrayList<>(Arrays.asList(split[1]))));
//                            return new ArrayList<>(Arrays.asList(split[0],split[1]));
                        },
                        (v1, v2) -> {
//                            v1.keySet().retainAll(v2.keySet());
                            v1.forEach((key,value) -> {
                                v2.merge(key,value, (l1,l2) -> {
                                    l2.addAll(l1);
//                                    System.out.println("merging value key "+key+ " l1> "+l1+" l2> "+l2);
                                    return l2;
                                });
                            });
//                            System.out.println("v2 >> "+v2);
                            return v2;
                        }
                ))
                .entrySet()
                .stream()
                 .filter(entry -> {
                     int size = entry.getValue().values().stream().flatMap(Collection::stream).toList() .size();
                     return size > 1;
                 })
                .map(entry -> {
                    return String.join("|", entry.getKey(),entry.getValue().keySet().toString(),entry.getValue().values().stream().flatMap(Collection::stream).toList().toString());
                })
//                 .map(line -> writeData(line + "\n", outPath))
                 .limit(10)
                 .forEach(System.out::println);
    }

    private static Path writeData(String line, Path filePath)  {
        try {
            return Files.write(filePath, line.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
