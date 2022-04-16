package com.example.parsingproj;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class ReadService {

    public static void readFile(String fileName, BlockingQueue<String> data) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            for (String line; (line = br.readLine()) != null; ) {
                data.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
