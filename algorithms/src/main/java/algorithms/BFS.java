package algorithms;

import java.util.ArrayList;
import java.util.HashMap;

public class BFS {

	/**
	 * performs a Breadth-First Search on Driver.NODES. uses a while loop, a queue
	 * and a visited array to keep track of search progress. outputs search results
	 * if a goal node is found, or error messages if not
	 * 
	 * @param start - identifier String of the starting node
	 * @param goal  - identifier String of the goal node
	 * @return a String describing the search results (invalid nodes supplied; num
	 *         nodes visited, num nodes in path, and length of path; or no goal node
	 *         found)
	 */
	public String search(final String start, final String goal) {
		if (!Driver.NODES.containsKey(start) || !Driver.NODES.containsKey(goal)) {
			return "Search failure: invalid nodes.";
		}

		// shallowest node will always be pushed to index 0 of the queue
		ArrayList<String> queue = new ArrayList<String>();

		// node added to visited when it is expanded
		ArrayList<String> visited = new ArrayList<String>();

		// keep track of parent of each node so that we can backtrack later to find the
		// path to the goal node
		HashMap<String, String> parentMap = new HashMap<String, String>();

		queue.add(start);
		String parent;
		System.out.println("Searching...");

		// we loop as long as queue is not empty (in such case, we have not found a goal
		// node and the search failed)
		while (!queue.isEmpty()) {
			parent = queue.remove(0);
			visited.add(parent);

			// looping through the connections (children) of the node selected for expansion
			for (String child : Driver.NODES.get(parent).getConnectionIds()) {

				// make sure it is not a repetition
				if (!parentMap.containsKey(child) && !queue.contains(child)) {
					parentMap.put(child, parent);

					// stopping condition. now backtrack to find a path and distance back to goal
					if (child.equals(goal)) {
						ArrayList<String> path = new ArrayList<String>();
						double distance = 0; // distance of path in kilometers

						while (child != start) {
							path.add(0, child);
							distance += Driver.NODES.get(child).getConnections().get(parentMap.get(child));
							child = parentMap.get(child);
						}
						path.add(0, start);

						return "Breadth-First Search from " + start + " to " + goal + "\nNum nodes visited: "
								+ visited.size() + "\nNum nodes on path: " + path.size() + "\nDistance (km): "
								+ distance;
					} else {
						queue.add(child);
					}
				}
			}
		}
		return "Search failure: goal node not found.";
	}
}
