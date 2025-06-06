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
	
	protected Graph graph2;
	
	private AlgorithmSupplier supplier;
	
	public ShortestPathTest (AlgorithmSupplier supplier) {
        this.supplier = supplier;
    }

	/*
	 * Teste le résultat de l'algorithme pour le chemin le plus COURT
	 * parmi tous les chemins disponibles
	 */
	@Test
	public void testShortestPathAll() {
		
		//avec la carte de l'insa
		
		this.origin = this.graph.get(5);
		this.destination = this.graph.get(413);
		
		this.data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(0));
		
		//on fait tourner l'algo à tester
		ShortestPathAlgorithm algo = supplier.getAlgorithm(data);
		ShortestPathSolution solution = algo.run();
		
		//on fait tourner l'algo de BF pour avoir la bonne solution 
		BellmanFordAlgorithm bellman = new BellmanFordAlgorithm(data);
		ShortestPathSolution solutionB = bellman.run();
		
		//on vérifie que la solution est bien possible et 
		//qu'elle est la même que celle trouvée par BellmanFord 
		
		assertEquals(solution.getStatus(), Status.OPTIMAL);
		assertEquals(solution.getPath().getArcs(), solutionB.getPath().getArcs());
		
		//même chose avec la carte de la haute garonne
		
		this.origin = this.graph2.get(114824);
		this.destination = this.graph2.get(19626);
		
		this.data = new ShortestPathData(graph2, origin, destination, ArcInspectorFactory.getAllFilters().get(0));
		
		algo = supplier.getAlgorithm(data);
		solution = algo.run();
		
		bellman = new BellmanFordAlgorithm(data);
		solutionB = bellman.run();
		
		assertEquals(solution.getStatus(), Status.OPTIMAL);
		assertEquals(solution.getPath().getArcs(), solutionB.getPath().getArcs());
			
	}
	
	/*
	 * Teste le résultat de l'algorithme pour le chemin le plus COURT
	 * en voiture parmi tous les chemins disponibles
	 */
	@Test
	public void testShortestPathCar() {
		
		//avec la carte de l'insa
		
		this.origin = this.graph.get(66);
		this.destination = this.graph.get(207);
		
		this.data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(1));

		ShortestPathAlgorithm algo = this.supplier.getAlgorithm(data);
		ShortestPathSolution solution = algo.run();
		
		BellmanFordAlgorithm bellman = new BellmanFordAlgorithm(data);
		ShortestPathSolution solutionB = bellman.run();
		
		assertEquals(solution.getStatus(), Status.OPTIMAL);
		assertEquals(solution.getPath().getArcs(), solutionB.getPath().getArcs());
		
		//même chose avec la carte de la haute garonne
		
		this.origin = this.graph2.get(114824);
		this.destination = this.graph2.get(19626);
		
		this.data = new ShortestPathData(graph2, origin, destination, ArcInspectorFactory.getAllFilters().get(1));
		
		algo = this.supplier.getAlgorithm(data);
		solution = algo.run();
		
		bellman = new BellmanFordAlgorithm(data);
		solutionB = bellman.run();
		
		assertEquals(solution.getStatus(), Status.OPTIMAL);
		assertEquals(solution.getPath().getArcs(), solutionB.getPath().getArcs());
			
	}
	
	/*
	 * Teste que le résultat de l'algorithme en mode shortest soit bien différent selon le type de chemins à 
	 * prendre (voiture, tout) pour une trajectoire qui serait différente uniquement en voiture 
	 */
	@Test
	public void testShortestPathAllvsCar() {
		
		//avec la carte de l'insa
		
		this.origin = graph.get(66);
		this.destination = graph.get(207);
		
		//on fait tourner l'algo en shortest all
		this.data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(0));
		ShortestPathAlgorithm algo = supplier.getAlgorithm(data);
		ShortestPathSolution solutionA = algo.run();
		
		//on fait tourner l'algo en shortest cars only
		this.data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(1));
		algo = supplier.getAlgorithm(data);
		ShortestPathSolution solutionC = algo.run();
		
		//on vérifie que les solutions sont bien différentes
		assertNotEquals(solutionA.getPath().getArcs(), solutionC.getPath().getArcs());
		
		//même chose avec la carte de la haute garonne
		
		this.origin = this.graph2.get(114824);
		this.destination = this.graph2.get(19626);
		
		this.data = new ShortestPathData(graph2, origin, destination, ArcInspectorFactory.getAllFilters().get(0));
		algo = supplier.getAlgorithm(data);
		solutionA = algo.run();
		
		this.data = new ShortestPathData(graph2, origin, destination, ArcInspectorFactory.getAllFilters().get(1));
		algo = supplier.getAlgorithm(data);
		solutionC = algo.run();
		
		assertNotEquals(solutionA.getPath().getArcs(), solutionC.getPath().getArcs());
			
	}
	
	/*
	 * Teste qu'un chemin qui n'est pas faisable a bien le statut INFEASIBLE
	 */
	@Test
	public void testNoPath() {
		
		//avec la carte de l'insa
		
		this.origin = graph.get(100);
		this.destination = graph.get(1282);
		
		this.data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(0));
		
		//on fait tourner l'algorithme
		ShortestPathAlgorithm algo = supplier.getAlgorithm(data);
		ShortestPathSolution solution = algo.run();
		
		//on vérifie que le statut de la solution est bien INFEASIBLE
		assertEquals(solution.getStatus(), Status.INFEASIBLE);
		
		//avec la carte de la haute garonne
		
		this.origin = graph2.get(68269);
		this.destination = graph2.get(135933);
				
		this.data = new ShortestPathData(graph2, origin, destination, ArcInspectorFactory.getAllFilters().get(0));

		algo = supplier.getAlgorithm(data);
		solution = algo.run();
		
		assertEquals(solution.getStatus(), Status.INFEASIBLE);
		
	}
	
	/*
	 * Teste qu'un chemin qui n'est pas faisable en voiture a bien le statut INFEASIBLE
	 * si on lance l'algorithme en Shortest Car Only
	 */
	@Test
	public void testNoPathCar() {
		
		//avec la carte de l'insa
		
		this.origin = graph.get(5);
		this.destination = graph.get(413);
		
		this.data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(1));

		ShortestPathAlgorithm algo = supplier.getAlgorithm(data);
		ShortestPathSolution solution = algo.run();
		
		assertEquals(solution.getStatus(), Status.INFEASIBLE);
		
		//avec la carte de la haute garonne
		
		this.origin = graph2.get(68269);
		this.destination = graph2.get(135933);
		
		this.data = new ShortestPathData(graph2, origin, destination, ArcInspectorFactory.getAllFilters().get(1));
		
		algo = supplier.getAlgorithm(data);
		solution = algo.run();
		
		assertEquals(solution.getStatus(), Status.INFEASIBLE);
		
	}
	
	/*
	 * Teste le résultat de l'algorithme pour le chemin le plus RAPIDE
	 * parmi tous les chemins disponibles
	 */
	@Test
	public void testFastestPathAll() {
		
		this.origin = graph.get(5);
		this.destination = graph.get(413);
		
		this.data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(2));
		
		//on fait tourner l'algo à tester
		ShortestPathAlgorithm algo = supplier.getAlgorithm(data);
		ShortestPathSolution solution = algo.run();
		
		//on fait tourner l'algo de BF pour avoir la bonne solution 
		BellmanFordAlgorithm bellman = new BellmanFordAlgorithm(data);
		ShortestPathSolution solutionB = bellman.run();
		
		//on vérifie que la solution est bien possible et 
		//qu'elle est la même que celle trouvée par BellmanFord 
		assertEquals(solution.getStatus(), Status.OPTIMAL);
		assertEquals(solution.getPath().getArcs(), solutionB.getPath().getArcs());
		
		//même chose avec la carte de la haute garonne
		
		this.origin = this.graph2.get(114824);
		this.destination = this.graph2.get(19626);
				
		this.data = new ShortestPathData(graph2, origin, destination, ArcInspectorFactory.getAllFilters().get(2));
				
		algo = supplier.getAlgorithm(data);
		solution = algo.run();
				
		bellman = new BellmanFordAlgorithm(data);
		solutionB = bellman.run();
				
		assertEquals(solution.getStatus(), Status.OPTIMAL);
		assertEquals(solution.getPath().getArcs(), solutionB.getPath().getArcs());
			
	}
	
	/*
	 * Teste le résultat de l'algorithme pour le chemin le plus RAPIDE
	 * parmi tous les chemins possibles pour un piéton
	 */
	@Test
	public void testFastestPathPedestrian() {
		
		this.origin = graph.get(878);
		this.destination = graph.get(252);
		
		this.data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(3));
		
		//On fait tourner l'algo à tester
		ShortestPathAlgorithm algo = supplier.getAlgorithm(data);
		ShortestPathSolution solution = algo.run();
		
		//On fait tourner l'algo de BF pour avoir la bonne solution 
		BellmanFordAlgorithm bellman = new BellmanFordAlgorithm(data);
		ShortestPathSolution solutionB = bellman.run();
		
		//On vérifie que la solution est bien possible et 
		//qu'elle est la même que celle trouvée par BellmanFord 
		assertEquals(solution.getStatus(), Status.OPTIMAL);
		assertEquals(solution.getPath().getArcs(), solutionB.getPath().getArcs());
		
		//même chose avec la carte de la haute garonne
		
		this.origin = this.graph2.get(114824);
		this.destination = this.graph2.get(19626);
						
		this.data = new ShortestPathData(graph2, origin, destination, ArcInspectorFactory.getAllFilters().get(3));
				
		algo = supplier.getAlgorithm(data);
		solution = algo.run();
						
		bellman = new BellmanFordAlgorithm(data);
		solutionB = bellman.run();
						
		assertEquals(solution.getStatus(), Status.OPTIMAL);
		assertEquals(solution.getPath().getArcs(), solutionB.getPath().getArcs());
			
	}
	
	/*
	 * Teste que le résultat de l'algorithme en mode fastest soit bien différent selon le type de chemins à 
	 * prendre (piéton, tout) pour une trajectoire qui serait différente pour quelqu'un uniquement à pied
	 */
	@Test
	public void testFastestPathAllvsPedestrian() {
		
		//avec la carte de l'insa
		
		this.origin = graph.get(878);
		this.destination = graph.get(252);
		
		//On fait tourner l'algo en fastest all
		this.data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(2));
		ShortestPathAlgorithm algo = supplier.getAlgorithm(data);
		ShortestPathSolution solutionA = algo.run();
		
		//On fait tourner l'algo en fastest pedestrian only
		this.data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(3));
		algo = supplier.getAlgorithm(data);
		ShortestPathSolution solutionP = algo.run();
		
		//on vérifie que les deux solutions sont différentes
		assertNotEquals(solutionA.getPath().getArcs(), solutionP.getPath().getArcs());
		
		//même chose avec la carte de la haute garonne
		
		this.origin = graph2.get(878);
		this.destination = graph2.get(252);
		
		this.data = new ShortestPathData(graph2, origin, destination, ArcInspectorFactory.getAllFilters().get(2));
		algo = supplier.getAlgorithm(data);
		solutionA = algo.run();
		
		this.data = new ShortestPathData(graph2, origin, destination, ArcInspectorFactory.getAllFilters().get(3));
		algo = supplier.getAlgorithm(data);
		solutionP = algo.run();
	
		assertNotEquals(solutionA.getPath().getArcs(), solutionP.getPath().getArcs());
			
	}
	
	/*
	 * Teste qu'un chemin qui n'est pas faisable à pied a bien le statut INFEASIBLE
	 * si on lance l'algorithme en Fastest Pedestrian Only
	 */
	@Test
	public void testNoPathPedestrian() {
		
		this.origin = graph.get(731);
		this.destination = graph.get(413);
		
		this.data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(3));
		
		//On fait tourner l'algo de Dijkstra à tester
		ShortestPathAlgorithm algo = supplier.getAlgorithm(data);
		ShortestPathSolution solution = algo.doRun();
		
		//On vérifie que la solution est bien impossible
		assertEquals(solution.getStatus(), Status.INFEASIBLE);
	
		this.origin = graph2.get(68269);
		this.destination = graph2.get(135933);
		
		this.data = new ShortestPathData(graph2, origin, destination, ArcInspectorFactory.getAllFilters().get(3));
		
		//On fait tourner l'algo de Dijkstra à tester
		algo = supplier.getAlgorithm(data);
		solution = algo.run();
		
		//On vérifie que la solution est bien impossible
		assertEquals(solution.getStatus(), Status.INFEASIBLE);
		
	}
	
	/*
	 * Teste que le résultat de l'algorithme en mode fastest et shortest pour une trajectoire qui 
	 * serait différente selon le besoin exprimé ici 
	 */
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
		
		//même chose avec la haute garonne
		
		this.origin = graph2.get(114824);
		this.destination = graph2.get(19626);

		this.data = new ShortestPathData(graph2, origin, destination, ArcInspectorFactory.getAllFilters().get(0));
		algo = supplier.getAlgorithm(data);
		solutionS = algo.run();
		
		//On fait tourner l'algo de Dijkstra à tester en fastest
		this.data = new ShortestPathData(graph2, origin, destination, ArcInspectorFactory.getAllFilters().get(2));
		algo = supplier.getAlgorithm(data);
		solutionF = algo.run();
		
		//On vérifie que la soltuion est bien possible et 
		//qu'elle est la même que celle trouvée par BellmanFord 
		assertNotEquals(solutionS.getPath().getArcs(), solutionF.getPath().getArcs());
			
	}

}
