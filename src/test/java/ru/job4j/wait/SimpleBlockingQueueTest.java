package ru.job4j.wait;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleBlockingQueueTest {
    @Test
    public void whenExecute2Thread() {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                queue.poll();
            }
        });
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                queue.offer(i);
            }
        });
        consumer.start();
        producer.start();
        assertEquals(5, queue.poll().intValue());
    }
}