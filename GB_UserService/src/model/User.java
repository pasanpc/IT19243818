package model;

import java.math.BigInteger;
import java.sql.*;

public class User {
	
	//A common method to connect to the DB
	private Connection connect()
	 {
		Connection con = null;
	 try
	 {
		 Class.forName("com.mysql.jdbc.Driver");
	
		 //Provide the correct details: DBServer/DBName, username, password
		 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_project","root", "root");
	 }
	 catch (Exception e)
	 {
		 e.printStackTrace();}
	     return con;
	 }
	
	//==========================================================================
	
	//=============insert Client Method===============
	
	public String insertClient(String userName,String email,String firstName,String lastName,String cardNumber,String CVV,String expDate,String password)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into user(`userId`,`userName`,`email`,`firstName`,`lastName`,`cardNumber`,`CVV`,`expDate`,`password`)"+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			String query2 = " insert into client values (LAST_INSERT_ID())";
			PreparedStatement preparedStmt2 = con.prepareStatement(query2);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, userName);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, firstName);
			preparedStmt.setString(5, lastName); 
			preparedStmt.setLong(6, Long.parseLong(cardNumber));
			preparedStmt.setInt(7, Integer.parseInt(CVV)); 
			preparedStmt.setString(8, expDate); 
			preparedStmt.setString(9, password); 
			

			//execute the statement
			preparedStmt.execute();
			preparedStmt2.execute();
			con.close();
			
			String newUser = readClients(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newUser + "\"}"; 
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the Client.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//=============Read all Clients ===============
	
	public String readClients()
	 {
		 String output = "";
		 try
		 {
			 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for reading."; }
		 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr>"+
		 "<th>Client User Name</th>" +
		 "<th>Email</th>" +
		 "<th>First Name</th><th>Last Name</th>" +
		 "<th>Card Number</th>" +
		 "<th>CVV</th>" +
		 "<th>Expiration Date</th>" +
		 "<th>Password</th>"
		 + "<th>Update</th><th>Remove</th></tr>";
		
		 String query = "select * from user u ,client c where c.clientId=u.userId";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
			 String userId = Integer.toString(rs.getInt("u.userId"));
			 String userName = rs.getString("userName");
			 String email = rs.getString("email");
			 String firstName = rs.getString("firstName");
			 String lastName = rs.getString("lastName");
			 String CardNumber = rs.getString("CardNumber");
			 String CVV = rs.getString("CVV");
			 String expDate = rs.getString("expDate");
			 String password = rs.getString("password");
			 
			 // Add into the html table
			 output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + userId
						+ "'>" + userName + "</td>";
			 output += "<td>" + email + "</td>";
			 output += "<td>" + firstName + "</td>";
			 output += "<td>" + lastName + "</td>";
			 output += "<td>" + CardNumber + "</td>";
			 output += "<td>" + CVV + "</td>";
			 output += "<td>" + expDate + "</td>";
			 output += "<td>" + password + "</td>";


			 
			 // buttons
	            output += "<td><input name='btnUpdate' type='button' value='Update' class=' btnUpdate btn btn-secondary' data-userid='" + userId + "'></td>"
	            		+ "<td><input name = 'btnRemove' type='button' value = 'Remove' "
	            		+ "class = 'btnRemove btn btn-danger' data-userid='" + userId + "'>"
	            		+"</td></tr>";
	            		
	 }
		 con.close();
		 
		 // Complete the html table
		 output += "</table>";
	 }
	 catch (Exception e)
	 {
		 output = "Error while reading Users.";
		 System.err.println(e.getMessage());
	 }
		 return output;
	 }
	
	//=============Updating a Client Method===============

	public String updateUser(String userId,String fname, String lname, String uname, String email,String cnumber,String CVV,String expdate,String password)
	{
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for updating."; }
			
		 	// create a prepared statement
			 String query = "UPDATE user SET userName=?,email=?,firstName=?,lastName=?,cardNumber=?,CVV=?,expDate=?,password=? WHERE userId=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 
			 // binding values
			 preparedStmt.setString(1, uname);
			 preparedStmt.setString(2, email);
		 	 preparedStmt.setString(3, fname);
			 preparedStmt.setString(4, lname);
			 preparedStmt.setLong(5, Long.parseLong(cnumber));
			 preparedStmt.setInt(6, Integer.parseInt(CVV));
			 preparedStmt.setString(7, expdate);
			 preparedStmt.setString(8, password);
			 preparedStmt.setInt(9, Integer.parseInt(userId));
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 
		 		String newUser = readClients(); 
				 output = "{\"status\":\"success\", \"data\": \"" + 
				 newUser + "\"}"; 
		 }
		 catch (Exception e)
		 {
				output = "{\"status\":\"error\", \"data\": \"Error while updating the Client.\"}";
				System.err.println(e.getMessage());
		 }
		 	return output;
		 }
	
	
	//=============Deleting Client Method===============
	
	public String deleteUser(String userId)
	 {
		String output = "";
	 try
	 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for deleting."; }
			 
		 	// create a prepared statement
			 String query = "delete from user where userId=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(userId));
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
				String newUser = readClients(); 
				 output = "{\"status\":\"success\", \"data\": \"" + 
				 newUser + "\"}"; 
		 }
		 catch (Exception e)
		 {
				output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";
				System.err.println(e.getMessage());
		 }
		 	return output;
		 }
	
	/*//=============insert Researcher Method===============
	
		public String insertResearcher(String userName,String email,String firstName,String lastName,String cardNumber,String CVV,String expDate,String password)
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database";
				}

				// create a prepared statement
				String query = " insert into user(`userId`,`userName`,`email`,`firstName`,`lastName`,`cardNumber`,`CVV`,`expDate`,`password`) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);

				String query2 = " insert into researcher values (LAST_INSERT_ID())";
				PreparedStatement preparedStmt2 = con.prepareStatement(query2);
				
				
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, userName);
				preparedStmt.setString(3, email);
				preparedStmt.setString(4, firstName);
				preparedStmt.setString(5, lastName); 
				preparedStmt.setLong(6, Long.parseLong(cardNumber)); 
				preparedStmt.setInt(7, Integer.parseInt(CVV)); 
				preparedStmt.setString(8, expDate); 
				preparedStmt.setString(9, password); 
				

				//execute the statement
				preparedStmt.execute();
				preparedStmt2.execute();
				con.close();
				
				String newUser = readResearchers(); 
				 output = "{\"status\":\"success\", \"data\": \"" + 
				 newUser + "\"}"; 
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\": \"Error while inserting the Researcher.\"}";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		
		//=============insert Admin Method===============
		
		public String insertAdmin(String userName,String email,String firstName,String lastName,String cardNumber,String CVV,String expDate,String password)
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database";
				}

				// create a prepared statement
				String query = " insert into user(`userId`,`userName`,`email`,`firstName`,`lastName`,`cardNumber`,`CVV`,`expDate`,`password`) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);

				String query2 = " insert into admin values (LAST_INSERT_ID())";
				PreparedStatement preparedStmt2 = con.prepareStatement(query2);
				
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, userName);
				preparedStmt.setString(3, email);
				preparedStmt.setString(4, firstName);
				preparedStmt.setString(5, lastName); 
				preparedStmt.setLong(6, Long.parseLong(cardNumber)); 
				preparedStmt.setInt(7, Integer.parseInt(CVV)); 
				preparedStmt.setString(8, expDate); 
				preparedStmt.setString(9, password); 
				

				//execute the statement
				preparedStmt.execute();
				preparedStmt2.execute();
				con.close();
				String newUser = readAdmins(); 
				 output = "{\"status\":\"success\", \"data\": \"" + 
				 newUser + "\"}"; 
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\": \"Error while inserting the Admin.\"}";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		
		*/
	//===========================================================================
	
	//=============Read all Clients ===============
	
	
	
	/*
	
	//=============Read all Researchers ===============
	
		public String readResearchers()
		 {
			 String output = "";
			 try
			 {
				 Connection con = connect();
			 if (con == null)
			 {
				 return "Error while connecting to the database for reading."; }
			 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr>"+
			 "<th>Researcher User Name</th>" +
			 "<th>Email</th>" +
			 "<th>First Name</th><th>Last Name</th>" +
			 "<th>Card Number</th>" +
			 "<th>CVV</th>" +
			 "<th>Expiration Date</th>" +
			 "<th>Password</th></tr>";
			
			 String query = "select * from user u ,researcher c where c.researcherId=u.userId";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
				 String userId = Integer.toString(rs.getInt("userId"));
				 String userName = rs.getString("userName");
				 String email = rs.getString("email");
				 String firstName = rs.getString("firstName");
				 String lastName = rs.getString("lastName");
				 String CardNumber = rs.getString("CardNumber");
				 String CVV = rs.getString("CVV");
				 String expDate = rs.getString("expDate");
				 String password = rs.getString("password");
				 
				 // Add into the html table
				output += "<tr><td><input id='hidItemIDUpdateR' name='hidItemIDUpdateR' type='hidden' value='" + userId
							+ "'>" + userName + "</td>";
				 output += "<td>" + email + "</td>";
				 output += "<td>" + firstName + "</td>";
				 output += "<td>" + lastName + "</td>";
				 output += "<td>" + CardNumber + "</td>";
				 output += "<td>" + CVV + "</td>";
				 output += "<td>" + expDate + "</td>";
				 output += "<td>" + password + "</td>";


				 
				 // buttons
		            output += "<td><input name='btnUpdateR' type='button' value='Update' class=' btnUpdateR btn btn-secondary' data-itemid='" + userId + "'></td>"
		            		+ "<td><input name = 'btnRemoveR' type='button' value = 'Remove' "
		            		+ "class = 'btnRemoveR btn btn-danger' data-itemid='" + userId + "'>"
		            		+"</td></tr>";
		 }
			 con.close();
			 
			 // Complete the html table
			 output += "</table>";
		 }
		 catch (Exception e)
		 {
			 output = "Error while reading Users.";
			 System.err.println(e.getMessage());
		 }
			 return output;
		 }
	
	
		//=============Read all Admins ===============
		
			public String readAdmins()
			 {
				 String output = "";
				 try
				 {
					 Connection con = connect();
				 if (con == null)
				 {
					 return "Error while connecting to the database for reading."; }
				 
				 // Prepare the html table to be displayed
				 output = "<table border='1'><tr>"+
				 "<th>Admin User Name</th>" +
				 "<th>Email</th>" +
				 "<th>First Name</th><th>Last Name</th>" +
				 "<th>Card Number</th>" +
				 "<th>CVV</th>" +
				 "<th>Expiration Date</th>" +
				 "<th>Password</th></tr>";
				
				 String query = "select * from user u ,Admin c where c.adminId=u.userId";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query);
				 
				 // iterate through the rows in the result set
				 while (rs.next())
				 {
					 String userId = Integer.toString(rs.getInt("userId"));
					 String userName = rs.getString("userName");
					 String email = rs.getString("email");
					 String firstName = rs.getString("firstName");
					 String lastName = rs.getString("lastName");
					 String CardNumber = rs.getString("CardNumber");
					 String CVV = rs.getString("CVV");
					 String expDate = rs.getString("expDate");
					 String password = rs.getString("password");
					 
					 // Add into the html table
					output += "<tr><td><input id='hidItemIDUpdateA' name='hidItemIDUpdateA' type='hidden' value='" + userId
								+ "'>" + userName + "</td>";
					 output += "<td>" + email + "</td>";
					 output += "<td>" + firstName + "</td>";
					 output += "<td>" + lastName + "</td>";
					 output += "<td>" + CardNumber + "</td>";
					 output += "<td>" + CVV + "</td>";
					 output += "<td>" + expDate + "</td>";
					 output += "<td>" + password + "</td>";


					 
					 // buttons
			            output += "<td><input name='btnUpdateA' type='button' value='Update' class=' btnUpdateA btn btn-secondary' data-itemid='" + userId + "'></td>"
			            		+ "<td><input name = 'btnRemoveA' type='button' value = 'Remove' "
			            		+ "class = 'btnRemoveA btn btn-danger' data-itemid='" + userId + "'>"
			            		+"</td></tr>";
			 }
				 con.close();
				 
				 // Complete the html table
				 output += "</table>";
			 }
			 catch (Exception e)
			 {
				 output = "Error while reading Users.";
				 System.err.println(e.getMessage());
			 }
				 return output;
			 }	
	
		
	//=========================================================================
			
	//=============Read details of a individual User Method===============
	
	public String readUserDetails(String userName,String password)
	 {
		 String output = "";
		 try
		 {
			 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for reading."; }
		 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr>"+
				 "<th>User Name</th>" +
				 "<th>Email</th>" +
				 "<th>First Name</th><th>Last Name</th>" +
				 "<th>Card Number</th>" +
				 "<th>CVV</th>" +
				 "<th>Expiration Date</th>" +
				 "<th>Password</th></tr>";
		
		 String query = "select * from user where userName = '" + userName +"' AND password = '" + password + "'";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
			 String userId = Integer.toString(rs.getInt("userId"));
			 String username = rs.getString("userName");
			 String email = rs.getString("email");
			 String firstName = rs.getString("firstName");
			 String lastName = rs.getString("lastName");
			 String CardNumber = rs.getString("CardNumber");
			 String CVV = rs.getString("CVV");
			 String expDate = rs.getString("expDate");
			 String passWord = rs.getString("password");
			 
			 // Add into the html table
			 output += "<td>" + userId + "</td>";
			 output += "<td>" + username + "</td>";
			 output += "<td>" + email + "</td>";
			 output += "<td>" + firstName + "</td>";
			 output += "<td>" + lastName + "</td>";
			 output += "<td>" + CardNumber + "</td>";
			 output += "<td>" + CVV + "</td>";
			 output += "<td>" + expDate + "</td>";
			 output += "<td>" + passWord + "</td>";


			 
			 // buttons
			 output +="<input name='userId' type='hidden' value='" + userId
			 + "'>" + "</form></td></tr>";
	 }
		 con.close();
		 
		 // Complete the html table
		 output += "</table>";
	 }
	 catch (Exception e)
	 {
		 output = "Error while reading Users.";
		 System.err.println(e.getMessage());
	 }
		 return output;
	 }
	 
	 */
	
	//===========================================================================
	
	//=============Updating a Client Method===============

	
	/*
	
	//=============Updating a Researcher Method===============

		public String updateResearcher(String userId,String fname, String lname, String uname, String email,String cnumber,String CVV,String expdate,String password)
		{
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {
				 return "Error while connecting to the database for updating."; }
				
			 	// create a prepared statement
				 String query = "UPDATE user SET userName=?,email=?,firstName=?,lastName=?,cardNumber=?,CVV=?,expDate=?,password=? WHERE userId=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 
				 
				 // binding values
				 preparedStmt.setString(1, uname);
				 preparedStmt.setString(2, email);
			 	 preparedStmt.setString(3, fname);
				 preparedStmt.setString(4, lname);
				 preparedStmt.setLong(5, Long.parseLong(cnumber));
				 preparedStmt.setInt(6, Integer.parseInt(CVV));
				 preparedStmt.setString(7, expdate);
				 preparedStmt.setString(8, password);
				 preparedStmt.setInt(9, Integer.parseInt(userId));
				 
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 
			 		String newUser = readResearchers(); 
					 output = "{\"status\":\"success\", \"data\": \"" + 
					 newUser + "\"}"; 
			 }
			 catch (Exception e)
			 {
					output = "{\"status\":\"error\", \"data\": \"Error while updating the researcher.\"}";
					System.err.println(e.getMessage());
			 }
			 	return output;
			 }
		
		//=============Updating a Admin Method===============

		public String updateAdmin(String userId,String fname, String lname, String uname, String email,String cnumber,String CVV,String expdate,String password)
		{
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {
				 return "Error while connecting to the database for updating."; }
				
			 	// create a prepared statement
				 String query = "UPDATE user SET userName=?,email=?,firstName=?,lastName=?,cardNumber=?,CVV=?,expDate=?,password=? WHERE userId=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 
				 
				 // binding values
				 preparedStmt.setString(1, uname);
				 preparedStmt.setString(2, email);
			 	 preparedStmt.setString(3, fname);
				 preparedStmt.setString(4, lname);
				 preparedStmt.setLong(5, Long.parseLong(cnumber));
				 preparedStmt.setInt(6, Integer.parseInt(CVV));
				 preparedStmt.setString(7, expdate);
				 preparedStmt.setString(8, password);
				 preparedStmt.setInt(9, Integer.parseInt(userId));
				 
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 
			 		String newUser = readAdmins(); 
					 output = "{\"status\":\"success\", \"data\": \"" + 
					 newUser + "\"}"; 
			 }
			 catch (Exception e)
			 {
					output = "{\"status\":\"error\", \"data\": \"Error while updating the Admin.\"}";
					System.err.println(e.getMessage());
			 }
			 	return output;
			 }
			*/
	
	
	//=============Deleting Client Method===============
	
	
	/*
	
	//=============Deleting Researcher Method===============
	
	public String deleteResearcher(String userId)
	 {
		String output = "";
	 try
	 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for deleting."; }
			 
		 	// create a prepared statement
			 String query = "delete from user where userId=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(userId));
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
				String newUser = readResearchers(); 
				 output = "{\"status\":\"success\", \"data\": \"" + 
				 newUser + "\"}"; 
		 }
		 catch (Exception e)
		 {
			 output = "Error while deleting the Researcher.";
			 System.err.println(e.getMessage());
		 }
		 	return output;
		 }
	
	//=============Deleting Admin Method===============
	
	public String deleteAdmin(String userId)
	 {
		String output = "";
	 try
	 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for deleting."; }
			 
		 	// create a prepared statement
			 String query = "delete from user where userId=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(userId));
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
				String newUser = readAdmins(); 
				 output = "{\"status\":\"success\", \"data\": \"" + 
				 newUser + "\"}"; 
		 }
		 catch (Exception e)
		 {
			 output = "Error while deleting the Admin.";
			 System.err.println(e.getMessage());
		 }
		 	return output;
		 }
		 */
	
}
