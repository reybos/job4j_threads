package ru.job4j.non.blocking;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CASCountTest {
    private static class ThreadCount extends Thread {
        private final CASCount count;

        private ThreadCount(final CASCount count) {
            this.count = count;
        }

        @Override
        public void run() {
            for (int i = 0; i < 500; i++) {
                this.count.increment();
            }
        }
    }

    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        final CASCount count = new CASCount();
        Thread first = new CASCountTest.ThreadCount(count);
        Thread second = new CASCountTest.ThreadCount(count);
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(count.get(), is(1000));
    }
}