package dbConnector;

import java.sql.*;

/**
 *
 * @author Barış Dalyan Emre
 */
public class MsSqlHandler {

    private String user;
    private String password;
    private String connectionString;
    private Connection connection;
    private Statement statement;
    private PreparedStatement praparedStatement;
    private ResultSet resultSet;

    public MsSqlHandler() throws SQLException {
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException exception) {
            System.out.println("Error: ");
            exception.printStackTrace();
        }
        this.connectDB(); // This method sets database connection.

    }

    public MsSqlHandler(String user, String password, String connectionString) throws SQLException {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException exception) {
            System.out.println("Error: ");
            exception.printStackTrace();
        }

        this.user = user;
        this.password = password;
        this.connectionString = connectionString;
        this.connectDB(); // This method sets database connection.
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public final void connectDB() throws SQLException {

        connection = DriverManager.getConnection(connectionString, user, password);

    }

    public void closeConnection() { // Method cuts database connection for safety.
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Disconnected !");
            } catch (SQLException exception) {
                System.out.println("Error Code: " + exception.getErrorCode());
                System.out.println("Error: ");
                exception.printStackTrace();
            }

        }
    }

    // To SELECT 
    public ResultSet executeQuery(String sqlT) throws SQLException {

        if (connection != null) {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlT);
        }
        return resultSet;
    }

    // To INSERT-UPDATE-DELETE 
    public PreparedStatement executeUpdateQuerys(String sqlT) throws SQLException {

        if (connection != null) {
            praparedStatement = connection.prepareStatement(sqlT);
        }
        return praparedStatement;

    }

}
