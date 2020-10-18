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

    private String getReversedHelper(String word, CharacterComparator cc) {
        word = word.toLowerCase();
        Deque<Character> characterDeque = wordToDeque(word);
        String reversed = "";
        while (characterDeque.size() != 0) {
            reversed += characterDeque.removeLast();
        }
        return reversed;
    }

    /** using wordToDeque method */
    public boolean isPalindrome(String word) {
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
        return reversed.equals(word);
    }

    private boolean containsMiddle(String word) {
        if (word.length() % 2 == 0) {
            return false;
        }
        return true;
    }

    /** overloaded method, using CharacterComparator */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        int len = word.length();
        if (len == 0 || len == 1) {
            return true;
        }
        String reversed = getReversedHelper(word, cc);
        int i = 0;
        while (i < len) {
            if (containsMiddle(word) && i == len/2) {
                i += 1;
                continue;
            }
            if (!cc.equalChars(word.charAt(i), reversed.charAt(i))) {
                return false;
            }
            i += 1;
        }
        return true;
    }

}
