package utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MatrixUtils {

    private static final List<Integer> VALUES = Arrays.asList(0, 1);

    private MatrixUtils() {
    }

    public static int[][] createMatrix(List<String> list) {
        int matrixSize = list.get(0).length();
        int[][] matrix = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                matrix[i][j] = Character.getNumericValue(list.get(i).charAt(j));
            }
        }
        return matrix;
    }

    public static int[] getColumn(int[][] array, int index) {
        int[] column = new int[array[0].length];
        for (int i = 0; i < column.length; i++) {
            column[i] = array[i][index];
        }
        return column;
    }

    public static int[][] setColumn(int[][] array, int index, int[] column) {
        for (int i = 0; i < column.length; i++) {
            array[i][index] = column[i];
        }
        return array;
    }

    public static boolean validateInput(int[][] matrix) {
        return checkValues(matrix) && checkMajorDiagonal(matrix);
    }

    private static boolean checkValues(int[][] matrix) {
        Stream<int[]> intArrayStream = Arrays.stream(matrix);
        IntStream intStream = intArrayStream.flatMapToInt(Arrays::stream);
        return intStream.allMatch(VALUES::contains);
    }

    private static boolean checkMajorDiagonal(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            if (0 != matrix[i][i]) {
                throw new IllegalStateException("Major diagonal must be zeros.");
            }
        }
        return true;
    }

}
