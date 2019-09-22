package algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Parser {

	// a master HashMap of node id #s to the node objects they correspond to
	HashMap<String, Node> fNodes = new HashMap<String, Node>();

	public Parser() {
		parseEdges();
		parseHeuristic();
	}

	/**
	 * creates a new Node with id nodeId. gives inits a connection with connectionId
	 * and distance. puts the node in the master fNodes HashMap with its id as the
	 * key
	 * 
	 * @param nodeId       - id of the new node
	 * @param connectionId - id of the node it is to connect with
	 * @param distance     - distance between new node and connected node
	 */
	private void initNewNode(String nodeId, String connectionId, String distance) {
		Node newNode = new Node(nodeId);
		newNode.newConnection(connectionId, distance);
		fNodes.put(nodeId, newNode);
	}

	/**
	 * work through "edges.txt", using arrays of form {nodeId, nodeId, distance}
	 * from a line of the file to put Node objects into the master fNodes HashMap
	 */
	private void parseEdges() {
		try {
			Scanner scanner = new Scanner(new File("src/main/resources/edges.txt"));
			String[] arr; // space-delimited items of a single line in "edges.txt"

			while (scanner.hasNextLine()) {
				arr = scanner.nextLine().split(" ", 3);

				// make a new connection if the node with id arr[0] is already in the master
				// map. if it isn't, make new {key,val} pair with the arr[0] as new node id
				if (fNodes.containsKey(arr[0])) {
					fNodes.get(arr[0]).newConnection(arr[1], arr[2]);
				} else {
					initNewNode(arr[0], arr[1], arr[2]);
				}

				// likewise for id arr[1]
				if (fNodes.containsKey(arr[1])) {
					fNodes.get(arr[1]).newConnection(arr[0], arr[2]);
				} else {
					initNewNode(arr[1], arr[0], arr[2]);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File \"edges.txt\" could not be found.");
		}
	}

	/**
	 * work through "heuristic.txt", using arrays of form {nodeId, heuristic value}
	 * from a line of the file to assign heuristic values to Nodes in fNodes
	 */
	private void parseHeuristic() {
		try {
			Scanner scanner = new Scanner(new File("src/main/resources/heuristic.txt"));
			String[] arr; // space-delimited items of a single line in "heuristic.txt"

			while (scanner.hasNextLine()) {
				arr = scanner.nextLine().split(" ", 2);

				// if the node with id arr[0] is in fNodes, set its heuristic to arr[1]
				if (fNodes.containsKey(arr[0])) {
					fNodes.get(arr[0]).setHeuristic(Double.parseDouble(arr[1]));
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File \"heuristic.txt\" could not be found.");
		}
	}

	public HashMap<String, Node> getNodes() {
		return fNodes;
	}
}
