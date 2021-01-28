package ru.job4j.synch;

import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    private final SimpleArray<T> list = new SimpleArray<>();

    public synchronized void add(T value) {
        list.add(value);
    }

    public synchronized T get(int index) {
        return list.get(index);
    }

    private synchronized SimpleArray<T> copy() {
        SimpleArray<T> duplicate = new SimpleArray<>();
        list.forEach(duplicate::add);
        return duplicate;
    }

    @Override
    public Iterator<T> iterator() {
        return copy().iterator();
    }
}