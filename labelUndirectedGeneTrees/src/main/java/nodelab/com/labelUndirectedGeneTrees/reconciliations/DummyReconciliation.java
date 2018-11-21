package nodelab.com.labelUndirectedGeneTrees.reconciliations;

public class DummyReconciliation implements RenconciliationServiceInterface {

	int x=-1;
	
	@Override
	public float getWeight(String S, String rootedG, String mapping) {
		x++;
		return x;
	}

}
