package ru.job4j.non.blocking;

public class OptimisticException extends RuntimeException {
    public OptimisticException(String message) {
        super(message);
    }
}