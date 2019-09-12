/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.theproject3.Managers;

import com.mycompany.theproject3.Employees.*;
import com.mycompany.theproject3.ConnectionTo;
import com.mycompany.theproject3.CurrentStageOperations;
import com.mycompany.theproject3.ExceptionHandler;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Add {
    
    private ConnectionTo CT = new ConnectionTo();
    private static ActionEvent Managers;
    
   
    
    @FXML
    private TextField NameField;

    @FXML
    private PasswordField PasswordField;
    
     @FXML
    private CheckBox Employees;

    @FXML
    private CheckBox Products;

    @FXML
    private CheckBox CurrencyAndTax;

    @FXML
    private CheckBox IncomeStatistics;
    
    
    

    @FXML
    void Add(ActionEvent event) {

           try{
                
        String ActualName = NameField.getText();
        String Password = PasswordField.getText();
        
       
        if(!ActualName.isEmpty()){
            
                int AddNewManager = CT.NewInsertQuery("Insert into user (User_Name , Password , Manager , Employees , Products , CurrencyAndTax  , IncomeStatistics  ,availability) values ('"+ActualName+"' ,'"+Password+"' , true , "+String.valueOf(Employees.isSelected())+" ,"+String.valueOf(Products.isSelected())+" , "+String.valueOf(CurrencyAndTax.isSelected())+" , "+String.valueOf(IncomeStatistics.isSelected())+" , true )");
                
                CurrentStageOperations.Close(event);
                  CurrentStageOperations.Close(Managers);
                  new Managers().Show();
           
                
           }
        
        
                
        
        
        
        
        
             } catch (SQLException ex) {
               ExceptionHandler AAA = new ExceptionHandler(ex);
            }  
        
        
    }

    @FXML
    void Close(ActionEvent event) {
        
     CurrentStageOperations.Close(event);
     
    }
    
    
    public void SetEvent(ActionEvent Managers){
        
       this.Managers = Managers;
        
    }
    
    
    

    public void Show(){
 
         try {
             
        Scene scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("/fxml/Managers/Managers_Add.fxml")));
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
