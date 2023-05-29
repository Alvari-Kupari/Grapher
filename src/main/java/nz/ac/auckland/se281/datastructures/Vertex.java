package nz.ac.auckland.se281.datastructures;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Vertex<T> {
  private T val;
  private int inDegree;
  private int outDegree;
  private boolean hasSelfLoop;
  private Set<Vertex<T>> successors;

  public Vertex(T value, Set<Edge<T>> edges) {
    this.val = value;
    inDegree = 0;
    outDegree = 0;
    successors = new HashSet<Vertex<T>>();

    for (Edge<T> edge : edges) {
      // check for self loop
      if (val == edge.getDestination() && val == edge.getSource()) {
        hasSelfLoop = true;
      }

      // count the in degree and out degree
      if (val == edge.getDestination()) {
        inDegree++;
      }
      if (val == edge.getSource()) {
        outDegree++;
      }
    }
  }

  /**
   * determines whether a vertex points to a given input vertex
   *
   * @param v the edge that this vertex might point to
   * @param edges the set of edges in teh graph
   * @return a boolean indicating whether this vertex points to v
   */
  public boolean pointsTo(Vertex<T> v, Set<Edge<T>> edges) {
    for (Edge<T> edge : edges) {
      // check if the vertex points to v
      if (val == edge.getSource() && v.getValue() == edge.getDestination()) {
        return true;
      }
    }
    return false;
  }

  /**
   * returns a set of vertices which this vertex points to
   *
   * @return a set of vertices that are pointed to by this
   */
  public Set<Vertex<T>> getSuccessors() {
    return successors;
  }

  /**
   * getter which returns the value of this vertex
   *
   * @return the value of this vertex
   */
  public T getValue() {
    return val;
  }

  /**
   * returns the in degree of this vertex IE number of incoming edges
   *
   * @return the in degree of this vertex
   */
  public int getInDegree() {
    return inDegree;
  }

  /**
   * returns an integer which is the out degree of this vertex IE number of outgoing edges
   *
   * @return the out degree of this veertex
   */
  public int getOutDegree() {
    return outDegree;
  }

  /**
   * indicates whether this vertex has a self loop
   *
   * @return a boolean indiating if this vertex has a self loop
   */
  public boolean hasSelfLoop() {
    return hasSelfLoop;
  }

  /**
   * this method is used when loading the graph, to determine which other vertices in the graph this
   * vertrx points to
   *
   * @param verticies the set of all other vertices in the graph
   * @param edges the edges of the graph
   */
  public void update(ArrayList<Vertex<T>> verticies, Set<Edge<T>> edges) {
    // iterate through all veritcies and add the successors
    for (Vertex<T> v : verticies) {
      if (this.pointsTo(v, edges)) {
        successors.add(v);
      }
    }
  }

  /**
   * finds the set of all vertices this vertex points to
   *
   * @return a set containing all successors of this vertex including itself
   */
  public Set<T> getEquivalenceSet() {
    Set<T> set = new HashSet<T>();

    // iterate through all successors and add them
    for (Vertex<T> v : successors) {
      set.add(v.getValue());
    }
    return set;
  }
}
