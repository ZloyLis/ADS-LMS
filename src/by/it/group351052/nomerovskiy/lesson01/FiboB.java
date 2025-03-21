package by.it.group351052.nomerovskiy.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить способ вычисления чисел Фибоначчи с вспомогательным массивом
 * без ограничений на размер результата (BigInteger)
 */

public class FiboB {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {

        //вычисление чисел простым быстрым методом
        FiboB fibo = new FiboB();
        int n = 55555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {

        // возвращаем значения если при слишком низких n
        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;

        // создаём массив для хранения чисел
        BigInteger[] mass = new BigInteger[n+1];
        mass[0] = BigInteger.ZERO;
        mass[1] = BigInteger.ONE;
        // заполняем массив начиная с 3 числа
        for (int i = 2; i <= n; i++) {
            mass[i] = (mass[i - 1]).add(mass[i - 2]);
        }
        return mass[n];

    }

}

