package com.accenture.gcp.analise.menu;

import javax.swing.JFrame;

public class Template {

	public static void iniciarMenu() {
		JFrame frame = new JFrame("ACCENTURE - Filtro de Arquivos");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new TemplateGrid());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
