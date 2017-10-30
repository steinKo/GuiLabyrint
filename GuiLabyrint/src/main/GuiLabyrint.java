package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Labyrint;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.layout.GridPane;
import main.Liste;

/**
*
* The GuiLabyrint class representerer labyrinten i Gui
*/

public class GuiLabyrint extends GridPane{
	
	int hoyde,bredde;
	private Labyrint labyrint;
	 
	 private Liste<String> utveier;
	 public GuiLabyrint(){
   	;
   	  
     }
	 private void opprettGuiRute(String line, int i, int j) {
			char ruteTegn;
	        ruteTegn =line.charAt(j);
	  
	        if (ruteTegn =='.')	
	        add(new GuiHvitRute(this), j+1, i+1);

	        if (ruteTegn =='#')
	        add(new GuiSortRute(), j+1, i+1);
		}
	 
		 private int koordinat(Scanner scanner) {
				String srader = scanner.next();
				return   Integer.parseInt(srader);
		 }		
	 
	 /**
		 * Konverterer losning-String fra oblig 5 til en boolean[][]-representasjon
		 * av losningstien.
		 * @param losningString String-representasjon av utveien
		 * @param bredde        bredde til labyrinten
		 * @param hoyde         hoyde til labyrinten
		 * @return              2D-representasjon av rutene der true indikerer at
		 *                      ruten er en del av utveien.
		 */
     static boolean[][] losningStringTilTabell(String losningString, int bredde, int hoyde) {
		    boolean[][] losning = new boolean[hoyde][bredde];
		    java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\(([0-9]+),([0-9]+)\\)");
		    java.util.regex.Matcher m = p.matcher(losningString.replaceAll("\\s",""));
		    while(m.find()) {
		        int x = Integer.parseInt(m.group(1))-1;
		        int y = Integer.parseInt(m.group(2))-1;
		        losning[y][x] = true;
		    }
		    return losning;
	 }
	 
     /**
		 * Lesfra fil og opprett ruter i gui
		 * .
		 * @param file file med tegn for ruter i labyrinten

		 */
	
	 public void lesFraFil(File fil) throws FileNotFoundException {   
        labyrint= Labyrint.lesFraFil(fil);
        System.out.println(labyrint.toString());
  	  try
  	  {
         	  
  	      Scanner input = new Scanner(fil);
  	      String kolonneOgRader = input.nextLine();
  	      Scanner scanner = new Scanner(kolonneOgRader);
  	      
  	      //les fra fil antall rader og kolonne
  	       hoyde = koordinat(scanner);
  	       bredde = koordinat(scanner);
  	      
  	      String line;
  	      
  	      //sletter tidliger labyrint  fra gui slik at vi kan laste en ny
  	      getChildren().clear();
  	      
  	      //opprett Gui for labyrint
  	      for (int i= 0;i< hoyde;i++){
  		     line = input.nextLine();
  	         for (int j = 0; j < bredde; j++) {
  	        	     opprettGuiRute(line, i, j);
			 }
  		  }
  	     scanner.close();
  	     input.close();
  
  	  }
  	      catch (FileNotFoundException fnFx){
  		  throw fnFx;
  	  }
    }

	
			
		
	public void finnUtveiFra(int startKollonne, int startRad) {
		System.out.println(Integer.toString(startKollonne) + " " + Integer.toString( startRad));
		utveier = labyrint.finnUtveiFra(startKollonne, startRad);
		if (!utveier.erTom())
		visUtvei();
		else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
	       alert.setTitle("Utveier");
	 	   alert.setHeaderText("Ingen utveier fra denne ruten");
	       alert.showAndWait();
		}
	}


	/**
	 * Vis utveiene som er funnet i labyrinten 
	 * .
	 */

	private void visUtvei() {
		String utvei =utveier.fjern();
		System.out.println("utvei" + utvei);
		System.out.println(Integer.toString(bredde) + " "+ Integer.toString(hoyde));
		boolean[][] losning = losningStringTilTabell(utvei,bredde,hoyde);
		
		//Forandre farge på rutene og Legge til en utvei til gridpan 
		for (int i= 0; i< bredde;i++)
			for (int j= 0; j<hoyde;j++){
				
				if (losning[j][i])
				{
					GuiHvitRute rute = new GuiHvitRute(this);
					rute.erUtvei();
					add(rute,i+1,j+1);	
				}
			}
	}


	/**
	 * Vis neste utveien som er funnet i labyrinten 
	 * .
	 */

	public boolean visNesteUtvei() {
		if (utveier == null) 
			return false;
		else
		     if (!utveier.erTom()) {
			     visUtvei();
			     return true;
		     }
		else
			return false;		
	}
	 	 
}
