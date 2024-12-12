class Main {
  public static void main(String[] args) {
    // Define vertices
    String[] vertices = {
      "Liberal Arts", "Student Services", "Health Careers & Sciences", 
      "Health Technologies Center", "Recreation Center", 
      "Technology Learning Center", "Business & Technology", "Theatre"
    };

    // Define edges based on the map
    int[][] edges = {
      {0, 1}, // Liberal Arts to Student Services
      {1, 2}, // Student Services to Health Careers & Sciences
      {1, 6}, // Student Services to Business & Technology
      {2, 3}, // Health Careers & Sciences to Health Technologies Center
      {2, 4}, // Health Careers & Sciences to Recreation Center
      {4, 5}, // Recreation Center to Technology Learning Center
      {5, 6}, // Technology Learning Center to Business & Technology
      {6, 7}  // Business & Technology to Theatre
    };

    // Create the graph
    Graph<String> graph = new UnweightedGraph<>(vertices, edges);

    // Perform DFS starting at "Business & Technology"
    UnweightedGraph<String>.SearchTree dfs = graph.dfs(graph.getIndex("Business & Technology"));

    // Display DFS order
    java.util.List<Integer> searchOrders = dfs.getSearchOrder();
    System.out.println(dfs.getNumberOfVerticesFound() + " vertices are searched in this DFS order:");
    for (int i = 0; i < searchOrders.size(); i++)
      System.out.print(graph.getVertex(searchOrders.get(i)) + " ");
    System.out.println();

    // Display parent relationships
    for (int i = 0; i < searchOrders.size(); i++)
      if (dfs.getParent(i) != -1)
        System.out.println("parent of " + graph.getVertex(i) +
          " is " + graph.getVertex(dfs.getParent(i)));

    // Print paths to specified buildings
    dfs.printPath(graph.getIndex("Health Technologies Center"));
    System.out.println();
    dfs.printPath(graph.getIndex("Student Services"));
    System.out.println();
    dfs.printPath(graph.getIndex("Recreation Center"));
    System.out.println();

    // Print the entire tree
    dfs.printTree();
  }
}