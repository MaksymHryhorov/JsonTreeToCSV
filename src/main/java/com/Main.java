package com;

import com.opencsv.CSVWriter;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        File file = new File("D:\\Projects\\JsonTree\\src\\main\\resources\\attendees.json");

        String jsonString = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray arrayList = jsonObject.getJSONArray("data");
        List<Object> objects = new ArrayList<>();

        for (int i = 0; i < arrayList.length(); i++) {
            JSONObject search = (JSONObject) arrayList.get(i);
            objects.addAll(analyze(search));
            readJsonWriteCsv(objects);
        }
    }

    private static List<Object> analyze(JSONObject search) {
        List<Object> objectList = new ArrayList<>();
        Set<String> keys = search.keySet();

        for (String key : keys) {
            objectList.addAll(checkKeysHaveObjectOrArray(key, search));
        }

        return objectList;
    }

    private static List<Object> checkKeysHaveObjectOrArray(String key, JSONObject search) {
        List<Object> objectList = new ArrayList<>();
        int count = 0;
        if (search.get(key).getClass() == JSONArray.class) {
            JSONArray array = (JSONArray) search.get(key);

            if (array.length() != 0) {
                objectList.addAll(checkValue(array));
            }
        } else {
            objectList.add(count, key);
            count++;
        }


        return objectList;
    }

    static List<Object> checkValue(JSONArray array) {
        List<Object> objectList = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {

            if (array.get(i).getClass().equals(JSONObject.class)) {
                JSONObject search = (JSONObject) array.get(i);
                analyze(search);
            }

        }

        return objectList;
    }

    static void readJsonWriteCsv(List<Object> search) throws IOException {
        FileWriter outputfile = new FileWriter("D:\\Projects\\JsonTree\\src\\main\\resources\\tst.csv");
        CSVWriter writer = new CSVWriter(outputfile);

        for (Object o : search) {
            System.out.println(o);
        }

        List<String> strings = search.stream()
                .map(object -> Objects.toString(object, null)).toList();

        String[] str = strings.stream().toArray(String[]::new);

        writer.writeNext(str);
        writer.close();

    }
}
