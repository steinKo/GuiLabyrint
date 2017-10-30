package main;

import java.io.File;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.HBox;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LabyrintApplication  extends Application{

	GuiLabyrint labyrintgrid;
	
	
	
	private HBox lagToppBoks() {
        TextField filFelt = new TextField();
        File valgt= null;
        Button velgFilKnapp = new Button("Velg labyrint fil...");
        velgFilKnapp.setOnAction(new FilVelger(filFelt,valgt));
        Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle("Utveier");
	 	alert.setHeaderText("Ingen flere utveier å vise");
        Button  visNesteUtvei;
		 visNesteUtvei = new Button("Vis Neste Utvei");
		 visNesteUtvei.setOnAction(e->{if(!labyrintgrid.visNesteUtvei())alert.showAndWait();});
        return new HBox(velgFilKnapp, filFelt, visNesteUtvei);
	 }
	
	private class FilVelger implements EventHandler<ActionEvent> {
        TextField filFelt;
        File valgt;
        public FilVelger(TextField filFelt,File valgt) {
            this.filFelt = filFelt;
            this.valgt = valgt;
        }
        @Override
        public void handle(ActionEvent event) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Velg labyrintfil");
            fileChooser.getExtensionFilters().addAll(new ExtensionFilter("IN files", "*.in"));
            valgt = fileChooser.showOpenDialog(null);
            filFelt.setText(valgt.getName());
            try {
				
				labyrintgrid.lesFraFil(valgt);
				
				
			} catch (FileNotFoundException e) {
				
			}
            }
        }
	
	
		
		
	
	
    public static void main(String[] args) {
        launch(args);
    }
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		
	     labyrintgrid = new GuiLabyrint();
	     BorderPane rot;
	     rot = new BorderPane();
	     AnchorPane anchorPane = new AnchorPane();
	     AnchorPane.setTopAnchor(rot, 50.0);
	     AnchorPane.setLeftAnchor(rot, 50.0);
	     AnchorPane.setRightAnchor(rot, 50.0);
	     AnchorPane.setBottomAnchor(rot, 50.0);
	     anchorPane.getChildren().add(rot);
	     HBox toppbox = lagToppBoks();
	     rot.setTop(toppbox);
	     rot.setCenter(labyrintgrid);
	   
		
        Scene scene = new Scene( anchorPane,1000,1000);
        primaryStage.setScene(scene);
        primaryStage.show();
        
	 }
	
    }
        

