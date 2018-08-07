///** *********************************************************
// * Name: Anjali Prabhala                                    *
// * Course: CS 2336 - 002                                    *
// * NetID: axp171330                                         *
// * Description: The main purpose of the program is to       *
// * implement LinkedList using classes and perform other     *
// * function such as searching, sorting, printing, etc.      *
// * using the LinkedList object. It takes input from two     *
// * files in which it calculates areas of pilots with        *
// * given coordinates from the first file and sorts/searches *
// * the list based on user input from the second file and    *
// * prints the results to their corresponding output files.  *
// *                                                          *
// * Merge sort implementation line numbers                   *
// * - In LinkedList class: line 110 to 121                   *
// *      helper functions: line 133 to 172                   *
// * - In mergeSort function call: line 180                   *
// *                                                          *
// ************************************************************/
package TieFighter1;

import java.io.*;
import java.util.*;

public class Main {

    //variables
    public static String[] pilotNames;
    public static double[][][] pilotCoordArray;
    public static double[] pilotArea;
    public static int pilotIndex;
    public static int numOfCoord;
    public static int totalNumOfCoord;
    public static Node<Payload> node = new Node<Payload>();
    private static LinkedList obj = new LinkedList(node);

    public static void main(String[] args) throws FileNotFoundException, IOException {
       
        //number of pilots
        pilotIndex = 0;
        //number of coordinates
        numOfCoord = 0;
        //Single Dimension string array to store pilot names
        pilotNames = new String[20];
    
        
        //function calls
        processPilotAreas();
        processCommand();         
    }
    /**
     * The calculateNumPilot method:
     * Function: calculates and returns the number of pilots in the file by taking
     * the file name as a string parameter
     * @param fileName
     * @return numPilot number of pilots
     * @throws java.io.FileNotFoundException
     */        
    public static int calculateNumPilot(String fileName)throws FileNotFoundException
    {
        File file = new File(fileName);
        //variable to store the number of pilots
        int numPilot = 0;
        //checks if the file exists
        if(file.exists())
        {
            Scanner scan = new Scanner(file);
            //goes through each line in the file and increments the number of pilots
            while(scan.hasNextLine())
            {
                //increment the number of pilots
                numPilot++;
                scan.nextLine();
            }
        }
        return numPilot;
    }
    
