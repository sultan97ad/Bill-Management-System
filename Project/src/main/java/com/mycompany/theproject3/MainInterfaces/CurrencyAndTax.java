/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.theproject3.MainInterfaces;

import com.mycompany.theproject3.ConnectionTo;
import com.mycompany.theproject3.CurrentStageOperations;
import com.mycompany.theproject3.ExceptionHandler;
import com.mycompany.theproject3.GetVatAndCurrencyCode;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;


public class CurrencyAndTax {
    
    
    private ConnectionTo CT = new ConnectionTo();
    
    
      @FXML
    private TextField CurrencyCodeField;

      @FXML
     private Spinner<Integer> VatField;
    
    public void initialize() {
           
       SpinnerValueFactory<Integer> valueFactory =  new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, GetVatAndCurrencyCode.GetVat());
       VatField.setValueFactory(valueFactory);
       
       CurrencyCodeField.setText(GetVatAndCurrencyCode.GetCurrencyCode());
       
 
     }
    
    
    
    

    @FXML
    void Change(ActionEvent event) {
        
 try {
     
    if(CurrencyCodeField.getText().length()==3){

        int Vat =  VatField.getValue();
       String CurrencyCode = CurrencyCodeField.getText();

        int UpdateVatAndCurrencyCode = CT.NewInsertQuery("update Confg set vat = "+Vat+" , Currency_Code = '"+CurrencyCode+"' where Confg_No = 0 ");
        
         CurrentStageOperations.Close(event);
          
           }else{
        
        JOptionPane.showMessageDialog(null, "Currency Code Must Be ISO 4217 Code with 3 Characters Length");
        
       } 
    
       } catch (SQLException ex) {
             ExceptionHandler AAA = new ExceptionHandler(ex);
       }
       
       
    }

    @FXML
    void Close(ActionEvent event) {
        
   CurrentStageOperations.Close(event);
   
    }
    
    
      public void Show(){
 
         try {
             
        Scene scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("/Admin/CurrencyAndTax.fxml")));
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
