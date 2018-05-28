package combinatorics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Permutation {
//
//    /*Driver function to check for above function*/
//    public static void main(String[] args) {
//        int arr[] = {1, 2, 3};
//        int r = 2;
//        printCombination(arr, r);
//    }
//
//    /* arr[]  ---> Input Array
//    data[] ---> Temporary array to store current combinatorics
//    start & end ---> Staring and Ending indexes in arr[]
//    index  ---> Current index in data[]
//    r ---> Size of a combinatorics to be printed */
//    static void combinationUtil(int arr[], int data[], int start, int index, int r) {
//        int end = arr.length - 1;
//        if (index == r) {
//            for (int i = 0; i < r; i++) {
//                System.out.print(data[i] + " ");
//            }
//            return;
//        }
//
//        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
//            data[index] = arr[i];
//            combinationUtil(arr, data, i + 1, index + 1, r);
//        }
//    }
//
//
//    static void printCombination(int arr[], int r) {
//        int data[] = new int[r];
//        combinationUtil(arr, data, 0, 0, r);
//    }

    public static List<Set<Integer>> getSubsets(List<Integer> superSet, int m) {
        List<Set<Integer>> res = new ArrayList<>();
        getSubsets(superSet, m, 0, new HashSet<>(), res);
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 3, 4};
        List<Set<Integer>> subsets = getSubsets(Arrays.stream(arr).boxed().collect(Collectors.toList()), 2);
        subsets.forEach(System.out::println);
    }

    private static void getSubsets(List<Integer> superSet, int k, int idx, Set<Integer> current, List<Set<Integer>> solution) {
        //successful stop clause
        if (current.size() == k) {
            solution.add(new HashSet<>(current));
            return;
        }
        //unseccessful stop clause
        if (idx == superSet.size()) {
            return;
        }
        Integer x = superSet.get(idx);
        current.add(x);
        //"guess" x is in the subset
        getSubsets(superSet, k, idx + 1, current, solution);
        current.remove(x);
        //"guess" x is not in the subset
        getSubsets(superSet, k, idx + 1, current, solution);
    }
}