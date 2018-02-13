public class TwoThreeTree {

	private Node head;
	
	public TwoThreeTree() {
		head = new Node();
	}
		
	// Insert a node into tree
	public boolean insert(int val) {
		Node correctNode = head.findLeaf(val);
		
		// If the node already contains the value then do not add duplicate node
		if (correctNode.containsValue(val)) {
			return false;
		// Else find the location to place the node
		} else {
			correctNode.insert(val);
			if (correctNode.isFull()) {
				correctNode.splitNode();
			}
		}
		return true;
	}
	
	// Find node starting from the head pointer node
	public String search(int val) {
		return head.findLeaf(val).toString();
	}
	
	private class Node {
		
		private Node parent;
		
		private Node[] children;
		private int[] keys;
		int numberOfKeys;

		private Node() {
			parent = null;
			children = new Node[4];
			keys = new int[3];
			numberOfKeys = 0;
		}

		private boolean containsValue(int searchedValue) {	
			for (int i = 0; i < numberOfKeys; i++) {
				if (keys[i] == searchedValue)
					return true;
			}
			return false;
		}

		private void insert(int val) {
			int index = 0;
			while (val > keys[index] && index < numberOfKeys) {
				index++;	
			}
			// make room for new node
			shiftArrays(index);
			keys[index] = val;
			numberOfKeys++;
		}

		// Shift current children and keys on the parent 
		// that you will be attaching the new child node after split
		private void shiftArrays(int fromLocation) {
			for (int i = keys.length - 1; i > fromLocation; i--) {
				keys[i] = keys[i-1];
			}
			for (int i = children.length - 1; i > fromLocation; i--) {
				children[i] = children[i-1];
			}	
			children[fromLocation] = null;
		}
		
		// Traverse tree until find the value or a leaf node
		private Node findLeaf(int value) {
			if (this.containsValue(value) || this.isLeaf()) {
				return this;
			} 
			
			if (value < this.keys[0]) {
				return this.children[0].findLeaf(value);
			} else if (this.numberOfKeys == 2 && value > this.keys[1]) {
				return this.children[2].findLeaf(value);
			} else {
				return this.children[1].findLeaf(value);
			}
		}
		
		// Helper methods to find know if node is leaf, root, or full
		private boolean isLeaf() {
			return this.children[0] == null;
		}
		
		private boolean isRoot() {
			return this.parent==null;
		}

		private boolean isFull() {
			return numberOfKeys == 3;
		}	
		
		private void splitNode() {
			
			Node currentNode = this;
			// Continue until parent node is not full
			while (currentNode != null && currentNode.isFull()) {
				if (currentNode.isRoot()) {
					splitRootNode(currentNode);	// at root
				} else {
					splitNonRootNode(currentNode);	// anywhere but root
				}
				currentNode = currentNode.parent;
			}
		}
		
		private void splitRootNode(Node currentNode) {
			Node newRoot = new Node();
			Node newLeftChild = new Node();
			Node newRightChild = new Node();
				
			newLeftChild.insert(currentNode.keys[0]);
			newRoot.insert(currentNode.keys[1]);
			newRightChild.insert(currentNode.keys[2]);
				
			newLeftChild.parent = newRoot;
			newRightChild.parent = newRoot;
			newRoot.children[0] = newLeftChild;
			newRoot.children[1] = newRightChild;
			
			setNewChildReferences(newLeftChild, newRightChild, currentNode);
			
			head = newRoot;
		}
		
		private void splitNonRootNode(Node currentNode) {
			Node newLeftChild = new Node();
			Node newRightChild = new Node();
			
			newLeftChild.insert(currentNode.keys[0]);
			currentNode.parent.insert(currentNode.keys[1]);		
			newRightChild.insert(currentNode.keys[2]);
			
			newLeftChild.parent = currentNode.parent;
			newRightChild.parent = currentNode.parent;
			
			attachNewChildrenToParent(newLeftChild, newRightChild);
			setNewChildReferences(newLeftChild, newRightChild, currentNode);
			
			currentNode = null;
		}
		
		private void attachNewChildrenToParent(Node newLeftChild, Node newRightChild) {
			int index = 0;
			while (newLeftChild.parent.children[index] != null) {
				index++;
			}
			newLeftChild.parent.children[index] = newLeftChild;
			newRightChild.parent.children[index+1] = newRightChild;
		}
		
		private void setNewChildReferences(Node newLeftChild, Node newRightChild, Node currentNode) {
			newLeftChild.children[0] = currentNode.children[0];
			newLeftChild.children[1] = currentNode.children[1];
			newRightChild.children[0] = currentNode.children[2];
			newRightChild.children[1] = currentNode.children[3];
			if(!newLeftChild.isLeaf()) {
				newLeftChild.children[0].parent = newLeftChild;
				newLeftChild.children[1].parent = newLeftChild;
				newRightChild.children[0].parent = newRightChild;
				newRightChild.children[1].parent = newRightChild;
			}
		}
		
		@Override
		public String toString() {
			if (numberOfKeys==1) {
				return this.keys[0] + "";
			} else {
				return this.keys[0] + " " + this.keys[1];
			}
		}
	}
}