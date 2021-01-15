/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_ihouse_project_final_version;

import Classes.RepairJob;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * FXML Controller class
 *
 * @author Main
 */
public class ActiveRepairJobsController implements Initializable {

    @FXML
    private TableView<RepairJob> tvActiveRepairJobs;
    @FXML
    private TableColumn<RepairJob, String> col_repair_id;
    @FXML
    private TableColumn<RepairJob, String> colCustomerID;
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

    private ArrayList<RepairJob> RepairJobList;
    private ObservableList<RepairJob> dataList;
    @FXML
    private JFXButton btnDelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            getDataJobs();
            showDataJobs();
            setTableEditable();
        } catch (Exception e) {
            System.out.println("Could not fetch data");
        }
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

    private void getDataJobs() throws Exception {
        RepairJobList = new ArrayList<>();
        String SQL;
        MyConnection.connectDB();
        System.out.println("Connection \n\n\n");
        SQL = "select R.repair_id, C.id_num, R.job_status, R.recieved_date, R.closed_date, R.technician_id, R.branch_no from repairJob R, custreqrep C  where R.repair_id = C.repair_id AND R.job_status<>'closed' and R.job_status<>'finished';";
        Statement stmt = MyConnection.con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        try {

            while (rs.next()) {
                RepairJobList.add(new RepairJob(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }
        } catch (Exception e) {
            System.out.println("Error in reading data");
        }
        rs.close();
        stmt.close();
        MyConnection.con.close();

        System.out.println("Connection closed" + RepairJobList.size());
        dataList = FXCollections.observableArrayList(RepairJobList);
        System.out.println(dataList);
    }

    private void showDataJobs() {
        col_repair_id.setCellValueFactory(new PropertyValueFactory<RepairJob, String>("Repair_id"));
        col_recieved_date.setCellValueFactory(new PropertyValueFactory<RepairJob, String>("Recieved_date"));
        col_job_status.setCellValueFactory(new PropertyValueFactory<RepairJob, String>("Job_status"));
        col_technician_id.setCellValueFactory(new PropertyValueFactory<RepairJob, String>("Technician_id"));
        col_branch_no.setCellValueFactory(new PropertyValueFactory<RepairJob, String>("Branch_no"));
        colCustomerID.setCellValueFactory(new PropertyValueFactory<RepairJob, String>("Customer_name"));
        search();
    }

    private void setTableEditable() {
        tvActiveRepairJobs.setEditable(true);
        col_job_status.setCellFactory(TextFieldTableCell.<RepairJob>forTableColumn());
        col_technician_id.setCellFactory(TextFieldTableCell.<RepairJob>forTableColumn());
    }
    @FXML
    private void editJobStatus(TableColumn.CellEditEvent<RepairJob, String> t) {
        if (t.getNewValue().equalsIgnoreCase("finished") || t.getNewValue().equalsIgnoreCase("not open") || t.getNewValue().equalsIgnoreCase("in progress")) {
            ((RepairJob) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())).setJob_status(t.getNewValue());
            updateJobStatus(t.getRowValue().getRepair_id(), t.getNewValue());
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Input");
            alert.setContentText("Job status can only be not open, in progress, or finished");
            alert.showAndWait();
            try {
                getDataJobs();
                showDataJobs();
            } catch (Exception e) {
                System.out.println("Could not refresh data");
            }
        }

    }

    private void updateJobStatus(String repairId, String status) {
        try {
            MyConnection.connectDB();
            System.out.println("update repairJob set job_status= '" + status + "' where repair_id = " + repairId + ";");
            MyConnection.ExecuteStatement("update repairJob set job_status= '" + status + "' where repair_id = " + repairId + ";");
            MyConnection.con.close();
            getDataJobs();
            showDataJobs();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Could not update status");
        }

    }
    @FXML
    private void editTechnician(TableColumn.CellEditEvent<RepairJob, String> t) {
        try {
            ArrayList<Integer> searchResult = getData(t.getNewValue());
            if (searchResult.size() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid Value Entered");
                alert.setContentText("Could find tachnician id in database. Please enter a valid Technician_ID");
                alert.showAndWait();
                getDataJobs();
                showDataJobs();
            }
            else{
                ((RepairJob) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())).setJob_status(t.getNewValue());
                updateTechnician(t.getRowValue().getRepair_id(), t.getNewValue());
            }

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Value Entered");
            alert.setContentText("Please enter a proper value for the repair_id");
            alert.showAndWait();
        }
        catch (Exception e){
            
        }
    }
    
    private ArrayList<Integer> getData(String id) throws SQLException, ClassNotFoundException {
        // TODO Auto-generated method stub
        ArrayList<Integer> data = new ArrayList<>();
        String SQL;
        MyConnection.connectDB();
        System.out.println("Connection \n\n\n");
        SQL = "select T.id_num from technician T where T.id_num=" + id;
        Statement stmt = MyConnection.con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        try {
            while (rs.next()) {
                data.add(Integer.parseInt(rs.getString(1)));
            }
        } catch (Exception e) {
            System.out.println("Could not fetch data");
        }
        rs.close();
        stmt.close();
        MyConnection.con.close();

        System.out.println("Connection closed" + data.size());
        return data;
    }
    
    private void updateTechnician(String repairId, String technician_id){
         try {
            MyConnection.connectDB();
            System.out.println("update repairJob set technician_id= '" + technician_id + "' where repair_id = " + repairId + ";");
            MyConnection.ExecuteStatement("update repairJob set technician_id= '" + technician_id + "' where repair_id = " + repairId + ";");
            MyConnection.con.close();
            getDataJobs();
            showDataJobs();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Could not update technician");
        }
        
    }


    @FXML
    private void btnDeleteClicked(ActionEvent event) {
        tvActiveRepairJobs.setItems(dataList);
        ObservableList<RepairJob> selectedRows = tvActiveRepairJobs.getSelectionModel().getSelectedItems();
        ArrayList<RepairJob> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> {
            tvActiveRepairJobs.getItems().remove(row);
            deleteRow(row);
            tvActiveRepairJobs.refresh();
        });
        showDataJobs();
    }
    
    private void deleteRow(RepairJob row) {
        // TODO Auto-generated method stub
        try {
            System.out.println("delete from apple_branch where branch_no=" + row.getBranch_no() + ";");
            MyConnection.connectDB();
            MyConnection.ExecuteStatement("delete from repairJob where repair_id=" + row.getRepair_id()+ ";");
            MyConnection.con.close();
            System.out.println("Connection closed");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }    
    
}
