package ru.job4j.wait;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class CountBarrier {
    private final Object monitor = this;
    private final int total;
    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public synchronized void count() {
        System.out.println(count++);
        if (count == total) {
            monitor.notifyAll();
        }
    }

    public synchronized void await() {
        while (count != total) {
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Внутри await");
        }
    }
}
