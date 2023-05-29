package nz.ac.auckland.se281.datastructures;

public class Queue<T> {
  private DoubleNode<T> last;
  private DoubleNode<T> first;

  public Queue() {
    first = null;
    last = null;
  }

  /**
   * places a value of the back of the que.
   *
   * @param data the value to be put into the back of the que.
   */
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

  /**
   * removes the first in line in que and returns it.
   *
   * @return the first in line in the que.
   */
  public T deQueue() {
    // check if empty

    T data = first.getValue();

    // shift the queue
    first = first.getPrevious();
    return data;
  }

  /**
   * finds the top of the que.
   *
   * @return the first value in the que.
   */
  public T peek() {

    return first.getValue();
  }

  /**
   * finds the size of the que.
   *
   * @return integer indicating the size of the que.
   */
  public int size() {
    DoubleNode<T> node = first;

    // iterate through to check when the node is null
    int count = 0;

    // increment count
    while (node != null) {
      node = node.getPrevious();
      count++;
    }
    return count;
  }

  /**
   * determines whether the que is empty.
   *
   * @return boolean indicating if the que is empty.
   */
  public boolean isEmpty() {
    return first == null;
  }
}
