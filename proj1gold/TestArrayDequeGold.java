import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void test() {
        boolean isDisagree = false;
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        // using messages to record the sequence
        String messages = "";
        while (!isDisagree) {
            // 0 is addFirst, 1 is addLast, 2 is removeFirst, 3 is removeLast
            int randomRoundNum = StdRandom.uniform(4);
            int randomAddNum = StdRandom.uniform(100);
            if (randomRoundNum == 0) {
                sad.addFirst(randomAddNum);
                ads.addFirst(randomAddNum);
                messages = messages + "addFirst(" + randomAddNum + ")\n";
            } else if (randomRoundNum == 1) {
                sad.addLast(randomAddNum);
                ads.addLast(randomAddNum);
                messages = messages + "addLast(" + randomAddNum + ")\n";
            } else if (randomRoundNum == 2 && !sad.isEmpty()) {
                Integer sadFirst = sad.removeFirst();
                Integer adsFirst = ads.removeFirst();
                if (!sadFirst.equals(adsFirst)) {
                    isDisagree = true;
                    messages = messages + "removeFirst()";
                    assertEquals(messages, adsFirst, sadFirst);
                } else {
                    messages = messages + "removeFirst()\n";
                }
            } else if (randomRoundNum == 3 && !sad.isEmpty()) {
                Integer sadLast = sad.removeLast();
                Integer adsLast = ads.removeLast();
                if (!sadLast.equals(adsLast)) {
                    isDisagree = true;
                    messages = messages + "removeLast()";
                    assertEquals(messages, adsLast, sadLast);
                } else {
                    messages = messages + "removeLast()\n";
                }
            }
        }
    }

}
