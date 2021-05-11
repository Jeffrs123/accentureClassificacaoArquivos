package com.accenture.gcp.analise;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.accenture.gcp.analise.menu.SessaoBaseUI;
import com.accenture.gcp.analise.menu.SessaoMenuItems;
import com.accenture.gcp.analise.menu.Template;
import com.accenture.gcp.analise.menu.ANTIGOSessaoMenuItems;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(Application.class, args);
		System.setProperty("java.awt.headless", "false");
		Template.iniciarMenu();
//		run();
		//		new BaseUI();
		//		System.out.println("curFile: " + curFile);
		//		System.out.println("chooser getCurrentDirectory: " + chooser.getCurrentDirectory());
		//		System.out.println("chooser getSelectedFile: " + chooser.getSelectedFile());
		//		fimProcesso();
		//		System.exit(0);

	}

//	private static void run() {
//		//      try {
//		//      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		//  } 
//		//  catch (Exception e) {
//		////  catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//		//      e.printStackTrace();
//		//  }
//
//		JFrame frame = new JFrame("ACCENTURE - Filtro de Arquivos");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.add(new SessaoBaseUI());
//		frame.pack();
//		frame.setLocationRelativeTo(null);
//		frame.setVisible(true);
//	}
}
