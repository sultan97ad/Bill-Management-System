/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.theproject3.Products;

import com.mycompany.theproject3.ConnectionTo;
import com.mycompany.theproject3.CurrentStageOperations;
import com.mycompany.theproject3.ExceptionHandler;
import com.mycompany.theproject3.MainInterfaces.Tables.Product;
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


public class Products {
    
    
    private ConnectionTo CT = new ConnectionTo();
    private   Stage ThisStage = new Stage();
    
   
    @FXML
    private TableView<Product> ProductsTable;

    @FXML
    private TableColumn Pro_No;

    @FXML
    private TableColumn Pro_Name;

    @FXML
    private TableColumn Price;
    
    
     @FXML
     public void initialize() {
     
     
     GetProducts();
     
     

     }
     
     
     
     private void GetProducts(){
         
         
           try {
              
          Pro_No.setCellValueFactory(new PropertyValueFactory<Product , Integer>("Pro_No"));
         Pro_Name.setCellValueFactory(new PropertyValueFactory<Product , String>("Pro_Name"));
         Price.setCellValueFactory(new PropertyValueFactory<Product , Double>("Price"));
         
         ObservableList<Product>  Table =  FXCollections.observableArrayList();
         
         
     ResultSet re = CT.NewSelectQuery("SELECT * FROM Product where Availability = 'ON'");
           
       
            while(re.next()){
                
           
                 Table.add(new Product(re.getInt("Pro_No") , re.getString("Pro_Name") , re.getDouble("Price")));
           
                
           }
            
            
       ProductsTable.setItems(Table);
       
        
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
        
    if(!ProductsTable.getSelectionModel().isEmpty()){
      
      Product SelectedProduct = ProductsTable.getSelectionModel().getSelectedItem();
      Modify ModifyProduct = new Modify();
      ModifyProduct.SetProNo(SelectedProduct.getPro_No());
      ModifyProduct.SetEvent(event);
      ModifyProduct.Show();
         
  }
    
    }

    @FXML
    void Remove(ActionEvent event) {
        
  if(!ProductsTable.getSelectionModel().isEmpty()){
          
      int Message =  JOptionPane.showConfirmDialog(null, "Are you sure for removing this Product", "confirm message", JOptionPane.YES_NO_OPTION);
        if(Message==JOptionPane.YES_OPTION){
           
          try {
             
           Product SelectedProduct = ProductsTable.getSelectionModel().getSelectedItem();
                
              ResultSet GetTheEmployeeBills = CT.NewSelectQuery("select * from Product  where Pro_No ="+SelectedProduct.getPro_No());
              int NumberOfProductBills = 0;
              
              while(GetTheEmployeeBills.next()){
                  
                  NumberOfProductBills++;
                  
              }
              
             
              
              if(NumberOfProductBills>0){
                  
                  int SetAvailabilityOff = CT.NewInsertQuery("update Product set availability = 'OFF' where Pro_No ="+SelectedProduct.getPro_No());
               
                  
                  }else{
                  
                   int DeleteProductRecord = CT.NewInsertQuery("delete from Product  where Pro_No ="+SelectedProduct.getPro_No());
                      
                  }
              
                CurrentStageOperations.Close(event);
                    new Products().Show();
              
              
              
          } catch (SQLException ex) {
              ExceptionHandler AAA = new ExceptionHandler(ex);
          }
            
            
        }
        
       
        
      }
    } 
    
    
   
    
    
     public void Show(){
 
         try {
             
        Scene scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("/fxml/Products/Products.fxml")));
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
