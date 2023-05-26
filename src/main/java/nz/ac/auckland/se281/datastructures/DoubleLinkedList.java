package nz.ac.auckland.se281.datastructures;

public class DoubleLinkedList<T> {
  private DoubleNode<T> tail;
  private DoubleNode<T> head;

  public DoubleLinkedList() {
    head = null;
    tail = null;
  }

  public void prepend(T data) {
    DoubleNode<T> node = new DoubleNode<T>(data);

    node.setNext(head);
    head.setPrevious(node);
    head = node;
  }

  public void append(T data) {
    DoubleNode<T> node = new DoubleNode<>(data);

    node.setPrevious(tail);
    tail.setNext(node);
    tail = node;
  }

  public DoubleNode<T> tail() {
    return tail;
  }

  public DoubleNode<T> head() {
    return head;
  }

  public T fetch(int pos) {
    DoubleNode<T> search = head;

    for (int i = 1; i < pos; i++) {
      search = search.getNext();
    }
    return search.getValue();
  }

  public void insert(int pos, T data) {
    DoubleNode<T> node = new DoubleNode<T>(data);
    DoubleNode<T> search = head;

    for (int i = 1; i < pos - 1; i++) {
      search = search.getNext();
    }
    node.setNext(search.getNext());
    search.setNext(node);
  }

  public void remove(int pos) {
    DoubleNode<T> search = head;

    for (int i = 1; i < pos - 1; i++) {
      search = search.getNext();
    }
    search.setNext(search.getNext().getNext());
    search.getNext().setPrevious(search);
  }

  public int size() {
    int size = 0;
    DoubleNode<T> search = head;
    while (search != null) {
      size++;
      search = search.getNext();
    }
    return size;
  }
}
