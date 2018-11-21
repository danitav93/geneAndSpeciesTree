package nodelab.com.labelUndirectedGeneTrees.structures;

public interface UnrootedGTreeInternalNodeInterface extends TreeNodeInterface<TreeNode> {

	float getParentWeight();
	
	float getLeftWeight();
	
	float getRightWeight();
	
	boolean isParentLabeled();
	
	boolean isLeftLabeled();
	
	boolean isRightLabeled();
	
	boolean isLabeled();

	void setParentWeight(float weight);
	
	void setLeftWeight(float weight);
	
	void setRightWeight(float weight);
	
	void setParentLabeled(boolean labeled);
	
	void setLeftLabeled(boolean labeled);
	
	void setRightLabeled(boolean labeled);

	void setCurrentWeight(float weight);

	String print();

	
}
