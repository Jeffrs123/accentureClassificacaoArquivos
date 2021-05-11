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

public class TemplateInfo extends JPanel{

	private static final long serialVersionUID = 1L;

	public TemplateInfo() {
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
		p1.add("	- Os arquivos, à separar, listados em arquivo *.csv, precisa constar a extensão, caso exista, senão não serão separados;");
		p1.add("	- Cada linha deverá ter os elementos separados por ponto e vírgula (';');");
		p1.add("	- Os dois primeiros elementos definirão a estrutura da separação dos arquivos, no primeiro elemento tendo o tipo, e no segundo o subtipo;");
		p1.add(" ");
		p1.add("	- Exemplo de arquivo de leitura:");		
		p1.add("		- SCORE;JCL;SIA180ON;CPJ770E;CPJ770E#;ASSO.MBR");
		p1.add("		- SCORE;PGM;CLPTRIG ;SIAPB084;SIAPB112;SIAPB199");
		p1.add("		- SCORE;Sysin;YPRD0001;YPRD0006");
		p1.add(" ");
		p1.stream().forEach(l->panel.add(new JLabel(l)));

		gbc.gridx++;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 0, 0, 0);

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
