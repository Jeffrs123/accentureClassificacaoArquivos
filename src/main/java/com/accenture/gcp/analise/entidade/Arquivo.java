package com.accenture.gcp.analise.entidade;

import java.util.ArrayList;
import java.util.List;

public class Arquivo {

	private String categoria;
	private String tipo;
	private List<String> files = new ArrayList<String>();

	public Arquivo(String linha) {
		String[] dado = linha.split(";");

		this.categoria = dado[0];
		this.tipo = dado[1];
		this.files = setFiles(dado);
	}

	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public List<String> getFiles() {
		return files;
	}
	public void setFiles(List<String> files) {
		this.files = files;
	}
	public List<String> setFiles(String[] dado) {
		for (int i = 2; i < dado.length; i++) {
			this.files.add(dado[i]);
		}
		return this.files;
	}


	@Override
	public String toString() {
		return "File [categoria=" + categoria + ", tipo=" + tipo + ", files=" + files + "]";
	}

}
