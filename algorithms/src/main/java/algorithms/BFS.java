package algorithms;

import java.util.ArrayList;
import java.util.HashMap;

public class BFS {

	public String search(String startNodeId, String goalNodeId) {
		if (!Driver.NODES.containsKey(startNodeId) || !Driver.NODES.containsKey(goalNodeId)) {
			return "Search failure: invalid nodes.";
		}

		ArrayList<String> queue = new ArrayList<String>();
		ArrayList<String> visited = new ArrayList<String>();
		HashMap<String, String> parentMap = new HashMap<String, String>();

		queue.add(startNodeId);
		String parentNodeId;

		while (!queue.isEmpty()) {
			parentNodeId = queue.remove(0);
			visited.add(parentNodeId);

			ArrayList<String> children = Driver.NODES.get(startNodeId).getConnectionIds();

			for (String childNodeId : Driver.NODES.get(parentNodeId).getConnectionIds()) {
				if (!visited.contains(childNodeId) && !queue.contains(childNodeId)) {
					parentMap.put(childNodeId, parentNodeId);

					if (childNodeId.equals(goalNodeId)) {
						ArrayList<String> path = new ArrayList<String>();
						double distance = 0;

						while (childNodeId != startNodeId) {
							path.add(0, childNodeId);
							distance += Driver.NODES.get(childNodeId).getConnections().get(parentMap.get(childNodeId));
							childNodeId = parentMap.get(childNodeId);
						}
						path.add(0, startNodeId);
						return "Breadth-First Search from " + parentNodeId + " to " + goalNodeId
								+ "\nNum nodes visited: " + visited.size() + "\nNum nodes on path: " + path.size()
								+ "\nDistance (km): " + distance;
					} else {
						queue.add(childNodeId);
					}
				}
			}
		}
		return "Search failure: goal node not found.";
	}
}
