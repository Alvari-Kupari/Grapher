package nz.ac.auckland.se281.datastructures;

public class Stack<T> {
  private Node<T> head;

  public Stack() {
    head = null;
  }

  public void push(T data) {
    Node<T> node = new Node<T>(data);
    node.setNext(head);

    head = node;
  }

  public T peek() {

    return head.getValue();
  }

  public T pop() {
    // check if empty

    // obtain the data
    T data = peek();

    // remove the current head and replace with the next element
    head = head.getNext();
    return data;
  }

  public int size() {
    int size = 0;
    Node<T> search = head;
    while (search != null) {
      size++;
      search = search.getNext();
    }
    return size;
  }

  public boolean isEmpty() {
    return head == null;
  }
}
