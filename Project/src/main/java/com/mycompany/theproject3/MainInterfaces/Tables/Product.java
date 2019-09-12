

package com.mycompany.theproject3.MainInterfaces.Tables;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Product {
    
  private IntegerProperty Pro_No;
  private StringProperty Pro_Name;
  private DoubleProperty Price;

  public Product(int Pro_No, String Pro_Name , double Price) {
     this.Pro_No = new SimpleIntegerProperty(Pro_No);
     this.Pro_Name = new SimpleStringProperty(Pro_Name);
     this.Price = new SimpleDoubleProperty(Price);
  }

  public final void setPro_No(int Pro_No) { 
      
      this.Pro_No.set(Pro_No);
  
  }
  
  
  public final void setPro_Name(String Pro_Name) {
      
      this.Pro_Name.set(Pro_Name);
  
  }
  
   public final void setPrice(double  Price) {
      
      this.Price.set(Price);
  
  }
   
  
  
  public int getPro_No() { 
      
      return Pro_No.get();
  
  }
  
   public String getPro_Name() { 
      
      return Pro_Name.get();
  
  }
   
  public double getPrice() { 
      
      return Price.get();
  
  } 
  

   
  

  
}