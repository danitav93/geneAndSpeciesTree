package nodelab.com.labelUndirectedGeneTrees.structures;

import nodelab.com.labelUndirectedGeneTrees.constants.Errors;
import nodelab.com.labelUndirectedGeneTrees.constants.Stars;
import nodelab.com.labelUndirectedGeneTrees.reconciliations.EvolutionaryReconciliationService.ReconciliationBuilding;

public class GStar   {

	private static RootedTree S;

	private String[] v2Mapping;

	private String[] v3Mapping;

	private float v2Weight;

	private float v3Weight;

	ReconciliationBuilding reconciliationBuilding;


	public GStar(ReconciliationBuilding reconciliationBuilding) {

		if (S==null) {
			throw new RuntimeException(Errors.ROOTED_TREE_S_NOT_INITIALIZED_ERROR);
		}

		this.reconciliationBuilding=reconciliationBuilding;

		int type;

		type=findType();

		if (type==-1) {
			throw new RuntimeException(Errors.STAR_NOT_FOUND_ERROR+": gammaR="+reconciliationBuilding.getGAMMA_R());
		}

		updateMappingsBasedOnType(type);

	}

	private int findType() {


		boolean v2bar=checkBar(reconciliationBuilding.getGAMMA_V_2(), reconciliationBuilding.getGAMMA_V());
		boolean v3bar=checkBar(reconciliationBuilding.getGAMMA_V_3(), reconciliationBuilding.getGAMMA_V());

		boolean edge1Type1=checkType1(reconciliationBuilding.getGAMMA_R(), reconciliationBuilding.getGAMMA_V_1());
		boolean edge1Type2=checkType2(reconciliationBuilding.getGAMMA_R(), reconciliationBuilding.getGAMMA_V_1());
		boolean edge1Type7=checkType7(reconciliationBuilding.getGAMMA_V(), reconciliationBuilding.getGAMMA_R());

		boolean edge2Type5=checkType5(reconciliationBuilding.getGAMMA_V(), reconciliationBuilding.getGAMMA_V_2());
		boolean edge2Type8=checkType8(reconciliationBuilding.getGAMMA_R(), reconciliationBuilding.getGAMMA_V_2());
		boolean edge2Type9=checkType9(reconciliationBuilding.getGAMMA_V_2(), reconciliationBuilding.getGAMMA_R());
		boolean edge2Type11=checkType11(reconciliationBuilding.getGAMMA_R(), reconciliationBuilding.getGAMMA_V_2());


		boolean edge3Type5=checkType5(reconciliationBuilding.getGAMMA_V(), reconciliationBuilding.getGAMMA_V_3());
		boolean edge3Type8=checkType8(reconciliationBuilding.getGAMMA_R(), reconciliationBuilding.getGAMMA_V_3());
		boolean edge3Type9=checkType9(reconciliationBuilding.getGAMMA_V_3(), reconciliationBuilding.getGAMMA_R());
		boolean edge3Type11=checkType11(reconciliationBuilding.getGAMMA_R(), reconciliationBuilding.getGAMMA_V_3());

		boolean edge12Type12 =checkType12(reconciliationBuilding.getGAMMA_V_1(), reconciliationBuilding.getGAMMA_V_2(), reconciliationBuilding.getGAMMA_R());
		boolean edge12Type13 =checkType12(reconciliationBuilding.getGAMMA_V_1(), reconciliationBuilding.getGAMMA_V_3(), reconciliationBuilding.getGAMMA_R());


		//Star A
		if (edge1Type1 && edge2Type11 && edge3Type8) {
			return Stars.A;
		}

		if (edge1Type1 && edge2Type8 && edge3Type11) {
			return Stars.AR;
		}

		//Star B
		if (v2bar && edge1Type1 && edge2Type8 && edge3Type8) {
			return Stars.B;
		}
		if (v3bar && edge1Type1 && edge2Type8 && edge3Type8) {
			return Stars.BR;
		}

		//Star D
		if (v2bar && edge1Type1 && edge2Type5 && edge3Type5) {
			return Stars.D;
		}
		if (v3bar && edge1Type1 && edge2Type5 && edge3Type5) {
			return Stars.DR;
		}

		//Star E
		if ( edge1Type1 && edge2Type5 && edge3Type5 && edge12Type12) {
			return Stars.E;
		}
		if (edge1Type1 && edge2Type5 && edge3Type5 && edge12Type13) {
			return Stars.ER;
		}		

		//Star F
		if ( edge1Type1 && edge2Type8 && edge3Type8) {
			return Stars.F;
		}

		//Star H
		if (edge1Type1 && edge2Type8 && edge3Type9 ) {
			return Stars.H;
		}
		if (edge1Type1 && edge2Type9 && edge3Type8) {
			return Stars.HR;
		}	


		//Star C
		if (edge1Type2 && edge1Type7 && edge2Type5 && edge3Type5) {
			return Stars.C;
		}

		//Star G
		if ( edge1Type1 && edge2Type5 && edge3Type5) {
			return Stars.G;
		}


		return -1;
	}

