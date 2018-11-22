package nodelab.com.labelUndirectedGeneTrees.input;

public class SimpleInput implements InputInterface{

	@Override
	public String getS() {
		
		return "((D,((A,B),(C,F))),E)";
		
	}

	@Override
	public String getUnrootedG() {

		return "((B0,((F0,E0),(D0,C0))),A0)";
				
	}

	@Override
	public String getMapping() {
		
		return "A0 : A, B0 : B, C0 : C, D0: D, E0: E, F0: F";
		
	}

}
