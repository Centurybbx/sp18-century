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
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        this.capacity = capacity;
        this.fillCount = 0;
        rb = (T[]) new Object[capacity];
        first = last = 0;
    }

    public ArrayRingBuffer() {
        // set default capacity equals 5
        this.capacity = 5;
        this.fillCount = 0;
        rb = (T[]) new Object[this.capacity];
        first = last = 0;
    }

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
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
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
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
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
        // TODO: Return the first item. None of your instance variables should change.
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

    // TODO: When you get to part 5, implement the needed code to support iteration.
}
