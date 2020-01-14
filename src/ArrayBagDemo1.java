public class ArrayBagDemo1 {

	public static void main(String[] args) {
		// Adding to an initially empty bag with sufficient capacity
		System.out.println("Testing an initially empty bag with sufficient capacity:");
		BagInterface<String> aBag = new ResizableArrayBag<>();
		String[] contentsOfBag1 = {"A", "A", "B", "A", "C", "A"};
		testAdd(aBag, contentsOfBag1);

		// Filling an initially empty bag to capacity
		System.out.println("\nTesting an initially empty bag that will be filled to capacity");
		aBag = new ResizableArrayBag<>(7);
		String[] contentsOfBag2 = {"A", "B", "A", "C", "B", "C", "D", "another string"};
		testAdd(aBag, contentsOfBag2);
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
