package nz.ac.auckland.se281.datastructures;

public class Queue<T> {
  private DoubleNode<T> last;
  private DoubleNode<T> first;

  public Queue() {
    first = null;
    last = null;
  }

  public void enQueue(T data) {
    DoubleNode<T> node = new DoubleNode<T>(data);

    // check if the tail was null
    if (isEmpty()) {
      first = node;
      last = node;
      return;
    }

    // set the new node tdo be the tail
    node.setNext(last);
    last.setPrevious(node);
    last = node;
  }

  public T deQueue() {
    // check if empty

    T data = first.getValue();

    // shift the queue
    first = first.getPrevious();
    return data;
  }

  public T peek() {

    return first.getValue();
  }

  public int size() {
    DoubleNode<T> node = first;

    // iterate through to check when the node is null
    int count = 0;

    while (node != null) {
      node = node.getPrevious();
      count++;
    }
    return count;
  }

  public boolean isEmpty() {
    return first == null;
  }
}
