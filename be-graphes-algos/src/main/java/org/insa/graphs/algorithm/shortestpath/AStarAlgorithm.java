package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.algorithm.AbstractInputData.Mode;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.Arc;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }
    
    @Override
    public void initializeLabels(int nb) {
    	
    	this.labels = new LabelStar[nb];
        this.heap = new BinaryHeap<Label>();
        
        if (this.data.getMode().equals(Mode.TIME)) {
        	this.nodeToTab = new LabelStar(data.getOrigin(), false, 0, null, 
        			data.getOrigin(), this.data.getMaximumSpeed());
        } else {
        	this.nodeToTab = new LabelStar(data.getOrigin(), false, 0, null, data.getOrigin());
        }
        
        this.labels[this.nodeToTab.getSommetCourant()] = this.nodeToTab;
        this.heap.insert(nodeToTab); 
        
        this.destination = data.getDestination().getId();
        
    }
    
    @Override
    public void treatArc(Arc arc) {
    	
        int sommetCourant = arc.getDestination().getId();

        if (labels[sommetCourant] == null) {
        	
        	if (this.data.getMode().equals(Mode.TIME)) {
        		this.nodeToTab = new LabelStar(arc.getDestination(), false, 
        				labels[arc.getOrigin().getId()].getCoutRealise() + this.data.getCost(arc), arc, 
        				data.getDestination(), this.data.getMaximumSpeed());
        	} else {
        		this.nodeToTab = new LabelStar(arc.getDestination(), false, 
        				labels[arc.getOrigin().getId()].getCoutRealise() + this.data.getCost(arc), arc, 
        				data.getDestination());
        	}
        	
        	notifyNodeReached(data.getGraph().get(sommetCourant));
            this.labels[sommetCourant] = nodeToTab;
            this.heap.insert(nodeToTab);
            
        } else if (!labels[sommetCourant].getMarque() && labels[sommetCourant].getCoutRealise() > labels[arc.getOrigin().getId()].getCoutRealise() + this.data.getCost(arc)) {
            
        	heap.remove(labels[sommetCourant]);
          	labels[sommetCourant].setCoutRealise(labels[arc.getOrigin().getId()].getCoutRealise() + this.data.getCost(arc));
          	labels[sommetCourant].setPere(arc);
            heap.insert(labels[sommetCourant]);
            
        }

    }
   

}
