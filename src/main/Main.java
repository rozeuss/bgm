package main;

import bgm.Algorithm;
import bgm.BGM;
import utils.FileUtils;
import utils.MatrixUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        List<String> inputData = FileUtils.readInputFile();
        int[][] matrix = createMatrix(inputData);
        if (MatrixUtils.validateInput(matrix)) {
            int m = getM();
            Algorithm algorithm = new Algorithm(new BGM(matrix, m));
            int[][] optimizedStructure = algorithm.optimizeStructure();
            FileUtils.writeOutputFile(optimizedStructure);
        } else {
            throw new IllegalArgumentException("Invalid input data.\nAllowed symbols: [ 0 , 1 ].");
        }
    }

    public static void workingBgmExample() {
        List<String> inputData = FileUtils.readInputFile();
        int[][] matrix = createMatrix(inputData);
        if (MatrixUtils.validateInput(matrix)) {
            int m = getM();
            BGM bgm = new BGM(matrix, m);
            bgm.diagnose();
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
}