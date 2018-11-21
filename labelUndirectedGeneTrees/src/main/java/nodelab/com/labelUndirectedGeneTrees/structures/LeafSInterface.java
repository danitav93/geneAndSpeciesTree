package nodelab.com.labelUndirectedGeneTrees.structures;

import java.util.Set;

public interface LeafSInterface extends LeafInterface {

	Set<LeafGInterface> getMappingCounterImage();
	
	void setMappingCounterImage(Set<LeafGInterface> mappingCounterImage);
	
}
