package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public synchronized void offer(T value) {
        if (isEmpty()) {
            notifyAll();
        }
        queue.offer(value);
    }

    public synchronized T poll() throws InterruptedException {
        if (isEmpty()) {
            this.wait();
        }
        return queue.poll();
    }

    public synchronized boolean isEmpty() {
        return queue.size() == 0;
    }
}