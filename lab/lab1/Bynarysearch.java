package lab1;

/* Реализовать алгоритм бинарного поиска двумя способами */

import java.util.Scanner;

public class Bynarysearch {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Кол-во элементов: ");

        int size = scanner.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        bubbleSort(arr);

        System.out.print("Отсортированный массив: ");
        outmass(arr);

        System.out.print("Введите элемент для поиска: ");
        int element = scanner.nextInt();

        System.out.println("Метод бинарного поиска:");
        binary(arr, element);

        System.out.println("Метод бинарного поиска рекурсией:");
        recbinary(arr, element);

        scanner.close();
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void outmass(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static int binary(int[] arr, int element) {
        // Проверка на пустой массив
        if (arr.length == 0) {
            System.out.println("Ошибка: массив пустой!");
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;
        boolean found = false;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            int current = arr[middle];

            if (current == element) {
                System.out.println("Место элемента: " + middle);
                found = true;
                return middle;
            }

            if (current > element) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        if (!found) {
            System.out.println("Элемент " + element + " не найден в массиве");
        }
        return -1;
    }

    public static int recbinary(int[] arr, int element, int left, int right) {
        // Проверка на пустой массив должна быть ПЕРВОЙ
        if (arr.length == 0) {
            System.out.println("Ошибка: массив пустой!");
            return -1;
        }

        // Затем проверка границ
        if (left > right) {
            System.out.println("Элемент " + element + " не найден в массиве");
            return -1;
        }

        // И только потом работа с элементами массива
        int middle = left + (right - left) / 2;
        int current = arr[middle];

        if (current == element) {
            return middle;
        }

        if (current > element) {
            return recbinary(arr, element, left, middle - 1);
        } else {
            return recbinary(arr, element, middle + 1, right);
        }
    }

    static void recbinary(int[] array, int element) {
        int result = recbinary(array, element, 0, array.length - 1);
        if (result != -1) {
            System.out.println("Место элемента: " + result);
        }
    }
}