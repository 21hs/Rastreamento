class BST extends BinaryTree {

    boolean search(int key) {
        return searchRec(root, key);
    }

    boolean searchRec(Node root, int key) {
        if (root == null) {
            return false;
        }
        if (key == root.data) {
            return true;
        }
        if (key < root.data) {
            return searchRec(root.left, key);
        } else {
            return searchRec(root.right, key);
        }
    }
}
