package org.insa.graphs.algorithm.shortestpath;

import static org.junit.Assert.*;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.junit.Test;

public class ShortestPathTest {
	
	protected Graph graph;
	private ShortestPathData data;
	private Node origin;
	private Node destination;
	
	private AlgorithmSupplier supplier;
	
	public ShortestPathTest (AlgorithmSupplier supplier) {
        this.supplier = supplier;
    }

	/*
	 * Teste le résultat de l'algorithme Dijkstra pour le chemin le plus court
	 * parmi tous les chemins disponibles
	 */
	@Test
	public void testShortestPathAll() {
		
		this.origin = graph.get(5);
		this.destination = graph.get(413);
		
		this.data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(0));
		
		//On fait tourner l'algo de Dijkstra à tester
		ShortestPathAlgorithm algo = supplier.getAlgorithm(data);
		ShortestPathSolution solutionD = algo.run();
		
		//On fait tourner l'algo de BF pour avoir la bonne solution 
		BellmanFordAlgorithm bellman = new BellmanFordAlgorithm(data);
		ShortestPathSolution solutionB = bellman.run();
		
		//On vérifie que la soltuion est bien possible et 
		//qu'elle est la même que celle trouvée par BellmanFord 
		
		assertEquals(solutionD.getStatus(), Status.OPTIMAL);
		assertEquals(solutionD.getPath().getArcs(), solutionB.getPath().getArcs());
			
	}
	
	/*
	 * Teste le résultat de l'algorithme Dijkstra pour le chemin le plus court
	 * parmi les chemins en voiture, et vérifie que le résultat est bien différent
	 */
	@Test
	public void testShortestPathCar() {
		
		this.origin = graph.get(66);
		this.destination = graph.get(207);
		
		this.data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(1));
		
		//On fait tourner l'algo de Dijkstra à tester
		ShortestPathAlgorithm algo = supplier.getAlgorithm(data);
		ShortestPathSolution solutionD = algo.run();
		
		//On fait tourner l'algo de BF pour avoir la bonne solution 
		BellmanFordAlgorithm bellman = new BellmanFordAlgorithm(data);
		ShortestPathSolution solutionB = bellman.run();
		
		//On vérifie que la soltuion est bien possible et 
		//qu'elle est la même que celle trouvée par BellmanFord 
		assertEquals(solutionD.getStatus(), Status.OPTIMAL);
		assertEquals(solutionD.getPath().getArcs(), solutionB.getPath().getArcs());
			
	}
	
	/*
	 * Teste que le résultat de l'algorithme de Dijkstra soit bien différent selon le type de chemins à 
	 * prendre en Shortest si on est sur un chemin différent avec uniquement des routes en voiture 
	 */
	@Test
	public void testShortestPathAllvsCar() {
		
		this.origin = graph.get(66);
		this.destination = graph.get(207);
		
		//On fait tourner l'algo de Dijkstra en shortest all
		this.data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(0));
		ShortestPathAlgorithm algo = supplier.getAlgorithm(data);
		ShortestPathSolution solutionA = algo.run();
		
		//On fait tourner l'algo de Dijkstra en shortest cars only
		this.data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(1));
		algo = supplier.getAlgorithm(data);
		ShortestPathSolution solutionC = algo.run();
		
		//On vérifie que la soltuion est bien possible et 
		//qu'elle est la même que celle trouvée par BellmanFord 
		assertNotEquals(solutionA.getPath().getArcs(), solutionC.getPath().getArcs());
			
	}
	
	/*
	 * Teste qu'un chemin qui n'est pas faisable a bien le statut INFEASIBLE
	 */
	@Test
	public void testNoPath() {
		
		this.origin = graph.get(100);
		this.destination = graph.get(1282);
		
		this.data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(0));
		
		//On fait tourner l'algo de Dijkstra à tester
		ShortestPathAlgorithm algo = supplier.getAlgorithm(data);
		ShortestPathSolution solutionD = algo.run();
		
		//On vérifie que la solution est bien impossible
		assertEquals(solutionD.getStatus(), Status.INFEASIBLE);
		
	}
	
	@Test
	public void testNoPathCar() {
		
		this.origin = graph.get(5);
		this.destination = graph.get(413);
		
		this.data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(1));
		
		//On fait tourner l'algo de Dijkstra à tester
		ShortestPathAlgorithm algo = supplier.getAlgorithm(data);
		ShortestPathSolution solutionD = algo.run();
		
		//On vérifie que la solution est bien impossible
		assertEquals(solutionD.getStatus(), Status.INFEASIBLE);
		
	}
	
	/*
	 * Teste le résultat de l'algorithme Dijkstra pour le chemin le plus court avec tous les chemins
	 */
	@Test
	public void testFastestPathAll() {
		
		this.origin = graph.get(5);
		this.destination = graph.get(413);
		
		this.data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(2));
		
		//On fait tourner l'algo de Dijkstra à tester
		ShortestPathAlgorithm algo = supplier.getAlgorithm(data);
		ShortestPathSolution solutionD = algo.run();
		
		//On fait tourner l'algo de BF pour avoir la bonne solution 
		BellmanFordAlgorithm bellman = new BellmanFordAlgorithm(data);
		ShortestPathSolution solutionB = bellman.run();
		
		//On vérifie que la soltuion est bien possible et 
		//qu'elle est la même que celle trouvée par BellmanFord 
		assertEquals(solutionD.getStatus(), Status.OPTIMAL);
		System.out.println(solutionD.getPath().getArcs());
		System.out.println(solutionB.getPath().getArcs());
		assertEquals(solutionD.getPath().getArcs(), solutionB.getPath().getArcs());
			
	}
	
	/*
	 * Teste le résultat de l'algorithme Dijkstra pour le chemin le plus court avec tous les chemins
	 */
	@Test
	public void testFastestPathPedestrian() {
		
		this.origin = graph.get(878);
		this.destination = graph.get(252);
		
		this.data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(3));
		
		//On fait tourner l'algo de Dijkstra à tester
		ShortestPathAlgorithm algo = supplier.getAlgorithm(data);
		ShortestPathSolution solutionD = algo.run();
		
		//On fait tourner l'algo de BF pour avoir la bonne solution 
		BellmanFordAlgorithm bellman = new BellmanFordAlgorithm(data);
		ShortestPathSolution solutionB = bellman.run();
		
		//On vérifie que la soltuion est bien possible et 
		//qu'elle est la même que celle trouvée par BellmanFord 
		assertEquals(solutionD.getStatus(), Status.OPTIMAL);
		System.out.println(solutionD.getPath().getArcs());
		System.out.println(solutionB.getPath().getArcs());
		assertEquals(solutionD.getPath().getArcs(), solutionB.getPath().getArcs());
			
	}
	
	/*
	 * Teste que le résultat de l'algorithme de Dijkstra soit bien différent selon le type de chemins à 
	 * prendre en Shortest si on est sur un chemin différent avec uniquement des routes en voiture 
	 */
	@Test
	public void testFastestPathAllvsPedestrian() {
		
		this.origin = graph.get(878);
		this.destination = graph.get(252);
		
		//On fait tourner l'algo de Dijkstra en shortest all
		this.data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(2));
		ShortestPathAlgorithm algo = supplier.getAlgorithm(data);
		ShortestPathSolution solutionA = algo.run();
		
		//On fait tourner l'algo de Dijkstra en shortest cars only
		this.data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(3));
		algo = supplier.getAlgorithm(data);
		ShortestPathSolution solutionC = algo.run();
		
		//On vérifie que la soltuion est bien possible et 
		//qu'elle est la même que celle trouvée par BellmanFord 
		assertNotEquals(solutionA.getPath().getArcs(), solutionC.getPath().getArcs());
			
	}
	
	@Test
	public void testNoPathPedestrian() {
		
		this.origin = graph.get(731);
		this.destination = graph.get(413);
		
		this.data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(1));
		
		//On fait tourner l'algo de Dijkstra à tester
		ShortestPathAlgorithm algo = supplier.getAlgorithm(data);
		ShortestPathSolution solutionD = algo.run();
		
		//On vérifie que la solution est bien impossible
		assertEquals(solutionD.getStatus(), Status.INFEASIBLE);
		
	}
	
	@Test
	public void testShortestFastestDifferent() {
		
		this.origin = graph.get(66);
		this.destination = graph.get(704);
		
		//On fait tourner l'algo de Dijkstra à tester en shortest
		this.data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(0));
		ShortestPathAlgorithm algo = supplier.getAlgorithm(data);
		ShortestPathSolution solutionS = algo.run();
		
		//On fait tourner l'algo de Dijkstra à tester en fastest
		this.data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(2));
		algo = supplier.getAlgorithm(data);
		ShortestPathSolution solutionF = algo.run();
		
		//On vérifie que la soltuion est bien possible et 
		//qu'elle est la même que celle trouvée par BellmanFord 
		assertNotEquals(solutionS.getPath().getArcs(), solutionF.getPath().getArcs());
			
	}

}
