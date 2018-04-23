public class LinkedList<T extends Comparable<T>> {
    private Node first;


    public LinkedList() {
        first = null;

    }

    public void add(T item) {
        if (first==null){
            first=new Node(item,null);
        }
        else{
            Node nextP=first;
            while (nextP.next!=null){
                nextP=nextP.next;
            }
            nextP.next=new Node(item,null);

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

        list.add('a');
        list.add('b');
        list.add('c');
        list.add('d');

        System.out.println("List: " + list);
    }
}
