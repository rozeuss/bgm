package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {

    //    input2 ze sprawka
    //    input3 przerobiony 2, nie przechodzi
    private static final int NUMBER = 1;
    private static final String INPUT = "input" + NUMBER + ".txt";
    private static final String OUTPUT = "output" + NUMBER + ".txt";
    private static final String PATH = System.getProperty("user.dir");

    private FileUtils() {
    }

    public static List<String> readInputFile() {
        List<String> inputData = null;
        try (Stream<String> stream = Files.lines(Paths.get(PATH, INPUT))) {
            inputData = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputData;
    }

    public static void writeOutputFile(int[][] output) {
        List<String> lines = new ArrayList<>();
        StringBuilder line;
        for (int[] outputRow : output) {
            line = new StringBuilder();
            for (int j = 0; j < output.length; j++) {
                line.append(outputRow[j]);
            }
            lines.add(line.toString());
        }
        try {
            Files.write(Paths.get(PATH, OUTPUT), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
