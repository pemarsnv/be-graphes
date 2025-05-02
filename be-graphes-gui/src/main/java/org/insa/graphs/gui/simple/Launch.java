package org.insa.graphs.gui.simple;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.insa.graphs.gui.drawing.Drawing;
import org.insa.graphs.gui.drawing.components.BasicDrawing;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Path;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.GraphReader;
import org.insa.graphs.model.io.PathReader;

public class Launch {

    /**
     * Create a new Drawing inside a JFrame an return it.
     *
     * @return The created drawing.
     * @throws Exception if something wrong happens when creating the graph.
     */
    public static Drawing createDrawing() throws Exception {
        BasicDrawing basicDrawing = new BasicDrawing();
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("BE Graphes - Launch");
                frame.setLayout(new BorderLayout());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.setSize(new Dimension(800, 600));
                frame.setContentPane(basicDrawing);
                frame.validate();
            }
        });
        return basicDrawing;
    }

    public static void main(String[] args) throws Exception {

        // visit these directory to see the list of available files on commetud

        final String mapName =
        		"../maps/insa.mapgr";
        
        File fichier = new File(mapName);
        System.out.println("Chemin du fichier : " + fichier.getAbsolutePath());
        System.out.println("Existe ? " + fichier.exists());
        System.out.println("Lisible ? " + fichier.canRead());
        System.out.println("Répertoire courant : " + System.getProperty("user.dir"));
        
        final String pathName =
                "../paths/path_fr31insa_rangueil_r2.path";

        final Graph graph;
        final Path path;

        // create a graph reader
        try (final GraphReader reader = new BinaryGraphReader(new DataInputStream(
                new BufferedInputStream(new FileInputStream(mapName))))) {

            //read the graph
            graph = reader.read();
        }

        // create the drawing
        final Drawing drawing = createDrawing();

        //draw the graph on the drawing
        drawing.drawGraph(graph);

        // TODO: create a path reader
        
        try (final PathReader pathReader = null) {

            // TODO: read the path
            path = null;
        }

        // TODO: draw the path on the drawing
    }

}
