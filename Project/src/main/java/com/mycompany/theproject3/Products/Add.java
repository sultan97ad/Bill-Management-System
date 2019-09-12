
package com.mycompany.theproject3.Products;

import com.mycompany.theproject3.Employees.*;
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
import com.mycompany.theproject3.Number;
import javafx.stage.StageStyle;


public class Add {
    
    private ConnectionTo CT = new ConnectionTo();
    private static ActionEvent Products;
    
   
    
    @FXML
    private TextField NameField;

    @FXML
    private TextField PriceField;

    @FXML
    void Add(ActionEvent event) {
        
         String Name = NameField.getText();
         String Price = PriceField.getText();
        
   if(!Name.isEmpty() && !Price.isEmpty() && Number.isNumeric(Price)){
       
           try{
                

            
                int AddNewProduct = CT.NewInsertQuery("Insert into Product (Pro_Name , Price , availability) values ('"+Name+"' ,"+Double.parseDouble(Price)+" , 'ON' )");
                
                  CurrentStageOperations.Close(event);
                  CurrentStageOperations.Close(Products);
                  new Products().Show();
           

             } catch (SQLException ex) {
               ExceptionHandler AAA = new ExceptionHandler(ex);
            }  
    }
   
    }
        
    

    @FXML
    void Close(ActionEvent event) {
        
     CurrentStageOperations.Close(event);
     
    }
    
    
    public void SetEvent(ActionEvent Products){
        
       this.Products = Products;
        
    }
    
    
    

    public void Show(){
 
         try {
             
        Scene scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("/fxml/Products/Products_Add.fxml")));
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
