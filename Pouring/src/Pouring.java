import java.util.*;

public class Pouring {
	
	public static void main (String[] args) {
		
		// Determine if a final state is reachable using DFS.
		try {
			dsolve(new State(), new HashSet<State>());
			System.out.println("No path");
		}
		catch (RuntimeException e) {
			System.out.println("Path exists");
		}
		
		// Find a path to a final state using BFS.
		HashMap<State,State> prev = new HashMap<State,State>();
		State s = bsolve(new State(), prev);
		
		// Print out the path from the root to the final state
		if (s != null) {
			LinkedList<State> result = new LinkedList<State>();
			result.addFirst(s);
			while (prev.get(s) != s) {
				s = prev.get(s);
				result.addFirst(s);
			}
			for (State r: result) {
				System.out.println(r);
			}
		}
	}
	
	// Uses DFS to determine if there's a path from s to a 
	// final state. If there is, throws a RuntimeException.
	public static void dsolve (State s, HashSet<State> visited) {
		visited.add(s);
		if (s.isFinalState()) throw new RuntimeException();
		for (State r: s.nextStates()) {
			if (!visited.contains(r)) {
				dsolve(r, visited);
			}
		}
	}
	
	
	// Does BFS beginning at s, filling out the prev array as it 
	// goes.  If a final state is encountered, returns it.  
	// Otherwise, returns null.
	public static State bsolve (State start, 
			                    HashMap<State,State> prev) {
		prev.put(start, start);
		LinkedList<State> queue = new LinkedList<State>();
		queue.addLast(start);
		while (!queue.isEmpty()) {
			State s = queue.removeFirst();
			for (State r: s.nextStates()) {
				if (prev.get(r) == null) {
					queue.addLast(r);
					prev.put(r, s);
					if (r.isFinalState()) {
						return r;
					}
				}
			}
		}
		return null;
	}

}
