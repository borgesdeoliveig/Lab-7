import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Define vertices
        String[] vertices = {
            "Liberal Arts", "Student Services", "Health Careers & Sciences",
            "Health Technologies Center", "Recreation Center", 
            "Technology Learning Center", "Business & Technology", "Theatre"
        };

        // Create adjacency list for the graph
        Map<String, List<String>> adjacencyList = new HashMap<>();
        for (String vertex : vertices) {
            adjacencyList.put(vertex, new ArrayList<>());
        }

        // Define edges
        adjacencyList.get("Liberal Arts").add("Student Services");
        adjacencyList.get("Student Services").add("Liberal Arts");
        adjacencyList.get("Student Services").add("Health Careers & Sciences");
        adjacencyList.get("Student Services").add("Business & Technology");
        adjacencyList.get("Health Careers & Sciences").add("Student Services");
        adjacencyList.get("Health Careers & Sciences").add("Health Technologies Center");
        adjacencyList.get("Health Careers & Sciences").add("Recreation Center");
        adjacencyList.get("Recreation Center").add("Health Careers & Sciences");
        adjacencyList.get("Recreation Center").add("Technology Learning Center");
        adjacencyList.get("Technology Learning Center").add("Recreation Center");
        adjacencyList.get("Technology Learning Center").add("Business & Technology");
        adjacencyList.get("Business & Technology").add("Student Services");
        adjacencyList.get("Business & Technology").add("Technology Learning Center");
        adjacencyList.get("Business & Technology").add("Theatre");
        adjacencyList.get("Theatre").add("Business & Technology");

        // Perform DFS from "Business & Technology"
        System.out.println("DFS starting from Business & Technology:");
        Set<String> visited = new HashSet<>();
        dfs("Business & Technology", adjacencyList, visited);

        // Display paths
        System.out.println("\nPaths:");
        printPath("Business & Technology", "Health Technologies Center", adjacencyList);
        printPath("Business & Technology", "Student Services", adjacencyList);
        printPath("Business & Technology", "Recreation Center", adjacencyList);

        // Print tree (Adjacency list)
        System.out.println("\nDFS Tree (Adjacency List):");
        adjacencyList.forEach((key, value) -> {
            System.out.println(key + " -> " + value);
        });
    }

    public static void dfs(String current, Map<String, List<String>> graph, Set<String> visited) {
        if (visited.contains(current)) return;
        System.out.println(current);
        visited.add(current);
        for (String neighbor : graph.get(current)) {
            dfs(neighbor, graph, visited);
        }
    }

    public static void printPath(String start, String end, Map<String, List<String>> graph) {
        List<String> path = new ArrayList<>();
        if (findPath(start, end, graph, new HashSet<>(), path)) {
            System.out.print("Path from " + start + " to " + end + ": ");
            System.out.println(path);
        } else {
            System.out.println("No path found from " + start + " to " + end);
        }
    }

    public static boolean findPath(String current, String end, Map<String, List<String>> graph, Set<String> visited, List<String> path) {
        if (visited.contains(current)) return false;
        visited.add(current);
        path.add(current);
        if (current.equals(end)) return true;
        for (String neighbor : graph.get(current)) {
            if (findPath(neighbor, end, graph, visited, path)) return true;
        }
        path.remove(path.size() - 1);
        return false;
    }
}
