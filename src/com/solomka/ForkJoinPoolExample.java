package com.solomka;

import com.solomka.examplesfromnet.MyFork;

import java.util.Date;
import java.util.concurrent.ForkJoinPool;
// 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946,
// 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578,
// 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141, 267914296, 433494437,
// 701408733, 1134903170, 1836311903, 2971215073, 4807526976, 7778742049, 12586269025

public class ForkJoinPoolExample {
    public static void main(String[] args) {
        long correctResult = 75025L;
        int number = 25;
        System.out.println("\tForkJoinPool implementation");
        ForkJoinPool pool = ForkJoinPool.commonPool();
        long startTime = System.nanoTime();
        long result = pool.invoke(new FibonacciCounter(number));
        System.out.printf("Spent time: %d ms%n", (System.nanoTime() - startTime) / 1000000);
        System.out.println("Result: " + result + " is correct -> " + ((result == correctResult) ? "Yes" : "No"));

        System.out.println("\tSimple implementation");
        startTime = System.nanoTime();
        long anotherResult = FibonacciCounter.findFibonacci(number);
        System.out.printf("Spent time: %d ms%n", (System.nanoTime() - startTime) / 1000000);
        System.out.println("Result: " + anotherResult + " is correct -> " + ((result == correctResult) ? "Yes" : "No"));

    }
}
