package com.mycompany.theproject3.MainInterfaces;

import com.mycompany.theproject3.MainInterfaces.Tables.BillItem;
import com.mycompany.theproject3.ConnectionTo;
import com.mycompany.theproject3.CurrentStageOperations;
import com.mycompany.theproject3.ExceptionHandler;
import com.mycompany.theproject3.GetVatAndCurrencyCode;
import com.mycompany.theproject3.Number;
import com.mycompany.theproject3.MainInterfaces.Tables.Product;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;




public class Employee_Interface {
    
     private  ConnectionTo CT = new ConnectionTo();
  
    
    
    
    @FXML
    private  TableView<BillItem> Bill;
       
    @FXML
    private TableColumn BPro_No;

    @FXML
    private TableColumn BPro_Name;

    @FXML
    private TableColumn BPrice;

    @FXML
    private TableColumn  Q;
    
    
    
    
     @FXML
    private  TableView<Product> ProductList;
       
    @FXML
    private TableColumn LPro_No;

    @FXML
    private TableColumn LPro_Name;

    @FXML
    private TableColumn LPrice;
    
    @FXML
    private DatePicker Date;
    
    @FXML
    private Text TotalLabel;
  
     @FXML
    private Text NameLabel;

  
    
    
     
       
    
    private static  String UserID;
    private static String Name;
    
  

     public void initialize() {
       
    
             
        GetProducts();
         PrepareBill();
         TotalLabel.setText("Total("+GetVatAndCurrencyCode.GetCurrencyCode()+") : 0.0");
         NameLabel.setText("Name : "+Name);
      
         
      
         
        
    }
     
     
    @FXML
    void GenerateBill(ActionEvent event) {
        
        ObservableList<BillItem>  Table =  Bill.getItems();
        
 if(Table.size()!=0){
     
        try {
            
            int Vat = GetVatAndCurrencyCode.GetVat();
            
            int CreateBill = CT.NewInsertQuery("insert into  Bill (DateTime , Vat) values(NOW() , "+Vat+")");
            
           
             
            
           
           
                
                ResultSet GetBillNo = CT.NewSelectQuery("SELECT LAST_INSERT_ID();");
                
               int BillNo = 0; 
                while(GetBillNo.next()){
                    
                    BillNo = GetBillNo.getInt(1);
                    
                }
                 
                   
                      
                if(BillNo!=0){
                          
                                
                 int ReferringBillToEmployee = CT.NewInsertQuery("insert into  Employee_Bill  values("+UserID+", "+BillNo+")");    
                    
                 for(int i = 0 ; i!=Table.size();i++){
                     
                    BillItem Row = Bill.getItems().get(i);
                    
                    
                     int AddProductToTheBill = CT.NewInsertQuery("insert into Bill_product values ("+BillNo+" ,"+Row.getPro_No()+" ,"+Row.getQ()+" , "+Row.getPrice()+")");
                     
                     
                     
                 }
                 
  
                   BillView Bill = new BillView();
                    Bill.setBillNo(BillNo);
                    Bill.Show();
                      NewBill();
                   
   
                }
            

        } catch (SQLException ex) {
            ExceptionHandler AAA = new ExceptionHandler(ex);
        }
        
     }
                                             

  
    }

    @FXML
    void NewBill(ActionEvent event) {
    NewBill(); 
    }
    
    
    
    
    private void NewBill(){
        
     ObservableList<BillItem>  Table =  FXCollections.observableArrayList();
     Bill.setItems(Table);
     
    TotalLabel.setText("Total("+GetVatAndCurrencyCode.GetCurrencyCode()+") : 0.0");
        
        
    }
     
  
       @FXML
   private void Add(ActionEvent event) {
       
      if(!ProductList.getSelectionModel().isEmpty()){
  
    ObservableList<BillItem>  Table =  Bill.getItems();
    
   Product ProductRow = ProductList.getSelectionModel().getSelectedItem();
   
  
   
         boolean TheItemInTheBill = false;
         int Row = 0;
         for(int i = 0;i<(Bill.getItems().size());i++){
             
               BillItem BillRow = Table.get(i);
          
               if(ProductRow.getPro_No()==BillRow.getPro_No()){
                   
                   
                   TheItemInTheBill = true;
                   Row = i;
                   
               }
             
             
             
         }
         
         if(TheItemInTheBill){
             
           BillItem BillRow = Table.get(Row);
           
           Table.set(Row, new BillItem(BillRow.getPro_No(), BillRow.getPro_Name(), BillRow.getPrice(), BillRow.getQ()+1));
           
           Bill.setItems(Table);
           
             
             
         }else{
             
             
            Table.add(new BillItem(ProductRow.getPro_No(), ProductRow.getPro_Name(), ProductRow.getPrice(), 1));
             
             
             
             
             
             
         }
     
      
       Bill.setItems(Table); 
        TotalLabel.setText("Total("+GetVatAndCurrencyCode.GetCurrencyCode()+") : "+Number.SetNumberOfDigits(Double.parseDouble(TotalLabel.getText().substring(13))+ProductRow.getPrice()));  
       
      }
    }
   
   
   
