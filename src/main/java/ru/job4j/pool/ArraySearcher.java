package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ArraySearcher<T> extends RecursiveTask<Integer> {
    private T[] array;
    private T elem;
    private int startIndex;
    private int endIndex;

    private ArraySearcher(T[] array, T elem, int startIndex, int endIndex) {
        this.array = array;
        this.elem = elem;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    protected Integer compute() {
        if (endIndex - startIndex < 10) {
            return findIndex();
        }
        int midIndex = startIndex + (endIndex - startIndex) / 2;
        ArraySearcher<T> leftPart = new ArraySearcher<>(array, elem, startIndex, midIndex);
        ArraySearcher<T> rightPart = new ArraySearcher<>(array, elem, midIndex + 1, endIndex);
        leftPart.fork();
        rightPart.fork();
        int leftIndex = leftPart.join();
        int rightIndex = rightPart.join();
        return leftIndex != -1 ? leftIndex : rightIndex;
    }

    private int findIndex() {
        for (int i = startIndex; i <= endIndex; i++) {
            if (array[i].equals(elem)) {
                return i;
            }
        }
        return -1;
    }

    public static <T> int findIndex(T[] array, T elem) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ArraySearcher<>(array, elem, 0, array.length - 1));
    }
}
