
import java.util.Scanner;

public class simpleList<T extends Comparable<T>> {
    private static final int MAX = 10;
    private int count;
    private T[] array;
    
    private void compress(T[] array, int empty_slot, int n) {
        for (int i = empty_slot + 1; i < n; i++)
            array[i - 1] = array[i];
    }

    public simpleList() {
        count = 0;
        array = (T[])new Comparable[MAX];
    }

    public boolean empty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public T at(int i) {
        return array[i];
    }

    public boolean insert_to_end(T item) {
        if (count < MAX) {
            array[count++] = item;
            return true;
        } else
            return false;
    }

    public int find_pos(T item) {
        for (int i = 0; i < count; i++)
            if (array[i].compareTo(item) == 0)
                return i;

        return -1;
    }

    public boolean del(int orderNo) {
        if (orderNo >= 0 && orderNo < count) {
            compress(array, orderNo, count);
            count--;
            return true;
        }
        else
            return false;
    }
    
    public static void main(String[] args) {
        simpleList<Time> list = new simpleList();
        Time             item;
        Scanner          s = new Scanner(System.in);
        int              i = 0;

        try {
            item = new Time(); item.read("Enter item? ");
            while (item.compareTo(new Time(0, 0)) != 0) {
                list.insert_to_end(item);
                item = new Time(); item.read("Enter item? ");
            }
            
            //Print the contents of the list
            for (i = 1; i <= list.size(); i++)
                System.out.print("\n " + i + "th item was " + list.at(i-1));
            
            item = new Time(); item.read("\nDelete list item ?");
            while (item.compareTo(new Time(0, 0)) != 0) {
                i = list.find_pos(item);
                if (i >= 0) {
                    System.out.print("\nThe position of the item in list is " + i);
                    list.del(i);
                } else
                    System.out.print("\nItem not found");
                
                item = new Time(); item.read("\nDelete list item ?");
            }
            
            //Print the contents of the list
            for (i = 1; i <= list.size(); i++)
                System.out.print("\n " + i + "th item was " + list.at(i-1));
            System.out.println();
        } catch(Exception e) {
        }
    }
}
