package com.accenture.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ContaLinhas {

	private String strRootFolderPath;
	private static String strOutputFile;
	BufferedWriter writer;
	BufferedWriter writerComentariosFaturaveis;

	// Linhas contendo os padroes abaixo ser�o consideradas coment�rios
	// n�o faturaveis
	final static String EXCL_PATTERN1 = new String(new char[30]).replace("\0", "*"); // 30
																						// Caracteres
																						// "*"
	final static String EXCL_PATTERN2 = new String(new char[30]).replace("\0", "="); // 30
																						// Caracteres
																						// "="
	final static String EXCL_PATTERN3 = new String(new char[30]).replace("\0", "-"); // 30
																						// Caracteres
																						// "-"
	final static String EXCL_PATTERN4 = new String(new char[30]).replace("\0", "%"); // 30
																						// Caracteres
																						// "%"

	private static List<String> fileExclList = new ArrayList<String>();

	private void init() {
		try {
			fileExclList.add("README.md");
			writer = new BufferedWriter(new FileWriter(strOutputFile));
			writer.write(
					"arquivo_fonte | num_linhas | comentarios_faturaveis | comentarios_nao_faturaveis | linhas_branco | caminho"
							+ "\n");
			writerComentariosFaturaveis = new BufferedWriter(
					new FileWriter(new File(new File(strOutputFile).getParent(), "comentarios_faturaveis.txt")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void process() {

		try {
			init();
			File[] files = new File(this.getStrRootFolderPath()).listFiles();
			processFiles(files);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void processDirectories(File[] files) {

		for (File file : files) {
			if (file.isDirectory()) {
				System.out.println("Diretorio: " + file.getName());
				if (file.getName().toString().equalsIgnoreCase("cobol_algol")
						|| file.getName().toString().equalsIgnoreCase("cobol")
						|| file.getName().toString().equalsIgnoreCase("algol")
						|| file.getName().toString().equalsIgnoreCase("sads")
						|| file.getName().toString().equalsIgnoreCase("wfl")) {
					processFiles(file.listFiles());
				}
				processDirectories(file.listFiles());
			}
		}
	}

	public void processFiles(File[] files) {
		for (File file : files) {
			if (file.isDirectory()) {
				System.out.println("Diretorio: " + file.getName());
				processFiles(file.listFiles());
			} else {

				if (!(fileExclList.contains(file.getName()))) {

					// System.out.println("Arquivo: " + file.getName());
					//
					try {
						countLines(file);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	private void countLines(File inputFile) throws IOException {

		try {

			if (inputFile.isFile()) {

				StringBuffer comentariosFaturaveis = new StringBuffer();

				BufferedReader reader;

				try {
					reader = new BufferedReader(new FileReader(inputFile));

					int intLines = 0;
					int intComentFaturavel = 0;
					int intComentNaoFaturavel = 0;
					int intBlankLines = 0;

					int intRowLen = 0;
					int intLineBegin = 0;

					String strCurrentLine;
					String strUtilLine;
					String strCol7;
					String strComentChar;

					while ((strCurrentLine = reader.readLine()) != null) {

						intLines++;

						if (strCurrentLine.trim().equals("")) {
							intBlankLines++;
						} else {
							if (strCurrentLine.trim().length() < 7) {
								intBlankLines++;
							} else {

								if (!StringUtils.isNumeric(strCurrentLine.substring(0, 6))) {
									intLineBegin = 1;
									strCol7 = strCurrentLine.trim().substring(0, 1);
									strComentChar = "%";
								} else {
									intLineBegin = 7;
									strCol7 = strCurrentLine.trim().substring(6, 7);
									strComentChar = "*";
								}

								intRowLen = strCurrentLine.trim().length();

								if (strCurrentLine.length() < 72) {
									intRowLen = strCurrentLine.length();
								} else {
									intRowLen = 72;
								}

								if (strCurrentLine.substring(intLineBegin, intRowLen).trim().equals("")) {
									intBlankLines++;
								} else {

									strUtilLine = strCurrentLine.substring(intLineBegin, intRowLen).trim();

									// if (
									// strUtilLine.trim().contains(EXCL_PATTERN1)
									// ||
									// strUtilLine.trim().contains(EXCL_PATTERN2)
									// ||
									// strUtilLine.trim().contains(EXCL_PATTERN3)
									// ||
									// strUtilLine.trim().contains(EXCL_PATTERN4))
									// {
									// intComentNaoFaturavel++;
									//
									// }else{
									//
									// if (strCol7.equals(strComentChar)){
									// if (intLineBegin==1){
									//
									// if
									// (strCurrentLine.substring(strCurrentLine.indexOf(strComentChar)+1,
									// intRowLen).trim().equals("")) {
									// intBlankLines++;
									// }else{
									// comentariosFaturaveis.append(strCurrentLine).append("\n");
									// intComentFaturavel++;
									// }
									// }else{
									// comentariosFaturaveis.append(strCurrentLine).append("\n");
									// intComentFaturavel++;
									// }
									// }
									// }

									/**
									 * strCol7 = Primeira Coluna util (1 ou 7)
									 * de uma linha strComentChar = "*" ou "%"
									 */
									if (strCol7.equals(strComentChar)) {

										boolean b1 = verificaComentarioUtil(strUtilLine);
										boolean b2 = StringUtil.isFaturavel(strCurrentLine.substring(7));
										if (verificaComentarioUtil(strUtilLine)) {
											intComentFaturavel++;
										} else {
											System.out.println(strCurrentLine.substring(0, 70));
											intComentNaoFaturavel++;
										}
									}
								}
							}
						}
					}

					writer.write(inputFile.getName() + "|" + intLines + "|" + intComentFaturavel + "|"
							+ intComentNaoFaturavel + "|" + intBlankLines + "|" + inputFile.getPath() + "\n");
					reader.close();

					writerComentariosFaturaveis.append("Arquivo: ").append(inputFile.getName()).append("\n");
					writerComentariosFaturaveis.append("Comentarios Faturaveis: ").append("\n")
							.append(comentariosFaturaveis);
					writerComentariosFaturaveis.append(
							"-------------------------------------------------------------------------------------------------\n");
					writerComentariosFaturaveis.flush();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getStrRootFolderPath() {
		return strRootFolderPath;
	}

	public void setStrRootFolderPath(String strRootFolderPath) {
		this.strRootFolderPath = strRootFolderPath;
	}

	public String getStrOutputFile() {
		return strOutputFile;
	}

	public void setStrOutputFile(String strOutputFile) {
		this.strOutputFile = strOutputFile;
	}

	protected boolean verificaComentarioUtil(String linhaUtil) {
		// Remover caracteres duplicados
		// banana fica ban
		// +=============+ fica +=

		// Quantidade de caracteres restante > 5
		// true
		// caso contrario
		// false

		String stringSimplificada = StringUtil.removeCharacteresDuplicados(linhaUtil);
		return stringSimplificada.length() > 5;
	}
}
