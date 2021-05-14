package com.accenture.gcp.analise.util;

public class StringUtil {
	public StringUtil() {
	}

	public static String removeCharacteresDuplicados(final String input) {
		final StringBuilder result = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			String currentChar = input.substring(i, i + 1);
			if (result.indexOf(currentChar) < 0) // if not contained
				result.append(currentChar);
		}
		return result.toString();
	}

	public static boolean isFaturavel(String conteudo) {

		char[] caracteres = conteudo.toCharArray();

		StringBuilder resultado = new StringBuilder();
		for (char caracter : caracteres) {
			if (resultado.indexOf(String.valueOf(caracter)) == -1) {
				resultado.append(caracter);
			}
		}

		//		if (!(resultado.length() > 5)) {
		//			System.out.println(conteudo + "/" + resultado);
		//		}
		return resultado.length() > 5;
	}
}
