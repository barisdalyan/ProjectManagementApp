
package objRepo;

/**
 *
 * @author Barış Dalyan Emre
 */
public class ComboBoxObj {

    // This class contains sold Project and Customer combobox id and String.
    
    private int id;
    private String itemDataString;

    
    public ComboBoxObj(String itemDataString, int id) {
        this.itemDataString = itemDataString;
        this.id = id;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemDataString() {
        return itemDataString;
    }

    public void setItemDataString(String itemDataString) {
        this.itemDataString = itemDataString;
    }
    
      
     @Override
    public String toString() {
        return itemDataString ;
    }
    
}
