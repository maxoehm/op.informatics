package de.maxoeh.me;

import de.maxoeh.me.BiBaum.BET;
import de.maxoeh.me.BiBaum.BinaryTree;

public class Application {

    public static void main(String [] args) {

        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        tree.setContent(5);
        tree.setLeftTree(new BinaryTree<Integer>(4, new BinaryTree<>(3), new BinaryTree<>(8)));
        tree.setRightTree(new BinaryTree<Integer>(12, new BinaryTree<>(6, new BinaryTree<>(1), new BinaryTree<>(7)), new BinaryTree<>(15, new BinaryTree<>(14), new BinaryTree<>(16))));

        tree.remove(tree, 5);
        var g = 5 + 1;
    }


    void BETTesting() {
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
