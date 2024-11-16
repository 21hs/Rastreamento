class BinaryTree {
    class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    Node root;

    BinaryTree() {
        root = null;
    }

    // Método de inserção
    void insert(int data) {
        root = insertRec(root, data);
    }

    Node insertRec(Node root, int data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }
        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }
        return root;
    }

    // Método para travessia in-ordem
    void inorder() {
        inorderRec(root);
    }

    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.data + " ");
            inorderRec(root.right);
        }
    }

    // Função para calcular a altura da árvore
    int height() {
        return heightRec(root);
    }

    int heightRec(Node root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = heightRec(root.left);
        int rightHeight = heightRec(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Função para contar nós folha
    int countLeaves() {
        return countLeavesRec(root);
    }

    int countLeavesRec(Node root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return countLeavesRec(root.left) + countLeavesRec(root.right);
    }

    // Método para verificar se a árvore é balanceada
    boolean isBalanced() {
        return isBalancedRec(root) != -1;
    }

    int isBalancedRec(Node root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = isBalancedRec(root.left);
        int rightHeight = isBalancedRec(root.right);

        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1; // Não balanceada
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Método para verificar se a árvore é uma árvore de busca binária
    boolean isBST() {
        return isBSTRec(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    boolean isBSTRec(Node node, int min, int max) {
        if (node == null) {
            return true;
        }
        if (node.data < min || node.data > max) {
            return false;
        }
        return isBSTRec(node.left, min, node.data - 1) && isBSTRec(node.right, node.data + 1, max);
    }

    // Método para deletar um nó
    void delete(int data) {
        root = deleteRec(root, data);
    }

    Node deleteRec(Node root, int data) {
        if (root == null) {
            return root;
        }

        if (data < root.data) {
            root.left = deleteRec(root.left, data);
        } else if (data > root.data) {
            root.right = deleteRec(root.right, data);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.data = minValue(root.right);

            root.right = deleteRec(root.right, root.data);
        }
        return root;
    }

    int minValue(Node root) {
        int minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }
}

// Classe para a árvore de busca binária
class BST extends BinaryTree {

    // Método de busca
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

// Classe para reconstrução da árvore a partir das sequências in-ordem e pré-ordem
class TreeReconstruction {
    static int preIndex = 0;

    static BinaryTree.Node buildTree(int[] inorder, int[] preorder, int inorderStart, int inorderEnd) {
        if (inorderStart > inorderEnd) {
            return null;
        }

        BinaryTree.Node node = new BinaryTree.Node(preorder[preIndex++]);

        if (inorderStart == inorderEnd) {
            return node;
        }

        int inorderIndex = search(inorder, inorderStart, inorderEnd, node.data);
        node.left = buildTree(inorder, preorder, inorderStart, inorderIndex - 1);
        node.right = buildTree(inorder, preorder, inorderIndex + 1, inorderEnd);
        
        return node;
    }

    static int search(int[] inorder, int start, int end, int value) {
        for (int i = start; i <= end; i++) {
            if (inorder[i] == value) {
                return i;
            }
        }
        return -1;
    }
}

// Classe para a árvore AVL
class AVLTree {
    class Node {
        int data, height;
        Node left, right;

        Node(int item) {
            data = item;
            height = 1;
            left = right = null;
        }
    }

    Node root;

    int height(Node node) {
        if (node == null) return 0;
        return node.height;
    }

    int getBalance(Node node) {
        if (node == null) return 0;
        return height(node.left) - height(node.right);
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    Node insert(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }

        if (data < node.data) {
            node.left = insert(node.left, data);
        } else if (data > node.data) {
            node.right = insert(node.right, data);
        } else {
            return node; // Não permite duplicados
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && data < node.left.data) {
            return rightRotate(node);
        }

        if (balance < -1 && data > node.right.data) {
            return leftRotate(node);
        }

        if (balance > 1 && data > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && data < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    void insert(int data) {
        root = insert(root, data);
    }
}
