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

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        File file = new File("D:\\Projects\\JsonTree\\src\\main\\resources\\attendees.json");

        String jsonString = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray arrayList = jsonObject.getJSONArray("data");

        for (int i = 0; i < arrayList.length(); i++) {
            JSONObject search = (JSONObject) arrayList.get(i);
            analyze(search);
        }


    }

    private static void analyze(JSONObject search) {
        List<Object> objectList = new ArrayList<>();
        Set<String> keys = search.keySet();

        for (String key : keys) {
            objectList.addAll(checkKeysHaveObjectOrArray(key, search));
        }

        for(Object o : objectList) {
            System.out.println(o);
        }
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

            if (array.get(0).getClass().equals(JSONObject.class)) {
                JSONObject search = (JSONObject) array.get(0);
                analyze(search);
            }

            objectList.add(i, array.get(i));
        }

        return objectList;
    }

    static void readJsonWriteCsv(JSONObject search) throws IOException {
        FileWriter outputfile = new FileWriter("D:\\Projects\\JsonTree\\src\\main\\resources\\tst.csv");
        CSVWriter writer = new CSVWriter(outputfile);

        writer.close();

    }
}
