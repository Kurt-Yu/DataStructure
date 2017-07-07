class DoublyLinkedList{
	static Node head = null;

	class Node{
		int data;
		Node next, prev;

		Node(int data){
			this.data = data;
			next = null;
			prev = null;
		}
	}

	// Print the elements in DLL
	public void printList(Node node){
		Node last = node;
		System.out.println("Traversal in forward direction");
		while (node != null){
			System.out.print(node.data + " ");
			last = node;
			node = node.next;
		}

		System.out.println("\nTraversal in backword direction");
		while (last != null){
			System.out.print(last.data + " ");
			last = last.prev;
		}
		System.out.println();
	}
	
	/* Given a reference (pointer to pointer) to the head of a list
    and an int, inserts a new node on the front of the list. */
    public void push(Node node, int newData){
    	// Allocate node and initialise data
    	Node newNode = new Node(newData);  

    	// Make next of new node as head and previous as NULL
    	newNode.next = node;
    	newNode.prev = null;

    	// Change prev of head node to new node
    	if (node != null) node.prev = newNode;

    	head = newNode;  // Move the head to point to the new node
    }

    // Given a node as prev_node, insert a new node after the given node
    public void insertAfter(Node node, int newData){
    	if (node == null){      // Check if the given node is null
    		System.out.println("The given node cannot be null.");
    		return; 
    	}

    	Node newNode = new Node(newData);  // Allocate node and initialise data

    	newNode.next = node.next;  // Make next of new node as next of prevNode
    	newNode.prev = node;    // Make prevNode as previous of newNode
    	node.next = newNode;    // Make the next of prevNode as newNode
    	// Change previous of newNode's next node
    	if (newNode.next != null) newNode.next.prev = newNode;
    }


    /* Given a reference (pointer to pointer) to the head
    of a DLL and an int, appends a new node at the end  */
    public void append(Node node, int newData){
    	Node newNode = new Node(newData); // Allocate node and initialise data

    	//This new node is going to be the last node, so make next of it as null
    	newNode.next = null; 

    	//If the Linked List is empty, then make the new node as head
    	if (node == null){
    		newNode.prev = null;
    		head = newNode;
    		return;
    	}  

    	// Else traverse till the last node
    	Node temp = node;
    	while (temp.next != null){
    		temp = temp.next;
    	}

    	temp.next = newNode;  // Change the next of last node
    	newNode.prev = temp;  // Make last node as previous of new node
    }	

    // Given a node as prev_node, insert a new node before the given node
    public void insertBefore(Node node, int newData){
    	if (node == null){      // Check if the given node is null
    		System.out.println("The given node cannot be null.");
    		return; 
    	}

		Node newNode = new Node(newData);  // Allocate node and initialise data

		newNode.next = node;
		newNode.prev = node.prev;
		node.prev = newNode;
		if (newNode.prev != null) newNode.prev.next = newNode;    	
    }

    // Function to delete a node in a Doubly Linked List
    // node --> head node
    // del --> the node need to be deleted
    public void delete(Node node, Node del){
    	if (head == null || del == null) return;

    	// If node to be deleted is head node
    	if (head == del) head = del.next;

    	// Change next only if node to be deleted is NOT the last node
    	if (del.next != null) del.next.prev = del.prev;

    	// Change prev only if node to be deleted is NOT the first node
    	if (del.prev != null) del.prev.next = del.next;

    	return;
    }

    // Function to reverse a Doubly Linked List
	public void reverse(){
		Node temp = null;
		Node curr = head;

		// swap next and prev for all nodes of doubly linked list
		while(curr != null){
			temp = curr.prev;
			curr.prev = curr.next;
			curr.next = temp;
			curr = curr.prev;
		}

		// Before changing head, check for the cases like empty list
		// and list with only one node
		if (temp != null) head = temp.prev;
	}

	public static void main(String[] args){
		DoublyLinkedList list = new DoublyLinkedList();

		list.append(head, 1);
		list.append(head, 2);
		list.append(head, 3);
		list.append(head, 4);
		list.append(head, 5);
		list.append(head, 6);
		list.push(head, 7);
		list.push(head, 8);
		list.insertAfter(head.next, 10);
		list.insertBefore(head.next, 11);
		list.delete(head, head);
		list.reverse();
		list.printList(head);
	}
}



