package org.melissir;

import java.sql.*;

/**
 * Created by mrhein on 4/7/16.
 */
public class Setup {
    // JDBC driver name and database URL
    static final String DB_URL = "jdbc:mysql://localhost/addressdb";

    //  Database credentials
    static final String USER = "testuser";
    static final String PASS = "Password55!";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            //Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String sql = String.format("drop table if exists %s", "contacts");
            System.out.println(sql);
            stmt.execute(sql);

            System.out.println("Creating table in given database...");


            sql = "CREATE TABLE contacts" +
                    "(id int auto_increment primary key not null, " +
                    " first VARCHAR(255), " +
                    " last VARCHAR(255), " +
                    " street varchar(255), " +
                    " state varchar(255), " +
                    " zip varchar(255), " +
                    "email varchar(255), " +
                    "phone varchar(255))";


            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main
}//end JDBCExample
