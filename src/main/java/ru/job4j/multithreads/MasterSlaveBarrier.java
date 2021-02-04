package ru.job4j.multithreads;

public class MasterSlaveBarrier {
    private volatile boolean isMasterWorking = true;

    public synchronized void tryMaster() {
        while (!isMasterWorking) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void trySlave() {
        while (isMasterWorking) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void doneMaster() {
        isMasterWorking = false;
        notifyAll();
    }

    public synchronized void doneSlave() {
        isMasterWorking = true;
        notifyAll();
    }
}
