/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_ihouse_project_final_version;

import Classes.Payment;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author labibah
 */
public class PaymentHistoryController implements Initializable {

    @FXML
    private TableView<Payment> tvPaymentHistory;
    @FXML
    private TableColumn<Payment, String> col_Payment_NO;
    @FXML
    private TableColumn<Payment, String> col_amount;
    @FXML
    private TableColumn<Payment, String> col_reveived_date;
    @FXML
    private TableColumn<Payment, String> col_payment_method;
    @FXML
    private TableColumn<Payment, String> col_customer;
    @FXML
    private TableColumn<Payment, String> col_repair_id;
    @FXML
    private JFXTextField tfSearch;
    private ArrayList<Payment> PaymentHistory;
    private ObservableList<Payment> dataList;
    @FXML
    private JFXButton Btn1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            getDataJobs();
            showDataJobs();
        } catch (Exception e) {
            System.out.println("Could not fetch data");
        }
    }

    private void search() {
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Payment> filteredData = new FilteredList<>(dataList, b -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(payment -> {
                // If filter text is empty, display all persons.				
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (payment.getAmount() != null && payment.getAmount().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (payment.getCustomer_id() != null && payment.getCustomer_id().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (payment.getPayment_method() != null && payment.getPayment_method().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (payment.getPayment_no() != null && payment.getPayment_no().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (payment.getReceived_date() != null && payment.getReceived_date().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (payment.getRepair_id() != null && payment.getRepair_id().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });
                // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Payment> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tvPaymentHistory.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tvPaymentHistory.setItems(sortedData);
    }
    

    private void getDataJobs() throws Exception {
        PaymentHistory = new ArrayList<>();
        String SQL;
        MyConnection.connectDB();
        System.out.println("Connection \n\n\n");
        SQL = "select * from payment P order by payment_no;";
        Statement stmt = MyConnection.con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        try {

            while (rs.next()) {
                PaymentHistory.add(new Payment(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
                System.out.println(PaymentHistory.get(0).toString());
            }
        } catch (Exception e) {
            System.out.println("Error in reading data");
        }
        rs.close();
        stmt.close();
        MyConnection.con.close();
        System.out.println("Labibah Sucks");
        System.out.println("Connection closed" + PaymentHistory.size());
        dataList = FXCollections.observableArrayList(PaymentHistory);
        System.out.println(dataList);
    }

    private void showDataJobs() {
        col_repair_id.setCellValueFactory(new PropertyValueFactory<Payment, String>("Repair_id"));
        col_Payment_NO.setCellValueFactory(new PropertyValueFactory<Payment, String>("Payment_no"));
        col_amount.setCellValueFactory(new PropertyValueFactory<Payment, String>("Amount"));
        col_reveived_date.setCellValueFactory(new PropertyValueFactory<Payment, String>("Received_date"));
        col_payment_method.setCellValueFactory(new PropertyValueFactory<Payment, String>("Payment_method"));
        col_customer.setCellValueFactory(new PropertyValueFactory<Payment, String>("Customer_id"));
        //tvPaymentHistory.setItems(dataList);
        search();
        System.out.println("We're here now");
    }
    public static int payment_No;

    private void GetNoRow(Payment row) {
        // TODO Auto-generated method stub
        payment_No = Integer.parseInt(row.getPayment_no());
        System.out.println(payment_No);
    }

    @FXML
    void btn1Clicked(ActionEvent event) {
        ObservableList<Payment> selectedRows = tvPaymentHistory.getSelectionModel().getSelectedItems();
        ArrayList<Payment> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> {
            tvPaymentHistory.getItems();
            GetNoRow(row);

            Parent root;
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Receipt.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

}
