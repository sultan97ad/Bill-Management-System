/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.theproject3.MainInterfaces;

import com.mycompany.theproject3.ConnectionTo;
import com.mycompany.theproject3.CurrentStageOperations;
import com.mycompany.theproject3.ExceptionHandler;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;


public class Change_Password {

    private ConnectionTo CT = new ConnectionTo();
    
    private static int UserID ;
            


    @FXML
    private PasswordField CurrentPasswordField;

    @FXML
    private PasswordField NewPassword1Field;

    @FXML
    private PasswordField NewPassword2Field;

    @FXML
    void Change(ActionEvent event) {
        
   String EnteredCurrentPassword = CurrentPasswordField.getText();
   String NewPassword1 = NewPassword1Field.getText();
   String NewPassword2 = NewPassword2Field.getText();





if(!EnteredCurrentPassword.isEmpty() && !NewPassword1.isEmpty() && !NewPassword2.isEmpty()){
    
    
    try {
      
        ResultSet GetCurrentPassword = CT.NewSelectQuery("Select Password from User where User_ID = "+UserID);
        
       String CurrentPassword = null;
       
       while(GetCurrentPassword.next()){
           
           CurrentPassword = GetCurrentPassword.getString("Password");
           
           
           
           
       }
       
       
       if(EnteredCurrentPassword.equals(CurrentPassword)){
           
           if(NewPassword1.equals(NewPassword2)){
               
               
               
               
               int SetNewPassword = CT.NewInsertQuery("update user set password = '"+NewPassword1+"' where User_ID = "+UserID);
               
               CurrentStageOperations.Close(event);
             
               
               
               
           }else{
                
               JOptionPane.showMessageDialog(null, "New Password(1) and New Password(2) don't match", "Match Problem", 0);   
               
           }
           
           
           
           
       }else{
           
           
           JOptionPane.showMessageDialog(null, "Corrent Password is incorrect", "Incorrect", 0);
           
           
       }
        
        
    } catch (SQLException ex) {
         ExceptionHandler AAA = new ExceptionHandler(ex);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}


    }

    @FXML
    void Close(ActionEvent event) {
  CurrentStageOperations.Close(event);
    }
    
    
    public void SetUserID(int UserID){
        
        this.UserID = UserID;
        
    }
    
    
    
     public void Show(){
 
         try {
             
        Scene scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("/Admin/Change_Password.fxml")));
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
