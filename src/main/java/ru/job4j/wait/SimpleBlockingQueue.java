package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.IntPredicate;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int capacity;

    public SimpleBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void offer(T value) {
        IntPredicate needWait = size -> size == capacity;
        IntPredicate needStart = size -> size == 1;
        controlThread(needWait, needStart);
        queue.offer(value);
    }

    public synchronized T poll() {
        IntPredicate needWait = size -> size == 0;
        IntPredicate needStart = size -> size == capacity;
        controlThread(needWait, needStart);
        return queue.poll();
    }

    private synchronized void controlThread(IntPredicate needWait, IntPredicate needStart) {
        if (needWait.test(queue.size())) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (needStart.test(queue.size())) {
            notifyAll();
        }
    }
}