package nodelab.com.labelUndirectedGeneTrees.structures;

import java.util.ArrayList;

import nodelab.com.labelUndirectedGeneTrees.constants.ApplicationConstants;
import nodelab.com.labelUndirectedGeneTrees.constants.Errors;
import nodelab.com.labelUndirectedGeneTrees.utility.Methods;
public class UnrootedTreeNexusTreeMapFormat extends UnrootedTreeAbstract {




	public UnrootedTreeNexusTreeMapFormat(String unrootedG) {

		internalNodes = new ArrayList<>();
		LeafG firstLeaf = new LeafG();
		try {
			firstLeaf.setLabel(unrootedG.substring(unrootedG.lastIndexOf(",")+1,unrootedG.lastIndexOf(")")));
			if (firstLeaf.getLabel().contains(",")||firstLeaf.getLabel().contains("(")||firstLeaf.getLabel().contains(")")) {
				throw new RuntimeException(Errors.UNROOTED_TREE_GENERATOR_ERROR_WRONG_FORMAT_FIRST_NODE+": "+unrootedG);
			}
		} catch (IndexOutOfBoundsException e) {
			throw new RuntimeException(Errors.UNROOTED_TREE_GENERATOR_ERROR_WRONG_FORMAT_FIRST_NODE+": "+unrootedG);
		}
		unrootedG=unrootedG.substring(1,unrootedG.lastIndexOf(","));
		initializeNodes(unrootedG,firstLeaf,ApplicationConstants.FATHER);
		
		iterator=internalNodes.iterator();
		
		if (!iterator.hasNext()) {
			throw new RuntimeException(Errors.UNROOTED_TREE_GENERATOR_ERROR_WRONG_FORMAT_NO_INTERNAL_NODE_FOUND);
		}
		
		currentInternalNode=iterator.next();
	};

	private void initializeNodes(String unrootedG, TreeNode parent, int relationship) {

		if ((unrootedG.charAt(0)!='(')||(unrootedG.charAt(unrootedG.length()-1)!=')')) {
			throw new RuntimeException(Errors.UNROOTED_TREE_GENERATOR_ERROR_WRONG_FORMAT_EXTERNAL_BRACKET+": "+unrootedG);
		} 
		
		UnrootedGTreeInternalNode internalNode = new UnrootedGTreeInternalNode();
		
		internalNodes.add(internalNode);

		switch (relationship) {
		case ApplicationConstants.FATHER:
			parent.setParent(internalNode);
			break;
		case ApplicationConstants.LEFT:
			parent.setLeft(internalNode);
			break;
		case ApplicationConstants.RIGHT:
			parent.setRight(internalNode);
			break;

		}

		internalNode.setParent(parent);

		
		//controllare il figlio destro e sinistro se sono foglie o meno e nel caso aggiungere oppure funzione ricorsiva

		//left child
		String left;
		try {
			left= unrootedG.substring(1,Methods.findMiddleIndex(unrootedG));
		} catch (IndexOutOfBoundsException e) {
			throw new RuntimeException(Errors.UNROOTED_TREE_GENERATOR_ERROR_WRONG_FORMAT_NOT_MIDDLE_FOUND+": "+unrootedG);
		}
		assert left!=null;
		//se il figlio sinistro contiene una virgola allora non può essere una foglia quindi funzione ricorsiva
		if (left.contains(",")) {
			initializeNodes(left, internalNode, ApplicationConstants.LEFT);
		} else { //altrimenti deve essere una foglia e quindi concludo qui
			if (left.contains("(")||left.contains(")")) {
				throw new RuntimeException(Errors.UNROOTED_TREE_GENERATOR_ERROR_LEAF_WRONG_FORMAT+": "+left);
			}
			LeafG leftLeaf= new LeafG();
			leftLeaf.setLabel(left);
			leftLeaf.setParent(internalNode);
			internalNode.setLeft(leftLeaf);
		}

		//right child
		String right;
		try {
			right= unrootedG.substring(Methods.findMiddleIndex(unrootedG)+1,unrootedG.length()-1);
		} catch (IndexOutOfBoundsException e) {
			throw new RuntimeException(Errors.UNROOTED_TREE_GENERATOR_ERROR_WRONG_FORMAT_NOT_MIDDLE_FOUND+": "+unrootedG);
		}
		assert right!=null;
		//se il figlio sinistro contiene una virgola allora non può essere una foglia quindi funzione ricorsiva
		if (right.contains(",")) {
			initializeNodes(right, internalNode, ApplicationConstants.RIGHT);
		} else { //altrimenti deve essere una foglia e quindi concludo qui
			if (right.contains("(")||right.contains(")")) {
				throw new RuntimeException(Errors.UNROOTED_TREE_GENERATOR_ERROR_WRONG_FORMAT+": "+right);
			}
			LeafG rightLeaf= new LeafG();
			rightLeaf.setLabel(right);
			rightLeaf.setParent(internalNode);
			internalNode.setRight(rightLeaf);
		}

		

	}

	

