package de.maxoeh.me.scanparse;

import de.maxoeh.me.datenstrukturen.Stack;

public class TerminalerScannerUE {

    public static void main(String[] args) {
    }

    public TerminalerScannerUE() { }

    public boolean analyse(String terminal) throws LexicalError, JavaCompileError {

        //Lexikalische Analyse
        Stack<Character> stack = new Stack<>();
        Stack<Character> stackCopy = new Stack<>();

        for (Character c : terminal.toCharArray()) {
            stack.push(c);
            stackCopy.push(c);
        }

        while (!stack.isEmpty()) {
            Character current = stack.top();
            if (current.equals('+') || current.equals('x') || current.equals('y') || current.equals('z')) {
                stack.pop();
            } else throw new LexicalError();
        }

        // Semantische Analyse / Parser

        // S -> A
        // A -> V | V + A
        // V -> X | Y | Y

        boolean evenodd = true;

        while (!stack.isEmpty()) {
            if (isV(stackCopy.top()) && evenodd) {
                stackCopy.pop();
                evenodd = false;
            } else if (stackCopy.top().equals('+') && !evenodd) {
                evenodd = true;
            } else throw new JavaCompileError();
        }

        return true;
    }

    boolean isV(Character c) {
        return c.equals('X') || c.equals('Y') || c.equals('Z');
    }


    static class LexicalError extends Exception {
        public LexicalError() {
            super("ERROR");
        }
    }

    static class JavaCompileError extends Exception {
        public JavaCompileError() {
            super("Compile Error");
        }
    }



}
