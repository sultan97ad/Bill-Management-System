/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.theproject3;

import java.text.DecimalFormat;

/**
 *
 * @author pc
 */
public class Number {
    
  public static boolean isNumeric(String Text){  
  
   try { 
    
     double Number = Double.parseDouble(Text);  
    
  }catch(NumberFormatException Ex) { 
    
    return false;  
    
   }
  
  return true; 
  
   }
  
  public static String SetNumberOfDigits(double N){
     
      DecimalFormat dec = new DecimalFormat("#0.00");
      
       return dec.format(N);
  }
          
  
}
