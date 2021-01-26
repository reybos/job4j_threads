package ru.job4j.common.resources;

import net.jcip.annotations.Immutable;

@Immutable
public class Node<T> {
    private Node<T> next;
    private T value;

    public Node(Node<T> next, T value) {
        this.next = next;
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }
}