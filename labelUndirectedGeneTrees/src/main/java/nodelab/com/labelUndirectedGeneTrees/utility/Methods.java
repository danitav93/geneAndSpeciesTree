package nodelab.com.labelUndirectedGeneTrees.utility;

import nodelab.com.labelUndirectedGeneTrees.constants.Errors;

public class Methods {

public static int findMiddleIndex(String unrootedG) {
		
		String minusOutsideBracket=unrootedG.substring(unrootedG.indexOf("(")+1, unrootedG.lastIndexOf(")"));

		int i=0;
		int sum=0;
		boolean end=false;
		
		//se il sinistro è una foglia la metà sarà la prima virgola
		if (minusOutsideBracket.charAt(0)!='(') {
			try {
				return unrootedG.indexOf(",");
			} catch (IndexOutOfBoundsException e) {
				throw new RuntimeException(Errors.UNROOTED_TREE_GENERATOR_ERROR_WRONG_FORMAT_NOT_COMMA_FOUND+": "+unrootedG);
			}
		}
		
		while (!end) {
			if (minusOutsideBracket.charAt(i)=='(') {
				sum++;
			}
			if (minusOutsideBracket.charAt(i)==')') {
				sum--;
				if (sum==0) {
					end=true;
				} else if (sum<0) {
					throw new RuntimeException(Errors.UNROOTED_TREE_GENERATOR_ERROR_WRONG_FORMAT_BRACKETS+": "+unrootedG);
				}
			}
			i++;
			if (i>=minusOutsideBracket.length()) {
				throw new RuntimeException(Errors.UNROOTED_TREE_GENERATOR_ERROR_WRONG_FORMAT_BRACKETS+": "+unrootedG);
			}
		}
		return i+1; //il più 1 è per il fatto che ho rimosso le parentesi esterne all'inizio
	}
	
public static int findMiddleIndexComma(String unrootedG) {
	
	String minusOutsideBracket=unrootedG.substring(unrootedG.indexOf("(")+1, unrootedG.lastIndexOf(")"));

	int i=0;
	int sum=0;
	boolean end=false;
	
	//se il sinistro è una foglia la metà sarà la prima virgola
	if (minusOutsideBracket.charAt(0)!='(') {
		try {
			return unrootedG.indexOf(",");
		} catch (IndexOutOfBoundsException e) {
			throw new RuntimeException(Errors.UNROOTED_TREE_GENERATOR_ERROR_WRONG_FORMAT_NOT_COMMA_FOUND+": "+unrootedG);
		}
	}
	
	while (!end) {
		if (minusOutsideBracket.charAt(i)=='(') {
			sum++;
		}
		if (minusOutsideBracket.charAt(i)==')') {
			sum--;
			if (sum==0) {
				end=true;
			} else if (sum<0) {
				throw new RuntimeException(Errors.UNROOTED_TREE_GENERATOR_ERROR_WRONG_FORMAT_BRACKETS+": "+unrootedG);
			}
		}
		i++;
		if (i>=minusOutsideBracket.length()) {
			throw new RuntimeException(Errors.UNROOTED_TREE_GENERATOR_ERROR_WRONG_FORMAT_BRACKETS+": "+unrootedG);
		}
	}
	
	return i+unrootedG.substring(i).indexOf(","); 
}








}
