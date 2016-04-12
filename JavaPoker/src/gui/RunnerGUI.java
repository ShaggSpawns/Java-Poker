package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RunnerGUI extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		root.setCenter(launchScreen(root));
 
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Seven-Card Stud Poker");
        primaryStage.show();
	}
	
	private GridPane launchScreen(BorderPane root) {
		GridPane grid = new GridPane();
		Button playBtn = new Button("Play");
		Button hostBtn = new Button("Host and Play");
		grid.add(playBtn, 0, 0);
		grid.add(hostBtn, 0, 1);

		playBtn.setOnAction(e -> root.setCenter(playScreen()));
		hostBtn.setOnAction(e -> root.setCenter(hostScreen()));
		return grid;
	}
	
	private GridPane playScreen() {
		GridPane grid = new GridPane();
		grid.add(new Label("Playing"), 0, 0);
		return grid;
	}
	
	private TabPane hostScreen() {
		TabPane tab = new TabPane();
		Tab playTab = new Tab();
		playTab.setContent(playScreen());
		Tab hostTab = new Tab();
		return tab;
	}
}
