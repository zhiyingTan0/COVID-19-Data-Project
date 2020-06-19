package com.library;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.InputMismatchException;
import java.util.Scanner;

// 1. check if enter non-digit into
// 2. check if contain one more id

public class PatientObservations {

  //private static Statement stmt;
  public static void main(String args[]) throws SQLException {
    // Register the driver
	 //String regex = "[0-9]+";
	 String input = args[0];
	 //int hid = Integer.parseInt(input);
	 //input=String.valueOf(hid);
	 String sql = "SELECT COUNT(hid) as count FROM professional WHERE hid ="+input;
	 // check if sucessfully register the driver
    try {
      DriverManager.registerDriver(new org.postgresql.Driver());
    } catch (Exception cnfe) {
      System.out.println("Class not found");
    }
   

    String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
    String username = "ztan10";
    String password = "dKZ9rn6I";


    Connection con = DriverManager.getConnection(url, username, password);

    Statement stmt =
        con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    //stmt=statement;
  
    // check if the entered hid is valid
    /* Find the */
    try {
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()) {
        	 int count = rs.getInt("count");
             if(count==0) {
             	System.out.println("Invalid hid value!");
             	return;
             } 
        }
       
        
    }catch(SQLException e) {
        System.err.println("msg: "+ e.getMessage() + 
                "code: " + e.getErrorCode() +
                "state: " + e.getSQLState());
    }
    System.out.println("Welcome to the Patient Observation Console !");
    outer: while (true) {
      try {
    	System.out.println("");
        System.out.println(
            "Please select one of the following choices");
        System.out.println("1. enter a patient's obeservation");
        System.out.println("2. quit the program");
        System.out.print("> ");

        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextLine()) {
          case "1":
            String s =enterObservation(input,stmt);
            System.out.println(s);
            break;
         
          case "2":
            break outer;
          default:
            System.out.println("This is an invalid selection. Please try again");
            break;
        }

      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
    stmt.close();
    con.close();

  }
  
  private static String enterObservation(String hid, Statement stmt) {
	  //String regex = "[0-9]+";
	  //Scanner scanner = new Scanner(System.in);
      System.out.println("What is the patient id? (Please enter a number)");
      Scanner scanner = new Scanner(System.in);
      String pid = scanner.nextLine();
      // check if it is a valid patient id
      String sql = "select count(pid) from patient where pid="+pid;
      try {
          ResultSet rs = stmt.executeQuery(sql);
          //String[] Header = {"NAME", "ISBN", "Stock Status", "Price", "Author", "Publisher"};
          //System.out.println("------------------ Matching Result ------------------  ");
         // System.out.format("%25s%25s%25s%25s%25s%25s\n", (Object[]) Header);
          while(rs.next()) {
         	 int count = rs.getInt("count");
              if(count==0) {
              	String s ="Invalid patient id value!\n";
              	return s;
              } 
         }
        
          
          
      }catch(SQLException e) {
          System.err.println("msg: "+ e.getMessage() + 
                  "code: " + e.getErrorCode() +
                  "state: " + e.getSQLState());
      }
      
      //Scanner scanner1 = new Scanner(System.in);
      // wh
      System.out.println("What is the date of visiting? eg.(2020-01-01)  (Dont't leave it blank)");
      String vday= scanner.nextLine();
      System.out.println("What is the hour of visiting? (Dont't leave it blank)" );
      String hour= scanner.nextLine();
      System.out.println("What is the text of observation? (Dont't leave it blank)");
      String text= scanner.nextLine();
     
     // String sql1= "insert into visit (pid,hid,hour,vday,text) values ("+pid+","+hid+","+hour+",'"+vday+"', '"+text+"');";
      //choose the vid number
      String sql2 = "select max(vid) as m from visit";
      String vid = "";
  
      try {
    	  ResultSet rs = stmt.executeQuery(sql2); 
    	  while(rs.next()) {
          	 vid = String.valueOf(rs.getInt("m")+1);
              
          }
    	  
    	  
      }catch(SQLException e){
    	  System.err.println("msg: "+ e.getMessage() + 
                  "code: " + e.getErrorCode() +
                  "state: " + e.getSQLState());
      }
      
      String sql1= "insert into visit (pid,hid,hour,vday,text,vid) values ("+pid+","+hid+","+hour+",'"+vday+"', '"+text+"',"+vid +");"; 
      try {
    	  stmt.executeUpdate(sql1);
          //String[] Header = {"NAME", "ISBN", "Stock Status", "Price", "Author", "Publisher"};
          //System.out.println("------------------ Matching Result ------------------  ");
         return "Complete Insert\n";
          
          
          
      }catch(SQLException e) {
          System.err.println("msg: "+ e.getMessage() + 
                  "code: " + e.getErrorCode() +
                  "state: " + e.getSQLState());
      }
      return "";
      
  } 
  

}
