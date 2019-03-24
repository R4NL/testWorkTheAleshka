package thealeshka.demo.buffer;

import thealeshka.demo.buffer.exeption.NoElementException;
import thealeshka.demo.buffer.exeption.OverflowException;

import java.util.*;

public class Buffer {
    private List<String> buffer;
    private int maxQuantity;

    public Buffer(int maxQuantity) {
        this.buffer = Collections.synchronizedList(new ArrayList<>());
        this.maxQuantity = maxQuantity;
    }

    public String pop() {
        if (buffer.isEmpty()) {
            throw new NoElementException();
        }
        String res = buffer.get(0);
        buffer.remove(res);
        return res;
    }

    public void push(String a) {
        if (buffer.size() < maxQuantity - 1) {
            buffer.add(a);
        } else {
            throw new OverflowException();
        }
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
