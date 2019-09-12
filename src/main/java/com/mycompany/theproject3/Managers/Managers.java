/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.theproject3.Managers;

import com.mycompany.theproject3.Managers.*;
import com.mycompany.theproject3.ConnectionTo;
import com.mycompany.theproject3.CurrentStageOperations;
import com.mycompany.theproject3.ExceptionHandler;
import com.mycompany.theproject3.MainInterfaces.Tables.Employee;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;


public class Managers {
    
    
    private ConnectionTo CT = new ConnectionTo();
    
    private   Stage ThisStage = new Stage();
    
   
    @FXML
    private TableView<Employee> ManagersTable;

    @FXML
    private TableColumn  ActualName;

    @FXML
    private TableColumn  User_ID;
    
    
    
     public void initialize() {
     
     
     GetManagers();
     
     

     }
     
     
     
     private void GetManagers(){
         
         
           try {
              
         User_ID.setCellValueFactory(new PropertyValueFactory<Employee , Integer>("User_ID"));
         ActualName.setCellValueFactory(new PropertyValueFactory<Employee , String>("ActualName"));
         
         ObservableList<Employee>  Table =  FXCollections.observableArrayList();
         
         
     ResultSet re = CT.NewSelectQuery("SELECT * FROM user where manager = true and Availability = true ");
           
       
            while(re.next()){
                
           
                 Table.add(new Employee(re.getInt("User_ID") , re.getString("User_Name")));
           
                
           }
            
            
       ManagersTable.setItems(Table);
       
        
          } catch (SQLException ex) {
                ExceptionHandler AAA = new ExceptionHandler(ex);
          }
         
         
         
         
     }
     
     


    @FXML
    void Add(ActionEvent event) {
        
    Add Frame = new Add();
    Frame.SetEvent(event);
    Frame.Show();
    
    }

    @FXML
    void Modify(ActionEvent event) {
        
    if(!ManagersTable.getSelectionModel().isEmpty()){
      
      Employee SelectedManager = ManagersTable.getSelectionModel().getSelectedItem();
      Modify ModifyManager = new Modify();
      ModifyManager.SetUserID(SelectedManager.getUser_ID());
      ModifyManager.SetEvent(event);
      ModifyManager.Show();
         
  }
    
    }

    @FXML
    void Remove(ActionEvent event) {
        
  if(!ManagersTable.getSelectionModel().isEmpty()){
          
      int Message =  JOptionPane.showConfirmDialog(null, "Are you sure for removing this User", "confirm message", JOptionPane.YES_NO_OPTION);
        if(Message==JOptionPane.YES_OPTION){
           
          try {
             
               Employee SelectedManager = ManagersTable.getSelectionModel().getSelectedItem();
                
            
                   int DeleteManagerRecord = CT.NewInsertQuery("delete from user  where User_ID ="+SelectedManager.getUser_ID());
                   
                   CurrentStageOperations.Close(event);
                    new Managers().Show();
              
              
              
          } catch (SQLException ex) {
              ExceptionHandler AAA = new ExceptionHandler(ex);
          }
            
            
        }
        
       
        
      }
    } 
    
    
   
    
    
     public void Show(){
 
         try {
             
        Scene scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("/fxml/Managers/Managers.fxml")));
        scene.getStylesheets().add("/styles/Styles.css");
        
      
        ThisStage.initStyle(StageStyle.UTILITY);
        ThisStage.setScene(scene);
        ThisStage.setResizable(false);
        ThisStage.show();
        
         
         } catch (IOException ex) {
           ExceptionHandler AAA = new  ExceptionHandler(ex);
         }
    }
}
