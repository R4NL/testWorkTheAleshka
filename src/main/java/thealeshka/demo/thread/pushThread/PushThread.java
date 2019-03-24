package thealeshka.demo.thread.pushThread;

import thealeshka.demo.buffer.Buffer;
import thealeshka.demo.buffer.exeption.OverflowException;
import thealeshka.demo.thread.ThreadCreator;

import java.util.List;

public class PushThread implements Runnable {
    private Buffer buffer;
    private String text;
    private List<Thread> listPop;
    private ThreadCreator tc;


    public PushThread(Buffer buffer, ThreadCreator tc) {
        this.buffer = buffer;
        this.tc = tc;
    }

    public PushThread() {
    }

    @Override
    public void run() {
        listPop = tc.getPopList();
        text = Thread.currentThread().getName() + "write data";
        for (; ; ) {
            try {
                buffer.push(text);
                notifyAll();
            } catch (OverflowException e) {
                try {
                    this.wait();
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

    public List<Thread> getListPop() {
        return listPop;
    }

    public void setListPop(List<Thread> listPop) {
        this.listPop = listPop;
    }
}
