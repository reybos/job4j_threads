package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private volatile boolean isPollStopped = true;

    public synchronized void offer(T value) {
        queue.offer(value);
        if (isPollStopped) {
            isPollStopped = false;
            notifyAll();
        }
    }

    public synchronized T poll() throws InterruptedException {
        if (isPollStopped) {
            this.wait();
        }
        if (queue.size() == 1) {
            isPollStopped = true;
        }
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.size() == 0;
    }
}