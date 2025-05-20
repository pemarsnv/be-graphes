package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.Arc;

public class AStarAlgorithm extends DijkstraAlgorithm {
	
	private LabelStar nodeToTab;

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }
    
    @Override
    public void initiliazeLabels(int nb) {
    	
    	this.labels = new LabelStar[nb];
        for (int i = 0; i < nb; i++) {
            labels[i] = null;
        }
        this.heap = new BinaryHeap <Label>();
        
        nodeToTab = new LabelStar(data.getOrigin(), false, 0, null, data.getOrigin());
        this.insertLabel(nodeToTab);
        heap.insert(nodeToTab);
        destination = data.getDestination().getId();
        
    }
    
    public void insertLabel(LabelStar nodeToTab) {
    	labels[nodeToTab.getSommetCourant()] = nodeToTab;
    }
    
    public void treatArc(Arc arc) {
    	
    	nodeToTab = new LabelStar(arc.getDestination(), false, labels[arc.getOrigin().getId()].getCoutRealise() + arc.getLength(), arc, arc.getOrigin());
        int sommetCourant = nodeToTab.getSommetCourant();
            
        if (labels[sommetCourant] == null) {
         	notifyNodeReached(data.getGraph().get(sommetCourant));
            labels[sommetCourant] = nodeToTab;
            heap.insert(nodeToTab);
       }
            
        if (labels[sommetCourant].getCoutRealise() > nodeToTab.getCoutRealise() && !labels[sommetCourant].getMarque()) {
          	labels[sommetCourant].setCoutRealise(nodeToTab.getCoutRealise());
            labels[sommetCourant].setPere(arc);
        }
    	
    }

}
