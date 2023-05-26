package nz.ac.auckland.se281.datastructures;

public class DoubleNode<T> {
  private DoubleNode<T> previous;
  private DoubleNode<T> next;
  private T value;

  public DoubleNode(T data) {
    this.next = null;
    this.previous = null;
  }

  public void setNext(DoubleNode<T> node) {
    this.next = node;
  }

  public T getValue() {
    return value;
  }

  public void setValue(T data) {
    value = data;
  }

  public DoubleNode<T> getNext() {
    return next;
  }

  public DoubleNode<T> getPrevious() {
    return previous;
  }

  public void setPrevious(DoubleNode<T> node) {
    previous = node;
  }
}