	@Override
	public String getNextFormattedRootedTree() {
		
		int info;
		if (currentInternalNode.isLeftChild()) {
			info=ApplicationConstants.LEFT;
		} else if (currentInternalNode.isRightChild()) {
			info=ApplicationConstants.RIGHT;
		} else {
			info=ApplicationConstants.FATHER;
		}
		
		if (!currentInternalNode.isParentLabeled()) {
			//System.out.println(currentInternalNode.print());
			return getNexusFormattedString(currentInternalNode.getParent(),info,currentInternalNode,ApplicationConstants.FATHER);
		}
		
		if (!currentInternalNode.isLeftLabeled()) {
			return getNexusFormattedString(currentInternalNode.getLeft(),ApplicationConstants.FATHER,currentInternalNode,ApplicationConstants.LEFT);
		}
		
		if (!currentInternalNode.isRightLabeled()) {
			return getNexusFormattedString(currentInternalNode.getRight(),ApplicationConstants.FATHER,currentInternalNode,ApplicationConstants.RIGHT);
		}
		
		//if all are labeled I must check if this was the last one node then i must say that the graph is labeled and exit
		if (!iterator.hasNext()) {
			isLabeled=true;
			return null;
		}
		
		//otherwise switch to the next node
		currentInternalNode=iterator.next();
		

		return getNextFormattedRootedTree();
		
	}

	public  String getNexusFormattedString(TreeNodeInterface<TreeNode> left, int infoLeft, TreeNodeInterface<TreeNode> right, int infoRight) {
		//System.out.println(left.print()+"    "+right.print());
		String leftString="";
		int info;
		if (left.isLeftChild()) {
			info=ApplicationConstants.LEFT;
		} else if (left.isRightChild()) {
			info=ApplicationConstants.RIGHT;
		} else {
			info=ApplicationConstants.FATHER;
		}
		
		if (left instanceof LeafG) {
			leftString=((LeafG) left).getLabel();
		} else {
			switch (infoLeft) {
			case ApplicationConstants.FATHER:	
				leftString =getNexusFormattedString(left.getLeft(),ApplicationConstants.FATHER, left.getRight(),ApplicationConstants.FATHER);
				break;
			case ApplicationConstants.LEFT:
				leftString =getNexusFormattedString(left.getParent(),info, left.getRight(),ApplicationConstants.FATHER);
				break;
			case ApplicationConstants.RIGHT:
				leftString =getNexusFormattedString(left.getParent(),info, left.getLeft(),ApplicationConstants.FATHER);
				break;
			}
		}
		
		String rightString="";
		if (right.isLeftChild()) {
			info=ApplicationConstants.LEFT;
		} else if (right.isRightChild()) {
			info=ApplicationConstants.RIGHT;
		} else {
			info=ApplicationConstants.FATHER;
		}
		if (right instanceof LeafG) {
			rightString=((LeafG) right).getLabel();
		} else {
			switch (infoRight) {
			case ApplicationConstants.FATHER:	
				rightString =getNexusFormattedString(right.getLeft(),ApplicationConstants.FATHER, right.getRight(),ApplicationConstants.FATHER);
				break;
			case ApplicationConstants.LEFT:
				rightString =getNexusFormattedString(right.getParent(),info, right.getRight(),ApplicationConstants.FATHER);
				break;
			case ApplicationConstants.RIGHT:
				rightString =getNexusFormattedString(right.getParent(),info, right.getLeft(),ApplicationConstants.FATHER);
				break;
			}
		}
		
		return "("+leftString+","+rightString+")";
		
	}

	public static String print(TreeNodeInterface<TreeNode> left, TreeNodeInterface<TreeNode> right) {
		
		String leftString="";
		
		
		if (left instanceof LeafG) {
			leftString=((LeafG) left).getLabel();
		} else {
		
				
			leftString =print(left.getLeft(), left.getRight());
			
		}
		
		String rightString="";
		
		if (right instanceof LeafG) {
			rightString=((LeafG) right).getLabel();
		} else {
			
				
				rightString =print(right.getLeft(), right.getRight());
				
		}
		
		return "("+leftString+","+rightString+")";
		
	}

	@Override
	public int getLeftOrRight() {
		if (!currentInternalNode.isParentLabeled()) {
			return ApplicationConstants.FATHER;
		}
		if (!currentInternalNode.isLeftLabeled()) {
			return ApplicationConstants.LEFT;
		}
		if (!currentInternalNode.isRightLabeled()) {
			return ApplicationConstants.RIGHT;
		}
		return 0;
	}

	@Override
	public int getLeftOrRightParent() {
		if (currentInternalNode.isLeftChild()) {
			return ApplicationConstants.LEFT;

		}
		return ApplicationConstants.RIGHT;
	}

}
