package nodelab.com.labelUndirectedGeneTrees.logic;

import nodelab.com.labelUndirectedGeneTrees.structures.UnrootedTreeAbstract;

/**
 * 
 * @author Daniele Tavernelli
 *
 * Abstract class that specify methods to generate the labeled unrooted gene tree
 *
 */
public abstract class LabelUndirectedGeneTreeAbstract{

	/**
	 * 
	 * @param host tree S
	 * @param gene unrooted tree unrootedG
	 * @param mapping of leaves mapping
	 */
	public  void generateLabeledGraph(String S, String unrootedG, String mapping) {
		
		//build the UnrootedTree object from a given format
		UnrootedTreeAbstract unrootedTreeG= getRootedG(unrootedG);
		
		//start generating all the rooted trees
		String rootedG = unrootedTreeG.getNextFormattedRootedTree();
		
		while (!unrootedTreeG.isLabeled()) {
			
			unrootedTreeG.setCurrentWeight(getCurrentWeight(S,rootedG,mapping,unrootedTreeG.getCurrentLevel(),unrootedTreeG.getLeftOrRight(),unrootedTreeG.getParentLevel(),unrootedTreeG.getLeftOrRightParent()));
			
			rootedG = unrootedTreeG.getNextFormattedRootedTree();
			
		}
		
		//output the labeled graph
		outputLabeledGraph(unrootedTreeG);
		
		
	}
	
	protected abstract UnrootedTreeAbstract getRootedG(String unrootedG);
	
	protected abstract float getCurrentWeight(String S, String rootedG, String mapping, int level, int leftOrRight, int parentLevel, int leftOrRIghtParent);
	
	protected abstract void outputLabeledGraph(UnrootedTreeAbstract G);


}
