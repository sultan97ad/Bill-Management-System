package com.mycompany.theproject3.MainInterfaces;

import com.mycompany.theproject3.ConnectionTo;
import com.mycompany.theproject3.CurrentStageOperations;
import com.mycompany.theproject3.ExceptionHandler;
import com.mycompany.theproject3.Number;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Login {
    
     private  ConnectionTo CT = new ConnectionTo();

    @FXML
    private Label label;

    @FXML
    private Button Login;

    @FXML
    private Button Close;

    @FXML
    private TextField UserIDField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Text Error;
    
    
   

    
    public void initialize() {
    
    }
    
   

    @FXML
    void login(ActionEvent event) {

    


       String U = UserIDField.getText();
        String P = PasswordField.getText();
        
     if( Number.isNumeric(U)){
         if(!U.isEmpty() &&  !P.isEmpty()){
             
             try {
                 
             
                     ResultSet Search = CT.NewSelectQuery("SELECT * FROM User  Where User_ID="+Integer.parseInt(U)+" and Password='"+P+"' and Availability = true");
                     int count = 0 ;
                     String User_Name = null;
                     boolean UserIsManager = false;
                     
                     while(Search.next()){
                         
                         count++;
                         User_Name = Search.getString("User_Name");
                         UserIsManager = Search.getBoolean("Manager");
                     }
                     
                     if(count==1){
                         
                         
                         if(UserIsManager){
                             
                        Manager_Interface Frame = new Manager_Interface();
                             Frame.SetUserID(Integer.parseInt(U));
                             Frame.SetUserName(User_Name);
                             Frame.Show();
                             CurrentStageOperations.Close(event); 
                             
                             
                         }else{
                             
                             
                           if(Integer.parseInt(U)==1){
                               
                                new Admin_Interface().Show();
                               CurrentStageOperations.Close(event);
                              
                           }else{
                               
                       Employee_Interface Frame = new Employee_Interface();
                       Frame.SetName(User_Name);
                       Frame.SetUserID(U);
                       
                       Frame.Show();
                       CurrentStageOperations.Close(event);
                       
                       
                           }
                             
                             
                             
                      
                             
                         }
                         
                         
                   
                         
                         
                     }else{
                         
                         Error.setText("User-ID or Password is incorrect");
                         
                     }
                     
                 
                 
                 
             } catch (SQLException ex) {
                 
                 Error.setText("Connection Error");
                 
             }
             
             
             
             
         }else{
             
             Error.setText("Enter User-ID and Password ");
             
         }} else {
     }
    
    }
    
    
    
    @FXML
    void Close(ActionEvent event) { 
        
        CurrentStageOperations.Close(event);
   
     
     
    }
    
    
    public void Show(){
 
         try {
             
        Scene scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("/fxml/Login.fxml")));
        scene.getStylesheets().add("/styles/Styles.css");
        
        Stage ThisStage = new Stage();
        ThisStage.initStyle(StageStyle.UNDECORATED);
        ThisStage.setScene(scene);
        ThisStage.setResizable(false);
        ThisStage.show();
        
         
         } catch (IOException ex) {
           ExceptionHandler AAA = new  ExceptionHandler(ex);
         }
    }
    
    
    
   
    
}
