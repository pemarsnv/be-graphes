package org.insa.graphs.algorithm.shortestpath;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.GraphReader;
import org.junit.Before;
import org.junit.Test;

public class AStarTest {
	
	private ShortestPathTest tests;
	
	@Before
	public void beforeAll() throws IOException {
		
		this.tests = new ShortestPathTest(AStarAlgorithm::new);

        String mapName = "../maps/insa.mapgr";
		GraphReader reader = new BinaryGraphReader(new DataInputStream(
				new BufferedInputStream(new FileInputStream(mapName))));
		this.tests.graph = reader.read();
		
	}

	@Test
	public void allTests() {
		
		this.tests.testShortestPathAll();
		this.tests.testShortestPathCar();
		this.tests.testShortestPathAllvsCar();
		this.tests.testNoPath();
		this.tests.testNoPathCar();
		this.tests.testFastestPathAll();
		this.tests.testFastestPathPedestrian();
		this.tests.testFastestPathAllvsPedestrian();
		this.tests.testNoPathPedestrian();
		this.tests.testShortestFastestDifferent();
			
	}
	
}
