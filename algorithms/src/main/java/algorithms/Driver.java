package algorithms;

import java.util.HashMap;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		new Driver();
	}

	// a master HashMap of node id #s to the node objects they correspond to
	public static HashMap<String, Node> NODES;

	BFS bfs;
	UCS ucs;
	AStar aStar;

	public Driver() {
		NODES = new Parser().getNodes();

		bfs = new BFS();
		ucs = new UCS();
		aStar = new AStar();

		System.out.println(bfs.search("105050228", "105082955"));
		System.out.println(bfs.search("104779422", "105012740"));
		System.out.println(ucs.search("105050228", "105082955"));
		System.out.println(ucs.search("104779422", "105012740"));

		while (true) {
			printNodeInfo();
		}
	}

	// for testing purposes
	public void printNodeInfo() {
		System.out.print("\nEnter node ID (type 'e' to exit): ");
		Scanner scanner = new Scanner(System.in);
		String nodeId = scanner.nextLine();

		if (nodeId.equals("e")) {
			System.exit(0);
		} else if (NODES.containsKey(nodeId)) {
			System.out.println("Node: " + nodeId);
			System.out.println("Heuristic " + NODES.get(nodeId).getHeuristic());
			System.out.println("Connections (  ID - distance  ): ");
			for (HashMap.Entry<String, Double> node : NODES.get(nodeId).getConnections().entrySet()) {
				System.out.println("\t" + node.getKey() + " - " + node.getValue());
			}
		} else {
			System.out.println("Could not find node " + nodeId);
		}
	}
}
