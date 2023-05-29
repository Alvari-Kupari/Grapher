package nz.ac.auckland.se281.datastructures;

public class Node<T> {
  protected T value;
  protected Node<T> next;

  /**
   * constructor for a node, creates a node.
   *
   * @param value the value for the node.
   */
  public Node(T value) {
    // set the value
    this.value = value;
    next = null;
  }

  /**
   * a setter function which sets the next node.
   *
   * @param node the node to be set as next in sequence.
   */
  public void setNext(Node<T> node) {
    this.next = node;
  }

  /**
   * a getter function which returns the vlaue of the node.
   *
   * @return the value of the node.
   */
  public T getValue() {
    return value;
  }

  /**
   * a getter which returns the next node.
   *
   * @return the next node.
   */
  public Node<T> getNext() {
    return next;
  }
}
