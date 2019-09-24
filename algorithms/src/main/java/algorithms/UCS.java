package algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

public class UCS {

	public String search(final String start, final String goal) {
		if (!Driver.NODES.containsKey(start) || !Driver.NODES.containsKey(goal)) {
			return "Search failure: invalid nodes.";
		}

		class Path {
			private double fDistance = 0; // distance of path in km
			private ArrayList<String> fNodes = new ArrayList<String>(); // path of nodes identifiers starting with
																		// "start" node

			public Path(Path parentPath, String newNode) {
				fNodes.addAll(parentPath.getNodes());
				fDistance = parentPath.getDistance()
						+ Driver.NODES.get(newNode).getConnections().get(fNodes.get(fNodes.size() - 1));
				fNodes.add(newNode);
			}

			public Path(String root) {
				fNodes.add(root);
				fDistance = 0.0;
			}

			public ArrayList<String> getNodes() {
				return fNodes;
			}

			public String getLeadNode() {
				return fNodes.get(fNodes.size() - 1);
			}

			public double getDistance() {
				return fDistance;
			}
		}

		Comparator<Path> pathComparator = new Comparator<Path>() {
			public int compare(Path p1, Path p2) {
				return p1.getDistance() < p2.getDistance() ? -1 : 1;
			}
		};

		ArrayList<Path> paths = new ArrayList<Path>(); // sorted longest to shortest distance
		HashMap<String, Path> leadNodeMap = new HashMap<String, Path>();
		HashSet<String> visited = new HashSet<String>();
		String parent;
		Path parentPath;
		Path childPath;

		// init the frontier and the default path (of the start node)
		paths.add(new Path(start));
		System.out.println("Searching...");

		while (!paths.isEmpty()) {
			parentPath = paths.remove(0);
			parent = parentPath.getLeadNode();
			visited.add(parent);

			if (parent.equals(goal)) {
				return "Uniform-Cost Search from " + start + " to " + goal + "\nNum nodes visited: " + visited.size()
						+ "\nNum nodes on path: " + parentPath.getNodes().size() + "\nDistance (km): "
						+ parentPath.getDistance();
			}

			// looping through the connections (children) of the node selected for expansion
			for (String child : Driver.NODES.get(parent).getConnectionIds()) {

				childPath = new Path(parentPath, child);

				if (!visited.contains(child) && !leadNodeMap.containsKey(child)) {
					paths.add(childPath);
					leadNodeMap.put(child, childPath);

				} else if (leadNodeMap.containsKey(child)
						&& childPath.getDistance() < leadNodeMap.get(child).getDistance()) {
					paths.remove(leadNodeMap.get(child));
					paths.add(childPath);
					leadNodeMap.replace(child, childPath);
				}
				paths.sort(pathComparator);
			}
		}
		return "Search failure: goal node not found.";
	}
}