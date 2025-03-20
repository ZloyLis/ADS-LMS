package by.it.group351051.barysiuk.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/*
Задача на программирование: наибольшая невозростающая подпоследовательность

Дано:
    целое число 1<=n<=1E5 ( ОБРАТИТЕ ВНИМАНИЕ НА РАЗМЕРНОСТЬ! )
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] не больше любого предыдущего
    т.е. для всех 1<=j<k, A[i[j]]>=A[i[j+1]].

    В первой строке выведите её длину k,
    во второй - её индексы i[1]<i[2]<…<i[k]
    соблюдая A[i[1]]>=A[i[2]]>= ... >=A[i[n]].

    (индекс начинается с 1)

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    5
    5 3 4 4 2

    Sample Output:
    4
    1 3 4 5
*/


public class C_LongNotUpSubSeq {

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //общая длина последовательности
        int n = scanner.nextInt();
        int[] m = new int[n];
        //читаем всю последовательность
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи методами динамического программирования (!!!)

        // Массив для динамического поиска
       int[] dp = new int[n];
        int[] pos = new int[n];
        int[] prev = new int[n];

        Arrays.fill(dp, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);

        int result = 0;

        for (int i = 0; i < n; i++) {
            int l = 0, r = result;
            while (l < r) {
                int mid = (l + r) / 2;
                if (dp[mid] < m[i]) { // Делаем условие строгое для невозрастающей последовательности
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }

            dp[l] = m[i];
            pos[l] = i;

            if (l > 0) {
                prev[i] = pos[l - 1];
            }

            if (l == result) {
                result++;
            }
        }

        // Восстановление индексов
        ArrayList<Integer> resultIndices = new ArrayList<>();
        int index = pos[result - 1];
        while (index != -1) {
            resultIndices.add(index + 1); // Индексация с 1
            index = prev[index];
        }
        Collections.reverse(resultIndices);

        // Вывод
        System.out.println(result);
        for (int i : resultIndices) {
            System.out.print(i + " ");
        }

        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }

}