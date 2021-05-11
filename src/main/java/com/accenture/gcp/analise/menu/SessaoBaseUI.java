package com.accenture.gcp.analise.menu;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SessaoBaseUI extends JPanel{

	//	public BaseUI() {
	//		
	//	}

	private static SessaoInformativo inf;
	private static SessaoMenuItems ec;
	private static SessaoProcessamento sp;

	public SessaoBaseUI() {

		System.out.println("BASEUI inicializado");
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0.33;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(4, 4, 4, 4);

		add((inf = new SessaoInformativo()), gbc);
		gbc.gridy++;
		add((ec = new SessaoMenuItems()), gbc);
		gbc.gridy++;
		add((sp = new SessaoProcessamento()), gbc);
		gbc.gridy++;
		

		gbc.gridy = 0;
		gbc.gridx++;
		gbc.gridheight = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.weighty = 1;
		gbc.weightx = 0;

		//		 JFrame frame = new JFrame("Relatórios");
		//	        EscolhaCaminho ec = new EscolhaCaminho();
		//	        Informativo inf = new Informativo();
		//	        frame.addWindowListener(
		//					new WindowAdapter() {
		//						public void windowClosing(WindowEvent e) {
		//							System.exit(0);
		//						}
		//					}
		//					);
		//	        
		//	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//	        frame.getContentPane().add(inf);
		//	        frame.getContentPane().add(ec);
		////	        frame.getContentPane().add(panel,"Center");
		//	        frame.pack();
		//	        frame.setLocationRelativeTo(null);
		//	        frame.setVisible(true);
	}

	private static void iniciar() {

	}
}
