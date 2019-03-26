package thealeshka.demo.thread.popThread;

import thealeshka.demo.buffer.Buffer;
import thealeshka.demo.logger.MyLogger;

import java.util.Objects;

public class PopThread implements Runnable {
    private Buffer buffer;
    private String text;
    private MyLogger logger;

    public PopThread(Buffer buffer, MyLogger logger) {
        this.buffer = buffer;
        this.logger = logger;
    }

    public PopThread(Buffer buffer) {
        this.buffer = buffer;
    }

    public PopThread() {
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " start");
        for (; ; ) {
            buffer.pop();
            logger.addLog(Thread.currentThread().getName()+" read");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PopThread popThread = (PopThread) o;
        return Objects.equals(buffer, popThread.buffer) &&
                Objects.equals(text, popThread.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buffer, text);
    }

    @Override
    public String toString() {
        return "PopThread{" +
                "buffer=" + buffer +
                ", text='" + text + '\'' +
                '}';
    }
}




