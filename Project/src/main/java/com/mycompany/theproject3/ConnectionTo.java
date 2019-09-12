/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.theproject3;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ConnectionTo {
    
   
           private  String Path = "jdbc:mysql://localhost:3306/theproject";
           private  String User = "root";
           private  String Pass = "1234";
           
           Connection  conn;
           
           public ConnectionTo() {

               try {
                       Class.forName("com.mysql.jdbc.Driver"); 
                   conn  = DriverManager.getConnection(Path,User,Pass);
                   
               } catch (SQLException ex) {
               ExceptionHandler AAA = new ExceptionHandler(ex);
               } catch (ClassNotFoundException ex) {
                  ExceptionHandler AAA = new ExceptionHandler(ex);
               }
                
           }
           
    
    
  
   
   
   
   
  public  ResultSet NewSelectQuery (String Query) throws SQLException {
      
    
     PreparedStatement St =  conn.prepareStatement(Query);
     ResultSet  R =St.executeQuery();
     
     
        return R;
          
      
      
  }
  
  
  public  int NewInsertQuery (String Query) throws SQLException {
      
     
       Statement St =  conn.createStatement();
        int  R =St.executeUpdate(Query);

      
      return R;
  
  
  
  
  }
  
  
  
  
  
  
  
    
    
    
}
