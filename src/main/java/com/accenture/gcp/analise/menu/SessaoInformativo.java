package com.accenture.gcp.analise.menu;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class SessaoInformativo extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SessaoInformativo() {
		setLayout(new GridBagLayout());
		setBorder(new CompoundBorder(new TitledBorder("Informativo"), new EmptyBorder(12, 0, 0, 0)));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(0, 0, 0, 4);

		JPanel panel = new JPanel(new GridBagLayout());
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		List<String> p1 = new ArrayList<String>();
		p1.add("Projeto desenvolvido para:");
		p1.add("	- Verificar arquivos que contém em determinado diretório e sub-diretórios;");
		p1.add("	- Comparar com arquivo em formato *.csv;");
		p1.add("	- Gerar relatório e copiar arquivos listados no arquivo *.csv;");
		p1.add("	- Os arquivos, à separar, listados em arquivo *.csv, precisa constar a extensão, caso exista, senão não serão separados.");
		p1.add(" ");
		p1.stream().forEach(l->panel.add(new JLabel(l)));
		
		gbc.gridx++;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 0, 0, 0);
//		panel.add((database = new JLabel()), gbc);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(4, 4, 4, 4);
		add(panel, gbc);

		gbc.gridwidth = 1;
		gbc.weightx = 0.25;
		gbc.gridy++;
	}
}
