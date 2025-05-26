package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Point;

public class LabelStar extends Label {	
	
	private Node destination;

	public LabelStar(Node node, boolean marque, double coutRealise, Arc pere, Node destination) {
		super(node, marque, coutRealise, pere);
		this.destination = destination;
	}
	
	private double calculateDistanceDestination() {
		return Point.distance(this.getNode().getPoint(), this.destination.getPoint());
	}
 	
	@Override 
	public double getCoutTotal() {
		return super.getCoutRealise() + this.calculateDistanceDestination();
	}

	@Override
	public int compareTo(Label autre) {
		if (this.getCoutTotal() < autre.getCoutTotal()) {
			return -1;
		} else if (this.getCoutTotal() > autre.getCoutTotal()) {
			return 1;
		} else {
			return 0;
		}
	}
}
