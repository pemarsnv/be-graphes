package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.algorithm.AbstractSolution.Status;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {
	
	protected ShortestPathData data;
	private Graph graph;
	protected int nbNodes;
	
	protected Label nodeToTab;
	protected int destination;
	
	protected Label[] labels;
	protected BinaryHeap<Label> heap;

    public DijkstraAlgorithm(ShortestPathData data) { 
        super(data);
    }
    
    public void initializeLabels(int nb) {
    	
    	this.labels = new Label[nb];
        this.heap = new BinaryHeap <Label>();
        
        this.nodeToTab = new Label(data.getOrigin(), false, 0, null);
        this.labels[this.nodeToTab.getSommetCourant()] = this.nodeToTab;
        this.heap.insert(nodeToTab); 
        
        this.destination = data.getDestination().getId();
        
    }
    
    public void treatArc(Arc arc) {
    	
        int sommetCourant = arc.getDestination().getId();

        if (labels[sommetCourant] == null) {
        	
            this.nodeToTab = new Label(arc.getDestination(), false, labels[arc.getOrigin().getId()].getCoutRealise() + this.data.getCost(arc), arc);
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

    @Override
    protected ShortestPathSolution doRun() {

        //on récupère les données de l'input
        this.data = getInputData();
        this.graph = data.getGraph();
        this.nbNodes = graph.size();
 
        notifyOriginProcessed(data.getOrigin());
        
        this.initializeLabels(this.nbNodes);
        
        Label currentLabel;

        //Tant qu'il nous reste des labels à traiter et qui n'ont pas été atteints ou marqués...
        
        do {
        	
            // on prend le sommet de plus petite valeur dans le tas, donc la racine du tas, et on le marque
            currentLabel = heap.deleteMin();
			currentLabel.setMarque(true);
            labels[currentLabel.getSommetCourant()].setMarque(true);
            notifyNodeMarked(data.getGraph().get(currentLabel.getSommetCourant()));

        	//on va parcourir tous les arcs et les traiter en conséquence
            for (Arc arc : graph.get(currentLabel.getSommetCourant()).getSuccessors()) {
            	if (this.data.isAllowed(arc)) this.treatArc(arc);
            } 
            
        } while (!heap.isEmpty() && (labels[destination] == null || !labels[destination].getMarque()));

        destination = data.getDestination().getId();
        Status status = Status.OPTIMAL;
        List<Arc> path = new ArrayList<Arc>();
        
      //on regarde si le sommet destination est atteint
        if (labels[destination] == null) {
        	status = Status.INFEASIBLE;
        } else {
        	
        	notifyDestinationReached(data.getGraph().get(destination));
        	
        	Arc currentArc = labels[destination].getPere();
            while (currentArc != null) {
                path.add(currentArc);
                currentArc = labels[currentArc.getOrigin().getId()].getPere();
            }
            Collections.reverse(path);
            
        }

        return new ShortestPathSolution(data, status, new Path(graph, path));
    }
    
}
