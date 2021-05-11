package com.accenture.gcp.analise;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.accenture.gcp.analise.menu.Template;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(Application.class, args);
		System.setProperty("java.awt.headless", "false");
		Template.iniciarMenu();
	}
}
