package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        int count = 0;
        char[] process = new char[] {'/', '|', '\\'};
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("\rLoading ... " + process[count++ % 3]);
        }
    }

    public static void main(String[] args) {
        Thread progress = new Thread(new ConsoleProgress());
        try {
            progress.start();
            Thread.sleep(2000);
            progress.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
