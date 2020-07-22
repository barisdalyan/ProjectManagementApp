package list;

import add.AddCustomer;
import dbConnector.MsSqlHandler;
import java.awt.Toolkit;
import objRepo.CustomerDataObj;
import java.sql.*;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import login.LoginDataBase;
import sale.SaleProject;
import update.UpdateCustomer;

/**
 *
 * @author Barış Dalyan Emre
 */
public class ListCustomers extends javax.swing.JFrame {

    private DefaultTableModel tblModel = null;
    private MsSqlHandler handler = null;
    private String connectionString = LoginDataBase.getDbConnectiString(); // Your DB link.
    private String user = LoginDataBase.getUserName();
    private String password = LoginDataBase.getPassword();

    public ListCustomers() {
        initComponents();
        tblModel = (DefaultTableModel) tblCustomers.getModel(); // Table model object was created.
    }

    /**
     * Paste initComponents() with Annotation: @SuppressWarnings("unchecked") here.
     * This method is called from within the constructor to initialize the form. 
     * WARNING: Do NOT modify this code.
     *
     */
   

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        buttonWarning.setText(""); // Cleans warning string.
        AddCustomer add = new AddCustomer(); // Calls AddCustomer frame to add new customer.
        add.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (tblCustomers.getSelectedRow() != -1) { // if there is a selection, it returns 1 or -1 .
            buttonWarning.setText("");

            UpdateCustomer update = new UpdateCustomer(); // Calls UpdateCustomer frame to update customer data.

            update.setTblModel(getTblModel()); // Access is opened for tblModel in UpdateCustomer.
            update.setTblCustomers(getTblCustomers()); // Access is opened for tblCustomers in UpdateCustomer.  
            update.setVisible(true);
        } else { // if there is no selection on table.
            buttonWarning.setText("*Select a row to update customer data!");

        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSellProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSellProjectActionPerformed
        buttonWarning.setText("");
        SaleProject sell = new SaleProject();  // Calls SaleProject frame for project sale.

        sell.setVisible(true);
        this.dispose(); // Closes this frame and returns memory space to RAM. It destroys this object. 
        System.gc(); // JVM returns unused objects in order to recycle to RAM.
    }//GEN-LAST:event_btnSellProjectActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        try {
            handler = new MsSqlHandler(user, password, connectionString);
            System.out.println("Connected..."); // You can delete this code.

            getCustomerData(handler); // It gets customer data from database to display on tblCustomers (Table).

        } catch (SQLException exception) {
            System.out.println("Error Code: " + exception.getErrorCode());
            System.out.println("Error: ");
            exception.printStackTrace();
        }
    }//GEN-LAST:event_formWindowOpened

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String customerName, address, phoneNum, warningString;
        int id, choice;
        String queryDelete = "DELETE FROM tbl_Customer WHERE Customer_Id= ?";

        try {
            buttonWarning.setText("");
            choice = tblCustomers.getSelectedRow(); // Row is selected that will be deleted.
            handler = new MsSqlHandler(user, password, connectionString); // Connection is set with DB until it will be disconnected by method closeConnection().

            customerName = tblModel.getValueAt(choice, 0).toString().trim();
            phoneNum = tblModel.getValueAt(choice, 1).toString().trim();
            address = tblModel.getValueAt(choice, 2).toString().trim();
            warningString = customerName + " - " + phoneNum + " - " + address + "  ";

            // JOptionPane warns user before delete customer.
            if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "About to delete: " + warningString, "Warning", 0)) {

                id = Integer.parseInt(tblModel.getValueAt(choice, 3).toString().trim());
                PreparedStatement preparedStatement = handler.executeUpdateQuerys(queryDelete);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate(); // queryDelete is processed and customer data is deleted in DB. 
                tblModel.removeRow(choice); // and from table.
            }

        } catch (SQLException exception) {
            System.out.println("Error Code: " + exception.getErrorCode());
            System.out.println("Error: ");
            exception.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException exception) { // if there is no selection. 
            buttonWarning.setText("*Select a row for deletion process! ");
            System.out.println("Error: " + exception.toString());

        } finally {
            handler.closeConnection(); // It cuts DB connection.
        }


    }//GEN-LAST:event_btnDeleteActionPerformed

    private void menuRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRefreshActionPerformed

        try { // Method is used to refresh table after adding new customer. Otherwise you can't see new added data.
            handler = new MsSqlHandler(user, password, connectionString);
            System.out.println("Connected...");  // You can delete this code.

            clearTable();
            getCustomerData(handler);

        } catch (SQLException exception) {
            System.out.println("Error Code: " + exception.getErrorCode());
            System.out.println("Error: ");
            exception.printStackTrace();
        }

    }//GEN-LAST:event_menuRefreshActionPerformed

    private void tfSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSearchKeyReleased
        String searchKey = tfSearch.getText();
        try {// TableRowSorter is created for object tblModel - DefaultTableModel.
            TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<DefaultTableModel>(tblModel);

            tableRowSorter.setRowFilter(RowFilter.regexFilter(searchKey)); // Added filter (searchKey) to tableRowSorter.
            tblCustomers.setRowSorter(tableRowSorter); // It sets object tableRowSorter to tblCustomers for filtering.
        } catch (PatternSyntaxException exception) {
            System.out.println("Error: ");
            exception.printStackTrace();
        }


    }//GEN-LAST:event_tfSearchKeyReleased

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?   ", "Warning", 0)) {
            handler.closeConnection(); // Closes connection after log out from system.
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        } else {
            setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        }
    }//GEN-LAST:event_formWindowClosing

    private void getCustomerData(MsSqlHandler handler) throws SQLException {
        String queryList = "SELECT * FROM tbl_Customer";
        // Customer data is selected from DB and added to tblCustomer row.
        ArrayList<CustomerDataObj> customerList = new ArrayList<CustomerDataObj>();
        ResultSet resultSet = handler.executeQuery(queryList);

        while (resultSet.next()) {
            //(Customer Name - Phone Num. - Address - Id )
            customerList.add(new CustomerDataObj(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));

        }
        for (CustomerDataObj listData : customerList) { // Adding datas to tblCustomer row.
            Object[] customer = {listData.getCustomerName(), listData.getPhone(), listData.getAddress(), listData.getId()};
            tblModel.addRow(customer);
        }

        handler.closeConnection();
    }

    private void clearTable() { // It clears tblCustomer before refreshing the table.
        int rowCount = tblModel.getRowCount();

        for (int i = rowCount - 1; i >= 0; i--) {
            tblModel.removeRow(i);
        }
    }

    public DefaultTableModel getTblModel() { // Like getter method for UpdateCustomer frame.
        return tblModel;
    }

    public JTable getTblCustomers() { // Like getter method for UpdateCustomer frame.
        return tblCustomers;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSellProject;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel buttonWarning;
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JMenu menu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuRefresh;
    private javax.swing.JTable tblCustomers;
    private javax.swing.JTextField tfSearch;
    // End of variables declaration//GEN-END:variables
}
