/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.theproject3.MainInterfaces;

import com.mycompany.theproject3.ConnectionTo;
import com.mycompany.theproject3.CurrentStageOperations;
import com.mycompany.theproject3.Employees.Employees;
import com.mycompany.theproject3.ExceptionHandler;
import com.mycompany.theproject3.GetVatAndCurrencyCode;
import com.mycompany.theproject3.MainApp;
import com.mycompany.theproject3.MainInterfaces.Tables.Information;
import com.mycompany.theproject3.MainInterfaces.Tables.BillItem;
import com.mycompany.theproject3.Products.Products;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author pc
 */
public class Manager_Interface {


   private ConnectionTo CT = new ConnectionTo();
   
      @FXML
    private Button Employees;

    @FXML
    private Button Products;

    @FXML
    private Button ChangePassword;
 
    @FXML
    private Text NameLabel;
    

    @FXML
    private Button CurrencyAndTax;

    @FXML
    private Button IncomeStatistics;
    
    
    
    private static int UserID ; 
    private static String UserName;
   
     public void initialize() {
         
     SetPrivileges(UserID);
     NameLabel.setText("Name : "+UserName);
     
          
        
        
    }
     
     
    private void SetPrivileges( int User_ID) {
        
        try {
            
           boolean EmployeesPrivilege = false;
           boolean ProductsPrivilege = false;
           boolean CurrencyAndTaxPrivilege = false;
           boolean IncomeStatisticsPrivilege = false;

         ResultSet  GetInformation = CT.NewSelectQuery("Select * from user Where User_ID = "+User_ID);
         
          while(GetInformation.next()){
              
              EmployeesPrivilege = GetInformation.getBoolean("Employees");
              ProductsPrivilege = GetInformation.getBoolean("Products");
              CurrencyAndTaxPrivilege = GetInformation.getBoolean("CurrencyAndTax");
              IncomeStatisticsPrivilege = GetInformation.getBoolean("IncomeStatistics");
                
          }
          
          Employees.setDisable(!EmployeesPrivilege);
          Products.setDisable(!ProductsPrivilege);
          CurrencyAndTax.setDisable(!CurrencyAndTaxPrivilege);
          IncomeStatistics.setDisable(!IncomeStatisticsPrivilege);
        
            } catch (SQLException ex) {
          ExceptionHandler AAA = new ExceptionHandler(ex);
       }
    }
   
       @FXML
    void Employees(ActionEvent event) {
        
    new Employees().Show();
    
      }
    
    
       @FXML
    void ChangePassword(ActionEvent event) {
        
      Change_Password Frame =  new Change_Password();
          Frame.SetUserID(UserID);
          Frame.Show();
    }

    @FXML
    void CurrencyAndTax(ActionEvent event) {
        
     new CurrencyAndTax().Show();
     
    }
  

    @FXML
    void IncomeStatistics(ActionEvent event) {
        
    new IncomeStatistics().Show();
   
    }

    @FXML
    void Products(ActionEvent event) {
        
      new Products().Show();
      
    }
    
    public void SetUserID(int UserID){
        
        this.UserID = UserID;
        
    }
    
     public void SetUserName(String UserName){
        
        this.UserName = UserName;
        
    }
    
    
    
   



 public void Show(){
 
         try {
             
        Scene scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("/Manager/Manager_Interface.fxml")));
        scene.getStylesheets().add("/styles/Styles.css");
        
        Stage ThisStage = new Stage();
        
         ThisStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

         @Override
         public void handle(WindowEvent event) {
             Platform.runLater(new Runnable() {

                 @Override
                 public void run() {
                 new Login().Show();
                 }
             });
         }
     });
        ThisStage.setScene(scene);
        ThisStage.setResizable(false);
        ThisStage.show();
        
         
         } catch (IOException ex) {
           ExceptionHandler AAA = new  ExceptionHandler(ex);
         }
    }

  
  

}