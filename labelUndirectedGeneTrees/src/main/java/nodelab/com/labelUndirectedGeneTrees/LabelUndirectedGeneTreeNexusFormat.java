package nodelab.com.labelUndirectedGeneTrees;

import nodelab.com.labelUndirectedGeneTrees.reconciliations.EucalyptReconciliationService;
import nodelab.com.labelUndirectedGeneTrees.reconciliations.RenconciliationServiceInterface;
import nodelab.com.labelUndirectedGeneTrees.structures.UnrootedGTreeInternalNodeInterface;
import nodelab.com.labelUndirectedGeneTrees.structures.UnrootedTreeAbstract;
import nodelab.com.labelUndirectedGeneTrees.structures.UnrootedTreeNexusTreeMapFormat;

public class LabelUndirectedGeneTreeNexusFormat extends LabelUndirectedGeneTreeAbstract {
	
	private RenconciliationServiceInterface reconciliation=new EucalyptReconciliationService();

	@Override
	protected UnrootedTreeAbstract getRootedG(String unrootedG) {
		
		return new UnrootedTreeNexusTreeMapFormat(unrootedG);
		
	}

	@Override
	protected float getCurrentWeight(String S, String rootedG, String mapping) {
		return reconciliation.getWeight(S, rootedG, mapping);
	}

	@Override
	protected void outputLabeledGraph(UnrootedTreeAbstract G) {

		
		for (UnrootedGTreeInternalNodeInterface node: G.getInternalNodes()) {
			System.out.println("node: "+node.print()+" --- P Weight: "+node.getParentWeight()+" --- L Weight: "+node.getLeftWeight()+" --- R Weight: "+node.getRightWeight()); 	
		}
		
	}

}
