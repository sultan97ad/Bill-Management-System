/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.theproject3.Products;

import com.mycompany.theproject3.Employees.*;
import com.mycompany.theproject3.ConnectionTo;
import com.mycompany.theproject3.CurrentStageOperations;
import com.mycompany.theproject3.ExceptionHandler;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Modify {
    
   private ConnectionTo CT = new ConnectionTo();
   
   private static int Pro_No;
   
   private static ActionEvent Products;
 
     @FXML
    private TextField NameField;

     @FXML
    private TextField PriceField;
    
    
    
    
     public void initialize() {
         
        
      GetInformation(Pro_No);
         
     }
     
     
      private void GetInformation(int Pro_No) {
      
        try {
          
          
           String  Name = null;
           double Price = 0;
           
           
          ResultSet GetInformation = CT.NewSelectQuery("Select * from Product Where Pro_No = "+Pro_No);
          
          while(GetInformation.next()){
              
              Name = GetInformation.getString("Pro_Name");
              Price = GetInformation.getDouble("Price");
              
              
              
          }
          
    
            NameField.setText(Name);
            PriceField.setText(String.valueOf(Price));
          
          
          
          
          
             } catch (SQLException ex) {
          
            ExceptionHandler AAA = new ExceptionHandler(ex);
            
      }
        
        
      }
      
      
      public void SetProNo(int Pro_No){
          
        this.Pro_No = Pro_No;
          
      }
     
       public void SetEvent(ActionEvent Products){
        
       this.Products = Products;
        
    }
  
    
    
    

    @FXML
    void Close(ActionEvent event) {
        
        CurrentStageOperations.Close(event);
        
    }

    @FXML
    void Modify(ActionEvent event) {

         try {
            
          String Name =  NameField.getText();
          double Price =  Double.parseDouble(PriceField.getText());
         
             
      int Modfiy = CT.NewInsertQuery("update Product set Pro_Name = '"+Name+"' ,  Price = "+Price+" where Pro_No = "+Pro_No);
            
             CurrentStageOperations.Close(event);
             CurrentStageOperations.Close(Products);
              new Products().Show();
                
 
            } catch (SQLException ex) {
            ExceptionHandler AAA = new ExceptionHandler(ex);
          }

    }
    
    
     public void Show(){
 
         try {
             
        Scene scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("/fxml/Products/Products_Modify.fxml")));
        scene.getStylesheets().add("/styles/Styles.css");
        
        Stage ThisStage = new Stage();
        ThisStage.initStyle(StageStyle.UTILITY);
        ThisStage.setScene(scene);
        ThisStage.setResizable(false);
        ThisStage.show();
        
         
         } catch (IOException ex) {
           ExceptionHandler AAA = new  ExceptionHandler(ex);
         }
    }

   
}
