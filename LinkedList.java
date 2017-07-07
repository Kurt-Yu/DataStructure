// LinkedList is a linear data structure

/*Difference between Linked List and Array:
	1. Array size is fixed, Linked LIst size is not fixed.

	2. Array memory is allocated either from Data section or Stack section.
	   Linked List memory is allocated from Heap section (e.g. using malloc() etc.). 

	3. Array is taken as a static data structure (residing in Data or Stack section).
	   Linked list is taken as a dynamic data structure (residing in Heap section).

	4. Array elements are stored in contiguous location.
	   LinkedList elements are not stored in contiguous location.
*/

class LinkedList{      

	static Node head;    // head of list         

	static class Node{    // Linked List Node, the inner class is made static
		int data;         // so that main() can access it
		Node next;
		Node(int data){    // constructor
			this.data = data;
			next = null;
		}
	} 

	// This function prints contents of linked list starting from head
	public void printList(){  
		Node temp = head;
		while (temp != null){
			System.out.print(temp.data + " ");
			temp = temp.next;
		}
		System.out.println();
	}

	// Add a node at the front
	public void push(int newData){   // O(1)
		Node newNode = new Node(newData);  // Allocate the Node & Put in the data
		newNode.next = head;   // Make next of new Node as head
		head = newNode;     // Move the head to point to new Node
	}

	// Add a node after a given node
	public void insertAfter(Node preNode, int newData){   // O(1)
		if (preNode == null){
			System.out.println("The given previous node cannot be true");
			return;
		}

		Node newNode = new Node(newData);  // Allocate the Node & Put in the data
		newNode.next = preNode.next;   // Make next of newNode as next of preNode
		preNode.next = newNode;   // Make next of preNode as newNode   
	}

	// Add a node at the end
	public void append(int newData){   // O(n)
		Node newNode = new Node(newData);

		//If the Linked List is empty, then make the new node as head
		if (head == null){
			head = new Node(newData);
			return;
		}
		// This new node is gonna be last node, so make next of it as null
		newNode.next = null;  

		Node last = head;
		while (last.next != null){   // Traverse till the last node
			last = last.next;
		}
		last.next = newNode;      // Change the next of last node
	}

	// Given a ksy, delete the first occurence of the key in Linked List
	public void deleteKey(int key){
		Node temp = head, prev = null;

		// If head itself holds the key to be deleted
		if (temp != null && temp.data == key){
			head = temp.next;
			return;
		}
		// earch for the key to be deleted
		while (temp != null && temp.data != key){
			prev = temp;
			temp = temp.next;
		}
		// If key was not presented in the Linked LIst
		if (temp == null) return;
		// Unlink the node from linked list
		prev.next = temp.next;
	}

	// Given a reference (pointer to pointer) to the head of a list
	// and a position, deletes the node at the given position
	public void deleteNode(int position){
		if (head == null) return;    // If the linked list is empty

		Node temp = head;    // Store head node
		// If head needs to be removed
		if (position == 0){
			head = temp.next;
			return;
		}
		// Find previous node of the node to be deleted
		for (int i = 0; temp != null && i < position - 1; i++){
			temp = temp.next;
		}

		if (temp == null || temp.next == null) return;

		// Node temp -> next is the node to be deleted,
		// Store pointer to the next of node to be deleted
		Node next = temp.next.next;
		temp.next = next;    // Unlink the deleted node from list
	}

	// Count number of nodes in a given singly linked list by iterative
	public int getCountIte(){
		Node temp = head;
		int count = 0;
		while (temp != null){
			count++;
			temp = temp.next;
		}
		return count;
	}

	// Count number of nodes in a given singly linked list by recursive
	public int getCountRec(Node node){
		if (node == null) return 0;
		return 1 + getCountRec(node.next);
	}

