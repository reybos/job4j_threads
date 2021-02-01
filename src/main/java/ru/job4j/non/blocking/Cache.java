package ru.job4j.non.blocking;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class Cache {
    private final ConcurrentHashMap<Integer, Base> store = new ConcurrentHashMap<>();

    public void add(Base model) {
        store.put(model.getId(), model);
    }

    public void update(Base model) {
        store.computeIfPresent(model.getId(), (key, value) -> {
            if (model.getVersion() != value.getVersion()) {
                throw new OptimisticException("Модель была ранее изменена");
            }
            model.checkChanges(value);
            return model;
        });
    }

    public void delete(Base model) {
        store.remove(model.getId());
    }
}
