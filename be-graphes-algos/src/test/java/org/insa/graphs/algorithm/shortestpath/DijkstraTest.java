package org.insa.graphs.algorithm.shortestpath;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.GraphReader;
import org.junit.Before;
import org.junit.Test;

public class DijkstraTest {
	
	private ShortestPathTest tests;
	
	@Before
	public void beforeAll() throws IOException {
		
		this.tests = new ShortestPathTest(DijkstraAlgorithm::new);

        String mapName = "../maps/insa.mapgr";
		GraphReader reader = new BinaryGraphReader(new DataInputStream(
				new BufferedInputStream(new FileInputStream(mapName))));
		this.tests.graph = reader.read();
		
		String mapName2 = "../maps/haute-garonne.mapgr";
		GraphReader reader2 = new BinaryGraphReader(new DataInputStream(
				new BufferedInputStream(new FileInputStream(mapName2))));
		this.tests.graph2 = reader2.read();
		
	}

	/*
	 * Teste le résultat de l'algorithme Dijkstra pour le chemin le plus court avec tous les chemins
	 */
	@Test
	public void testShortestPathAll() {
		
		this.tests.testShortestPathAll();
			
	}
	
	/*
	 * Teste le résultat de l'algorithme Dijkstra pour le chemin le plus court
	 * parmi les chemins en voiture, et vérifie que le résultat est bien différent
	 */
	@Test
	public void testShortestPathCar() {
		
		this.tests.testShortestPathCar();
			
	}
	
	/*
	 * Teste que le résultat de l'algorithme de Dijkstra soit bien différent selon le type de chemins à 
	 * prendre en Shortest si on est sur un chemin différent avec uniquement des routes en voiture 
	 */
	@Test
	public void testShortestPathAllvsCar() {
		
		this.tests.testShortestPathAllvsCar();
			
	}
	
	/*
	 * Teste qu'un chemin qui n'est pas faisable a bien le statut INFEASIBLE
	 */
	@Test
	public void testNoPath() {
		
		this.tests.testNoPath();
		
	}
	
	@Test
	public void testNoPathCar() {
		
		this.tests.testNoPathCar();
		
	}
	
	/*
	 * Teste le résultat de l'algorithme Dijkstra pour le chemin le plus court avec tous les chemins
	 */
	@Test
	public void testFastestPathAll() {
		
		this.tests.testFastestPathAll();
			
	}
	
	/*
	 * Teste le résultat de l'algorithme Dijkstra pour le chemin le plus court avec tous les chemins
	 */
	@Test
	public void testFastestPathPedestrian() {
		
		this.tests.testFastestPathPedestrian();
			
	}
	
	/*
	 * Teste que le résultat de l'algorithme de Dijkstra soit bien différent selon le type de chemins à 
	 * prendre en Shortest si on est sur un chemin différent avec uniquement des routes en voiture 
	 */
	@Test
	public void testFastestPathAllvsPedestrian() {
		
		this.tests.testFastestPathAllvsPedestrian();
			
	}
	
	@Test
	public void testNoPathPedestrian() {
		
		this.tests.testNoPathPedestrian();
		
	}
	
	@Test
	public void testShortestFastestDifferent() {
		
		this.tests.testShortestFastestDifferent();
			
	}
	
}
