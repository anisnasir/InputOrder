

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class Main { 
	public static void main(String[] args) throws IOException{
		String directory = args[0];
		String inFile = args[1];
		String inFileName= directory+inFile+".ungraph.txt";
		String sep = "\t";
		BufferedReader in = null;
		
		try {
            InputStream rawin = new FileInputStream(inFileName);
            in = new BufferedReader(new InputStreamReader(rawin));
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
            System.exit(1);
        }

        //initialize the input reader
        StreamEdgeReader reader = new StreamEdgeReader(in,sep);
		StreamEdge item = reader.nextItem();
		while(item.getSource().equals(item.getDestination())) {
			item = reader.nextItem();
			if(item == null)
				break;
		}
		
		//Declare outprint interval variables
		int PRINT_INTERVAL=1000000;
		long simulationStartTime = System.currentTimeMillis();
		
			//Data Structures specific to the Algorithm
		NodeMap nodeMap = new NodeMap();
		DegreeMap degreeMap = new DegreeMap();
		EdgeHandler utility = new EdgeHandler();
		
		int edgeCounter = 1;
		while(item != null) {
			if (++edgeCounter % PRINT_INTERVAL == 0) {
				System.out.println("Read " + edgeCounter/(double)PRINT_INTERVAL
						+ "M edges.\tSimulation time: "
						+ (System.currentTimeMillis() - simulationStartTime)
						/ 1000 + " seconds");
				
			}
			utility.handleEdgeAddition(item, nodeMap,degreeMap);
			
			item = reader.nextItem();
			if(item !=null)
				while(nodeMap.contains(item) || (item.getSource().equals(item.getDestination()))) {
					item = reader.nextItem();
					if(item == null)
						break;
				}
		}
		
		System.out.println("Finished Processing! Read " + edgeCounter/(double)PRINT_INTERVAL
				+ "M edges.\tSimulation time: "
				+ (System.currentTimeMillis() - simulationStartTime)
				/ 1000 + " seconds");
		
		InputGenerator generator = new InputGenerator();
		
		generator.generateSortedDescending(nodeMap, degreeMap, inFile);
		System.out.println("generated Sorted Descending");
		generator.generateSortedAscending(nodeMap, degreeMap, inFile);
		System.out.println("generated Sorted Ascending");
		generator.generateBFS(nodeMap, degreeMap, inFile);
		System.out.println("generated BFS");
		generator.generateDFS(nodeMap, degreeMap, inFile);
		System.out.println("generated DFS");
		
		
	
	in.close();
	}

}
