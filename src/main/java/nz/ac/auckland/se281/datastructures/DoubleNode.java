package nz.ac.auckland.se281.datastructures;

/**
 * A double node is a data structure. it has information about its value. as well as the next and
 * previous nodes.
 */
public class DoubleNode<T> {
  private DoubleNode<T> previous;
  private DoubleNode<T> next;
  private T value;

  /**
   * a doublenode consists of a value and 2 pointers.
   *
   * @param data the value of the node.
   */
  public DoubleNode(T data) {
    this.next = null;
    this.previous = null;
    this.value = data;
  }

  /**
   * sets the next value in the node.
   *
   * @param node the node to be set as next.
   */
  public void setNext(DoubleNode<T> node) {
    this.next = node;
  }

  /**
   * getter which returns the value of this node.
   *
   * @return the value of this node.
   */
  public T getValue() {
    return value;
  }

  /**
   * getter which returns the next node.
   *
   * @return the next node.
   */
  public DoubleNode<T> getNext() {
    return next;
  }

  /**
   * getter which returns the previous node.
   *
   * @return the previous node.
   */
  public DoubleNode<T> getPrevious() {
    return previous;
  }

  /**
   * setter which sets the previous node.
   *
   * @param node node to be set as pervious.
   */
  public void setPrevious(DoubleNode<T> node) {
    previous = node;
  }
}
