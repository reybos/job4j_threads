package ru.job4j.wait;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleBlockingQueueTest {
    @Test
    public void whenExecute2Thread() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    queue.poll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread producer = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 6; i++) {
                queue.offer(i);
            }
        });
        consumer.start();
        producer.start();
        assertEquals(5, queue.poll().intValue());
    }
}