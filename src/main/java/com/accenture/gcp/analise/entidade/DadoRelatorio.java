package com.accenture.gcp.analise.entidade;

import java.util.ArrayList;
import java.util.List;

public class DadoRelatorio {
	
	private String nomeArquivo;
	private String caminhoArquivo;
	private int ocorrencias;
	private List<String> caminhosDeOcorrencias = new ArrayList<String>();
	
	public DadoRelatorio(String dado) {

		dado.replace("[", "");
		dado.replace("]", "");
		String [] linha = dado.split(";");
		
		setNomeArquivo(linha[0]);
		setCaminhoArquivo(linha[1]);
		setOcorrencias(Integer.parseInt(linha[2]));
		
		if (linha[3] == null || linha[3].length() <= 0) {
			getCaminhosDeOcorrencias().add(linha[1]);
		} else {
			getCaminhosDeOcorrencias().add(linha[3]);
		}
		
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getCaminhoArquivo() {
		return caminhoArquivo;
	}

	public void setCaminhoArquivo(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
	}

	public int getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(int ocorrencias) {
		this.ocorrencias = ocorrencias;
	}

	public List<String> getCaminhosDeOcorrencias() {
		return caminhosDeOcorrencias;
	}

	public void setCaminhosDeOcorrencias(String caminho) {
		this.caminhosDeOcorrencias.add(caminho);
	}
	
	public void setCaminhosDeOcorrencias(List<String> caminhosDeOcorrencias) {
		this.caminhosDeOcorrencias = caminhosDeOcorrencias;
	}

	@Override
	public String toString() {
		return "Relatorio [nomeArquivo=" + nomeArquivo + ", caminhoArquivo=" + caminhoArquivo + ", ocorrencias="
				+ ocorrencias + ", caminhosDeOcorrencias=" + caminhosDeOcorrencias + "]";
	}
	
	
}
