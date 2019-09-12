/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.theproject3;


import java.sql.ResultSet;
import java.sql.SQLException;



public class GetVatAndCurrencyCode {
    
    public static int GetVat(){
      
        
         
         try {
             
        ConnectionTo CT = new ConnectionTo();
         int Vat = 0;
             
             
             
            ResultSet GetVat = CT.NewSelectQuery("Select Vat from Confg ");
  
           while(GetVat.next()){
               
               Vat = GetVat.getInt("Vat");
              
           }
           
           return Vat;
        
        
           
           
           
           
         } catch (SQLException ex) {
             ExceptionHandler AAA = new ExceptionHandler(ex);
        }
     
        
        
           return 0;
      
    }
    
    
    
    
    
    
    
    public static String GetCurrencyCode(){
      
        
         
         try {
             
        ConnectionTo CT = new ConnectionTo();
         String CurrencyCode = null;
             
             
             
            ResultSet GetCurrencyCode = CT.NewSelectQuery("Select Currency_Code from Confg ");
  
           while(GetCurrencyCode.next()){
               
               CurrencyCode = GetCurrencyCode.getString("Currency_Code");
              
           }
           
           return CurrencyCode;
        
        
           
           
           
           
         } catch (SQLException ex) {
             ExceptionHandler AAA = new ExceptionHandler(ex);
        }
         
         
        return null;
        
        
        
      
    }
    
    
    
    
    
    
}
