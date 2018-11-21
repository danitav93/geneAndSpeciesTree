package nodelab.com.labelUndirectedGeneTrees.structures;

public class LeafG extends Leaf implements LeafGInterface {

	LeafSInterface mappingImage;
	
	@Override
	public LeafSInterface getMappingImage() {
		return mappingImage;
	}

	@Override
	public void setMappingImage(LeafSInterface mappingImage) {
		this.mappingImage=mappingImage;
	}

}
