package thealeshka.demo.thread.pushThread;

import thealeshka.demo.buffer.Buffer;

import java.util.List;

public class PushThread implements Runnable {
    private Buffer buffer;
    private String text;

    public PushThread(Buffer buffer) {
        this.buffer = buffer;
    }

    public PushThread() {
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"start");
        text = Thread.currentThread().getName() + "write data";
        for (; ; ) {
            buffer.push(text);
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

}
