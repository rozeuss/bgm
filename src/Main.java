import bgm.BGM;
import utils.MatrixUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    //    input2 ze sprawka
//    input3 przerobiony 2, nie przechodzi
    static final String INPUT = "\\input2.txt"; //macierz przejsc
    static final String OUTPUT = "\\output.txt";
    static final String PATH = System.getProperty("user.dir");
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        List<String> inputData = readInputFile();
        int[][] matrix = createMatrix(inputData);
        if (MatrixUtils.validateInput(matrix)) {
//            int m = getM();
//            TODO ^
            int m = 1;
            BGM bgm = new BGM(matrix, m);
            while (!bgm.diagnose()) {
//                            MatrixUtils.changeMatrix
//                bgm.setStructure(new int[][]{
//                        {0, 0, 1, 1},
//                        {1, 0, 0, 1},
//                        {0, 1, 0, 0},
//                        {0, 0, 1, 0}});
            }

        } else {
            throw new IllegalArgumentException("Invalid input data.\nAllowed symbols: [ 0 , 1 ].");
        }
    }

    private static int getM() {
        System.out.print("Provide m parameter: ");
        int m = in.nextInt();
        System.out.println("m: " + m);
        return m;
    }

    private static int[][] createMatrix(List<String> inputData) {
        int[][] matrix = MatrixUtils.createMatrix(inputData);
        System.out.println("Matrix: " + Arrays.deepToString(matrix));
        return matrix;
    }

    private static List<String> readInputFile() {
        List<String> inputData = null;
        try (Stream<String> stream = Files.lines(Paths.get(PATH + INPUT))) {
            inputData = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputData;
    }
}