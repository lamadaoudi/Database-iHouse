/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_ihouse_project_final_version;

import Classes.AvailableTechnicians;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;

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
    @FXML
    private VBox vboxLabelsCustomerInfo;
    @FXML
    private VBox vBoxTextFieldsCustomersInfo;
    @FXML
    private JFXTextField tfCustomerName;
    @FXML
    private JFXTextField tfCustomerAddress;
    @FXML
    private JFXTextField tfPrimaryCustomerPhone;
    @FXML
    private JFXTextField tfSecondaryCustomerPhone;
    @FXML
    private JFXTextField tfSecondaryCustomerPhone2;
    @FXML
    private JFXButton btnAddPhoneNum;

    private int countPhones = 0;
    private int countProblems = 0;
    private int currentRepairID;
    private int currentCustomerID;
    private String currentAreaOfSpecialty;
    private boolean alreadyRegisteredCustomer = false;
    private boolean customerSuccess=false;
    private boolean problemSucess=false;
    private boolean deviceSucces=false;
    private boolean technicianSucess=false;
    private ToggleGroup toggle= new ToggleGroup();
    private ArrayList<AvailableTechnicians> dataTechnicians;
    private static ObservableList<AvailableTechnicians> dataList;

    
    @FXML
    private JFXTabPane tabPane;
    @FXML
    private Tab tab2;
    @FXML
    private JFXTextArea taProblem1;
    @FXML
    private JFXButton btnAddProblem;
    @FXML
    private Label labelProblem2;
    @FXML
    private JFXTextArea taProblem2;
    @FXML
    private Label labelProblem3;
    @FXML
    private JFXTextArea taProblem3;
    @FXML
    private Label labelProblem4;
    @FXML
    private JFXTextArea taProblem4;
    @FXML
    private JFXButton btnContinueTab2;
    @FXML
    private Tab tab3;
    @FXML
    private Tab tab4;
    @FXML
    private HBox hboxPrice1;
    @FXML
    private JFXTextField tfPrice1;
    @FXML
    private HBox hboxPrice2;
    @FXML
    private JFXTextField tfPrice2;
    @FXML
    private HBox hboxPrice3;
    @FXML
    private JFXTextField tfPrice3;
    @FXML
    private HBox hboxPrice4;
    @FXML
    private JFXTextField tfPrice4;
    @FXML
    private Tab tab1;
    @FXML
    private JFXTextField tfSerialNumber;
    @FXML
    private JFXTextField tfModelNumer;
    @FXML
    private JFXRadioButton radioiPhone;
    @FXML
    private JFXRadioButton radioiPad;
    @FXML
    private JFXRadioButton radioMacBook;
    @FXML
    private JFXRadioButton radioAppleWatch;
    @FXML
    private JFXTextField tfSpecifications;
    @FXML
    private JFXToggleButton toggleWarranty;
    @FXML
    private JFXTextField tfCondition;
    @FXML
    private JFXButton btnContinueTab3;
    @FXML
    private TableView<AvailableTechnicians> technicianTV;
    @FXML
    private TableColumn<AvailableTechnicians, Integer> colID;
    @FXML
    private TableColumn<AvailableTechnicians, String> colName;
    @FXML
    private TableColumn<AvailableTechnicians, Integer> colNumberOfActiveJobs;
    @FXML
    private JFXButton btnDone;
    @FXML
    private JFXButton btnAssign;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Tab 1
        labelRegistered.setVisible(false);
        vboxLabelsCustomerInfo.setVisible(false);
        vBoxTextFieldsCustomersInfo.setVisible(false);
        btnAddPhoneNum.setVisible(false);
        tab2.setDisable(true);
        tab3.setDisable(true);
        tab4.setDisable(true);
        fadeIn.setNode(labelRegistered);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.setCycleCount(1);
        fadeIn.setAutoReverse(false);
        // Tab 2
        labelProblem2.setVisible(false);
        taProblem2.setVisible(false);
        labelProblem3.setVisible(false);
        labelProblem4.setVisible(false);
        taProblem3.setVisible(false);
        taProblem4.setVisible(false);
        hboxPrice4.setVisible(false);
        hboxPrice2.setVisible(false);
        hboxPrice3.setVisible(false);
        // Tab 3
        radioiPhone.setToggleGroup(toggle);
        radioiPad.setToggleGroup(toggle);
        radioMacBook.setToggleGroup(toggle);
        radioAppleWatch.setToggleGroup(toggle);
        // Tab 4
        try {
            getDataTechnicians();
            setTableEditable();
            showTechnicians();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ******************************************************************************************* TAB 1 ********************************************************************************
     */
    @FXML
    private void tfIDCustomerClicked(ActionEvent event) {
        try {
            ArrayList<Integer> searchResult = getData(Integer.parseInt(tfIDCustomer.getText()));
            if (searchResult.size() != 0) {
                labelRegistered.setText("It seems that this customer is already registered, click continue...");
                labelRegistered.setVisible(true);
                fadeIn.playFromStart();
                alreadyRegisteredCustomer = true;
            } else {
                vboxLabelsCustomerInfo.setVisible(true);
                vBoxTextFieldsCustomersInfo.setVisible(true);
                labelRegistered.setText("Thank you for choosing iHouse");
                labelRegistered.setVisible(true);
                fadeIn.playFromStart();
                btnAddPhoneNum.setVisible(true);
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Improper ID number");
            alert.setContentText("Please enter a proper ID number");
            alert.showAndWait();
        } catch (Exception e) {
            System.out.println("Connection not established");
        }
    }

    private ArrayList<Integer> getData(int id) throws SQLException, ClassNotFoundException {
        // TODO Auto-generated method stub
        ArrayList<Integer> data = new ArrayList<>();
        String SQL;
        MyConnection.connectDB();
        System.out.println("Connection \n\n\n");
        SQL = "select c.customer_id from customer c where c.customer_id=" + id;
        Statement stmt = MyConnection.con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        try {
            while (rs.next()) {
                data.add(Integer.parseInt(rs.getString(1)));
            }
        } catch (Exception e) {
            System.out.println("Could not read customers");
        }
        rs.close();
        stmt.close();
        MyConnection.con.close();

        System.out.println("Connection closed" + data.size());
        return data;
    }

    @FXML
    private void btnAddPhoneClicked(ActionEvent event) {
        if (countPhones == 0) {
            tfSecondaryCustomerPhone.setVisible(true);
            countPhones++;
        } else if (countPhones == 1) {
            tfSecondaryCustomerPhone2.setVisible(true);
        }

    }

    @FXML
    private void btnContinuetab1Clicked(ActionEvent event) {
        try {
            /**
             * ** Adding repair Job **
             */
            String SQL;
            MyConnection.connectDB();
            System.out.println("Connection \n\n\n");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            SQL = "insert into repairJob (recieved_date) values('" + formatter.format(date) + "');";
            MyConnection.ExecuteStatement(SQL);
            MyConnection.con.close();
            System.out.println("Connection closed");
            System.out.println("Added repair job successfully");
            String SQLToFindCurrentJob;
            MyConnection.connectDB();
            System.out.println("Connection \n\n\n");
            SQL = "select max(repair_id) from repairJob";
            Statement stmt = MyConnection.con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                currentRepairID = Integer.parseInt(rs.getString(1));
            }
            rs.close();
            stmt.close();
            MyConnection.con.close();
            System.out.println("Connection closed");
            System.out.println("Current repair ID is: " + currentRepairID);
            /**
             * ************ Done Adding repair Job*****************
             */

            /**
             * ************ Adding Customer ***********************
             */
            try {
                if (!alreadyRegisteredCustomer) {
                    addCustomer();
                    addCustomerPhones();
                } else {
                    addCustReqRep();
                }
            } catch (Exception e) {
                System.out.println("Could not add customer");
            }

        } catch (Exception e) {
            System.out.println("Could not add repair Job");
        }
        if(customerSuccess==true){
            tabPane.getSelectionModel().selectNext();
            tab2.setDisable(false);
            tab1.setDisable(true);
        }
        else{
            try{
                MyConnection.connectDB();
                String Abort_SQL = "delete from repairJob where repair_id="+currentRepairID+";";
                MyConnection.ExecuteStatement(Abort_SQL);
                MyConnection.con.close();
            }
            catch(Exception e){
                System.out.println("Could not establish connection");
            }
        }
    }

    public void addCustomer() throws Exception {
        MyConnection.connectDB();
        try {
            int customerID = Integer.parseInt(tfIDCustomer.getText());
            String customerName = tfCustomerName.getText();
            String customerAddress = tfCustomerAddress.getText();
            if (customerAddress.length() == 0 || customerName.length() == 0) {
                throw new NullPointerException();
            }
            currentCustomerID=customerID;
            String SQL = "insert into customer (customer_id,customer_name,address) values (" + customerID + ",'" + customerName + "','" + customerAddress + "');";
            MyConnection.ExecuteStatement(SQL);
            String SQL2= "insert into CustReqRep (repair_id, id_num) values("+currentRepairID+","+customerID+");";
            MyConnection.ExecuteStatement(SQL2);
            MyConnection.con.close();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Improper ID number");
            alert.setContentText("Please enter a proper ID number");
            alert.showAndWait();
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Empty Fields");
            alert.setContentText("Please enter all fields");
            alert.showAndWait();
        } finally {
            MyConnection.con.close();
        }
    }

    public void addCustomerPhones() throws Exception {
        MyConnection.connectDB();

        try {
            
            int customerID = Integer.parseInt(tfIDCustomer.getText());
            String CustomerPhone1 = tfPrimaryCustomerPhone.getText();
            if (CustomerPhone1.isEmpty()) {
                throw new NullPointerException();
            }
            String CustomerPhone2 = tfSecondaryCustomerPhone.getText();
            String CustomerPhone3 = tfSecondaryCustomerPhone2.getText();
            if (!CustomerPhone1.isEmpty() && CustomerPhone2.isEmpty() && CustomerPhone3.isEmpty()) {
                String SQL = "insert into customer_phone (customer_id, phone_num) values (" + customerID + "," + Integer.parseInt(CustomerPhone1) + ");";
                MyConnection.ExecuteStatement(SQL);
                customerSuccess=true;
            } else if (!CustomerPhone1.isEmpty() && !CustomerPhone2.isEmpty() && CustomerPhone3.isEmpty()) {
                String SQL1 = "insert into customer_phone (customer_id, phone_num) values (" + customerID + "," + Integer.parseInt(CustomerPhone1) + ");";
                String SQL2 = "insert into customer_phone (customer_id, phone_num) values (" + customerID + "," + Integer.parseInt(CustomerPhone2) + ");";
                if (Integer.parseInt(CustomerPhone1) == Integer.parseInt(CustomerPhone2)) {
                    showAlertViolation();
                } else {
                    MyConnection.ExecuteStatement(SQL1);
                    MyConnection.ExecuteStatement(SQL2);
                    customerSuccess=true;
                }
            } else if (!CustomerPhone1.isEmpty() && !CustomerPhone2.isEmpty() && !CustomerPhone3.isEmpty()) {
                String SQL1 = "insert into customer_phone (customer_id, phone_num) values (" + customerID + "," + Integer.parseInt(CustomerPhone1) + ");";
                String SQL2 = "insert into customer_phone (customer_id, phone_num) values (" + customerID + "," + Integer.parseInt(CustomerPhone2) + ");";
                String SQL3 = "insert into customer_phone (customer_id, phone_num) values (" + customerID + "," + Integer.parseInt(CustomerPhone3) + ");";
                if (Integer.parseInt(CustomerPhone1) == Integer.parseInt(CustomerPhone2) || Integer.parseInt(CustomerPhone2) == Integer.parseInt(CustomerPhone3) || Integer.parseInt(CustomerPhone1) == Integer.parseInt(CustomerPhone3)) {
                    showAlertViolation();
                } else {
                    MyConnection.ExecuteStatement(SQL1);
                    MyConnection.ExecuteStatement(SQL2);
                    MyConnection.ExecuteStatement(SQL3);
                    customerSuccess=true;
                }
            }
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Phone Number");
            alert.setContentText("Please enter at least one phone number");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Improper phone number");
            alert.setContentText("Please enter a proper value for phone number, avoid using + symbol");
            alert.showAndWait();
        } finally {
            MyConnection.con.close();
        }
    }

    public void showAlertViolation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Duplicate Data");
        alert.setContentText("Make sure phone numbers are unique!");
        alert.showAndWait();
    }
    public void addCustReqRep() throws Exception{
        MyConnection.connectDB();
        try{
            currentCustomerID=Integer.parseInt(tfIDCustomer.getText());
            int customerID = Integer.parseInt(tfIDCustomer.getText());
            String SQL2= "insert into CustReqRep (repair_id, id_num) values("+currentRepairID+","+customerID+");";
            MyConnection.ExecuteStatement(SQL2);
            customerSuccess=true;
        }
        catch (NumberFormatException e){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Improper ID number");
            alert.setContentText("Please enter a proper ID number");
            alert.showAndWait();
        }
        MyConnection.con.close();
        System.out.println("Added relationship successfully");
    }



    /**
     * ************************************************************************************************ TAB 1 DONE *******************************************************************************
     */
    
        /**
     * ************************************************************************************************ TAB 2 *******************************************************************************
     */
    @FXML
    private void btnAddProblemClicked(ActionEvent event) {
        if (countProblems==0){
            labelProblem2.setVisible(true);
            taProblem2.setVisible(true);
            hboxPrice2.setVisible(true);
            countProblems++;
        }
        else if (countProblems==1){
            labelProblem3.setVisible(true);
            taProblem3.setVisible(true);
            hboxPrice3.setVisible(true);
            countProblems++;
        }
        else if (countProblems==2){
            labelProblem4.setVisible(true);
            taProblem4.setVisible(true);
            hboxPrice4.setVisible(true);
            countProblems++;
        }

    }

    @FXML
    private void btnContinuetab2Clicked(ActionEvent event) throws Exception{
        MyConnection.connectDB();
       try{ 
            if (taProblem1.getText().isEmpty() || tfPrice1.getText().isEmpty() || (!taProblem2.getText().isEmpty() && tfPrice2.getText().isEmpty()) || (!taProblem3.getText().isEmpty() && tfPrice3.getText().isEmpty()) || (!taProblem4.getText().isEmpty() && tfPrice4.getText().isEmpty())) 
                throw new NullPointerException();
            if(!taProblem1.getText().isEmpty()){
                String SQL = "insert into repair_problems (problem, repair_id,price) values('"+taProblem1.getText()+"',"+currentRepairID+","+Double.parseDouble(tfPrice1.getText())+");";
                MyConnection.ExecuteStatement(SQL);
            }
            if(!taProblem2.getText().isEmpty()){
                String SQL2= "insert into repair_problems (problem, repair_id,price) values('"+taProblem2.getText()+"',"+currentRepairID+","+Double.parseDouble(tfPrice2.getText())+");";
                MyConnection.ExecuteStatement(SQL2);
            }
            if( !taProblem3.getText().isEmpty()){
                String SQL3 = "insert into repair_problems (problem, repair_id,price) values('"+taProblem3.getText()+"',"+currentRepairID+","+Double.parseDouble(tfPrice3.getText())+");";
                MyConnection.ExecuteStatement(SQL3);
            }
            if( !taProblem4.getText().isEmpty()){
                String SQL4 = "insert into repair_problems (problem, repair_id,price) values('"+taProblem4.getText()+"',"+currentRepairID+","+Double.parseDouble(tfPrice4.getText())+");";
                MyConnection.ExecuteStatement(SQL4);
            }
            problemSucess=true;
            if (problemSucess){
                tabPane.getSelectionModel().selectNext();
                tab3.setDisable(false);
                tab2.setDisable(true);
            }
       }
       catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Missing Info");
            alert.setContentText("Please enter problems specifications and estimated prices");
            alert.showAndWait();
       }
       catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Info");
            alert.setContentText("Please enter proper values of price, avoid using symbols");
            alert.showAndWait();
       }
       MyConnection.con.close();
    }


    /**
     * ************************************************************************************************ TAB 2 DONE *******************************************************************************
     */
    /**
     * ************************************************************************************************ TAB 3 *******************************************************************************
     */
    @FXML
    private void btnContinuetab3Clicked(ActionEvent event) throws Exception {
        MyConnection.connectDB();

        try{
            if(tfSerialNumber.getText().isEmpty() || tfModelNumer.getText().isEmpty() || tfCondition.getText().isEmpty() || toggle.getSelectedToggle()==null || tfSpecifications.getText().isEmpty())
                throw new NullPointerException();
            String deviceType=""; //HERE IS THE AREA OF SPECIALTY
           
            if(radioAppleWatch.isSelected())
                deviceType="Apple Watch";
            else if(radioMacBook.isSelected())
                deviceType="MacBook";
            else if(radioiPhone.isSelected())
                deviceType="iPhone";
            else if(radioiPad.isSelected())
                deviceType="iPad";
            currentAreaOfSpecialty= String.valueOf(deviceType); 
            int warranty= toggleWarranty.isSelected()?1:0;
            System.out.println("Device Type:"+deviceType+" warranty: "+warranty);
            String SQL="insert into device (serial_no, device_type, model_no, warranty, specs, device_condition, repair_id, customer_id) values ('"+tfSerialNumber.getText()+"','"+deviceType+"','"+tfModelNumer.getText()+"',"+warranty+",'"+tfSpecifications.getText()+"','"+tfCondition.getText()+"',"+currentRepairID+","+currentCustomerID+");";
            System.out.println(SQL);
            MyConnection.ExecuteStatement(SQL);
            deviceSucces=true;
        }
        catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Missing Info");
            alert.setContentText("Please fill all fields");
            alert.showAndWait();
        }
        MyConnection.con.close();
        if (deviceSucces){
                getDataTechnicians();
                showTechnicians();
                tabPane.getSelectionModel().selectNext();
                tab4.setDisable(false);
                tab3.setDisable(true);

        }
    }

        /**
     * ************************************************************************************************ TAB 3 DONE *******************************************************************************
     */
    /**
     *         /**
     * ************************************************************************************************ TAB 4 ********************************************************************************/
    
    @FXML
    private void btnDoneClicked(ActionEvent event) {
        if(technicianSucess){
                Stage stage = (Stage) btnDone.getScene().getWindow();
                stage.close();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Missing Info");
            alert.setContentText("Please select a technician");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnAssignClicked(ActionEvent event) throws Exception{
        ObservableList<AvailableTechnicians> selectedRows = technicianTV.getSelectionModel().getSelectedItems();
        ArrayList<AvailableTechnicians> rows = new ArrayList<>(selectedRows);
        try{
            int technician_id=rows.get(0).getTechnician_id();
            MyConnection.connectDB();
            String SQL = "update repairJob R set R.technician_id="+technician_id+" where R.repair_id="+currentRepairID+";";
            MyConnection.ExecuteStatement(SQL);
            MyConnection.con.close();
            getDataTechnicians();
            showTechnicians();
            technicianSucess=true;
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Missing Info");
            alert.setContentText("Please select a technician");
            alert.showAndWait();
        }
    }
    private void getDataTechnicians() throws SQLException, ClassNotFoundException {
        // TODO Auto-generated method stub
        dataTechnicians = new ArrayList<>();
        String SQL;
        MyConnection.connectDB();
        System.out.println("Connection \n\n\n");
        SQL = "select T.id_num, E.eName, COUNT(R.repair_id)\n" +
        "FROM Technician T, repairJob R, Employee E\n" +
        "WHERE T.id_num = E.id_num AND T.id_num = R.technician_id\n" +
        "AND R.job_status<>'closed' AND R.job_status<>'finished' AND T.area_of_specialty='"+currentAreaOfSpecialty+"'\n" +
        "GROUP BY R.technician_id\n" +
        "UNION\n" +
        "SELECT T.id_num, E.eName,  0 AS 'NUMBER_OF_ASSOCIATED JOBS'\n" +
        "FROM Technician T, Employee E\n" +
        "WHERE T.id_num = E.id_num AND T.area_of_specialty='"+currentAreaOfSpecialty+"' AND T.id_num NOT IN\n" +
        "( SELECT R.technician_id FROM repairJob R WHERE R.technician_id AND (R.job_status<>'closed' AND R.job_status<>'finished'));";
        Statement stmt = MyConnection.con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        try {

            while (rs.next()) {
                dataTechnicians.add(new AvailableTechnicians(
                        Integer.parseInt(rs.getString(1)),
                        rs.getString(2),
                        Integer.parseInt(rs.getString(3))));
            }
        } catch (Exception e) {
            System.out.println("Could not find technicians");
        }
        rs.close();
        stmt.close();
        MyConnection.con.close();

        System.out.println("Connection closed" + dataTechnicians.size());
        dataList = FXCollections.observableArrayList(dataTechnicians);
        System.out.println(dataList);
    } 
        private void setTableEditable(){
        technicianTV.setEditable(true);
        colName.setCellFactory(TextFieldTableCell.<AvailableTechnicians>forTableColumn());
        colID.setCellFactory(TextFieldTableCell.<AvailableTechnicians, Integer>forTableColumn( new IntegerStringConverter()));
        colNumberOfActiveJobs.setCellFactory(TextFieldTableCell.<AvailableTechnicians, Integer>forTableColumn( new IntegerStringConverter()));
}
            
    public void showTechnicians() {
        colID.setCellValueFactory(new PropertyValueFactory<AvailableTechnicians, Integer>("Technician_id"));
        colName.setCellValueFactory(new PropertyValueFactory<AvailableTechnicians, String>("Technician_Name"));
        colNumberOfActiveJobs.setCellValueFactory(new PropertyValueFactory<AvailableTechnicians, Integer>("NumberOfActiveJobs"));
        technicianTV.setItems(dataList);
    }
            /**
     * ************************************************************************************************ TAB 4 DONE *******************************************************************************
     */
   private FadeTransition fadeIn = new FadeTransition(
        Duration.millis(300)
    );
      
}
