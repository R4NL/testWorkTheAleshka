package thealeshka.demo.thread;

import thealeshka.demo.buffer.Buffer;
import thealeshka.demo.logger.MyLogger;
import thealeshka.demo.thread.popThread.PopThread;
import thealeshka.demo.thread.pushThread.PushThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ThreadCreator {
    private int read;
    private int write;
    private volatile Buffer buffer;
    private MyLogger logger;

    public ThreadCreator(int read, int write, MyLogger logger,Buffer bufer) {
        this.read = read;
        this.write = write;
        this.logger = logger;
        this.buffer =bufer;
    }

    public ThreadCreator(int read, int write, MyLogger logger) {
        this.read = read;
        this.write = write;
        this.logger = logger;
        this.buffer = new Buffer(10);
    }

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
            Thread t = new Thread(new PopThread(buffer,logger));
            t.setName("поток чтения с номером " + i);
            pop.add(t);
        }
        for (int i = 0; i < write; i++) {
            Thread t = new Thread(new PushThread(buffer,logger));
            t.setName("поток записи с номером  " + i);
            push.add(t);
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

    public List<String> getBuffer() {
        System.out.println(buffer.getBuffer());
        return buffer.getBuffer();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThreadCreator that = (ThreadCreator) o;
        return read == that.read &&
                write == that.write &&
                Objects.equals(buffer, that.buffer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(read, write, buffer);
    }

    @Override
    public String toString() {
        return "ThreadCreator{" +
                "read=" + read +
                ", write=" + write +
                ", buffer=" + buffer +
                '}';
    }
}
