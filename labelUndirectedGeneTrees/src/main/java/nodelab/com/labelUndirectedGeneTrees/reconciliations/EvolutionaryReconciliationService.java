package nodelab.com.labelUndirectedGeneTrees.reconciliations;

import java.util.ArrayList;

import nodelab.com.labelUndirectedGeneTrees.constants.ApplicationConstants;
import nodelab.com.labelUndirectedGeneTrees.constants.Errors;
import nodelab.com.labelUndirectedGeneTrees.structures.GStar;
import nodelab.com.labelUndirectedGeneTrees.structures.ReconciliationInterface;
import nodelab.com.labelUndirectedGeneTrees.utility.Methods;

public class EvolutionaryReconciliationService implements RenconciliationServiceInterface {


	ReconciliationBuilding currentReconciliation=null;

	EucalyptReconciliationService eucalyptReconciliationService= new EucalyptReconciliationService();

	int internalStarCount=1;

	@Override
	public float getWeight(String S, String rootedG, String mapping, int level, int leftOrRight, int parentLevel, int leftOrRIghtParent) {

		//se la riconciliazione corrente è null allora la devo inizializzare con eucalypt.
		if (level==0 && internalStarCount==1) {
			initCurrentReconciliation(S,rootedG,mapping,0);
		}

		currentReconciliation.update(rootedG,level,leftOrRight,parentLevel,leftOrRIghtParent);

		return currentReconciliation.weight;
	}


	private void initCurrentReconciliation(String S, String rootedG, String mapping,int level) {

		currentReconciliation=new ReconciliationBuilding();

		eucalyptReconciliationService.getEvolutionaryReconciliationBuildingBlock(S, rootedG, mapping, currentReconciliation);

	}

	public class ReconciliationBuilding implements ReconciliationInterface {

		

		private String GAMMA_R;
		private String GAMMA_V;
		private String GAMMA_V_1;
		private String GAMMA_V_2;
		private String GAMMA_V_3;
		
		private String Label_R;
		private String Label_V;
		private String Label_V_1;
		private String Label_V_2;
		private String Label_V_3;

		private int level=0;
		private int leftOrRight;
		private int parentLevel;
		private int leftOrRightParent;

		private GStar currentStar;

		private float weight;

		private String hostLabeling;

		private String geneLabeling;

		private ArrayList<String[]> mappings=new ArrayList<>();

		private ArrayList<Float> weights=new ArrayList<>();




		public ArrayList<Float> getWeights() {
			return weights;
		}

		public void setWeights(ArrayList<Float> weights) {
			this.weights = weights;
		}

		public void setWeight(float weight) {
			this.weight = weight;
		}

		public String getHostLabeling() {
			return hostLabeling;
		}

		public void setHostLabeling(String hostLabeling) {
			this.hostLabeling = hostLabeling;
		}

		public String getGeneLabeling() {
			return geneLabeling;
		}

		public void setGeneLabeling(String geneLabeling) {
			this.geneLabeling = geneLabeling;
		}

		public String getGAMMA_R() {
			return GAMMA_R;
		}

		public void setGAMMA_R(String gAMMA_R) {
			GAMMA_R = gAMMA_R;
		}

		public String getGAMMA_V() {
			return GAMMA_V;
		}

		public void setGAMMA_V(String gAMMA_V) {
			GAMMA_V = gAMMA_V;
		}

		public String getGAMMA_V_1() {
			return GAMMA_V_1;
		}

		public void setGAMMA_V_1(String gAMMA_V_1) {
			GAMMA_V_1 = gAMMA_V_1;
		}

		public String getGAMMA_V_2() {
			return GAMMA_V_2;
		}

		public void setGAMMA_V_2(String gAMMA_V_2) {
			GAMMA_V_2 = gAMMA_V_2;
		}

		public String getGAMMA_V_3() {
			return GAMMA_V_3;
		}

		public void setGAMMA_V_3(String gAMMA_V_3) {
			GAMMA_V_3 = gAMMA_V_3;
		}

		@Override
		public void update(String rootedG, int level, int leftOrRight,int parentLevel, int leftOrRIghtParent) {

			
			if (internalStarCount==1) {
				this.level=level;
				this.leftOrRight=leftOrRight;
				this.parentLevel=parentLevel;
				this.leftOrRightParent=leftOrRIghtParent;

				//logica delle stelle
				//               --   v_2
				// v_1   -R-  v
				//               --   v_3
				//individuo i nodi 

				//v_1 è considerato il nodo a leftOrRight
				//V3 il nodo a destra di v

				updateInitialStarMappings();

				starDetection();

				createMappingsAndWeightsOfNewEdges();
			}

			setWeightOfCurrentEdge();

			
			if (internalStarCount<3) {
				internalStarCount++;
			} else {
				internalStarCount=1;
			}


		}

		private void createMappingsAndWeightsOfNewEdges() {
			
			mappings.add(currentStar.getV2Mapping());
			mappings.add(currentStar.getV3Mapping());
			weights.add(currentStar.getV2Weight());
			weights.add(currentStar.getV3Weight());
			
		}

