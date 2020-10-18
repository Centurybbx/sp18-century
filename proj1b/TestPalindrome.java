import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("Racecar"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));
        assertFalse(palindrome.isPalindrome("century"));
    }

    @Test
    public void testBy1IsPalindrome() {
        OffByOne offByOne = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", offByOne));
        assertTrue(palindrome.isPalindrome("ab", offByOne));
        assertTrue(palindrome.isPalindrome("a", offByOne));
        assertTrue(palindrome.isPalindrome("", offByOne));
        assertTrue(palindrome.isPalindrome("detrude", offByOne));
        assertFalse(palindrome.isPalindrome("century", offByOne));
    }

    @Test
    public void testByNIsPalindrome() {
        OffByN offBy3 = new OffByN(3);
        assertTrue(palindrome.isPalindrome("csadvf", offBy3));
        assertTrue(palindrome.isPalindrome("csaedvf", offBy3));
        assertFalse(palindrome.isPalindrome("century", offBy3));
    }

//    Uncomment this class once you've created your Palindrome class.
}
