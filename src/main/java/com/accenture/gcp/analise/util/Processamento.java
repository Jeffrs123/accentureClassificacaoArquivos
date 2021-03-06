package com.accenture.gcp.analise.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.accenture.gcp.analise.entidade.Arquivo;
import com.accenture.gcp.analise.entidade.ArquivoEncontrado;
import com.accenture.gcp.analise.menu.TemplateStatus;
import com.accenture.gcp.analise.util.StringUtil;

public class Processamento {

	private static String DIRETORIO_FILE_ANALISAR = "C:\\TEMP\\ANALISE\\Cópia de componentes.csv";
	//	private static String DIRETORIOS_RAIZ_ESCANEAR =  "C:\\Users\\jefferson.r.a.silva\\OneDrive - Accenture\\Documents\\TESTE";
	private static String DIRETORIOS_RAIZ_ESCANEAR =  "C:\\Projeto BVS\\as400code\\MF";
	private static String DIRETORIO_RAIZ_OUT =  "C:\\TEMP";

	private static List<String> listaRelatorio = new ArrayList<String>();
	private static List<String> listaArquivos = new ArrayList<String>(); // OK
	private static List<String> listaLoggers = new ArrayList<String>();
	private static List<ArquivoEncontrado> listaArquivosEncontrados = new ArrayList<ArquivoEncontrado>(); // OK
	private static List<String> listaArquivosLogger = new ArrayList<String>(); // OK
	private static List<Arquivo> listaLinhasArquivoCSV = new ArrayList<Arquivo>();
	private static List<String> testeConstaLinhas = new ArrayList<String>();
	private static int val = 0;

