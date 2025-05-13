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

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {

        // retrieve data from the input problem (getInputData() is inherited from the
        // parent class ShortestPathAlgorithm)
        final ShortestPathData data = getInputData();
        Graph graph = data.getGraph();
        int nbNodes = graph.size();

        // donnees pour l'algorithme
        Label nodeToTab;
        int destination;

        notifyOriginProcessed(data.getOrigin());

        Label[] labels = new Label[nbNodes];
        for (int i = 0; i < nbNodes; i++) {
            labels[i] = null;
        }

        //create a binary heap to store the labels
        BinaryHeap<Label> heap = new BinaryHeap <Label>();
        nodeToTab = new Label(data.getOrigin().getId(), false, 0, null);
        labels[nodeToTab.getSommetCourant()] = nodeToTab;
        heap.insert(nodeToTab);
        
        destination = data.getDestination().getId();
        
        // on prend le sommet de plus petite valeur dans le tas, donc la racine du tas
        Label currentLabel = heap.deleteMin();
        currentLabel.setMarque(true); // on le marque comme traité

        while (!heap.isEmpty() && (labels == null || !labels[destination].getMarque())) {
        	
            for (Arc arc : graph.get(currentLabel.getSommetCourant()).getSuccessors()) {
                nodeToTab = new Label(arc.getDestination().getId(), false, labels[arc.getOrigin().getId()].getCoutRealise() + arc.getLength(), arc);
                int sommetCourant = nodeToTab.getSommetCourant();
                if (labels[sommetCourant] == null) {
                    labels[sommetCourant] = nodeToTab;
                    heap.insert(nodeToTab);
                }
                if (labels[sommetCourant].getCoutRealise() > nodeToTab.getCoutRealise() && !labels[sommetCourant].getMarque()) {
                    labels[sommetCourant].setCoutRealise(nodeToTab.getCoutRealise());
                    labels[sommetCourant].setPere(arc);
                }
            }    
            
            // on prend le sommet de plus petite valeur dans le tas, donc la racine du tas
             // on le marque comme traité
            currentLabel = heap.deleteMin();
			currentLabel.setMarque(true);
            
        }

        //On regarde si le sommet destination est atteint
        destination = data.getDestination().getId();
        Status status = Status.OPTIMAL;
        Arc currentArc;
        List<Arc> path = new ArrayList<Arc>();;
        
        if (labels[destination] == null) {
        	status = Status.INFEASIBLE;
        } else {
        	
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
