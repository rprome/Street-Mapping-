import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StreetMap extends JComponent {
    private Graph graph = new Graph();
    private double minx = Double.MAX_VALUE;
    private double maxx = Double.MIN_VALUE;
    private double miny = Double.MAX_VALUE;
    private double maxy = Double.MIN_VALUE;

    public StreetMap(String mapFile) {
        try (Scanner sc = new Scanner(new File(mapFile))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\s+");

                if (parts[0].equals("i")) {
                    String id = parts[1];
                    double lat = Double.parseDouble(parts[2]);
                    double lon = Double.parseDouble(parts[3]);
                    graph.addNode(id, lon, lat);
                    updateMinMax(lon, lat);
                } else if (parts[0].equals("r")) { 
                    String roadId = parts[1];
                    String from = parts[2];
                    String to = parts[3];
                    graph.addEdge(roadId, from, to);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + mapFile);
            System.exit(1);
        }
    }

    private void updateMinMax(double x, double y) {
        minx = Math.min(minx, x);
        maxx = Math.max(maxx, x);
        miny = Math.min(miny, y);
        maxy = Math.max(maxy, y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        for (Edge edge : graph.edges) {
            setEdgeColorAndStroke(edge, g2);

            double x1 = scaleX(graph.nodeMap.get(edge.getFrom())[0]);
            double y1 = scaleY(graph.nodeMap.get(edge.getFrom())[1]);
            double x2 = scaleX(graph.nodeMap.get(edge.getTo())[0]);
            double y2 = scaleY(graph.nodeMap.get(edge.getTo())[1]);

            Line2D line = new Line2D.Double(x1, y1, x2, y2);
            g2.draw(line);
        }
    }

    private double scaleX(double x) {
        return 100 + ((x - minx) / (maxx - minx)) * 800;
    }

    private double scaleY(double y) {
        return 100 + ((maxy - y) / (maxy - miny)) * 800;
    }

    private void setEdgeColorAndStroke(Edge edge, Graphics2D g2) {
        if (graph.nodes.get(edge.getFrom()).isHighlighted() && graph.nodes.get(edge.getTo()).isHighlighted()) {
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(2.5f));
        } else {
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(1.2f));
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("provide the input file and arguments to run the program");
            return;
        }

        String mapFile = args[0];
        boolean show = Arrays.asList(args).contains("--show");
        int dirIndex = Arrays.asList(args).indexOf("--directions");
        String startIntersection = null;
        String endIntersection = null;

        if (dirIndex != -1 && dirIndex + 2 < args.length) {
            startIntersection = args[dirIndex + 1];
            endIntersection = args[dirIndex + 2];
        }

        StreetMap visualizer = new StreetMap(mapFile);
        visualizer.graph.modifyGraph();

        if (startIntersection != null && endIntersection != null) {
            //System.out.println("Finding shortest path from " + startIntersection + " to " + endIntersection + "...");
            List<String> path = visualizer.graph.getShortestPath(startIntersection, endIntersection);
            if (path.isEmpty()) {
                //System.out.println("No path found between " + startIntersection + " and " + endIntersection + ".");
            } else {
                //System.out.println("Shortest path: " + String.join(" -> ", path));
                //System.out.println("Total distance: " + visualizer.graph.nodes.get(endIntersection).getDistance() + " miles.");
            }
        }

        if (show) {
            JFrame frame = new JFrame("Street Mapping");
            frame.add(visualizer);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000, 1000);
    }
}
