package nodelab.com.labelUndirectedGeneTrees.structures;

import java.util.Set;

public interface TreeNodeInterface<T> {
	T getParent();
	T getLeft();
	T getRight();
	T getSibling();
	Set<T> getCluster();
	boolean isLeaf();
	boolean isRoot();
	boolean isLeftChild();
	boolean isRightChild();
	boolean comparable(TreeNodeInterface<T> node);
	boolean isAscendant(TreeNodeInterface<T> node);
	boolean isDescendant(TreeNodeInterface<T> node);
	String print();
}
