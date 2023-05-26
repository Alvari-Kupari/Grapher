package nz.ac.auckland.se281.datastructures;

public class LinkedList<T> {
  private Node<T> head;

  public LinkedList() {
    head = null;
  }

  public void prepend(T data) {
    Node<T> node = new Node<T>(data);
    node.setNext(head);

    head = node;
  }

  public void append(T data) {

    Node<T> search = head;

    // search through the list until you reach the final element
    while (search.getNext() != null) {
      search = search.getNext();
    }
    // link the new node with the last data point
    search.setNext(new Node<T>(data));
  }

  public T fetch(int pos) {
    Node<T> search = head;

    for (int i = 1; i < pos; i++) {
      search = search.getNext();
    }
    return search.getValue();
  }

  public void insert(int pos, T data) {
    Node<T> node = new Node<T>(data);
    Node<T> search = head;

    for (int i = 1; i < pos - 1; i++) {
      search = search.getNext();
    }
    node.setNext(search.getNext());
    search.setNext(node);
  }

  public void remove(int pos) {
    Node<T> search = head;

    for (int i = 1; i < pos - 1; i++) {
      search = search.getNext();
    }
    search.setNext(search.getNext().getNext());
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

  public Node<T> head() {
    return head;
  }
}
