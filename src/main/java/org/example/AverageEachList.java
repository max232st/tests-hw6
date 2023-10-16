package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс подсчета среднего значения в листе.
 */
public class AverageEachList {

    private List<Integer> firstList;
    private List<Integer> secondList;

    public AverageEachList(List<Integer> firstList, List<Integer> secondList) {
        this.firstList = firstList;
        this.secondList = secondList;
    }

    public AverageEachList(){}

    public List<Integer> getFirstList() {
        return firstList;
    }

    public void setFirstList(List<Integer> firstList) {
        this.firstList = firstList;
    }

    public List<Integer> getSecondList() {
        return secondList;
    }

    public void setSecondList(List<Integer> secondList) {
        this.secondList = secondList;
    }

    /**
     * Метод рассчитывает среднее значение элементов листа.
     *
     * @return среднее значение
     */
    public List<Integer> getAverage() throws RuntimeException {
        if (firstList.isEmpty() || secondList.isEmpty()) throw new RuntimeException("Переданный список пуст!");
        List<Integer> resultList = new ArrayList<>();
        resultList.add(firstList.stream().reduce(Integer::sum).get() / firstList.size());
        resultList.add(secondList.stream().reduce(Integer::sum).get() / secondList.size());
        return resultList;
    }

    /**
     * Метод сравнивает средние значения двух листов.
     *
     */
    public String compareAverageLists() throws RuntimeException {
        List<Integer> averageList = this.getAverage();
        if (averageList.get(0) > averageList.get(1)) return "Первый список имеет большее среднее значение";
        else if (averageList.get(0) < averageList.get(1)) return "Второй список имеет большее среднее значение";
        else return "Средние значения равны";
    }
}
