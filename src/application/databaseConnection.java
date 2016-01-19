package application;
import java.sql.*;
import javax.swing.*;
	
public class databaseConnection {
	Connection connect=null;
	public static Connection connector(){
		try{
			Connection connect= DriverManager.getConnection("jdbc:mysql://localhost:3306/newark_it_db","root","root");
			JOptionPane.showMessageDialog(null, "Connection Successful");
			return connect;
		}
		catch(Exception exception){
			JOptionPane.showMessageDialog(null, exception);
			return null;
		}
	}
}
