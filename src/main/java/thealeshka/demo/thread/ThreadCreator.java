package thealeshka.demo.thread;

import thealeshka.demo.buffer.Buffer;
import thealeshka.demo.thread.popThread.PopThread;
import thealeshka.demo.thread.pushThread.PushThread;

import java.util.ArrayList;
import java.util.List;

public class ThreadCreator {
    private int read;
    private int write;
    private Buffer buffer;


    public ThreadCreator(int read, int write, Buffer buffer) {
        this.read = read;
        this.write = write;
        this.buffer = buffer;
    }

    public ThreadCreator(int read, int write) {
        this.read = read;
        this.write = write;
        this.buffer = new Buffer(10);

    }

    public ThreadCreator() {
    }

    public void create() {
        List<Thread> pop = new ArrayList<>();
        List<Thread> push = new ArrayList<>();
        for (int i = 0; i < read; i++) {
            pop.add(new Thread(new PopThread(buffer)));
        }
        for (int i = 0; i < write; i++) {
            push.add(new Thread(new PushThread(buffer)));
        }

        for (Thread t : pop) {
            t.setDaemon(true);
            t.start();
        }
        for (Thread t : push) {
            t.setDaemon(true);
            t.start();
        }

        for (Thread t : pop) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (Thread t : push) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
