package de.maxoeh.me.datenstrukturen;


public class PalindromStackPruefung {

    public static class tools {
        public static boolean isPalindrom(String s) {
            return s.equals(new StringBuilder(s).reverse().toString());
        }

        public static boolean isPalindromeUsingStack(String s) {

            String resultString = "";
            char[] s1 = s.toCharArray();

            Stack<Character> stack = new Stack<>();

            for (char c : s1) {
                stack.push(c);
            }
            while (!stack.isEmpty()) {
                resultString = resultString + stack.pop();
            }

            return resultString.equals(resultString);
        }
    }


}
