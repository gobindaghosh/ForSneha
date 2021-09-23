
package com.practice.sneha;

public class MainClass {
    public static void main(String[] args){
        //Declare Object of Manipulate Class
        ManipulateData mp = new ManipulateData();
        String tableName = "tblMovies";
        // Step 1: Creating the Database
        mp.createNewDatabase("MoviesDb.db");
        
        // Step 2: Connection Database
        mp.connectDB();
        
        //Step 3: Create table
        mp.createTable(tableName);
        
        //Step 4: Inserting Data into the Table Name, Actor , Actress, Director, YearofRelease
        String data1[] = {"NameABC", "Actor ABC","Actress ABC", "Director ABC", "2020"};
        String data2[] = {"NameXYZ", "Actor XYZ","Actress XYZ", "Director XYZ", "2020"};
        String data3[] = {"NameMNO", "Actor MNO","Actress MNO", "Director MNO", "2021"};
        
        mp.insertData(data1, tableName);
        mp.insertData(data2, tableName);
        mp.insertData(data3, tableName);
        
        //Step 5: Selecting All Data from the Table
        mp.selectAllData(tableName);
        
        //Step 6: Selecting Data by field names and values
        mp.selectDataWithParam(tableName, "Name", "YearofRelease", "NameABC", "2020");
        
    }
}
