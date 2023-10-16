package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit-тестирование класса AverageEachList
 */
class AverageEachListTest {
    // Поле класса AverageEachList
    private AverageEachList averageEachList;

    /**
     * Метод инициализации объекта класса AverageEachList
     * и заполнение полей объекта данными
     */
    @BeforeEach
    void setUp(){
        averageEachList = new AverageEachList(
                Arrays.asList(1,1,1),
                Arrays.asList(2,2,2)
        );
    }

    /**
     * Тест проверяющий получение корректный расчет среднего значения элементов списка
     */
    @Test
    void getAverageShouldCorrectValue() {
        int expectValue = 2; // Создаем эталонное значение
        // Заполняем одно из полей класса AverageEachList листом с заданными значениями
        averageEachList.setSecondList(Arrays.asList(2,2,2));
        int actualValue = averageEachList.getAverage().get(1); // Получаем среднее значение второго списка с заданными данным
        // Сравниваем эталонное и полученное значение
        assertEquals(expectValue, actualValue, "Метод getAverage некорректно считает среднее значение");
    }

    /**
     * Тест проверяющий генерацию исключения RuntimeException,
     * в случае получения методом getAverage() пустого листа.
     */
    @Test
    void getAverageShouldGeneratedExceptions(){
        // Задаем эталонное исключение
        Exception expectException = new RuntimeException();
        // Заполняем поле объекта класса AverageEachList пустым листом
        averageEachList.setSecondList(Collections.emptyList());
        // Перехватываем сгенерированное исключение
        Exception actualException = assertThrows(RuntimeException.class,
                () -> averageEachList.getAverage());
        // Сравниваем эталонное и актуальные исключения
        assertEquals(expectException.getClass(), actualException.getClass(),
                "метод getAverage некорректно генерирует исключение");
    }

    /**
     * Тест проверяющий на корректность возвращаемое значение методом compareAverageLists()
     */
    @Test
    void compareAverageListsShouldReturnCorrectMessage() {
        // Задаем эталонные возвращаемые значения
        String exMessageOne = "Первый список имеет большее среднее значение";
        String exMessageTwo = "Второй список имеет большее среднее значение";
        String exMessageThree = "Средние значения равны";
        // Получаем возвращаемое значение когда среднее значение первого листа больше значения второго листа
        averageEachList.setFirstList(Arrays.asList(5,5,5));
        averageEachList.setSecondList(Arrays.asList(1,1));
        String acMessageOne = averageEachList.compareAverageLists();
        // Получаем возвращаемое значение когда среднее значение первого листа меньше значения второго листа
        averageEachList.setFirstList(Arrays.asList(1,1));
        averageEachList.setSecondList(Arrays.asList(5,5,5));
        String acMessageTwo = averageEachList.compareAverageLists();
        // Получаем возвращаемое значение когда среднее значение обоих листов равное
        averageEachList.setFirstList(Arrays.asList(1,1));
        averageEachList.setSecondList(Arrays.asList(1,1));
        String acMessageThree = averageEachList.compareAverageLists();
        // Проверяем эталонные и ожидаемые возвращаемые значения
        assertEquals(exMessageOne, acMessageOne, "метод compareAverageLists возвращает некорректное сообщение");
        assertEquals(exMessageTwo, acMessageTwo, "метод compareAverageLists возвращает некорректное сообщение");
        assertEquals(exMessageThree, acMessageThree, "метод compareAverageLists возвращает некорректное сообщение");
    }

    /**
     * Тестирование получения листа из поля firstList
     */
    @Test
    void getFirstList(){
        List<Integer> expectList = new ArrayList<>(Arrays.asList(1,1,1));
        assertEquals(expectList, averageEachList.getFirstList());
    }
    /**
     * Тестирование получения листа из поля secondList
     */
    @Test
    void getSecondList(){
        List<Integer> expectList = new ArrayList<>(Arrays.asList(2,2,2));
        assertEquals(expectList, averageEachList.getSecondList());
    }
    /**
     * Тестирование задания листа в поля firstList
     */
    @Test
    void setFirstList(){
        List<Integer> expectList = Arrays.asList(3,3);
        averageEachList.setFirstList(expectList);
        assertEquals(expectList, averageEachList.getFirstList());
    }
    /**
     * Тестирование задания листа в поля secondList
     */
    @Test
    void setSecondList(){
        List<Integer> expectList = Arrays.asList(3,3);
        averageEachList.setSecondList(expectList);
        assertEquals(expectList, averageEachList.getSecondList());
    }
}