/** **********************************************
 * Name: Anjali Prabhala                         *
 * Course: CS 2336 - 002                         *
 * NetID: axp171330                              *
 * Description: Implementation of the Node class *
 * which is generic class used in Linked List and*
 * to hold the Payload object.
 **************************************************/
package TieFighter1;

public class Node<T>
{
    //attributes
    protected T data; //generic variable to hold object
    protected Node<T> next; 
    protected Node<T> prev;
    
    //default
    public Node()
    {
        this.data = null;
    }
    //constructor
    public Node(T data)
    {
        this.data = data;
        next = null;
        prev = null;
    }
    //mutator method
    public void setData(T data)
    {
        this.data = data;
    }
    //mutator method
    public void setNextNode(Node<T> next)
    {
        this.next = next;
    }
    //mutator method
    public void setPrevNode(Node<T> prev)
    {
        this.prev = prev;
    }
    //accessor method
    public Node<T> getNextNode() 
    {
        return next;
    }
    //accessor method
    public Node<T> getPrevNode() 
    {
        return prev;
    }
    //accessor method
    public T getData()
    {
        return data;
    }
    //toString method
    @Override
    public String toString()
    {
        return "" + getData();
     }
}
