/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.theproject3.MainInterfaces;

import com.mycompany.theproject3.ConnectionTo;
import com.mycompany.theproject3.ExceptionHandler;
import com.mycompany.theproject3.Number;
import com.mycompany.theproject3.GetVatAndCurrencyCode;
import com.mycompany.theproject3.MainInterfaces.Tables.Information;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;


public class IncomeStatistics {
    
    private ConnectionTo CT = new ConnectionTo();
    
    @FXML
    private TableView<Information> InformationTable;

    @FXML
    private TableColumn  Property;

    @FXML
    private TableColumn  Value;

    @FXML
    private DatePicker From;
    
     @FXML
    private DatePicker To;
     
     
     

    @FXML
    void Get(ActionEvent event) {
        
    if(!From.getValue().toString().isEmpty() && !From.getValue().toString().isEmpty()){

        try {
            
            
          
        int FromYear = From.getValue().getYear();
        int FromMonth = From.getValue().getMonthValue();
        int FromDay = From.getValue().getDayOfMonth();
        
        int ToYear = To.getValue().getYear();
        int ToMonth = To.getValue().getMonthValue();
        int ToDay = To.getValue().getDayOfMonth();
        
        
       
        
       
        
         
        int NumberOfBills = 0;
        double TotalIncomeExclVat = 0;
        double TotalIncomeInclVat = 0;
        double VatAmount = 0;
        int VatOfBill = 0;
        double TotalOfBill = 0;
        double AverageDailyIncomeInclVat = 0;
        int NumberOfSoldUnits = 0;
       
          ResultSet GetBillsAtGivinDate = CT.NewSelectQuery("Select * from Bill where DateTime Between '"+FromYear+"-"+FromMonth+"-"+FromDay+" 00:00:00' AND '"+ToYear+"-"+ToMonth+"-"+ToDay+" 23:59:59'");
          
               while(GetBillsAtGivinDate.next()){
                   
                  VatOfBill = GetBillsAtGivinDate.getInt("Vat");
                   
                  ResultSet GetProductsOfBill = CT.NewSelectQuery("Select * from Bill_Product where Bill_No ="+GetBillsAtGivinDate.getString("Bill_No"));
   
                  while(GetProductsOfBill.next()){
                      
                      TotalOfBill = GetProductsOfBill.getDouble("Price")*GetProductsOfBill.getInt("Q")+TotalOfBill;
                    NumberOfSoldUnits = NumberOfSoldUnits +GetProductsOfBill.getInt("Q");
                  }
                  
                 
                    VatAmount = Double.parseDouble(Number.SetNumberOfDigits(VatAmount + (TotalOfBill/100)*(VatOfBill)));
                    TotalIncomeExclVat = Double.parseDouble(Number.SetNumberOfDigits(TotalIncomeExclVat + TotalOfBill));
                    TotalOfBill = 0;
                    NumberOfBills++;
                    
               }
               
               
               TotalIncomeInclVat = Double.parseDouble(Number.SetNumberOfDigits(VatAmount + TotalIncomeExclVat));
               AverageDailyIncomeInclVat = Double.parseDouble(Number.SetNumberOfDigits(TotalIncomeInclVat/daysBetween(Date.valueOf(From.getValue()),Date.valueOf(To.getValue()))));
               
              
               
                    Property.setCellValueFactory(new PropertyValueFactory<Information , String>("Property"));
                     Value.setCellValueFactory(new PropertyValueFactory<Information , String>("Value"));
               
                ObservableList<Information>  Table =  FXCollections.observableArrayList();
               
          
               
               Table.add(new Information( "Number Of Bill", String.valueOf(NumberOfBills)));
               Table.add(new Information( "Total Income Excl.Vat ("+GetVatAndCurrencyCode.GetCurrencyCode()+")", String.valueOf(TotalIncomeExclVat)));
               Table.add(new Information( "Total Income Incl.Vat ("+GetVatAndCurrencyCode.GetCurrencyCode()+")", String.valueOf(TotalIncomeInclVat)));
               Table.add(new Information( "Average Daily Income Incl.Vat ("+GetVatAndCurrencyCode.GetCurrencyCode()+")", String.valueOf(AverageDailyIncomeInclVat)));
               Table.add(new Information( "Vat Amount ("+GetVatAndCurrencyCode.GetCurrencyCode()+")", String.valueOf(VatAmount)));
               Table.add(new Information( "Number Of Sold Units", String.valueOf(NumberOfSoldUnits)));
           
               
               InformationTable.setItems(Table);
               
               
             
               
               
               
               
               
               
               
               
      } catch (SQLException ex) {
          ExceptionHandler AAA = new ExceptionHandler(ex);
      }
        
   }
    
    }
    
      private int daysBetween(Date d1, Date d2){
             return Math.abs((int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24)));
     }
    
        public void Show(){
 
         try {
             
        Scene scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("/Admin/IncomeStatistics.fxml")));
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
