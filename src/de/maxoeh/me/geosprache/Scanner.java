package de.maxoeh.me.geosprache;

import de.maxoeh.me.datenstrukturen.Stack;

import java.util.Arrays;

public class Scanner {

    public Scanner(String code) {
        tokenList(code);
        lexScan();
    }

    private Stack<String> toTestTokens = new Stack<>();
    public Stack<String> tokens = new Stack<>();

    public void tokenList(String input) {
        Arrays.stream(input.split(" ")).forEach(
                token -> toTestTokens.push(token)
        );
    }
    public boolean lexScan() {

        while (!toTestTokens.isEmpty()) {
            switch (toTestTokens.top()) {
                case "re", "vw", "li", "wh", "[", "]" -> {
                    tokens.push(toTestTokens.pop());
                }
                default -> {
                    if (toTestTokens.top().matches("[0-9]+")) {
                        tokens.push(toTestTokens.pop());
                    } else return false;
                }
            }
        }
        return false;
    }


}