	/**
	 * v_1     v
	 * @param gammaR
	 * @param gammaV1
	 * @return
	 */
	private boolean checkType1(String gammaR, String gammaV1) {
		return !gammaR.equals(gammaV1);
	}

	/**
	 * v_1 ->  v
	 * @param gammaR
	 * @param gammaV1
	 * @return
	 */
	private boolean checkType2(String gammaR, String gammaV1) {
		return gammaR.equals(gammaV1);
	}


	/**
	 * v_1 -->  v
	 * @param gammaR
	 * @param gammaV1
	 * @return
	 */
	private boolean checkType3(String gammaR, String gammaV1) {

		TreeNode gammaRNode = S.getNodeByLabel(gammaR);
		TreeNode gammaV1Node = S.getNodeByLabel(gammaV1);

		boolean gammaV1Switch= isSwitch(gammaRNode,gammaV1Node);

		return gammaV1Switch && !gammaV1Node.comparable(gammaRNode.getParent());


	}


	/**
	 * v_1 --o-->  v
	 * @param gammaR
	 * @param gammaV1
	 * @return
	 */
	private boolean checkType4(String gammaR, String gammaV1) {

		TreeNode gammaRNode = S.getNodeByLabel(gammaR);

		TreeNode gammaV1Node = S.getNodeByLabel(gammaV1);

		boolean gammaV1Switch= isSwitch(gammaRNode,gammaV1Node);

		boolean comparable= gammaV1Node.comparable(gammaRNode.getParent());

		return gammaV1Switch && comparable && gammaV1Node.getDistance(gammaRNode.getParent())==1;

	}

	/**
	 * v		v_i     
	 * @return
	 */
	private boolean checkType5(String gammaV, String gammaVi) {
		return !gammaV.equals(gammaVi);
	}

	/**
	 * v  <-   v_i
	 * @return
	 */
	private boolean checkType6(String gammaR, String gammaVi) {
		return gammaVi.equals(gammaR);
	}

	/**
	 * v_1  <-   v
	 * @return
	 */
	private boolean checkType7(String gammaV, String gammaR) {
		return gammaV.equals(gammaR);
	}

	/**
	 * v_1  <-   v
	 * @return
	 */
	private boolean checkType8(String gammaV, String gammaVi) {
		return gammaV.equals(gammaVi);
	}

	/**
	 * v  <--  v_i
	 * @param gammaR
	 * @param gammaV1
	 * @return
	 */
	private boolean checkType9(String gammaVi, String gammaR) {

		TreeNode gammaRNode = S.getNodeByLabel(gammaR);
		TreeNode gammaViNode = S.getNodeByLabel(gammaVi);

		boolean gammaViSwitch= isSwitch(gammaRNode,gammaViNode);

		return gammaViSwitch && !gammaViNode.comparable(gammaRNode.getParent());


	}


	/**
	 * v <--o-- v_i
	 * @param gammaR
	 * @param gammaV1
	 * @return
	 */
	private boolean checkType10(String gammaR, String gammaVi) {

		TreeNode gammaRNode = S.getNodeByLabel(gammaR);

		TreeNode gammaViNode = S.getNodeByLabel(gammaVi);

		boolean gammaViSwitch= isSwitch(gammaRNode,gammaViNode);

		boolean comparable= gammaViNode.comparable(gammaRNode.getParent());

		return gammaViSwitch && comparable && gammaViNode.getDistance(gammaRNode.getParent())==1;

	}

	/**
	 * v <--o+-- v_i
	 * @param gammaR
	 * @param gammaV1
	 * @return
	 */
	private boolean checkType11(String gammaR, String gammaVi) {

		TreeNode gammaRNode = S.getNodeByLabel(gammaR);

		TreeNode gammaViNode = S.getNodeByLabel(gammaVi);

		boolean gammaViSwitch= isSwitch(gammaRNode,gammaViNode);

		boolean comparable= gammaViNode.comparable(gammaRNode);

		return gammaViSwitch && comparable && gammaViNode.getDistance(gammaRNode)==1;

	}

	/**
	 * v_i  ->   v_j
	 * @return
	 */
	private boolean checkType12(String gammaVi, String gammaVj, String gammaR) {
		TreeNode gammaViNode=S.getNodeByLabel(gammaVi);
		TreeNode gammaVjNode=S.getNodeByLabel(gammaVj);
		TreeNode gammaVRNode=S.getNodeByLabel(gammaR);
		return (!gammaVRNode.comparable(gammaVjNode))&& !gammaVRNode.comparable(gammaViNode);
	}

