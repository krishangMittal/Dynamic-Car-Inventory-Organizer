// --== CS400 Fall 2023 File Header Information ==--
// Name: Krishang Mittal
// Email: kmmittal@wisc.edu
// Group: C32
// TA: Anvay Grover
// Lecturer: Florian
// Notes to Grader: <optional extra notes>


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    protected static class RBTNode<T> extends Node<T> {
        public int blackHeight = 0;
        public RBTNode(T data) {
            super(data);
        }

        public RBTNode<T> getUp() {
            return (RBTNode<T>)this.up;
        }
        public RBTNode<T> getDownLeft() {
            return (RBTNode<T>)this.down[0];
        }
        public RBTNode<T> getDownRight() {
            return (RBTNode<T>)this.down[1];
        }
    }


    /**
     * Enforces the Red-Black Tree properties after inserting a new node into the tree.
     *
     * @param newNode The newly inserted red node that may violate the Red-Black Tree properties.
     */
    public void enforceRBTreePropertiesAfterInsert(RBTNode<T> newNode) {

        enforceRBTreePropertiesAfterInsertRecursive(newNode);
    }

    /**
     * Recursively enforces the Red-Black Tree properties after inserting a new node into the tree.
     * This method is intended for internal use.
     *
     * @param currentNode The current node being checked for Red-Black Tree property violations.
     */
    private void enforceRBTreePropertiesAfterInsertRecursive(RBTNode<T> currentNode) {
        if (currentNode == null || currentNode == root ) {
            // Base case: Stop when we reach the root or a black parent
            if (currentNode != null) {
                currentNode.blackHeight = 1; // Set node to black
            }
            return;
        }

        RBTNode<T> parent = currentNode.getUp();
        if (parent == null) {
            return;
        }

        RBTNode<T> grandparent = parent.getUp();

        // Use the provided getDownLeft and getDownRight methods to access children
        RBTNode<T> uncle = null;
        if (grandparent != null) {
            if (parent == grandparent.getDownLeft()) {
                uncle = grandparent.getDownRight();
            } else {
                uncle = grandparent.getDownLeft();
            }
        }

        // Case 1: Parent's sibling (uncle) is red
        if (grandparent != null) {
            if (uncle != null && uncle.blackHeight != 1) {
                // Case 1: Parent's sibling is red, which violates the RB tree properties
                // Adjust colors and perform a recursive call on the grandparent
                parent.blackHeight = 1;
                uncle.blackHeight = 1;
                grandparent.blackHeight = 0;
                currentNode.blackHeight = 0; // Set node to red
                enforceRBTreePropertiesAfterInsertRecursive(grandparent);
            } else {
                // Cases 2 and 3: Parent's sibling (uncle) is black
                if ((parent.blackHeight == 1 && grandparent.blackHeight == 1 && uncle.blackHeight == 1)) {
                    // Case 2: Parent, grandparent, and uncle are all black
                    // No further adjustments needed, return
                    return;
                }
                if (parent == grandparent.getDownLeft()) {
                    if (currentNode == parent.getDownRight()) {
                        // Case 2: Left-Right (LR) rotation needed
                        rotate(currentNode, parent);
                        rotate(currentNode, grandparent);
                        currentNode.blackHeight = 1;
                        grandparent.blackHeight = 0;
                    } else if (currentNode == parent.getDownLeft()) {
                        // Case 3: Left-Left (LL) rotation needed
                        rotate(parent, grandparent);
                        parent.blackHeight = 1;
                        grandparent.blackHeight = 0;
                        currentNode = currentNode.getUp();
                    }
                } else {
                    if (currentNode == parent.getDownLeft()) {
                        // Case 2: Right-Left (RL) rotation needed
                        rotate(currentNode, parent);
                        rotate(currentNode, grandparent);
                        currentNode.blackHeight = 1;
                        grandparent.blackHeight = 0;
                    } else if (currentNode == parent.getDownRight()) {
                        // Case 3: Right-Right (RR) rotation needed
                        rotate(parent, grandparent);
                        parent.blackHeight = 1;
                        grandparent.blackHeight = 0;
                        currentNode = currentNode.getUp();
                    }
                }

                if (currentNode != root) {
                    // Continue enforcement recursively if currentNode is not the root
                    enforceRBTreePropertiesAfterInsertRecursive(currentNode);
                }
            }
        }
    }

    /**
     * Inserts a new node with the specified data into the Red-Black Tree and ensures that
     * Red-Black Tree properties are maintained after insertion.
     *
     * @param data The data value to insert into the tree.
     * @return True if the data was successfully inserted, false otherwise.
     * @throws NullPointerException if the provided data is null.
     */
    @Override
    public boolean insert(T data) throws NullPointerException {
        if (data == null)
            throw new NullPointerException("Cannot insert data value null into the tree.");
        RBTNode<T> newNode = new RBTNode<>(data);
        boolean insertHelper = this.insertHelper(newNode);

        if (insertHelper) {
            enforceRBTreePropertiesAfterInsert(newNode);
        }
        if (newNode == root) {
            newNode.blackHeight = 1; // Set the color to black
        }
        return insertHelper;
    }




    /**
     * Test for recoloring the parent node when both child and parent are red and the aunt is red.
     *
     * @return true if all the test passes.
     */
    @Test
    public void testRecoloring() {
        // Create a Red-Black Tree instance
        RedBlackTree<Integer> rbt = new RedBlackTree<>();

        // Insert nodes to create a situation where both child and parent are red
        rbt.insert(12);
        rbt.insert(6);
        rbt.insert(17);
        rbt.insert(19);

        // Assert the expected tree structure after insertion
        assertEquals("[ 12, 6, 17, 19 ]", rbt.toLevelOrderString());
        assertEquals("[ 6, 12, 17, 19 ]", rbt.toInOrderString());

        // Check if the root node is black
        RBTNode<Integer> root1 = (RBTNode<Integer>) rbt.root;
        if (root1.blackHeight != 1) {
            Assertions.fail("Root node is not black");
        }
    }

    /**
     * Test for left rotation followed by right rotation and recoloring.
     *
     * @return true if all the test passes.
     */
    @Test
    public void testRotation() {
        // Create a Red-Black Tree instance
        RedBlackTree<Integer> rbt = new RedBlackTree<>();

        // Insert nodes to create a situation that requires left and right rotation and recoloring
        rbt.insert(12);
        rbt.insert(6);
        rbt.insert(17);
        rbt.insert(19);
        rbt.insert(2);
        rbt.insert(7);
        rbt.insert(18);

        // Assert the expected tree structure after insertion
        assertEquals("[ 12, 6, 18, 2, 7, 17, 19 ]", rbt.toLevelOrderString());
        assertEquals("[ 2, 6, 7, 12, 17, 18, 19 ]", rbt.toInOrderString());

        // Check if the root node is black
        RBTNode<Integer> root1 = (RBTNode<Integer>) rbt.root;
        if (root1.blackHeight != 1) {
            Assertions.fail("Root node is not black");
        }
    }

    /**
     * Test for cascading fix where the root node is changed.
     *
     * @return true if all the test passes.
     */
    @Test
    public void testCascadingFix() {
        // Create a Red-Black Tree instance
        RedBlackTree<Integer> rbt = new RedBlackTree<>();

        // Insert nodes to create a situation that requires a cascading fix with root change
        rbt.insert(12);
        rbt.insert(6);
        rbt.insert(17);
        rbt.insert(19);
        rbt.insert(2);
        rbt.insert(7);
        rbt.insert(18);
        rbt.insert(22);
        rbt.insert(25);
        rbt.insert(23);

        // Assert the expected tree structure after insertion
        assertEquals("[ 18, 12, 22, 6, 17, 19, 25, 2, 7, 23 ]", rbt.toLevelOrderString());
        assertEquals("[ 2, 6, 7, 12, 17, 18, 19, 22, 23, 25 ]", rbt.toInOrderString());

        // Check if the root node is black
        RBTNode<Integer> root1 = (RBTNode<Integer>) rbt.root;
        if (root1.blackHeight != 1) {
            Assertions.fail("Root node is not black");
        }

    }


}