    /**
     * The calculateCoord method:
     * Function: calculates and returns the maximum number of coordinates
     * from all the coordinates in the pilot_routes.txt input file.
     * @param fileName
     * @return max: the maximum number of coordinates
     * @throws java.io.FileNotFoundException
     */    
    public static int calculateCoord(String fileName)throws FileNotFoundException
    {
        File inputFile = new File(fileName);
        //store all the coordinates in an arrayList
        ArrayList<Integer>numCoords = new ArrayList<>();
        //check if the file exists
        if(inputFile.exists())
        {
            //variable to find the number of coorinate in each line
            int num = 0;
            Scanner scan = new Scanner(inputFile);
            while(scan.hasNextLine())
            {
                String line = scan.nextLine();
                for(int i = 0; i < line.length(); i++)
                {
                    //checks for a comma and then increments the number of coordinates
                    if(line.charAt(i) == ',')
                    {
                        num++;
                    }
                }
                if(num != 0)
                {
                    numCoords.add(num);                 
                }
            }
                                 
        }
        //max is an int variable to store the maximum number of coordnates
        int max = 0;
        for(int j = 0; j < numCoords.size(); j++)
        {
            if(max < numCoords.get(j))
            {
                max = numCoords.get(j);
            }
        }
        return max;
    }
    
    
    /**
     * The processCommand method:
     * Function: Processes the command input and output files and also
     * checks for valid input.
     * @throws java.io.IOException
     */ 
    public static void processCommand() throws IOException 
    {
        //open commands for the second input  and output files
        File input2 = new File("commands3.txt");
        File output2 = new File("results.txt");
        PrintWriter out = new PrintWriter(output2);
        Scanner scan = new Scanner(input2);
        out.println();
        //go through each line in the file using a scanner object
        while (scan.hasNextLine()) 
        { 
            //store the entire line in a string named line
            String line = scan.nextLine();
            //seperate the words by a space using split and store it in a string array named temp
            String[] temp = line.split(" ");
            //input validation 
            if (temp[0].equals("sort")) 
            {
                 //check if the sorting attribute equals "pilot" or "area"
                if(temp[1].equals("pilot")||temp[1].equals("area"))
                {
                    if(temp[1].equals("pilot"))
                    {
                        //get the head for the node
                        Node<Payload> node1 = obj.getHead();
                        //while the head of the linkedlist does not equal null
                        while(node1 != null)
                        {
                            node1.getData().flag = true;
                            node1 = node1.next;
                        }
                    }
                    else
                    {
                        Node<Payload> node1 = obj.getHead();
                        while(node1 != null)
                        {
                            node1.getData().flag = false;
                            node1 = node1.next;
                        }
                    }
                    //check if the order equals "asc" or "dec"
                    if(temp[2].equals("asc") ||temp[2].equals("dec"))
                    {
                        Node<Payload> temp1 = obj.merge1(obj.getHead(), temp[2]);
                        obj.sethead(temp1);
                        
                        out.printf("%-20s  %-20s%n", "Head: " + obj.getHead(), "Tail: " + obj.getTail());
                        out.println();
                    }
                }
            }
            //if not a sort function command
            //input validation to make sure the pilotName has valid  characters and/or the pilot Areas contain all digits using regex 
            else if(line.matches("^(\\p{Alnum}*+((\\s|['-]+)\\p{Alnum})?)*+$"))
            {
                //node to search if the given area or pilot is in the LinkedList or not.
                //If it is in the LinkedList, the search method returns true
                //FAULTY CODE:
                //if(obj.search(temp[0]))
                if(obj.search(line))
                {
                    //if found
                   out.printf("%-20.20s  %-20.20s%n", line, "found");
                }
                else{
                    //if not found
                    out.printf("%-20.20s  %-20.20s%n", line, "not found");
                }
            }
            else if(temp[0].matches("[0-9.]+"))
            {
                if(obj.search(Double.parseDouble(temp[0])))
                {
                    //if found
                   out.printf("%-20.20s  %-20.20s%n", temp[0], "found");
                }
                else{
                    //if not found
                    out.printf("%-20.20s  %-20.20s%n", temp[0], "not found");
                }
            }            
        }
        out.close();
    }
   
