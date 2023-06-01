package nz.ac.auckland.se281.datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
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

  /**
   * a constructor for a graph. This creates all vertices and updates them with all relevant edges.
   *
   * @param verticies a set of verticies.
   * @param edges a set of edges.
   */
  public Graph(Set<T> verticies, Set<Edge<T>> edges) {

    this.verticies = new ArrayList<Vertex<T>>();
    this.edges = edges;

    // add each vertex to the arraylist
    for (T val : verticies) {
      this.verticies.add(new Vertex<T>(val, edges));
    }
    // now update all the verticies connections
    for (Vertex<T> v : this.verticies) {
      v.update(this.verticies, edges);
    }
  }

  /**
   * returns a set of verticies which are the roots of the graph.
   *
   * @return set of roots of the graph.
   */
  public Set<T> getRoots() {
    List<Integer> roots = new ArrayList<>();

    for (Vertex<T> v : verticies) {
      // always add the in degree 0 roots
      if (v.getInDegree() == 0 && v.getOutDegree() > 0) {
        roots.add(Integer.parseInt((String) v.getValue()));
      }
    }

    if (isEquivalence()) {
      // if equivalence relation we can add self loops
      for (Vertex<T> v : verticies) {
        List<Integer> temp = new ArrayList<>();

        // convert the equivalence set to integers
        for (T t : v.getEquivalenceSet()) {
          temp.add(Integer.parseInt((String) t));
        }
        // add the minimum
        roots.add(Collections.min(temp));
      }
    }
    // order the roots
    Collections.sort(roots);

    // convert roots to t type
    Set<T> rootSet = new LinkedHashSet<>();
    for (int i : roots) {
      rootSet.add(getVertex(i).getValue());
    }
    return rootSet;
  }

  /**
   * determines whether a graph is reflexive.
   *
   * @return a boolean indicating if the graph is reflexive or not.
   */
  public boolean isReflexive() {
    for (Vertex<T> vertex : verticies) {
      // iterate through every vertex and make sure it has a self loop
      if (!vertex.hasSelfLoop()) {
        return false;
      }
    }

    return true;
  }

  /**
   * determines whether the graph is symmetric.
   *
   * @return boolean indicating whether graph is symmetric.
   */
  public boolean isSymmetric() {
    // iterate over all possible pairs
    for (Vertex<T> v : verticies) {
      for (Vertex<T> v1 : v.getSuccessors()) {
        // check if symmetry holds
        if (!v1.getSuccessors().contains(v)) {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * determines whether the graph is transitive.
   *
   * @return boolean indicating twhether the graph is trnasitive.
   */
  public boolean isTransitive() {
    // iterate through all possibly transitive edges
    for (Vertex<T> v : verticies) {
      for (Vertex<T> v1 : v.getSuccessors()) {
        for (Vertex<T> v2 : v1.getSuccessors()) {
          // if the transitive edge is not located return false
          if (!v.getSuccessors().contains(v2)) {
            return false;
          }
        }
      }
    }

    return true;
  }

  /**
   * determines whether a graph is antisymmetric.
   *
   * @return boolean indicating whether the graph is antisymmetric.
   */
  public boolean isAntiSymmetric() {
    // iterate through all possible antisymmetric edges
    for (Vertex<T> v : verticies) {
      for (Vertex<T> v1 : v.getSuccessors()) {
        if (v1.getSuccessors().contains(v) && (v != v1)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * determines whether the lkoaded graph is an equivalence relation.
   *
   * @return a boolean indicating whether the graph is an equivalence relation.
   */
  public boolean isEquivalence() {

    // return true if all three properties below are satisfied
    if (isSymmetric() && isReflexive() && isTransitive()) {
      return true;
    }
    return false;
  }

  /**
   * calculates and returns the equivalence class of a given vertex.
   *
   * @param vertex a vertex of the graph.
   * @return a the set of vertices which are the equivalence class of the input.
   */
  public Set<T> getEquivalenceClass(T vertex) {
    Set<T> set = new HashSet<T>();

    // first check if its an equivalence relation
    if (!isEquivalence()) {
      return set;
    }

    // iterate through all edges
    for (Edge<T> edge : edges) {
      if (edge.getSource().equals(vertex)) {
        set.add(edge.getDestination());
      }
    }
    return set;
  }

  /**
   * performs a breadth first search of the graph using and iterative approach.
   *
   * @return a list of the vertices searched in order.
   */
  public List<T> iterativeBreadthFirstSearch() {
    // initialise all lists
    Queue<Integer> queue = new Queue<>();
    ArrayList<T> visited = new ArrayList<T>();
    Set<Integer> discovered = new HashSet<>();
    ArrayList<Integer> roots = new ArrayList<Integer>();

    // get the roots
    Set<T> rootSet = getRoots();
    for (T vertex : rootSet) {
      roots.add(Integer.parseInt((String) vertex));
    }
    // sort the roots
    Collections.sort(roots);

    // iterate through all roots
    for (int root : roots) {
      // queue the root
      queue.enQueue(root);

      while (!queue.isEmpty()) {
        int vertex = queue.deQueue();

        ArrayList<Integer> successors = sort(visited, discovered, vertex);

        // add thte successors to the discovered list
        discovered.addAll(successors);

        // que all successors
        for (int v1 : successors) {
          queue.enQueue(v1);
        }
      }
    }
    return visited;
  }

  /**
   * performs a depth first search of the graph.
   *
   * @return a list of the vertices searched in order
   */
  public List<T> iterativeDepthFirstSearch() {
    // initialise all lists
    Stack<Integer> stack = new Stack<>();
    ArrayList<T> visited = new ArrayList<>();
    Set<Integer> discovered = new HashSet<>();
    ArrayList<Integer> roots = new ArrayList<Integer>();

    // get the roots
    Set<T> rootSet = getRoots();
    for (T vertex : rootSet) {
      roots.add(Integer.parseInt((String) vertex));
    }
    // sort the roots
    Collections.sort(roots);

    // iterate through all roots
    for (int root : roots) {
      stack.push(root);

      while (!stack.isEmpty()) {
        int vertex = stack.pop();

        // sort the successors
        ArrayList<Integer> successors = sort(visited, discovered, vertex);

        Collections.reverse(successors);

        // add thte successors to the discovered list
        discovered.addAll(successors);

        // push all vertices to the stack
        for (int v1 : successors) {
          stack.push(v1);
        }
      }
    }
    return visited;
  }

  /**
   * helper function which sorts the discovered, visited vertices and also finds and sorts the
   * successor vertices.
   *
   * @param visited arraylist containing already visited vertices
   * @param discovered set containing all discovered vertices
   * @param vertex a vertex
   * @return an arraylist of sorted successors
   */
  public ArrayList<Integer> sort(ArrayList<T> visited, Set<Integer> discovered, int vertex) {
    // add the vertex
    visited.add(getVertex(vertex).getValue());
    discovered.add(vertex);

    // initialise the successors
    ArrayList<Integer> successors = new ArrayList<Integer>();

    for (Vertex<T> v : getVertex(vertex).getSuccessors()) {
      if (!discovered.contains(Integer.parseInt((String) v.getValue()))) {
        successors.add(Integer.parseInt((String) v.getValue()));
      }
    }

    // reverse the successors list
    Collections.sort(successors);
    return successors;
  }

  /**
   * searches and returns a vertex given an integer (the value of the vertex).
   *
   * @param vertex the integer value of the verticies.
   * @return a vertex, the one matching the value of the input.
   */
  public Vertex<T> getVertex(int vertex) {

    for (Vertex<T> v : verticies) {
      // return the vertex if it matched
      if (vertex == Integer.parseInt((String) v.getValue())) {
        return v;
      }
    }
    return null;
  }

  /**
   * returns a vertrx based on the value of the vertex.
   *
   * @param value the value of the given vertex.
   * @return the vertex the value came from.
   */
  public Vertex<T> getVertex(T value) {

    for (Vertex<T> v : verticies) {
      // return the vertex if it matched
      if (value == v.getValue()) {
        return v;
      }
    }
    return null;
  }

  /**
   * performs a breadth first search of the graph recursively.
   *
   * @return a list of the vertices searched in order.
   */
  public List<T> recursiveBreadthFirstSearch() {
    // initialise all lists
    Set<Integer> discovered = new HashSet<>();
    ArrayList<Integer> roots = new ArrayList<Integer>();
    ArrayList<T> visited = new ArrayList<T>();
    Queue<Integer> queue = new Queue<>();

    // get the roots
    Set<T> rootSet = getRoots();
    for (T vertex : rootSet) {
      roots.add(Integer.parseInt((String) vertex));
    }
    // sort the roots
    Collections.sort(roots);

    for (int root : roots) {
      bfs(root, visited, discovered, queue);
    }
    return visited;
  }

  /**
   * a helper function which performs a recursive bread first search of one root.
   *
   * @param root the root to be searched
   * @param visited a list of vertices already visited
   * @param discovered a list of vertices already discovered
   * @param queue a queue containing the vertices that have been discovered
   */
  public void bfs(int root, ArrayList<T> visited, Set<Integer> discovered, Queue<Integer> queue) {
    // initialise the successors
    ArrayList<Integer> successors = new ArrayList<>();

    // que the root
    if (!discovered.contains(root)) {
      queue.enQueue(root);
    }
    discovered.add(root);

    for (Vertex<T> v : getVertex(root).getSuccessors()) {
      if (!discovered.contains(Integer.parseInt((String) v.getValue()))) {
        // add the vertex to successors
        successors.add(Integer.parseInt((String) v.getValue()));
      }
    }
    // sort the successors
    Collections.sort(successors);

    // update the discovered verticies
    discovered.addAll(successors);

    for (int v : successors) {
      // que all successors
      queue.enQueue(v);
    }
    // now deque the que
    visited.add(getVertex(queue.deQueue()).getValue());

    // base case
    if (queue.isEmpty()) {
      return;
    }

    // recurse
    bfs(queue.peek(), visited, discovered, queue);
  }

  /**
   * perfornms a depth first search of the graph recursively.
   *
   * @return a list containig the vertices searched in order.
   */
  public List<T> recursiveDepthFirstSearch() {
    // initialise all lists
    Set<Integer> discovered = new HashSet<>();
    ArrayList<Integer> roots = new ArrayList<>();
    ArrayList<T> visited = new ArrayList<T>();

    // get the roots
    Set<T> rootSet = getRoots();
    for (T vertex : rootSet) {
      roots.add(Integer.parseInt((String) vertex));
    }
    // sort the roots
    Collections.sort(roots);

    for (int root : roots) {
      dfs(root, visited, discovered);
    }
    return visited;
  }

  /**
   * a helper function which performs a depth first search recursively for one root vertex.
   *
   * @param root the root to be searched.
   * @param visited a list of already visited vertices.
   * @param discovered a list of already discovered vertices.
   */
  private void dfs(int root, ArrayList<T> visited, Set<Integer> discovered) {
    // initialise the successors
    ArrayList<Integer> successors = new ArrayList<>();
    visited.add(getVertex(root).getValue());
    discovered.add(root);

    for (Vertex<T> v : getVertex(root).getSuccessors()) {
      if (!discovered.contains(Integer.parseInt((String) v.getValue()))) {
        // add the vertex to successors
        successors.add(Integer.parseInt((String) v.getValue()));
      }
    }
    // sort the successors
    Collections.sort(successors);

    // update the discovered verticies
    discovered.addAll(successors);

    // base case
    if (successors.isEmpty()) {
      return;
    }

    for (int vertex : successors) {
      // recurse the dfs
      dfs(vertex, visited, discovered);
    }
  }

  /**
   * a helper function which converts a list of type T into a list of integers.
   *
   * @param list a list to be converted to T tpye.
   * @return a list of integers.
   */
  public List<Integer> sort(List<T> list) {
    // create new list
    List<Integer> intList = new ArrayList<Integer>();

    // iterate through and convert to int
    for (T data : list) {
      intList.add(Integer.parseInt((String) data));
    }
    return intList;
  }
}
