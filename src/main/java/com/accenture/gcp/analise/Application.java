package com.accenture.gcp.analise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.accenture.gcp.analise.util.Processamento;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		Processamento.comecarProcesso();
	}

}
