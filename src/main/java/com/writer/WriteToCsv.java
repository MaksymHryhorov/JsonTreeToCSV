package com.writer;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class WriteToCsv {
    private int countFile = 0;

    /**
     * Write data to the csv file
     * @param map (key - name of column; value - values of column)
     * @throws IOException
     */
    public void write(Map<Object, Object> map) throws IOException {
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
