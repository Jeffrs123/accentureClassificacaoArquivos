package com.accenture.gcp.analise.menu;

public enum StatusProcessamento {
	
	
	NAO_INICIADO("Não Processado"),
	PROCESSANDO("Processando"),
	PROCESSADO("Processado");

	private String desc;
	
	StatusProcessamento(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
	
}
