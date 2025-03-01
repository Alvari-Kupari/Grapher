package nz.ac.auckland.se281.datastructures;

/**
 * An edge in a graph that connects two verticies.
 *
 * <p>You must NOT change the signature of the constructor of this class.
 *
 * @param <T> The type of each vertex.
 */
public class Edge<T> {
  private T source;
  private T destination;

  /**
   * an edge consists of a source and a destination vertex.
   *
   * @param source the beginning of the arrow.
   * @param destination the end of the edge.
   */
  public Edge(T source, T destination) {
    this.source = source;
    this.destination = destination;
  }

  /**
   * getter which returns the source of hte vertex.
   *
   * @return the source vertex of the edge.
   */
  public T getSource() {
    return source;
  }

  /**
   * returns the destination vertex of the edge.
   *
   * @return the destination vertex of the edge.
   */
  public T getDestination() {
    return destination;
  }
}
