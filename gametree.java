// public class GameTree<T> {
//     // A non generic implementation of the tree.

//     private State initial;
     
//     public GameTree(State initialState) {
//         this.initial = initialState;
//         this.root.children = new ArrayList<Node<T>>();
//     }
    
// }

public class Tree<T> {
    private Node<T> root;   

    public GameTree(T treeRoot) {
        this.root = new Node<T>();
        this.root.data = treeRoot;
        this.root.children = new ArrayList<Node<T>>();
    }

    private class Node<T> {

        T data;
        Boolean pruned;
        int heuristicValue;
        int cost;

        Node<T> parent;
        ArrayList<Node<T>> children;
    }   
}