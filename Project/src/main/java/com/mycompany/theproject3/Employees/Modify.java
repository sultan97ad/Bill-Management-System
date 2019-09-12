/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.theproject3.Employees;

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
   
   private static int User_ID;
   
   private static ActionEvent Employees;
 
     @FXML
    private TextField NameField;

    @FXML
    private TextField PasswordField;
    
    
    
    
     public void initialize() {
         
        
      GetInformation(User_ID);
         
     }
     
     
      private void GetInformation(int User_ID) {
      
        try {
          
          
           String User_Name = null;
           String Password = null;
           
           
          ResultSet GetInformation = CT.NewSelectQuery("Select * from user Where User_ID = "+User_ID);
          
          while(GetInformation.next()){
              
            User_Name = GetInformation.getString("User_Name");
              Password = GetInformation.getString("Password");
              
              
              
          }
          
    
            NameField.setText(User_Name);
          PasswordField.setText(Password);
          
          
          
          
          
             } catch (SQLException ex) {
          
            ExceptionHandler AAA = new ExceptionHandler(ex);
            
      }
        
        
      }
      
      
      public void SetUserID(int User_ID){
          
        this.User_ID = User_ID;
          
      }
     
       public void SetEvent(ActionEvent Employees){
        
       this.Employees = Employees;
        
    }
  
    
    
    

    @FXML
    void Close(ActionEvent event) {
        
        CurrentStageOperations.Close(event);
        
    }

    @FXML
    void Modify(ActionEvent event) {

         try {
            
          String Name =  NameField.getText();
          String Password =  PasswordField.getText();
         
             
      int Modfiy = CT.NewInsertQuery("update User set User_Name = '"+Name+"' ,  Password = '"+Password+"' where User_Id = "+User_ID);
            
             CurrentStageOperations.Close(event);
             CurrentStageOperations.Close(Employees);
              new Employees().Show();
                
 
            } catch (SQLException ex) {
            ExceptionHandler AAA = new ExceptionHandler(ex);
          }

    }
    
    
     public void Show(){
 
         try {
             
        Scene scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("/fxml/Employees/Employees_Modify.fxml")));
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
