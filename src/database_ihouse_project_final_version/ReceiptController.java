/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_ihouse_project_final_version;

import Classes.Payment;
import Classes.Receipt;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.sql.ResultSet;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author omarq
 */
public class ReceiptController implements Initializable {

    private ArrayList<Receipt> data;
    @FXML
    private JFXButton PrintButton;
    @FXML
    private JFXButton Exitbutton;
    @FXML
    private Label RecLab;
    @FXML
    private Label totalLab;
    @FXML
    private Label Top;
    @FXML
    private Label thank;
    @FXML
    private Label DATE;
    @FXML
    private Label payno;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            getData();
            showData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void showData() {
        DATE.setText(data.get(0).getDate());
        totalLab.setText("The Total is: " + String.valueOf(data.get(0).getAmount()));
        RecLab.setText(data.get(0).getDetails());
        payno.setText("Receipt No: " + String.valueOf(data.get(0).getReceiptNo()));
        RecLab.setFont(new Font("Book Antiqua", 14));
        totalLab.setFont(new Font("Book Antiqua", 18));
        thank.setFont(new Font("Book Antiqua", 12));
        Top.setFont(new Font("Book Antiqua", 16));
        DATE.setFont(new Font("Book Antiqua", 14));
    }
    private void getData() throws SQLException, ClassNotFoundException {
        data = new ArrayList<>();
        String SQL;
        MyConnection.connectDB();
        System.out.println("Connection \n\n\n");
        SQL = "select R.payment_no,R.details,R.date_of_issue,P.amount\n"
                + "from receipt r,payment P\n"
                + "where R.payment_no=P.payment_no\n"
                + "and P.payment_No=" + PaymentHistoryController.payment_No + ";";
        Statement stmt = MyConnection.con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        try {
            while (rs.next()) {
                data.add(new Receipt(
                        Integer.parseInt(rs.getString(1)),
                        rs.getString(2),
                        rs.getString(3),
                        Double.parseDouble(rs.getString(4))));
            }

        } catch (Exception e) {
            System.out.println("Error in Loop");
            System.exit(1);
        }
    }

    @FXML
    private void exitBtn(ActionEvent event) {
        Stage stage = (Stage) Exitbutton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

}
