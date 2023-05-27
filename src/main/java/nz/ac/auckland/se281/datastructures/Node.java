package nz.ac.auckland.se281.datastructures;

public class Node<T> {
  protected T value;
  protected Node<T> next;

  public Node(T value) {
    this.value = value;
    next = null;
  }

  public void setNext(Node<T> node) {
    this.next = node;
  }

  public T getValue() {
    return value;
  }

  public Node<T> getNext() {
    return next;
  }
}
