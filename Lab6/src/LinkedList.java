import java.util.Scanner;

public class LinkedList<T extends Comparable<T>> {
    private Node first;



    public LinkedList() {



    }



    public void insert_to_begin(T item){
        first = new Node(item, first);
    }

    public void delete_first(){
        if (first != null){
            first = first.next;
        }
    }

    public void removeLast() {
        if (first != null) {

            Node recent = first;
            Node initial;
            initial = recent;
            while (recent.next != null) {
                initial = recent;
                recent = recent.next;
            }
            if (first.next == null) {
                first = null;
            }
            initial.next = null;
        }

    }

    int find_pos_in_list(T item) {
        Node node = first;
        int i = 0;
        while (node.data != item) {
            node = node.next;
            i++;
        }
        return i;
    }

    void insert_to_list_middle(Node insertHere, T item) {
        Node newnode;
        newnode = new Node(item, insertHere.next);
        insertHere.next = newnode;
    }

    public void insert_to_end(T item) {
        if (first != null) {
            Node lastnow = first;
            while (lastnow.next != null) {
                lastnow = lastnow.next;
            }
            lastnow.next = new Node(item, null);
        } else {
            first = new Node(item, null);
        }
    }


    private class Node {
        private T data;
        private Node next;

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    @Override public String toString() {
        StringBuilder s = new StringBuilder();
        Node p = first;
        while (p != null) {
            s.append(p.data + " ");
            p = p.next;
        }

        return s.toString();
    }

    public static void main(String[] args) {
        LinkedList<Character> list = new LinkedList<Character>();
        Scanner s = new Scanner(System.in);
        int order_no;
        char to_be_searched;
        try {
            list.removeLast();
            list.insert_to_end('?');
            list.removeLast();

            list.insert_to_end('x');
            list.insert_to_end('a');
            list.insert_to_end('b');
            list.insert_to_end('c');
            list.insert_to_end('d');
            list.insert_to_end('y');

            System.out.println("List: " + list);
            System.out.print("Enter first character to be searched ? ");
            to_be_searched = s.next().charAt(0);
            if ((order_no = list.find_pos_in_list(to_be_searched)) >= 0) {
                System.out.println("The order no is " + order_no);
            } else {
                System.out.println("Not found");
            }
            System.out.print("Enter second character to be searched ? ");
            to_be_searched = s.next().charAt(0);
            if ((order_no = list.find_pos_in_list(to_be_searched)) >= 0) {
                System.out.println("The order no is " + order_no);
            } else {
                System.out.println("Not found");
            }
            System.out.println("List: " + list);
            list.delete_first();
            System.out.println("List: " + list);
            list.removeLast();

            System.out.println("List: " + list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}