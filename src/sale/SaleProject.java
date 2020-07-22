package sale;

import dbConnector.MsSqlHandler;
import java.awt.Toolkit;
import objRepo.ComboBoxObj;
import objRepo.SaleDataObj;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import list.ListCustomers;
import login.LoginDataBase;

/**
 *
 * @author Barış Dalyan Emre
 */
public class SaleProject extends javax.swing.JFrame {

    private DefaultTableModel tblModel = null;
    private PreparedStatement preparedStatement = null;
    private MsSqlHandler handler = null;
    private ResultSet resultCustomers = null;
    private ResultSet resultProjects = null;
    private String connectionString = LoginDataBase.getDbConnectiString(); // Your DB link.
    private String user = LoginDataBase.getUserName();
    private String password = LoginDataBase.getPassword();
    private SimpleDateFormat simpleDate = null;

    public SaleProject() {
        initComponents();
        tblModel = (DefaultTableModel) tblProjectSales.getModel(); // Table model object was created.
        simpleDate = new SimpleDateFormat("yyyy-MM-dd"); // To format calendar data to DB date.

    }

    /**
     * Paste initComponents() with Annotation: @SuppressWarnings("unchecked") here.
     * This method is called from within the constructor to initialize the form. 
     * WARNING: Do NOT modify this code.
     *
     */
   

