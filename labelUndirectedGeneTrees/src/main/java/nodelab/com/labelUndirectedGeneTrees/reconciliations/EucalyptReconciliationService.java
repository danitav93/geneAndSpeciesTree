package nodelab.com.labelUndirectedGeneTrees.reconciliations;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nodelab.com.labelUndirectedGeneTrees.constants.Errors;
import nodelab.com.labelUndirectedGeneTrees.systemCall.SystemCommandExecutor;

public class EucalyptReconciliationService implements RenconciliationServiceInterface {

	/*public static void main(String[] args) {
		try {

			List<String> commands = new ArrayList<String>();
			commands.add("java");
			commands.add("-jar");
			commands.add("C:\\Users\\User\\Desktop\\biologia\\eucalypt\\Eucalypt-1.0.4\\Eucalypt-1.0.4.jar");
			commands.add("-i");
			commands.add("C:\\Users\\User\\Desktop\\biologia\\eucalypt\\Eucalypt-1.0.4\\myProgram\\input.txt");
			commands.add("-task");
			commands.add("3");
			commands.add("-cc");
			commands.add("0");
			commands.add("-cd");
			commands.add("1");
			commands.add("-ch");
			commands.add("1");
			commands.add("-cl");
			commands.add("1");
			commands.add("-o");
			commands.add("C:\\Users\\\\User\\\\Desktop\\\\biologia\\\\eucalypt\\\\Eucalypt-1.0.4\\\\myProgram\\output.txt");

			SystemCommandExecutor commandExecutor = new SystemCommandExecutor(commands);

			commandExecutor.executeCommand();


			// stdout and stderr of the command are returned as StringBuilder objects
			StringBuilder stdout = commandExecutor.getStandardOutputFromCommand();
			StringBuilder stderr = commandExecutor.getStandardErrorFromCommand();
			String subStringWeightLine=stdout.substring(stdout.indexOf("Optimum cost ="),stdout.indexOf("Optimum cost =")+stdout.substring(stdout.indexOf("Optimum cost = ")).indexOf("\n"));
			float weight= Float.parseFloat(subStringWeightLine.substring(subStringWeightLine.indexOf("= ")+2));
			System.out.println(stdout);

		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/

	@Override
	public float getWeight(String S, String rootedG, String mapping) {


		try {

			//creo il file di input per eucalypt
			FileWriter fileout= new FileWriter("C:\\Users\\User\\Desktop\\biologia\\eucalypt\\Eucalypt-1.0.4\\myProgram\\input.txt");

			fileout.write("#NEXUS\n");
			fileout.write("BEGIN HOST;\n");
			fileout.write("    TREE HOST = ");
			for (int i=0; i<S.length();i++) {
				fileout.write(S.charAt(i));
			}
			fileout.write(";\n");
			fileout.write("ENDBLOCK;\n");
			fileout.write("BEGIN PARASITE;\n");
			fileout.write("    TREE PARASITE = ");
			for (int i=0; i<rootedG.length();i++) {
				fileout.write(rootedG.charAt(i));
			}
			fileout.write(";\n");
			fileout.write("ENDBLOCK;\n");
			fileout.write("BEGIN DISTRIBUTION;\n");
			fileout.write("    RANGE\n");
			fileout.write("        ");
			for (int i=0; i<mapping.length();i++) {
				fileout.write(mapping.charAt(i));
			}
			fileout.write(";\n");
			fileout.write("ENDBLOCK;");

			fileout.close();
		} catch (IOException e) {
			throw new RuntimeException(Errors.BUILDING_EUCALYPT_INPUT_ERROR);
		}

		//chiamata di sistema a eucalypt
		try {

			List<String> commands = new ArrayList<String>();
			commands.add("java");
			commands.add("-jar");
			commands.add("C:\\Users\\User\\Desktop\\biologia\\eucalypt\\Eucalypt-1.0.4\\Eucalypt-1.0.4.jar");
			commands.add("-i");
			commands.add("C:\\Users\\User\\Desktop\\biologia\\eucalypt\\Eucalypt-1.0.4\\myProgram\\input.txt");
			commands.add("-task");
			commands.add("3");
			commands.add("-cc");
			commands.add("0");
			commands.add("-cd");
			commands.add("1");
			commands.add("-ch");
			commands.add("1");
			commands.add("-cl");
			commands.add("1");
			commands.add("-o");
			commands.add("C:\\Users\\\\User\\\\Desktop\\\\biologia\\\\eucalypt\\\\Eucalypt-1.0.4\\\\myProgram\\output.txt");

			SystemCommandExecutor commandExecutor = new SystemCommandExecutor(commands);

			commandExecutor.executeCommand();


			// stdout and stderr of the command are returned as StringBuilder objects
			StringBuilder stdout = commandExecutor.getStandardOutputFromCommand();
			try {
				String subStringWeightLine=stdout.substring(stdout.indexOf("Optimum cost ="),stdout.indexOf("Optimum cost =")+stdout.substring(stdout.indexOf("Optimum cost = ")).indexOf("\n"));
				return Float.parseFloat(subStringWeightLine.substring(subStringWeightLine.indexOf("= ")+2));
			} catch (Exception e) {
				throw new RuntimeException(Errors.EUCALYPT_ERROR+":"+stdout );
			}

		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(Errors.LAUNCHING_EUCALYPT_JAR_ERROR);

		}

	}











}
