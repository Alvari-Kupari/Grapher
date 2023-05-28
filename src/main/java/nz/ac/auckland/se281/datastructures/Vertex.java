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
      if (val == edge.getDestination() && val == edge.getSource()) hasSelfLoop = true;

      // count the in degree and out degree
      if (val == edge.getDestination()) inDegree++;
      if (val == edge.getSource()) outDegree++;
    }
  }

  public boolean pointsTo(Vertex<T> v, Set<Edge<T>> edges) {
    for (Edge<T> edge : edges) {
      // check if the vertex points to v
      if (val == edge.getSource() && v.getValue() == edge.getDestination()) return true;
    }
    return false;
  }

  public Set<Vertex<T>> successors() {
    return successors;
  }

  public T getValue() {
    return val;
  }

  public int getInDegree() {
    return inDegree;
  }

  public int getOutDegree() {
    return outDegree;
  }

  public boolean hasSelfLoop() {
    return hasSelfLoop;
  }

  public void update(ArrayList<Vertex<T>> verticies, Set<Edge<T>> edges) {
    for (Vertex<T> v : verticies) {
      if (this.pointsTo(v, edges)) {
        successors.add(v);
      }
    }
  }

  public Set<T> getEquivalenceSet() {
    Set<T> set = new HashSet<T>();
    for (Vertex<T> v : successors) {
      set.add(v.getValue());
    }
    return set;
  }
}
