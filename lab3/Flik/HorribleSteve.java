public class HorribleSteve {
    public static void main(String [] args) {
        Integer i = 0;
        for (Integer j = 0; i < 500; ++i, ++j) {
            if (!Flik.isSameNumber(i, j)) {
                break; // break exits the for loop!
            }
        }
        System.out.println("i is " + i);
    }
}
