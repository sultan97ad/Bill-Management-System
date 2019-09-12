/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.theproject3.MainInterfaces.Tables;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

 
public class EmployeesRow {
    
  private IntegerProperty User_ID;
  private StringProperty ActualName;



  public EmployeesRow(int User_ID, String ActualName) {
      
   this.User_ID = new SimpleIntegerProperty(User_ID);
     this.ActualName = new SimpleStringProperty(ActualName);
    
  }

  public final void setUser_ID(int User_ID) { 
      
      this.User_ID.set(User_ID);
  
  }
  
  
  public final void setActualName(String ActualName) {
      
      this.ActualName.set(ActualName);
  
  }
  
  
  
  public int getUser_ID() { 
      
      return User_ID.get();
  
  }
  
   public String getActualName() { 
      
      return ActualName.get();
  
  }
   
 
  
}
