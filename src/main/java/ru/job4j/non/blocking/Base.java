package ru.job4j.non.blocking;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class Base {
    private final int id;
    private String name;
    private final AtomicInteger version;

    public Base(int id, String name) {
        this.id = id;
        this.name = name;
        version = new AtomicInteger(0);
    }

    public synchronized void checkChanges(Base anotherModel) {
        if (!name.equals(anotherModel.getName())) {
            increaseVersion();
        }
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version.get();
    }

    public String getName() {
        return name;
    }

    private void increaseVersion() {
        version.incrementAndGet();
    }
}
