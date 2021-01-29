package ru.job4j.wait;

public class CountBarrierDemo {
    public static void main(String[] args) {
        CountBarrier barrier = new CountBarrier(100);
        Thread counter = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                barrier.count();
            }
        });
        Thread await = new Thread(
                () -> {
                    barrier.await();
                    System.out.println(Thread.currentThread().getName());
                },
                "await"
        );
        Thread await2 = new Thread(
                () -> {
                    barrier.await();
                    System.out.println(Thread.currentThread().getName());
                },
                "await2"
        );
        await.start();
        await2.start();
        counter.start();
    }
}
