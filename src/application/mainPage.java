package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class mainPage {
	
	Label user;
	Button signout,b1,b2,b3,b4,b5,b6,add;
	Scene scene3;
	Stage stage;
	int storage=0,inc1=0, pid=0,price=0;
	String quan;
	Connection conn =null;
	signUp sign;
	String mem=null;
	mainPage(){
		sign = new signUp();
		stage = new Stage();
		
		b1 = new Button("SELECT");
		b2 = new Button("SELECT");
		b3 = new Button("SELECT");
		b4 = new Button("SELECT");
		b5 = new Button("SELECT");
		b6 = new Button("SELECT");
		add = new Button("ADD TO CART");
		
		
		
		user = new Label();
		user.setText(sign.name);
		user.setLayoutX(70);
		user.setLayoutY(25);
		
		signout = new Button("Sign OUT");
		signout.setLayoutX(1180);
		signout.setLayoutY(20);
		
		b1.setLayoutX(145);
		b1.setLayoutY(472);
		
		b2.setLayoutX(577);
		b2.setLayoutY(442);
		
		b3.setLayoutX(979);
		b3.setLayoutY(442);
		
		b4.setLayoutX(158);
		b4.setLayoutY(790);
		
		b5.setLayoutX(530);
		b5.setLayoutY(775);
		
		b6.setLayoutX(955);
		b6.setLayoutY(790);
		
		add.setLayoutX(1162);
		add.setLayoutY(814);
		
		b1.setOnAction(e -> addOnCartb1());
		b2.setOnAction(e -> addOnCartb2());
		b3.setOnAction(e -> addOnCartb3());
		b4.setOnAction(e -> addOnCartb4());
		b5.setOnAction(e -> addOnCartb5());
		b6.setOnAction(e -> addOnCartb6());
		
		try{
			AnchorPane page2 = (AnchorPane) FXMLLoader.load(getClass().getResource("/Interface/Items.fxml"));
			page2.setId("pane");
			page2.getChildren().addAll(user,signout,b1,b2,b3,b4,b5,b6,add);
			scene3 = new Scene(page2);
			add.setOnAction(e -> insertCart());
			scene3.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			}
			catch(Exception e1){
				e1.printStackTrace();
			}

			
			
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newark_it_db","root","root");
				String query = "select max(cartid) as maxCardID from appears_in";			
				PreparedStatement statement1 = conn.prepareStatement(query);
				System.out.println("trying mainPage"+sign.increment);
				ResultSet rs = statement1.executeQuery(query);
				while(rs.next()){
					inc1 = rs.getInt("maxCardID");
				}

				System.out.println("inc1: "+inc1+"mem: "+mem);
				inc1++;
				statement1.close();
				conn.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try{
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newark_it_db","root","root");
				String query1 = "select c_status from customer where cid=?";
				PreparedStatement statement2 = conn.prepareStatement(query1);
				statement2.setInt(1, sign.increment);
				ResultSet rs1 = statement2.executeQuery(query1);
				while(rs1.next()){
					mem = rs1.getString("c_status");
				}
				
				statement2.close();
				conn.close();
			}catch(SQLException e){
				
			}
			
			stage.setTitle("Newark IT Company");
			stage.setScene(scene3);
			stage.show();	
			
	}
	private void addOnCartb6() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Item is Selected");
		storage++;
		pid= 205;
		quan = JOptionPane.showInputDialog(null,"Enter the quantity of the item.");
		price =100;
		addItems();
		
	}
	private void addOnCartb5() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Item is Selected");
		storage++;
		pid= 204;
		quan = JOptionPane.showInputDialog(null,"Enter the quantity of the item.");
		if(mem=="Regular"){
			price = 1000;
		}
		else
			price = 850;
		addItems();
		
	}
	private void addOnCartb4() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Item is Selected");
		storage++;
		pid= 203;
		quan = JOptionPane.showInputDialog(null,"Enter the quantity of the item.");
		if(mem=="Regular"){
			price = 500;
		}
		else
			price = 490;
		addItems();
		
	}
	private void addOnCartb3() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Item is Selected");
		storage++;
		pid= 202;
		quan = JOptionPane.showInputDialog(null,"Enter the quantity of the item.");
		if(mem=="Regular"){
			price = 200;
		}
		else
			price = 175;
		addItems();
		
	}
	private void addOnCartb2() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Item is Selected");
		storage++;
		pid= 201;
		quan = JOptionPane.showInputDialog(null,"Enter the quantity of the item.");
		if(mem=="Regular"){
			price = 1000;
		}
		else
			price = 800;
		addItems();
	}
	private void insertCart() {
		// TODO Auto-generated method stub
		new CreditCard();
		
	}
	private void addOnCartb1() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Item is Selected");
		storage++;
		pid= 200;
		quan = JOptionPane.showInputDialog(null,"Enter the quantity of the item.");
		if(mem=="Regular"){
			price = 450;
		}
		else
			price = 500;
		addItems();
	}
	
	public void addItems(){
		try {

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newark_it_db","root","root");
			String query = "insert into appears_in values(?,?,?,?)";
			PreparedStatement statement2 = conn.prepareStatement(query);
			statement2.setInt(1,inc1);
			statement2.setInt(2, pid);
			statement2.setInt(3, Integer.parseInt(quan));
			statement2.setInt(4, Integer.parseInt(quan)*price);
			JOptionPane.showMessageDialog(null,	"Item added to Cart.");
			statement2.execute();
			statement2.close();
			conn.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
