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
import com.mycompany.theproject3.Managers.Managers;
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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author pc
 */
public class Admin_Interface {


   private ConnectionTo CT = new ConnectionTo();
   
      @FXML
    private Button Employees;

    @FXML
    private Button Products;

    @FXML
    private Button ChangePassword;

    @FXML
    private Button DeleteBills;

    @FXML
    private Button CurrencyAndTax;

    @FXML
    private Button IncomeStatistics;
    
   

   

    
    
       @FXML
    void ChangePassword(ActionEvent event) {
        
     Change_Password Frame = new Change_Password();
            Frame.SetUserID(1);
            Frame.Show();
    }

 

    @FXML
    void DeleteBills(ActionEvent event) {
        
  int Message =  JOptionPane.showConfirmDialog(null, "Are you sure for Deleting All stored Bills", "confirm message", JOptionPane.YES_NO_OPTION);
        if(Message==JOptionPane.YES_OPTION){
        
         try {
             

              
                    ResultSet GetAllBills = CT.NewSelectQuery("select * from  Bill ");
                     while(GetAllBills.next()){
                         
                         int DeleteBill = CT.NewInsertQuery("delete from Bill where Bill_No = "+GetAllBills.getInt("Bill_No")); 
                         
                     }
                 
                     
                     
                     
                     ResultSet GetNotAvailableEmployees = CT.NewSelectQuery("select * from  employee where availability ='OFF'");
                     while(GetNotAvailableEmployees.next()){
                         
                         int DeleteNotAvailableEmployee = CT.NewInsertQuery("delete from employee where User_ID = "+GetNotAvailableEmployees.getInt("User_ID")); 
                         
                     }
                     
                     
                     
                       ResultSet GetNotAvailableProducts = CT.NewSelectQuery("select * from  Product where availability ='OFF'");
                     while(GetNotAvailableProducts.next()){
                         
                         int DeleteNotAvailableProduct = CT.NewInsertQuery("delete from Product where Pro_No = "+GetNotAvailableProducts.getInt("Pro_No")); 
                         
                         
                     }
                    
           JOptionPane.showMessageDialog(null, "Done");
 
         } catch (SQLException ex) {
               ExceptionHandler AAA = new ExceptionHandler(ex);
         }
        
        
        
        
        
        
        }
    }
    
    
       @FXML
    void Managers(ActionEvent event) {
        
     new Managers().Show();
    
    }

    

    
    
    
   



 public void Show(){
 
         try {
             
        Scene scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("/Admin/Admin_Interface.fxml")));
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