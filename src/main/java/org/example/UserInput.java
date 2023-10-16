package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Класс обработки пользовательского ввода
 */
public class UserInput {
    private List<Integer> list;

    /**
     * Метод получает данные от пользователя
     * @return лист целочисленных значений
     * @throws NumberFormatException исключение при некорректном вводе пользователя или при отсутствии данных
     */
    public List<Integer> fillingList() throws NumberFormatException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите через пробел числа для заполнения списка, для окончания ввода нажмите Enter: ");
        String [] userInput = scanner.nextLine().strip().split("\s");
        int [] arrNumbers = Arrays.stream(userInput).mapToInt(Integer::parseInt).toArray();
        this.list = Arrays.stream(arrNumbers).boxed().toList();
        return this.list;
    }
}
