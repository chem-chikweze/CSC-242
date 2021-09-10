
import java.util.ArrayList;

public class GameTree {
    // A non generic implementation of the tree.

    State root;
    ArrayList<State> children;

    public GameTree(State initialState) {
        this.root = initialState;
        this.children = new ArrayList<State>();
    }

    public State getRoot() {
        return root;
    }
}

// public class GameTree<T> {
//     private Node<T> root;   

//     public GameTree(T treeRoot) {
//         this.root = new Node<T>();
//         this.root.data = treeRoot;
//         this.root.children = new ArrayList<Node<T>>();
//     }   

//     public Node<T> getRoot() {
//         return root;
//     }
// }