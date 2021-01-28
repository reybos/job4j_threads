package ru.job4j.resource.synchronization;

public interface Transferable {
    boolean transfer(int fromId, int toId, int amount);
}
