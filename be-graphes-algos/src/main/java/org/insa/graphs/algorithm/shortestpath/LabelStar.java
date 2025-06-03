package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Point;

public class LabelStar extends Label {	
	
	private Node destination;
	private int maxSpeed;

	public LabelStar(Node node, boolean marque, double coutRealise, Arc pere, Node destination, int maxSpeed) {
		super(node, marque, coutRealise, pere);
		this.destination = destination;
		this.maxSpeed = maxSpeed;
	}
	
	private double calculateDistanceDestination() {
		if (this.maxSpeed < 0) {
			return Point.distance(this.getNode().getPoint(), this.destination.getPoint());
		} else {
			return Point.distance(this.getNode().getPoint(), this.destination.getPoint()) / this.maxSpeed;
		}
		
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
