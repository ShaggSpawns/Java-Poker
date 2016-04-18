package gui;

import connection.Client;
import connection.NetworkConnection;
import connection.Server;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
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
		TextArea text = new TextArea();
	    NetworkConnection connection = createClient(text);
	    try {
			connection.startConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    GridPane grid = new GridPane();
		text.setEditable(false);
		text.setFocusTraversable(false);
		GridPane.setConstraints(text, 0, 0, 2, 1, null, null, Priority.ALWAYS, Priority.ALWAYS, new Insets(0,0,0,0));
		TextField textField = new TextField();
		textField.setOnAction(e -> {
			String message = "Client: " + textField.getText();
			
			text.appendText(message + "\n");
			textField.clear();
			try {
				connection.send(message);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		GridPane.setConstraints(textField, 0, 1, 1, 1, null, null, Priority.ALWAYS, Priority.NEVER, new Insets(5,5,5,5));
		Button submitBtn = new Button("Submit");
		submitBtn.setOnAction(e -> textField.fireEvent(e));
		GridPane.setConstraints(submitBtn, 1, 1, 1, 1, null, null, Priority.NEVER, Priority.NEVER, new Insets(5,5,5,5));
		grid.getChildren().addAll(text, textField, submitBtn);
		return grid;
	}

    private Client createClient(TextArea text) {
        return new Client("127.0.0.1", 55555, data -> {
            Platform.runLater(() -> {
                text.appendText(data.toString() + "\n");
            });
        });
    }
	
	private GridPane hostScreen() {
		TextArea text = new TextArea();
	    NetworkConnection connection = createServer(text);
	    GridPane grid = new GridPane();
		text.setEditable(false);
		text.setFocusTraversable(false);
		GridPane.setConstraints(text, 0, 0, 2, 1, null, null, Priority.ALWAYS, Priority.ALWAYS, new Insets(0,0,0,0));
		TextField textField = new TextField();
		textField.setOnAction(e -> {
			String message = "Server: " + textField.getText();
			
			text.appendText(message + "\n");
			textField.clear();
			try {
				connection.send(message);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		GridPane.setConstraints(textField, 0, 1, 1, 1, null, null, Priority.ALWAYS, Priority.NEVER, new Insets(5,5,5,5));
		Button submitBtn = new Button("Submit");
		submitBtn.setOnAction(e -> textField.fireEvent(e));
		GridPane.setConstraints(submitBtn, 1, 1, 1, 1, null, null, Priority.NEVER, Priority.NEVER, new Insets(5,5,5,5));
		grid.getChildren().addAll(text, textField, submitBtn);
		return grid;
	}
	
    private Server createServer(TextArea text) {
        return new Server(55555, data -> {
            Platform.runLater(() -> {
                text.appendText(data.toString() + "\n");
            });
        });
    }
}
