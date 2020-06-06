import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {

    @Test
    public void testIsSameNumber() {
        Integer a = 128;
        Integer b = 128;
        Integer c = 500;
        boolean actual = Flik.isSameNumber(a, b);
        boolean actual2 = Flik.isSameNumber(a, c);
        assertTrue(actual);
        assertFalse(actual2);
    }

}
