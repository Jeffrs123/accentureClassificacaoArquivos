package com.accenture.util;

public class ContaLinhasMain {

	public static void main(String[] args) {
		
	
		String strOutputFile = args[0];			
		String strRootFolder = args[1];
			

		try {
			
			//Contagem de linhas de programas Cobol, Algol e WFL
			ContaLinhas cl = new ContaLinhas();
			cl.setStrOutputFile(strOutputFile);
			cl.setStrRootFolderPath(strRootFolder);
			cl.process();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Ending");
		
	}
	
	
	
	

}
