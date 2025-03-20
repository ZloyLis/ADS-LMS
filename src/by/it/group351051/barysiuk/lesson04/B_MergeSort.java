package by.it.group351051.barysiuk.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Реализуйте сортировку слиянием для одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо отсортировать полученный массив.

Sample Input:
5
2 3 9 2 9
Sample Output:
2 2 3 9 9
*/
public class B_MergeSort {

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);

        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }

        mergeSort(a, 0, n - 1);
        return a;
    }

    // основной метод
    private void mergeSort(int[] a, int left, int right) {
        // Если массив содержит более одного элемента
        if (left < right) {
            // Нахождение среднего индекса
            int mid = left + (right - left) / 2;

            // Рекурсивно сортируем левую и правую части массива
            mergeSort(a, left, mid);
            mergeSort(a, mid + 1, right);

            // Сливаем отсортированные половины
            merge(a, left, mid, right);
        }
    }

    // Метод слияния двух отсортированных массивов
    private void merge(int[] a, int left, int mid, int right) {
        // Вычисляем размеры двух подмассивов
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Создаем временные массивы
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Копируем данные в временные массивы
        System.arraycopy(a, left, leftArray, 0, n1);
        System.arraycopy(a, mid + 1, rightArray, 0, n2);

        // Индексы для обхода временных массивов
        int i = 0, j = 0;
        int k = left;

        // Слияние массивов
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                a[k] = leftArray[i];
                i++;
            } else {
                a[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Копируем оставшиеся элементы из левого массива (если есть)
        while (i < n1) {
            a[k] = leftArray[i];
            i++;
            k++;
        }

        // Копируем оставшиеся элементы из правого массива (если есть)
        while (j < n2) {
            a[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result = instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index : result) {
            System.out.print(index + " ");
        }
    }


}
