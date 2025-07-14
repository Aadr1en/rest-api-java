import java.util.Queue;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class FIFO {
    private Queue<Integer> queue = new LinkedList<>();

    public void enqueue(int element) {
        queue.add(element);
    }

    public int dequeue() {
        if (queue.isEmpty()) throw new NoSuchElementException("File vide");
        return queue.poll();
    }

    public int peek() {
        if (queue.isEmpty()) throw new NoSuchElementException("File vide");
        return queue.peek();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }
}
