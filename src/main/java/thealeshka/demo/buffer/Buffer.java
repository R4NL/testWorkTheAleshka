package thealeshka.demo.buffer;

import java.util.PriorityQueue;
import java.util.Queue;

public class Buffer {
    private volatile Queue<String> buffer;
    private int maxQuantity;

    public Buffer(int maxQuantity) {
        this.maxQuantity = maxQuantity;
        buffer = new PriorityQueue<>();
    }

    public Buffer() {
    }

    public synchronized void push(String a) {
        while (buffer.size() > (maxQuantity - 1)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        buffer.add(a);
        System.out.println(buffer);
        notify();
    }

    public synchronized String pop() {
        while (buffer.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String a = buffer.poll();
        System.out.println(buffer);
        notify();
        return a;
    }

    public Queue<String> getBuffer() {
        return buffer;
    }

    public void setBuffer(Queue<String> buffer) {
        this.buffer = buffer;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }
}
