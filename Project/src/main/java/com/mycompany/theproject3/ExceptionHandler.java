/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.theproject3;

import java.sql.SQLException;
import javax.swing.JOptionPane;


public class ExceptionHandler {
    
    
  public   ExceptionHandler(Exception ex){
  
    JOptionPane.showMessageDialog(null, ex);
  
  }
  
  
  

    public ExceptionHandler(SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
    
    
    
    
    
}