    /**
     * The processPilotArea method:
     * Function: The process pilot areas processes the input for file1, pilot_routes.txt
     * and does input validation. It also calls the function printFile1() which prints 
     * the output for file 1, pilot_areas.txt.
     * @throws java.io.IOException
     */ 
    public static void processPilotAreas() throws IOException 
    {
        //returns true if first node is not null
        boolean firstNode = true;
        File input1 = new File("pilot_routes3.txt");
        Scanner scan = new Scanner(input1);
        pilotCoordArray = new double[calculateNumPilot(input1.getName())][calculateCoord(input1.getName())][2];    
        //goes through each line of the file
        while (scan.hasNextLine()) 
        {
            String line = scan.nextLine();
            String[] temp = line.split(" ");
            //checking if the first part of the line is a String using regex
            if (temp[0].matches( "^(\\p{Alnum}*+((\\s|['-]+)\\p{Alnum})?)*+$")) 
            {
                //store the name or part of the name in the string name
                String name = temp[0];
                //boolean variable to check if the name is more than one word. Returns true once the name is found
                boolean nameFound = false;
                int i = 1;
 
                //if name is more than one String
                while (!nameFound && i < temp.length) {
                    if (!(temp[i].matches("^(\\p{Alnum}*+((\\s|['-]+)\\p{Alnum})?)*+$"))) 
                    {
                        nameFound = true;
                    }
                    else
                    {
                        name += " " + temp[i];
                        i++;
                    }
                }
                //once the full name is found and stored in the variable name, name Found is true
                if (nameFound == true)//&& line.substring(name.length()).matches(line))//&& line.substring(name.length()).matches(number,number+) 
                {
                    
                    //CORRECTED CODE
                    if(line.substring(name.length()).matches("( (-?[0-9]+(\\.[0-9])?)+,(-?[0-9]+(\\.[0-9]+)?))+")){
                    //store all the names in the array pilotNames
                    pilotNames[pilotIndex] = name;
                    
                    for (int x = i; x < temp.length; x++) 
                    {
                        String[] comma = temp[x].split(",");

                        //regular expression didnt consider for two decimal points and
                        //FAULTY CODE:
                        //if(comma[0].matches("[0-9.]+") || comma[1].matches("[0-9.]+"))
                        //{
                            //gets the x coordinate  
                            pilotCoordArray[pilotIndex][x - i][0] = Double.parseDouble(comma[0]);
                            //gets the y coordinate
                            pilotCoordArray[pilotIndex][x - i][1] = Double.parseDouble(comma[1]);
                        //}
                    }

                    }
                    else
                        continue;
                }
                    
                //node for each pilotNames as the linkedList has to be filled with names and areas
               if(!(line.equals("")))
               {
                   
                    node = new Node<>(new Payload(pilotNames[pilotIndex]));
                    //set the area of the node by calling the calculate area fucntion and getting the area of the pilot
                    node.getData().setArea(calculateArea(pilotIndex));
                    
                    //if the node is the first node, then set the node as the head of the linkedlist
                    if(firstNode)
                    {
                        obj = new LinkedList(node);
                        firstNode = false;
                    }
                    //else, add the node to the list
                    else
                    {
                        if(!(obj.search(node.getData().getName())))
                        {
                            obj.addLast(node);
                        }
                    }
                }
            }
            //increment the pilot count for each line
            pilotIndex++;
        }
        //function call for printFile1 
         printFile1(obj);
        pilotIndex = 0;
        //close the scanner object
        scan.close();
    }


    /**
     * The calculate area method:
     * Function: calculates and returns the area of a given pilot taking in the 
     * number of pilots and number of coordinates are parameters.
     * @param pilotIndex
     * @return 
     */ 
    public static double calculateArea(int pilotIndex) 
    {
        //***
        //sum of the coordinates using the formula
        double sumCoord = (pilotCoordArray[pilotIndex][0][0] + pilotCoordArray[pilotIndex][1][0])
                            * (pilotCoordArray[pilotIndex][1][1] - pilotCoordArray[pilotIndex][0][1]);
            
        int j = 1; 
        while(!((pilotCoordArray[pilotIndex][j][0] == pilotCoordArray[pilotIndex][0][0]) && (pilotCoordArray[pilotIndex][j][1] == pilotCoordArray[pilotIndex][0][1])))
        {
            sumCoord += (pilotCoordArray[pilotIndex][j+1][0] + pilotCoordArray[pilotIndex][j][0])
                        * (pilotCoordArray[pilotIndex][j+1][1] - pilotCoordArray[pilotIndex][j][1]);
            j++;
        }
         return 0.5 * Math.abs(sumCoord);
    }
    
    /**
     * The printFile1 method:
     * Function: prints the LinkedList to the file pilot_areas.txt
     * using the toString function.
     * @param list which is a LinkedList object
     * @throws java.io.FileNotFoundException
     */
    public static void printFile1(LinkedList list) throws FileNotFoundException 
    {
       File outputFile = new File("pilot_areas.txt");
       PrintWriter out = new PrintWriter(outputFile);
       //calls the toString object of the list to print the output into the file
       out.print(list.toString());
       out.close();
    }    

}
