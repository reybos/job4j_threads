package ru.job4j.resource.synchronization;

import java.io.*;

public class ParseFile {
    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized String getContent() throws IOException {
        StringBuilder output = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines().forEach(output::append);
        }
        return output.toString();
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        StringBuilder output = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            int data;
            while ((data = in.read()) > 0) {
                if (data < 0x80) {
                    output.append((char) data);
                }
            }
        }
        return output.toString();
    }

    public synchronized void saveContent(String content) throws IOException {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            out.write(content);
        }
    }
}