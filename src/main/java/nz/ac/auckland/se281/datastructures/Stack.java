package nz.ac.auckland.se281.datastructures;

/**
 * A stack is a datastructure which. uses nodes to create a "stack" of objects. Nodes can be
 * inserted and deleted only from the top of the stack.
 */
public class Stack<T> {
  private Node<T> head;

  /** constructor for a stack. initializes the next node to be null. */
  public Stack() {
    head = null;
  }

  /**
   * inserts a data value to the top of the stack.
   *
   * @param data the data to be pushed to the top.
   */
  public void push(T data) {
    Node<T> node = new Node<T>(data);

    // set the new value to be the head
    node.setNext(head);

    head = node;
  }

  /**
   * returns the value at the top of the stack.
   *
   * @return the top of the stack.
   */
  public T peek() {

    return head.getValue();
  }

  /**
   * removes the top of the stack and returns it.
   *
   * @return the top of the stack.
   */
  public T pop() {
    // check if empty

    // obtain the data
    T data = peek();

    // remove the current head and replace with the next element
    head = head.getNext();
    return data;
  }

  /**
   * finds the value of the stack.
   *
   * @return the size of the stack.
   */
  public int size() {
    int size = 0;
    Node<T> search = head;

    // increment size
    while (search != null) {
      size++;
      search = search.getNext();
    }
    return size;
  }

  /**
   * determines whether the stack is empty.
   *
   * @return boolean indicating if the stack is empty.
   */
  public boolean isEmpty() {
    return head == null;
  }
}
