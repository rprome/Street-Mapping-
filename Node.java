import java.util.ArrayList;
import java.util.List;

public class Node {
   private double Longitude;
   private double Latitude;
   private boolean visited;
   private boolean highlighted;
   private String prev;
   private double distance = Double.POSITIVE_INFINITY;
   private List<Edge> edges = new ArrayList<Edge>();

    public double getLongitude() {
        return Longitude;
    }
    public void setLongitude(double longitude) {
        Longitude = longitude;
    }
    public double getLatitude() {
        return Latitude;
    }
    public void setLatitude(double latitude) {
     Latitude = latitude;
    }
    public boolean isVisited() {
        return visited;
    }
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    public boolean isHighlighted() {
        return highlighted;
    }
    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }
    public String getPrev() {
        return prev;
    }
    public void setPrev(String prev) {
        this.prev = prev;
    }
    public double getDistance() {
        return distance;
    }
    public void setDistance(double distance) {
        this.distance = distance;
        }
    public List<Edge> getEdges() {
        return edges;
    }
    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

}
