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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Main {
    static int countFile = 0;

    public static void main(String[] args) throws IOException, ParseException {
        File file = new File("D:\\Projects\\JsonTree\\src\\main\\resources\\attendees.json");

        String jsonString = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray arrayList = jsonObject.getJSONArray("data");
        Map<Object, Object> map = new LinkedHashMap<>();

        for (int i = 0; i < arrayList.length(); i++) {
            JSONObject search = (JSONObject) arrayList.get(i);
            map.putAll(analyze(search));
            readJsonWriteCsv(map);
        }
    }

    private static Map<Object, Object> analyze(JSONObject search) {
        Map<Object, Object> map = new LinkedHashMap<>();
        Set<String> keys = search.keySet();

        for (String key : keys) {
            map.putAll(checkKeysHaveObjectOrArray(key, search));
        }

        return map;
    }

    private static Map<Object, Object> checkKeysHaveObjectOrArray(String key, JSONObject search) {
        Map<Object, Object> map = new LinkedHashMap<>();
        if (search.get(key).getClass() == JSONArray.class) {
            JSONArray array = (JSONArray) search.get(key);

            if (array.length() != 0) {
                map.putAll(checkValue(array));
            }
        } else {
            map.put(key, search.get(key));
        }

        return map;
    }

    static Map<Object, Object> checkValue(JSONArray array) {
        Map<Object, Object> map = new LinkedHashMap<>();
        for (int i = 0; i < array.length(); i++) {

            if (array.get(i).getClass().equals(JSONObject.class)) {
                JSONObject search = (JSONObject) array.get(i);
                analyze(search);
            }

        }

        return map;
    }

    static void readJsonWriteCsv(Map<Object, Object> map) throws IOException {
        FileWriter outputFile = new FileWriter("D:\\Projects\\JsonTree\\src\\main\\resources\\tst" + countFile + ".csv");
        CSVWriter writer = new CSVWriter(outputFile);

        String[] keys = map.keySet().toArray(new String[0]);
        String[] values = new String[map.size()];

        Object[] checkNull = map.values().toArray();

        int count = 0;
        for (Object o : checkNull) {
            if (o.equals(null)) {
                values[count] = "null";
            } else {
                values[count] = o.toString();
            }
            count++;
        }

        writer.writeNext(keys);
        writer.writeNext(values);
        writer.close();
        countFile++;
    }
}
