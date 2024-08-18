package org.lucky.springinterview;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidateTrip {

    public static void main(String[] args) throws IOException {
        String base_folder = "C:\\Users\\a818517\\Downloads\\test\\compaire";
        String topic_file = base_folder + "/bosch-trip.csv";
        String validate_file = base_folder+"/bosch_missing_17th.csv";

        Set<String> db_trip_list = Files.lines(Path.of(validate_file))
                .skip(1)
//                .limit(10)
                .map(line -> line.split(",")[2].replace("\"", ""))
                .collect(Collectors.toSet());

        int db_trip_count = db_trip_list.size();
        System.out.println("Db trip>> "+db_trip_count);

        Set<String> topic_trip = Files.lines(Path.of(topic_file))
                .filter(line -> line.contains("2024-06-17"))
                .map(line -> line.split(",")[2].replace("\"", ""))
                .collect(Collectors.toSet());

        int topic_trip_count = topic_trip.size();
        System.out.println("topic trip>> "+topic_trip_count);
        System.out.println("Diff between db and topic >> "+ (db_trip_count - topic_trip_count));
        db_trip_list.removeAll(topic_trip);
//        System.out.println(db_trip_list.size());

//        System.out.println("Bosch message count >> "+Files.lines(Path.of(topic_file)).count());

        db_trip_list.stream().limit(10).forEach(System.out::println);

    }
}
