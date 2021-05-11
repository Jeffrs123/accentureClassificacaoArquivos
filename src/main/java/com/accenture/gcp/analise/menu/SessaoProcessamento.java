package com.accenture.gcp.analise.menu;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class SessaoProcessamento extends JPanel implements ActionListener {
	
	private static JButton primeiroS, segundoS;
//	private JButton segundoS;
//	private JButton terceiroS;
	private boolean buttonAFlag = true;
	static JTextField textField = new JTextField("Teste");
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public SessaoProcessamento() {
		iniciar();
//		add(panel, gbc);
	}
	static GridBagConstraints gbc = new GridBagConstraints();
	static JPanel panel = new JPanel(new GridBagLayout());
//	private static String tet = setT();// textField.toString();
//	static JLabel jlb = new JLabel(getTet());
	
//	private static String setT() {
//		return tet == "abc" ? setTet("syz") : setTet("abc");
//	}
//	
//	
//	public static String getTet() {
//		return tet;
//	}

//	public static String setTet(String teta) {
//		return tet = teta;
//	}

	public void iniciar() {
		setLayout(new GridBagLayout());
		setBorder(new CompoundBorder(new TitledBorder("Status Processamento"), new EmptyBorder(12, 0, 0, 0)));
//		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(0, 0, 0, 4);

		
//		panel.add(new JLabel("Database: "), gbc);		
//		panel.add(jlb, gbc);
		
//		JPanel panel = new JPanel(new GridBagLayout());
//		panel.add(new JLabel("Database: "), gbc);
//		JLabel jlb = new JLabel("");
		
//		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//		
//		
//		panel.add(jlb, gbc);

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		gbc.gridx++;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 0, 0, 0);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.CENTER;
		gbc.insets = new Insets(4, 4, 4, 4);

		gbc.gridwidth = 1;
		gbc.weightx = 0.25;
		gbc.gridy++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add((primeiroS = new JButton("Iniciar o processamento")), gbc);
		gbc.gridx++;
		add((segundoS = new JButton("segundoS")), gbc);
		gbc.gridx++;
		
//		panel.
		
		textField.setEditable(false);
		add(textField, gbc);
		gbc.gridx++;

//		add(jlb, gbc);
//		gbc.gridx++;
		
		primeiroS.addActionListener(this);
		segundoS.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == primeiroS){
			// Alterar status
			buttonAFlag = buttonAFlag ? false: true;
			
			// iniciar monitoramento e alterar status no layout
			textField.setText("buttonAFlag status: " + buttonAFlag);
			
			// terminar processamento e alterar status no layout
		}
		
		if(e.getSource() == segundoS){
			System.out.println("segundoS button clicked");
			toggleBotao();
		}
		
	}
	
	public static void toggleBotao() {
		if (primeiroS.isEnabled()) {
//			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));			
//			panel.add(jlb, gbc);
//			panel.add(jlb, gbc);
//			jlb.getAccessibleContext().setAccessibleDescription("teste de mudança");
			primeiroS.setEnabled(false);
//			setT();
//			setTet(textField.toString());
			
			
		}
		else {
//			setT();
//			panel.remove(jlb);
//			jlb.getAccessibleContext().setAccessibleDescription("teste de mudança --- ELSE");
			primeiroS.setEnabled(true);
		}
	}
}
