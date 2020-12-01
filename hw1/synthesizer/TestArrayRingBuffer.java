package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        //ArrayRingBuffer arb = new ArrayRingBuffer(10);
    }

    @Test
    public void test() {
        ArrayRingBuffer arb = new ArrayRingBuffer(5);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        int expectedDeque = 1;
        assertEquals(expectedDeque, arb.dequeue());
        int expectedPeek = 2;
        assertEquals(expectedPeek, arb.peek());
        assertEquals(expectedPeek, arb.peek());
        arb.enqueue(4);
        arb.enqueue(5);
        arb.enqueue(6);
//        arb.enqueue(7);
        assertEquals(2, arb.dequeue());
        assertEquals(3, arb.dequeue());
        assertEquals(4, arb.dequeue());
        assertEquals(5, arb.dequeue());
        assertEquals(6, arb.dequeue());
//        arb.dequeue();
    }

    @Test
    public void testDemo() {
        BoundedQueue<Integer> bq = new ArrayRingBuffer<>(5);
        bq.enqueue(1);
        bq.enqueue(2);
        bq.enqueue(3);
        bq.enqueue(4);
        bq.enqueue(5);
        bq.dequeue();
        bq.dequeue();
        bq.enqueue(111);
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
