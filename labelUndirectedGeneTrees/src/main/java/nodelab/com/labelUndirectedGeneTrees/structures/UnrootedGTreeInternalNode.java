package nodelab.com.labelUndirectedGeneTrees.structures;

public class UnrootedGTreeInternalNode extends TreeNode implements UnrootedGTreeInternalNodeInterface{

	private float parentWeight;
	private float rightWeight;
	private float leftWeight;
	
	private boolean isParentLabeled=false;
	private boolean isRightLabeled=false;
	private boolean isLeftLabeled=false;
	
	@Override
	public float getParentWeight() {
		return parentWeight;
	}

	@Override
	public float getLeftWeight() {
		return leftWeight;
	}

	@Override
	public float getRightWeight() {
		return rightWeight;
	}

	@Override
	public boolean isParentLabeled() {
		return isParentLabeled;
	}

	@Override
	public boolean isLeftLabeled() {
		return isLeftLabeled;
	}

	@Override
	public boolean isRightLabeled() {
		return isRightLabeled;
	}

	@Override
	public void setParentWeight(float weight) {
		this.parentWeight=weight;
	}

	@Override
	public void setLeftWeight(float weight) {
		this.leftWeight=weight;
	}

	@Override
	public void setRightWeight(float weight) {
		this.rightWeight=weight;
	}

	@Override
	public void setParentLabeled(boolean labeled) {
		this.isParentLabeled=labeled;
	}

	@Override
	public void setLeftLabeled(boolean labeled) {
		this.isLeftLabeled=labeled;
	}

	@Override
	public void setRightLabeled(boolean labeled) {
		this.isRightLabeled=labeled;
	}

	@Override
	public void setCurrentWeight(float weight) {
		
		if (!isParentLabeled) {
			parentWeight=weight;
			setParentLabeled(true);
			//metto anche il peso al nodo (interno) dell'altro arco per non ripetere l'operazione
			if (getParent() instanceof UnrootedGTreeInternalNode && isLeftChild()) {
				((UnrootedGTreeInternalNode)getParent()).setLeftLabeled(true);
				((UnrootedGTreeInternalNode)getParent()).setLeftWeight(weight);
			} else if (getParent() instanceof UnrootedGTreeInternalNode && isRightChild()) {
				((UnrootedGTreeInternalNode)getParent()).setRightLabeled(true);
				((UnrootedGTreeInternalNode)getParent()).setRightWeight(weight);
			} else if (getParent() instanceof UnrootedGTreeInternalNode) {
				((UnrootedGTreeInternalNode)getParent()).setParentLabeled(true);
				((UnrootedGTreeInternalNode)getParent()).setParentWeight(weight);
			}
			return;
		}
		
		if (!isLeftLabeled) {
			leftWeight=weight;
			setLeftLabeled(true);
			if (getLeft() instanceof UnrootedGTreeInternalNode) {
				((UnrootedGTreeInternalNode)getLeft()).setParentLabeled(true);
				((UnrootedGTreeInternalNode)getLeft()).setParentWeight(weight);
			}
			return;
		}
		if (!isRightLabeled) {
			rightWeight=weight;
			setRightLabeled(true);
			if (getRight() instanceof UnrootedGTreeInternalNode) {
				((UnrootedGTreeInternalNode)getRight()).setParentLabeled(true);
				((UnrootedGTreeInternalNode)getRight()).setParentWeight(weight);
			}
			return;
		}
	}

	@Override
	public boolean isLabeled() {
		return isParentLabeled && isLeftLabeled && isRightLabeled;
	}

	@Override
	public String print() {
		return UnrootedTreeNexusTreeMapFormat.print(getLeft(),getRight());
	}

}
