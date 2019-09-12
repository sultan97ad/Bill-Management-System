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

/**
 *
 * @author pc
 */
 public class SearchResultRow {
     
  private IntegerProperty Bill_No;
  private IntegerProperty User_ID;
  private StringProperty DateTime;

  public SearchResultRow(int Bill_No, int User_ID , String DateTime) {
     this.Bill_No = new SimpleIntegerProperty(Bill_No);
     this.User_ID = new SimpleIntegerProperty(User_ID);
     this.DateTime = new SimpleStringProperty(DateTime);
  }

  public final void setBill_No(int Bill_No) { 
      
      this.Bill_No.set(Bill_No);
  
  }
  
  
  public final void setUser_ID(int User_ID) {
      
      this.User_ID.set(User_ID);
  
  }
  
   public final void setDateTime(String  DateTime) {
      
      this.DateTime.set(DateTime);
  
  }
   
  
  
  public int getBill_No() { 
      
      return Bill_No.get();
  
  }
  
   public int getUser_ID() { 
      
      return User_ID.get();
  
  }
   
  public String getDateTime() { 
      
      return DateTime.get();
  
  } 
   
}
