package org.example;

import org.example.dataframe.DataFrame;
import org.example.readers.CsvLoader;
import org.example.readers.Reader;
import org.example.runner.Environment;
import org.example.structure.ColumnTypes;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        var runner = Environment.getRunner();
        var df = new DataFrame(Map.of("names",ColumnTypes.STRING,"age",ColumnTypes.INTEGER),List.of(1,"Jenny"),List.of(55,32));
        df.show();
       // CsvLoader.loadCsv("customer_shopping_data.csv");
        String csvPath = "customer_shopping_data.csv";
        int chunkSize = 1000;
        int numThreads = 4;
        int totalLines = 10000; // set this manually or compute dynamically

        Reader.loadCsvInChunks(csvPath, chunkSize, numThreads, totalLines);

    }
}