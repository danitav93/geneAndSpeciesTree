package nodelab.com.labelUndirectedGeneTrees;

import nodelab.com.labelUndirectedGeneTrees.input.InputInterface;
import nodelab.com.labelUndirectedGeneTrees.input.SimpleInput;
import nodelab.com.labelUndirectedGeneTrees.logic.LabelUndirectedGeneTreeNexusFormat;

public class App 
{
    
    public static void main(String[] args) {

    	long start = System.currentTimeMillis();
    	
    	InputInterface inputInterface= new SimpleInput();
    	
    	String S= inputInterface.getS();
    	
    	String unRootedG= inputInterface.getUnrootedG();
    	
    	String mapping= inputInterface.getMapping();
    	
    	new LabelUndirectedGeneTreeNexusFormat().generateLabeledGraph(S, unRootedG, mapping);
    	
    	long end = System.currentTimeMillis();
    	
    	System.out.println((end - start)/1000 + "s");

		
	}


}
