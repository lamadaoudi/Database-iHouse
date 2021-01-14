/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_ihouse_project_final_version;

/**
 *
 * @author Main
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javafx.scene.control.Alert;

public class MyConnection {
            private static String dbURL;
            private static String dbUsername;
            private static String dbPassword;
            static Connection con;
            private static final String URL = "127.0.0.1";
            private static final String port = "3306";
            private static final String dbName = "ihouse";

    public static String getDbUsername() {
        return dbUsername;
    }

    public static void setDbUsername(String dbUsername) {
        MyConnection.dbUsername = dbUsername;
    }

    public static String getDbPassword() {
        return dbPassword;
    }

    public static void setDbPassword(String dbPassword) {
        MyConnection.dbPassword = dbPassword;
    }


        
    static void connectDB() throws ClassNotFoundException, SQLException {

        dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=ConvertToNull&serverTimezone=GMT";

        Properties p = new Properties();

        p.setProperty("user", dbUsername);
        p.setProperty("password", dbPassword);
        p.setProperty("useSSL", "false");
        p.setProperty("autoReconnect", "true");
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection (dbURL, p);
        System.out.println("Connection Succesful");
}
    public static void ExecuteStatement(String SQL) throws SQLException {

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(SQL);
            stmt.close();

        }  catch (MySQLIntegrityConstraintViolationException ignored){
            System.out.println("Duplicated key!\n\n\n\n");
            CustomerController.duplicatedID=1;
            EmployeeController.duplicatedID=1;
            ignored.printStackTrace();
        } catch (SQLException s) {
            s.printStackTrace();
            System.out.println("SQL statement is not executed!");

        }

    }
           
}

