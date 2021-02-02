package ru.job4j.pool;

import ru.job4j.wait.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            threads.add(new Thread(
                    () -> {
                        System.out.println(
                                "Поток " + Thread.currentThread().getName() + " начал работать"
                        );
                        while (!Thread.currentThread().isInterrupted()) {
                            try {
                                System.out.println(
                                        "Поток " + Thread.currentThread().getName() + " работает"
                                );
                                tasks.poll().run();
                            } catch (InterruptedException e) {
                                System.out.println(
                                        "Поток " + Thread.currentThread().getName() + " прерван"
                                );
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
            ));
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void work(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool();
        pool.work(() -> System.out.println("work1"));
        pool.work(() -> System.out.println("work2"));
        pool.work(() -> System.out.println("work3"));
        pool.shutdown();
    }
}