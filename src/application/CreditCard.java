package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CreditCard {
	@FXML
	Label bill;
	Connection conn=null;
	mainPage page;
	int total;
	@FXML
	TextField TF1,TF2,TF3,TF4,TF5,TF6;
	
	Button rem,pay;
	signUp sign;
    Date date;
    SimpleDateFormat date_format;
	Scene scene4;
	Stage stage3;
	
	CreditCard(){
		
		date = new Date();
		date_format = new SimpleDateFormat("yyyy-mm-dd");
		
		stage3 = new Stage();
		sign = new signUp();
		
		bill = new Label();
		page = new mainPage();
		
		TF1 = new TextField();
		TF1.setPromptText("CC Number");
		TF1.setLayoutX(94);
		TF1.setLayoutY(204);
		
		TF2 = new TextField();
		TF2.setPromptText("Sec Number");
		TF2.setLayoutX(94);
		TF2.setLayoutY(247);
		
		TF3 = new TextField();
		TF3.setPromptText("Owner Name");
		TF3.setLayoutX(94);
		TF3.setLayoutY(293);
		
		TF4 = new TextField();
		TF4.setPromptText("Credit Type");
		TF4.setLayoutX(94);
		TF4.setLayoutY(342);
		
		TF5 = new TextField();
		TF5.setPromptText("CC Address");
		TF5.setLayoutX(94);
		TF5.setLayoutY(389);
		
		TF6 = new TextField();
		TF6.setPromptText("Expiry Date");
		TF6.setLayoutX(94);
		TF6.setLayoutY(443);
		
		rem = new Button("Remember Card Details");
		rem.setLayoutX(94);
		rem.setLayoutY(506);
		
		rem.setOnAction(e -> remember());
		
		pay = new Button("PAY");
		pay.setLayoutX(150);
		pay.setLayoutY(570);
		
		pay.setOnAction(e -> pay());
	
		try{
			AnchorPane page2 = (AnchorPane) FXMLLoader.load(getClass().getResource("/Interface/card.fxml"));
			page2.setId("pane");
			page2.getChildren().addAll(bill,TF1,TF2,TF3,TF4,TF5,TF6,rem,pay);
			scene4 = new Scene(page2);
			scene4.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			}
			catch(Exception e1){
				e1.printStackTrace();
			}

		
	stage3.setTitle("Newark IT Company");
	stage3.setScene(scene4);
	stage3.show();	
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newark_it_db","root","root");
			String query1 = "select sum(pricesold)as total from appears_in where cartid = ?";
			PreparedStatement statement3 = conn.prepareStatement(query1);
			statement3.setInt(1, page.inc1);
			ResultSet rs = statement3.executeQuery();
			while(rs.next()){
				total = rs.getInt("total");
			}
			bill.setText("$"+Integer.toString(1900));
			bill.setLayoutX(219);
			bill.setLayoutY(151);
			statement3.close();
			conn.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		



		
	}

	private void pay() {
		// TODO Auto-generated method stub
		try {
			String saname = null;
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newark_it_db","root","root");
			String query = "insert into cart values(?,?,?,?,?,?)";
			String query1 = "select saname from shipping_address where cid = ?";
			PreparedStatement statement2 = conn.prepareStatement(query);
			PreparedStatement statement3 = conn.prepareStatement(query1);
			statement3.setInt(1, sign.increment);
			ResultSet rs = statement3.executeQuery();
			while(rs.next()){
				saname = rs.getString("saname");
			}
			statement2.setInt(1,page.inc1);
			statement2.setInt(2, sign.increment);
			System.out.println("trying CC"+sign.increment);
			System.out.println("trying CC Page"+page.inc1);
			statement2.setString(3, saname);
			statement2.setInt(4, Integer.parseInt(TF1.getText()));
			statement2.setString(5, "Not Delivered");
			statement2.setString(6, "2015-09-15");
			JOptionPane.showMessageDialog(null,	"Transaction has been successful.");
			statement2.execute();
			statement2.close();
			conn.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	}

	private void remember() {
		// TODO Auto-generated method stub
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newark_it_db","root","root");
			String query = "insert into credit_card values(?,?,?,?,?,?)";
			String query1 = "insert into stored_card values(?,?)";
			PreparedStatement statement2 = conn.prepareStatement(query);
			PreparedStatement statement3 = conn.prepareStatement(query1);
			statement2.setInt(1,Integer.parseInt(TF1.getText()));
			statement2.setInt(2, Integer.parseInt(TF2.getText()));
			statement2.setString(3, TF3.getText());
			statement2.setString(4, TF4.getText());
			statement2.setString(5, TF5.getText());
			statement2.setString(6, TF6.getText());
			statement3.setInt(1, Integer.parseInt(TF1.getText()));
			statement3.setInt(2, sign.increment);
			JOptionPane.showMessageDialog(null,	"Credit Card Stored");
			statement2.execute();
			statement3.execute();
			statement3.close();
			statement2.close();
			conn.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
