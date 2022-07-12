package carsDatabs;

import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;

public class CarsExample {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Connection con=null;
		try
		{
			String url="jdbc:sqlserver://localhost:1433;databaseName=Cars;integratedSecurity=true; ";
			con=DriverManager.getConnection(url);
			if(con!=null)
				System.out.println("Connected");
			
			
			String sql="INSERT INTO Newcars (model,brand,price) VALUES (?,?,?) ";
			PreparedStatement stm=con.prepareStatement(sql);
			stm.setNString(1,"abc");
			stm.setNString(2, "ght");
			stm.setInt(3, 80000);
			int rowsInserted=stm.executeUpdate();
			if(rowsInserted>0)
				System.out.println("Data were inserted successfully");
			
			
			sql="SELECT * FROM Newcars ";
			Statement stmt=con.createStatement();
			ResultSet result=stmt.executeQuery(sql);
			int count=0;
			while(result.next())
			{
				String model=result.getString("model");
				String brand=result.getString("brand");
				int price=result.getInt("price");
				String output = "User #%d: %s - %s - %d";
			    System.out.println(String.format(output, ++count, model,brand,price));
			}
			
			
			sql="UPDATE Newcars SET model=?,brand=?,price=? WHERE model=?";
			stm.setString(1, "Opel");
			stm.setString(2, "Corsa");
			stm.setInt(3, 14550);
			int rowsUpdated=stm.executeUpdate();
			if(rowsUpdated>0)
				System.out.println("Updated");
			
			
			sql="DELETE FROM Newcars WHERE model=?";
			stm=con.prepareStatement(sql);
			stm.setString(1,"abc");
			int rowsDeleted=stm.executeUpdate();
			if(rowsDeleted>0)
				System.out.println("Deleted");
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			try
			{
				if(con!=null && !con.isClosed())
					con.close();
			}catch(SQLException ex) {ex.printStackTrace();}
			
		}
		

	}

}
