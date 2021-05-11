package com.accenture.gcp.analise.menu;

import java.util.Scanner;

import com.accenture.gcp.analise.util.Processamento;

public class MenuInicial {

	public static void iniciarMenu() {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		try {
			System.out.println(
					"Menu Inicial\n"
							+ "Digite uma das opções a seguir.\n"
							+ "0 - Encerrar Programa\n"
							+ "1 - Iniciar Processamento/Análise\n"
							+ "2 - Escolher/Alterar diretório raiz\n"
							+ "3 - Escolher/Alterar diretório/arquivo para analisar\n"
							+ "4 - Escolher/Alterar diretório para gerar relatório\n"
					);

			int condicao = in.nextInt();
			switch(condicao) {
			case 0:{
				System.out.println("Programa encerrado!");
				System.exit(0);
				break;
			}
			case 1:{
				in.reset();
				Processamento.comecarProcesso(); // Enviar path, para análises, caso necessário.
				iniciarMenu();
				break;
			}
			case 2:{
				in.reset();
				mudarCaminhoOriginal(TipoMudancaPath.DIRETORIO_RAIZ_ANALISAR);
				break;
			}
			case 3:{
				in.reset();
				mudarCaminhoOriginal(TipoMudancaPath.DIRETORIO_ARQUIVO_ANALISAR);
				break;
			}
			case 4:{
				in.reset();
				mudarCaminhoOriginal(TipoMudancaPath.DIRETORIO_RELATORIO);
				break;
			}
			default:{
				in.reset();
				System.err.println("você digitou uma opção inválida!");
				iniciarMenu();
				break;
			}
			}

		} catch (Exception e) {
			System.err.println("Você digitou um valor que não é um número!");
			iniciarMenu();
		}
	}

	private static void mudarCaminhoOriginal(TipoMudancaPath tipoDiretorio) {

		String path;
		String message;

		switch(tipoDiretorio) {
			case DIRETORIO_RAIZ_ANALISAR:{
				message = "caminho do diretorio raiz para analisar e filtrar os arquivos";
				path = chamarInputDoUsuario(message);
				Processamento.setDIRETORIOS_RAIZ(path);
				break;
			}
			case DIRETORIO_ARQUIVO_ANALISAR:{
				message = "caminho do diretorio ou Arquivo para analisar";
				path = chamarInputDoUsuario(message);
				Processamento.setDIRETORIO_FILE_ANALISAR(path);
				break;
			}
			case DIRETORIO_RELATORIO:{
				message = "caminho do diretorio raiz para geração de relatórios e loggers";
				path = chamarInputDoUsuario(message);
				Processamento.setDIRETORIOS_RAIZ(path);
				break;
			}
			default:{
				System.err.println("[ERRO]: Opção Enum inválida, para trocar o caminho de Arquivo/Diretório");
				break;
			}
		}
		iniciarMenu();
	}

	public static String chamarInputDoUsuario(String mensagem) {
		return CallScanner.getUserInput(mensagem);
	}
}