	// Funciton to swap Nodes x and y in linked list by changing links
	public void swapNodes(int x, int y){
		if (x == y) return;   // Nothing to do if x and y are the same

		// Search for x (keep track of prevX and CurrX)
		Node prevX = null, currX = head;
		while (currX != null && currX.data != x){
			prevX = currX;
			currX = currX.next;
		}

		// Search for y (keep track of prevY and CurrY)
		Node prevY = null, currY = head;
		while (currY != null && currY.data != x){
			prevY = currY;
			currY = currY.next;
		}

		// If either x or y is not present, do nothing
		if (currX == null || currY == null) return;  

		if (prevX != null) prevX.next = currY; // If x is not head of linked list
		else head = currY;  // else let y be the new head

		if (prevY != null) prevY.next = currX; // If y is not head of linked list
		else head = currX;  // else let x be the newhead

		// Swap next pointers
		Node temp = currX.next;
		currX.next = currY.next;
		currY.next = temp;
	}

	// Reverse a linked list in iterative method
	public Node reverseIte(Node node){
		Node prev = null;
		Node curr = node;
		Node next = null;
		while (curr != null){
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		node = prev;
		return node;
	}

	// Reverse a linked list in recursive method
	/*Time Complexity: O(n)
	  Space Complexity: O(1) */
	/*public Node reverseRec(Node curr, Node prev){
		if (curr.next == null){    // If we reached the last node,
			head = curr;           // make it to be the new head
			curr.next = prev;          
			return null;
		}

		Node temp = curr.next;   // When code reached the base case,
        curr.next = prev;        // "node" now refers to the second last node
		reverseRec(temp, curr);
		return head;
	}*/

	public Node reverseRec(Node node){
		if (node.next == null){   // If we reached the last node,
			head = node;          // make it to be the new head          
			return null;
		}

		reverseRec(node.next);

		Node temp = node.next;   // When code reached the base case,
        temp.next = node;        // "node" now refers to the second last node
		node.next = null;
		return head;
	}

	public Node sortedMerge(Node a, Node b){
		Node result = null;

		if (a == null) return b; // Base case
		if (b == null) return a;

		// Pick either a or b, and recursivly sort it
		if (a.data <= b.data){
			result = a;
			result.next = sortedMerge(a.next, b);
		}
		else {
			result = b;
			result.next = sortedMerge(b.next, a);
		}
		return result;
	}

	// Merge Sort for Linked Lists
	/* Step 1, we need a getMiddle method */
	public Node getMiddle(Node node){
		if (node == null) return node;

		Node fastPointer = node.next;
		Node slowPointer = node;

		while (fastPointer != null){
			fastPointer = fastPointer.next;
			if (fastPointer != null){
				slowPointer = slowPointer.next;
				fastPointer = fastPointer.next;
			}
		}
		return slowPointer;
	}

	// Step 2, mergeSort
	public Node mergeSort(Node node){
		if (node == null || node.next == null) return node;   // base case

		// Get the middle of the list
		Node middle = getMiddle(node);  
		Node nextToMiddle = middle.next;

		// This is the end of left linked list, so set to null
		middle.next = null;  

		Node left = mergeSort(node);      // Apply mergeSort on left list
		Node right = mergeSort(nextToMiddle);   // Apply mergeSort on right list

		Node sortedList = sortedMerge(left, right);
		return sortedList;
	}


	// Reverse a Linked List in groups of given size
	public Node reverseByGroup(Node head, int k){
		Node curr = head;
		Node next = null;
		Node prev = null;

		int count = 0;

		// Reverse first k nodes of linked list
		while (count < k && curr != null){
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
			count++;
		}

		// next now is a pointer to (k + 1)th node
		// Recursively call for the list starting from current
		// And make rest of the list as next of first node
		if (next != null){
			head.next = reverseByGroup(next, k);
		}
		return prev;
	}

	public static void main(String[] args){
        LinkedList list = new LinkedList();
        list.head = new Node(85);
        list.head.next = new Node(15);
        list.head.next.next = new Node(4);
        list.head.next.next.next = new Node(20);
         
        System.out.println("Given Linked list");
        list.printList();
        head = list.reverseByGroup(head, 2);
        System.out.println("");
        System.out.println("Reversed linked list ");
        list.printList();
	}
}