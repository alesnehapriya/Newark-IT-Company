package application;

import java.io.IOException;
import java.sql.*;

import javax.swing.JOptionPane;

import javafx.beans.value.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class signUp {
	Scene scene2;
	Stage stage;
	@FXML
	Button myBtnYo;
	Connection conn=null;
	@FXML
	Button creatBtn,addShip,next;
	PreparedStatement statement1,statement2;
	@FXML
	TextField firstName,lastName,email,address,pNumber;
	int increment=0;
	@FXML
	RadioButton regular,silver,gold,platinum;
	
	@FXML
	ToggleGroup membership;
	@FXML
	RadioButton chk;
	@FXML
	String name;
	
	signUp(){
		// TODO Auto-generated method stub


		try {
			stage = new Stage();
			
			firstName = new TextField();
			lastName = new TextField();
			email = new TextField();
			address = new TextField();
			pNumber = new TextField();
			
			regular = new RadioButton("Regular");
			silver = new RadioButton("Silver");
			gold = new RadioButton("Gold");
			platinum = new RadioButton("Platinum");
			
			membership = new ToggleGroup();
			
			firstName.setPromptText("First Name");
			firstName.setLayoutX(102);
			firstName.setLayoutY(187);
			
			
			lastName.setPromptText("Last Name");
			lastName.setLayoutX(308);
			lastName.setLayoutY(187);
			
			email.setPromptText("Email");
			email.setLayoutX(102);
			email.setLayoutY(237);
			
			address.setPromptText("Address");
			address.setLayoutX(102);
			address.setLayoutY(290);
			
			pNumber.setPromptText("Phone Number");
			pNumber.setLayoutX(102);
			pNumber.setLayoutY(346);
			
			regular.setLayoutX(102);
			regular.setLayoutY(402);
			regular.setSelected(true);
			regular.setToggleGroup(membership);
			
			silver.setLayoutX(196);
			silver.setLayoutY(403);
			silver.setToggleGroup(membership);
			
			gold.setLayoutX(281);
			gold.setLayoutY(403);
			gold.setToggleGroup(membership);
			
			platinum.setLayoutX(363);
			platinum.setLayoutY(403);
			platinum.setToggleGroup(membership);
			
				
			creatBtn = new Button("CREATE ACCOUNT");
			addShip = new Button("ADD SHIPPING ADDRESS");
			next = new Button("NEXT");
			AnchorPane page2 = (AnchorPane) FXMLLoader.load(getClass().getResource("/Interface/SignUp.fxml"));
			page2.setId("pane");
			creatBtn.setLayoutX(102);
			creatBtn.setLayoutY(455);
			addShip.setLayoutX(102);
			addShip.setLayoutY(520);
			next.setLayoutX(94);
			next.setLayoutY(567);
			page2.getChildren().addAll(creatBtn,firstName,lastName,email,address,pNumber,regular,silver,gold,platinum,addShip,next);
			scene2 = new Scene(page2);
			scene2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			creatBtn.setOnAction(e ->{
						insertData(e);
						
			}
						);
			
			addShip.setOnAction(e -> new shippingAddress());
			
			 membership.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			        @Override
			        public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
			        	try{
			        	chk = (RadioButton)t1.getToggleGroup().getSelectedToggle();
			        	}
			        	catch(Exception e2){
			        		
			        	}
			            System.out.println("Selected Radio Button - "+chk.getText());

			        }
			    });
			
			 next.setOnAction(e ->{ new mainPage();
			 stage.close();
			 });
			
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newark_it_db","root","root");
				String query = "select max(CID) as maxCID from customer";
				statement1 = conn.prepareStatement(query);
				ResultSet rs = statement1.executeQuery(query);
				while(rs.next()){
					increment = rs.getInt("maxCID");
				}
				System.out.println(increment);
				statement1.close();
				conn.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage.setTitle("Newark IT Company");
		stage.setScene(scene2);
		stage.show();

			
		
	}
	

	public void insertData(ActionEvent e) {
		// TODO Auto-generated method stub
		
			System.out.println("Entered into Button");
			try {
				increment++;
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newark_it_db","root","root");
				String query = "insert into customer values(?,?,?,?,?,?,?)";
				statement2 = conn.prepareStatement(query);
				statement2.setInt(1,increment);
				statement2.setString(2, firstName.getText());
				name = firstName.getText();
				statement2.setString(3, lastName.getText());
				statement2.setString(4, email.getText());
				statement2.setString(5, address.getText());
				statement2.setString(6, pNumber.getText());
				statement2.setString(7, chk.getText());
				JOptionPane.showMessageDialog(null,	"User Registered Successfully. Your Password has been sent to your Email.");
				statement2.execute();
				statement2.close();
				conn.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(chk.getText()=="Silver"){
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newark_it_db","root","root");
					String query = "insert into silver_and_above values(?,?)";
					statement2 = conn.prepareStatement(query);
					statement2.setInt(1,increment);
					statement2.setInt(2, 50);
				
					JOptionPane.showMessageDialog(null,	"User added to SILVER and ABOVE Category with 50 as CreditLine");
					statement2.execute();
					statement2.close();
					conn.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			if(chk.getText()=="Gold"){
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newark_it_db","root","root");
					String query = "insert into silver_and_above values(?,?)";
					statement2 = conn.prepareStatement(query);
					statement2.setInt(1,increment);
					statement2.setInt(2, 75);
				
					JOptionPane.showMessageDialog(null,	"User added to SILVER and ABOVE Category with 75 as CreditLine");
					statement2.execute();
					statement2.close();
					conn.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			if(chk.getText()=="Platinum"){
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newark_it_db","root","root");
					String query = "insert into silver_and_above values(?,?)";
					statement2 = conn.prepareStatement(query);
					statement2.setInt(1,increment);
					statement2.setInt(2, 50);
				
					JOptionPane.showMessageDialog(null,	"User added to SILVER and ABOVE Category with 100 as CreditLine");
					statement2.execute();
					statement2.close();
					conn.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}

	}




	public void clearForm() {
		// TODO Auto-generated method stub
		firstName.clear();
		lastName.clear();
		email.clear();
		pNumber.clear();
		address.clear();
		membership.selectToggle(null);
		
	}


	
	@FXML
	public void theMethod(ActionEvent actionEvent){
		System.out.println("Hey this is btn Clicked event");
		
	}
}

