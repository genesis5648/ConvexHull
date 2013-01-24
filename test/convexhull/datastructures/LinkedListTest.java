package convexhull.datastructures;

import convexhull.algorithms.Helper;
import convexhull.comparators.AngleComparator;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Heikki Haapala
 */
public class LinkedListTest {

    public LinkedListTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of insert method, of class LinkedList.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        LinkedList instance = new LinkedList();
        // inserting null point
        instance.insert(null);
        // check if it got in the list
        assertNull(instance.getHead().getPoint());
        // insert a test point
        Point2D.Double testPoint = new Point2D.Double(3, 2);
        instance.insert(testPoint);
        // list should now contain the test point
        assertEquals(testPoint, instance.getHead().getNext().getPoint());
    }

    /**
     * Test of getHead method, of class LinkedList.
     */
    @Test
    public void testGetHead() {
        System.out.println("getHead");
        LinkedList instance = new LinkedList();
        // empty list head should be null
        assertNull(instance.getHead());
        // add test point
        Point2D.Double testPoint = new Point2D.Double(1, 2);
        instance.insert(testPoint);
        // head should now contain the test point
        assertEquals(testPoint, instance.getHead().getPoint());
    }

    /**
     * Test of getLength method, of class LinkedList.
     */
    @Test
    public void testGetLength() {
        System.out.println("getLength");
        LinkedList points = new LinkedList();
        // empty list length 0
        assertEquals(0, points.getLength());
        try {
            points = Helper.parseFile("testfile");
        } catch (Exception ex) {
            fail("Could not parse input file.");
        }
        // test file has 100 points
        assertEquals(100, points.getLength());
    }

    /**
     * Test of sort method, of class LinkedList.
     */
    @Test
    public void testSort() {
        System.out.println("sort");
        LinkedList points1 = null;
        try {
            points1 = Helper.parseFile("testfile");
        } catch (Exception ex) {
            fail("Could not parse input file.");
        }
        // create comparator
        AngleComparator comparator = new AngleComparator(new Point2D.Double(0, 0));
        // use the merge sort
        points1.sort(comparator);

        // make also an arraylist of the same points
        ArrayList<Point2D.Double> points2 = new ArrayList<Point2D.Double>();
        LinkedListNode node = points1.getHead();
        while (node != null) {
            points2.add(node.getPoint());
            node = node.getNext();
        }

        // sort the arraylist using the same comparator
        Collections.sort(points2, comparator);

        // compare the results
        if (!Helper.sameOrder(points1, points2)) {
            fail("Sorting returned the list in wrong order.");
        }
    }
}
