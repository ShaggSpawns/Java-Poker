package connection;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Client extends Application {
	
	private TextArea output = new TextArea();
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		output.setEditable(false);
		output.setFocusTraversable(false);
		output.setStyle("-fx-focus-color: transparent;");
		TextField userInput = new TextField();
		userInput.setOnAction(e -> {
			handleInput(userInput.getText());
			userInput.clear();
			});
		
		root.setCenter(output);
		root.setBottom(userInput);
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Seven-Card Stud Poker");
        primaryStage.show();
	}
	
	private void handleInput(String input) {
		if (input.toUpperCase().equals("EXIT"))
			Platform.exit();
		output.appendText(input + "\n");
	}
}
