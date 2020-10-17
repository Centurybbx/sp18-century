import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {

    @Test
    public void testIsSameNumber() {
        Integer a = 128;
        Integer b = 128;
        Integer c = 500;
        boolean same = Flik.isSameNumber(a, b);
        boolean diff = Flik.isSameNumber(a, c);
        assertTrue(same);
        assertFalse(diff);
    }

}
