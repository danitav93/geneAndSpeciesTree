package nodelab.com.labelUndirectedGeneTrees.reconciliations;

public class DummyReconciliation implements RenconciliationServiceInterface {

	int x=-1;
	
	@Override
	public float getWeight(String S, String rootedG, String mapping,int level, int leftOrRight, int parentLevel, int leftOrRIghtParent) {
		x++;
		return x;
	}

}
