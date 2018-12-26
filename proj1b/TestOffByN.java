import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    @Test
    public void testOffByN() {
        OffByN offByN2 = new OffByN(2);
        OffByN offByN4 = new OffByN(4);
        assertTrue(offByN2.equalChars('a', 'c'));
        /*assertTrue(offByN2.equalChars('A', 'c'));
        assertTrue(offByN2.equalChars('a', 'C'));*/
        assertTrue(offByN2.equalChars('c', 'a'));
        assertFalse(offByN2.equalChars('a', 'd'));
        assertFalse(offByN2.equalChars('d', 'a'));
        assertTrue(offByN4.equalChars('a', 'e'));
        assertTrue(offByN4.equalChars('e', 'a'));
        assertFalse(offByN4.equalChars('f', 'a'));
        assertFalse(offByN4.equalChars('a', 'f'));
    }
}
