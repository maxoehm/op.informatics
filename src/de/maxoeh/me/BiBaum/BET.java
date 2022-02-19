package de.maxoeh.me.BiBaum;

import java.util.Stack;

/**
 *  Binary Expression Tree (BET) implementing parser
 */
public class BET {

    // Declaration of the root
    private BinaryTree expressionRoot;
    // Declaration of the BinaryNode stack for evaluating postfix notation
    private Stack<BinaryTree> postfixStack = new Stack<BinaryTree>();
    // Declaration of the BinaryNode infix operand stack which will keep track of
    // operands read from an expression
    private Stack<BinaryTree> infixOperandStack = new Stack<BinaryTree>();
    // Declaration of the BinaryNode infix operator stack that will keep track of
    // operands read from an expression
    private Stack<BinaryTree> infixOperatorStack = new Stack<BinaryTree>();
    // Initialization of the counter used in the for loops
    int i = 0;

    // Default constructor
    public BET() {
    }

    // Expression constructor
    public BET(String expr, char mode) {

        // If the mode selected is postfix
        if (mode == 'p') {
            // Build the binary expression tree from the postfix expression; if the build
            // fails, throw an IllegalStateException
            if (!buildFromPostfix(expr)) {
                throw new IllegalStateException("Invalid notation: " + expr);
            }
            // If the mode selected is infix
        } else if (mode == 'i') {
            // Build the binary expression tree from the postfix expression; if the build
            // fails, throw an IllegalStateException
            if (!buildFromInfix(expr)) {
                throw new IllegalStateException("Invalid notation: " + expr);
            }
        }
    }

    // Builds a binary expression tree from a postfix expression
    public boolean buildFromPostfix(String postfix) {

        // Clears any existing tree and stacks
        makeEmpty();

        // Initializes a variable that will be used for storing each operator or operand
        // on each iteration of the loop
        String tempValue = "";

        // Check for any exceptions in the function
        try {

            // For loop which iterates all the way through the expression
            for (i = 0; i < postfix.length(); i++) {

                // Resets tempValue on every iteration of the loop
                tempValue = "";

                // If the character being read is a space, skip to the next iteration of the
                // loop
                if (postfix.charAt(i) == ' ') {
                    continue;
                    // If the character being read is an operator
                } else if (postfix.charAt(i) == '*' || postfix.charAt(i) == '/' || postfix.charAt(i) == '+'
                        || postfix.charAt(i) == '-') {

                    // Adds the operator to tempValue
                    tempValue += postfix.charAt(i);

                    // Create a subtree with the operator as the root and the 2 operands at the top
                    // of the operand stack as children
                    createSubtree(new BinaryTree(tempValue), 'p');

                    // Otherwise, create a variable from the string and push it to the stack
                } else {
                    postfixStack.push(new BinaryTree(buildVariable(postfix)));
                }
            }

            // If any exceptions are caught, return false
        } catch (Exception e) {
            return false;
        }

        // If the tree is empty after an intended build, return false
        if (isEmpty()) {
            return false;
        }

        // If the item at the top of the stack isn't an operator, return false
        if (!(postfixStack.peek().getElementChar() == '*' || postfixStack.peek().getElementChar() == '/'
                || postfixStack.peek().getElementChar() == '+' || postfixStack.peek().getElementChar() == '-')) {
            return false;
        }

        // If there is more than 1 item remaining in the stack, return false
        if (postfixStack.size() > 1) {
            return false;
        }

        // Set i variable back to 0 for next build
        i = 0;

        // Return true if the build was succesful
        return true;
    }

