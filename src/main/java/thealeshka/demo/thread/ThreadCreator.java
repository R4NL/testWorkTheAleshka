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
    private List<Thread> popList;
    private List<Thread> pushList;

    public ThreadCreator(int read, int write, Buffer buffer) {
        this.read = read;
        this.write = write;
        this.buffer = buffer;
    }

    public ThreadCreator(int read, int write) {
        this.read = read;
        this.write = write;
        this.buffer=new Buffer(10);

    }

    public ThreadCreator() {
    }

    public void create() {
        List<PopThread> pop = popListGet();
        List<PushThread> push = pushListGet();
        popList = new ArrayList<>();
        pushList = new ArrayList<>();
        for (PopThread p : pop) {
            popList.add(new Thread(p));
        }
        for (PushThread p : push) {
            pushList.add(new Thread(p));
        }
        for (Thread t : popList) {
            t.setDaemon(true);
            t.start();
        }
        for (Thread t : pushList) {
            t.setDaemon(true);
            t.start();
        }
        for (Thread t : popList) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (Thread t : pushList) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private List<PopThread> popListGet() {
        List<PopThread> list = new ArrayList<>();
        for (int i = 0; i < read; i++) {
            list.add(new PopThread(buffer, this));
        }
        return list;
    }

    private List<PushThread> pushListGet() {
        List<PushThread> list = new ArrayList<>();
        for (int i = 0; i < write; i++) {
            list.add(new PushThread(buffer, this));
        }
        return list;
    }

    public List<Thread> getPopList() {
        return popList;
    }

    public void setPopList(List<Thread> popList) {
        this.popList = popList;
    }

    public List<Thread> getPushList() {
        return pushList;
    }

    public void setPushList(List<Thread> pushList) {
        this.pushList = pushList;
    }
}
