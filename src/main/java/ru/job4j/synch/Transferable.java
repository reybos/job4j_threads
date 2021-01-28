package ru.job4j.synch;

public interface Transferable {
    boolean transfer(int fromId, int toId, int amount);
}