    // Builds a binary expression tree from an infix expression
    public boolean buildFromInfix(String infix) {

        // Check for any exceptions in the function
        try {

            // Clears any existing tree and stacks
            makeEmpty();

            // Initializes a variable that will be used for storing each operator or operand
            // on each iteration of the loop
            String tempValue = "";

            // For loop which iterates all the way through the expression
            for (i = 0; i < infix.length(); i++) {

                // Resets tempValue on every iteration of the loop
                tempValue = "";

                // If the character being read is a space, skip to the next iteration of the
                // loop
                if (infix.charAt(i) == ' ') {
                    continue;
                    // If the character being read is an operator
                } else if (infix.charAt(i) == '*' || infix.charAt(i) == '/' || infix.charAt(i) == '+'
                        || infix.charAt(i) == '-') {

                    // Adds the operator to tempValue
                    tempValue += infix.charAt(i);

                    // If the operand stack has less than 2 elements in it, push the operator onto
                    // the operator stack
                    if (infixOperandStack.size() < 2) {
                        infixOperatorStack.push(new BinaryTree(tempValue));
                        // If the operand stack has 2 or more elements in it
                    } else {

                        // If value does not have precedence over what is below it
                        if (!hasPrecedence(tempValue)) {
                            // Create a subtree with the operator as the root and the 2 operands at the top
                            // of the operand stack as children
                            createSubtree(infixOperatorStack.pop(), 'i');
                        }

                        // Push a node with the current operator onto the operator stack
                        infixOperatorStack.push(new BinaryTree(tempValue));

                    }
                    // If the character being read is a '('
                } else if (infix.charAt(i) == '(') {
                    // Make the tempValue equal the '('
                    tempValue += infix.charAt(i);
                    // Push the '(' onto the stack
                    infixOperatorStack.push(new BinaryTree(tempValue));
                    // If the character being read is a ')'
                } else if (infix.charAt(i) == ')') {

                    // Sets tempValue equal to a ')'
                    tempValue += infix.charAt(i);

                    // While the top value of the operator stack is not a '('
                    while (infixOperatorStack.peek().getElementChar() != '(') {

                        // Create a subtree with the operator at the top of the stack as the root and
                        // the 2 elements at the top of the operand stack as children
                        createSubtree(infixOperatorStack.pop(), 'i');
                        // PRECEDENCE ISSUE, DOESN'T CHECK FOR IT
                    }

                    // Pops the '(' off the stack
                    infixOperatorStack.pop();

                    // Otherwise, create a variable from the string and push it to the stack
                } else {
                    infixOperandStack.push(new BinaryTree(buildVariable(infix)));
                }
            }

            // While the operand stack has 2 or more elements and
            while (infixOperandStack.size() >= 2 && infixOperatorStack.size() >= 1) {
                createSubtree(infixOperatorStack.pop(), 'i');
            }

            // Set i variable back to 0 for next build
            i = 0;

            // If the stack still has more than 1 operand on the operand stack and any
            // operators on the operator stack, return false
            if (infixOperandStack.size() > 1 || infixOperatorStack.size() > 0) {
                return false;
            }

            // If any exceptions are caught, return false
        } catch (Exception e) {
            return false;
        }

        // If the tree is empty, return false
        if (isEmpty()) {
            return false;
        }

        // Return true if the build was succesful
        return true;

    }

    // Public function that calls the private print postfix expression function
    public void printPostfixExpression() {
        printPostfixExpression(expressionRoot);
        System.out.print("\n");
    }

    // Public function that calls the private print postfix expression function
    public void printInfixExpression() {
        printInfixExpression(expressionRoot);
        System.out.print("\n");
    }

    // Public function that returns the size of the tree
    public int size() {
        return size(expressionRoot);
    }

    // Public function that returns how many leaf nodes are in the tree
    public int leafNodes() {
        return leafNodes(expressionRoot);
    }

    // Public function that returns true if the tree is empty, and false if it is
    // not
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    // Function that checks to see if an operator has precedence over the operator
    // below it
    private boolean hasPrecedence(String tempValue) {

        // Set operator variable equal to the operator being passed into the function
        char operator = tempValue.charAt(0);

        // If the operator being read is a '*' or '/'
        if (operator == '*' || operator == '/') {
            // If the operator at the top of the stack is a '+' or '-', return true
            if (infixOperatorStack.peek().getElementChar() == '+'
                    || infixOperatorStack.peek().getElementChar() == '-') {
                return true;
            }
        }
        // If the operator below the operator being read is a '(', return true
        if (infixOperatorStack.peek().getElementChar() == '(') {
            return true;
        }
        // Return false if the operator doesn't have precedence over the operator at the
        // top of the stack
        return false;
    }

    // Function that clears anything in the binary expression tree or stacks
    private void makeEmpty() {
        expressionRoot = null;
        postfixStack.clear();
        infixOperandStack.clear();
        infixOperatorStack.clear();
    }

