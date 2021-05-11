package com.accenture.gcp.analise.menu;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

public class TemplateGrid extends JPanel{

	private static final long serialVersionUID = 1L;

	public TemplateGrid() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0.33;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(4, 4, 4, 4);

		add((new TemplateInfo()), gbc);
		gbc.gridy++;
		add((new TemplateOptions()), gbc);
		gbc.gridy++;
		add((new TemplateStatus()), gbc);
		gbc.gridy++;

		gbc.gridy = 0;
		gbc.gridx++;
		gbc.gridheight = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.weighty = 1;
		gbc.weightx = 0;
	}
}
