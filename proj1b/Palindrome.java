public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        int len = word.length();
        Deque<Character> characterDeque = new LinkedListDeque<>();
        for (int i = 0; i < len; i++) {
            char c = word.charAt(i);
            characterDeque.addLast(c);
        }
        return characterDeque;
    }

    /*
    public boolean isPalindrome(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        word = word.toLowerCase();
        String reversed = "";
        for (int i = word.length() - 1 ; i >= 0; i--) {
            reversed += word.charAt(i);
        }
        return reversed.equals(word);
    } */

    /** using wordToDeque method */
    public boolean isPalindrome(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        word = word.toLowerCase();
        Deque<Character> characterDeque = wordToDeque(word);
        String reversed = "";
        while (characterDeque.size() != 0) {
            reversed += characterDeque.removeLast();
        }
        return reversed.equals(word);
    }

    /** overloaded method, using CharacterComparator */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        int len = word.length();
        if (len == 0 || len == 1) {
            return true;
        }
        word = word.toLowerCase();
        Deque<Character> characterDeque = wordToDeque(word);
        String reversed = "";
        while (characterDeque.size() != 0) {
            reversed += characterDeque.removeLast();
        }
        int i = 0;
        while (i < len) {
            /** To ensure the two characters are off by N(according to the comparator)
             * and when the two characters are the same, it also should be passed */
            if (!(cc.equalChars(word.charAt(i), reversed.charAt(i)) || Math.abs(word.charAt(i) - reversed.charAt(i)) == 0)) {
                return false;
            }
            i += 1;
        }
        return true;
    }

}
