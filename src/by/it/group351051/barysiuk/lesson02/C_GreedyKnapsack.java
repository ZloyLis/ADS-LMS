package by.it.group351051.barysiuk.lesson02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

public class C_GreedyKnapsack {
    private static class Item implements Comparable<Item> {
        int cost;
        int weight;

        Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "cost=" + cost +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(Item o) {
            double thisValuePerWeight = (double) this.cost / this.weight;
            double otherValuePerWeight = (double) o.cost / o.weight;
            return Double.compare(otherValuePerWeight, thisValuePerWeight); // Обратный порядок
        }
    }

    double calc(File source) throws FileNotFoundException {
        Scanner input = new Scanner(source);
        int n = input.nextInt();      // Количество предметов в файле
        int W = input.nextInt();      // Вместимость рюкзака
        Item[] items = new Item[n];   // Массив предметов

        // Читаем предметы из файла
        for (int i = 0; i < n; i++) {
            items[i] = new Item(input.nextInt(), input.nextInt());
        }

        // Показываем предметы
        System.out.println("Доступные предметы:");
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n", n, W);

        // Сортируем предметы по стоимости за единицу веса
        Arrays.sort(items);

        double result = 0; // Общая стоимость вещей в рюкзаке
        int remainingWeight = W; // Оставшийся вес рюкзака

        // Жадный алгоритм заполнения рюкзака
        for (Item item : items) {
            if (remainingWeight == 0) {
                break; // Если рюкзак полностью заполнен, выходим
            }

            if (item.weight <= remainingWeight) {
                // Если предмет помещается целиком, добавляем его
                result += item.cost;
                remainingWeight -= item.weight;
            } else {
                // Если предмет не помещается целиком, берем его часть
                double fraction = (double) remainingWeight / item.weight;
                result += item.cost * fraction;
                remainingWeight = 0; // Рюкзак заполнен
            }
        }

        System.out.printf("Удалось собрать рюкзак на сумму %f\n", result);
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "greedyKnapsack.txt");
        double costFinal = new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)", costFinal, finishTime - startTime);
    }
}