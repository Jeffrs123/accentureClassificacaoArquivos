package com.accenture.gcp.analise.entidade;

public class ArquivoEncontrado {

	private String nomeArquivo;
	private String caminhoArquivo;
		
	public ArquivoEncontrado(String nomeArquivo, String caminhoArquivo) {
		this.nomeArquivo = nomeArquivo;
		this.caminhoArquivo = caminhoArquivo;
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

	@Override
	public String toString() {
		return "ArquivoEncontrado [nomeArquivo=" + nomeArquivo + ", caminhoArquivo=" + caminhoArquivo + "]";
	}
	
	
}
