package de.maxoeh.me;

import de.maxoeh.me.BiBaum.BET;
import de.maxoeh.me.BiBaum.MorseTree;
import de.maxoeh.me.BiBaum.WordCompletion;
import de.maxoeh.me.dast.PalindromStackPruefung;
import de.maxoeh.me.dast.Queue;
import de.maxoeh.me.dast.StackZahlQueue;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String [] args) {
        try {

            System.out.println("\n\ntest1: ( 3 + 2 ) * 3 + 1");
            BET test = new BET("( 3 + 2 ) * 3 + 1" , 'i');
            System.out.print("postfix: ");
            test.printPostfixExpression();
            System.out.print("infix: ");
            test.printInfixExpression();
            System.out.print("size: ");
            System.out.println(test.size());
            System.out.print("isEmpty: ");
            System.out.println(test.isEmpty());
            System.out.print("# of leaves: ");
            System.out.println(test.leafNodes());

        }
        catch(IllegalStateException e) {
            System.out.println(e.getMessage());
        }

    }

}
