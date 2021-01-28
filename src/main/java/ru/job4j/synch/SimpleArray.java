package ru.job4j.synch;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] container;
    private int size = 0;
    private int modCount = 0;

    public SimpleArray() {
        this.container = new Object[10];
    }

    public SimpleArray(int length) {
        this.container = new Object[length];
    }

    private void ensureCapacity(int nextSize) {
        int oldCapacity = container.length;
        if (nextSize == oldCapacity) {
            int newCapacity = (oldCapacity * 3) / 2 + 1;
            Object[] newContainer = new Object[newCapacity];
            System.arraycopy(container, 0, newContainer, 0, size);
            container = newContainer;
        }
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    public void add(T model) {
        ensureCapacity(size + 1);
        container[size++] = model;
        modCount++;
    }

    public boolean contains(T elem) {
        boolean rsl = false;
        Iterator<T> iter = this.iterator();
        while (iter.hasNext()) {
            if (Objects.equals(iter.next(), elem)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final Object[] data = container;
            private final int expectedModCount = modCount;
            private final int currSize = size;
            private int point = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < currSize;
            }

            @SuppressWarnings("unchecked")
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) data[point++];
            }
        };
    }
}