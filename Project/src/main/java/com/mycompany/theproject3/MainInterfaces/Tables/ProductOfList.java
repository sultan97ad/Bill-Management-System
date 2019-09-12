

package com.mycompany.theproject3.MainInterfaces.Tables;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProductOfList {
    
  private IntegerProperty Pro_No;
  private StringProperty Pro_Name;
  private IntegerProperty Price;

  public ProductOfList(int Pro_No, String Pro_Name , int Price) {
     this.Pro_No = new SimpleIntegerProperty(Pro_No);
     this.Pro_Name = new SimpleStringProperty(Pro_Name);
     this.Price = new SimpleIntegerProperty(Price);
  }

  public final void setPro_No(int Pro_No) { 
      
      this.Pro_No.set(Pro_No);
  
  }
  
  
  public final void setPro_Name(String Pro_Name) {
      
      this.Pro_Name.set(Pro_Name);
  
  }
  
   public final void setPrice(int  Price) {
      
      this.Price.set(Price);
  
  }
   
  
  
  public int getPro_No() { 
      
      return Pro_No.get();
  
  }
  
   public String getPro_Name() { 
      
      return Pro_Name.get();
  
  }
   
  public int getPrice() { 
      
      return Price.get();
  
  } 
  

   
  

  
}