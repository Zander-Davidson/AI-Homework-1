package algorithms;

import java.util.ArrayList;
import java.util.HashMap;

public class Node {

	private String fIdentifier;
	private double fHeuristic;
	private HashMap<String, Double> fConnections = new HashMap<String, Double>();

	public Node(String id) {
		fIdentifier = id;
	}

	public String getIdentifier() {
		return fIdentifier;
	}

	public void setHeuristic(double heuristic) {
		fHeuristic = heuristic;
	}

	public double getHeuristic() {
		return fHeuristic;
	}

	public HashMap<String, Double> getConnections() {
		return fConnections;
	}

	public ArrayList<String> getConnectionIds() {
		ArrayList<String> connectionIds = new ArrayList<String>();
		for (HashMap.Entry<String, Double> edge : fConnections.entrySet()) {
			connectionIds.add(edge.getKey());
		}
		return connectionIds;
	}

	public void newConnection(String node, String distance) {
		fConnections.put(node, Double.parseDouble(distance));
	}
}
