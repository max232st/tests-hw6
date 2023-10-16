package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit-тестирование класса UserInput
 */
class UserInputTest {
    private UserInput userInput;

    /**
       Метод инициализации объекта класса UserInput
     */
    @BeforeEach
    void setUp(){
        userInput = new UserInput();
    }

    /**
     * Тестирование работы метода при корректной подаче данных на вход
     */
    @Test
    void correctFillingList() {
        List <Integer> expectList = new ArrayList<>(Arrays.asList(1,2,3)); // Создаем эталонный лист с целочисленными значениями
        ByteArrayInputStream in = new ByteArrayInputStream("1 2 3\n".getBytes()); // В поток ввода передаем 1 2 3
        System.setIn(in);
        List <Integer> actualList = userInput.fillingList();  // Заполняем лист используя метод fillingList тестируемого класса
        assertTrue(expectList.equals(actualList), "Метод fillingList не корректен"); // Проверяем что эталонный лист равен полученному листу от тестируемого метода
        System.setIn(System.in); // Возвращаем поток ввода в исходное состояние
    }

    /**
     * Тестирование выбрасывания исключения при вводе строкового значения
     */
    @Test
    void checkExceptionFillingListIncorrectData(){
        Exception expectException = new NumberFormatException(); // Создаем эталонное исключение
        ByteArrayInputStream in = new ByteArrayInputStream("aaa\n".getBytes()); // В поток ввода передаем строковое значение
        System.setIn(in);
        Exception actualException = assertThrows(NumberFormatException.class,
                () -> userInput.fillingList());  // Перехватываем сгенерированное методом исключение
        assertEquals(
                expectException.getClass(),
                actualException.getClass(),
                "Метод некорректно fillingList выбрасывает исключение");  // Сравниваем классы эталонного и полученного исключений
    }

    /**
     * Тестирование выбрасывания исключения при отсутствии данных на ввод
     */
    @Test
    void checkExceptionFillingListEmptyData(){
        Exception expectException = new NumberFormatException(); // Создаем эталонное исключение
        ByteArrayInputStream in = new ByteArrayInputStream("\n".getBytes()); // В потоке ввода имитируем отсутствие ввода
        System.setIn(in);
        Exception actualException = assertThrows(NumberFormatException.class,
                () -> userInput.fillingList());  // Перехватываем сгенерированное методом исключение
        assertEquals(
                expectException.getClass(),
                actualException.getClass(),
                "Метод некорректно fillingList выбрасывает исключение");  // Сравниваем классы эталонного и полученного исключений
    }
}