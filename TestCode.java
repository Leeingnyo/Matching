
public class TestCode {
	
	public static void main(String[] args){
		
		try {
			//assert는 어떻게 쓰는 거지
			MyLinkedList<Point> linkedList = new MyLinkedList<Point>();
			Point item1 = new Point(3, 5);
			linkedList.add(item1);
			System.out.println(linkedList);
			Point item2 = new Point(4, 2);
			linkedList.add(item2);
			System.out.println(linkedList);
			linkedList.remove(item1);
			System.out.println(linkedList);
			
			MyAVLTree<Integer, Integer> test = new MyAVLTree<Integer, Integer>();
			test.insert(1, 1);
			test.insert(2, 2);
			test.insert(2, 4);
			test.insert(3, 3);
			test.insert(4, 4);
			test.insert(5, 5);
			test.insert(6, 6);
			test.insert(7, 7);
			test.insert(8, 8);
			test.insert(9, 9);
			System.out.println(test);
			System.out.println(test.search(2));

			HashTableForMatching<Item, Point> hashTable = new HashTableForMatching<Item, Point>();
			hashTable.put(new Item("000002"), new Point(2, 1));
			hashTable.put(new Item("000002"), new Point(2, 0));
			hashTable.put(new Item("000020"), new Point(2, 2));
			hashTable.put(new Item("000200"), new Point(2, 3));
			System.out.println(hashTable.indexOf(new Item("200000").hashCode()));
			System.out.println(hashTable.get(new Item("000002")));
			System.out.println(hashTable.indexOf(new Item("200001").hashCode()));
		} catch (AssertionError t) {
			t.printStackTrace(System.err);
			System.err.println("Test failed! Debug your program with the information above");
		}
	}
}
