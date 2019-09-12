/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.theproject3.Employees;

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


public class Employees {
    
    
    private ConnectionTo CT = new ConnectionTo();
    private   Stage ThisStage = new Stage();
    
   
    @FXML
    private TableView<Employee> EmployeesTable;

    @FXML
    private TableColumn  ActualName;

    @FXML
    private TableColumn  User_ID;
    
    
    
     public void initialize() {
     
     
     GetEmployees();
     
     

     }
     
     
     
     private void GetEmployees(){
         
         
           try {
              
         User_ID.setCellValueFactory(new PropertyValueFactory<Employee , Integer>("User_ID"));
         ActualName.setCellValueFactory(new PropertyValueFactory<Employee , String>("ActualName"));
         
         ObservableList<Employee>  Table =  FXCollections.observableArrayList();
         
         
     ResultSet re = CT.NewSelectQuery("SELECT * FROM User where Availability = true and Employee = true");
           
       
            while(re.next()){
                
           
                 Table.add(new Employee(re.getInt("User_ID") , re.getString("User_Name")));
           
                
           }
            
            
       EmployeesTable.setItems(Table);
       
        
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
        
    if(!EmployeesTable.getSelectionModel().isEmpty()){
      
      Employee SelectedEmployee = EmployeesTable.getSelectionModel().getSelectedItem();
      Modify ModifyEmployee = new Modify();
      ModifyEmployee.SetUserID(SelectedEmployee.getUser_ID());
      ModifyEmployee.SetEvent(event);
      ModifyEmployee.Show();
         
  }
    
    }

    @FXML
    void Remove(ActionEvent event) {
        
  if(!EmployeesTable.getSelectionModel().isEmpty()){
          
      int Message =  JOptionPane.showConfirmDialog(null, "Are you sure for removing this User", "confirm message", JOptionPane.YES_NO_OPTION);
        if(Message==JOptionPane.YES_OPTION){
           
          try {
             
               Employee SelectedEmployee = EmployeesTable.getSelectionModel().getSelectedItem();
                
              ResultSet GetTheEmployeeBills = CT.NewSelectQuery("select * from Employee_Bill  where User_ID ="+SelectedEmployee.getUser_ID());
              int NumberOfEmployeeBills = 0;
              
              while(GetTheEmployeeBills.next()){
                  
                  NumberOfEmployeeBills++;
                  
              }
              
             
              
              if(NumberOfEmployeeBills>0){
                  
                  int SetAvailabilityOff = CT.NewInsertQuery("update User set availability = false where User_ID ="+SelectedEmployee.getUser_ID());
               
                  
                  }else{
                  
                   int DeleteEmployeeRecord = CT.NewInsertQuery("delete from User  where User_ID ="+SelectedEmployee.getUser_ID());
                      
                  }
              
                CurrentStageOperations.Close(event);
                    new Employees().Show();
              
              
              
          } catch (SQLException ex) {
              ExceptionHandler AAA = new ExceptionHandler(ex);
          }
            
            
        }
        
       
        
      }
    } 
    
    
   
    
    
     public void Show(){
 
         try {
             
        Scene scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("/fxml/Employees/Employees.fxml")));
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
