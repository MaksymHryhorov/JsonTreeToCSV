package com.util;

import com.writer.WriteToCsv;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class JsonSearch {

    /**
     * Put all key&value into the map.
     * Write map to csv file
     * @param jsonReader String format json
     */
    @SneakyThrows
    public void search(String jsonReader) {
        WriteToCsv writeToCsv = new WriteToCsv();
        AnalyzeJson json = new AnalyzeJson();

        JSONObject jsonObject = new JSONObject(jsonReader);
        JSONArray arrayList = jsonObject.getJSONArray("data");
        Map<Object, Object> map = new LinkedHashMap<>();

        for (int i = 0; i < arrayList.length(); i++) {
            JSONObject search = (JSONObject) arrayList.get(i);
            map.putAll(json.analyze(search));

            writeToCsv.write(map);
        }

        System.out.println("Files successfully created");
    }
}
