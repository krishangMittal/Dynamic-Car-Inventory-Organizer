// --== CS400 Fall 2023 File Header Information ==--
// Name: Krishang Mittal
// Email: kmmittal@wisc.edu
// Group: C32
// TA: Anvay Grover
// Lecturer: Florian
// Notes to Grader: <optional extra notes>


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class IterableMultiKeyRBT<T extends Comparable<T>> extends BinarySearchTree<KeyListInterface<T>> implements IterableMultiKeySortedCollectionInterface<T> {

    private Comparable<T> iterationStartPoint; // Field to store the iteration start point
    private int numKeys; // Field to store the number of keys


    /**
     * Inserts value into tree that can store multiple objects per key by keeping
     * lists of objects in each node of the tree.
     *
     * @param key object to insert
     * @return true if a new node was inserted, false if the key was added into an existing node
     */
    @Override
    public boolean insertSingleKey(T key) {
        KeyList<T> newKeyList = new KeyList<>(key);
        Node<KeyListInterface<T>> existingNode = findNode(newKeyList);
        if (existingNode != null) {
            existingNode.data.addKey(key);
            numKeys++; // Increment the number of keys
            return false;
        } else {
            Node<KeyListInterface<T>> newNode = new Node<>(newKeyList);
            super.insertHelper(newNode);
            numKeys++;
            return true;

        }
    }

    /**
     * @return the number of values in the tree.
     */
    @Override
    public int numKeys() {
        return numKeys;
    }

    /**
     * Returns an iterator that does an in-order iteration over the tree.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Stack<Node<KeyListInterface<T>>> stack = getStartStack();
            private Iterator<T> keyListIterator = null;

            @Override
            public boolean hasNext() {
                while (keyListIterator == null || !keyListIterator.hasNext()) {
                    if (stack.isEmpty()) {
                        return false;  // No more elements in the stack
                    }
                    Node<KeyListInterface<T>> node = stack.pop();
                    keyListIterator = node.data.iterator();
                    // Push the right subtree onto the stack
                    Node<KeyListInterface<T>> current = node.down[1];
                    while (current != null) {
                        stack.push(current);
                        current = current.down[0];
                    }
                }
                return true;  // More elements to iterate over
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return keyListIterator.next();
            }
        };
    }

    /**
     * returns an instance of java.util.Stack containing nodes after initialization. If no iteration start point is set
     * (the field that stores the start point is set to null), the stack is initialized with the nodes on the path from
     * the root node to (and including) the node with the smallest key in the tree. If the iteration start point is set,
     * then the stack is initialized with all the nodes with keys equal to or larger than the start point along the path
     * of the search for the start point.
     *
     * @return a stack of nodes for in-order iteration based on the iteration start point (if set).
    */

    protected Stack<Node<KeyListInterface<T>>> getStartStack() {
        Stack<Node<KeyListInterface<T>>> startStack = new Stack<>();
        Node<KeyListInterface<T>> current = root;

        if (iterationStartPoint != null) {
            // Initialize the stack based on the iteration start point
            while (current != null) {
                int compare = iterationStartPoint.compareTo(current.data.iterator().next());
                if (compare > 0) {
                    // Start point is greater, so move to the right subtree
                    current = current.down[1];
                } else {
                    // Start point is less or equal, add this node and move to the left subtree
                    startStack.push(current);
                    current = current.down[0];
                }
            }
        } else {
            // If no iteration start point is set, initialize the stack with the leftmost path
            while (current != null) {
                startStack.push(current);
                current = current.down[0];
            }
        }
        return startStack;
    }


    /**
     * Sets the starting point for iterations. Future iterations will start at the
     * starting point or the key closest to it in the tree. This setting is remembered
     * until it is reset. Passing in null disables the starting point.
     *
     * @param startPoint the start point to set for iterations
     */
    @Override
    public void setIterationStartPoint(Comparable<T> startPoint) {
        // Handle the case when startPoint is null (disable the starting point)
        this.iterationStartPoint = startPoint;
    }

    @Override
    public void clear() {
        super.clear();
        numKeys = 0;
    }


    /**
     * Test the insertSingleKey method for adding keys to the IterableMultiKeyRBT and checking the number of keys.
     */
    @Test
    public void testInsertSingleKey() {
        IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();
        tree.insertSingleKey(10);
        tree.insertSingleKey(20);
        tree.insertSingleKey(5);
        tree.insertSingleKey(5);
        assertFalse(tree.insertSingleKey(10)); // Key 1 already exists
        Assertions.assertEquals(3, tree.size());
        Assertions.assertEquals(5, tree.numKeys());
    }

    /**
     * Test the setIterationStartPoint method to ensure that the iterator starts at or after the specified point.
     */
    @Test
    public void testSetIterationStartPoint() {
        IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();
        tree.insertSingleKey(1);
        tree.insertSingleKey(20);
        tree.insertSingleKey(40);

        tree.setIterationStartPoint(20);
        Assertions.assertEquals(20, tree.iterator().next());
        tree.setIterationStartPoint(34);
        Assertions.assertEquals(40, tree.iterator().next());
        tree.setIterationStartPoint(null);
        Assertions.assertEquals(1, tree.iterator().next());
    }

    @Test
    public void testIterator() {
        IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();
        tree.insertSingleKey(10);
        tree.insertSingleKey(20);
        tree.insertSingleKey(30);
        tree.insertSingleKey(10);
        tree.insertSingleKey(20);
        tree.insertSingleKey(30);
        tree.insertSingleKey(5);
        tree.insertSingleKey(5);

        Assertions.assertEquals(4, tree.size());
        Assertions.assertEquals(8, tree.numKeys());

        int count = 0;
        Iterator<Integer> iter = tree.iterator();
        for (Integer key : tree) {
            List<Integer> expected = Arrays.asList(5, 5, 10, 10, 20, 20, 30, 30);
            Assertions.assertEquals(expected.get(count), iter.next());
            count++;
        }
    }

}
