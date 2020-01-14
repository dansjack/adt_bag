public class LinkedBagDemo1 {

	public static void main(String[] args) {
		System.out.println("Creating an empty bag.");
		BagInterface<String> aBag = new LinkedBag<>();
		testIsEmpty(aBag, true);
		displayBag(aBag);

		String[] contentsOfBag = {"A", "D", "B", "A", "C", "A", "D"};
		testAdd(aBag, contentsOfBag);
		testIsEmpty(aBag, false);
	}

	private static void testIsEmpty(BagInterface<String> bag, boolean empty) {
		System.out.print("\nTesting isEmpty with ");
		if (empty) {
			System.out.println("an empty bag:");
		} else {
			System.out.println("a bag that is not empty:");
		}

		System.out.print("isEmpty finds the bag ");
		if (empty && bag.isEmpty()) {
			System.out.println("empty: OK.");
		} else if (empty) {
			System.out.println("not empty, but it is ERROR.");
 		} else if (!empty && bag.isEmpty()) {
			System.out.println("empty, but it is not empty: ERROR.");
		} else {
			System.out.println("not empty: OK");
		}
	}

	private static void testAdd(BagInterface<String> aBag, String[] content) {
		System.out.print("Adding the following strings to the bag: ");
		for (int i = 0; i < content.length; i++) {
			if (aBag.add(content[i])) {
				System.out.print(content[i] + " ");
			} else {
				System.out.print("\nUnable to add " + content[i] + " to the bag.");
			}
		}
		System.out.println();
		displayBag(aBag);
	}

	private static void displayBag(BagInterface<String> aBag) {
		System.out.print("The bag contains the following string(s): ");
		Object[] bagArray = aBag.toArray();
		for (int i = 0; i < bagArray.length; i++) {
			System.out.print(bagArray[i] + " ");
		}
		System.out.println();
	}

}
