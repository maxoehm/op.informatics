package de.maxoeh.me.dast;



public class StackZahlQueue {

    public void run(Queue<Double> queue) {
        System.out.println(print(writeInStack(queue)));
    }

    private Stack<Double> stack;

    private Stack<Double> writeInStack(Queue<Double> queue) {
        stack = new Stack<>();

        while(!queue.isEmpty()) {
            stack.push(queue.dequeue());
        }
        //hallo

        return stack;
    }

    static String print(Stack<Double> stack) {
        String result = "Umgedrehte Reihenfolge: ";

        while (!stack.isEmpty()) {
            result = result + ";" + stack.pop();
        }

        return result;
    }
}
