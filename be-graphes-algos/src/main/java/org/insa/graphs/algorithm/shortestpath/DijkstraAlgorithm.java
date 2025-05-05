package org.insa.graphs.algorithm.shortestpath;

import java.util.Arrays;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;

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
        double[] distances = new double[nbNodes];
        Arrays.fill(distances, Double.POSITIVE_INFINITY);
        distances[data.getOrigin().getId()] = 0;
        notifyOriginProcessed(data.getOrigin());
        Arc[] predecessorArcs = new Arc[nbNodes];

        // variable that will contain the solution of the shortest path problem
        ShortestPathSolution solution = null;

        // TODO: implement the Dijkstra algorithm

        // when the algorithm terminates, return the solution that has been found
        return solution;
    }

}
