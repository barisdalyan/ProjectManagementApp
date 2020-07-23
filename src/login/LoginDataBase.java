package login;

import java.awt.Toolkit;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import list.ListCustomers;

/**
 *
 * @author Barış Dalyan Emre
 */
public class LoginDataBase extends javax.swing.JFrame {

    // Connection data is supplied from here.
    private static String dbConnectiString;
    private static String userName;
    private static String password;

    public LoginDataBase() {
        initComponents();
        // setImg() method is used to set Login images through ImagePanel.jar.
        try {
            imgDB.setImg(ImageIO.read(getClass().getResource("server.png")));
            imgUser.setImg(ImageIO.read(getClass().getResource("user.png")));
            imgPassword.setImg(ImageIO.read(getClass().getResource("password.png")));
        } catch (IOException exception) {
            System.out.println("Error: ");
            exception.printStackTrace();
        }
        this.setLocationRelativeTo(this);
        cmdDataBase.setModel(new DefaultComboBoxModel(new String[]{"SQL Server", "MySQL Server"}));
    }

    public static String getDbConnectiString() {
        return dbConnectiString;
    }

    public static void setDbConnectiString(String dbConnectiString) {
        LoginDataBase.dbConnectiString = dbConnectiString;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        LoginDataBase.userName = userName;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        LoginDataBase.password = password;
    }

    /**
     * Paste initComponents() with Annotation: @SuppressWarnings("unchecked") here.
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. 
     * 
     */
    

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed

        // Connection data is gotten from text fields.
        String dbName = cmdDataBase.getSelectedItem().toString().trim();
        userName = tfUser.getText().trim();
        password = new String(pfPassword.getPassword());

        if (dbName.equals("") || userName.equals("") || password.equals("")) { // Check, did user fill all fields. 
            lblWarning.setText("*Fields can't be blank !");
        } else {
            if (dbName.equals("SQL Server") && userName.equals("sadmin") && password.equals("1368")) { // Your Ms Server username and password, this is why it needs to be changed. 

                dbConnectiString; // Your DB link must be assigned here.
                ListCustomers listCustomers = new ListCustomers(); // Log in and initialize the app.
                listCustomers.setVisible(true);
                this.dispose(); // Closes this frame and returns memory space to RAM. It destroys this object. 

            } /**
             * else if (dbName.equals("MySQL Server") && userName.equals("") &&
             * password.equals("")) { .... } Or create a DB table to keep Log-in
             * datas, then compare with user inputs. If a new Data Base is
             * necessary, use the code that is bellow init() method.
             * cmdDataBase.setModel(new DefaultComboBoxModel(new String[]{"SQL
             * Server", "MySQL Server"})) add a new data base string and control
             * it.
             */
            else {
                lblWarning.setText("*There is problem with your input ! ");
            }
        }
    }//GEN-LAST:event_btnLoginActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JComboBox<String> cmdDataBase;
    private ImagePanel.TabPanel imgDB;
    private ImagePanel.TabPanel imgPassword;
    private ImagePanel.TabPanel imgUser;
    private javax.swing.JPanel jPanel;
    private javax.swing.JSeparator jSeparator;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblWarning;
    private javax.swing.JPasswordField pfPassword;
    private javax.swing.JTextField tfUser;
    // End of variables declaration//GEN-END:variables
}
