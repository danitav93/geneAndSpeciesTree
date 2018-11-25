package nodelab.com.labelUndirectedGeneTrees.constants;

public class Errors {

	public static final String UNROOTED_TREE_ERROR_INTERNAL_NODE= "Undirected graph must be initialized: CURRENT-INTERNAL-NODE";
	
	
	public static final String TREE_NODE_ERROR_PARENT = "Undirected graph must be initialized: PARENT-NODE";


	public static final String TREE_NODE_ERROR_CHILDREN ="Undirected graph must be initialized: CHILDREN";


	public static final String UNROOTED_TREE_GENERATOR_ERROR_WRONG_FORMAT_FIRST_NODE = "Wrong format for unrooted gene tree: FIRST-NODE";


	public static final String UNROOTED_TREE_GENERATOR_ERROR_WRONG_FORMAT = "Wrong format for unrooted gene tree";


	public static final String UNROOTED_TREE_GENERATOR_ERROR_WRONG_FORMAT_NOT_COMMA_FOUND = "Wrong format for unrooted gene tree: NOT-COMMA-FOUND";


	public static final String UNROOTED_TREE_GENERATOR_ERROR_LEAF_WRONG_FORMAT = "Wrong format for unrooted gene tree: LEAF";


	public static final String UNROOTED_TREE_GENERATOR_ERROR_WRONG_FORMAT_EXTERNAL_BRACKET = "Wrong format for unrooted gene tree: EXTERNAL-BRACKETS";


	public static final String UNROOTED_TREE_GENERATOR_ERROR_WRONG_FORMAT_BRACKETS = "Wrong format for unrooted gene tree: BRACKETS";


	public static final String UNROOTED_TREE_GENERATOR_ERROR_WRONG_FORMAT_NOT_MIDDLE_FOUND = "Wrong format for unrooted gene tree: MIDDLE";


	public static final String UNROOTED_TREE_GENERATOR_ERROR_WRONG_FORMAT_NO_INTERNAL_NODE_FOUND = "No internal node found";


	public static final String TREE_NODE_ERROR_IS_LEFT_OR_RIGHT = "No parent found!";


	public static final String BUILDING_EUCALYPT_INPUT_ERROR = "Error in generating input for Eucalypt";


	public static final String LAUNCHING_EUCALYPT_JAR_ERROR = "Error while launching Eucalypt jar";


	public static final String EUCALYPT_ERROR = "Eucalypt didn't generate any output";


	public static final String READING_EUCALYPT_OUTPUT_ERROR = "Error while reading eucalypt";


	public static final String UPDATING_RECONCILIATION_ERROR_NOT_MAPPING_FOUND = "Error while updating the reconciliation: NOT MAPPING FOUND";


	public static final String ROOTED_TREE_S_NOT_INITIALIZED_ERROR = "Rooted tree S has not been initialized";


	public static final String STAR_NOT_FOUND_ERROR = "STAR NOT FOUND EXCEPTION";


	public static final String TREE_NODE_DISTANCE_ERROR = "Cannot compute distance on non-comparable or non ascendant nodes";
	
	
}
