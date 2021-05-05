package com.accenture.gcp.analise.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.accenture.gcp.analise.entidade.DadoRelatorio;

public class ProcessamentoOld  {

	private static List<String> listaRelatorio = new ArrayList<String>();
	private static List<String> listaArquivos = new ArrayList<String>();
	private static List<String> listaLoggers = new ArrayList<String>();
	private static List<DadoRelatorio> listaDadosRelatorio = new ArrayList<DadoRelatorio>();
	
	/**
	 * Lista de checagem
	 * 	<code>
	 * 		<ul>
	 * 				<li>OK - Resetar listas</li>
	 * 				<li>OK - Vericar diretórios para gerar relatórios</li>
	 * 				<li>NÃO OK - Vericar se no diretório "C:/TEMP/RELATORIOS" tem arquivo</li>
	 * 				<li>NÃO OK - Caso tenha arquivo, inserir em lista, para processar/verificar</li>
	 * 				<li>NÃO OK - Verificar arquivos já processados - listaAP</li>
	 * 				<li>NÃO OK - listaAP.csv, separado por ";" com nome do file e source do file</li>
	 * 				<li>NÃO OK - </li>
	 * 				<li>NÃO OK - </li>
	 * 		</ul>
	 * 	</code>
	 * 
	 * @author jefferson.r.a.silva
	 */
	public static void startProcess() {
		resetLists();
		checkPathDirectories();
		checkPathsAndDirectoriesToProcess();
		//		lerDiretorio();
	}

	/**
	 * Limpar as listas para conferência de arquivos, loggers e relatórios.
	 */
	private static void resetLists() {
		listaArquivos.clear();
		listaLoggers.clear();
		System.out.println("\nListas Zeradas\n");
	}

	/**
	 * Checar para ver se o diretório base existe. <br>
	 * Diretório principal - "C:/TEMP", <br>
	 * Diretório de logger - "C:/TEMP/LOGGER", <br>
	 * Diretório de relatórios (em word) - "C:/TEMP/RELATORIOS", <br>
	 * Diretório de arquivos já processados - "C:/TEMP/FILES_TO_ANALISE".
	 */
	private static void checkPathDirectories() {

		List<String> paths = new ArrayList<String>();

		paths.add("C:/TEMP");
		paths.add("C:/TEMP/FILES_TO_ANALISE");
		paths.add("C:/TEMP/LOGGER");
		paths.add("C:/TEMP/RELATORIOS");

		for (String path : paths) {
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
		}
	}

	private static void lerDiretorioRelatorio() {
		File arquivos2[] = new File("C:/TEMP/RELATORIOS").listFiles() ;
		File arquivos[];
		File diretorio = new File("C:/TEMP/RELATORIOS");
		arquivos = diretorio.listFiles();

		for(int i = 0; i < arquivos.length; i++){
			// Se arquivo não tiver sido acrescentado ainda na lista
			if(!listaRelatorio.contains(arquivos[i].getName())) {
				 processFile(arquivos[i].getName());
			}
		}
	}
	
	private static void processFile(String fileName) {
		try {
			List<String> listFileData = Files.readAllLines(Path.of(fileName));
			for (String data : listFileData) {
				processData(data);
			}
			
			listaRelatorio.add(fileName);
			
			if (listaLoggers.size() > 0) {
//				gerarReportLogger();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	private static void updateData(String fileName) {
		
		int it = listaDadosRelatorio.indexOf(fileName);
		DadoRelatorio li = listaDadosRelatorio.get(it);
		li.setOcorrencias(li.getOcorrencias() + 1); 	//		li.setCaminhosDeOcorrencias();
		listaDadosRelatorio.add(li);
		
	}
	
	private static void processData(String dado) {
		try {
			
			DadoRelatorio li = new DadoRelatorio(dado);

			// Verificar se arquivo NÃO foi processado
			if (!listaDadosRelatorio.contains(li)) {
				listaDadosRelatorio.add(li);				
			} 
			// Senão, atualizar o arquivo
			else {
				
				li.setOcorrencias(li.getOcorrencias() + 1);
				li.setCaminhosDeOcorrencias(li.getCaminhoArquivo());
				
				listaDadosRelatorio.set(listaDadosRelatorio.indexOf(li), li);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Copiar arquivos de um local para outro.
	 * 
	 * @param origem - URL do arquivo
	 * @param destino - URL de destino
	 * @throws IOException
	 */
	private static void copyFile(String origem, String destino) throws IOException {
		//	Files.move(Paths.get(origem), Paths.get(destino), StandardCopyOption.REPLACE_EXISTING);
		Files.copy(Paths.get(origem), Paths.get(destino), StandardCopyOption.REPLACE_EXISTING);
	}

	/**
	 * Verificar os arquivos que constam na pasta raiz. <br>
	 * Os documentos serão separados na pasta: <br>
	 * <b> C:/TEMP/FILES_TO_ANALISE </b>
	 * 
	 * @author jefferson.r.a.silva
	 */
	private static void checkPathsAndDirectoriesToProcess() {

		String dirLocation = "C:\\TEMP";
		Path directory = Paths.get(dirLocation);

		try {

			List<Path> directories = Files.walk(directory).collect(Collectors.toList());

			for (Path path : directories) {
				File file = new File(path.toString());
				if (
						!file.isDirectory() && 							// Verificar se não é pasta &
						!listaArquivos.contains(file.getName()) &&		// Verificar se o arquivo ainda não está processado &
						!file.getName().startsWith("~$")				// Caso seja um arquivo "aberto"
						) {

					// Criar método para adicionar o nome do arquivo, e para mover/copiar arquivos.
					listaArquivos.add(file.getName());
				}
			}
			System.out.println("\nLISTA DE ARQUIVOS\n");
			for (String arquivo : listaArquivos) {
				System.out.println("ARQUIVO à processar: " + arquivo);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
