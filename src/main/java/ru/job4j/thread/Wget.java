package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;
    private final String store;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
        this.store = "temp.txt";
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(store)) {
            byte[] dataBuffer = new byte[speed];
            int bytesRead;
            long startTime = System.currentTimeMillis();
            long leftTime;
            while ((bytesRead = in.read(dataBuffer, 0, speed)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                leftTime = System.currentTimeMillis() - startTime;
                if (leftTime < 1000) {
                    Thread.sleep(leftTime);
                }
                startTime = System.currentTimeMillis();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}