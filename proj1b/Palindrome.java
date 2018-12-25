public class Palindrome {

    /**
     * Add string into Deque and return the deque.
     * @param word
     * @return
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> de = new ArrayDeque<>();
        int i;
        for (i = 0; i < word.length(); i++) {
            de.addLast(word.charAt(i));
        }
        return de;
    }

    public boolean isPalindrome(String word) {
        return isPalindromeHelper(word, 0);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindromeHelper(word,  0, cc);
    }

    private boolean isPalindromeHelper(String word, int index) {
        int oppositeIndex = word.length() - index - 1;
        if (index >= oppositeIndex) {
            return true;
        }
        if (word.charAt(index) == word.charAt(oppositeIndex)) {
            return isPalindromeHelper(word, index + 1);
        } else {
            return false;
        }
    }

    private boolean isPalindromeHelper(String word, int index, CharacterComparator cc) {
        int oppositeIndex = word.length() - index - 1;
        if (index >= oppositeIndex) {
            return true;
        }
        if (cc.equalChars(word.charAt(index), word.charAt(oppositeIndex))) {
            return isPalindromeHelper(word, index + 1, cc);
        } else {
            return false;
        }
    }

}
