package de.maxoeh.me.scanparse;

import de.maxoeh.me.datenstrukturen.Stack;

import java.util.function.Predicate;

public class KlammerParser {

    public static void main(String[] args) throws TerminalerScannerUE.LexicalError {
        KlammerParser klammerParser = new KlammerParser("{}{{}");
    }

    private Stack stack = new Stack();

    public KlammerParser(String parser) throws TerminalerScannerUE.LexicalError {

        // Predicate that checks if the current character is a left bracket
        Predicate<String> checkChar = c -> {
            for (char ch : c.toCharArray() )
                if(ch != '{' || ch != '}') {
                    return false;
            }
            return true;
        };

        checkChar.test(parser);

        for (Character c : parser.toCharArray()) {
            switch (c) {
                case '{' -> {
                    stack.push('k');
                }

                case '}' -> {
                    if (stack.top().equals('k')) {
                        stack.pop();
                    } else throw new TerminalerScannerUE.LexicalError();

                }
            }
        }

        if (stack.isEmpty()) {
            System.out.println("Korrekt");
        } else System.out.println("Fehlerhaft");

    }

}
