package nodelab.com.labelUndirectedGeneTrees.structures;

import nodelab.com.labelUndirectedGeneTrees.constants.Errors;
import nodelab.com.labelUndirectedGeneTrees.constants.Stars;

public class GStar   {

	private static RootedTree S;


	private String[] v2Mapping;

	private String[] v3Mapping;

	private float v2Weight;

	private float v3Weight;


	public GStar(String labelR, String gammaR, String labelV1,  String gammaV1, String labelV, String gammaV,String labelV2, String gammaV2, String labelV3, String gammaV3, String[] mapping, float weight) {

		if (S==null) {
			throw new RuntimeException(Errors.ROOTED_TREE_S_NOT_INITIALIZED_ERROR);
		}

		int type=-1;

		if (type==-1) {
			throw new RuntimeException(Errors.STAR_NOT_FOUND_ERROR+": gammaR="+gammaR);
		}
		updateMappingsBasedOnType(labelR,gammaR,labelV1,gammaV1,labelV,gammaV,labelV2,gammaV2,labelV3,gammaV3,type,mapping, weight,type);

	}

	private void updateMappingsBasedOnType(String labelR, String gammaR, String labelV1,  String gammaV1, String labelV, String gammaV,String labelV2, String gammaV2, String labelV3, String gammaV3, String[] mapping, float weight,int type) {

		v2Mapping=mapping;
		v3Mapping=mapping;
		
		switch (type) {

		case Stars.A:
			updateEntry(v2Mapping, labelR, S.getParentOf(gammaR));
			updateEntry(v2Mapping, labelV, gammaR);
			v2Weight=weight-1;
			updateEntry(v3Mapping, labelV, gammaV1);
			v3Weight=weight;
			break;
		case Stars.B:
			updateEntry(v2Mapping, labelV, gammaR);
			updateEntry(v2Mapping, labelV2, gammaR);
			v2Weight=weight-1;
			updateEntry(v3Mapping, labelV, gammaR);
			v2Weight=weight;
			break;
		case Stars.C:
			break;
		case Stars.D:
			break;
		case Stars.E:
			break;
		case Stars.F:
			break;
		case Stars.G:
			break;
		case Stars.H:
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
