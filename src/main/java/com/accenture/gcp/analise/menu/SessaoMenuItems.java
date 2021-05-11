package com.accenture.gcp.analise.menu;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SessaoMenuItems extends JPanel implements ActionListener {

	private JButton primeiro, segundo, terceiro;
	static JFileChooser chooser;
	String choosertitle;
	
	public SessaoMenuItems() {
		iniciar();
	}
	
	
	@SuppressWarnings("null")
	public void iniciar() {
		
		
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
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add((primeiro = new JButton("Pasta Raíz de Leitura")), gbc);
		gbc.gridx++;
		add((segundo = new JButton("Pasta Saída de Relatório")), gbc);
		gbc.gridx++;
		add((terceiro = new JButton("Arquivo *.csv")), gbc);
		
		primeiro.addActionListener(this);
		segundo.addActionListener(this);
		terceiro.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == primeiro){
			System.out.println("primeiro button clicked");
			JFileChooser pb = chamarDiretorioEFile(".", JFileChooser.FILES_AND_DIRECTORIES);
			
			if(pb.getSelectedFile() == null) {
				pb.setSelectedFile(pb.getCurrentDirectory());
			}
			System.out.println("pb.getCurrentDirectory() " + pb.getCurrentDirectory());
			System.out.println("pb.getSelectedFile() " + pb.getSelectedFile());
		}
		if(e.getSource() == segundo){
			System.out.println("segundo button clicked");
		}
		if(e.getSource() == terceiro){
			System.out.println("terceiro button clicked");
		}


	}

	private JFileChooser chamarDiretorioEFile(String filePath, int tipoSelecao) {
		//		int result;

		chooser = new JFileChooser(); 
		
		chooser.setCurrentDirectory(new File(filePath));
		chooser.setDialogTitle(choosertitle);
		//		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setFileSelectionMode(tipoSelecao);
		chooser.setFileFilter(new FileNameExtensionFilter("Properties file", "properties"));
		//
		// disable the "All files" option.
		//
		chooser.setAcceptAllFileFilterUsed(false);


		chooser.setSelectedFile(chooser.getCurrentDirectory());
		//

		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			//			System.out.println("pb.getCurrentDirectory() " + pb.getCurrentDirectory());
			//			System.out.println("pb.getSelectedFile() " + pb.getSelectedFile());

			return chooser;
		} else {
			System.out.println("No Selection ");
			return chooser;
		}
	}
}
