package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;
public class Label implements Comparable<Label> {
	
	private Node node;
	private int sommetCourant;
	private boolean marque;
	private double coutRealise;
	private Arc pere;
	// 

	public Label(Node node, boolean marque, double coutRealise, Arc pere) {
		this.node = node;
		this.sommetCourant = node.getId();
		this.marque = false;
		this.coutRealise = coutRealise;
		this.pere = pere;		
	}

	/*
	public Label(int sommetCourant, boolean marque, double coutRealise, Arc pere) {
		this.sommetCourant = sommetCourant;
		this.marque = marque;
		this.coutRealise = coutRealise;
		this.pere = pere;	
	}
	*/
	
	public Node getNode() {
		return this.node;
	}
	
	public int getSommetCourant() {
		return this.sommetCourant;
	}
	
	public boolean getMarque() {
		return this.marque;
	}

	public void setMarque(boolean marque) {
		this.marque = marque;
	}
	
	public double getCoutRealise() {
		return this.coutRealise;
	}
	
	public double getCoutTotal() {
		return this.getCoutRealise();
	}

	public void setCoutRealise(double coutRealise) {
		this.coutRealise = coutRealise;
	}
	
	public Arc getPere() {
		return this.pere;
	}

	public void setPere(Arc pere) {
		this.pere = pere;
	}
	
	public double getCout() {
		return pere.getLength();
	}

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
