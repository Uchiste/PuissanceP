package PuissanceP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Accueil extends JFrame implements ActionListener{
	private JTextArea aj1;
	private JTextArea aj2;
	private JButton lancer;
	private JFrame frame;
	
	public Accueil() {
		
		frame=new JFrame();
		frame.setTitle("PuissanceP");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLayout(new GridLayout(4,1));
		JTextField welcome = new JTextField("\t  Bienvenue sur PuissanceP");
		welcome.setEditable(false);
		frame.add(welcome);
		
		JTextField textPseudo = new JTextField("\t  Donner les pseudos");
		textPseudo.setEditable(false);
		frame.add(textPseudo);
		
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(2,2));
		JTextField t1=new JTextField("joueur 1");
		t1.setEditable(false);
		JTextField t2=new JTextField("joueur 2");
		t2.setEditable(false);
		aj1=new JTextArea();
		aj2=new JTextArea();
		p.add(t1);
		p.add(t2);
		p.add(aj1);
		p.add(aj2);
		
		frame.add(p);
		
		lancer=new JButton("Lancer la partie");
		lancer.addActionListener(this);
		frame.add(lancer);
		
		

		
		frame.setSize(400,500);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == lancer) {
			String sj1=aj1.getText();
			String sj2=aj2.getText();
			if (sj1.length() == 0 || sj2.length() == 0) {
				System.out.println("erreur, les pseudo sont vide\n");
				return;
			}
			Human j1=new Human(sj1,Color.yellow);
			Human j2=new Human(sj2,Color.red);
			frame.setVisible(false);
			Window window = new Window(j1,j2);
		}
	}

}
