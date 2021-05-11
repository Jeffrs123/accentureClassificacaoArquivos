package com.accenture.gcp.analise.menu;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.accenture.gcp.analise.util.Processamento;

public class TemplateOptions extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton primeiro, segundo, terceiro;
	private static JFileChooser chooser;

	public TemplateOptions() {
		setLayout(new GridBagLayout());
		setBorder(new CompoundBorder(new TitledBorder("Alterar/Escolher"), new EmptyBorder(12, 0, 0, 0)));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(0, 0, 0, 4);

		JPanel panel = new JPanel(new GridBagLayout());
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
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add((primeiro = new JButton("Pasta para Escanear")), gbc);
		gbc.gridx++;
		add((segundo = new JButton("Pasta para gerar Relatórios")), gbc);
		gbc.gridx++;
		add((terceiro = new JButton("Arquivo *.csv")), gbc);

		primeiro.addActionListener(this);
		segundo.addActionListener(this);
		terceiro.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == primeiro){
			String path = escolherArquivoOuDiretorio(Processamento.getDIRETORIOS_RAIZ(), JFileChooser.DIRECTORIES_ONLY, "Escolhar pasta base para escanear dentro dela e das subpastas todos os arquivos");
			if (path != null) Processamento.setDIRETORIOS_RAIZ(path);
		}
		if(e.getSource() == segundo){
			String path = escolherArquivoOuDiretorio(Processamento.getDIRETORIO_RAIZ_OUT(), JFileChooser.DIRECTORIES_ONLY, "Escolher pasta base para saída de relatórios, loggers e outros");
			if (path != null) Processamento.setDIRETORIO_RAIZ_OUT(path);
		}
		if(e.getSource() == terceiro){
			String path = escolherArquivoOuDiretorio(Processamento.getDIRETORIO_FILE_ANALISAR(), JFileChooser.FILES_ONLY, "Escolher arquivo para ler");
			if (path != null) Processamento.setDIRETORIO_FILE_ANALISAR(path);
		}
	}

	/**
	 * Configurações da caixa de seleção. <br>
	 * Se o parâmetro tipoSelecao igual "JFileChooser.FILES_ONLY" irá filtrar por aquivos *.csv
	 * 
	 * 
	 * @param filePath Caminho raiz para leitura - "."
	 * @param tipoSelecao arquivos que pode ser selecionado ex: JFileChooser.DIRECTORIES_ONLY ou JFileChooser.FILES_AND_DIRECTORIES etc.
	 * @param titleDialogName Nome da caixa de diálogo
	 * @return Arquivo selecionado, caso nenhum, a seleção será a pasta raíz.
	 * 
	 * @author jefferson.r.a.silva
	 */
	private String escolherArquivoOuDiretorio(String filePath, int tipoSelecao, String titleDialogName) {

		chooser = new JFileChooser();

		chooser.setCurrentDirectory(new File(filePath));
		chooser.setDialogTitle(titleDialogName);
		chooser.setFileSelectionMode(tipoSelecao);

		// Filtrar para não aparecer arquivos de propriedades
		if (tipoSelecao == JFileChooser.FILES_ONLY) {
			chooser.setFileFilter(new FileNameExtensionFilter("Arquivos aceitos", "csv")); // "Arquivos aceitos", "csv", "zip", "jar"
			chooser.setSelectedFile(new File(filePath));			
		}
		else 
			chooser.setFileFilter(new FileNameExtensionFilter("Properties file", "properties"));

		chooser.setAcceptAllFileFilterUsed(false);

		return chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION ? chooser.getSelectedFile().toString(): null;
	}

}
