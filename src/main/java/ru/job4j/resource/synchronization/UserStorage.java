package ru.job4j.resource.synchronization;

import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public class UserStorage implements Storage<User>, Transferable {
    private final List<User> users = new ArrayList<>();

    @Override
    public synchronized boolean add(User elem) {
        return users.add(elem);
    }

    @Override
    public synchronized boolean update(User elem) {
        int index = users.indexOf(elem);
        if (index == -1) {
            return false;
        }
        users.add(index, elem);
        return true;
    }

    @Override
    public synchronized boolean delete(User elem) {
        return users.remove(elem);
    }

    @Override
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        int indexFrom = users.indexOf(new User(fromId));
        int indexTo = users.indexOf(new User(toId));
        if (indexFrom == -1 || indexTo == -1) {
            return false;
        }
        User userFrom = users.get(indexFrom);
        User userTo = users.get(indexTo);
        if (userFrom.getAmount() - amount < 0) {
            return false;
        }
        userFrom.setAmount(userFrom.getAmount() - amount);
        userTo.setAmount(userTo.getAmount() + amount);
        return true;
    }
}
