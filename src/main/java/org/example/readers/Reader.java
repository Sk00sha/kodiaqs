package org.example.readers;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

public interface Reader {

    static void processCsvChunk(String path, int startLine, int chunkSize) {
        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            String[] line;
            int lineCount = 0;

            // Skip lines before the chunk
            while (lineCount < startLine && (line = reader.readNext()) != null) {
                lineCount++;
            }

            // Read chunk
            int processed = 0;
            while (processed < chunkSize && (line = reader.readNext()) != null) {
                System.out.println("Thread " + Thread.currentThread().getName() + " - " + Arrays.toString(line));
                processed++;
            }

        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    static void loadCsvInChunks(String path, int chunkSize, int numThreads, int totalLines) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<Future<?>> futures = new ArrayList<>();

        for (int start = 0; start < totalLines; start += chunkSize) {
            int finalStart = start;
            futures.add(executor.submit(() -> processCsvChunk(path, finalStart, chunkSize)));
        }

        for (Future<?> f : futures) {
            try {
                f.get(); // wait for all chunks
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }
}
