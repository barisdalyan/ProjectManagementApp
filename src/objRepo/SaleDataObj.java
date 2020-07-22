
package objRepo;

/**
 *
 * @author Barış Dalyan Emre
 */
public class SaleDataObj {
    
     // This class contains sold Project datas.
    
    private int id;
    private String customerName;
    private String projectName;
    private String date;
    private int supportTime;
    private float price;
    
    
    public SaleDataObj(int id, String customerName, String projectName, String date, int supportTime, float price) {
        this.id = id;
        this.customerName = customerName;
        this.projectName = projectName;
        this.date = date;
        this.supportTime = supportTime;
        this.price = price;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSupportTime() {
        return supportTime;
    }

    public void setSupportTime(int supportTime) {
        this.supportTime = supportTime;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
   
}
