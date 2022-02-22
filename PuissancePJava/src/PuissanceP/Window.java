package PuissanceP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class Window extends JFrame implements ActionListener{
	
	private ArrayList <ArrayList <Button>> list;
	private JTextField jaune,rouge,play;
	private JButton quitter;
	private Game game;
	private JFrame frame;
	
	public Window(){
		Human j1=new Human("j1",Color.yellow);
		Human j2=new Human("j2",Color.red);
		game=new Game(j1, j2);
		
		frame=new JFrame();
		frame.setTitle("PuissanceP");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLayout(new GridLayout(11,1));
		
		
		
		list=new ArrayList<ArrayList<Button>>();
		for (int i=0;i<10;i++){
			ArrayList <Button> temp=new ArrayList();
			
			JPanel grille=new JPanel();
			grille.setLayout(new GridLayout(1, 10));
			for (int j=0;j<10;j++) {
				Button b=new Button(i, j);
				temp.add(b);
				
				JButton jb=b.getButton();
				jb.addActionListener(this);
				jb.setPreferredSize(new Dimension(10, 10)); 
				grille.add(jb);
			}
			frame.add(grille);
			list.add(temp);
			
		}
	}
		
		public Window(Human j1, Human j2){
			
			game=new Game(j1, j2);
			
			JFrame frame=new JFrame();
			frame.setTitle("PuissanceP");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			frame.setLayout(new GridLayout(11,1));
			
			
			
			list=new ArrayList<ArrayList<Button>>();
			for (int i=0;i<10;i++){
				ArrayList <Button> temp=new ArrayList();
				
				JPanel grille=new JPanel();
				grille.setLayout(new GridLayout(1, 10));
				for (int j=0;j<10;j++) {
					Button b=new Button(i, j);
					temp.add(b);
					
					JButton jb=b.getButton();
					jb.addActionListener(this);
					jb.setPreferredSize(new Dimension(10, 10)); 
					grille.add(jb);
				}
				frame.add(grille);
				list.add(temp);
				
			}
		
		JPanel display=new JPanel();
		display.setLayout(new GridLayout(1,4));
		jaune = new JTextField("jaune");
		display.add(jaune);
		rouge = new JTextField("rouge");
		display.add(rouge);
		play = new JTextField("play");
		play.setBackground(game.get_color_playing());
		play.setText(j1.getName());
		display.add(play);
		quitter = new JButton("Quitter");
		quitter.addActionListener(this);
		display.add(quitter);
		frame.add(display);
		
		frame.setSize(400,500);
		frame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==quitter) {
			frame.setVisible(false);
			Accueil a = new Accueil();
			
		}
		else {
			for (int i=0;i<10;i++) {
				for (int j=0;j<10;j++) {
					Button b=list.get(i).get(j);
					if (e.getSource() == b.getButton()) {
						Color color_playing=game.get_color_playing();
						if (game.test_plays(i, j, color_playing) == 1) {
							b.getButton().setBackground(color_playing);
							game.play(i,j);
						}
					}
				}
			}
			
			String temp="Point Jaune : "+Integer.toString(game.get_pointsY());
			jaune.setText(temp);
			temp="Point Rouge : "+Integer.toString(game.get_pointsR());
			rouge.setText(temp);
			play.setBackground(game.get_color_playing());
			play.setText(game.get_player_playing().getName());
			if (game.check_end_game() == 0) {
				//fin de game
				System.out.println("FIN DE LA PARTIE");
			}
		}
		
		
	}
	
}
