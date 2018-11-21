package nodelab.com.labelUndirectedGeneTrees.input;

public class SimpleInput implements InputInterface{

	@Override
	public String getS() {
		
		return "((A,B),C)";
		
	}

	@Override
	public String getUnrootedG() {

		return "(((((((((((A0,B0),C0),C1),B1),A1),A2),B2),C2),C3),A3),B3)";
				
	}

	@Override
	public String getMapping() {
		
		return "A1 : A, A0 : A, A2 : A, A3 :A, B0 : B, B1 : B, B2 : B, B3 : B, C0 : C, C1 : C, C2 : C, C3 : C";
		
	}

}
