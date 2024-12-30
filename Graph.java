import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.List;

public class Graph {
    Map <String, double []> nodeMap;
    Map <String, Node> nodes;
    List<Edge> edges;
    private int edgeSize;

    public Graph(){
        nodeMap = new HashMap<>();
        nodes = new HashMap<>();
        edges = new ArrayList<>();
        edgeSize = 0;
    }

    public void addNode (String name, double longitude, double latitude){
        nodeMap.put (name, new double[]{longitude, latitude});
    }
    public double calculateDistance (String node1, String node2){
        double [] coord1 = nodeMap.get (node1);
        double [] coord2 = nodeMap.get (node2);


         double lon1 = coord1[0];
         double lat1 = coord1[1];
         double lon2 = coord2[0];
         double lat2 = coord2[1];
 
         final int R = 3961; 
         double dLat = Math.toRadians(lat2 - lat1);
         double dLon = Math.toRadians(lon2 - lon1);
 
         double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                 + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                 * Math.sin(dLon / 2) * Math.sin(dLon / 2);
         double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
         return R * c; 
     }
 
     public void addEdge(String id, String from, String to) {
        edges.add(new Edge(id, from, to, calculateDistance(from, to)));
        edgeSize++;

    }
    public void modifyGraph() {
        Edge[] edgeArray = edges.toArray(new Edge[0]);

        for (String name : nodeMap.keySet()) {
            nodes.put(name, new Node());
        }

        edgeSize = edgeArray.length;

        for (Edge edge : edgeArray) {
            nodes.get(edge.getFrom()).getEdges().add(edge);
            nodes.get(edge.getTo()).getEdges().add(edge);
        }
    }

    public void dijkstra(String start, String end) {
    String next = start; 
    nodes.get(next).setDistance(0); 

    for (String name : nodes.keySet()) {
        if (nodes.get(end).isVisited()) {
            break; 
        }

        if (next.equals("")) {
            break; 
        }

        List<Edge> temp = nodes.get(next).getEdges(); 

        for (Edge edge : temp) {
            String neighbor = edge.getNeighbor(next); 

            if (!nodes.get(neighbor).isVisited()) {
                double dist = nodes.get(next).getDistance() + edge.getLength(); 

                if (dist < nodes.get(neighbor).getDistance()) {
                    nodes.get(neighbor).setDistance(dist);
                    nodes.get(neighbor).setPrev(next); 
                }
            }
        }

        nodes.get(next).setVisited(true); 
        next = getPath();
    }
}

public String getPath() {
    String storage = ""; 
    double shortestDistance = Double.MAX_VALUE; 

    for (String key : nodes.keySet()) {
        double temp = nodes.get(key).getDistance();

        if (!nodes.get(key).isVisited() && temp < shortestDistance) {
            shortestDistance = temp; 
            storage = key; 
        }
    }

    return storage; 
}
public List<String> getShortestPath(String start, String end) {
    System.out.println("Finding shortest path from " + start + " to " + end + "...");
    List<String> path = new ArrayList<>();
    dijkstra(start, end); 

    if (start.equals(end)) {
        path.add(start);
        System.out.println("Start equals end: " + start);
        return path;
    } else if (nodes.get(end).getDistance() == Double.POSITIVE_INFINITY) {
        System.out.println("No path found between " + start + " and " + end);
        return path;
    } else {
        Stack<String> stack = new Stack<>();
        String temp = end;
        stack.push(end);
        nodes.get(end).setHighlighted(true); 

        while (!nodes.get(temp).getPrev().equals(start)) {
            temp = nodes.get(temp).getPrev();
            nodes.get(temp).setHighlighted(true); 
            stack.push(temp);
        }

        stack.push(start); 
        nodes.get(start).setHighlighted(true);

        while (!stack.isEmpty()) {
            path.add(stack.pop());
        }
        System.out.println("Shortest path: " + String.join(" -> ", path));
        System.out.println("Total distance: " + nodes.get(end).getDistance() + " miles");

        return path;
    }
}


}

