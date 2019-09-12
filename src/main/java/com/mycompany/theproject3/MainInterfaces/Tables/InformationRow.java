

package com.mycompany.theproject3.MainInterfaces.Tables;



import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InformationRow {
    

  private StringProperty Property;
  private StringProperty Value;



  public InformationRow(String Property, String Value) {
      
   this.Property = new SimpleStringProperty(Property);
     this.Value = new SimpleStringProperty(Value);
    
  }

  public final void setProperty(String Property) { 
      
      this.Property.set(Property);
  
  }
  
  
  public final void setValue(String Value) {
      
      this.Value.set(Value);
  
  }
  
  
  
  public String getProperty() { 
      
      return Property.get();
  
  }
  
   public String getValue() { 
      
      return Value.get();
  
  }
   
 
  

  
}