        @FXML
    private void Remove(ActionEvent event) {
       
     if(!ProductList.getSelectionModel().isEmpty()){     
  
    ObservableList<BillItem>  Table =  Bill.getItems();
    
   Product ProductRow = ProductList.getSelectionModel().getSelectedItem();
   
         boolean TheItemInTheBill = false;
         int Row = 0;
         for(int i = 0;i<(Bill.getItems().size());i++){
             
               BillItem BillRow = Table.get(i);
          
               if(ProductRow.getPro_No()==BillRow.getPro_No()){
                   
                   
                   TheItemInTheBill = true;
                   Row = i;
                   
               }
             
             
             
         }
         
         if(TheItemInTheBill){
             
             
             
           BillItem BillRow = Table.get(Row);
           
           if(BillRow.getQ()==1){
               
           Table.remove(Row);
          
           }else{
               
          Table.set(Row, new BillItem(BillRow.getPro_No(), BillRow.getPro_Name(), BillRow.getPrice(), BillRow.getQ()-1));
           
           }
           
         
             
            TotalLabel.setText("Total("+GetVatAndCurrencyCode.GetCurrencyCode()+") : "+Number.SetNumberOfDigits(Double.parseDouble(TotalLabel.getText().substring(13))-ProductRow.getPrice()));  
             
         }
     
      
       Bill.setItems(Table);
      
       
     }
       
    }
    
    
       @FXML
     private void Search(ActionEvent event) {
         
         if(Date.getValue().toString()!=null){
     
      
            LocalDate TheDate = Date.getValue();
          
            
            SearchResult Search = new SearchResult();
            Search.setDate(TheDate.getYear() , TheDate.getMonthValue(), TheDate.getDayOfMonth());
            Search.Show();
            
                    
                     
            
          
          
            
            
            
            
         }
        
     
     }
     

      

   
   
   
    public void SetUserID(String UserID){
        
        this.UserID = UserID;
        
    }
    
    public void SetName(String Name){
        
        this.Name = Name;
        
    }
    
    public void GetProducts(){
        
          try {
              
         LPro_No.setCellValueFactory(new PropertyValueFactory<Product , Integer>("Pro_No"));
         LPro_Name.setCellValueFactory(new PropertyValueFactory<Product , String>("Pro_Name"));
         LPrice.setCellValueFactory(new PropertyValueFactory<Product , Double>("Price"));
         
         ObservableList<Product>  Table =  FXCollections.observableArrayList();
         
         
     ResultSet re = CT.NewSelectQuery("SELECT * FROM Product where Availability = 'ON'");
           
       
            while(re.next()){
                
           
                 Table.add(new Product(re.getInt("Pro_No") , re.getString("Pro_Name") , re.getDouble("Price")));
           
                
           }
            
            
       ProductList.setItems(Table);
       
        
          } catch (SQLException ex) {
                ExceptionHandler AAA = new ExceptionHandler(ex);
          }
    }
    
    public void PrepareBill(){
        
         BPro_No.setCellValueFactory(new PropertyValueFactory<BillItem , Integer>("Pro_No"));
         BPro_Name.setCellValueFactory(new PropertyValueFactory<BillItem , String>("Pro_Name"));
         BPrice.setCellValueFactory(new PropertyValueFactory<BillItem , Integer>("Price"));
         Q.setCellValueFactory(new PropertyValueFactory<BillItem , Integer>("Q"));
         
       
        
        
    }
    
    
    
    
    
    public void Show(){
 
         try {
             
        Scene scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("/fxml/Employee_Interface.fxml")) , Color.TRANSPARENT);
        scene.getStylesheets().add("/styles/Styles.css");
      
        Stage ThisStage = new Stage();
        ThisStage.setScene(scene);
        ThisStage.setResizable(false);
        
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
        ThisStage.show();
        
         
         } catch (IOException ex) {
           ExceptionHandler AAA = new  ExceptionHandler(ex);
         }
    }

   
   
    
    
    

 
}
