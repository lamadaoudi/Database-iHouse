/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_ihouse_project_final_version;

import Classes.AvailableTechnicians;
import Classes.ReplacementParts;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Main
 */
public class RequestReplacementPartController implements Initializable {

    @FXML
    private Label labelEnterRepairID;
    @FXML
    private JFXTextField tfEnterRepairID;
    @FXML
    private JFXButton btnRequest;
    @FXML
    private TableView<ReplacementParts> tvParts;
    @FXML
    private TableColumn<ReplacementParts, String> colSerialNo;
    @FXML
    private TableColumn<ReplacementParts, String> colPartType;
    @FXML
    private TableColumn<ReplacementParts, Integer> colAmountInStock;
    @FXML
    private TableColumn<ReplacementParts, Double> colPrice;
    @FXML
    private TableColumn<ReplacementParts, String> colCompatibility;

    private int repairID;
    private ArrayList<ReplacementParts> dataParts;
    private static ObservableList<ReplacementParts> dataList;
    @FXML
    private Label labelCompatable;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tvParts.setVisible(false);
        labelCompatable.setVisible(false);
        fadeIn.setNode(tvParts);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.setCycleCount(1);
        fadeIn.setAutoReverse(false);
    }    

    @FXML
    private void tfEnterRepairIDClicked(ActionEvent event) throws Exception {
        try{
            repairID=Integer.parseInt(tfEnterRepairID.getText());
            ArrayList<Integer> searchResult = getData(repairID);
            if (searchResult.size() != 0) {
                getDataParts();
                showDataParts();
                tvParts.setVisible(true);
                labelCompatable.setVisible(true);
                fadeIn.playFromStart();
            } else {
                tvParts.setVisible(false);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid Value Entered");
                alert.setContentText("Could find repair job in database. Please enter a valid Repair_ID");
                alert.showAndWait();
            }
            
            
        }
        catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Value Entered");
            alert.setContentText("Please enter a proper value for the repair_id");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnRequestClicked(ActionEvent event) {
        ObservableList<ReplacementParts> selectedRows = tvParts.getSelectionModel().getSelectedItems();
        ArrayList<ReplacementParts> rows = new ArrayList<>(selectedRows);
        try{
            String serialNo=rows.get(0).getSerialNo();
            MyConnection.connectDB();
            updateOrInsertRelationship(serialNo);
            String SQL2= "update replacement_parts set amount_in_stock=amount_in_stock-1 where serial_no='"+serialNo+"';";
            MyConnection.ExecuteStatement(SQL2);
            MyConnection.con.close();
            getDataParts();
            showDataParts();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Part Requested");
            alert.setContentText("Thank you for requesting this part.");
            alert.showAndWait();
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Missing Info");
            alert.setContentText("Please select a part to request");
            alert.showAndWait();
        }
    }
    public void updateOrInsertRelationship (String serialNo){
        try{
            ArrayList<Integer> data = new ArrayList<>();
            String SQL = "select * from repNeedsrep R where R.repair_id=" + repairID +" and R.serial_no='"+serialNo+"';";
            Statement stmt = MyConnection.con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            try {
                while (rs.next()) {
                    data.add(Integer.parseInt(rs.getString(1)));
                }
            } catch (Exception e) {
                System.out.println("Error In loop");
            }
            rs.close();
            stmt.close();
            if(data.size()!=0){
                SQL="update repNeedsrep R set R.amount=R.amount+1 where R.repair_id="+repairID+" and R.serial_no='"+serialNo+"';";
                MyConnection.ExecuteStatement(SQL);
                System.out.println("The amount is increased by one");
            }
            else{
                SQL = "insert into RepNeedsRep (repair_id, serial_no) values ("+repairID+",'"+serialNo+"');";   
                MyConnection.ExecuteStatement(SQL);
            }
            
        }
        catch (Exception e){
            System.out.println("Could not fetch data");
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
            System.out.println("Could not fetch data");
        }
        rs.close();
        stmt.close();
        MyConnection.con.close();

        System.out.println("Connection closed" + data.size());
        return data;
    }
       private void getDataParts() throws SQLException, ClassNotFoundException {
        // TODO Auto-generated method stub
        dataParts = new ArrayList<>();
        String SQL;
        MyConnection.connectDB();
        System.out.println("Connection \n\n\n");
        SQL = "select * from replacement_parts R where R.amount_in_stock>0 and R.compatability=ALL (select D.device_Type from repairJob R, device D WHERE R.repair_id=D.repair_id AND R.repair_id="+repairID+");";
        Statement stmt = MyConnection.con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        try {

            while (rs.next()) {
                dataParts.add(new ReplacementParts(rs.getString(1),rs.getString(3),Integer.parseInt(rs.getString(2)), rs.getString(5), Double.parseDouble(rs.getString(4))));
            }
        } catch (Exception e) {
            System.out.println("Could not fetch data");
        }
        rs.close();
        stmt.close();
        MyConnection.con.close();
        System.out.println("Connection closed" + dataParts.size());
        dataList = FXCollections.observableArrayList(dataParts);
        System.out.println(dataList);
    }
    public void showDataParts() {
        colSerialNo.setCellValueFactory(new PropertyValueFactory<ReplacementParts, String>("SerialNo"));
        colAmountInStock.setCellValueFactory(new PropertyValueFactory<ReplacementParts, Integer>("AmountInStock"));
        colCompatibility.setCellValueFactory(new PropertyValueFactory<ReplacementParts, String>("Compatibility"));
        colPartType.setCellValueFactory(new PropertyValueFactory<ReplacementParts, String>("PartType"));
        colPrice.setCellValueFactory(new PropertyValueFactory<ReplacementParts, Double>("Price"));
        tvParts.setItems(dataList); 
    }
    
    
    
    private FadeTransition fadeIn = new FadeTransition(
        Duration.millis(300)
    );
    
      
    
}
