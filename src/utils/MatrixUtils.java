package utils;

import combinatorics.Permutation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

    public static void main(String[] args) {
//        int[][] arr = new int[][] {
//            {9, 4, 1, 1},
//            {9, 4, 1, 1},
//            {9, 4, 1, 0},
//            {9, 4, 1, 0}};
//        System.out.println("MACIERZ WEJSCIOWA");
//        System.out.println(Arrays.deepToString(arr));
//
//        int[] column = getColumn(arr, 0);
//        System.out.println("Col 1 " + Arrays.toString(column));
//        int[] column2 = getColumn(arr, 1);
//        System.out.println("Col 2 " + Arrays.toString(column2));
//        int[] column3 = getColumn(arr, 2);
//        System.out.println("Col 3 " + Arrays.toString(column3));
//        int[] column4 = getColumn(arr, 3);
//        System.out.println("Col 4 " + Arrays.toString(column4));
//
//        System.out.println("WYZERUJ 1 KOLUMNE");
//        int[][] resultArr = setColumn(arr, 1, new int[] {0, 0, 0, 0});
//        System.out.println(Arrays.deepToString(resultArr));

        System.out.println("ARR TO OPTIMIZE");
        int[][] arrToOptimize = new int[][] {
            {0, 0, 1, 1},
            {1, 0, 1, 1},
            {0, 1, 0, 1},
            {0, 0, 1, 0}};
        System.out.println(Arrays.deepToString(arrToOptimize));
        System.out.println();


        List<Column> columnsToOptimize = getColumnsToOptimize(arrToOptimize, 1);
        System.out.println();


        System.out.println("COLUMNS TO OPTIMIZE");
        columnsToOptimize.forEach(System.out::println);
        System.out.println();


        System.out.println("COLUMN SUBSETS");
        Map<Integer, List<Set<Integer>>> columnSubsets = new HashMap<>();
        for (Column c : columnsToOptimize) {
            columnSubsets.put(c.getColumnNumber(), Permutation.getSubsets(c.getPositions(), 1));
        }
        System.out.println(columnSubsets);
        System.out.println();


        Map<Integer, List<Column>> optimizedColumnsMap = new HashMap<>();
        int iterator = 0;
        for (Map.Entry<Integer, List<Set<Integer>>> integerListEntry : columnSubsets.entrySet()) {
            System.out.println();
            System.out.println(integerListEntry);
            List<Column> optimizedColumns = new ArrayList<>();
            for (Set<Integer> subset : integerListEntry.getValue()) {
                for (Integer integer : subset) {
                    Column optimizedCol;
                    System.out.print(integer + " ");
                    int[] data = columnsToOptimize.get(iterator).getData().clone();
                    for (int i = 0; i < data.length; i++) {
                        if (i == integer) {
                            data[i] = 1;
                        } else {
                            data[i] = 0;
                        }
                    }
                    optimizedCol = new Column(columnsToOptimize.get(iterator).getColumnNumber(), data);
                    System.out.println(optimizedCol);
                    optimizedColumns.add(optimizedCol);
                }
                optimizedColumnsMap.put(integerListEntry.getKey(), optimizedColumns);
            }
            iterator++;
        }
        System.out.println(optimizedColumnsMap);

//        for (Set<Integer> subset : subsets) {
//            for (Integer integer : subset) {
//                Column optimizedCol;
//                System.out.print(integer + " ");
//                int[] data = columnsToOptimize.get(0).getData().clone();
//                for (int i = 0; i < data.length; i++) {
//                    if (i == integer) {
//                        data[i] = 1;
//                    } else {
//                        data[i] = 0;
//                    }
//                }
//                optimizedCol = new Column(columnsToOptimize.get(0).getColumnNumber(), data);
//                System.out.println(optimizedCol);
//                optimizedColumns.add(optimizedCol);
//            }
//        }
//        optimizedColumns.forEach(System.out::println);
    }

    public static List<Column> getColumnsToOptimize(int[][] arr, int m) {
        List<Column> columns = new ArrayList<>();
        List<Integer> positions;
        int counter;
        for (int i = 0; i < arr.length; i++) {
            counter = 0;
            positions = new ArrayList<>();
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[j][i]);
                if (arr[j][i] == 1) {
                    counter++;
                    positions.add(j);
                }
            }
            if (counter > m) {
                columns.add(new Column(i, MatrixUtils.getColumn(arr, i), positions));
            }
            System.out.println();
        }

        return columns;
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
