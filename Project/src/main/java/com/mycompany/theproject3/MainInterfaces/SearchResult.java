/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.theproject3.MainInterfaces;

import com.mycompany.theproject3.ConnectionTo;
import com.mycompany.theproject3.CurrentStageOperations;
import com.mycompany.theproject3.ExceptionHandler;
import com.mycompany.theproject3.MainInterfaces.Tables.BillItem;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class SearchResult {
    
   private ConnectionTo CT = new ConnectionTo();
    
      @FXML
    private AnchorPane Layout;
      
     @FXML
    private TableView<com.mycompany.theproject3.MainInterfaces.Tables.SearchResult> Result;

    @FXML
    private TableColumn  Bill_No;

    @FXML
    private TableColumn  User_ID;

    @FXML
    private TableColumn  DateTime;

    
    
   private  boolean IsThereResult;
 
   private static  int  Year = 0;
   private static  int  Month = 0;
   private  static int  Day = 0;
   
   
     public void initialize() {
     
     SearchForBillsAt(Year , Month ,Day);
     
     
     }
   

  

    @FXML
    void View(ActionEvent event) {

   
            int BillNo = Result.getSelectionModel().getSelectedItem().getBill_No();
            
            BillView Bill = new BillView();
            Bill.setBillNo(BillNo);
            Bill.Show();
            

    }

      @FXML
    void Close(ActionEvent event) {
        
        CurrentStageOperations.Close(event);
   
    }
     @FXML
    
     
     
     public void setDate(int Year , int Month , int Day){
     
       this.Year = Year; 
       this.Month = Month; 
       this.Day = Day; 
         
     }
    
    
    
    
    public void SearchForBillsAt(int Year , int Month , int Day  ){
        

          try { 
              
              Bill_No.setCellValueFactory(new PropertyValueFactory<com.mycompany.theproject3.MainInterfaces.Tables.SearchResult, Integer>("Bill_No"));
             User_ID.setCellValueFactory(new PropertyValueFactory<com.mycompany.theproject3.MainInterfaces.Tables.SearchResult, Integer>("User_ID"));
             DateTime.setCellValueFactory(new PropertyValueFactory<com.mycompany.theproject3.MainInterfaces.Tables.SearchResult, String>("DateTime"));
              
            ObservableList<com.mycompany.theproject3.MainInterfaces.Tables.SearchResult>  Table =  FXCollections.observableArrayList();
            
           
             
             
          
            
          
         ResultSet Search = CT.NewSelectQuery("Select Employee_Bill.User_ID , Employee_Bill.Bill_No ,Bill.DateTime  from Employee_Bill inner join User on Employee_Bill.USer_ID = User.USer_ID inner join Bill on Bill.Bill_No = Employee_Bill.Bill_No where DateTime Between  '"+Year+"-"+Month+"-"+Day+" 00:00:00' AND '"+Year+"-"+Month+"-"+Day+" 23:59:59'");

             
            
              while(Search.next()){
                  
                  Table.add(new com.mycompany.theproject3.MainInterfaces.Tables.SearchResult(Search.getInt("Bill_No"),Search.getInt("User_ID") ,Search.getString("DateTime")));
     
              }

              
         Result.setItems(Table);
         
   
         
         
         
         
         
                  
            
       
        } catch (SQLException ex) {
       ExceptionHandler AAA = new ExceptionHandler(ex);
      }
          
   }
    
    

    
        public void Show(){
 
         try {
             
        Scene scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("/fxml/SearchResult.fxml")));
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
