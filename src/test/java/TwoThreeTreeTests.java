import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class TwoThreeTreeTests {
	
	private int heightOfTree = 15;
	
	@Test
	public void testOneInsert() {
		TwoThreeTree t = new TwoThreeTree();
		t.insert(10);
		
		//				10
		
		assertEquals("10", t.search(10));
	}

	
	@Test
	public void testNotThere() {
		TwoThreeTree t = new TwoThreeTree();
		t.insert(10);
		t.insert(11);
		t.insert(12);
		t.search(10);
		assertEquals("10", t.search(10));
	}

	
	@Test
	public void testOneSplit() {
		TwoThreeTree t = new TwoThreeTree();
		t.insert(10);
		t.insert(11);
		t.insert(12);	// first split root
		
		//				11
		//			   /  \
		//			  10  12
		
		assertEquals("10", t.search(10));
		assertEquals("11", t.search(11));
		assertEquals("12", t.search(12));
	}

	@Test
	public void testSplitLeftSideParentWithTwoChildren() {
		TwoThreeTree t = new TwoThreeTree();
		t.insert(9);
		t.insert(10);
		t.insert(11);	// first split root
		t.insert(8);
		t.insert(7);	// second split (parent with 2 children) from the left side
		
		//				8,10
		//			   / |  \
		//			  7  9   11
		
		assertEquals("7", t.search(7));
		assertEquals("9", t.search(9));
		assertEquals("11", t.search(11));
		assertEquals("8 10", t.search(8));
		assertEquals("8 10", t.search(10));		
	}
	
	@Test
	public void testSplitRightSideParentWithTwoChildren() {
		TwoThreeTree t = new TwoThreeTree();
		t.insert(9);
		t.insert(10);
		t.insert(11);	// first split root
		t.insert(12);
		t.insert(13);	// second split (parent with 2 children) from the right side
		
		//				10,12
		//			   /  |   \
		//			  9   11   13

		assertEquals("9", t.search(9));
		assertEquals("11", t.search(11));
		assertEquals("13", t.search(13));
		assertEquals("10 12", t.search(10));
		assertEquals("10 12", t.search(12));	
	}
	
	@Test
	public void testAddValueAfterTwoSplits() {
		TwoThreeTree t = new TwoThreeTree();
		t.insert(9);
		t.insert(10);
		t.insert(11);	// first split root
		t.insert(12);
		t.insert(13);	// second split (parent with 2 children) from the left side
		t.insert(8);
		
		//				10,12
		//			   /  |   \
		//			 8,9   11   13

		assertEquals("8 9", t.search(8));
		assertEquals("8 9", t.search(9));
		assertEquals("11", t.search(11));
		assertEquals("13", t.search(13));
		assertEquals("10 12", t.search(10));
		assertEquals("10 12", t.search(12));	
	}
	
	@Test
	public void testLeftSplitFourNodeRoot() {
		TwoThreeTree t = new TwoThreeTree();
		t.insert(9);
		t.insert(10);
		t.insert(11);	// first split root
		t.insert(12);
		t.insert(13);	// second split (parent with 2 children) from the right side
		t.insert(8);
		t.insert(7);	// third split (parent with 3 children) from the left side then Four Node root split
		
		//				  10
		//				/    \
		//			   8     12
		//			  / \   /  \
		//			 7   9 11  13

		assertEquals("7", t.search(7));
		assertEquals("8", t.search(8));
		assertEquals("9", t.search(9));
		assertEquals("10", t.search(10));
		assertEquals("11", t.search(11));
		assertEquals("12", t.search(12));
		assertEquals("13", t.search(13));
	}
	
	@Test
	public void testRightSplitFourNodeRoot() {
		TwoThreeTree t = new TwoThreeTree();
		t.insert(1);
		t.insert(10);
		t.insert(20);	// first split root
		t.insert(30);
		t.insert(40);	// second split (parent with 2 children) from the right side
		t.insert(50);
		t.insert(60);	// third split (parent with 3 children) from the right side then Four Node root split
		
		//				  30
		//				/    \
		//			  10     50
		//			 / \    /  \
		//		    1  20  40  60

		assertEquals("1", t.search(1));
		assertEquals("10", t.search(10));
		assertEquals("20", t.search(20));
		assertEquals("30", t.search(30));
		assertEquals("40", t.search(40));
		assertEquals("50", t.search(50));
		assertEquals("60", t.search(60));
	}
	
	@Test
	public void testMiddleSplitFourNodeRoot() {
		TwoThreeTree t = new TwoThreeTree();
		t.insert(1);
		t.insert(10);
		t.insert(20);	// first split root
		t.insert(11);
		t.insert(15);	// second split (parent with 2 children) from the right side
		t.insert(12);
		t.insert(13);	// third split (parent with 3 children from the middle then Four Node root split

		
		//				  12
		//				/    \
		//			  10     15
		//			 / \    /  \
		//		    1  11  13  20

		assertEquals("1", t.search(1));
		assertEquals("10", t.search(10));
		assertEquals("11", t.search(11));
		assertEquals("12", t.search(12));
		assertEquals("13", t.search(13));
		assertEquals("15", t.search(15));
		assertEquals("20", t.search(20));
	}

	@Test
	public void testBigSplitRightTree() {
		TwoThreeTree t = new TwoThreeTree();
		t.insert(1);
		t.insert(10);
		t.insert(20);	// first split root
		t.insert(30);
		t.insert(40);	// second split (parent 2 children) from right side
		t.insert(50);
		t.insert(60);	// third split (parent 3 children) from right side then Four Node root split
		t.insert(70);
		t.insert(80);	// fourth split (parent 2 children) from right side
		t.insert(90);
		t.insert(100);	// fifth split (parent 3 children) from right side then Four Node INSIDE split from right side
		
		//				     30,70
		//				/      |        \
		//			  10      50        90
		//			 / \     /  \      /  \
		//		    1   20  40  60   80   100
		
		assertEquals("1", t.search(1));
		assertEquals("10", t.search(10));
		assertEquals("20", t.search(20));
		assertEquals("30 70", t.search(30));
		assertEquals("40", t.search(40));
		assertEquals("50", t.search(50));
		assertEquals("60", t.search(60));
		assertEquals("30 70", t.search(70));
		assertEquals("80", t.search(80));
		assertEquals("90", t.search(90));
		assertEquals("100", t.search(100));
	}
	
	@Test
	public void testBigSplitLeftTree() {
		TwoThreeTree t = new TwoThreeTree();
		t.insert(100);
		t.insert(90);
		t.insert(80);	// first split root
		t.insert(70);
		t.insert(60);	// second split (parent 2 children) from left side
		t.insert(50);
		t.insert(40);	// third split (parent 3 children) from left side then Four Node root split
		t.insert(30);
		t.insert(20);	// fourth split (parent 2 children) from left side
		t.insert(10);
		t.insert(1);	// fifth split (parent 3 children) from right side then Four Node INSIDE split from left side
	
		
		//				     30,70
		//				/      |        \
		//			  10      50        90
		//			 / \     /  \      /  \
		//		    1   20  40  60   80   100
		
		assertEquals("1", t.search(1));
		assertEquals("10", t.search(10));
		assertEquals("20", t.search(20));
		assertEquals("30 70", t.search(30));
		assertEquals("40", t.search(40));
		assertEquals("50", t.search(50));
		assertEquals("60", t.search(60));
		assertEquals("30 70", t.search(70));
		assertEquals("80", t.search(80));
		assertEquals("90", t.search(90));
		assertEquals("100", t.search(100));
	}
	
	@Test
	public void testReallyBigTree() {
		TwoThreeTree t = new TwoThreeTree();
		t.insert(100);
		t.insert(90);
		t.insert(80);	// first split root
		t.insert(70);
		t.insert(60);	// second split (parent 2 children) from left side
		t.insert(50);
		t.insert(40);	// third split (parent 3 children) from left side then Four Node root split
		t.insert(30);
		t.insert(20);	// fourth split (parent 2 children) from left side
		t.insert(10);
		t.insert(1);	// fifth split (parent 3 children) from right side then Four Node INSIDE split from left side
		t.insert(45);
		t.insert(46);	// sixth split (parent 2 children) from the middle
		t.insert(41);
		t.insert(42);	// seventh split (parent 3 children) from the middle then Four Node INSIDE split from middle then Four Node INSIDE split from middle
		
		//                   45
		//             /             \
		//			  30	          70
		//		   /      \        /      \
		//		 10       41      50       90
		//		/  \	 /  \    /  \     /  \
		//	   1   20   40  42  46  60   80   100  
		
		assertEquals("100", t.search(100));
		assertEquals("90", t.search(90));
		assertEquals("80", t.search(80));
		assertEquals("70", t.search(70));
		assertEquals("60", t.search(60));
		assertEquals("50", t.search(50));
		assertEquals("40", t.search(40));
		assertEquals("30", t.search(30));
		assertEquals("20", t.search(20));
		assertEquals("10", t.search(10));
		assertEquals("1", t.search(1));
		assertEquals("45", t.search(45));
		assertEquals("46", t.search(46));
		assertEquals("41", t.search(41));
		assertEquals("42", t.search(42));
	}
		
	@Test
	public void testAnySizeTreeDescreasing() {
		TwoThreeTree t = new TwoThreeTree();
		double numberOfNodes = Math.pow(2.0, heightOfTree);
		
		for (int i = (int)numberOfNodes; i > 1; i--) {
			t.insert(i);
		}
		
		for (int i = 2; i < (int) numberOfNodes; i++) {
			assertEquals(""+i, t.search(i));
		}
	}
	
	@Test
	public void testAnySizeTreeIncreasing() {
		TwoThreeTree t = new TwoThreeTree();
		double numberOfNodes = Math.pow(2.0, heightOfTree);
		
		for (int i = 1; i < (int)numberOfNodes; i++) {
			t.insert(i);
		}
				
		for (int i = 1; i < (int) numberOfNodes; i++) {
			assertEquals(""+i, t.search(i));
		}
	}
	
	@Test
	public void testAnySizeTreeRandom() {
		TwoThreeTree t = new TwoThreeTree();
		int numberOfNodes = (int)( Math.pow(2.0, heightOfTree));
		
		ArrayList<Integer> tempArrayList = new ArrayList<>(numberOfNodes);
		for (int i = 0; i < numberOfNodes; i++) {
			tempArrayList.add((int) Math.random() * Integer.MAX_VALUE / 2);
		}
		
		for (int i = 1; i < numberOfNodes; i++) {
			t.insert(i);
		}
				
		for (int i = 1; i < numberOfNodes; i++) {
			assertEquals(""+i, t.search(i));
		}
	}
		
	// Supplied tests
	@Test
	public void singleNodeTree() {
		TwoThreeTree t = new TwoThreeTree();
		int val = 9;
		t.insert(val);
		String expected = "9";
		
		assertEquals(expected, t.search(val));
		val = 8;
		assertEquals(expected, t.search(val));
		val = 10;
		assertEquals(expected, t.search(val));

		val = 15;
		t.insert(val);
		expected = "9 15";
		val = 9;
		assertEquals(expected, t.search(val));
		val = 8;
		assertEquals(expected, t.search(val));
		val = 10;
		assertEquals(expected, t.search(val));
		val = 15;
		assertEquals(expected, t.search(val));
		val = 18;
		assertEquals(expected, t.search(val));

		t = new TwoThreeTree();
		val = 15;
		t.insert(val);
		val = 9;
		t.insert(val);
		val = 9;
		assertEquals(expected, t.search(val));
		val = 8;
		assertEquals(expected, t.search(val));
		val = 10;
		assertEquals(expected, t.search(val));
		val = 15;
		assertEquals(expected, t.search(val));
		val = 18;
		assertEquals(expected, t.search(val));
	}

	@Test
	public void oneSplitLeft() {
		TwoThreeTree t = new TwoThreeTree();
		t.insert(9);
		t.insert(15);
		t.insert(1);

		String expected = "9";
		assertEquals(expected, t.search(9));
		expected = "15";
		assertEquals(expected, t.search(15));
		assertEquals(expected, t.search(17));
		assertEquals(expected, t.search(11));

		expected = "1";
		assertEquals(expected, t.search(1));
		assertEquals(expected, t.search(0));
		assertEquals(expected, t.search(3));
	}

	@Test
	public void oneSplitRight() {
		TwoThreeTree t = new TwoThreeTree();
		t.insert(1);
		t.insert(9);
		t.insert(15);

		String expected = "9";
		assertEquals(expected, t.search(9));
		expected = "15";
		assertEquals(expected, t.search(15));
		assertEquals(expected, t.search(17));
		assertEquals(expected, t.search(11));

		expected = "1";
		assertEquals(expected, t.search(1));
		assertEquals(expected, t.search(0));
		assertEquals(expected, t.search(3));
	}

	@Test
	public void oneSplitMiddle() {
		TwoThreeTree t = new TwoThreeTree();
		t.insert(1);
		t.insert(15);
		t.insert(9);

		String expected = "9";
		assertEquals(expected, t.search(9));
		expected = "15";
		assertEquals(expected, t.search(15));
		assertEquals(expected, t.search(17));
		assertEquals(expected, t.search(11));

		expected = "1";
		assertEquals(expected, t.search(1));
		assertEquals(expected, t.search(0));
		assertEquals(expected, t.search(3));
	}

	@Test
	public void testDuplicates() {
		TwoThreeTree t = new TwoThreeTree();
		t.insert(1);
		t.insert(9);
		t.insert(15);
		t.insert(13);
		t.insert(20);
		t.insert(7);
		t.insert(4);
		t.insert(1);
		t.insert(9);
		t.insert(15);
		t.insert(1);
		t.insert(9);
		t.insert(15);
		t.insert(13);
		t.insert(20);
		t.insert(7);
		t.insert(4);
		t.insert(13);
		t.insert(20);
		t.insert(7);
		t.insert(4);

		String expected = "9";
		assertEquals(expected, t.search(9));
		expected = "4";
		assertEquals(expected, t.search(4));
		expected = "15";
		assertEquals(expected, t.search(15));

		expected = "13";
		assertEquals(expected, t.search(12));
		assertEquals(expected, t.search(13));
		assertEquals(expected, t.search(14));
		expected = "20";
		assertEquals(expected, t.search(19));
		assertEquals(expected, t.search(20));
		assertEquals(expected, t.search(21));

		expected = "1";
		assertEquals(expected, t.search(1));
		assertEquals(expected, t.search(0));
		assertEquals(expected, t.search(3));

		expected = "7";
		assertEquals(expected, t.search(6));
		assertEquals(expected, t.search(7));
		assertEquals(expected, t.search(8));

	}	
	
}

