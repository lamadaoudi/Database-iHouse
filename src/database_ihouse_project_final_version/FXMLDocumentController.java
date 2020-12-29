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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private JFXButton btnSendToAppleBranch;
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
    private void btnAddNewCaseClicked(ActionEvent event) throws Exception{
        final Stage primaryStage2 = new Stage();
        primaryStage2.initModality(Modality.APPLICATION_MODAL);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage2.initOwner(app_stage);
        Parent nextSceneParent = FXMLLoader.load(getClass().getResource("AddRepairCustomer.fxml"));
        Scene scene11 = new Scene(nextSceneParent);
        primaryStage2.setScene(scene11);
        primaryStage2.show();
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
    private void btnRequestReplacementPartClicked(ActionEvent event) throws Exception {
        final Stage primaryStage2 = new Stage();
        primaryStage2.initModality(Modality.APPLICATION_MODAL);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage2.initOwner(app_stage);
        Parent nextSceneParent = FXMLLoader.load(getClass().getResource("RequestReplacementPart.fxml"));
        Scene scene11 = new Scene(nextSceneParent);
        primaryStage2.setScene(scene11);
        primaryStage2.show();
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

    @FXML
    private void btnSendToAppleBranchClicked(ActionEvent event) throws Exception{
        final Stage primaryStage2 = new Stage();
        primaryStage2.initModality(Modality.APPLICATION_MODAL);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage2.initOwner(app_stage);
        Parent nextSceneParent = FXMLLoader.load(getClass().getResource("SendToAppleBranch.fxml"));
        Scene scene11 = new Scene(nextSceneParent);
        primaryStage2.setScene(scene11);
        primaryStage2.show();
    }
    
}
