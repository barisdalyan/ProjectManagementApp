package update;

import dbConnector.MsSqlHandler;
import java.awt.Toolkit;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import login.LoginDataBase;

/**
 *
 * @author Barış Dalyan Emre
 */
public class UpdateCustomer extends javax.swing.JFrame {

    private MsSqlHandler handler = null;
    private String connectionString = LoginDataBase.getDbConnectiString(); // Your DB link.
    private String user = LoginDataBase.getUserName();
    private String password = LoginDataBase.getPassword();
    private JTable tblCustomers = null;
    private DefaultTableModel tblModel = null;

    public UpdateCustomer() {
        initComponents();
    }

    /**
     * Paste initComponents() with Annotation: @SuppressWarnings("unchecked") here.
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. 
     * 
     */
    

    private void btnUpdateCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCustomerActionPerformed
        int id, choice;
        String customerName, address, phoneNum;
        String queryUpdate = "UPDATE tbl_Customer SET Customer_Name = ?, Address = ?, "
                + "Phone = ? WHERE Customer_Id = ?";
        try {
            choice = tblCustomers.getSelectedRow();
            PreparedStatement preparedStatement = handler.executeUpdateQuerys(queryUpdate);
            id = Integer.parseInt(tblModel.getValueAt(choice, 3).toString().trim());

            customerName = tfCustomerName.getText();
            phoneNum = tfPhone.getText();
            address = tfAddress.getText();

            if (!customerName.equals("") && !phoneNum.equals("") && !address.equals("")) { // Check, did user fill all fields. 
                preparedStatement.setString(1, tfCustomerName.getText());
                preparedStatement.setString(2, tfAddress.getText());
                preparedStatement.setString(3, tfPhone.getText());
                preparedStatement.setInt(4, id);

                tblModel.setValueAt(customerName, choice, 0);
                tblModel.setValueAt(phoneNum, choice, 1);
                tblModel.setValueAt(address, choice, 2);

                preparedStatement.executeUpdate(); // queryUpdate is processed and datas are updated.
                lblInfo.setText("*Update successful !");

                handler.closeConnection(); // Cuts connection.
            } else {
                lblInfo.setText("*Please fill necessary fields!");
            }
        } catch (SQLException exception) {
            System.out.println("Error Code: " + exception.getErrorCode());
            System.out.println("Error: ");
            exception.printStackTrace();
        }


    }//GEN-LAST:event_btnUpdateCustomerActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        try {
            handler = new MsSqlHandler(user, password, connectionString);
            System.out.println("Connected..."); // You can delete this code.

            int choice = tblCustomers.getSelectedRow();
            
            // Selected row datas are auto-inserted to text fields. 
            tfCustomerName.setText(tblModel.getValueAt(choice, 0).toString().trim());
            tfPhone.setText(tblModel.getValueAt(choice, 1).toString().trim());
            tfAddress.setText(tblModel.getValueAt(choice, 2).toString().trim());

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

     // Access is opened from tblCustomers for tblModel and tblCustomers.
    public void setTblCustomers(JTable tblCustomers) {
        this.tblCustomers = tblCustomers;
    }

    public void setTblModel(DefaultTableModel tblModel) {
        this.tblModel = tblModel;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUpdateCustomer;
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
