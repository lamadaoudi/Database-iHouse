/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_ihouse_project_final_version;

import Classes.RepairJob;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Main
 */
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
    private JFXButton btnSendToAppleBranch;
    @FXML
    private JFXButton btnWarehouse;
    @FXML
    private BarChart<?, ?> RepairJobsChart;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private Label labelPendingRepairs;
    @FXML
    private Label labelClosedRepairs;
    @FXML
    private Label labelFninishedJobs;
    @FXML
    private JFXDatePicker datePickerFrom;
    @FXML
    private JFXDatePicker datePickerTo;
    @FXML
    private JFXButton btnNumberOfJobs;
    @FXML
    void initialize() {
        try {
            setBarChart();
            setLabelQueries();
        } catch (Exception e) {
            System.out.println("Was not able to generate barchart.");
        }
        Calendar c = Calendar.getInstance();
        System.out.println(c);
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if (timeOfDay >= 0 && timeOfDay < 12) {
            labelGreeting.setText("GOOD MORNING");
        } else if (timeOfDay >= 12 && timeOfDay < 16) {
            labelGreeting.setText("GOOD AFTERNOON");
        } else {
            labelGreeting.setText("GOOD EVENING");
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        labelDate.setText(formatter.format(date));
    }

    private void setLabelQueries() throws Exception {
        int countPending = getCount("not open");
        labelPendingRepairs.setText(countPending + "\nPending Repairs");
        int countClosed = getCount("closed");
        labelClosedRepairs.setText(countClosed + "\nClosed Repairs");
        int countFinished = getCount("finished");
        labelFninishedJobs.setText(countFinished + "\nFinished Repairs");
    }

    private int getCount(String status) throws Exception {
        MyConnection.connectDB();
        int count = 0;
        String SQL = "SELECT COUNT(R.repair_id) FROM repairJob R WHERE R.job_status = '" + status + "';";
        Statement stmt = MyConnection.con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        try {
            while (rs.next()) {
                count = Integer.parseInt(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("Error in reading data");
        }
        rs.close();
        stmt.close();
        MyConnection.con.close();
        return count;
    }

    private void setBarChart() throws Exception {
        RepairJobsChart.getData().clear();
        XYChart.Series set1 = new XYChart.Series<>();
        MyConnection.connectDB();
        ArrayList<Integer> countList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            String monthString = new DateFormatSymbols().getMonths()[i - 1];
            int countJobs = 0;
            int year = Calendar.getInstance().get(Calendar.YEAR);
            String SQL = "SELECT COUNT(R.repair_id) FROM repairJob R WHERE month(R.recieved_date)=" + i + " AND year(R.recieved_date)=" + year + ";";
            Statement stmt = MyConnection.con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            try {
                while (rs.next()) {
                    countJobs = Integer.parseInt(rs.getString(1));
                }
            } catch (Exception e) {
                System.out.println("Error in reading data");
            }
            rs.close();
            stmt.close();
            set1.getData().add(new XYChart.Data(monthString, countJobs));
        }
        MyConnection.con.close();
        RepairJobsChart.getData().addAll(set1);

    }

    @FXML
    private void btnAddNewCaseClicked(ActionEvent event) throws Exception {
        changeScene("AddRepairCustomer.fxml", event);

    }

    @FXML
    private void btnPayClicked(ActionEvent event) throws Exception {
        changeScene("PayFXML.fxml", event);

    }

    @FXML
    private void btnPaymentHistoryClicked(ActionEvent event) throws Exception {
        changeScene("PaymentHistory.fxml", event);
    }

    @FXML
    private void btnActiveRepairJobsClicked(ActionEvent event) throws Exception {
        changeScene("ActiveRepairJobs.fxml", event);

    }

    @FXML
    private void btnRepairJobsArchiveClicked(ActionEvent event) throws Exception {
        changeScene("RepairJobsArchive.fxml", event);
    }

    @FXML
    private void btnRequestReplacementPartClicked(ActionEvent event) throws Exception {
        changeScene("RequestReplacementPart.fxml", event);
    }

    @FXML
    private void btnMyEmployeesClicked(ActionEvent event) throws Exception {
        changeScene("Employees.fxml", event);

    }

    @FXML
    private void btnMyCustomersClicked(ActionEvent event) throws Exception {
        changeScene("Customers.fxml", event);
    }

    @FXML
    private void btnSendToAppleBranchClicked(ActionEvent event) throws Exception {
        changeScene("SendToAppleBranch.fxml", event);
    }

    @FXML
    private void btnWarehouseClicked(ActionEvent event) throws Exception {
        changeScene("Warehouse.fxml", event);
    }

    @FXML
    private void refrechClicked(ActionEvent event) {
        try {
            setBarChart();
            setLabelQueries();
            datePickerFrom.getEditor().clear();
            datePickerTo.getEditor().clear();
            btnNumberOfJobs.setText("Number of jobs: ");
        } catch (Exception e) {
            System.out.println("Could not load the Bar Chart");
        }
    }

    private void changeScene(String sceneName, ActionEvent event) throws Exception {
        try {
            final Stage primaryStage2 = new Stage();
            primaryStage2.initModality(Modality.APPLICATION_MODAL);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage2.initOwner(app_stage);
            Parent nextSceneParent = FXMLLoader.load(getClass().getResource(sceneName));
            Scene scene11 = new Scene(nextSceneParent);
            primaryStage2.setScene(scene11);
            primaryStage2.show();
        } catch (Exception e) {
            System.out.println("Could not load scene");
        }
    }

    @FXML
    private void numberJobsClicked(ActionEvent event) throws Exception {
        if(datePickerFrom.getValue() != null && datePickerTo.getValue()!= null){
            int count=0;
            LocalDate localDate = datePickerFrom.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date FROM = Date.from(instant);
            LocalDate localDate2 = datePickerTo.getValue();
            Instant instant2 = Instant.from(localDate2.atStartOfDay(ZoneId.systemDefault()));
            Date TO = Date.from(instant2);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String fromString = df.format(FROM);
            String toString = df.format(TO);
            
            String SQL = "select count(*) from repairJob R where R.recieved_date >= '"+fromString+"' AND R.recieved_date <= '"+toString+"';";
            System.out.println(SQL);
            MyConnection.connectDB();
            Statement stmt = MyConnection.con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            try {
                while (rs.next()) {
                    count = Integer.parseInt(rs.getString(1));
                }
            } catch (Exception e) {
                System.out.println("Error in reading data");
            }
            rs.close();
            stmt.close();
            btnNumberOfJobs.setText("Number of jobs: "+count);
            rs.close();
            stmt.close();
            MyConnection.con.close();         
        }
    }

}
