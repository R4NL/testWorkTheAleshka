package thealeshka.demo.buffer.exeption;

public class OverflowException extends RuntimeException {
    public OverflowException(String message) {
        super(message);
    }

    public OverflowException() {
    }
}
