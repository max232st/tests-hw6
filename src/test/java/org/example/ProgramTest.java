package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Класс сквозных тестов общего поведения программы
 */
@ExtendWith(MockitoExtension.class)
class ProgramTest {
    // Создаем поле мок - объекта UserInput
    @Mock
    private UserInput userInput;
    // Создаем поле spy - объекта AverageEachList
    @Spy
    private AverageEachList averageEachList;
    // Создаем поле spy - объекта Program и внедряем в него мок-объект AverageEachList
    @Spy
    @InjectMocks
    private Program program;

    /**
     * Метод инициализации Spy - объектов
     * выполняется перед каждым запуском тестового метода
     */
    @BeforeEach
    public void setUp(){
        averageEachList = new AverageEachList();
        program = new Program(userInput, averageEachList);
    }

    /**
     * Проводим сквозной тест общей работы программы
     */
    @Test
    void startCorrect() {
        // Задаем поведение мок объекта при вызове метода fillingList()
        when(userInput.fillingList()).thenReturn(new ArrayList<>(Arrays.asList(1,1,1)));
        // Задаем эталонное сообщение, которое ожидаем получить в консоли
        String expectMessage = """
                Среднее значение листа №1 = 1
                Среднее значение листа №2 = 1
                Средние значения равны
                """;
        // Перехватываем вывод в консоль
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        // Запускаем выполнение программы
        program.start();
        // Помещаем вывод в консоль в строку, убирая сепараторы
        String actualMessage = outContent.toString().replaceAll("\r", "");
        // Возвращаем вывод в консоль в исходное состояние
        System.setOut(System.out);
        // Сравниваем эталонное и полученное сообщения
        assertEquals(expectMessage, actualMessage);
    }

    /**
     * Тест проверяющий вывод сообщения пользователю в случае передачи пустого листа
     * P.S. Сделал реализацию теста в качестве эксперимента.
     */
    @Test
    public void startShouldMessageRuntimeException(){
        // Задаем поведение мок-объекта, мок-объект должен вернуть пустой список
        when(userInput.fillingList()).thenReturn(Collections.emptyList());
        // Задаем эталонное сообщение
        String expectMessage = "Переданный список пуст!\n";
        // Создаем пул потоков с одним потоком
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        /*  В объект класса Future помещаем результат вывода в консоль
            получаем при помощи отдельного потока используя класс Callable, который
            может возвращать результат выполнения потока.
         */
        Future<String> message = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                // Вызываем приватный метод отвечающий за чтение консоли
                return getMessageConsole();
            }
        });
        // Завершаем работу пула потоков
        executorService.shutdown();
        // Запускаем выполнение основного метода программы
        program.start();
        String actualMessage = "";
        try {
            /*  Дожидаемся окончания работы потока читающего сообщения из консоли и получаем данные,
                убирая сепараторы
             */
            actualMessage = message.get().replaceAll("\r", "");
        } catch (InterruptedException e) {
            throw new RuntimeException(e); // Обработку исключений опустил
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        // Сравниваем эталонное и актуальные сообщения из консоли
        assertEquals(expectMessage, actualMessage);
    }

    /**
     * Метод осуществляющий чтение из консоли
     * @return результат вывода в консоль в строковом представлении
     * @throws InterruptedException исключение, которое может быть вызвано в случае прерывания потока
     */
    private String getMessageConsole() throws InterruptedException {
        // Перехватываем вывод в консоль
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        // Устанавливаем задержку для выполнения основного потока
        Thread.sleep(100);
        String message = outContent.toString().replaceAll("\r", "");
        // Возвращаем вывод в консоль в исходное состояние
        System.setOut(System.out);
        return message;
    }
}