	public static boolean iniciarProcesso() {
		try {
			TemplateStatus.setTextField("Processando ... !");
			zerarListas();
			checarCaminhoDosDiretorios();
			cabecalhoDetalhesArquivos();
			analisarArquivos();
			lerArquivoCSV();
			moverListaArquivoExcel();
			verificarLoggerReport();
			gerarRelatorioLogger();
			gerarRelatorioDetalhesArquivos();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private static void zerarListas() {
		listaRelatorio.clear();
		listaArquivos.clear();
		listaLoggers.clear();
		listaArquivosLogger.clear();
		listaArquivosEncontrados.clear();
		listaLinhasArquivoCSV.clear();
		testeConstaLinhas.clear();
		val = 0;
	}

	/**
	 * Verificar se as pastas para gerar os relatórios estão definidas.
	 */
	private static void checarCaminhoDosDiretorios() {

		List<String> paths = new ArrayList<String>();

		paths.add(getDIRETORIO_RAIZ_OUT());
		paths.add(getDIRETORIO_RAIZ_OUT() + "\\RELATORIOS");
		paths.add(getDIRETORIO_RAIZ_OUT() + "\\LOGGER");

		for (String path : paths) {
			verificarDiretorio(true, path);
		}
	}


	/**
	 * Analisar os arquivos que constam na pasta raiz e suas sub-pastas. <br>
	 * Os arquivos encontrados, por meio do método processarArquvo(File file), serão atrituídos: <br>
	 * <ul>
	 * <li>List<ArquivoEncontrado> listaArquivosEncontrados // Para usarmos o nome e caminho do arquivo</li>
	 * <li>List<String> listaArquivos // Para controlar e diminuir arquivos já processados</li>
	 * </ul> <br>
	 * Não será levado em consideração arquivos abertos, que começam com "~$" <br>
	 * 
	 */
	private static void analisarArquivos() {

		Path directory = Paths.get(getDIRETORIOS_RAIZ());

		try {

			List<Path> directories = Files.walk(directory).collect(Collectors.toList());
			
			for (Path path : directories) {
				File file = new File(path.toString());
				if (
						!file.isDirectory() && 							// Verificar se não é pasta &
						!listaArquivos.contains(file.getName()) &&		// Verificar se o arquivo ainda não está processado &
						!file.getName().startsWith("~$")				// Caso seja um arquivo "aberto"
						) {
					processarArquvo(file);
				}
			}
		}
		catch (NoSuchFileException e) {
			System.err.println("DIRETÓRIO NÃO EXISTE: " + e.getMessage());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}


	private static void processarArquvo(File file) {		
		ArquivoEncontrado ae = new ArquivoEncontrado(file.getName(), file.toString());
		listaArquivosEncontrados.add(ae);
		listaArquivos.add(file.getName());
	}

	private static void cabecalhoDetalhesArquivos() {
		testeConstaLinhas.add( "arquivo_fonte;"
				+ "num_linhas;"
				+ "comentarios_faturaveis;"
				+ "comentarios_nao_faturaveis;"
				+ "linhas_branco;"
				+ "caminho"
		);
	}

	private static BufferedWriter writer;
	private static BufferedWriter writerComentariosFaturaveis;

	private static void countLines(String path) throws IOException {
		File fp = new File(path);
		countLines(fp);
	}
	
	private static void countLines(File inputFile) throws IOException {
		try {

			if (inputFile.isFile()) {

				StringBuffer comentariosFaturaveis = new StringBuffer();

				BufferedReader reader;

				try {
					reader = new BufferedReader(new FileReader(inputFile));
					
					System.out.println("inputFile" + inputFile);
					System.out.println("inputFile.getAbsolutePath()" + inputFile.getAbsolutePath());

					int intLines = 0; // número da linha
					int intComentFaturavel = 0;
					int intComentNaoFaturavel = 0;
					int intBlankLines = 0; // quantidade de linhas em branco

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

										//	boolean b1 = verificaComentarioUtil(strUtilLine);
										//	boolean b2 = StringUtil.isFaturavel(strCurrentLine.substring(7));
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
					
					
					testeConstaLinhas.add(
							inputFile.getName() 
							+ ";" + intLines
							+ ";" + intComentFaturavel
							+ ";" + intComentNaoFaturavel 
							+ ";" + intBlankLines
							+ ";" + inputFile.getPath())
					;
					
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

	private static boolean verificaComentarioUtil(String linhaUtil) {
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

	/**
	 * Escanear o arquivo que será a base para analisar e separar arquivos encontrados na pasta para analisar.
	 * Cada linha é quebrada pelo separador ";"
	 * Na coluna[0] recebemos a categoria (será criado pasta para cada categoria)
	 * Na coluna[1] recebemos o tipo (será criado pasta para cada tipo dentro da categoria)
	 * Da coluna [2] em diante, são os arquivos que serão copiados, caso existam.
	 */
	private static void lerArquivoCSV() {
		try {
			List<String> listFileData = Files.readAllLines(Path.of(getDIRETORIO_FILE_ANALISAR()));

			for (String linha : listFileData) {
				Arquivo aq = new Arquivo(linha);
				listaLinhasArquivoCSV.add(aq);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private static void moverListaArquivoExcel() {
		listaLinhasArquivoCSV.stream().forEach( l -> compararArquivosExcelESalvos(l) );
	}

	/**
	 * Comparar se arquivos existem. <br>
	 * Caso existam, serão copiados para lista de relatorio. <br>
	 * Caso não existam, serão adicionados a listaArquivosLogger para elaboração do relatório. <br>
	 * 
	 * 
	 * @param arq Arquivo para comparar.
	 */
	private static void compararArquivosExcelESalvos(Arquivo arq) {
		val += arq.getFiles().size();
		arq.getFiles().stream().forEach(l -> {
			listaArquivosEncontrados.stream()
			.forEach(
					l2-> {
						//	if(l2.getNomeArquivo().startsWith(l))
						if(l2.getNomeArquivo().equals(l)) {
							efetuarCopia(arq.getCategoria(), arq.getTipo(), l2.getCaminhoArquivo(), l2.getNomeArquivo());
							try {
//								cabecalhoDetalhesArquivos();
								countLines(l2.getCaminhoArquivo());
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						// adicionar o arquivo em questão na lista de logger
						else if (!listaArquivosLogger.contains(l) )
							listaArquivosLogger.add(l);
					});
		});
	}

	/**
	 * Excluir arquivos já separados, da lista de logger.
	 */
	private static void verificarLoggerReport() {
		listaArquivosEncontrados.stream().forEach(l -> {
			if (listaArquivosLogger.contains(l.getNomeArquivo())) listaArquivosLogger.remove(l.getNomeArquivo());
		});
	}


	private static void efetuarCopia(String categoriaArquivo, String tipoArquivo, String caminhoOrigemArquivo ,String nomeArquivo) {

		File caminho = new File(getDIRETORIO_RAIZ_OUT() + "\\RELATORIOS\\" + categoriaArquivo);
		File subCaminho = new File(getDIRETORIO_RAIZ_OUT() + "\\RELATORIOS\\" + categoriaArquivo + "\\" + tipoArquivo);

		if (!caminho.exists()) caminho.mkdirs();		
		if (!subCaminho.exists()) subCaminho.mkdirs();

		try {
			Files.copy(Paths.get(caminhoOrigemArquivo), Paths.get(subCaminho + "\\" + nomeArquivo), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private static void gerarRelatorioDetalhesArquivos() {
		String caminho = getDIRETORIO_RAIZ_OUT() + "\\LOGGER\\detalhes_arquivos_" + LocalDate.now() + ".csv";

		try {
			Files.write(Path.of(caminho), testeConstaLinhas);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void gerarRelatorioLogger() {



		try {
			if (!listaArquivosLogger.isEmpty()) {
				int v = val - listaArquivosEncontrados.size();

				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				String caminho = getDIRETORIO_RAIZ_OUT() + "\\LOGGER\\Relatorio_Logger_" + LocalDate.now() + ".csv";

				// Cabeçalho - Relatório Logger
				listaLoggers.add("Data: " + dateFormat.format(date));
				listaLoggers.add("Arquivos encontrados: " + listaArquivosEncontrados.size()  + "/" + val);
				listaLoggers.add("Arquivos não encontrados: " + v  + "/" + val);

				// Adicionar arquivos não encontrados ao Relatório de Logger
				listaArquivosLogger.stream().forEach(l -> listaLoggers.add(l));
				Files.write(Path.of(caminho), listaLoggers);

			} else
				System.out.println("Todos arquivos foram encontrados");
		} catch (Exception e) {
			System.err.println("[ERRO] ao gerar Report Logger: " + e.getMessage());
		}
	}

	private static boolean verificarDiretorio(boolean eDiretorio, String path) {
		File file = null;
		if (path != null) file = new File(path);
		if (path != null && eDiretorio && !file.exists()) file.mkdirs();
		return path == null ? false : file.exists();
	}

	// GETTERS && SETTERS

	public static String getDIRETORIO_FILE_ANALISAR() {
		return DIRETORIO_FILE_ANALISAR;
	}

	/**
	 * Aceita arquivo existente.
	 * 
	 * @param diretorio
	 */
	public static void setDIRETORIO_FILE_ANALISAR(String diretorio) {
		if (
				diretorio != null && 
				!diretorio.equals(getDIRETORIO_FILE_ANALISAR()) && 
				diretorio.length() > 0 &&
				verificarDiretorio(false, diretorio)) {
			DIRETORIO_FILE_ANALISAR = diretorio;
			alterarTemplateStatus("Arquivo alterado com sucesso."  );
		} else {
			String message = diretorio.equals(getDIRETORIO_FILE_ANALISAR()) ? "Arquivo é igual ao já selecionado." : "Arquivo não existe.";
			alterarTemplateStatus("Erro ao atualizar arquivo." + " " + message);
		}

		chegarTodosDiretoriosArquivos();
	}

	public static String getDIRETORIOS_RAIZ() {
		return DIRETORIOS_RAIZ_ESCANEAR;
	}

	public static void setDIRETORIOS_RAIZ(String diretorio) {
		if (diretorio != null && !diretorio.equals(getDIRETORIOS_RAIZ()) && diretorio.length() > 0) {
			DIRETORIOS_RAIZ_ESCANEAR = diretorio;
			verificarDiretorio(true, diretorio);
		}
		chegarTodosDiretoriosArquivos();
		System.out.println("DIRETORIOS_RAIZ_ESCANEAR" + getDIRETORIOS_RAIZ());
	}

	public static String getDIRETORIO_RAIZ_OUT() {
		return DIRETORIO_RAIZ_OUT;
	}

	public static void setDIRETORIO_RAIZ_OUT(String diretorio) {
		if (diretorio != null && !diretorio.equals(getDIRETORIO_RAIZ_OUT()) && diretorio.length() > 0) {
			DIRETORIO_RAIZ_OUT = diretorio;
			verificarDiretorio(true, diretorio);
		}
		chegarTodosDiretoriosArquivos();
		System.out.println("DIRETORIO_RAIZ_OUT: " + getDIRETORIO_RAIZ_OUT());
	}

	private static void alterarTemplateStatus(String message) {
		TemplateStatus.setTextField(message);
	}

	public static void chegarTodosDiretoriosArquivos() {

		File diretorioRaiz = new File(getDIRETORIOS_RAIZ());
		File diretorioSaida = new File(getDIRETORIO_RAIZ_OUT());
		File arquivoAnalisar = new File(getDIRETORIO_FILE_ANALISAR());

		if (diretorioRaiz.exists() && diretorioSaida.exists() && arquivoAnalisar.exists()) {
			alterarTemplateStatus("Pronto para processar!");
			TemplateStatus.toggleBotao(true);
		}
		else {
			alterarTemplateStatus("Verificar se diretório de saída ou para escanear estão definidos, ou se o arquivo *.csv existe.");
			TemplateStatus.toggleBotao(false);			
		}
	}

}
