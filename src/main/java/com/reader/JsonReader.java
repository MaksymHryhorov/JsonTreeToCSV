package com.reader;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class JsonReader {

    /**
     * Read Json
     * @return json string format
     */
    @SneakyThrows
    public String readJson() {
        File file = new File("D:\\Projects\\JsonTree\\src\\main\\resources\\attendees.json");

        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }
}
