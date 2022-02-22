package PuissanceP;

import java.awt.Color;
import java.util.ArrayList;


public class Game {
	private ArrayList <ArrayList <Color>> grille=new ArrayList<>();
	private ArrayList <ArrayList <ArrayList <Integer>>> point_grid=new ArrayList<>();
	private Player playerY, playerR;
	private int pointsY, pointsR;
	private Color color_playing;
	private int compte=0;
	
	
	
	public Game(Player playerY, Player playerR) {
		pointsR=0;
		pointsY=0;
		
		color_playing=Color.yellow;
		
		for (int i=0;i<10;i++) {
			ArrayList<Color> temp=new ArrayList<>();
			ArrayList<ArrayList <Integer>> temp2 = new ArrayList<>();
			for (int j=0;j<10;j++) {
				temp.add(Color.white);
				ArrayList <Integer> temp3=new ArrayList<>();
				temp2.add(temp3);
			}
			point_grid.add(temp2);
			grille.add(temp);
		}
		this.playerY = playerY;
		this.playerR = playerR;
	}

	public Color get_color_playing() {
		return color_playing;
	}
	public Player get_player_playing() {
		if (color_playing == Color.yellow) {
			return playerY;
		}
		return playerR;
	}
	
	public int get_pointsY() {
		return pointsY;
	}
	
	public int get_pointsR() {
		return pointsR;
	}
	
	public Color oposite_color() {
		if (color_playing == Color.yellow) {
			return Color.red;
		}
		else {
			 return Color.yellow;
		}
	}

	public void play(int ligne, int colonne) {
		if (color_playing == Color.yellow) {
			grille.get(ligne).set(colonne, color_playing);
		}
		else {
			grille.get(ligne).set(colonne, color_playing);
		}
	
		//verif point
		verif_new_point(ligne, colonne);
		
		color_playing=oposite_color();
	}
	
	public int test_plays(int ligne, int colonne, Color c){
		int taille=10;
	    int i=ligne+1;
	    Boolean a=true;
	    if (grille.get(ligne).get(colonne) != Color.white) {
	    	return(0);
	    }
	    if (ligne ==0 || ligne == taille-1 || colonne ==0 || colonne ==9) return 1;
	    
	    while (i<taille) {
	    	if (grille.get(i).get(colonne) == Color.white) {
	    		a=false;
	    		break;
	    	}
	    	i++;
	    }
	    if (a) {
	    	return 1;
	    }
	    a=true;
	    i=ligne-1;
	    
	    while (i>=0) {
	    	if (grille.get(i).get(colonne) == Color.white) {
	    		a=false;
	    		break;
	    	}
	    	i--;
	    }
	    if (a) {
	    	return 1;
	    }
	    a=true;
	    
	    int j=colonne-1;
	    while (j>=0) {
	    	if (grille.get(ligne).get(j) == Color.white) {
	    		a=false;
	    		break;
	    	}
	    	j--;
	    }
	    if (a) {
	    	return 1;
	    }
	    a=true;
	    j=colonne+1;
	    
	    while (j<taille) {
	    	if (grille.get(ligne).get(j) == Color.white) {
	    		a=false;
	    		break;
	    	}
	    	j++;
	    }
	    if (a) {
	    	return 1;
	    }
	    
	    return(0);
	}
	
	
	public void verif_new_point(int ligne,int colonne) {
		//4 cas possible
			//pour chaque cas vérifier si 3 des cases ne sont pas déja utilisé
		ArrayList <Square> s=new ArrayList<Square>();
		
		//ligne
		for (int i=0;i<4;i++) {
			for (int j=0;j<4;j++) {
				if (ligne - i +j <10 && ligne - i +j >= 0 && grille.get(ligne-i+j).get(colonne) == color_playing) {
					s.add(new Square(ligne-i+j,colonne));
				}
				if (s.size()==4) {
					check_vector_point(s);
				}
			}
			s.removeAll(s);
		}
		
		//colonne
		for (int i=0;i<4;i++) {
			for (int j=0;j<4;j++) {
				if (colonne - i +j <10 && colonne - i +j >= 0 && grille.get(ligne).get(colonne-i+j) == color_playing) {
					s.add(new Square(ligne,colonne-i+j));
				}
				if (s.size()==4) {
					check_vector_point(s);
				}
			}
			s.removeAll(s);
		}
		
		
		
		
		for (int i=0;i<4;i++) {
			for (int j=0;j<4;j++) {
				if (ligne - i +j <10 && ligne - i +j >= 0 && colonne - i +j <10 && colonne - i +j >= 0 && grille.get(ligne-i+j).get(colonne-i+j) == color_playing) {
					s.add(new Square(ligne-i+j,colonne-i+j));
				}
				if (s.size()==4) {
					check_vector_point(s);
				}
			}
			s.removeAll(s);
		}
		
		for (int i=0;i<4;i++) {
			for (int j=0;j<4;j++) {
				if (ligne - i +j <10 && ligne - i +j >= 0 && colonne + i -j <10 && colonne + i -j >= 0 && grille.get(ligne-i+j).get(colonne+i-j) == color_playing) {
					s.add(new Square(ligne-i+j,colonne+i-j));
				}
				if (s.size()==4) {
					check_vector_point(s);
				}
			}
			s.removeAll(s);
		}
	}
	
	public int check_vector_point(ArrayList <Square> l) {
		ArrayList<Integer> stock=new ArrayList<Integer>(100);
		for (int i=0;i<100;i++) {
			stock.add(0);
		}
		
		for (int p=0;p<4;p++) {
			Square square=l.get(p);
			ArrayList<Integer> grid=point_grid.get(square.x).get(square.y);
			for (int i=0;i<grid.size();i++) {
				int temp=stock.get(grid.get(i));
				stock.set(grid.get(i),temp+1);
			}
		}
		
		for (int i=0;i<100;i++) {
			if (stock.get(i) >2) {
				return 1;
			}
		}
		for (int p=0;p<4;p++) {
			Square square=l.get(p);
			point_grid.get(square.x).get(square.y).add(compte);
		}
		compte++;
		System.out.println("point\n");
		if (color_playing == Color.yellow) {
			pointsY++;
		}
		else {
			pointsR++;
		}
		return(0);
	}
	

	public int check_end_game() {
		for (int i=0;i<10;i++) {
			for (int j=0;j<10;j++) {
				if (grille.get(i).get(j) == Color.white) return 1; 
			}
		}
		return(0);
	}
}
