package com.accenture.gcp.analise.menu;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.accenture.gcp.analise.util.Processamento;

public class TemplateStatus extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static JButton primeiroS;
	public static JTextField textField = new JTextField("Bem vindo!");

	public TemplateStatus() {

		JPanel panel = new JPanel(new GridBagLayout());
		setLayout(new GridBagLayout());
		setBorder(new CompoundBorder(new TitledBorder("Status Processamento"), new EmptyBorder(12, 0, 0, 0)));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(0, 0, 0, 4);

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

		textField.setEditable(false);
		add(textField, gbc);
		gbc.gridx++;


		primeiroS.addActionListener(this);
		
		Processamento.chegarTodosDiretoriosArquivos();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == primeiroS){
			setTextField("Processando");
			if (Processamento.iniciarProcesso()) {
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				
				setTextField("Arquivos processados com SUCESSO! \\o/\\O/\\o/ " + dateFormat.format(date));
			}
		}
	}

	public static void setTextField(String message) {
		TemplateStatus.textField.setText(message);
	}

	public static void toggleBotao(boolean valor) {
		primeiroS.setEnabled(valor);
	}
}
