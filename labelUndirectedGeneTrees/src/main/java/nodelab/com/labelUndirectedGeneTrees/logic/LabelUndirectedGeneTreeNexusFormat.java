package nodelab.com.labelUndirectedGeneTrees.logic;

import nodelab.com.labelUndirectedGeneTrees.reconciliations.EvolutionaryReconciliationService;
import nodelab.com.labelUndirectedGeneTrees.reconciliations.RenconciliationServiceInterface;
import nodelab.com.labelUndirectedGeneTrees.structures.UnrootedGTreeInternalNodeInterface;
import nodelab.com.labelUndirectedGeneTrees.structures.UnrootedTreeAbstract;
import nodelab.com.labelUndirectedGeneTrees.structures.UnrootedTreeNexusTreeMapFormat;

public class LabelUndirectedGeneTreeNexusFormat extends LabelUndirectedGeneTreeAbstract {
	
	private RenconciliationServiceInterface reconciliation=new EvolutionaryReconciliationService();
	//private DummyReconciliation dummyReconciliation=new DummyReconciliation();

	@Override
	protected UnrootedTreeAbstract getRootedG(String unrootedG) {
		
		return new UnrootedTreeNexusTreeMapFormat(unrootedG);
		
	}

	@Override
	protected float getCurrentWeight(String S, String rootedG, String mapping,int level, int leftOrRight, int parentLevel, int leftOrRIghtParent) {
		return reconciliation.getWeight(S, rootedG, mapping,level,leftOrRight,parentLevel,leftOrRIghtParent);
	}

	@Override
	protected void outputLabeledGraph(UnrootedTreeAbstract G) {

		
		for (UnrootedGTreeInternalNodeInterface node: G.getInternalNodes()) {
			System.out.println("node: "+node.print()+" --- P Weight: "+node.getParentWeight()+" --- L Weight: "+node.getLeftWeight()+" --- R Weight: "+node.getRightWeight()); 	
		}
		
	}

}
