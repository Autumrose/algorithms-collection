import java.util.*;

// Represents a state of the pouring problem.
public class State {

	private int vol10; // Liters in 10-liter container
	private int vol7; // Liters in 7-liter container
	private int vol4; // Liters in 4-liter container

	// Constructs start state
	public State() {
		this(0, 7, 4);
	}

	// Constructs the specified state
	private State(int vol10, int vol7, int vol4) {
		this.vol10 = vol10;
		this.vol7 = vol7;
		this.vol4 = vol4;
	}

	// Returns list of all states reachable with one pouring
	public List<State> nextStates() {
		LinkedList<State> states = new LinkedList<State>();
		if (vol10 > 0 && vol7 < 7) {
			int poured = Math.min(vol10, 7 - vol7);
			states.add(new State(vol10 - poured, vol7 + poured, vol4));
		}
		if (vol10 > 0 && vol4 < 4) {
			int poured = Math.min(vol10, 4 - vol4);
			states.add(new State(vol10 - poured, vol7, vol4 + poured));
		}
		if (vol7 > 0 && vol10 < 10) {
			int poured = Math.min(vol7, 10 - vol10);
			states.add(new State(vol10 + poured, vol7 - poured, vol4));
		}
		if (vol7 > 0 && vol4 < 4) {
			int poured = Math.min(vol7, 4 - vol4);
			states.add(new State(vol10, vol7 - poured, vol4 + poured));
		}
		if (vol4 > 0 && vol10 < 10) {
			int poured = Math.min(vol4, 10 - vol10);
			states.add(new State(vol10 + poured, vol7, vol4 - poured));
		}
		if (vol4 > 0 && vol7 < 7) {
			int poured = Math.min(vol4, 7 - vol7);
			states.add(new State(vol10, vol7 + poured, vol4 - poured));
		}
		return states;
	}

	// Reports whether this is a final state
	public boolean isFinalState() {
		return vol7 == 2 || vol4 == 2;
	}

	// Converts this state to a string
	@Override
	public String toString() {
		return "[" + vol10 + " " + vol7 + " " + vol4 + "]";
	}

	// Defines state equality
	@Override
	public boolean equals(Object o) {
		if (o instanceof State) {
			State s = (State) o;
			return (vol10 == s.vol10) && (vol7 == s.vol7) && (vol4 == s.vol4);
		} else {
			return false;
		}
	}

	// Defines state hash code
	@Override
	public int hashCode() {
		return vol10 << 20 + vol7 << 10 + vol4;
	}

}
