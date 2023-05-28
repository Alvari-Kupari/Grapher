package nz.ac.auckland.se281.datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A graph that is composed of a set of verticies and edges.
 *
 * <p>You must NOT change the signature of the existing methods or constructor of this class.
 *
 * @param <T> The type of each vertex, that have a total ordering.
 */
public class Graph<T extends Comparable<T>> {
  private ArrayList<Vertex<T>> verticies;
  private Set<Edge<T>> edges;
  private ArrayList<T> temp;

  public Graph(Set<T> verticies, Set<Edge<T>> edges) {
    temp = new ArrayList<>();
    for (T v : verticies) {
      temp.add(v);
    }
    Collections.sort(temp);

    this.verticies = new ArrayList<Vertex<T>>();
    this.edges = edges;

    // add each vertex to the arraylist
    for (T val : temp) {
      this.verticies.add(new Vertex<T>(val, edges));
    }
    // now update all the verticies connections
    for (Vertex<T> v : this.verticies) {
      v.update(this.verticies, edges);
    }
  }

  public Set<T> getRoots() {
    Set<T> set = new HashSet<T>();
    for (Vertex<T> v : verticies) {
      if (v.getInDegree() == 0) set.add(v.getValue());
    }

    if (isEquivalence()) {
      for (Vertex<T> v : verticies) {
        set.add(Collections.min(v.getEquivalenceSet()));
      }
    }
    return set;
  }

  public boolean isReflexive() {
    for (Vertex<T> vertex : verticies) {
      // iterate through every vertex and make sure it has a self loop
      if (!vertex.hasSelfLoop()) return false;
    }

    return true;
  }

  public boolean isSymmetric() {
    // iterate over all possible pairs
    for (Vertex<T> v : verticies) {
      for (Vertex<T> v1 : v.successors()) {
        // check if symmetry holds
        if (!v1.successors().contains(v)) return false;
      }
    }

    return true;
  }

  public boolean isTransitive() {
    // iterate through all possibly transitive edges
    for (Vertex<T> v : verticies) {
      for (Vertex<T> v1 : v.successors()) {
        for (Vertex<T> v2 : v1.successors()) {
          // if the transitive edge is not located return false
          if (!v.successors().contains(v2)) return false;
        }
      }
    }

    return true;
  }

  public boolean isAntiSymmetric() {
    // iterate through all possible antisymmetric edges
    for (Vertex<T> v : verticies) {
      for (Vertex<T> v1 : v.successors()) {
        if (v1.successors().contains(v) && (v != v1)) return false;
      }
    }
    return true;
  }

  public boolean isEquivalence() {

    if (isSymmetric() && isReflexive() && isTransitive()) {
      return true;
    }
    return false;
  }

  public Set<T> getEquivalenceClass(T vertex) {
    Set<T> set = new HashSet<T>();

    // first check if its an equivalence relation
    if (!isEquivalence()) return set;

    for (Edge<T> edge : edges) {
      if (edge.getSource().equals(vertex)) {
        set.add(edge.getDestination());
      }
    }
    return set;
  }

  public List<T> iterativeBreadthFirstSearch() {
    // TODO: Task 2.
    throw new UnsupportedOperationException();
  }

  public List<T> iterativeDepthFirstSearch() {
    // TODO: Task 2.
    throw new UnsupportedOperationException();
  }

  public List<T> recursiveBreadthFirstSearch() {
    // TODO: Task 3.
    throw new UnsupportedOperationException();
  }

  public List<T> recursiveDepthFirstSearch() {
    // TODO: Task 3.
    throw new UnsupportedOperationException();
  }
}
