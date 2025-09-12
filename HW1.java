
/*
 * Erick Diaz (COMP 272 002)
 * Homework # 1 (Programming Assignment). This Java class defines some basic
 * manipulation operations on Linked-Lists and Stacks.
 *
 * Additionally, there are two algorithm analysis methods where you need
 * to return a specified number as provided in comments of each method indicating
 * the complexity of the code shown. The testing routine will be looking for a
 * specific number returned.
 */

import java.util.Stack;

public class HW1 {

    /*
     * Class LinkedList
     *
     * This class builds a singly linked list. Each node of the linked-list
     * contains a single integer values.
     *
     * Methods:
     *  - void   sortInserted(val)     - Inserts value 'val' into the linked-list in
     *                                   sorted fashion
     *  - void   removeElementsLT(val) - Removed values in the linked-list that are less
     *                                   than 'val'
     *  - void   removeElement(val)    - Removes all values in the linked list of
     *                                   value 'val'
     *  - String toString()            - coverts and returns the linked-lists as a string
     *                                   delimited by brackets []
     *
     */

    static class LinkedList {
        static class Node {
            int data;
            Node next;

            Node(int d)  {        // Constructor
                data = d;
                next = null;
            }
        }
        Node head;                // head of Linked-list


        /*
         * Method sortedInsert() - this method will insert a new node to the
         * linked list containing the value specific in teh parameter 'data'.
         * The newly inserted node will be inserted in sorted order within
         * the linked-list.
         *
         */
        public void sortedInsert ( int data ) {
            Node new_node = new Node(data);

            new_node.next = null;

            // Special case for head node.
            if (this.head == null || head.data >= new_node.data ) {
                new_node.next = head;
                head = new_node;
            } else {
                // locate the node before the point of insertion
                Node current = this.head;

                // Identify where to place the item to insert
                while (current.next != null && current.next.data < data) {
                    current = current.next;
                }
                new_node.next = current.next;
                current.next = new_node;
            }

            return;
        }


        /*
         * Method removeElementsLT() - this method removes all nodes that contain a
         * value that is less than the provided parameter 'ltValue'.
         *
         * The method will invoke the method removeElements for each element
         * found in the linked-list that is less than thr parameter value passed.
         *
         */

        /**
         * Mutator Method: removeElementsLT()
         * @param ltValue
         * @return void
         * pre: LinkedList must NOT be empty
         *
         * removeElementsLT() will traverse a given LinkedList, check for any values less than the 'ltValue', and remove them.
         * Note: removeElementsLT() utilizes the removeElement() function below
         */

        public void removeElementsLT ( int ltValue ) {

            Node current = this.head;
            //checks if the LinkedList is empty
            if(current == null){
                return;
            } else {
                //Using removeElement(), traverse through LinkedList and remove each node if their data less than 'ltValue'
                while(current.next != null && current.data < ltValue){
                    removeElement(current.data);
                    current = current.next;
                }
            }
            return;
        }


        /*
         * Method removeElement() - this method removes all nodes that contain a
         * value equal to the value the provided parameter 'value'.
         *
         */

        /**
         * Mutator method: removeElement()
         * @param value
         * @return void
         *
         * pre: LinkedList must NOT be empty
         *
         * removeElement() will traverse through a given LinkedList and locate a node with the value EQUAL to the input variable 'value' and remove/unlink it.
         *
         */
        public void removeElement ( int value ) {

            Node current = this.head;
            Node prev = null;

            //check for empty LinkedList
            if(current == null){
                return;
            //check if the data of the head of the LinkedList is equal to the input variable 'value'
            } else if (current.data == value){
                head = current.next;
                return;
            } else {
                //traverse through LL while keeping note of previous node and current node links
                while(current.next != null){
                    prev = current;
                    current = current.next;
                    //unlinks current node if its data is equal to the input variable 'value'
                    if(current.data == value){
                        //unlinks previous node pointer and refers to current's next node pointer
                        prev.next = current.next;
                    }
                }
            }
            return;
        }


        /*
         * Method toString() - this is a helper method for printing / constructing
         * a string object from the linked-list.
         */
        public String toString () // Method to output the LinkedList as a String
        {
            String output = "[";
            Node currNode = this.head;
            while (currNode != null) {
                output += currNode.data + " ";
                currNode = currNode.next;
            }
            return output.trim() + "]";
        }

    } // End class LinkedList




    /*
     * Class Stacks
     *
     * This class utilizes the Java Common Framework Library Stack class.
     *
     * The intent of this class is to write methods which utilize the Java
     * library's Stack class. You need to utilize this library class in
     * your solution.
     *
     * Methods:
     *  - boolean isPalindrome(string)   - method returns true or false if 'string'
     *                                     is a palindrome
     *  - int     findlargestK(stack, k) - method accepts a stack and returns the
     *                                     the largest index in the stack containing
     *                                     value 'k' (stack index starts at 0)
     *
     */

    static class Stacks {

        /*
         * Method isPalindrome() - This method will return the boolean value 'true'
         * or 'false' on if the passed in parameter string is a palindrome or not.
         *
         * The routine should be case insensitive! Meaning 'RaCe cAr' is a palindrome.
         * Moreover, spaces are ignore, so both 'race car' and 'racecar' are palindromes.
         *
         * The method should utilize the provided Stack class.
         */

