package nodelab.com.labelUndirectedGeneTrees.structures;

import java.util.ArrayList;
import java.util.Iterator;

import nodelab.com.labelUndirectedGeneTrees.constants.Errors;

/**
 * 
 * @author Daniele Tavernelli
 *
 * Defines the unrooted tree object and his methods
 */
public abstract class UnrootedTreeAbstract {
	
	protected boolean isLabeled=false;
	
	protected ArrayList<UnrootedGTreeInternalNodeInterface> internalNodes;
	
	protected Iterator<UnrootedGTreeInternalNodeInterface> iterator;
	
	protected UnrootedGTreeInternalNodeInterface currentInternalNode;
	
	public abstract String getNextFormattedRootedTree();
	
	public  void setCurrentWeight(float weight) {
		
		if (currentInternalNode==null) {
			throw new RuntimeException(Errors.UNROOTED_TREE_ERROR_INTERNAL_NODE);
		}
		
		currentInternalNode.setCurrentWeight(weight);
		
	};
	
	public boolean isLabeled() {
		return isLabeled;
	};
	
	public ArrayList<UnrootedGTreeInternalNodeInterface> getInternalNodes() {
		return internalNodes;
	}

	public int getCurrentLevel() {
		return internalNodes.indexOf(currentInternalNode);
	}

	public abstract int getLeftOrRight();

	public int getParentLevel() {
		return internalNodes.indexOf((UnrootedGTreeInternalNodeInterface)currentInternalNode.getParent());
	}

	public abstract int getLeftOrRightParent();
	
}
