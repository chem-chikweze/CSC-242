import java.util.ArrayList;

class Node<T> {

    T data;
    Boolean pruned;
    int heuristicValue;
    int cost;

    Node<T> parent;
    ArrayList<Node<T>> children;
}