/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_ihouse_project_final_version;

import Classes.AppleBranch;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author Main
 */
public class SendToAppleBranchController implements Initializable {

    @FXML
    private Label labelEnterRepairID;
    @FXML
    private JFXTextField tfEnterRepairID;
    @FXML
    private TableView<AppleBranch> tvBranch;
    @FXML
    private TableColumn<AppleBranch, Integer> colBranchNo;
    @FXML
    private TableColumn<AppleBranch, String> colContactInfo;
    @FXML
    private TableColumn<AppleBranch, String> colLocation;
    @FXML
    private JFXButton btnSend;

    private ArrayList<AppleBranch> dataAppleBranch;
    private static ObservableList<AppleBranch> dataList;
    private int currentRepairID = -1;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXTextField tfEnterBranchNo;
    @FXML
    private JFXTextField tfEnterContactInfo;
    @FXML
    private JFXTextField tfEnterLocation;
    @FXML
    private JFXButton btnInsert;
    @FXML
    private JFXTextField tfSearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            getDataAppleBranch();
            showDataAppleBranch();
            setTableEditable();
                   
        } catch (Exception e) {
            System.out.println("Could not fetch data");
        }
    }
    // The below Function references is: Youtube channel: Cool IT Help | JavaFX TableView with Search Filter
    private void search(){
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<AppleBranch> filteredData = new FilteredList<>(dataList, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		tfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(applebranch -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (applebranch.getContactInfo()!=null && applebranch.getContactInfo().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (applebranch.getLocation()!=null && applebranch.getLocation().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if ( String.valueOf(applebranch.getBranch_no()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<AppleBranch> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tvBranch.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tvBranch.setItems(sortedData); 
    }

    private void setTableEditable() {
        tvBranch.setEditable(true);
        colBranchNo.setCellFactory(TextFieldTableCell.<AppleBranch, Integer>forTableColumn(new IntegerStringConverter()));
        colContactInfo.setCellFactory(TextFieldTableCell.<AppleBranch>forTableColumn());
        colLocation.setCellFactory(TextFieldTableCell.<AppleBranch>forTableColumn());
    }

    private void getDataAppleBranch() throws Exception {
        dataAppleBranch = new ArrayList<>();
        String SQL;
        MyConnection.connectDB();
        System.out.println("Connection \n\n\n");
        SQL = "select * from apple_branch;";
        Statement stmt = MyConnection.con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        try {

            while (rs.next()) {
                dataAppleBranch.add(new AppleBranch(Integer.parseInt(rs.getString(1)), rs.getString(3), rs.getString(2)));
            }
        } catch (Exception e) {
            System.out.println("Error in reading data");
        }
        rs.close();
        stmt.close();
        MyConnection.con.close();

        System.out.println("Connection closed" + dataAppleBranch.size());
        dataList = FXCollections.observableArrayList(dataAppleBranch);
        System.out.println(dataList);
    }

    private void showDataAppleBranch() {
        colBranchNo.setCellValueFactory(new PropertyValueFactory<AppleBranch, Integer>("Branch_no"));
        colLocation.setCellValueFactory(new PropertyValueFactory<AppleBranch, String>("Location"));
        colContactInfo.setCellValueFactory(new PropertyValueFactory<AppleBranch, String>("ContactInfo"));
        //tvBranch.setItems(dataList);
        search();
        System.out.println("We're here now");
    }

    @FXML
    private void tfEnterRepairIDClicked(ActionEvent event) throws Exception {
        try {
            currentRepairID = Integer.parseInt(tfEnterRepairID.getText());
            ArrayList<Integer> searchResult = getData(currentRepairID);
            if (searchResult.size() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid Value Entered");
                alert.setContentText("Could find repair job in database. Please enter a valid Repair_ID");
                alert.showAndWait();
                currentRepairID = -1;
            }

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Value Entered");
            alert.setContentText("Please enter a proper value for the repair_id");
            alert.showAndWait();
        }
    }

    private ArrayList<Integer> getData(int id) throws SQLException, ClassNotFoundException {
        // TODO Auto-generated method stub
        ArrayList<Integer> data = new ArrayList<>();
        String SQL;
        MyConnection.connectDB();
        System.out.println("Connection \n\n\n");
        SQL = "select R.repair_id from repairJob R where R.job_status <> 'finished' AND R.job_status<>'closed' AND R.repair_id=" + id;
        Statement stmt = MyConnection.con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        try {
            while (rs.next()) {
                data.add(Integer.parseInt(rs.getString(1)));
            }
        } catch (Exception e) {
            System.out.println("Error In loop");
            //System.exit(1);
        }
        rs.close();
        stmt.close();
        MyConnection.con.close();

        System.out.println("Connection closed" + data.size());
        return data;
    }

    @FXML
    private void btnSendClicked(ActionEvent event) {
        ObservableList<AppleBranch> selectedRows = tvBranch.getSelectionModel().getSelectedItems();
        ArrayList<AppleBranch> rows = new ArrayList<>(selectedRows);
        try {
            int branch_no = rows.get(0).getBranch_no();
            MyConnection.connectDB();
            if (currentRepairID != -1) {
                String SQL = "update repairJob R set R.branch_no=" + branch_no + " where R.repair_id=" + currentRepairID + ";";
                MyConnection.ExecuteStatement(SQL);
                MyConnection.con.close();
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Branch Requested");
            alert.setContentText("Repair Job sent to the Apple Branch");
            alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid Value Entered");
                alert.setContentText("Please enter a the repair_id and press enter");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Missing Info");
            alert.setContentText("Please select an Apple Branch");
            alert.showAndWait();
        }
    }
    @FXML
    private void editContactInfo(TableColumn.CellEditEvent<AppleBranch, String> t) {
        ((AppleBranch) t.getTableView().getItems().get(
                t.getTablePosition().getRow())).setContactInfo(t.getNewValue());
        updateContactInfo(t.getRowValue().getBranch_no(), t.getNewValue());
    }
    @FXML
    private void editLocation(TableColumn.CellEditEvent<AppleBranch, String> t) {
        ((AppleBranch) t.getTableView().getItems().get(
                t.getTablePosition().getRow())).setLocation(t.getNewValue());
        updateLocation(t.getRowValue().getBranch_no(), t.getNewValue());
    }

    private void updateContactInfo(int branchNo, String contactInfo) {
        try {
            MyConnection.connectDB();
            MyConnection.ExecuteStatement("update apple_branch set contact_info= '" + contactInfo + "' where branch_no = " + branchNo + ";");
            MyConnection.con.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void updateLocation(int branchNo, String location) {
        try {
            MyConnection.connectDB();
            MyConnection.ExecuteStatement("update apple_branch set location= '" + location + "' where branch_no = " + branchNo + ";");
            MyConnection.con.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnDeleteClicked(ActionEvent event) {
        tvBranch.setItems(dataList);
        ObservableList<AppleBranch> selectedRows = tvBranch.getSelectionModel().getSelectedItems();
        ArrayList<AppleBranch> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> {
            tvBranch.getItems().remove(row);
            deleteRow(row);
            tvBranch.refresh();
        });
        showDataAppleBranch();
    }

    private void deleteRow(AppleBranch row) {
        // TODO Auto-generated method stub

        try {
            System.out.println("delete from apple_branch where branch_no=" + row.getBranch_no() + ";");
            MyConnection.connectDB();
            MyConnection.ExecuteStatement("delete from apple_branch where branch_no=" + row.getBranch_no() + ";");
            MyConnection.con.close();
            System.out.println("Connection closed");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnInsertClicked(ActionEvent event) {
        try {
            MyConnection.connectDB();
            if (tfEnterBranchNo.getText().isEmpty() || tfEnterContactInfo.getText().isEmpty() || tfEnterLocation.getText().isEmpty()) {
                throw new NullPointerException();
            }
            int branchNo = Integer.parseInt(tfEnterBranchNo.getText());
            String location = tfEnterLocation.getText();
            String contactInfo = tfEnterContactInfo.getText();
            dataAppleBranch = new ArrayList<>();

            ArrayList<AppleBranch> searchResult = new ArrayList<>();
            MyConnection.connectDB();
            System.out.println("Connection \n\n\n");
            String SQL = "select * from apple_branch where branch_no=" + branchNo + ";";
            Statement stmt = MyConnection.con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            try {
                while (rs.next()) {
                    searchResult.add(new AppleBranch(Integer.parseInt(rs.getString(1)), rs.getString(3), rs.getString(2)));
                }
            } catch (Exception e) {
                System.out.println("Error in reading data");
            }
            rs.close();
            stmt.close();
            if (searchResult.size() != 0) {
                throw new SQLException();
            }
            SQL = "insert into apple_branch (branch_no, location, contact_info) values (" + branchNo + ",'" + location + "','" + contactInfo + "');";
            MyConnection.ExecuteStatement(SQL);
            MyConnection.con.close();
            getDataAppleBranch();
            showDataAppleBranch();
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Missing Info");
            alert.setContentText("Please fill all fields");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Info");
            alert.setContentText("Please enter a proper branch number");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Duplicate info");
            alert.setContentText("Make sure the entered branch number does not already exist in database");
            alert.showAndWait();
        } catch (Exception e) {
            System.out.println("Could not insert branch");
        }
    }



}
