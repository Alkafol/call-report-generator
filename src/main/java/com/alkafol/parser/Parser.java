package com.alkafol.parser;

import com.alkafol.model.Call;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Parser {
    public static Map<Long, List<Call>> parse() throws FileNotFoundException {
        File file = new File("cdr.txt");
        Scanner in = new Scanner(file);
        Map<Long, List<Call>> numberToCallsMap = new HashMap<>();

        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] data = line.split("[ ,]+");

            Call call = parseDataToCall(data);
            long number = Long.parseLong(data[1]);

            numberToCallsMap.putIfAbsent(number, new ArrayList<>());
            numberToCallsMap.compute(number, (key, value) ->{
                value.add(call);
                return value;
            });
        }


        return numberToCallsMap;
    }

    public static Call parseDataToCall(String[] data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return new Call(
                LocalDateTime.parse(data[2], formatter),
                LocalDateTime.parse(data[3], formatter),
                data[4],
                data[0]
        );
    }
}
