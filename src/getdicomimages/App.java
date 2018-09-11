package getdicomimages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

public class App {

	public static void main(String[] args) {

		try {
			getArraysTrueValidacao();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getArraysTrueTreino() throws FileNotFoundException, IOException {
		
		ArrayList<String> arrayTrues = new ArrayList<String>();
		ArrayList<String> arrayFalses = new ArrayList<String>();
		
		
		//TRUES
		try (BufferedReader brtrue = new BufferedReader(new FileReader(new File("src//getdicomimages//teste_VERDADEIRO.txt")))) {
		    String line = null;
		    while ((line = brtrue.readLine()) != null) {
		       arrayTrues.add(line);
		    }
		}
		
		//FALSES
		try (BufferedReader brfalse = new BufferedReader(new FileReader("src//getdicomimages//teste_FALSO.txt"))) {
		    String line = null;
		    while ((line = brfalse.readLine()) != null) {
			       arrayFalses.add(line);
		    }
		}
				
		String pathOrigin = "C:\\Users\\Rodrigo\\Desktop\\diagnostico\\PROSTATEx-Filtrados(12-000007)";
		String pathDestFalses = "C:\\Users\\Rodrigo\\Desktop\\diagnostico\\Treino\\false";
		String pathDestTrues = "C:\\Users\\Rodrigo\\Desktop\\diagnostico\\Treino\\true";
				
		for (String string : arrayFalses) {
			
			String newname = string.substring(10,14)+"-12-000007.dcm";

			try {
				copyFileUsingApacheCommonsIO(new File(pathOrigin+"\\"+newname), new File(pathDestFalses+"\\"+newname));
				System.out.println("COPIADO --> "+newname);
			} catch (IOException e) {
			}
			
		}
		
		for (String string : arrayTrues) {
			String newname = string.substring(10,14)+"-12-000007.dcm";

			try {
				copyFileUsingApacheCommonsIO(new File(pathOrigin+"\\"+newname), new File(pathDestTrues+"\\"+newname));
				System.out.println("COPIADO --> "+newname);
			} catch (IOException e) {
			}		
			}

	}
	
	public static void getArraysTrueValidacao() throws FileNotFoundException, IOException {
		ArrayList<String> arrayTrues = new ArrayList<String>();
		ArrayList<String> arrayFalses = new ArrayList<String>();
		
		
		//TRUES
		try (BufferedReader brtrue = new BufferedReader(new FileReader(new File("src//getdicomimages//val_VERDADEIRO.txt")))) {
		    String line = null;
		    while ((line = brtrue.readLine()) != null) {
		       arrayTrues.add(line);
		    }
		}
		
		//FALSES
		try (BufferedReader brfalse = new BufferedReader(new FileReader("src//getdicomimages//val_FALSO.txt"))) {
		    String line = null;
		    while ((line = brfalse.readLine()) != null) {
			       arrayFalses.add(line);
		    }
		}
				
		String pathOrigin = "C:\\Users\\Rodrigo\\Desktop\\diagnostico\\PROSTATEx-Filtrados(12-000007)";
		String pathDestFalses = "C:\\Users\\Rodrigo\\Desktop\\diagnostico\\Val\\false";
		String pathDestTrues = "C:\\Users\\Rodrigo\\Desktop\\diagnostico\\Val\\true";
				
		for (String string : arrayFalses) {
			
			String newname = string.substring(10,14)+"-12-000007.dcm";

			try {
				copyFileUsingApacheCommonsIO(new File(pathOrigin+"\\"+newname), new File(pathDestFalses+"\\"+newname));
				System.out.println("COPIADO --> "+newname);
			} catch (IOException e) {
			}
			
		}
		
		for (String string : arrayTrues) {
			String newname = string.substring(10,14)+"-12-000007.dcm";

			try {
				copyFileUsingApacheCommonsIO(new File(pathOrigin+"\\"+newname), new File(pathDestTrues+"\\"+newname));
				System.out.println("COPIADO --> "+newname);
			} catch (IOException e) {
			}		
			}
	}
	
	public static void prepararDataset() {
		String pathSource = "C:\\Users\\rodrigofg\\Documents\\Diagnostico por imagem\\PROSTATEx";
		String pathDest = "C:\\Users\\rodrigofg\\Documents\\Diagnostico por imagem\\PROSTATEx-Filtrados(12-000007)";
		
		for(String folder :listFiles(pathSource)){
			String newname = folder.substring(72,76)+"-12-000007.dcm";

			try {
				copyFileUsingApacheCommonsIO(new File(folder), new File(pathDest+"\\"+newname));
				System.out.println("COPIADO --> "+newname);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static ArrayList<String> listFiles(String folder) {
		
		ArrayList<String> folders = new ArrayList<String>();
		
		File directory = new File(folder);
		File[] contents = directory.listFiles();
		for (File f : contents) {
			//System.out.println(f.getAbsolutePath());
			
			File directory2 = new File(f.getAbsolutePath());
			File[] contents2 = directory2.listFiles();
			for (File f2 : contents2) {
				//System.out.println(f.getAbsolutePath());
				File directory3 = new File(f2.getAbsolutePath());
				File[] contents3 = directory3.listFiles();
				for (File f3 : contents3) {
					//System.out.println(f.getAbsolutePath());
					if(f3.getName().substring(0,2).equals("12"))
						folders.add(f3.getAbsolutePath()+"\\000007.dcm");
				}				
			}		
		}
		
		return folders;

	}
	
	private static void copyFileUsingApacheCommonsIO(File source, File dest)
	        throws IOException {
	    FileUtils.copyFile(source, dest);
	
	}

}
