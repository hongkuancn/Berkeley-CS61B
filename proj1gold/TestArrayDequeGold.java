import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void testStudentArray() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        String failureSequence = new String();

        for (int i = 0; i < 50; i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne > 0.75) {
                sad.addFirst(i);
                ads.addFirst(i);
                failureSequence = failureSequence + "addFirst(" + i + ")\n";
            } else if (numberBetweenZeroAndOne > 0.5) {
                sad.addLast(i);
                ads.addLast(i);
                failureSequence = failureSequence + "addLast(" + i + ")\n";
            } else if (numberBetweenZeroAndOne > 0.25) {
                if (!sad.isEmpty() && !ads.isEmpty()) {
                    Integer a = sad.removeFirst();
                    Integer b = ads.removeFirst();
                    assertEquals(failureSequence, b, a);
                    failureSequence = failureSequence + "removeFirst()\n";
                }
            } else {
                if (!sad.isEmpty() && !ads.isEmpty()) {
                    Integer a = sad.removeLast();
                    Integer b = ads.removeLast();
                    assertEquals(failureSequence, b, a);
                    failureSequence = failureSequence + "removeLast()\n";
                }
            }
        }
        /*
        for (int i = 0; i < 10; i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne > 0.5) {
                sad.addFirst(i);
                ads.addFirst(i);
                failureSequence = failureSequence + "addFirst(" + i + ")\n";
            } else {
                sad.addLast(i);
                ads.addLast(i);
                failureSequence = failureSequence + "addLast(" + i + ")\n";
            }
        }


        for (int i = 0; i < 9; i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne > 0.5) {
                if (!sad.isEmpty() && !ads.isEmpty()) {
                    Integer a = sad.removeFirst();
                    Integer b = ads.removeFirst();
                    assertEquals(failureSequence, b, a);
                    failureSequence = failureSequence + "removeFirst()\n";
                }
            } else {
                if (!sad.isEmpty() && !ads.isEmpty()) {
                    Integer a = sad.removeLast();
                    Integer b = ads.removeLast();
                    assertEquals(failureSequence, b, a);
                    failureSequence = failureSequence + "removeLast()\n";
                }
            }
        }*/
    }
}
