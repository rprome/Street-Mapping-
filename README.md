# Street Mapping Project

This Java-based mapping application calculates the shortest path between two points using latitude and longitude. The project utilizes the **Haversine formula** for distance calculation and **Dijkstra’s algorithm** to find the shortest path. It also includes basic visualization of the map and computed paths.

Three maps are included for testing:
- **University of Rochester campus**
- **Monroe County**
- **New York State**

Currently, the program calculates and displays the shortest path along with the total distance and provides a simple map visualization. I am working on implementing more interactive features and a better UI. 

---

## Folder Structure

```
src/
Edge.java           # Represents the edges (roads) in the graph
Graph.java          # Implements graph operations, Dijkstra’s algorithm, and the Haversine formula
Node.java           # Represents the nodes (intersections) in the graph
StreetMap.java      # Main program to read input, compute shortest paths, and handle visualization
monroe.txt          # Dataset for Monroe County map
nys.txt             # Dataset for New York State map
ur.txt              # Dataset for UofR campus map
README.md           # Project documentation
```

---

## Features

1. **Shortest Path Calculation**:
   - Computes the shortest path between two points using **Dijkstra's algorithm**.
   - Accurate distance calculations based on the **Haversine formula**.

2. **Visualization**:
   - Simple graphical representation of the map (lines represent connections).
   - Highlights the shortest path when directions are requested.

3. **Datasets**:
   - Includes three datasets for different regions: UofR campus, Monroe County, and New York State.

---

## Usage Instructions

### Compile the Code
```bash
javac src/*.java -d bin
```

### Run the Program
- **To show directions between two locations**:
  ```bash
  java -cp bin StreetMap src/ur.txt --directions startLocation endLocation
  ```
- **To display the map**:
  *(Note: Visualization may be basic, with roads represented as simple lines.)*
  ```bash
  java -cp bin StreetMap src/ur.txt --show
  ```
- **To do both (display map and show directions)**:
  ```bash
  java -cp bin StreetMap src/ur.txt --show --directions startLocation endLocation
  ```

Replace `src/ur.txt` with the desired map dataset file (e.g., `src/monroe.txt` or `src/nys.txt`).

---

## Resources Used
- [Haversine Formula](https://en.wikipedia.org/wiki/Haversine_formula)
- [Dijkstra’s Algorithm](https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/)
- ChatGPT (syntax assistance)
