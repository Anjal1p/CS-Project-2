/** **********************************************
 * Name: Anjali Prabhala                         *
 * Course: CS 2336 - 002                         *
 * NetID: axp171330                              *
 * Description: Class to store the pilot's name  *
 * and area that can be later used in the main   *
 * as a payload object to set the area and name  *
 * the node.                                     *
 **************************************************/
package TieFighter1;

public class Payload implements Comparable<Payload> 
{
    //attributes
    String name; //pilot Name
    double area; //patrol area
    boolean flag; //flag that tracks the type of comparision
    
    //constructor
    public Payload(String name)
    {
        this.name = name;
        area = 0;
        flag = true;
    }
    //mutator method
    public void setName(String n)
    {
        name = n;
    }
     //mutator method
    public void setFlag(boolean bool)
    {
        flag = bool;
    }
    //mutator method
    public void setArea(double a)
    {
        area = a;
    }
    //accessor method
    public String getName()
    {
        return name;
    }
    //accessor method
    public double getArea()
    {
        return area;
    }
    //accessor method
    private boolean getFlag() 
    {
        return flag;
    }
    @Override
    public String toString()
    {
        return name + "\t\t" + area + "\r\n";
    }
    
    //compareTo method
    @Override
    public int compareTo(Payload o)
    {
        //if dealing with names/ Strings
        if(getFlag() == true)
        {
            if(getName().compareTo(o.getName()) <= 0)
            {
             return -1;  
            }
              
            else
            {
                return 1;
            }
        }
        //if dealing with areas/ double
        if(getArea() < (o.getArea()))
        {
            return -1;
        }
        else
        {
            return 1;
        }        
    } 
}
