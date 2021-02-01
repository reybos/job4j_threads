package ru.job4j.non.blocking;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.Matchers.is;

public class CacheTest {
    @Test
    public void whenThrowException() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread thread = new Thread(
                () -> {
                    try {
                        throw new RuntimeException("Throw Exception in Thread");
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        thread.start();
        thread.join();
        Assert.assertThat(ex.get().getMessage(), is("Throw Exception in Thread"));
    }

    @Test
    public void whenDifferentModelVersionThenError() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Cache cache = new Cache();
        cache.add(new Base(1, "Ivan"));
        Thread thread = new Thread(
                () -> {
                    try {
                        cache.update(new Base(1, "Masha"));
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        Thread thread2 = new Thread(
                () -> {
                    try {
                        cache.update(new Base(1, "Andrey"));
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        thread.start();
        thread2.start();
        thread.join();
        thread2.join();
        Assert.assertThat(ex.get().getMessage(), is("Модель была ранее изменена"));
    }
}