package com.accenture.gcp.analise.menu;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.accenture.gcp.analise.Application;

public class ANTIGOSessaoMenuItems  extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton primeiro, segundo, go, bye, terceiro, quarto;
	static JFileChooser chooser;
	String choosertitle;
	//	private JLabel database;
	public ANTIGOSessaoMenuItems() {
		iniciar();
		//		go = new JButton("Do it nowww");
		//		bye = new JButton("bye it nowww");
		//		terceiro = new JButton("terceiro it nowww");
		//		quarto = new JButton("quarto it nowww");
		//
		//		go.addActionListener(this);
		//		bye.addActionListener(this);
		//		terceiro.addActionListener(this);
		//		quarto.addActionListener(this);
		//
		//		add(go);
		//		add(bye);
		//		add(terceiro);
		//		add(quarto);
	}

	public void iniciar() {


		setLayout(new GridBagLayout());
		setBorder(new CompoundBorder(new TitledBorder("Caminhos de Diretório e Arquivos"), new EmptyBorder(12, 0, 0, 0)));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(0, 0, 0, 4);

		JPanel panel = new JPanel(new GridBagLayout());
		panel.add(new JLabel("Database: "), gbc);
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
		add((primeiro = new JButton("Primeiro")), gbc);
		gbc.gridx++;
		add((segundo = new JButton("Segundo")), gbc);
		gbc.gridx++;
		add((terceiro = new JButton("Terceiro")), gbc);
		gbc.gridx++;
		add((quarto = new JButton("Quarto")), gbc);

		primeiro.addActionListener(this);
		segundo.addActionListener(this);
		terceiro.addActionListener(this);
		quarto.addActionListener(this);
	}

//	public Dimension getPreferredSize(){
//		return new Dimension(200, 200);
//	}

	public static void chamarEscolhaArquivo3() {

		JFrame frame = new JFrame("");
		ANTIGOSessaoMenuItems panel = new ANTIGOSessaoMenuItems();
		frame.addWindowListener(
				new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				}
				);
		frame.getContentPane().add(panel,"Center");
		frame.setSize(panel.getPreferredSize());
		frame.setVisible(true);
		//		JFrame frame = new JFrame("");
		//		DemoJFileChooser panel = new DemoJFileChooser();
		//		frame.addWindowListener(
		//				new WindowAdapter() {
		//					public void windowClosing(WindowEvent e) {
		//						System.exit(0);
		//					}
		//				}
		//				);
		//		frame.getContentPane().add(panel,"Center");
		//		frame.setSize(panel.getPreferredSize());
		//		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {


		if(e.getSource() == go){
			//            System.exit(0);
			JFileChooser pb = chamarDiretorioEFile(JFileChooser.FILES_AND_DIRECTORIES);
			System.out.println("Go button clicked");
			//			if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			System.out.println("pb.getCurrentDirectory() " + pb.getCurrentDirectory());
			System.out.println("pb.getSelectedFile() " + pb.getSelectedFile());
			//			} else {
			//				System.out.println("No Selection ");
			//}
		}
		if(e.getSource() == bye){
			System.out.println("bye button clicked");
		}
		if(e.getSource() == terceiro){
			System.out.println("terceiro button clicked");
		}
		if(e.getSource() == quarto){
			System.out.println("quarto button clicked");
		}
		//		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
		//			System.out.println("getCurrentDirectory(): " 
		//					+  chooser.getCurrentDirectory());
		//			System.out.println("getSelectedFile() : " 
		//					+  chooser.getSelectedFile());
		//		}
		//		else {
		//			System.out.println("No Selection ");
		//		}

	}

	private JFileChooser chamarDiretorioEFile(int tipoSelecao) {
		//		int result;

		chooser = new JFileChooser(); 
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle(choosertitle);
		//		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setFileSelectionMode(tipoSelecao);
		chooser.setFileFilter(new FileNameExtensionFilter("Properties file", "properties"));
		//
		// disable the "All files" option.
		//
		chooser.setAcceptAllFileFilterUsed(false);


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
