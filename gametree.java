public class GameTree<T> {
    private Node<T> root;

     
    public GameTree(T treeRoot) {
        this.root = new Node<T>();
        this.root.data = treeRoot;
        this.root.children = new ArrayList<Node<T>>();
    }

    private class Node<T> {
        T data;
        Node<T> parent;
        ArrayList<Node<T>> children;
    }
}