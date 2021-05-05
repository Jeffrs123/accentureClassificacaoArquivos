package com.accenture.gcp.analise.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.accenture.gcp.analise.entidade.Arquivo;
import com.accenture.gcp.analise.entidade.ArquivoEncontrado;

public class Processamento {

	private static String CAMINHO_FILE_CSV_ANALISAR = "C:\\TEMP\\ANALISE\\Cópia de componentes.csv";
//	private static String CAMINHO_DIRETORIOS_PARA_ANALISAR =  "C:\\Users\\jefferson.r.a.silva\\OneDrive - Accenture\\Documents\\TESTE";
	private static String CAMINHO_DIRETORIOS_PARA_ANALISAR =  "C:\\Projeto BVS\\as400code\\MF";
	
	private static List<String> listaRelatorio = new ArrayList<String>();
	private static List<String> listaArquivos = new ArrayList<String>(); // OK
	private static List<String> listaLoggers = new ArrayList<String>();
	private static List<ArquivoEncontrado> listaArquivosEncontrados = new ArrayList<ArquivoEncontrado>(); // OK
	private static List<Arquivo> listaArquivosExcel = new ArrayList<Arquivo>();

	public static void comecarProcesso() {
		zerarListas();
		checarCaminhoDosDiretorios();
		analisarArquivos();
		lerArquivoExcel();
		moverListaArquivoExcel();
		System.out.println("\nPROCESSO FINALIZADO COM SUCESSO !!!\n");
	}

	private static void zerarListas() {
		listaRelatorio.clear();
		listaArquivos.clear();
		listaLoggers.clear();
		listaArquivosEncontrados.clear();
		listaArquivosExcel.clear();
		System.out.println("\nListas Zeradas\n");
	}

	private static void checarCaminhoDosDiretorios() {

		List<String> paths = new ArrayList<String>();

		paths.add("C:/TEMP");
		paths.add("C:/TEMP/RELATORIOS");
		paths.add("C:/TEMP/LOGGER");

		for (String path : paths) {
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
				System.out.println("PASTA CRIADA: " + file);
			}
		}
	}

	private static void analisarArquivos() {
		
		Path directory = Paths.get(CAMINHO_DIRETORIOS_PARA_ANALISAR);

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

	private static void compararArquivosExcelESalvos(Arquivo arq) {
		arq.getFiles().stream().forEach(l -> {
			listaArquivosEncontrados.stream()
			.forEach(
					l2-> {
						if (l.equals(l2.getNomeArquivo())) {
							efetuarCopia(arq.getCategoria(), arq.getTipo(), l2.getCaminhoArquivo(), l2.getNomeArquivo());			
						}
					});
		});
	}
	
	private static void efetuarCopia(String categoriaArquivo, String tipoArquivo, String caminhoOrigemArquivo ,String nomeArquivo) {
		
		File caminho = new File("C:/TEMP/RELATORIOS/" + categoriaArquivo);
		File subCaminho = new File("C:/TEMP/RELATORIOS/" + categoriaArquivo + "/" + tipoArquivo);
		
		if (!caminho.exists()) caminho.mkdirs();		
		if (!subCaminho.exists()) subCaminho.mkdirs();
		
		try {
			Files.copy(Paths.get(caminhoOrigemArquivo), Paths.get(subCaminho + "/" + nomeArquivo), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void moverListaArquivoExcel() {
		listaArquivosExcel.stream().forEach( l -> compararArquivosExcelESalvos(l) );
	}
	
	private static void lerArquivoExcel() {
		try {
			List<String> listFileData = Files.readAllLines(Path.of(CAMINHO_FILE_CSV_ANALISAR));

			for (String linha : listFileData) {
				Arquivo aq = new Arquivo(linha);
				listaArquivosExcel.add(aq);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
