/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_ihouse_project_final_version;

import com.jfoenix.controls.JFXButton;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author Main
 */
//package database_ihouse_project_final_version;


public class FXMLDocumentController {

    @FXML
    private Label labelGreeting;
    @FXML
    private Label labelDate;
    @FXML
    private JFXButton btnAddNewCase;
    @FXML
    private JFXButton btnPay;
    @FXML
    private JFXButton btnPaymentHistory;
    @FXML
    private JFXButton btnActiveRepairJobs;
    @FXML
    private JFXButton btnRepairJobsArchive;
    @FXML
    private JFXButton btnRequestReplacementPart;
    @FXML
    private JFXButton btnMyEmployees;
    @FXML
    private JFXButton btnMyCustomers;
    @FXML
    private JFXButton btnSettings;
    @FXML
    void initialize() {
        Calendar c = Calendar.getInstance();
        System.out.println(c);
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if(timeOfDay >= 0 && timeOfDay < 12)
            labelGreeting.setText("GOOD MORNING!");
                      
        else if(timeOfDay >= 12 && timeOfDay < 16)
            labelGreeting.setText("GOOD AFTERNOON!");
        
        else
            labelGreeting.setText("GOOD EVENING!");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
	labelDate.setText(formatter.format(date));
    }

    @FXML
    private void btnAddNewCaseClicked(ActionEvent event) {
    }

    @FXML
    private void btnPayClicked(ActionEvent event) {
    }

    @FXML
    private void btnPaymentHistoryClicked(ActionEvent event) {
    }

    @FXML
    private void btnActiveRepairJobsClicked(ActionEvent event) {
    }

    @FXML
    private void btnRepairJobsArchiveClicked(ActionEvent event) {
    }

    @FXML
    private void btnRequestReplacementPartClicked(ActionEvent event) {
    }

    @FXML
    private void btnMyEmployeesClicked(ActionEvent event) {
    }

    @FXML
    private void btnMyCustomersClicked(ActionEvent event) {
    }

    @FXML
    private void btnSettingsClicked(ActionEvent event) {
    }
}
