package thealeshka.demo.thread.popThread;

import thealeshka.demo.buffer.Buffer;
import thealeshka.demo.buffer.exeption.NoElementException;
import thealeshka.demo.buffer.exeption.OverflowException;
import thealeshka.demo.thread.ThreadCreator;

import java.util.List;

public class PopThread implements Runnable {
    private Buffer buffer;
    private String text;
    private List<Thread> listPush;
    private ThreadCreator tc;

    public PopThread(Buffer buffer, ThreadCreator tc) {
        this.buffer = buffer;
        this.tc = tc;
    }

    public PopThread(Buffer buffer) {
        this.buffer = buffer;
    }

    public PopThread() {
    }

    @Override
    public void run() {
        listPush=tc.getPushList();
        text = Thread.currentThread().getName() + "read data";
        for (; ; ) {
            try {
                buffer.pop();
                notifyAll();
            } catch (NoElementException e) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                    Thread.currentThread().interrupted();
                }
            }
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

    public List<Thread> getListPush() {
        return listPush;
    }

    public void setListPush(List<Thread> listPush) {
        this.listPush = listPush;
    }
}


