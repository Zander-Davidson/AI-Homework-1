package algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

public class UCS {

	public String search(final String start, final String goal) {
		if (!Driver.NODES.containsKey(start) || !Driver.NODES.containsKey(goal)) {
			return "Search failure: invalid nodes.\n";
		}
		System.out.println("Uniform-Cost Search from " + start + " to " + goal);

		// Object that stores data for a path from the start node to any given node.
		class Path {
			private double fDistance = 0; // distance of path in km
			private ArrayList<String> fNodes = new ArrayList<String>(); // path of nodes identifiers starting with
																		// "start" node

			// create a new path from another path by adding a single node to the end.
			// update distance accordingly
			public Path(Path parentPath, String newNode) {
				fNodes.addAll(parentPath.getNodes());
				fDistance = parentPath.getDistance()
						+ Driver.NODES.get(newNode).getConnections().get(fNodes.get(fNodes.size() - 1));
				fNodes.add(newNode);
			}

			// default/root node case
			public Path(String root) {
				fNodes.add(root);
				fDistance = 0.0;
			}

			public ArrayList<String> getNodes() {
				return fNodes;
			}

			// get the frontier node of this path
			public String getLeadNode() {
				return fNodes.get(fNodes.size() - 1);
			}

			public double getDistance() {
				return fDistance;
			}
		}

		// use this for comparing path distances when sorting the "paths" ArrayList
		Comparator<Path> pathComparator = new Comparator<Path>() {
			public int compare(Path p1, Path p2) {
				return p1.getDistance() < p2.getDistance() ? -1 : 1;
			}
		};

		// the queue; sorted longest to shortest distance with the comparator and
		// .sort()
		ArrayList<Path> paths = new ArrayList<Path>();

		// a map of frontier and visited node identifiers to the Paths they are
		// associated with
		HashMap<String, Path> leadNodeMap = new HashMap<String, Path>();

		HashSet<String> visited = new HashSet<String>();
		String parent;
		Path parentPath;
		Path childPath;

		// init the frontier and the default path (of the start node)
		paths.add(new Path(start));
		System.out.println("Searching...");

		while (!paths.isEmpty()) {
			parentPath = paths.remove(0); // pop the shortest path off the queue
			parent = parentPath.getLeadNode();
			visited.add(parent);

			// goal test
			if (parent.equals(goal)) {
				return "Num nodes visited: " + visited.size() + "\nNum nodes on path: " + parentPath.getNodes().size()
						+ "\nDistance (km): " + parentPath.getDistance() + "\n";
			}

			// looping through the connections (children) of the node selected for expansion
			for (String child : Driver.NODES.get(parent).getConnectionIds()) {

				childPath = new Path(parentPath, child);

				// if the new path has a unique lead node, add it to the list of paths.
				// otherwise, check if it is not unique and chose the shorter path to keep in
				// the lead node map
				if (!visited.contains(child) && !leadNodeMap.containsKey(child)) {
					paths.add(childPath);
					leadNodeMap.put(child, childPath);

				} else if (leadNodeMap.containsKey(child)
						&& childPath.getDistance() < leadNodeMap.get(child).getDistance()) {
					paths.remove(leadNodeMap.get(child));
					paths.add(childPath);
					leadNodeMap.replace(child, childPath);
				}
				paths.sort(pathComparator); // sort the paths list shortest (index 0) to longest
			}
		}
		return "Search failure: goal node not found.\n";
	}
}