	//TODOOOOOO
	private boolean checkBar(String gammaVi,String gammaV) {
		return false;
	}



	private boolean isSwitch(TreeNode gammaRNode, TreeNode gammaV1Node) {

		return gammaRNode.comparable(gammaV1Node);
	}

	private void updateMappingsBasedOnType(int type) {

		v2Mapping=reconciliationBuilding.getCurrentStarMapping();
		v3Mapping=reconciliationBuilding.getCurrentStarMapping();

		switch (type) {

		case Stars.A:
			updateEntry(v2Mapping, reconciliationBuilding.getLabel_R(), S.getNodeByLabel(S.getNodeByLabel(reconciliationBuilding.getGAMMA_R()).getParent()));
			updateEntry(v2Mapping, reconciliationBuilding.getLabel_V(), reconciliationBuilding.getGAMMA_R());
			v2Weight=reconciliationBuilding.getCurrentMappingWeight()-1;
			updateEntry(v3Mapping, reconciliationBuilding.getLabel_V(), reconciliationBuilding.getGAMMA_V_1());
			v3Weight=reconciliationBuilding.getCurrentMappingWeight();
			break;
		case Stars.AR:
			updateEntry(v3Mapping, reconciliationBuilding.getLabel_R(), S.getNodeByLabel(S.getNodeByLabel(reconciliationBuilding.getGAMMA_R()).getParent()));
			updateEntry(v3Mapping, reconciliationBuilding.getLabel_V(), reconciliationBuilding.getGAMMA_R());
			v3Weight=reconciliationBuilding.getCurrentMappingWeight()-1;
			updateEntry(v2Mapping, reconciliationBuilding.getLabel_V(), reconciliationBuilding.getGAMMA_V_1());
			v2Weight=reconciliationBuilding.getCurrentMappingWeight();
			break;
		case Stars.B:
			updateEntry(v2Mapping, reconciliationBuilding.getLabel_V(), reconciliationBuilding.getGAMMA_R());
			updateEntry(v2Mapping, reconciliationBuilding.getLabel_V_2(), reconciliationBuilding.getGAMMA_R());
			v2Weight=reconciliationBuilding.getCurrentMappingWeight()-1;
			updateEntry(v3Mapping, reconciliationBuilding.getLabel_V(), reconciliationBuilding.getGAMMA_R());
			v3Weight=reconciliationBuilding.getCurrentMappingWeight();
			break;
		case Stars.BR:
			updateEntry(v3Mapping, reconciliationBuilding.getLabel_V(), reconciliationBuilding.getGAMMA_R());
			updateEntry(v3Mapping, reconciliationBuilding.getLabel_V_3(), reconciliationBuilding.getGAMMA_R());
			v3Weight=reconciliationBuilding.getCurrentMappingWeight()-1;
			updateEntry(v3Mapping, reconciliationBuilding.getLabel_V(), reconciliationBuilding.getGAMMA_R());
			v2Weight=reconciliationBuilding.getCurrentMappingWeight();
			break;
		case Stars.C:
			v2Weight=reconciliationBuilding.getCurrentMappingWeight()+1;
			v3Weight=reconciliationBuilding.getCurrentMappingWeight()+1;
			break;
		case Stars.D:
			updateEntry(v2Mapping, reconciliationBuilding.getLabel_V(), reconciliationBuilding.getGAMMA_V_1());
			updateEntry(v2Mapping, reconciliationBuilding.getLabel_V_2(), reconciliationBuilding.getGAMMA_V());
			v2Weight=reconciliationBuilding.getCurrentMappingWeight();
			updateEntry(v3Mapping, reconciliationBuilding.getLabel_R(), reconciliationBuilding.getGAMMA_V());
			updateEntry(v3Mapping, reconciliationBuilding.getLabel_V(), reconciliationBuilding.getGAMMA_V_2());
			v3Weight=reconciliationBuilding.getCurrentMappingWeight()+1;
			break;
		case Stars.DR:
			updateEntry(v3Mapping, reconciliationBuilding.getLabel_V(), reconciliationBuilding.getGAMMA_V_1());
			updateEntry(v3Mapping, reconciliationBuilding.getLabel_V_3(), reconciliationBuilding.getGAMMA_V());
			v3Weight=reconciliationBuilding.getCurrentMappingWeight();
			updateEntry(v2Mapping, reconciliationBuilding.getLabel_R(), reconciliationBuilding.getGAMMA_V());
			updateEntry(v2Mapping, reconciliationBuilding.getLabel_V(), reconciliationBuilding.getGAMMA_V_3());
			v2Weight=reconciliationBuilding.getCurrentMappingWeight()+1;
			break;
		case Stars.E:
			updateEntry(v2Mapping, reconciliationBuilding.getLabel_V_2(), reconciliationBuilding.getGAMMA_V_1());
			updateEntry(v2Mapping, reconciliationBuilding.getLabel_V_1(), reconciliationBuilding.getGAMMA_V_2());
			v2Weight=reconciliationBuilding.getCurrentMappingWeight();
			updateEntry(v3Mapping, reconciliationBuilding.getLabel_R(), reconciliationBuilding.getGAMMA_V());
			updateEntry(v3Mapping, reconciliationBuilding.getLabel_V(), reconciliationBuilding.getGAMMA_V_2());
			v3Weight=reconciliationBuilding.getCurrentMappingWeight()+1;
			break;
		case Stars.ER:
			updateEntry(v3Mapping, reconciliationBuilding.getLabel_V_3(), reconciliationBuilding.getGAMMA_V_1());
			updateEntry(v3Mapping, reconciliationBuilding.getLabel_V_1(), reconciliationBuilding.getGAMMA_V_3());
			v3Weight=reconciliationBuilding.getCurrentMappingWeight();
			updateEntry(v2Mapping, reconciliationBuilding.getLabel_R(), reconciliationBuilding.getGAMMA_V());
			updateEntry(v2Mapping, reconciliationBuilding.getLabel_V(), reconciliationBuilding.getGAMMA_V_3());
			v2Weight=reconciliationBuilding.getCurrentMappingWeight()+1;
			break;
		case Stars.F:
			updateEntry(v2Mapping, reconciliationBuilding.getLabel_R(), reconciliationBuilding.getGAMMA_R());
			updateEntry(v2Mapping, reconciliationBuilding.getLabel_V(), reconciliationBuilding.getGAMMA_R());
			v2Weight=reconciliationBuilding.getCurrentMappingWeight();
			updateEntry(v3Mapping, reconciliationBuilding.getLabel_R(), reconciliationBuilding.getGAMMA_R());
			updateEntry(v3Mapping, reconciliationBuilding.getLabel_V(), reconciliationBuilding.getGAMMA_R());
			v3Weight=reconciliationBuilding.getCurrentMappingWeight();
			break;
		case Stars.G:
			updateEntry(v2Mapping, reconciliationBuilding.getLabel_R(), reconciliationBuilding.getGAMMA_V());
			updateEntry(v2Mapping, reconciliationBuilding.getLabel_V(), reconciliationBuilding.getGAMMA_V_3());
			v2Weight=reconciliationBuilding.getCurrentMappingWeight()+1;
			updateEntry(v3Mapping, reconciliationBuilding.getLabel_R(), reconciliationBuilding.getGAMMA_V());
			updateEntry(v3Mapping, reconciliationBuilding.getLabel_V(), reconciliationBuilding.getGAMMA_V_2());
			v3Weight=reconciliationBuilding.getCurrentMappingWeight()+1;
			break;
		case Stars.H:
			updateEntry(v2Mapping, reconciliationBuilding.getLabel_V(), reconciliationBuilding.getGAMMA_V_1());
			v2Weight=reconciliationBuilding.getCurrentMappingWeight();
			updateEntry(v3Mapping, reconciliationBuilding.getLabel_V(), reconciliationBuilding.getGAMMA_V_1());
			v3Weight=reconciliationBuilding.getCurrentMappingWeight()+1;
			break;
		case Stars.HR:
			updateEntry(v3Mapping, reconciliationBuilding.getLabel_V(), reconciliationBuilding.getGAMMA_V_1());
			v3Weight=reconciliationBuilding.getCurrentMappingWeight();
			updateEntry(v2Mapping, reconciliationBuilding.getLabel_V(), reconciliationBuilding.getGAMMA_V_1());
			v2Weight=reconciliationBuilding.getCurrentMappingWeight()+1;
			break;

		}


	}


	private void  updateEntry(String[] mapping, String key, String value) {

		for (String str:mapping) {
			if (str.split("@")[0].equals(key)) {
				str = str.split("@")[0]+"@"+value;
			} 
		}

	}

	public  String[] getV2Mapping() {
		return v2Mapping;
	}

	public  String[] getV3Mapping() {
		return v3Mapping;
	}


	public   Float getV2Weight() {
		return v2Weight;
	}


	public  Float getV3Weight() {
		return v3Weight;
	}



	public  void setS(RootedTree s) {
		S = s;
	}



	public  void setType(int type) {

	}

}
