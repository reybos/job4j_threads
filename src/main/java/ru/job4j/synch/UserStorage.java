package ru.job4j.synch;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage implements Storage<User>, Transferable {
    private final Map<Integer, User> users = new HashMap<>();

    @Override
    public synchronized boolean add(User elem) {
        users.put(elem.getId(), elem);
        return true;
    }

    @Override
    public synchronized boolean update(User elem) {
        if (!users.containsKey(elem.getId())) {
            return false;
        }
        users.put(elem.getId(), elem);
        return true;
    }

    @Override
    public synchronized boolean delete(User elem) {
        return users.remove(elem.getId()) != null;
    }

    @Override
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        if (!users.containsKey(fromId) || !users.containsKey(toId)) {
            return false;
        }
        User userFrom = users.get(fromId);
        User userTo = users.get(toId);
        if (userFrom.getAmount() - amount < 0) {
            return false;
        }
        userFrom.setAmount(userFrom.getAmount() - amount);
        userTo.setAmount(userTo.getAmount() + amount);
        return true;
    }
}