		private void starDetection() {

			currentStar= new GStar(this);

		}
		
		

		private void setWeightOfCurrentEdge() {
			if (level==0) {
				setWeight(weights.get(0));
			} else {
				setWeight(weights.get((leftOrRightParent==ApplicationConstants.LEFT)? parentLevel*2-1 : parentLevel*2));
			}
		}

		public float getCurrentMappingWeight() {
			int index=0;
			if (level!=0) {
				index= (leftOrRightParent==ApplicationConstants.LEFT)? parentLevel*2-1 : parentLevel*2;
			}
			return weights.get(index);
		}
		
		public String[] getCurrentStarMapping() {
			int mappingIndex=0;
			if (level!=0) {
				mappingIndex= (leftOrRightParent==ApplicationConstants.LEFT)? parentLevel*2-1 : parentLevel*2;
			}
			return mappings.get(mappingIndex);
		}
		
		private String findMapping(String gNode) {
			String[] currentMapping= getCurrentStarMapping();
			for (String str : currentMapping) {
				if (str.split("@")[0].equals(gNode)) {
					return str.split("@")[1];
				}
			}
			throw new RuntimeException(Errors.UPDATING_RECONCILIATION_ERROR_NOT_MAPPING_FOUND);
		}

		private void updateInitialStarMappings() {

			Label_R=findLabel(geneLabeling);
			Label_V_1=findLabelV1();
			Label_V=findLabelV();
			Label_V_2=findLabelV2();
			Label_V_3=findLabelV3();

			GAMMA_R = findMapping(Label_R);
			GAMMA_V_1 = findMapping(Label_V_1);
			GAMMA_V = findMapping(Label_V);
			GAMMA_V_2 = findMapping(Label_V_2);
			GAMMA_V_3 = findMapping(Label_V_3);
			/*System.out.println("!P0"+"@"+GAMMA_R+"   "+findLabelV1()+"@"+GAMMA_V_1+"   "+findLabelV()+"@"+GAMMA_V+"   "+findLabelV2()+"@"+GAMMA_V_2+"   "+findLabelV3()+"@"+GAMMA_V_3);
			System.out.println("dasds");*/
		}

		private String findLabel(String x) {
			if (!x.contains(",")) {
				return x;
			}
			return x.substring(x.lastIndexOf(")")+1);
		}

		private String findLabelV2() {
			try {
				String firstLeftChild=geneLabeling.substring(1,Methods.findMiddleIndexComma(geneLabeling));
				return findLabel(firstLeftChild.substring(1,Methods.findMiddleIndexComma(firstLeftChild)));
			} catch (Exception e) {
				throw new RuntimeException(Errors.UPDATING_RECONCILIATION_ERROR_NOT_MAPPING_FOUND);
			}
		}

		private String findLabelV3() {
			try {
				String firstLeftChild=geneLabeling.substring(1,Methods.findMiddleIndexComma(geneLabeling));
				return findLabel(firstLeftChild.substring(Methods.findMiddleIndexComma(firstLeftChild)+1,firstLeftChild.lastIndexOf(")")));

			} catch (Exception e) {
				throw new RuntimeException(Errors.UPDATING_RECONCILIATION_ERROR_NOT_MAPPING_FOUND);
			}
		}

		private String findLabelV() {
			try {
				return findLabel(geneLabeling.substring(1,Methods.findMiddleIndexComma(geneLabeling)));
			} catch (Exception e) {
				throw new RuntimeException(Errors.UPDATING_RECONCILIATION_ERROR_NOT_MAPPING_FOUND);
			}
		}

		String findLabelV1() {
			try {
				return findLabel(geneLabeling.substring(Methods.findMiddleIndexComma(geneLabeling)+1,geneLabeling.lastIndexOf(")")));
			} catch (Exception e) {
				throw new RuntimeException(Errors.UPDATING_RECONCILIATION_ERROR_NOT_MAPPING_FOUND);
			}
		}


		public String getLabel_R() {
			return Label_R;
		}

		public void setLabel_R(String label_R) {
			Label_R = label_R;
		}

		public String getLabel_V() {
			return Label_V;
		}

		public void setLabel_V(String label_V) {
			Label_V = label_V;
		}

		public String getLabel_V_1() {
			return Label_V_1;
		}

		public void setLabel_V_1(String label_V_1) {
			Label_V_1 = label_V_1;
		}

		public String getLabel_V_2() {
			return Label_V_2;
		}

		public void setLabel_V_2(String label_V_2) {
			Label_V_2 = label_V_2;
		}

		public String getLabel_V_3() {
			return Label_V_3;
		}

		public void setLabel_V_3(String label_V_3) {
			Label_V_3 = label_V_3;
		}

		public ArrayList<String[]> getMappings() {
			return mappings;
		}

		int max(int i1,int i2) {
			if (i1>i2) {
				return i1;
			}
			return i2;
		}

		@Override
		public float getWeight() {
			return weight;
		}

	


	}

}
