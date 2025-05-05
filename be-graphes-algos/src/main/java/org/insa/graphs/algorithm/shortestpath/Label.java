package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Node;

public class Label {
	
	private int sommetCourant;
	private boolean marque;
	private int coutRealise;
	private int pere;
	private Node noeud;
	
	public int getSommetCourant() {
		return this.sommetCourant;
	}
	
	public boolean getMarque() {
		return this.marque;
	}
	
	public int getCoutRealise() {
		return this.coutRealise;
	}
	
	public int getPere() {
		return this.pere;
	}
	
	public int getCout() {
		return this.coutRealise;
	}
	
	public void setNoeud(Node node) {
		this.noeud = node;
	}
	
	public Node getNoeud() {
		return this.noeud;
	}

}
