/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.theproject3.MainInterfaces;

import com.mycompany.theproject3.ConnectionTo;
import com.mycompany.theproject3.ExceptionHandler;
import com.mycompany.theproject3.GetVatAndCurrencyCode;
import com.mycompany.theproject3.Number;
import com.mycompany.theproject3.MainInterfaces.Tables.Information;
import com.mycompany.theproject3.MainInterfaces.Tables.BillItem;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.StageStyle;

/**
 *
 * @author pc
 */
public class BillView {


   private ConnectionTo CT = new ConnectionTo();
  private static int Bill_No = 0;
   

    @FXML
    private TableView<BillItem> Bill;

    @FXML
    private TableColumn  BPro_No;

    @FXML
    private TableColumn  BPro_Name;

    @FXML
    private TableColumn  BPrice;

    @FXML
    private TableColumn  Q;
    
    @FXML
    private TableView<Information> BillInformation;

   
    @FXML
    private TableColumn  Property;

    @FXML
    private TableColumn  Value;
    
    
    
      public void initialize() {
       
        GetBill(Bill_No);
        
    }
      
    private void GetBill(int Bill_No){
        
        try {
        
         BPro_No.setCellValueFactory(new PropertyValueFactory<BillItem , Integer>("Pro_No"));
         BPro_Name.setCellValueFactory(new PropertyValueFactory<BillItem , String>("Pro_Name"));
         BPrice.setCellValueFactory(new PropertyValueFactory<BillItem , Double>("Price"));
         Q.setCellValueFactory(new PropertyValueFactory<BillItem , Integer>("Q"));
              
            ObservableList<BillItem>  BillTable =  FXCollections.observableArrayList();
        
       
          ResultSet GetBill = CT.NewSelectQuery("Select *  from Bill_Product inner join  Product on Product.Pro_No = Bill_Product.Pro_No and Bill_No = "+Bill_No+" inner join Bill on Bill_Product.Bill_No = Bill.Bill_No");
      
               ResultSet GetCreatorUserID = CT.NewSelectQuery("select User_ID from Employee_Bill where Bill_No = "+Bill_No);
            int UserID = 0;
            
            while(GetCreatorUserID.next()){
                
                UserID = GetCreatorUserID.getInt("User_ID");
                
            }
            
            ResultSet GetCreatorActualName = CT.NewSelectQuery("select User_Name from User where User_ID = "+UserID);
            String ActualName = null;
            
            while(GetCreatorActualName.next()){
                
                ActualName = GetCreatorActualName.getString("User_Name");
                
            }
            
            
            
      
            
          
            

            double TotalExclVat = 0;
            String DateTime = null;
            int Vat = 0;
            

           
            while(GetBill.next()){
                
               BillTable.add(new BillItem(GetBill.getInt("Pro_No"),GetBill.getString("Pro_Name"), GetBill.getDouble("Price") ,GetBill.getInt("Q")));
               
               TotalExclVat = GetBill.getDouble("Price")*GetBill.getInt("Q")+TotalExclVat;
               DateTime = GetBill.getString("DateTime");
               Vat = GetBill.getInt("Vat");
               
               
            }
            
            TotalExclVat = Double.parseDouble(Number.SetNumberOfDigits(TotalExclVat));
            String TotalInclVat = Number.SetNumberOfDigits((TotalExclVat/100)*(100+Vat));
                         
            Bill.setItems(BillTable);
            
            
             Property.setCellValueFactory(new PropertyValueFactory<Information , String>("Property"));
             Value.setCellValueFactory(new PropertyValueFactory<Information , String>("Value"));
         
              
            ObservableList<Information>  InformationTable =  FXCollections.observableArrayList();
            
            InformationTable.add(new Information("Total("+GetVatAndCurrencyCode.GetCurrencyCode()+") Excl.Vat",String.valueOf(TotalExclVat)));
            InformationTable.add(new Information( "Total("+GetVatAndCurrencyCode.GetCurrencyCode()+") Incl.Vat", TotalInclVat ));
            InformationTable.add(new Information( "Vat%", String.valueOf(Vat)));
            InformationTable.add(new Information( "Bill No",String.valueOf(Bill_No)));
            InformationTable.add(new Information( "DateTime",DateTime));
            InformationTable.add(new Information( "Created By",ActualName+"("+UserID+")"));
                    
     BillInformation.setItems(InformationTable);
 
  

      
       } catch (SQLException ex) {
            ExceptionHandler AAA = new ExceptionHandler(ex);
       }
}
    
    
    
public void setBillNo(int Bill_No){
    
    this.Bill_No = Bill_No;
    
    
}



 public void Show(){
 
         try {
             
        Scene scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("/fxml/BillView.fxml")));
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