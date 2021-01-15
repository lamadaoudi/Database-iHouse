/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_ihouse_project_final_version;

import Classes.AppleBranch;
import Classes.RepairJob;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
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
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author labibah
 */
public class RepairJobsArchiveController implements Initializable {

    @FXML
    private TableView<RepairJob> tvActiveRepairJobs;
    @FXML
    private TableColumn<RepairJob, String> col_repair_id;
    @FXML
    private TableColumn<RepairJob, String> col_job_status;
    @FXML
    private TableColumn<RepairJob, String> col_recieved_date;
    @FXML
    private TableColumn<RepairJob, String> col_technician_id;
    @FXML
    private TableColumn<RepairJob, String> col_branch_no;
    @FXML
    private JFXTextField tfSearch;
    private ArrayList<RepairJob> ArchivedJobList;
    private ObservableList<RepairJob> dataList;
    @FXML
    private TableColumn<RepairJob, String> col_closed_date;
    @FXML
    private JFXTabPane tabpane;
    @FXML
    private Tab tab1;
    @FXML
    private JFXButton btnDetails;
    @FXML
    private Tab tab2;
    @FXML
    private Label labelRepairID;
    @FXML
    private Label labelCustomerName;
    @FXML
    private Label labelSerialNo;
    @FXML
    private Label labelDeviceType;
    @FXML
    private Label labelTechnicianID;
    @FXML
    private Label labelTechnicianName;
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
    private void getDataJobs() throws Exception {
        ArchivedJobList = new ArrayList<>();
        String SQL;
        MyConnection.connectDB();
        System.out.println("Connection \n\n\n");
        SQL = "select * from repairJob R where R.job_status = 'closed' or R.job_status='finished';";
        Statement stmt = MyConnection.con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        try {

            while (rs.next()) {
                ArchivedJobList.add(new RepairJob(rs.getString(1), "0" , rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
        } catch (Exception e) {
            System.out.println("Could not load data");
        }
        rs.close();
        stmt.close();
        MyConnection.con.close();

        System.out.println("Connection closed" + ArchivedJobList.size());
        dataList = FXCollections.observableArrayList(ArchivedJobList);
        System.out.println(dataList);
    }
      private void showDataJobs() {
        col_repair_id.setCellValueFactory(new PropertyValueFactory<RepairJob, String>("Repair_id"));
        col_recieved_date.setCellValueFactory(new PropertyValueFactory<RepairJob, String>("Recieved_date"));
        col_job_status.setCellValueFactory(new PropertyValueFactory<RepairJob, String>("Job_status"));
        col_technician_id.setCellValueFactory(new PropertyValueFactory<RepairJob, String>("Technician_id"));
        col_branch_no.setCellValueFactory(new PropertyValueFactory<RepairJob, String>("Branch_no"));
       col_closed_date.setCellValueFactory(new PropertyValueFactory<RepairJob, String>("Closed_date"));
        //tvActiveRepairJobs.setItems(dataList);
       search();
        System.out.println("We're here now");
    }
      // The below Function references is: Youtube channel: Cool IT Help | JavaFX TableView with Search Filter

    private void search() {
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<RepairJob> filteredData = new FilteredList<>(dataList, b -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(repairjob -> {
                // If filter text is empty, display all persons.				
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if ( repairjob.getBranch_no()!=null && repairjob.getBranch_no().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (repairjob.getJob_status() != null && repairjob.getJob_status().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (repairjob.getRecieved_date()!=null && repairjob.getRecieved_date().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else if (repairjob.getClosed_date()!=null && repairjob.getClosed_date().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;                        
                } else if (repairjob.getRepair_id()!=null && repairjob.getRepair_id().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (repairjob.getTechnician_id()!=null && repairjob.getTechnician_id().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<RepairJob> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tvActiveRepairJobs.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tvActiveRepairJobs.setItems(sortedData);
    }

    @FXML
    private void btnDetailsClicked(ActionEvent event) {
        tvActiveRepairJobs.setItems(dataList);
        ObservableList<RepairJob> selectedRows = tvActiveRepairJobs.getSelectionModel().getSelectedItems();
        ArrayList<RepairJob> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> {
            try{
                showDetails(row);
            }
            catch (Exception e){
                System.out.println("Could not load details");
            }
        });
        showDataJobs();
    }
    private void showDetails(RepairJob row) throws Exception{
        String repair_id="";
        String customer_name="";
        String serial_no="";
        String deviceType="";
        String technician_num="";
        String technician_name="";
        MyConnection.connectDB();
        String SQL = "select R.repair_id, C.customer_name, D.serial_no, D.device_type, T.id_num, E.eName\n" +
                    "FROM RepairJob R, Device D, Technician T, Customer C, custreqrep Q, Employee E\n" +
                    "WHERE R.repair_id=D.repair_id AND\n" +
                    "	  R.technician_id = T.id_num AND\n" +
                    "      E.id_num = T.id_num AND\n" +
                    "      Q.repair_id = R.repair_id AND\n" +
                    "      Q.id_num = C.customer_id AND\n" +
                    "      R.repair_id="+ row.getRepair_id()+";";
        Statement stmt = MyConnection.con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        try {
            while (rs.next()) {
                repair_id=rs.getString(1);
                customer_name=rs.getString(2);
                serial_no=rs.getString(3);
                deviceType=rs.getString(4);
                technician_num=rs.getString(5);
                technician_name=rs.getString(6);
            }
        } catch (Exception e) {
            System.out.println("Could not get data");
        }
        rs.close();
        stmt.close();
        MyConnection.con.close();
        labelCustomerName.setText(customer_name);
        labelDeviceType.setText(deviceType);
        labelRepairID.setText(repair_id);
        labelSerialNo.setText(serial_no);
        labelTechnicianID.setText(technician_num);
        labelTechnicianName.setText(technician_name);
        tabpane.getSelectionModel().selectNext();
    }


}
