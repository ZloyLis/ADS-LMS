package by.it.group351051.barysiuk.lesson02;

import java.util.ArrayList;
import java.util.List;
/*
даны события events
реализуйте метод calcStartTimes, так, чтобы число включений регистратора на
заданный период времени (1) было минимальным, а все события events
были зарегистрированы.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/

import java.util.*;

public class A_VideoRegistrator {

    public static void main(String[] args)  {
        A_VideoRegistrator instance = new A_VideoRegistrator();
        double[] events = new double[]{1, 1.1, 1.6, 2.2, 2.4, 2.7, 3.9, 8.1, 9.1, 5.5, 3.7};
        List<Double> starts = instance.calcStartTimes(events, 1); // рассчитываем моменты старта, с длительностью сеанса 1
        System.out.println(starts); // выводим моменты старта
    }//модификаторы доступа опущены для возможности тестирования

    // Метод для нахождения минимальных моментов старта видеокамеры
    List<Double> calcStartTimes(double[] events, double workDuration)  {
        List<Double> result = new ArrayList<>();

        // Сортировка событий по времени
        Arrays.sort(events);

        int i = 0;  // индекс текущего события
        int n = events.length;

        while (i < n) {
            // Начинаем новый сеанс с текущего события
            double startTime = events[i];
            result.add(startTime);

            // Камера будет работать до времени (startTime + workDuration)
            double endTime = startTime + workDuration;

            // Пропускаем все события, которые могут быть покрыты текущим сеансом
            while (i < n && events[i] <= endTime) {
                i++;
            }
        }

        return result;
    }
}

