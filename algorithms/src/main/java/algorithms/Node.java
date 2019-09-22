package algorithms;

import java.util.HashMap;

public class Node {

	String fIdentifier;
	double fHeuristic;
	HashMap<String, Double> fConnections = new HashMap<String, Double>();

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

	public void newConnection(String node, String distance) {
		fConnections.put(node, Double.parseDouble(distance));
	}
}
