package nodelab.com.labelUndirectedGeneTrees.structures;

public interface ReconciliationInterface {

	void update(String rootedG, int level, int leftOrRight,int parentLevel, int leftOrRIghtParent);
	
	float getWeight();
		
	
}
