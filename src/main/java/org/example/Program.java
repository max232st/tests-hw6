package org.example;

import java.util.List;

/**
 * Класс содержащий логику программы
 */
public class Program {
    private UserInput userInput;
    private AverageEachList averageEachList;

    public Program(UserInput userInput, AverageEachList averageEachList) {
        this.userInput = userInput;
        this.averageEachList = averageEachList;
    }

    public void start(){
        try{
            List<Integer> listOne = userInput.fillingList();
            List<Integer> listTwo = userInput.fillingList();
            averageEachList.setFirstList(listOne);
            averageEachList.setSecondList(listTwo);
            System.out.printf("Среднее значение листа №1 = %d\n", averageEachList.getAverage().get(0));
            System.out.printf("Среднее значение листа №2 = %d\n", averageEachList.getAverage().get(1));
            String result = averageEachList.compareAverageLists();
            System.out.println(result);
        } catch (NumberFormatException e){
            System.out.println("Введены не корректные данные");
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
}
