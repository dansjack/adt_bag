public final class LinkedBag<T> implements BagInterface<T> {

	private Node firstNode; // Reference to first node
	private int numberOfEntries;

	public LinkedBag() {
		firstNode = null;
		numberOfEntries = 0;
	}

	/**
	 * Gets the current number of entries in this bag.
	 *
	 * @return the integer number of entries currently in the bag
	 */
	@Override
	public int getCurrentSize() {
		return 0;
	}

	/**
	 * Sees whether this bag is empty
	 *
	 * @return True if the bag is empty, or false if not
	 */
	@Override
	public boolean isEmpty() {
		return false;
	}

	/**
	 * Adds a new entry to this bag
	 *
	 * @param newEntry The objective to be added as a new entry
	 * @return True if the addition is successful, or false if not
	 */
	@Override
	public boolean add(T newEntry) { // Out of memory error possible
		// Add to begining of chain
		Node newNode = new Node(newEntry);
		newNode.setNextNode(firstNode);

		firstNode = newNode;
		numberOfEntries++;
		return true;
	}

	/**
	 * Removes one unspecified entry from this bag, if possible.
	 *
	 * @return Either the removed entry, if the removal was successful, or null.
	 */
	@Override
	public T remove() {
		T result = null;
		if (firstNode != null) {
			result = firstNode.getData();
			firstNode = firstNode.next;
			numberOfEntries--;
		}
		return result;
	}

	/**
	 * Removes one occurrence of a given entry from this bag, if possible.
	 *
	 * @param anEntry The entry to be removed.
	 * @return True if the removal was successful, or false if not
	 */
	@Override
	public boolean remove(T anEntry) {
		boolean result = false;
		Node nodeN = getReferenceTo(anEntry);
		if (nodeN != null) {
			nodeN.setData(firstNode.getData()); // replace located entry with entry in first node
			firstNode = firstNode.next; // remove first node
			numberOfEntries--;
			result = true;
		}
		return result;
	}

	/**
	 * Removes all entries from this bag
	 */
	@Override
	public void clear() {
		while (!isEmpty()) {
			remove();
		}
	}

	/**
	 * Counts the number of times a given entry appears in this bag.
	 *
	 * @param anEntry The entry to be counted.
	 * @return The number of times anEntry appears in the bag.
	 */
	@Override
	public int getFrequencyOf(T anEntry) {
		int frequency = 0;
		int loopCounter = 0;
		Node currentNode = firstNode;

		while ((loopCounter < numberOfEntries) && (currentNode != null)) {
			if (anEntry.equals(currentNode.data)) {
				frequency++;
			}
			loopCounter++;
			currentNode = currentNode.getNextNode();
		}
		return frequency;
	}

	/**
	 * Tests whether this bag contains a given entry.
	 *
	 * @param anEntry The entry to find.
	 * @return True if the bag contains anEntry, or false if not.
	 */
	@Override
	public boolean contains(T anEntry) {
		return getReferenceTo(anEntry) != null;
	}

	/**
	 * Retrieves all entries that are in this bag.
	 *
	 * @return A newly allocated array of all the entries in the bag. Note: If the bag is empty, the
	 * returned array is empty.
	 */
	@Override
	public T[] toArray() {
		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
			T[] result = (T[]) new Object[numberOfEntries];
		int index = 0;
		Node currentNode = firstNode;
		// Not necessary to use both checks in while loop, but provides a check
		// against mistakes in code
		while ((index < numberOfEntries) && (currentNode != null)) {
			result[index] = currentNode.data;
			index++;
			currentNode = currentNode.getNextNode();
		}
		return result;
	}

	private Node getReferenceTo(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;

		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.data)) {
				found = true;
			} else {
				currentNode = currentNode.getNextNode();
			}
		}
		return currentNode;
	}


	private class Node {
		private T data; // Entry in bag
		private Node next; // Link to next node

		private Node(T dataPortion) {
			this(dataPortion, null);
		}

		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		}

		private T getData() {
			return data;
		}

		private void setData(T newData) {
			data = newData;
		}

		private Node getNextNode() {
			return next;
		}

		private  void setNextNode(Node nextNode) {
			next = nextNode;
		}
	}

}
