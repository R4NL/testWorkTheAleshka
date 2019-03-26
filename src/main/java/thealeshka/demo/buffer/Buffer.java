package thealeshka.demo.buffer;

import java.util.*;

public class Buffer {
    private volatile List<String> buffer;
    private int maxQuantity;

    public Buffer(int maxQuantity) {
        this.maxQuantity = maxQuantity;
        buffer = Collections.synchronizedList(new ArrayList<>());
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
        String a = buffer.remove(0);
        notify();
        return a;
    }

    public List<String> getBuffer() {
        List<String> res=new ArrayList<>();
        synchronized (buffer) {
            System.out.println(buffer);
            for (String a:buffer) {
               res.add(a);
            }
        }
        return res;
    }

    public void setBuffer(List<String> buffer) {
        this.buffer = buffer;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Buffer buffer1 = (Buffer) o;
        return maxQuantity == buffer1.maxQuantity &&
                Objects.equals(buffer, buffer1.buffer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buffer, maxQuantity);
    }

    @Override
    public String toString() {
        return "Buffer{" +
                "buffer=" + buffer +
                ", maxQuantity=" + maxQuantity +
                '}';
    }
}
