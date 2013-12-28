package net.zwerks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

//import com.zwerks.subgrapher.RankedNodeList;

public class DegreeRanker {
	//private RankedNodeList theRankedList;
	private String myBigGraphFilePath;
	private ArrayList <String> top1000Nodes;
	private HashMap <String, String> countedNodes;

	public DegreeRanker() {
		this.myBigGraphFilePath = System.getProperty("user.dir") + "\\" + "Web-Google.txt";
		this.countedNodes = new HashMap<String, String>();
		this.top1000Nodes = new ArrayList<String>();
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DegreeRanker myDegreeRanker =  new DegreeRanker();
		myDegreeRanker.runBigGraph();
		
		myDegreeRanker.printTop1000(myDegreeRanker.getCountedNodes());
		
		//myDegreeRanker.printTop1000Nodes(myDegreeRanker.getTop1000Nodes());
		
	}
	
	public void runBigGraph(){
		System.out.println(this.getBigGraphFilePath());
		
		Scanner s = null;
		try {
			s = new Scanner(new File(this.getBigGraphFilePath()));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String leftNode = null;
		String rightNode = null;
		String newNode = null;
		Integer newNodeCount = 0;
		Integer currNodeCount = 0;
		int NodeCounter = 0;
		int ItemCounter = 0;
		
		Integer lastRankIndex = 0;
		
//		try {
//			System.out.println("Starting at the top of the file ...");
//			PrintWriter writeToFile = new PrintWriter(System.getProperty("user.dir") + "\\" + "NodeCount.txt");
			
			while (s.hasNext()){
				newNode = s.next();
			    if(this.countedNodes.containsKey(newNode)){
			    	currNodeCount = Integer.parseInt((this.countedNodes.get(newNode)));
			    	newNodeCount = currNodeCount + 1;
			    	this.countedNodes.put(newNode, (newNodeCount.toString()));
			    	
			    	//writeToFile.println(newNode + "\t" + newNodeCount);
			    	
			    	/********/
			    	//Sort the ranking from top to bottom ---> Largest first
			    	/********/
			    /*	
			    	if(this.top1000Nodes.isEmpty() || !this.top1000Nodes.contains(newNode)){
			    		this.top1000Nodes.add(newNode);
			    		//System.out.println("List is Either empty, or doesn't contain this node.");
			    		//System.out.print(".");
			    	
					    ItemCounter++;
					    if(ItemCounter % 1000 == 0){
					    	 System.out.print(":");
					    	 if (ItemCounter % 50000 == 0){
					    		 System.out.println("|");
					    	 }
					    }
			    	}else if(this.top1000Nodes.contains(newNode)){
			    		lastRankIndex = this.top1000Nodes.indexOf(newNode);
			    		this.top1000Nodes.remove(lastRankIndex);
			    		if (lastRankIndex > 0){
			    			this.top1000Nodes.add(lastRankIndex-1, newNode);
			    		}else{
			    			this.top1000Nodes.add(0, newNode);
			    		}
			    	}
			    	
			    	*/
			    
			    }else{
			    	this.countedNodes.put(newNode, "1");
			    	//writeToFile.println(newNode + "\t" + "1");
			    }
			    
			    NodeCounter++;
			    if(NodeCounter % 1000 == 0){
			    	 System.out.print(".");
			    	 if (NodeCounter % 50000 == 0){
			    		 System.out.println("|");
			    	 }
			    }
			}
			
			System.out.println("Reached the end of the file ...");
			System.out.println("HashMap Size: " + countedNodes.size());
		
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public String getBigGraphFilePath(){
		return this.myBigGraphFilePath;
	}
	
	public HashMap<String, String> getCountedNodes(){
		return this.countedNodes;
	}
	
	public ArrayList <String> getTop1000Nodes(){
		return this.top1000Nodes;
	}
	
	public void printTop1000(HashMap<String, String> hmp){
		try {
			
			PrintWriter writeToFile = new PrintWriter(System.getProperty("user.dir") + "\\" + "NodeCount.txt");
		
			System.out.println("Starting write to file ...");
			System.out.println("Entry-set size: "+ hmp.entrySet().size());
			
			Iterator it = hmp.entrySet().iterator();  
			while (it.hasNext()) {
			    Map.Entry entry = (Map.Entry) it.next();
			    
			    /**/
			    String key = (String)entry.getKey();
			    String val = (String)entry.getValue();
			    //System.out.println("key,val: " + key + "," + val);

			    writeToFile.println(key + "\t" + val);
			}
			
			System.out.println("Finished write to file ...");
			System.out.println("HashMap size 2: " + hmp.size());
			
			writeToFile.close();
			System.out.println("Closed file ...");
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printTop1000Nodes(ArrayList myTop1000List){
		for(int i=0; i < myTop1000List.size(); i++){
			System.out.println(myTop1000List.get(i));
		}
		
	}

}
