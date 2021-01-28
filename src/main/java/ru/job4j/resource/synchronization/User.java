package ru.job4j.resource.synchronization;

import java.util.Objects;

public class User {
    private int id;
    private int amount;

    public User(int id) {
        this.id = id;
    }

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
