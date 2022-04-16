package com.example.parsingproj;

import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;


@SpringBootApplication
public class ParsingProjApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParsingProjApplication.class, args);
        BlockingQueue<String> data = new LinkedBlockingQueue<>();
        List<String> permittedExt = new ArrayList<String>(Arrays.asList("json", "csv"));
        List<String> res = new ArrayList<>();
        for (String arg : args) {
            String ext = null;
            try {
                ext = FilenameUtils.getExtension(arg);
                if (!permittedExt.contains(ext))
                    throw new IllegalArgumentException();
                System.out.println(ext);
            } catch (IllegalArgumentException e) {
                System.out.println("Expected permitted extension");
            }
            if (Objects.equals(ext, "json")){
                JsonConverter.convert(arg, res);
            } else {
                CsvConverter.convert(arg, res);
            }
        }
        var count = 0;
        for (int i = 1; i < res.size() + 1; i++) {
            System.out.println("{\"id\"=\"" + i +"\"," + res.get(i - 1) + "}");
        }
    }
}