        /**
         * Mutator method: isPalindrome()
         * pre: input string must NOT be empty
         * @param input
         * @return boolean
         *
         * isPalindrome will store the string input variable in a temporary stack.
         * This results in a reversed string that will be used to check if the input is a palindrome by equality.
         */
        public static boolean isPalindrome(String input) {

            Stack<Character> stack = new Stack<>();
            input = input.toLowerCase().replaceAll("\\s+", "");

            int inputLength = input.length();
            String reversedInput = "";
            boolean palindrome = false;

            for(int i = 0; i < input.length(); i++){

                //using ASCII values, convert each char in input string to integers
                int currentChar = (int) input.charAt(i);

                //ASCII values that are [a-z] or [A-Z] are in range [65,90] and [97, 122] and checks whether a given character falls under them
                boolean upperCase = currentChar >= 65 && currentChar <= 90;
                boolean lowerCase = currentChar >= 97 && currentChar <= 122;
                //each char is stored in the temporary stack and is then pushed out to be stored in a reversedInput string
                if(lowerCase || upperCase){
                    stack.push(input.charAt(i));
                    reversedInput += stack.peek();
                }
            }
            //checks whether the input and reverseInput strings match (checking again for case insensitivity)
            if(reversedInput.equalsIgnoreCase(input)){
                return !palindrome;
            }
            return palindrome;
        }


        /*
         * Method findLargestk() - This method will return the largest index
         * position in the stack for the value specified by the parameter 'k'.
         *
         * Note that the bottom of the stack is index location 0. So it you push
         * on to the stack the values 3 4 9 4 4 7 4, in that order. And you pass the
         * value '4' for the parameter k, then the largest index position is index
         * location 6.
         *
         * The method should utilize the provided Stack class. This method should NOT
         * destroy the passed in stack, meaning when the method returns, the passed in
         * stack should be identical to when this method is passed. One trick as you
         * pop elements off the passed in stack, place them in a temp stack. Then when
         * completed, place them all back in teh original stack.
         */

        /**
         * Accessor methodL findLargestK()
         * pre: stack must NOT be empty
         * @param stack
         * @param k
         * @return position
         *
         * findLargestK() takes in both a stack and an integer 'k' and locates the position of such integer in the stack (assuming its present)
         * By utilizing a temp stack, findLargestK() notes each number's index in the stack and as it returns such integers to the original stack, notes their position.
         * Then, the method continues to check if another integer like integer k is present, but higher up in the stack, and notes its position.
         * If the integer k is not located, findLargestK()
         */
        public static int findLargestK(Stack<Integer> stack, int k) {

            Stack<Integer> stack2 = new Stack<>();
            int currentNum = 0;
            int index = 0;
            //position should be -1 if element k not found or empty stack, making it a default value
            int position = -1;

            //checks for empty stack
           if(stack.empty()){
               return position;
           } else {

               //Accounts for the number of integers in original stack then transfers them to the temp stack
               //NOTE: could've used stack.size() here but going to the oracle docs, its not stack class, its inherit from vector class..
               while(!stack.empty()){
                    currentNum = stack.pop();
                    stack2.push(currentNum);
                }

               //for every single pop in the temp stack (which goes back to original stack), check if its the target value and note its position using indexes and incrementing it every pop
               //Traverses the temp stack and accounts for matching integers to k
               //the higher the index, the more it would be in a higher position of the stack
               while(!stack2.empty()){
                    currentNum = stack2.pop();
                    if(currentNum == k){
                        position = index;
                    }
                    stack.push(currentNum);
                    index++;
                }
                return position;
           }

        }

    }  // End class Stack


    /*******************************
     *
     * Algorithm Analysis
     *
     * Below are two methods, algorithmAnalysis1 and algorithmAnalysis2.
     * Modify the return statement to return the correct option number.
     *
     *********************************/

    public static int algorithmAnalysis1(int n, int m) {
        int a = 0, b = 0;

        for (int i=0; i < n; i++) //up to n and increments by 1 -> O(N)
            a+= Math.random(); //constant

        for (int j=0; j < m; j++) //up to m and increments by 1 -> O(M)
            b+= Math.random();//constant
        // N + 1 + M + 1 -> N + M + 2 -> (drop constants) -> O(N+M) time complexity
        //This method doesn't take in any data structures nor iterates through one... so O(1) for space complexity...
        /*
         * Select the correct option listed below:
         *   1. O(N * M) time, O(1) space
         *   2. O(N + M) time, O(N + M) space
         *   3. O(N + M) time, O(1) space
         *   4. O(N * M) time, O(N + M) space
         *
         * TODO: return the answer (which option is correct), in the return statement
         */

        // RETURN THE CORRECT OPTION NUMBER LISTED ABOVE
        return 1;
    }


    public static int algorithmAnalysis2(int n) {
        int i, j, k = 0;
        for (i = n/2; i <= n; i++) //outer loop increments by 1, so O(N)
            for ( j = 2; j <= n; j = j*2 ) //inner loop multiples by 2, exponentially increases, meaning O(log n)
                k+= n/2;
        //IDK how to write the formula here, but could it be like
        //(counting the 6 for loop related constants and the reassignmen inside the inner loop)?
        //write like this?: ((1/2 * N)+ 3) * (log N + 4)??...
        // Regardless.... we drop constants -> O(N log N) time complexity!
        /*
         * Select the correct option listed below:
         *   1. O(N) time
         *   2. O(N log N) time
         *   3. O(N^2) time
         *   4. O(N^2Log n) time
         *
         * TODO: return the answer (which option is correct), in the return statement
         */

        // RETURN THE CORRECT OPTION LISTED ABOVE
        return 2;
    }

}

