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


public class Add {
    
    private ConnectionTo CT = new ConnectionTo();
    private static ActionEvent Employees;
    
   
    
    @FXML
    private TextField NameField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    void Add(ActionEvent event) {

           try{
                
        String Name = NameField.getText();
        String Password = PasswordField.getText();
        
        
        if(!Name.isEmpty()){
            
                int AddNewEmployee = CT.NewInsertQuery("Insert into User (User_Name , Password , Employee ,  availability) values ('"+Name+"' ,'"+Password+"' , true , true )");
                
                CurrentStageOperations.Close(event);
                  CurrentStageOperations.Close(Employees);
                  new Employees().Show();
           
                
           }
        
        
                
        
        
        
        
        
             } catch (SQLException ex) {
               ExceptionHandler AAA = new ExceptionHandler(ex);
            }  
        
        
    }

    @FXML
    void Close(ActionEvent event) {
        
     CurrentStageOperations.Close(event);
     
    }
    
    
    public void SetEvent(ActionEvent Employees){
        
       this.Employees = Employees;
        
    }
    
    
    

    public void Show(){
 
         try {
             
        Scene scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("/fxml/Employees/Employees_Add.fxml")));
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
