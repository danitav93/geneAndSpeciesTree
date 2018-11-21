package nodelab.com.labelUndirectedGeneTrees.structures;

public class Leaf extends TreeNode implements LeafInterface{

	private String label;
	
	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public void setLabel(String label) {
		this.label=label;
	}

	
}