    // Function that constructs a variable of any length of characters from the
    // string
    private String buildVariable(String expr) {

        // Creates a null string
        String variable = "";

        // While the character being read is not at the end of the variable
        while (expr.charAt(i) != ' ') {

            // Add the character to the variable
            variable += expr.charAt(i);

            // If the next space in the string is the end, break out of the loop
            if (i + 1 == expr.length()) {
                break;
            }

            i++;
        }

        // Return the created variable
        return variable;
    }

    // Function that creates a subtree from an operator and 2 operands and pushes it
    // to the stack
    private void createSubtree(BinaryTree operatorNode, char mode) {
        // If mode is postfix expression
        if (mode == 'p') {
            // Sets the root equal to the operator
            expressionRoot = operatorNode;
            // Sets right node equal to the node at the top of the stack
            expressionRoot.setRightTree(postfixStack.pop());
            // Sets left node equal to the node at the top of the stack
            expressionRoot.setLeftTree(postfixStack.pop());
            // Pushes the tree onto the oper
            postfixStack.push(expressionRoot);
            // If mode is infix expression
        } else if (mode == 'i') {
            // Sets the root equal to the operator
            expressionRoot = operatorNode;
            // Sets right node equal to the node at the top of the operand stack
            expressionRoot.setRightTree(infixOperandStack.pop());
            // Sets left node equal to the node at the top of the operand stack
            expressionRoot.setLeftTree(infixOperandStack.pop());
            // Pushes the subtree onto the operand stack
            infixOperandStack.push(expressionRoot);
        }
    }

    // Private recursive function that counts how many leaf nodes are in the tree
    private int leafNodes(BinaryTree n) {

        // If n is at a null node, return 0
        if (n == null) {
            return 0;
        }

        // If n is a leaf, return 1
        if (n.isEmpty()) {
            return 1;
        }

        // Otherwise return the sum of the left leaf nodes plus the sum of the right
        // leaf nodes
        return leafNodes(n.getLeftTree()) + leafNodes(n.getRightTree());
    }

    // Private recursive function that prints the contents of the expression tree
    // into a postfix expression
    private void printPostfixExpression(BinaryTree n) {
        // If the node is null, return
        if (n == null) {
            return;
        }

        // Postorder traversal printing the elements of the tree in postfix notation
        printPostfixExpression(n.getLeftTree());
        printPostfixExpression(n.getRightTree());
        System.out.print(n.getContent() + " ");

    }

    // Private recursive function that prints the contents of the expression tree
    // into an infix expression
    private void printInfixExpression(BinaryTree n) {
        // If the node is not null
        if (n.getContent() != null) {
            // If the node is an operator, print a '('
            if (n.getElementChar() == '+' || n.getElementChar() == '-' || n.getElementChar() == '*'
                    || n.getElementChar() == '/') {
                System.out.print("( ");
            }

            // Inorder traversal printing the elements of the tree in infix notation
            printInfixExpression(n.getLeftTree());
            System.out.print(n.getRightTree() + " ");
            printInfixExpression(n.getRightTree());

            // If the node is an operator, print a '('
            if (n.getElementChar() == '+' || n.getElementChar() == '-' || n.getElementChar() == '*'
                    || n.getElementChar() == '/') {
                System.out.print(") ");
            }
        }
    }

    // Private recursive funtion that returns the size of the tree
    private int size(BinaryTree root) {
        // If the root is null, return 0
        if (root == null) {
            return 0;
        }
        // If the root is not null, add 1 to the number of nodes in the left and right
        // tree
        return 1 + size(root.getLeftTree()) + size(root.getRightTree());
    }

    // Binary node class
    class BinaryNode {

        // Variable declarations
        private String element;
        private BinaryNode left;
        private BinaryNode right;

        // Default constructor
        BinaryNode() {
        }

        // Binary node constructor that takes a string and sets the element variable to
        // it
        BinaryNode(String e) {
            element = e;
        }

        // Returns the element
        public String getValue() {
            return element;
        }

        // Returns the character at the first index of the element string
        public char getElementChar() {
            return element.charAt(0);
        }

        // Sets the left node
        public void setLeft(BinaryNode l) {
            left = l;
        }

        // Sets the right node
        public void setRight(BinaryNode r) {
            right = r;
        }

        // Returns the left node
        public BinaryNode left() {
            return left;
        }

        // Returns the right node
        public BinaryNode right() {
            return right;
        }

        // Returns true if node is a leaf, if not it returns false
        public boolean isLeaf() {
            if (left == null && right == null) {
                return true;
            }
            return false;
        }
    }
}