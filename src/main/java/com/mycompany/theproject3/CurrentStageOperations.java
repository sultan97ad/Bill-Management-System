/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.theproject3;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Node;


public class CurrentStageOperations {
    
    
    
  public static void Close(ActionEvent event){
      
       Stage CurrentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
         CurrentStage.close();
      
  }

  
}
