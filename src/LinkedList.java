/** **********************************************
 * Name: Anjali Prabhala                         *
 * Course: CS 2336 - 002                         *
 * NetID: axp171330                              *
 * Description: Implementation of the LinkedList *
 * class. This class consists of the mergeSort   *
 * method and search method                      *
 ************************************************* */
package TieFighter1;

import java.text.DecimalFormat;

public class LinkedList {

    //attributes
    protected Node<Payload> head;
    protected Node<Payload> current;
    protected Node<Payload> tail;

    //constructor
    public LinkedList(Node<Payload> node) {
        head = tail = node;
        head.setNextNode(tail);
        tail.setPrevNode(head);
        tail.setNextNode(null);
    }

    //mutator method
    public void sethead(Node<Payload> head) {
        this.head = head;
    }

    //mutator method
    public void settail(Node<Payload> tail) {
        this.tail = tail;
    }

    //accessor method
    public Node<Payload> getHead() {
        return head;
    }

    //accessor method
    public Node<Payload> getTail() {
        return tail;
    }

    /**
     * The split method Function: this function splits a doubly linked list into
     * two doubly linked lists of half sizes. The main purpose of it is to split
     * the lists in the mergeSort method.
     *
     * @param head
     * @return temp
     */
    public Node<Payload> split(Node<Payload> head) {
        Node<Payload> first = head, second = head;
        while (first.next != null && first.next.next != null) {
            first = first.next.next;
            second = second.next;
        }
        Node<Payload> temp = second.next;
        second.next = null;
        return temp;
    }

    /**
     * The addLast method Function: adds a node at the end of the Linked List.
     * It takes in a node to add at the end of the list as a parameter.
     *
     * @param tmp: the node to add
     */
    public void addLast(Node<Payload> tmp) {
        if (tail != null) {
            tail.next = tmp;
            tmp.prev = tail;
            tail = tmp;
        }

        if (head == null) {
            head = tmp;
        }
    }

    /**
     * The merge1 method Function: this function returns the sorted list.
     *
     * @param n
     * @param order
     * @return
     */
    public Node<Payload> merge1(Node n, String order) {
        Node<Payload> temp = n;
        //call the mergeSort function and return the sorted array
        Node<Payload> sorted = mergeSort(temp, order);
       
        //checks for head and tail:  //CORRECTED CODE
        head = sorted;
        while(sorted.next != null)
        {
            sorted = sorted.next;
        }
        tail = sorted;
        return head;
        //FAULTY CODE:
        //return sorted;
    }

    /**
     * The mergeSort method Function: this function is the main function of the
     * mergeSort implementation. It takes in a node and the String
     * order(ascending or descending) and uses the merge function to return a
     * node.
     *
     * @param n: node
     * @param order : String (asc or dec)
     * @return sorted node
     */
    public Node<Payload> mergeSort(Node n, String order) {
        if (n == null || n.next == null) {
            return n;
        }
        Node second = split(n);

        //left and right halves
        n = mergeSort(n, order);
        second = mergeSort(second, order);

        return merge(n, second, order);
    }

    /**
     * The merge method Function: this function merges two list. It takes in two
     * nodes and an order String (ascending or descending) as parameters and
     * returns a list.
     *
     * @param first
     * @param second
     * @param order
     * @return merged list
     */
    public Node<Payload> merge(Node first, Node second, String order) {
        //if the first linkedlist is empty
        if (first == null) {
            return second;
        }

        //if the second linkedlist is empty
        if (second == null) {
            return first;
        }

        switch (order) {
            //ascending order for merge sort
            case "asc":
                if ((((Payload) (second.data)).compareTo((Payload) (first.data))) == 1) {
                    Node<Payload> node = first;
                    node.next = merge(first.next, second, order);
                    return node;
                } else {
                    Node<Payload> node = second;
                    node.next = merge(first, second.next, order);
                    return node;
                }
            case "dec":
                //descending order for merge sort
                if (((Payload) (first.data)).compareTo((Payload) (second.data)) == -1) {
                    Node<Payload> node = second;
                    node.next = merge(first, second.next, order);
                    return node;
                } else {
                    Node<Payload> node = first;

                    node.next = merge(first.next, second, order);
                    return node;
                }
            default:
                return null;

        }
    }

    /**
     * The search method Function: takes in an object as a parameter and returns
     * true or false depending on if the object is found or not found.
     *
     * @param x (an Object parameter)
     * @return true or false
     */
    public boolean search(Object x) {
        DecimalFormat df = new DecimalFormat("#.00");
        if (x instanceof Double) {
            Node<Payload> curr = head; // initialize current
            while (curr != null) {
                if (df.format(curr.data.area).equals(df.format((Double) x))) //found
                {
                    return true;
                }
                curr = curr.next;
            }
        }
        if (x instanceof String) {
            Node<Payload> curr = head; // initialize current
            while (curr != null) {

                if ((curr.data.name).equals((String) x)) //found
                {
                    return true;
                }
                curr = curr.next;
            }
        }
        return false;
    }

    //recursive implementation of the toString method
    @Override
    public String toString() {
        Node<Payload> temp = head;
        return helper(temp);

    }

    //helper method
    public String helper(Node<Payload> node) {
        if (node == null) {
            return "";
        }
        DecimalFormat df = new DecimalFormat("#.00");
        return (node.getData()).getName() + "\t\t" + df.format(node.getData().getArea()) + "\r\n" + helper(node.next);
    }

}
