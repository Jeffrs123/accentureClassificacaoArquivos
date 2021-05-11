package com.accenture.gcp.analise.menu;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Template {

	public static void iniciarMenu() {
		JFrame frame = new JFrame("ACCENTURE - Filtro de Arquivos");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new SessaoBaseUI());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
//		JFrame f = null;  
//		JOptionPane.showMessageDialog(f,
//				"Processo finalizado com sucesso!",
//				"Obrigado pela preferência, volte sempre!",
//				JOptionPane.PLAIN_MESSAGE);
	}
}
