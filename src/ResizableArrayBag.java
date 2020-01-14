import java.util.Arrays;

public final class ResizableArrayBag<T> implements  BagInterface<T> {
	private T[] bag;
	private int numberOfEntries;
	private static final int DEFAULT_CAPACITY = 25;
	private boolean integrityOK;
	private static final int MAX_CAPACITY = 10000;

	/** Creates an empty bag whose initial capacity is 25. */
	public ResizableArrayBag() {
		this(DEFAULT_CAPACITY);
	}

	/** Creates an empty bag having a given initial capacity.
	 *
	 * @param desiredCapacity  The integer capacity desired. */
	public ResizableArrayBag(int desiredCapacity) {
		integrityOK = false;
		if (desiredCapacity <= MAX_CAPACITY) {
			// The cast is safe because the new array contains null entries
			@SuppressWarnings("unchecked")
			T[] tempBag = (T[]) new Object[desiredCapacity];
			bag = tempBag;
			numberOfEntries = 0;
			integrityOK = true;
		} else {
			throw new IllegalStateException("Attempt to create a bag whose capacity "
				+ "exceeds allowed maximum");
		}
	}


	/**
	 * Gets the current number of entries in this bag.
	 *
	 * @return the integer number of entries currently in the bag
	 */
	@Override
	public int getCurrentSize() {
		return numberOfEntries;
	}

	/**
	 * Sees whether this bag is empty
	 *
	 * @return True if the bag is empty, or false if not
	 */
	@Override
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}

	/**
	 * Adds a new entry to this bag
	 *
	 * @param newEntry The objective to be added as a new entry
	 * @return True if the addition is successful, or false if not
	 */
	@Override
	public boolean add(T newEntry) {
		checkIntegrity();
		if (isArrayFull()) {
			doubleCapacity();
		}
		bag[numberOfEntries] = newEntry;
		numberOfEntries++;
		return true;
	}

	// Returns true if the ArrayBag is full, or false if not
	private boolean isArrayFull() {
		return numberOfEntries >= bag.length;
	}

	/**
	 * Removes one unspecified entry from this bag, if possible.
	 *
	 * @return Either the removed entry, if the removal was successful, or null.
	 */
	@Override
	public T remove() {
		checkIntegrity();
		T result = removeEntry(numberOfEntries - 1);
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
		checkIntegrity();
		int index = getIndexOf(anEntry);
		T result = removeEntry(index);
		return anEntry.equals(result);
	}

	// Removes and returns the entry at a given array index. If no such entry exists, return null.
	private T removeEntry(int givenIndex) {
		T result = null;
		if (!isEmpty() && (givenIndex >= 0)) {
			result = bag[givenIndex];
			bag[givenIndex] = bag[numberOfEntries - 1];
			bag[numberOfEntries - 1] = null;
			numberOfEntries--;
		}
		return result;
	}

	// Locates a given entry within the array bag. Returns the index of the entry, if located, or -1
	private int getIndexOf(T anEntry) {
		int where = -1;
		boolean stillLooking = true;
		int index = 0;
		while (stillLooking && (index < numberOfEntries)) {
			if (anEntry.equals(bag[index])) {
				stillLooking = false;
				where = index;
			} else {
				index++;
			}
		}
		return where;
	}

	/**
	 * Removes all entries from this bag
	 */
	@Override
	public void clear() {
		while (remove() != null) {
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
		checkIntegrity();
		int counter = 0;
		for (int i = 0; i < numberOfEntries; i++) {
			if (anEntry.equals(bag[i])) {
				counter++;
			}
		}
		return counter;
	}

	/**
	 * Tests whether this bag contains a given entry.
	 *
	 * @param anEntry The entry to find.
	 * @return True if the bag contains anEntry, or false if not.
	 */
	@Override
	public boolean contains(T anEntry) {
		checkIntegrity();
		return getIndexOf(anEntry) > -1;
	}

	// Throws an exception if this object is not initialized
	private void checkIntegrity() {
		if (!integrityOK) {
			throw new SecurityException("ArrayBag object is corrupt.");
		}
	}

	private void doubleCapacity() {
		int newLength = 2 * bag.length;
		checkCapacity(newLength);
		bag = Arrays.copyOf(bag, newLength);
	}

	private void checkCapacity(int capacity) {
		if (capacity > MAX_CAPACITY) {
			throw new IllegalStateException("Attempt to create a bag whose capacity exceeds " +
				"allowed maximum of " + MAX_CAPACITY);
		}
	}

	/**
	 * Retrieves all entries that are in this bag.
	 * @return A newly allocated array of all the entries in the bag. Note: If the bag is empty, the
	 * returned array is empty.
	 */
	@Override
	public T[] toArray() {
		checkIntegrity();
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries];
		for (int i = 0; i < numberOfEntries; i++) {
			result[i] = bag[i];
		}
		return result;
	}
}
