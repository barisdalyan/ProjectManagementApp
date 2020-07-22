
package objRepo;

/**
 *
 * @author Barış Dalyan Emre
 */
public class CustomerDataObj {
    
    // This class contains Customer datas.
    
    private int id;
    private String customerName;
    private String address;
    private String phone;
   
    
    public CustomerDataObj(int id, String customerName, String address, String phone) {
        this.id = id;
        this.customerName = customerName;
        this.address = address;
        this.phone = phone;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
  
    
}
