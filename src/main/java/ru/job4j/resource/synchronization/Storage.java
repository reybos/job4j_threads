package ru.job4j.resource.synchronization;

public interface Storage<T> {
    boolean add(T elem);

    boolean update(T elem);

    boolean delete(T elem);
}
