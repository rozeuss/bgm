package utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MatrixUtils {

    private static final List<String> VALUES = Arrays.asList("0", "1", "x", "X");

    private MatrixUtils() {
    }

    public static String[][] createMatrix(List<String> list) {
        int matrixSize = list.get(0).length();
        String[][] matrix = new String[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                matrix[i][j] = Character.toString(list.get(i).charAt(j));
            }
        }
        return matrix;
    }

    public static boolean validateInput(String[][] matrix) {
        return checkValues(matrix) && checkMajorDiagonal(matrix);
    }

    private static boolean checkMajorDiagonal(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            if (!"X".equalsIgnoreCase(matrix[i][i])) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkValues(String[][] matrix) {
        Stream<String[]> stringArrayStream = Arrays.stream(matrix);
        Stream<String> stringStream = stringArrayStream.flatMap(Arrays::stream);
        return stringStream.allMatch(VALUES::contains);
    }

}
