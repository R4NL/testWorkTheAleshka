package thealeshka.demo.thread.pushThread;

import thealeshka.demo.buffer.Buffer;
import thealeshka.demo.logger.MyLogger;

import java.util.Objects;

public class PushThread implements Runnable {
    private Buffer buffer;
    private String text;
    private MyLogger logger;

    public PushThread(Buffer buffer, MyLogger logger) {
        this.buffer = buffer;
        this.logger = logger;
    }

    public PushThread(Buffer buffer) {
        this.buffer = buffer;
    }

    public PushThread() {
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" start");
        text = Thread.currentThread().getName() + " записал данные";
        for (; ; ) {
            buffer.push(text);
            logger.addLog(Thread.currentThread().getName()+" write");
        }
    }

    public Buffer getBuffer() {
        return buffer;
    }

    public void setBuffer(Buffer buffer) {
        this.buffer = buffer;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PushThread that = (PushThread) o;
        return Objects.equals(buffer, that.buffer) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buffer, text);
    }

    @Override
    public String toString() {
        return "PushThread{" +
                "buffer=" + buffer +
                ", text='" + text + '\'' +
                '}';
    }
}
