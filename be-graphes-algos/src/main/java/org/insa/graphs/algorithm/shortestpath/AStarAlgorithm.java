package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.insa.graphs.algorithm.AbstractInputData.Mode;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Path;

public class AStarAlgorithm extends DijkstraAlgorithm {
	

	private LabelStar nodeToTab;
	protected ShortestPathData data;
	private Graph graph;
	private int nbNodes;
	protected int destination;
	
	protected LabelStar[] labels;
	protected BinaryHeap<Label> heap;

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }
    
    @Override
    public void initiliazeLabels(int nb) {
    	
    	this.labels = new LabelStar[nb];
        for (int i = 0; i < nb; i++) {
            labels[i] = null;
        }
        this.heap = new BinaryHeap<Label>();
        
        nodeToTab = new LabelStar(data.getOrigin(), false, 0, null, data.getOrigin());
        this.insertLabel(nodeToTab);
        heap.insert(nodeToTab);
        destination = data.getDestination().getId();
        
    }
    
    public void insertLabel(LabelStar nodeToTab) {
    	labels[nodeToTab.getSommetCourant()] = nodeToTab;
    }
    
    public void treatArc(Arc arc) {
        int sommetCourant = arc.getDestination().getId();
        if (labels[sommetCourant] == null) {
            if (this.data.getMode().equals(Mode.TIME)) {
        		this.nodeToTab = new LabelStar(arc.getDestination(), false, labels[arc.getOrigin().getId()].getCoutRealise() + arc.getMinimumTravelTime(), arc, data.getDestination());
         	} else {
        		this.nodeToTab = new LabelStar(arc.getDestination(), false, labels[arc.getOrigin().getId()].getCoutRealise() + arc.getLength(), arc, data.getDestination());
         	}
            notifyNodeReached(data.getGraph().get(sommetCourant));
            labels[sommetCourant] = nodeToTab;
            heap.insert(nodeToTab);
       } 
        else if (labels[sommetCourant].getCoutRealise() > labels[arc.getOrigin().getId()].getCoutRealise() + arc.getLength()) {
          	labels[sommetCourant].setCoutRealise(labels[arc.getOrigin().getId()].getCoutRealise() + arc.getLength());
            labels[sommetCourant].setPere(arc);
        }
    	
    	
    }

    
    @Override
    protected ShortestPathSolution doRun() { // probleme au remove de la heap, peut etre refaire entierement l'algo

        //On récupère les données de l'input
        this.data = getInputData();
        this.graph = data.getGraph();
        this.nbNodes = graph.size();
 
        notifyOriginProcessed(data.getOrigin());
        
        //On crée un tableau et un BinaryHeap pour stocker les labels
        this.initiliazeLabels(this.nbNodes);
        
        //On prend et on traite la racine du tas 
        Label currentLabel;

        //Tant qu'il nous reste des labels à traiter et qu'on a pas atteint et marqué la destination...
        do {
            // on prend le sommet de plus petite valeur dans le tas, donc la racine du tas
            // on le marque comme traité
            currentLabel = heap.deleteMin();
			currentLabel.setMarque(true);
            labels[currentLabel.getSommetCourant()].setMarque(true);
            //On notifie le sommet traité
			notifyNodeMarked(data.getGraph().get(currentLabel.getSommetCourant()));

        	//On va parcourir tous les arcs
            for (Arc arc : graph.get(currentLabel.getSommetCourant()).getSuccessors()) {
            	if (data.isAllowed(arc)) this.treatArc(arc);
            }         
        } while (!heap.isEmpty() && (labels[destination] == null || !labels[destination].getMarque()));

        //On regarde si le sommet destination est atteint
        destination = data.getDestination().getId();
        Status status = Status.OPTIMAL;
        Arc currentArc;
        List<Arc> path = new ArrayList<Arc>();
        
        if (labels[destination] == null) {
        	status = Status.INFEASIBLE;
        } else {
        	
        	notifyDestinationReached(data.getGraph().get(destination));
        	
        	currentArc = labels[destination].getPere();

            while (currentArc != null) {
                path.add(currentArc);
                currentArc = labels[currentArc.getOrigin().getId()].getPere();
            }

            //reverse the path
            Collections.reverse(path);
            
        }
        
        // variable that will contain the solution of the shortest path problem
        ShortestPathSolution solution = new ShortestPathSolution(data, status, new Path(graph, path));

        // when the algorithm terminates, return the solution that has been found
        return solution;
    }
}
