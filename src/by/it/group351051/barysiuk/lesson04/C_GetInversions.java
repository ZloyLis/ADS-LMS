package by.it.group351051.barysiuk.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Рассчитать число инверсий одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо посчитать число пар индексов 1<=i<j<n, для которых A[i]>A[j].

    (Такая пара элементов называется инверсией массива.
    Количество инверсий в массиве является в некотором смысле
    его мерой неупорядоченности: например, в упорядоченном по неубыванию
    массиве инверсий нет вообще, а в массиве, упорядоченном по убыванию,
    инверсию образуют каждые (т.е. любые) два элемента.
    )

Sample Input:
5
2 3 9 2 9
Sample Output:
2

Головоломка (т.е. не обязательно).
Попробуйте обеспечить скорость лучше, чем O(n log n) за счет многопоточности.
Докажите рост производительности замерами времени.
Большой тестовый массив можно прочитать свой или сгенерировать его программно.
*/


public class C_GetInversions {

    int calc(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int result = 0;
        // Вспомогательный массив для слияния
        int[] temp = new int[n];

        // Вызываем функцию слияния и подсчета инверсий
        result =  mergeSortAndCount(a, temp, 0, n - 1);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    // Функция слияния и подсчета инверсий
    private int mergeSortAndCount(int[] a, int[] temp, int left, int right) {
        int invCount = 0;
        if (left < right) {
            // Разделение массива пополам
            int mid = (left + right) / 2;

            // Рекурсивно сортируем и считаем инверсии в левой и правой частях
            invCount += mergeSortAndCount(a, temp, left, mid);
            invCount += mergeSortAndCount(a, temp, mid + 1, right);

            // Сливаем отсортированные части и считаем инверсии во время слияния
            invCount += mergeAndCount(a, temp, left, mid, right);
        }
        return invCount;
    }

    // Функция слияния двух отсортированных частей массива и подсчета инверсий
    private int mergeAndCount(int[] a, int[] temp, int left, int mid, int right) {
        int i = left;    // Начало левой части
        int j = mid + 1; // Начало правой части
        int k = left;    // Индекс для временного массива
        int invCount = 0;

        // Слияние двух отсортированных частей
        while (i <= mid && j <= right) {
            if (a[i] <= a[j]) {
                temp[k++] = a[i++];
            } else {
                // Если элемент из правой части меньше, чем элемент из левой,
                // то все оставшиеся элементы в левой части массива будут инверсиями с a[j]
                temp[k++] = a[j++];
                invCount += (mid - i + 1); // Считаем количество инверсий
            }
        }

        // Копируем оставшиеся элементы из левой части, если есть
        while (i <= mid) {
            temp[k++] = a[i++];
        }

        // Копируем оставшиеся элементы из правой части, если есть
        while (j <= right) {
            temp[k++] = a[j++];
        }

        // Копируем отсортированные данные обратно в исходный массив
        System.arraycopy(temp, left, a, left, right - left + 1);

        return invCount;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}
