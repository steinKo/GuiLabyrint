package main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;


/**
 *
 * The GuiHvitRute class er ansvarlig fro interaksjonen med brukeren for en hvit rute i grafiske bruker grensesnittet for 
 */
public class GuiHvitRute extends GuiRute{

	GuiLabyrint guiLabyrint;
	public GuiHvitRute(GuiLabyrint guiLabyrint){
		  super();
		  this.guiLabyrint= guiLabyrint;
		  setStyle("-fx-background-color: white;");
		  setOnAction(new MinRuteAction(this));
		  
		
	 }
	
	/**
	 *
	 * The MinRute class er ansvarlig for å håndtere klikket på knappen.
	 */
	 class MinRuteAction implements EventHandler<ActionEvent>{
		  private GuiHvitRute rute;
		  MinRuteAction(GuiHvitRute rute){
			  this.rute = rute;
		  }
		  
	    @Override 
	    public void handle(ActionEvent e) {
	        
	        int kolonne = GridPane.getColumnIndex(rute);
	        int rad = GridPane.getRowIndex(rute);
	        System.out.println("GuiRute kolonne" + Integer.toString(kolonne));
	        System.out.println("GuiRute rad" + Integer.toString(rad));
	        guiLabyrint.finnUtveiFra(kolonne,rad);
	        
	       
	    }
	}
	 /**
	 *
	 *Skifter farge fra hvit til blå når ruten er endel av en utvei.
	 */
	public void erUtvei() {
		 setStyle("-fx-background-color: blue;");
		
	}
}
