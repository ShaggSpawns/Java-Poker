package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RunnerGUI extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane border = new BorderPane();
        border.setCenter(launchSceen());
 
        Scene scene = new Scene(border, 1280, 720);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Seven-Card Stud Poker");
        primaryStage.show();
	}
	
	private GridPane launchSceen() {
		GridPane grid = new GridPane();
		Button play = new Button("Play");
		Button host = new Button("Host and Play");
		grid.add(play, 0, 0);
		grid.add(host, 0, 1);
		return grid;
	}
}
