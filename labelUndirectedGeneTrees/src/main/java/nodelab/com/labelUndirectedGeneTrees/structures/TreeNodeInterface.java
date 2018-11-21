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
	String print();
}
