package com.company;

import java.util.Random;

public class Sort {
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        double timeForBubbleSort = timeForBubbleSort(5);
        System.out.println("Среднее время сортировки пузырьком " + timeForBubbleSort + " мс.");
        double timeForSelectionSort = timeForSelectionSort(5);
        System.out.println("Среднее время сортировки выбором " + timeForSelectionSort + " мс.");
        double timeForInsertionSort = timeForInsertionSort(5);
        System.out.println("Среднее время сортировки вставкой " + timeForInsertionSort + " мс.");
    }

    private static double timeForBubbleSort(int itr) {
        double sum = 0;
        for (int i = 0; i < itr; i++) {
            int[] array = generateArray(100000);
            long start = System.currentTimeMillis();
            sortBubble(array);
            long end = System.currentTimeMillis() - start;
            sum += end;
        }
        return sum / itr;
    }

    private static double timeForSelectionSort(int itr) {
        double sum = 0;
        for (int i = 0; i < itr; i++) {
            int[] array = generateArray(100000);
            long start = System.currentTimeMillis();
            sortSelection(array);
            long end = System.currentTimeMillis() - start;
            sum += end;
        }
        return sum / itr;
    }

    private static double timeForInsertionSort(int itr) {
        double sum = 0;
        for (int i = 0; i < itr; i++) {
            int[] array = generateArray(100000);
            long start = System.currentTimeMillis();
            sortInsertion(array);
            long end = System.currentTimeMillis() - start;
            sum += end;
        }
        return sum / itr;
    }


    private static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    private static int[] generateArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = RANDOM.nextInt(-100, 100);
        }
        return array;
    }

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    private static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    private static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    public static void quickSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(int[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }
}
