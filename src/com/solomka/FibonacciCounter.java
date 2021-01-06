package com.solomka;

import com.solomka.examplesfromnet.MyFork;

import java.util.concurrent.RecursiveTask;

public class FibonacciCounter extends RecursiveTask<Long> {
    private int end;
    private final int numberOfCores = Runtime.getRuntime().availableProcessors();

    public FibonacciCounter(int end) {
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end <= numberOfCores) {
            return findFibonacci(end);
        } else {
            FibonacciCounter firstHalf = new FibonacciCounter(end-1);
            firstHalf.fork();
            FibonacciCounter secondHalf = new FibonacciCounter(end-2);
            secondHalf.fork();
            long secondValue = secondHalf.compute();
            return firstHalf.join() + secondValue;
        }
    }

    static long findFibonacci(int n) {
        long[][] matrix = {{0, 1}, {1, 1}};
        return matrixPow(matrix, n);
    }

    static long[][] matrixMultiply(long[][] left, long[][] right) {
        int n = left[0].length;
        long[][] result = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += left[i][k] * right[k][j];
                }
            }
        }
        return result;
    }

    private static long matrixPow(long[][] matrix, int n) {
        long[][] result = {{0, 1}, {1, 1}};
        while (n != 0) {
            if (n % 2 != 0) {
                result = matrixMultiply(result, matrix);
            }
            n /= 2;
            matrix = matrixMultiply(matrix, matrix);
        }
        return result[0][0];
    }
}
