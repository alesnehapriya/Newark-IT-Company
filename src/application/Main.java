package application;
	
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	Scene scene1;
	Stage window;
	Button signup;
	@Override
	public void start(Stage primaryStage) {
		window= primaryStage; 
		try {
			signup = new Button("Sign Up");
			
			signup.setLayoutX(1142);
			signup.setLayoutY(88);
			AnchorPane page1 = (AnchorPane) FXMLLoader.load(getClass().getResource("/Interface/HomePage.fxml"));
			scene1 = new Scene(page1);
			scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			page1.getChildren().add(signup);
			window.setTitle("Newark IT Company");
		page1.setId("pane");
			window.setScene(scene1);
			window.show();
			
			signup.setOnAction(e -> new signUp());
			
		} catch(Exception e) {
			System.out.println(e+"  in Main");
		}
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
