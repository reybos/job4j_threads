package ru.job4j.common.resources;

public class DCLSingleton {
    private static volatile DCLSingleton inst;

    private DCLSingleton() { }

    public static DCLSingleton instOf() {
        if (inst == null) {
            synchronized (DCLSingleton.class) {
                if (inst == null) {
                    inst = new DCLSingleton();
                }
            }
        }
        return inst;
    }
}