    private void moveListCustomersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveListCustomersActionPerformed
        ListCustomers listele = new ListCustomers(); // To return to ListCustomers frame.
        listele.setVisible(true);
        this.dispose(); // Closes this frame and returns memory space to RAM. It destroys this object. 
        System.gc(); // JVM returns unused objects in order to recycle to RAM.
    }//GEN-LAST:event_moveListCustomersActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        try {// Connection is set with DB until it will be disconnected by method closeConnection().
            handler = new MsSqlHandler(user, password, connectionString);
            System.out.println("Connected...");  // You can delete this code.

            getCustomers(handler); // It carries Customers data to combobox.
            getProjects(handler); // It carries Projects data to combobox.
            getSaleData(handler); // It gets project sale datas from DB to display on tblProjectSales (Table).

        } catch (SQLException exception) {
            System.out.println("Error Code: " + exception.getErrorCode());
            System.out.println("Error: ");
            exception.printStackTrace();
        } finally {
            handler.closeConnection(); // It cuts DB connection.
        }
    }//GEN-LAST:event_formWindowOpened

    private void btnSellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSellActionPerformed
        ComboBoxObj selectedCustomer, selectedProject;
        int supportTime, timePeriod, times;
        float price;
        String date;

        String querySell = "INSERT INTO tbl_Project_Sales "
                + "(Customer_Id,Project_Id,Sell_Date,TechnicalSupportTime,TimePeriod,Price)"
                + " VALUES(?,?,?,?,?,?)";
        try {

            selectedCustomer = (ComboBoxObj) cmbCustomer.getSelectedItem(); // It assigns selected combobox object to referance selectedCustomer. 
            selectedProject = (ComboBoxObj) cmbProject.getSelectedItem(); // It assigns selected combobox object to referance selectedProject.

            timePeriod = Integer.parseInt(radioButton().get(0));
            times = Integer.parseInt(tfTimes.getText()); // Equivalent: how many times.
            supportTime = timePeriod * times; // Equivalent: how long time support.
            date = simpleDate.format(dtSale.getDate()); // It formatted calendar data to DB date.
            price = Float.parseFloat(tfPrice.getText());

            handler = new MsSqlHandler(user, password, connectionString);

            preparedStatement = handler.executeUpdateQuerys(querySell);
            preparedStatement.setInt(1, selectedCustomer.getId());
            preparedStatement.setInt(2, selectedProject.getId());
            preparedStatement.setString(3, date);
            preparedStatement.setInt(4, supportTime);
            preparedStatement.setString(5, radioButton().get(0));
            preparedStatement.setFloat(6, price);

            preparedStatement.executeUpdate(); // querySell is processed in DB and sale ​​has been completed.
            lblWarning.setText("*Sale successful !");

        } catch (NullPointerException exception) {
            System.out.println("Error: " + exception.toString());
            lblWarning.setText("*Please fill necessary fields !");
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Error: " + exception.toString());
            lblWarning.setText("*Please fill necessary fields !");
        } catch (NumberFormatException exception) {
            System.out.println("Error: " + exception.toString());
            lblWarning.setText("*Please fill necessary fields !");
        } catch (SQLException exception) {
            System.out.println("Error Code: " + exception.getErrorCode());
            System.out.println("Error: ");
            exception.printStackTrace();
        } finally {
            handler.closeConnection();
        }
    }//GEN-LAST:event_btnSellActionPerformed

    private void tblProjectSalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProjectSalesMouseClicked
        if (tblProjectSales.getSelectedColumn() == 4) { // Explanation about support time.

            JOptionPane.showMessageDialog(this, " It is calculated based on day.", "About Support Time", INFORMATION_MESSAGE);

            /*
            \n D/M/Y x Times = S.T.\n Day - 1, Month - 30, Year - 365
             */
        } else {

        }
    }//GEN-LAST:event_tblProjectSalesMouseClicked

    private void menuRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRefreshActionPerformed

        try {// Method is used to refresh table after adding new project sale. Otherwise you can't see new added data.
            handler = new MsSqlHandler(user, password, connectionString);
            System.out.println("Connected...");  // You can delete this code.
            clearTable();
            getSaleData(handler);

        } catch (SQLException exception) {
            System.out.println("Error Code: " + exception.getErrorCode());
            System.out.println("Error: ");
            exception.printStackTrace();
        } finally {
            handler.closeConnection();
        }

    }//GEN-LAST:event_menuRefreshActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        int id, choice;
        String queryCancel = "DELETE FROM tbl_Project_Sales WHERE id = ?";

        try {
            lblWarning.setText("");
            choice = tblProjectSales.getSelectedRow(); // Row is selected that will be deleted.
            handler = new MsSqlHandler(user, password, connectionString);
            id = Integer.parseInt(tblModel.getValueAt(choice, 0).toString().trim());

            // JOptionPane warns user before delete project sale.
            if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "You about to cancel sale ID: " + id, "Warning", 0)) {

                preparedStatement = handler.executeUpdateQuerys(queryCancel);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate(); // queryCancel is processed and sale data is deleted in DB.
                tblModel.removeRow(choice); // and from table.
            }
        } catch (SQLException exception) {
            System.out.println("Error Code: " + exception.getErrorCode());
            System.out.println("Error: ");
            exception.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException exception) { // if there is no selection. 
            lblWarning.setText("*Select a row to cancel ! ");
            System.out.println("Error: " + exception.toString());
        } finally {
            handler.closeConnection();
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?   ", "Warning", 0)) {
            handler.closeConnection(); // Closes connection after log out from system.
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        } else {
            setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        }
    }//GEN-LAST:event_formWindowClosing

    private void getSaleData(MsSqlHandler handler) throws SQLException {
        String queryGetSales = "SELECT id,(SELECT Customer_Name FROM tbl_Customer "
                + "WHERE tbl_Customer.Customer_Id = tbl_Project_Sales.Customer_Id),"
                + "(SELECT Project_Name FROM tbl_Project WHERE Project_Id = tbl_Project_Sales.Project_Id),"
                + "Sell_Date,TechnicalSupportTime,Price  FROM tbl_Project_Sales ORDER BY Sell_Date";
// InnerJoin may be used----------------------------------------------------------------------------------------------
        ArrayList<SaleDataObj> projectSales = new ArrayList<SaleDataObj>();
        ResultSet resultSet = handler.executeQuery(queryGetSales);

        while (resultSet.next()) {
            // (Id - Customer Name - Project Name - Sale Date - Support Time - Price)
            projectSales.add(new SaleDataObj(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getInt(5), resultSet.getFloat(6)));

        }

        for (SaleDataObj sales : projectSales) { // Adding datas to tblProjectSales row.
            Object[] saleData = {sales.getId(), sales.getCustomerName(), sales.getProjectName(),
                sales.getDate(), sales.getSupportTime(), sales.getPrice()};
            tblModel.addRow(saleData);

        }

    }

    private void getCustomers(MsSqlHandler handler) throws SQLException {
        String queryGetCustomers = "SELECT * FROM tbl_Customer ORDER BY Customer_Name"; // List datas alphabetical.
        resultCustomers = handler.executeQuery(queryGetCustomers);
        Vector<ComboBoxObj> modelCmbCustomers = new Vector<ComboBoxObj>();

        while (resultCustomers.next()) {

            // Customer string is added to Vector.
            modelCmbCustomers.add(new ComboBoxObj(resultCustomers.getString(2) + " - " + resultCustomers.getString(4) + " - " + resultCustomers.getString(3), resultCustomers.getInt(1)));
        }

        // Vector object is added to obj cmbCustomer in this way we can see strings on combobox.
        cmbCustomer.setModel(new DefaultComboBoxModel(modelCmbCustomers));

    }

    private void getProjects(MsSqlHandler handler) throws SQLException {
        String queryGetProjects = "SELECT * FROM tbl_Project ORDER BY Project_Name"; // List datas alphabetical.
        resultProjects = handler.executeQuery(queryGetProjects);
        Vector<ComboBoxObj> modelCmbProjects = new Vector<ComboBoxObj>();

        while (resultProjects.next()) {

            modelCmbProjects.add(new ComboBoxObj(resultProjects.getString(2), resultProjects.getInt(1)));
        }
        cmbProject.setModel(new DefaultComboBoxModel(modelCmbProjects));
    }

    private ArrayList<String> radioButton() {
        /* D/M/Y x Times = Support Time 
           Day = 1 d, Month = 30 d, Year = 365 d
           Not: d = Day.
         */

        ArrayList<String> listRadio = new ArrayList();

        if (radioDay.isSelected()) {
            listRadio.add("1");

        } else {
            listRadio.remove("1");
        }

        if (radioMonth.isSelected()) {
            listRadio.add("30");

        } else {
            listRadio.remove("30");
        }

        if (radioYear.isSelected()) {
            listRadio.add("365");

        } else {
            listRadio.remove("365");
        }

        return listRadio;
    }

    private void clearTable() { // It clears tblProjectSales before refreshing the table.
        int rowCount = tblModel.getRowCount();

        for (int i = rowCount - 1; i >= 0; i--) {
            tblModel.removeRow(i);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSell;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmbCustomer;
    private javax.swing.JComboBox<String> cmbProject;
    private com.toedter.calendar.JDateChooser dtSale;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JSeparator jSeparator;
    private javax.swing.JPanel jpBackground;
    private javax.swing.JPanel jpChoicePanel;
    private javax.swing.JPanel jpTable;
    private javax.swing.JPanel jpTitleAndButtons;
    private javax.swing.JLabel lblCurrency;
    private javax.swing.JLabel lblCustomer;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblProject;
    private javax.swing.JLabel lblTimePeriod;
    private javax.swing.JLabel lblTimes;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblWarning;
    private javax.swing.JMenu menu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuRefresh;
    private javax.swing.JMenuItem moveListCustomers;
    private javax.swing.JRadioButton radioDay;
    private javax.swing.JRadioButton radioMonth;
    private javax.swing.JRadioButton radioYear;
    private javax.swing.JTable tblProjectSales;
    private javax.swing.JTextField tfPrice;
    private javax.swing.JTextField tfTimes;
    // End of variables declaration//GEN-END:variables
}
