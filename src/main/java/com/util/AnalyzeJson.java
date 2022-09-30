package com.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class AnalyzeJson {
    /**
     * Get key set from json object and put values to the map
     * @param search (json object)
     * @return Map(Key, Value)
     */
    public Map<Object, Object> analyze(JSONObject search) {
        Map<Object, Object> map = new LinkedHashMap<>();
        Set<String> keys = search.keySet();

        for (String key : keys) {
            map.putAll(checkKeysHaveObjectOrArray(key, search));
        }

        return map;
    }

    /**
     * Check what json contains.
     * If contains array -> checkValue
     * Else put to map key and value
     * @param key name of column
     * @param search (json object)
     * @return Map(Key, Value)
     */
    private Map<Object, Object> checkKeysHaveObjectOrArray(String key, JSONObject search) {
        Map<Object, Object> map = new LinkedHashMap<>();
        if (search.get(key).getClass() == JSONArray.class) {
            JSONArray array = (JSONArray) search.get(key);

            if (array.length() != 0) {
                map.putAll(checkValue(key, array));
            }
        } else {
            map.put(key, search.get(key));
        }

        return map;
    }

    /**
     * Check if array contains json object class, goto -> analyze
     * Else put key and value to map
     * @param key name of column
     * @param array json array
     * @return Map(Key, Value)
     */
    private Map<Object, Object> checkValue(String key, JSONArray array) {
        Map<Object, Object> map = new LinkedHashMap<>();
        for (int i = 0; i < array.length(); i++) {

            if (array.get(i).getClass().equals(JSONObject.class)) {
                JSONObject search = (JSONObject) array.get(i);
                analyze(search);
            } else {
                map.put(key, array.get(i));
            }

        }

        return map;
    }
}
