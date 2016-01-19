package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class shippingAddress {
	@FXML
	Scene scene3;
	@FXML
	Stage stage;
	@FXML
	TextField sa_name,recp_name,street,snumber,city,zip,state,country;
	@FXML
	Button add;
	Connection conn = null;
	PreparedStatement statement2;
	

	shippingAddress(){
		
		stage = new Stage();
		
		sa_name = new TextField();
		recp_name = new TextField();
		street = new TextField();
		snumber = new TextField();
		city = new TextField();
		zip = new TextField();
		country = new TextField();
		state = new TextField();
		
		add = new Button("ADD");
		
		sa_name.setPromptText("Shipping Address Name");
		sa_name.setLayoutX(107);
		sa_name.setLayoutY(162);
		
		recp_name.setPromptText("Recepient Name");
		recp_name.setLayoutX(107);
		recp_name.setLayoutY(210);
		
		street.setPromptText("Street");
		street.setLayoutX(107);
		street.setLayoutY(258);
		
		snumber.setPromptText("Street Number");
		snumber.setLayoutX(107);
		snumber.setLayoutY(310);
		
		city.setPromptText("City");
		city.setLayoutX(107);
		city.setLayoutY(354);
		
		zip.setPromptText("Zip");
		zip.setLayoutX(107);
		zip.setLayoutY(405);
		
		state.setPromptText("State");
		state.setLayoutX(107);
		state.setLayoutY(457);
		
		country.setPromptText("Country");
		country.setLayoutX(107);
		country.setLayoutY(508);
		
		add.setLayoutX(107);
		add.setLayoutY(562);
		try{
		AnchorPane page2 = (AnchorPane) FXMLLoader.load(getClass().getResource("/Interface/shipping.fxml"));
		page2.setId("pane");
		page2.getChildren().addAll(sa_name,recp_name,street,snumber,city,zip,state,country,add);
		scene3 = new Scene(page2);
		add.setOnAction(e -> insertShip());
		scene3.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		}
		catch(Exception e1){
			e1.printStackTrace();
		}
		stage.setTitle("Newark IT Company");
		stage.setScene(scene3);
		stage.show();
		
	}


	private void insertShip() {
		// TODO Auto-generated method stub
		signUp sign = new signUp();
		System.out.println("Entered into Button");
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newark_it_db","root","root");
			String query = "insert into shipping_address values(?,?,?,?,?,?,?,?,?)";
			statement2 = conn.prepareStatement(query);

			statement2.setInt(1,sign.increment);
			statement2.setString(2, sa_name.getText());
			statement2.setString(3, recp_name.getText());
			statement2.setString(4, street.getText());
			statement2.setString(5, snumber.getText());
			statement2.setString(6, city.getText());
			statement2.setString(7, zip.getText());
			statement2.setString(8, state.getText());
			statement2.setString(9, country.getText());
			statement2.execute();
			JOptionPane.showMessageDialog(null,	"Shipping Address Registered Successfully. ");
			statement2.close();
			conn.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		clearForm();
		stage.close();
	}


	private void clearForm() {
		// TODO Auto-generated method stub
		recp_name.clear();
		street.clear();
		snumber.clear();
		city.clear();
		zip.clear();
		state.clear();
		country.clear();
		
	}
	
	
}
