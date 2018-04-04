public class simpleStack<T extends Comparable<T>> {
    private static final int MAXN = 10;
    private int top;
    private T[] array;

    public simpleStack() {
        top = -1;
        array = (T[])new Comparable[MAXN];
    }

    public boolean push(T item) {
        if (top >= MAXN-1)
            return false;
        else
            array[++top] = item;
        return true;
    }

    public T pop() {
        if (top == -1)
            return null;
        else
            return array[top--];
    }

    public void print() {
        for (int i = top; i >= 0; i--)
            System.out.print(array[i] + " ");
    }




    public static void main(String[] args) {
        simpleStack<Character> stack = new simpleStack();
        Character   item;

        System.out.println("Enter a letter to push onto stack");
        System.out.println("or digit 1 to take a letter from stack");
        System.out.println("Return to end the program\n");
        try {
            item = new Character((char)System.in.read());
            while (item.compareTo(new Character('\n')) != 0) {
                if(item.compareTo(new Character('+')) == 0){
                    int  a = Character.getNumericValue(stack.pop());
                    int  b = Character.getNumericValue(stack.pop());
                    int sum = a + b;
                    stack.push(Integer.toString(sum).charAt(0));
                }
                else if(item.compareTo('-') == 0){
                    int i1= Character.getNumericValue(stack.pop());
                    int i2= Character.getNumericValue(stack.pop());
                    int i3=i2-i1;
                    char c=Integer.toString(i3).charAt(0);
                    stack.push(c);
                }
                else if(item.compareTo('=') == 0){
                    char  b=stack.pop();
                    System.out.println("The top stack value is: " + b);
                    stack.push(b);
                }
                else if (item.compareTo('Q') == 0){
                    System.out.print("Stack content: ");
                    stack.print();
                    return;
                }              /*  if (item.compareTo(new Character('1')) == 0)
                    System.out.println("Letter popped from stack is " + stack.pop());*/
                else{
                    stack.push(item);
                }
                item = new Character((char)System.in.read());
                System.out.println(item);
            }
            System.out.println();
        } catch(Exception e) {
            System.out.println("Exception " + e);
        }
    }
}
