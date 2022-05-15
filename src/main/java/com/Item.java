package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; 

public class Item 
{ //A common method to connect to the DB
		private Connection connect(){ 
			
						Connection con = null; 
						
						try{ 
								Class.forName("com.mysql.cj.jdbc.Driver"); 
 
								//Provide the correct details: DBServer/DBName, username, password 
								con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/world", "root", "20001212@abc"); 
						} 
						catch (Exception e) {
							e.printStackTrace();
							} 
						
						return con; 
			} 
		
		
		public String insertItem(String AccountNo, String Month, String UnitConsumed, String UnitPrice, String TotalAmount){ 
			
					String output = ""; 
					
					try
					{ 
						Connection con = connect(); 
						
						if (con == null) 
						{
							return "Error while connecting to the database for inserting."; 
							
						} 
						// create a prepared statement
						
						String query = " insert into items (`BillId`,`Accountno`,`Month`,`UnitConsumed`,`UnitPrice`, `TotalAmount` )"+" values (?, ?, ?, ?, ?, ?)"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						 preparedStmt.setInt(1, 0); 
						 preparedStmt.setString(2, AccountNo); 
						 preparedStmt.setString(3, Month); 
						 preparedStmt.setString(4, UnitConsumed); 
						 preparedStmt.setString(5, UnitPrice);
						 preparedStmt.setString(6, TotalAmount);  
						// execute the statement
 
						preparedStmt.execute(); 
						con.close(); 
						
						String newItems = readItems(); 
						output = "{\"status\":\"success\",\"data\":\""+newItems+"\"}"; 
					} 
					
					catch (Exception e) 
					{ 
						output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}"; 
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
		
		
		
		public String readItems() 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for reading."; 
		 } 
		 // Prepare the html table to be displayed
		 /*output = "<table border=\"1\" class=\"table\"><tr><th>Account No</th>"
		 		+ "<th>Month</th><th>Unit Consumed</th>"
		 		+ "<th>Unit Price</th>"
		 		+ "<th>Total Amount</th>"
		 		+ "<th>Update</th>"
		 		+ "<th>Remove</th></tr>"; */
		
		 String query = "select * from billing"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 
		 output = "<table border='1'><tr><th>AccountNo</th>" +"<th>Month</th>"
				    + "<th>UnitConsumed</th>"
		 		+ "<th>UnitPrice</th>" 
				    + "<th>TotalAmount</th>"
		 		+ "<th>Update</th><th>Remove</th></tr>"; 
		 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
		 String BillId = Integer.toString(rs.getInt("BillId")); 
		 String AccountNo = rs.getString("AccountNo"); 
		 String Month = rs.getString("Month"); 
		 String UnitConsumed = rs.getString("UnitConsumed"); 
		 String UnitPrice = rs.getString("UnitPrice"); 
		 String TotalAmount = rs.getString("TotalAmount");
		 // Add into the html table
		 output +=  "<tr><td>" + AccountNo + "</td>";
		 output += "<td>" + Month + "</td>"; 
		 output += "<td>" + UnitConsumed + "</td>"; 
		 output += "<td>" + UnitPrice + "</td>"; 
		 output += "<td>" + TotalAmount + "</td>";  
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' "
				 + "class='btnUpdate btn btn-secondary' data-billid='" + BillId + "'></td>"
				 + "<td><input name='btnRemove' type='button' value='Remove' "
				 + "class='btnRemove btn btn-danger' data-billid='" + BillId + "'></td></tr>"; 
		 
		 } 
		 con.close(); 
		 // Complete the html table
		 output += "</table>"; 
		 } 
		 
		catch (Exception e) 
		 { 
		 output = "Error while reading the items."; 
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
		}

			
			
			public String updateItem(String BillId, String AccountNo, String Month, String UnitConsumed, String UnitPrice, String TotalAmount){ 
				
					String output = ""; 
					
					try{ 
							Connection con = connect(); 
							if (con == null){
								return "Error while connecting to the database for updating.";
								} 
							// create a prepared statement
							String query = "UPDATE billing SET AccountNo=?,Month=?,UnitConsumed=?,UnitPrice=?,TotalAmount=? WHERE BillId=?"; 
							PreparedStatement preparedStmt = con.prepareStatement(query); 
							// binding values
							preparedStmt.setString(1, AccountNo);
							preparedStmt.setString(2, Month);
							preparedStmt.setString(3, UnitConsumed); 
							preparedStmt.setString(4, UnitPrice); 
							preparedStmt.setString(5, TotalAmount); 
							preparedStmt.setInt(6, Integer.parseInt(BillId)); 
							// execute the statement
							preparedStmt.execute(); 
							con.close(); 
							String newItems = readItems(); 
							output = "{\"status\":\"success\",\"data\":\""+newItems+"\"}"; 

					} 
					
					catch (Exception e){ 
						
						output = "{\"status\":\"error\",\"data\":\"Error while updating the item.\"}"; 

						System.err.println(e.getMessage()); 
						
					} 
					
					return output; 
			} 
			
			
			public String deleteItem(String BillId){ 
				
					String output = ""; 
					
					try{ 
						Connection con = connect(); 
						
						if (con == null){
							return "Error while connecting to the database for deleting."; 
							} 
						// create a prepared statement
						String query = "delete from billing where BillId=?"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, Integer.parseInt(BillId)); 
						// execute the statement
						preparedStmt.execute(); 
						con.close(); 
						String newItems = readItems(); 
						 output = "{\"status\":\"success\",\"data\":\""+newItems+"\"}"; 

					} 
					
					catch (Exception e){ 
						output = "{\"status\":\"error\",\"data\":\"Error while deleting the item.\"}";
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
			
			
			
			
} 
