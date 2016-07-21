import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class InputGenerator {
	void generateSortedDescending(NodeMap nodeMap, DegreeMap degreeMap, String inFile) {
		OutputWriter output = new OutputWriter(inFile+"-descending.ungraph.txt");
		HashSet<String> visited = new HashSet<String>();
		int i = degreeMap.capacity-1;
		int count =0;
		while( i >= 0) {
			HashSet<String> temp = degreeMap.getNodes(i);
			for(String node: temp) {
				
				HashSet<String> neighbors = nodeMap.getNeighbors(node);
				for(String neighbor:neighbors) {
					if(!visited.contains(neighbor)) {
						output.writeOutput(node+"\t" + neighbor);
						count++;
					}
					if(count%10000 == 0)
						output.flush();
				}
				visited.add(node);
			}
			//System.out.println(temp);
			i--;
			
		}
		output.close();
	}
	void generateSortedAscending(NodeMap nodeMap, DegreeMap degreeMap, String inFile) {
		OutputWriter output = new OutputWriter(inFile+"-ascending.ungraph.txt");
		
		HashSet<String> visited = new HashSet<String>();
		int i = 0;
		int count =0;
		while( i <degreeMap.capacity) {
			HashSet<String> temp = degreeMap.getNodes(i);
			for(String node: temp) {
				HashSet<String> neighbors = nodeMap.getNeighbors(node);
				for(String neighbor:neighbors) {
					if(!visited.contains(neighbor)) {
						output.writeOutput(node+"\t" + neighbor);
						count++;
					}
					if(count%10000 == 0)
						output.flush();
				}
				visited.add(node);
			}
			//System.out.println(temp);
			i++;
		}
		output.close();
	}
	void generateBFS(NodeMap nodeMap, DegreeMap degreeMap, String inFile) {
		OutputWriter output = new OutputWriter(inFile+"-bfs.ungraph.txt");
		
		HashSet<String> visited = new HashSet<String>();
		long count = 0 ;
		for(String node: nodeMap.map.keySet()) {
			if(!visited.contains(node)) {
				Queue<String> queue = new LinkedList<String>();
				queue.add(node);
				while(!queue.isEmpty()) {
					String item = queue.remove();
					if(!visited.contains(item))  {
						visited.add(item);
					
						HashSet<String> neighbors = nodeMap.getNeighbors(item);
						for(String neighbor:neighbors) {
							if(!visited.contains(neighbor)) {
								output.writeOutput(item+"\t" + neighbor);
								queue.add(neighbor);
								count++;
							}
							if(count%10000 == 0)
								output.flush();
						}
					}
 				}
			}
		}
		output.close();
	}
	
	void generateDFS(NodeMap nodeMap, DegreeMap degreeMap, String inFile) {
		OutputWriter output = new OutputWriter(inFile+"-dfs.ungraph.txt");
		
		HashSet<String> visited = new HashSet<String>();
		long count = 0 ;
		for(String node: nodeMap.map.keySet()) {
			if(!visited.contains(node)) {
				Stack<String> stack = new Stack<String>();
				stack.push(node);
				while(!stack.isEmpty()) {
					String item = stack.pop();
					if(!visited.contains(item))  {
						visited.add(item);
						HashSet<String> neighbors = nodeMap.getNeighbors(item);
						for(String neighbor:neighbors) {
							if(!visited.contains(neighbor)) {
								output.writeOutput(item+"\t" + neighbor);
								stack.add(neighbor);
								count++;
							}
							if(count%10000 == 0)
								output.flush();
						}
					}
 				}
			}
		}
		output.close();
	}
}
