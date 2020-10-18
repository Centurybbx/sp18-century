import static org.junit.Assert.*;
import org.junit.Test;

public class TestOffByN {

    static OffByN offBy0 = new OffByN(0);
    static OffByN offBy1 = new OffByN(1);
    static OffByN offBy2 = new OffByN(2);

    @Test
    public void testOffBy0() {
        assertTrue(offBy0.equalChars('a', 'a'));
        assertTrue(offBy0.equalChars('b', 'b'));
        assertFalse(offBy0.equalChars('a', 'b'));
        assertFalse(offBy0.equalChars('c', 'd'));
    }

    @Test
    public void testOffBy1() {
        assertTrue(offBy1.equalChars('a', 'b'));
        assertTrue(offBy1.equalChars('$', '%'));
        assertFalse(offBy1.equalChars('a', 'a'));
        assertFalse(offBy1.equalChars('a', 'z'));
    }

    @Test
    public void testOffBy2() {
        assertTrue(offBy2.equalChars('a', 'c'));
        assertTrue(offBy2.equalChars('b', 'd'));
        assertFalse(offBy2.equalChars('a', 'z'));
        assertFalse(offBy2.equalChars('a', 'e'));
    }

}
