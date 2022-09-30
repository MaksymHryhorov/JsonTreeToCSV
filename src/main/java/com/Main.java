package com;

import com.reader.JsonReader;
import com.util.JsonSearch;
import lombok.SneakyThrows;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        JsonSearch jsonSearch = new JsonSearch();
        JsonReader jsonReader = new JsonReader();

        jsonSearch.search(jsonReader.readJson());
    }
}
