package nodelab.com.labelUndirectedGeneTrees.structures;

import java.util.Set;

public class LeafS extends Leaf implements LeafSInterface {

	private Set<LeafGInterface> mappingCounterImage;

	@Override
	public Set<LeafGInterface> getMappingCounterImage() {
		return mappingCounterImage;
	}

	@Override
	public void setMappingCounterImage(Set<LeafGInterface> mappingCounterImage) {
		this.mappingCounterImage=mappingCounterImage;		
	}

}
