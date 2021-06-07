package hours_80.dz2;

import java.util.LinkedList;
import java.util.Random;

import static hours_80.dz2.List_creator.*;

/*
Реализовать цикл, который будет 100 раз вызывать созданный вами метод fifo и будет передавать ему в аргументы
целочисленное случайное число в интервале от 0 до 9 включительно. Метод fifo должен реализовать добавление элементов
в буфер длиной 8 элементов. При достижении длины буфера заданного значения новые числа должны вытеснять самые старые
числа. После каждого вызова метода fifo требуется распечатать в одной строке текущее содержимое буфера, отсортированное
по возрастанию содержимое а также медианное значение.
 */
public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        List_creator list_creator = new List_creator(8);
        for (int i = 0; i < 100; i++) {
            list_creator.fifo(list, randomNumber());
        }

        list_creator.setSizeBuffer(19);
        for (int i = 0; i < 100; i++) {
            list_creator.fifo(list, randomNumber());
        }
    }


}
