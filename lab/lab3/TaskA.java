package lab3;

import java.util.Scanner;

/**
 * Дан массив arr из N элементов. Назовем инверсией пару индексов (i, j),
 * таких что i < j и arr[i] > arr[j]. Требуется определить количество инверсий в
 * данном массиве и вывести их. Дать комментарии. Вычислить сложность.
 */


public class TaskA {
    public static void main(String[] args) {
        int[] array = inputArray();
        System.out.println(countInversion(array));

    }
    //заполняем массив
    public static int[] inputArray() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите длину массива: ");
        int size = sc.nextInt();
        int[] array = new int[size];
        System.out.println("Введите элементы массива");
        for (int i = 0; i < size; i++) {
            System.out.print("array[" + i + "]: ");
            array[i] = sc.nextInt();
        }
        return array;
    }

    /**подсчет инверсий.
     * Принцип работы: первый элемент принимается за i, все остальные за j.
     * Далее идет проверка где arr[i]>arr[j] подсчет инверсий пока не закончится массив.
     * После проверки всех j для текущего i, i увеличивается на 1, а j начинается с нового i+1 и так далее
     */

    private static int countInversion(int[] array) {
        int count = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    count++;
                }
            }
        }
        return count;
    }
}
