package thealeshka.demo.thread.popThread;

import thealeshka.demo.buffer.Buffer;

import java.util.List;

public class PopThread implements Runnable {
    private Buffer buffer;
    private String text;

    public PopThread(Buffer buffer) {
        this.buffer = buffer;
    }

    public PopThread() {
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "start");
        for (; ; ) {
            System.out.println(buffer.pop());
        }

    }
}




