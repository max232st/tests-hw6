package org.example;

/**
 * Точка входа в программу.
 */
public class Main {
    public static void main(final String[] args) {
        Program program = new Program(new UserInput(), new AverageEachList());
        program.start();
    }
}
