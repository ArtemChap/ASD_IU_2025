package lab3;


/**
 * Вам предоставлен целочисленный массив nums, отсортированный в
 * порядке неубывания. Определите, возможно ли разбить nums на одну или
 * несколько подпоследовательностей таким образом, чтобы выполнялись
 * оба следующих условия:
 * • Каждая подпоследовательность представляет собой
 * последовательную возрастающую последовательность (т.е. каждое
 * целое число ровно на единицу больше предыдущего целого числа).
 * • Все подпоследовательности имеют длину 3 или более.
 * Верните значение true, если вы можете разделить числа в
 * соответствии с вышеуказанными условиями, или значение false в
 * противном случае. Так же необходимо вывести последовательности.
 * Подпоследовательность массива – это новый массив, который
 * формируется из исходного массива путем удаления некоторых (может не
 * быть ни одного) элементов без изменения относительного положения
 * остальных элементов. (т.е. [1,3,5] является подпоследовательностью
 * [1,2,3,4,5], в то время как [1,3,2] не является).
 * Примечание. Сложность должна быть O(n). Докажите сложность.
 */

import static lab3.TaskA.inputArray;

public class TaskC {

    public static void main(String[] args) {
        int[] nums = inputArray();
        System.out.println(canSplit(nums));
    }

    private static boolean canSplit(int[] nums) {
        if (nums.length < 3) return false;

        int sequenceCount = 0;
        int currentLength = 1;
        boolean valid = true;

        /*
         Сложность: O(n) - один проход по массиву
         Вложенных циклов нет, все операции O(1)
        */
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                currentLength++;
            } else {

                if (currentLength < 3) {
                    valid = false;
                } else {
                    sequenceCount++;
                }
                currentLength = 1;
            }
        }


        if (currentLength < 3) {
            valid = false;
        } else {
            sequenceCount++;
        }


        if (valid && sequenceCount > 0) {
            System.out.println("Массив можно разбить на " + sequenceCount + " последовательностей");
            printSequences(nums);
            return true;
        }

        return false;
    }

    private static void printSequences(int[] nums) {
        System.out.print("Последовательности: ");
        int start = 0;

        for (int i = 1; i <= nums.length; i++) {
            if (i == nums.length || nums[i] != nums[i - 1] + 1) {
                if (i - start >= 3) {
                    System.out.print("[");
                    for (int j = start; j < i; j++) {
                        System.out.print(nums[j]);
                        if (j < i - 1) System.out.print(", ");
                    }
                    System.out.print("] ");
                }
                start = i;
            }
        }
        System.out.println();
    }
}