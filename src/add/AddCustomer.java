package add;

import dbConnector.MsSqlHandler;
import java.awt.Toolkit;
import java.sql.*;
import login.LoginDataBase;

/**
 *
 * @author Barış Dalyan Emre
 */
public class AddCustomer extends javax.swing.JFrame {

    private MsSqlHandler handler = null;
    private String connectionString = LoginDataBase.getDbConnectiString(); // Your DB link.
    private String user = LoginDataBase.getUserName();
    private String password = LoginDataBase.getPassword();

    public AddCustomer() {
        initComponents();
    }

    /**
     * Paste initComponents() with Annotation: @SuppressWarnings("unchecked") here.
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. 
     * 
     */
   

    private void btnSaveCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveCustomerActionPerformed

        String queryRecord = "INSERT INTO tbl_Customer (Customer_Name,Address,Phone) VALUES(?,?,?)";
        String customerName, address, phoneNum;

        try {
            customerName = tfCustomerName.getText().trim();
            address = tfAddress.getText().trim();
            phoneNum = tfPhone.getText().trim();
            if (!customerName.equals("") && !phoneNum.equals("") && !address.equals("")) { // Check, did user fill all fields. 

                PreparedStatement preparedStatement = handler.executeUpdateQuerys(queryRecord);
                preparedStatement.setString(1, customerName);
                preparedStatement.setString(2, address);
                preparedStatement.setString(3, phoneNum);
                preparedStatement.executeUpdate(); // queryRecord is processed and customer data is added to DB. 
                lblInfo.setText("*Registration successful !");

                handler.closeConnection(); // Cuts connection.
            } else {
                lblInfo.setText("*Please fill necessary fields!");
            }
        } catch (SQLException exception) {
            System.out.println("Error Code: " + exception.getErrorCode());
            System.out.println("Error: ");
            exception.printStackTrace();
        }

    }//GEN-LAST:event_btnSaveCustomerActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        try {
            handler = new MsSqlHandler(user, password, connectionString);
            System.out.println("Connected...");  // You can delete this code.
        } catch (SQLException exception) {
            System.out.println("Error Code: " + exception.getErrorCode());
            System.out.println("Error: ");
            exception.printStackTrace();
        }
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        handler.closeConnection(); 
        System.gc(); // JVM returns unused objects in order to recycle to RAM.
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSaveCustomer;
    private javax.swing.JPanel jPanel;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNumberWarning;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JTextField tfAddress;
    private javax.swing.JTextField tfCustomerName;
    private javax.swing.JTextField tfPhone;
    // End of variables declaration//GEN-END:variables
}
