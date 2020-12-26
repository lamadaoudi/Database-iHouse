/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_ihouse_project_final_version;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
//import com.mysql.jdbc.Statement;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 * FXML Controller class
 *
 * @author Main
 */
public class AddRepairCustomerController implements Initializable {

    @FXML
    private JFXTextField tfIDCustomer;
    @FXML
    private Label labelRegistered;
    @FXML
    private JFXButton btnContinueTab1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        labelRegistered.setVisible(false);
    }    

    @FXML
    private void tfIDCustomerClicked(ActionEvent event) {
        try{
            ArrayList<Integer> searchResult=getData(Integer.parseInt(tfIDCustomer.getText()));
            if (searchResult.size()!=0)
                labelRegistered.setVisible(true);
        }
        catch (Exception e){
            System.out.println("Connection not established");
    }
    }
    
        private ArrayList<Integer> getData(int id) throws SQLException, ClassNotFoundException {
        // TODO Auto-generated method stub
        ArrayList<Integer> data = new ArrayList<>();
        String SQL;
        MyConnection.connectDB();
        System.out.println("Connection \n\n\n");
        SQL = "select c.customer_id from customer c where c.customer_id="+id;
        Statement stmt = MyConnection.con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        try {
            while (rs.next()) {
                data.add(Integer.parseInt(rs.getString(1)));
            }
        } catch (Exception e) {
            System.out.println("Error In loop");
            System.exit(1);
        }
        rs.close();
        stmt.close();
        MyConnection.con.close();

        System.out.println("Connection closed" + data.size());
        return data;
    } 
    
}
