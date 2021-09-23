
package com.practice.sneha;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ManipulateData {
    
    Connection con = null;
    String databaseName;
    String tableName; 
    //Create Database
    public void createNewDatabase(String dbName) {
        
        String url = "jdbc:sqlite:C:/Users/Gobinda/OneDrive/Documents/NetBeansProjects/Sneha/" + dbName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
               databaseName = dbName;
               System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    //Connection to the Database
    public Connection connectDB()
    {
        try{
            Class.forName("org.sqlite.JDBC"); //Register SQLite Driver
            Connection con = DriverManager.getConnection("jdbc:sqlite:"+ databaseName);
            System.out.println("Connection to SQLite has been established.");
            return con;
        }
        catch(SQLException e){
            System.out.println("Unable to select Database.. " + e);
            //JOptionPane.showMessageDialog(null,"Unable to connect the Database... " + e);
            return null;
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(ManipulateData.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
   
    //Creating Table     
    public void createTable(String tableName){
        //Connect to the Database
        con = connectDB();
        try{
                String sql = "CREATE TABLE "+ tableName +"(" +
                    "    Name   text  PRIMARY KEY," +
                    "    Actor text,  " +
                    "    Actress text, " +
                    "    Director text, " +
                    "    YearofRelease integer " +
                    ")";
                
                PreparedStatement pst=con.prepareStatement(sql);
                pst.executeUpdate();
                System.out.println("Table " + tableName + " created successfully!");
                con.close();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    //Inserting Data
    public void insertData(String[] arrList, String tableName ){
        
        //Connect to the Database
        con = connectDB();
       
        try {
            String sql="insert into "+ tableName +" (Name, Actor , Actress, Director, YearofRelease) values "
                     + "(?,?,?,?,?);";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1,arrList[0]);
            pst.setString(2,arrList[1]);
            pst.setString(3,arrList[2]);
            pst.setString(4,arrList[3]);
            pst.setInt(5,Integer.parseInt(arrList[4]));
            System.out.println("Prepared Statement bindings : " + pst.toString());
            pst.executeUpdate();
            con.close();
            System.out.println("Data inserted Successfully");
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
        
    }
        
    //Select All Data
    public void selectAllData(String tableName){
            //Connect to the Database
            con = connectDB();
            try {
                String sql="select * from " + tableName;
                Statement stmt= con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                int count = 1;
                
                while(rs.next()){  
                     System.out.println("Record No: " + count);
                     System.out.println("=============================");
                     System.out.println("Name: " + rs.getString(1));
                     System.out.println("Actor: " + rs.getString(2));
                     System.out.println("Actress: " + rs.getString(3));
                     System.out.println("Director: " + rs.getString(4));
                     System.out.println("YearofRelease: " + rs.getInt(5));
                     count++;
                } 
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
    }
    
    public void selectDataWithParam(String tableName, String colName1, String colName2, String value1, String value2){
        //Connect to the Database
            con = connectDB();
            try {
                String sql="select * from "+ tableName+" where "+ colName1 + "= ? and " + colName2 + "= ?";
                PreparedStatement pst = con.prepareStatement(sql);
                           
                pst.setString(1, value1); // 1 - specifies the first parameter
                pst.setString(2, value2);
                ResultSet rs = pst.executeQuery();
                
                int count = 1;
                while(rs.next()){  
                     System.out.println("Record No: " + count);
                     System.out.println("=============================");
                     System.out.println("Name: " + rs.getString(1));
                     System.out.println("Actor: " + rs.getString(2));
                     System.out.println("Actress: " + rs.getString(3));
                     System.out.println("Director: " + rs.getString(4));
                     System.out.println("YearofRelease: " + rs.getInt(5));
                     count++;
                } 
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
    }
     
}

