package cs4150T;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class KrushalMST {

	public static void main(String args[]) {
//		Scanner scan = new Scanner(System.in);
//		String nextLine = scan.nextLine();
//		String data[] = nextLine.split(" ");
//		int amount;
//		int minToWait;
//		int numOfPeople = Integer.parseInt(data[0]);
//		int minutesToClose = Integer.parseInt(data[1]);
//		Graph graph = new Graph(numOfPeople + 1, numOfPeople);
//		for (int i = 1; i <= numOfPeople; i++) {
//			nextLine = scan.nextLine();
//			data = nextLine.split(" ");
//			amount = Integer.parseInt(data[0]);
//			minToWait = Integer.parseInt(data[1]);
//			graph.edge[i].src = 0;
//			graph.edge[i].dest = 0;
//			graph.edge[i].weight = minToWait;
//			graph.edge[i].amount = amount;
//		}
//		graph.KruskalMST(minutesToClose);
		test();
	}

	public static void test() {
		Graph graph = new Graph(5);
		graph.addEgde(1, 0, 1, 1000);
		graph.addEgde(2, 0, 2, 2000);
		graph.addEgde(3, 0, 2, 500);
		graph.addEgde(4, 0, 0, 1200);
		graph.kruskalMST(4);

//		graph.edge[0].src = 1;
//		graph.edge[0].dest = 0;
//		graph.edge[0].weight = 1;
//		graph.edge[0].amount = 1000;
//
//		graph.edge[1].src = 2;
//		graph.edge[1].dest = 0;
//		graph.edge[1].weight = 2;
//		graph.edge[1].amount = 2000;
//
//		graph.edge[2].src = 3;
//		graph.edge[2].dest = 0;
//		graph.edge[2].weight = 2;
//		graph.edge[2].amount = 500;
//
//		graph.edge[3].src = 4;
//		graph.edge[3].dest = 0;
//		graph.edge[3].weight = 0;
//		graph.edge[3].amount = 1200;
	}

	static class Edge {
		int source;
		int destination;
		int weight;
		int amount;

		public Edge(int source, int destination, int weight, int amount) {
			this.source = source;
			this.destination = destination;
			this.weight = weight;
			this.amount = amount;
		}
	}

	static class Graph {
		int vertices;
		ArrayList<Edge> allEdges = new ArrayList<>();

		Graph(int vertices) {
			this.vertices = vertices;
		}

		public void addEgde(int source, int destination, int weight, int amount) {
			Edge edge = new Edge(source, destination, weight, amount);
			allEdges.add(edge); // add to total edges
		}

		public void kruskalMST(int totalMin) {
			PriorityQueue<Edge> pq = new PriorityQueue<>(allEdges.size(), Comparator.comparingInt(o -> o.weight));

			// add all the edges to priority queue, //sort the edges on weights
			for (int i = 0; i < allEdges.size(); i++) {
				pq.add(allEdges.get(i));
			}

			// create a parent []
			int[] parent = new int[vertices];

			// makeset
			makeSet(parent);

			ArrayList<Edge> mst = new ArrayList<>();

			// process vertices - 1 edges
			int index = 0;
			while (index < vertices - 1) {
				Edge edge = pq.remove();
				// check if adding this edge creates a cycle
				int x_set = find(parent, edge.source);
				int y_set = find(parent, edge.destination);

				if (x_set == y_set) {
					// ignore, will create cycle
				} else {
					// add it to our final result
					mst.add(edge);
					index++;
					union(parent, x_set, y_set);
				}
			}
			// print MST
			System.out.println("Minimum Spanning Tree: ");
			printGraph(mst, totalMin);
		}

		public void makeSet(int[] parent) {
			// Make set- creating a new element with a parent pointer to itself.
			for (int i = 0; i < vertices; i++) {
				parent[i] = i;
			}
		}

		public int find(int[] parent, int vertex) {
			// chain of parent pointers from x upwards through the tree
			// until an element is reached whose parent is itself
			if (parent[vertex] != vertex)
				return find(parent, parent[vertex]);
			;
			return vertex;
		}

		public void union(int[] parent, int x, int y) {
			int x_set_parent = find(parent, x);
			int y_set_parent = find(parent, y);
			// make x as parent of y
			parent[y_set_parent] = x_set_parent;
		}

		public void printGraph(ArrayList<Edge> edgeList, int totalMin) {
			int curAmount = edgeList.get(0).amount;
			int curWait = edgeList.get(0).weight;
			int count = 1;
			int max = 0;
			int total = 0;
			int totalCount = 0;
			for (int i = 1; i < edgeList.size(); i++) {
				Edge edge = edgeList.get(i);
//				System.out.println("Edge-" + i + " source: " + edge.source + " destination: " + edge.destination
//						+ " weight: " + edge.weight);
				if (totalCount < totalMin) {
					if (curWait == edgeList.get(i).weight) {
						count++;
						break;
					} else {
						if (count > 1) {
							total -= (edgeList.get(i - count).amount);
							for (int j = i - count; j <= count; i++) {
								if (edgeList.get(i).amount > max) {
									max = edgeList.get(i).amount;
								}
							}
						} else {
							max = edgeList.get(i).amount;
						}
						total += max;
						totalCount++;
					}
				}
				curAmount = edgeList.get(i).amount;
			}
			System.out.println(total);
		}
	}
//	public static void test() {
//		Graph graph = new Graph(4, 4);
//		graph.edge[0].src = 1;
//		graph.edge[0].dest = 0;
//		graph.edge[0].weight = 1;
//		graph.edge[0].amount = 1000;
//
//		graph.edge[1].src = 2;
//		graph.edge[1].dest = 0;
//		graph.edge[1].weight = 2;
//		graph.edge[1].amount = 2000;
//
//		graph.edge[2].src = 3;
//		graph.edge[2].dest = 0;
//		graph.edge[2].weight = 2;
//		graph.edge[2].amount = 500;
//
//		graph.edge[3].src = 4;
//		graph.edge[3].dest = 0;
//		graph.edge[3].weight = 0;
//		graph.edge[3].amount = 1200;
//		graph.KruskalMST(4);
//
//	}
//}
//
//class Graph {
////A class to represent a graph edge 
//	class Edge implements Comparable<Edge> {
//		int src, dest, weight, amount;
//
//		// Comparator function used for sorting edges
//		// based on their weight
//		public int compareTo(Edge compareEdge) {
//			return this.weight - compareEdge.weight;
//		}
//	};
//
//// A class to represent a subset for union-find 
//	class subset {
//		int parent, rank;
//	};
//
//	int V, E; // V-> no. of vertices & E->no.of edges
//	Edge edge[]; // collection of all edges
//
//// Creates a graph with V vertices and E edges 
//	Graph(int v, int e) {
//		V = v;
//		E = e;
//		edge = new Edge[E];
//		for (int i = 0; i < e; ++i)
//			edge[i] = new Edge();
//	}
//
//// A utility function to find set of an element i 
//// (uses path compression technique) 
//	int find(subset subsets[], int i) {
//		// find root and make root as parent of i (path compression)
//		if (subsets[i].parent != i)
//			subsets[i].parent = find(subsets, subsets[i].parent);
//
//		return subsets[i].parent;
//	}
//
//// A function that does union of two sets of x and y 
//// (uses union by rank) 
//	void Union(subset subsets[], int x, int y) {
//		int xroot = find(subsets, x);
//		int yroot = find(subsets, y);
//
//		// Attach smaller rank tree under root of high rank tree
//		// (Union by Rank)
//		if (subsets[xroot].rank < subsets[yroot].rank)
//			subsets[xroot].parent = yroot;
//		else if (subsets[xroot].rank > subsets[yroot].rank)
//			subsets[yroot].parent = xroot;
//
//		// If ranks are same, then make one as root and increment
//		// its rank by one
//		else {
//			subsets[yroot].parent = xroot;
//			subsets[xroot].rank++;
//		}
//	}
//
//// The main function to construct MST using Kruskal's algorithm 
//	int KruskalMST(int totalMin) {
//		Edge result[] = new Edge[V]; // Tnis will store the resultant MST
//		int e = 0; // An index variable, used for result[]
//		int i = 0; // An index variable, used for sorted edges
//		for (i = 0; i < V; ++i)
//			result[i] = new Edge();
//
//		// Step 1: Sort all the edges in non-decreasing order of their
//		// weight. If we are not allowed to change the given graph, we
//		// can create a copy of array of edges
//		Arrays.sort(edge);
//
//		// Allocate memory for creating V ssubsets
//		subset subsets[] = new subset[V];
//		for (i = 0; i < V; ++i)
//			subsets[i] = new subset();
//
//		// Create V subsets with single elements
//		for (int v = 0; v < V; ++v) {
//			subsets[v].parent = v;
//			subsets[v].rank = 0;
//		}
//
//		i = 0; // Index used to pick next edge
//
//		// Number of edges to be taken is equal to V-1
//		while (e < V - 1) {
//			// Step 2: Pick the smallest edge. And increment
//			// the index for next iteration
//			Edge next_edge = new Edge();
//			next_edge = edge[i++];
//
//			int x = find(subsets, next_edge.src);
//			int y = find(subsets, next_edge.dest);
//
//			// If including this edge does't cause cycle,
//			// include it in result and increment the index
//			// of result for next edge
//			if (x != y) {
//				result[e++] = next_edge;
//				Union(subsets, x, y);
//			}
//			// Else discard the next_edge
//		}
//
//		// print the contents of result[] to display
//		// the built MST
//		// System.out.println("Following are the edges in " + "the constructed MST");
//		int cur = result[0].weight;
//		int count = 1;
//		int max = 0;
//		int total = 0;
//		for (i = 1; i < e; ++i) {
//			if (i < totalMin) {
//				if (cur == result[i].weight) {
//					count++;
//					break;
//				} else {
//					if (count > 0) {
//						for (int j = i - count; j <= count; i++) {
//							if (result[i].amount > max) {
//								max = result[i].amount;
//							}
//						}
//					} else {
//						max = result[i].weight;
//					}
//					total += max;
//				}
//			}
//		}
//		return total;
//		// System.out.println(result[i].src + " -- " + result[i].dest + " == " +
//		// result[i].weight);
//	}

}
