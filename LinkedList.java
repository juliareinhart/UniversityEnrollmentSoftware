import application.MyGenericList.Node;

class MyGenericList <T extends Comparable<T> >//generic linked list class, can be used like an arrayList class, but, only with objects that implement the comparable interface, with a compareTo method

{
    private  class Node<T>
    {
                T value; //holds a value of type T
                Node<T>  next; //reference to the next node so we can traverse the list and they are linked

    }     
     
     private Node<T> first = null; // head of the list, initially null meaning list is empty
     int count=0; //counting size of the list, how many nodes there are, initially list is empty with a size/count of 0
   

    public void  add(  T element) // add a new node to the end of the list
     {
         Node<T> newnode = new Node<T>(); //create a new object new node
         newnode.value = element; //replace the value field with the element to be added
         newnode.next = null; //whatever is after the node points to null now because we added this element at the end of the list and the end of the list always points to null
        if (first== null) //if the list is empty, then initialize the head/first to the new node
        {
               first = newnode;
        }
        else //else, go to the last node using the function he provided, and point the next pointer of the last node to the new node
        {
               Node<T> lastnode = gotolastnode(first); 
               lastnode.next = newnode;
        }
         count++; //add one to the size/count of the list
     }

    public T get(int pos) //get the value at the specific position

    {
    	if (pos < 0 || pos >= count) //out of bounds
    	{
    	    throw new IndexOutOfBoundsException("Position " + pos + " is out of bounds.");
    	}

    	//walks the list position times and returns the value that was requested
         Node<T> Nodeptr = first;
         int hopcount=0; //counter variable, similar to i, count is the size of the list
         while (hopcount < count && hopcount<pos) //while hopcount is less than the size of the list and less than the position we want continue to traverse
         {   if(Nodeptr!=null) //if we haven't reached a null pointer, (end of list or corrupted list) keep traversing
                 {
                   Nodeptr = Nodeptr.next;
                 }
                hopcount++;
         }
        return  Nodeptr.value; //once we reach position, return the value at that position

    }

        private Node<T> gotolastnode(Node<T> nodepointer)
        {
          if (nodepointer== null ) //if the list is empty, return that node
           {
               return nodepointer;

               }
          else

          {
               if (nodepointer.next == null) // if we have reached the last element, return the nodepointer

               return nodepointer;

               else //else recursively call the last node function to traverse the list and find the last element in the list
                        return gotolastnode( nodepointer.next);

        }

        }
        
        public void sort() { //uses a bubble sort to sort the values in the list, just like we did for the homework
            if (first == null || first.next == null) {
                return; // nothing to sort, because the list is empty or only has one node
            }

            boolean swapped;
            do {//does each pass of the bubble sort
                swapped = false;
                Node<T> current = first;
                while (current.next != null) {
                    if (current.value.compareTo(current.next.value) > 0) {
                        // Swap the values
                        T temp = current.value;
                        current.value = current.next.value;
                        current.next.value = temp;
                        swapped = true;
                    }
                    current = current.next;
                }
            } while (swapped); //keeps sorting if there is at least one swap in the bubble sort meaning the list is still unsorted and needs to be checked thoroughly until the swapped is set to false, meaning it is in ascending order
        }
        
        public void update(int pos, T newValue) {
            if (pos < 0 || pos >= count) { //check the bounds for the position make sure it is within bounds
                throw new IndexOutOfBoundsException("Position " + pos + " is out of bounds.");
            }

            Node<T> current = first;
            int index = 0;

            while (index < pos) { //traverse the list until we find the position we need to update, then stop
                current = current.next;
                index++;
            }

            current.value = newValue; //update the new value
        }
        
        //plan on using this delete to replace an element, but couldn't remember which code my project used so also included a delete by position just in case, mine says "remove" will be changing it to "delete"
        public boolean delete(T element)
        {
            if (first == null)
            {
                return false; // List is empty
            }

            if (first.value.equals(element))
            {
                // Remove the first node
                first = first.next;
                count--;
                return true;
            }

            // Find the predecessor of the node to delete
            Node<T> pred = first;

            while (pred.next != null && !pred.next.value.equals(element))
            {
                pred = pred.next;
            }

            if (pred.next == null)
            {
                return false; // Element not found
            }

            // Element found so remove the node
            pred.next = pred.next.next;
            count--;
            return true;
        }
        
        public T delete(int pos)
        {
            if (pos < 0 || pos >= count)
            {
                throw new IndexOutOfBoundsException("Position " + pos + " is out of bounds.");
            }

            T element; // The element to return

            if (pos == 0)
            {
                // Removal of the first item in the list
                element = first.value;
                first = first.next;
            }
            else
            {
                // To remove an element other than the first,
                // find the predecessor of the element to be removed
                Node<T> pred = first;

                // Move pred forward pos − 1 times
                for (int k = 1; k <= pos - 1; k++)
                {
                    pred = pred.next;
                }

                // Store the value to return
                element = pred.next.value;

                // Route link around the node to be removed
                pred.next = pred.next.next;
            }

            count--;
            return element;
        }
        
        public int size() {
            return count;
        }
        
        public void clear() {
            first = null;  // removes reference to the head node
            count = 0;     // resets the size counter
        }
        
      //used to test whether or not the clear function worked, by trying to print the contents of the entire generic list vs one object in the list
        @Override
        public String toString() {
            if (first == null) return "[Empty List]";

            StringBuilder sb = new StringBuilder();
            Node<T> current = first;
            while (current != null) {
                sb.append(current.value).append("\n"); // this calls T's toString() — like the other classes that have the toString method
                current = current.next;
            }
            return sb.toString();
        }




        
        public void printAll() {
            Node<T> current = first;
            while (current != null) {
                System.out.println(current.value);
                current = current.next;
            }
        }


        
        

}