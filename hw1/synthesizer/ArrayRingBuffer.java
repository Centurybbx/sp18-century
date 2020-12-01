package synthesizer;

import java.util.Iterator;


public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.capacity = capacity;
        this.fillCount = 0;
        rb = (T[]) new Object[capacity];
        first = last = 0;
    }

//    public ArrayRingBuffer() {
//        // set default capacity equals 5
//        this.capacity = 5;
//        this.fillCount = 0;
//        rb = (T[]) new Object[this.capacity];
//        first = last = 0;
//    }

    private int increaseIndex(int index) {
        return (index + 1) % capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = increaseIndex(last);
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T deletedItem = rb[first];
        rb[first] = null;
        first = increaseIndex(first);
        fillCount -= 1;
        return deletedItem;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    private class QueueIterator implements Iterator<T> {
        int remain;
        int current;

        QueueIterator() {
            remain = fillCount();
            current = first;
        }

        @Override
        public boolean hasNext() {
            return remain > 0;
        }

        @Override
        public T next() {
            T item = rb[current];
            remain -= 1;
            current = increaseIndex(current);
            return item;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

}
