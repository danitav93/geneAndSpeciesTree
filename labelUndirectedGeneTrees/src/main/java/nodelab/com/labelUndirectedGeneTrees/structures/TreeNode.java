package nodelab.com.labelUndirectedGeneTrees.structures;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import nodelab.com.labelUndirectedGeneTrees.constants.Errors;

public class TreeNode implements TreeNodeInterface<TreeNode> {

	private TreeNode parent;
	
	private TreeNode left;
	
	private TreeNode right;
	
	public TreeNode getParent() {
		return parent;
	}

	public TreeNode getLeft() {
		return left;
	}

	public TreeNode getRight() {
		return right;
	}

	public TreeNode getSibling() {
		
		if (isLeftChild()) {
			return parent.getRight();
		}
		return parent.getLeft();
	}

	public Set<TreeNode> getCluster() {
		
		if (this instanceof LeafInterface ) {
			return new HashSet<>(Arrays.asList(this));
		}
		
		if (left==null || right==null) {
			throw new RuntimeException(Errors.TREE_NODE_ERROR_CHILDREN);
		}
		
		Set<TreeNode> cluster= left.getCluster();
		cluster.addAll(right.getCluster());
		
		return cluster;
		
	}

	public boolean isLeaf() {
		
		return this instanceof LeafInterface;
		
	}

	public boolean isRoot() {
		return parent==null;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}

	@Override
	public boolean isLeftChild() {
		if (parent==null) {
			throw new RuntimeException(Errors.TREE_NODE_ERROR_IS_LEFT_OR_RIGHT);

		}
		return parent.getLeft()==this;
	}

	@Override
	public boolean isRightChild() {
		if (parent==null) {
			throw new RuntimeException(Errors.TREE_NODE_ERROR_IS_LEFT_OR_RIGHT);

		}
		return parent.getRight()==this;
	}

	@Override
	public String print() {
		if (this instanceof Leaf) {
			return ((Leaf)this).getLabel();
		}
		if (this instanceof UnrootedGTreeInternalNode) {
			return ((UnrootedGTreeInternalNode)this).print();
		}
		return null;
	}

	
	@Override
	public boolean comparable(TreeNodeInterface<TreeNode> node) {
		return isAscendant(node) || isDescendant(node);
	}

	@Override
	public boolean isAscendant(TreeNodeInterface<TreeNode> node) {
		if (node.getParent()==null) {
			return false;
		}
		if (this==node.getParent()) {
			return true;
		}
		return isAscendant(node.getParent());
	}

	@Override
	public boolean isDescendant(TreeNodeInterface<TreeNode> node) {
		
		if (getParent()==null) {
			return false;
		}
		if (node==getParent()) {
			return true;
		}
		return isDescendant(getParent());
	}

	//non efficient
	public int getDistance(TreeNode ascendant) {
	
		
		if (ascendant==this) {
			return 0;
		}
		
		if (ascendant.left==this || ascendant.right==this) {
			return 1;
		}
		
		if (parent==null) {
			throw new RuntimeException(Errors.TREE_NODE_DISTANCE_ERROR);
		}
		
		return  parent.getDistance(ascendant);
		
	}

}
