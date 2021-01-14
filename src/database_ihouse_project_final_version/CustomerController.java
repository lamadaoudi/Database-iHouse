package database_ihouse_project_final_version;

import Classes.Customer;
import Classes.Customer_Phone;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.util.converter.IntegerStringConverter;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
    public JFXButton btnInsert1;
    public Label alert3;
    public Label alert2;
    public Label alert4;
    public Label emptyalert2;
    public Label emptyalert1;
    public Label emptyalert3;
    public Label emptyalert4;
    public JFXButton btnAddPhoneNum;
    public Label takenID;
    static int duplicatedID = 0;
    public TableView<Customer_Phone> tvPhone;
    public TableColumn<Customer_Phone, Integer> colIDphone;
    public TableColumn colNamephone;

    public TableColumn colPhone;
    public JFXTextField tfSearchPhone;
    public Label NotFoundID;
    public JFXTextField IDphonetf;
    public JFXTextField insertphonetf;
    public Label phonenuminvalid;
    public Label requiredphonenum;
    public Label idphonerequired;
    public Label invalidIDphone;
    @FXML
    private JFXComboBox<String> combo;
    @FXML
    private Label alert1;
    @FXML
    private TableView<Customer> tv;
    @FXML
    private TableColumn<Customer, Integer> colID;
    @FXML
    private TableColumn<Customer, String> colName;
    @FXML
    private TableColumn<Customer, String> colAdd;
    @FXML
    private JFXTextField tfSearch;
    @FXML
    private JFXTextField tfID;
    @FXML
    private JFXTextField tfName;
    @FXML
    private JFXTextField tfAdd;
    @FXML
    private JFXButton btnInsert;
    @FXML
    private Tab tab1;
    @FXML
    private Tab tab2;
    @FXML
    private JFXTextField tfPrimaryCustomerPhone;
    @FXML
    private JFXTextField tfSecondaryCustomerPhone;
    @FXML
    private JFXTextField tfSecondaryCustomerPhone2;
    @FXML
    private JFXTabPane tabPane;
    private ArrayList<Customer> data;
    static ObservableList<Customer> dataList;
    static ObservableList<Customer_Phone> dataListInfo;
    private ArrayList<Customer_Phone> dataInfo;
    private int countPhones = 0;
    static int InsertedSuccessfully = 0;
    private int Phone_inserted=0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            getData();
            setTableEditable();
            showCustomers();
            getContactInfo();
            setTableEditable2();
            showCustomerContactInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ObservableList<String> sortby = FXCollections.observableArrayList("Name", "ID", "Recently");
        if (combo != null) {
            combo.setItems(sortby);
        }

    }

    private void getContactInfo() throws SQLException, ClassNotFoundException {
        // TODO Auto-generated method stub
        dataInfo = new ArrayList<>();
        String SQL;
        MyConnection.connectDB();
        SQL = "select cp.customer_id , c.customer_name, cp.phone_num from customer_phone cp, customer c\n" +
                " where cp.customer_id = c.customer_id ;";
        Statement stmt = MyConnection.con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        try {
            Customer_Phone cust;
            while (rs.next()) {
                cust = new Customer_Phone(Integer.parseInt(rs.getString(1)),
                        rs.getString(2),
                        Integer.parseInt(rs.getString(3)));

                dataInfo.add(cust);
            }
        } catch (Exception e) {

            System.out.println("Error In Info Loop");
            System.exit(1);
        }

        rs.close();
        stmt.close();
        MyConnection.con.close();
        System.out.println("Info is : " + dataInfo.size());
        dataListInfo = FXCollections.observableArrayList(dataInfo);

    }

    private void setTableEditable2() {
        tvPhone.setEditable(true);
        colNamephone.setCellFactory(TextFieldTableCell.forTableColumn());
        colPhone.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }

    public void showCustomerContactInfo() {
        colIDphone.setCellValueFactory(new PropertyValueFactory<Customer_Phone, Integer>("customer_id"));
        colNamephone.setCellValueFactory(new PropertyValueFactory<Customer_Phone, String>("customer_name"));
        colPhone.setCellValueFactory(new PropertyValueFactory<Customer_Phone, Integer>("phone_num"));

        searchContactInfo();
    }

    void searchContactInfo() {
// Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Customer_Phone> filteredData = new FilteredList<>(dataListInfo, b -> true);
// 2. Set the filter Predicate whenever the filter changes.

        tfSearchPhone.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(info -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if (info.getCustomer_name()!=null && info.getCustomer_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else // Does not match.

                    if (String.valueOf(info.getCustomer_id()).indexOf(lowerCaseFilter) != -1)
                        return true;
                    else
                        return String.valueOf(info.getPhone_num()).indexOf(lowerCaseFilter) != -1;

            });


        });


        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Customer_Phone> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tvPhone.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tvPhone.setItems(sortedData);
    }

    private void getData() throws SQLException, ClassNotFoundException {
        // TODO Auto-generated method stub
        data = new ArrayList<>();
        String SQL;
        MyConnection.connectDB();
        System.out.println("Connection \n\n\n");
        SQL = "select * from customer order by customer_id";
        Statement stmt = MyConnection.con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        try {
            while (rs.next()) {
                data.add(new Customer(
                        Integer.parseInt(rs.getString(1)),
                        rs.getString(2),
                        rs.getString(3))

                );

            }


        } catch (Exception e) {
            System.exit(1);
        }
        rs.close();
        stmt.close();
        MyConnection.con.close();
        System.out.println("Connection closed" + data.size());
        dataList = FXCollections.observableArrayList(data);
        System.out.println(dataList);

    }


    public void showCustomers() {
        colID.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customer_id"));
        colName.setCellValueFactory(new PropertyValueFactory<Customer, String>("customer_name"));
        colAdd.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        search();
    }


    private void setTableEditable() {
        tv.setEditable(true);
        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colAdd.setCellFactory(TextFieldTableCell.forTableColumn());
        //colPhone.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        System.err.println("Table is editable now");
    }

    public void updateName(int id, String name) {
        setTableEditable();
        try {
            System.out.println("update customer set customer_name = '" + name + "' where customer_id = " + id);
            MyConnection.connectDB();
            MyConnection.ExecuteStatement("update customer set customer_name = '" + name + "' where customer_id = " + id + ";");
            MyConnection.con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void editName(TableColumn.CellEditEvent<Customer, String> t) {
        t.getTableView().getItems().get(
                t.getTablePosition().getRow()).setCustomer_name(t.getNewValue());
        updateName(t.getRowValue().getCustomer_id(), t.getNewValue());
    }

    public void editAdd(TableColumn.CellEditEvent<Customer, String> t) {
        t.getTableView().getItems().get(
                t.getTablePosition().getRow()).setAddress(t.getNewValue());
        updateAdd(t.getRowValue().getCustomer_id(), t.getNewValue());
    }

    public void updateAdd(int id, String address) {
        setTableEditable();
        try {
            System.out.println("update customer set address = '" + address + "' where customer_id = " + id);
            MyConnection.connectDB();
            MyConnection.ExecuteStatement("update customer set address = '" + address + "' where customer_id = " + id + ";");
            MyConnection.con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnInsertClicked(ActionEvent event) throws Exception {

        boolean nonEmptyEntries = !tfName.getText().isEmpty() &&
                !tfID.getText().isEmpty() &&
                !tfAdd.getText().isEmpty();
        if (nonEmptyEntries) {
            Customer rc = new Customer(
                    Integer.parseInt(tfID.getText()),
                    tfName.getText(),
                    tfAdd.getText());
            insertData(rc);
            if (InsertedSuccessfully == 1) {
                dataList.add(rc);

                insertPhones();
                tfID.clear();
                tfName.clear();
                tfAdd.clear();
                tfPrimaryCustomerPhone.clear();
                if (tfSecondaryCustomerPhone != null) tfSecondaryCustomerPhone.clear();
                if (tfSecondaryCustomerPhone2 != null) tfSecondaryCustomerPhone2.clear();
                tabPane.getSelectionModel().selectFirst();
            }
        } else {
            if (tfID.getText().isEmpty())
                emptyalert1.setVisible(true);
            if (tfName.getText().isEmpty())
                emptyalert2.setVisible(true);
            if (tfAdd.getText().isEmpty())
                emptyalert3.setVisible(true);
            if (tfPrimaryCustomerPhone.getText().isEmpty()) {
                btnAddPhoneNum.setVisible(false);
                emptyalert4.setVisible(true);
            }
        }

    }

    private void insertData(Customer rc) {
        InsertedSuccessfully = 0;
        if (!alert1.isVisible() && !alert2.isVisible() && !alert3.isVisible() && !alert4.isVisible()) {
            System.out.println("Your  Allowed to add");
            try {
                MyConnection.connectDB();
                System.out.println("Insert into customer (customer_id,customer_name,address) values(" + rc.getCustomer_id() + ",'" + rc.getCustomer_name() + "','" + rc.getAddress() + "')");
                MyConnection.ExecuteStatement("Insert into customer (customer_id,customer_name,address) values(" + rc.getCustomer_id() + ",'" + rc.getCustomer_name() + "','" + rc.getAddress() + "')");
                MyConnection.con.close();
                System.out.println("Connection closed");
                if (duplicatedID == 1)
                    takenID.setVisible(true);
                else {

                    InsertedSuccessfully = 1;
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    void search() {
// Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Customer> filteredData = new FilteredList<>(dataList, b -> true);
// 2. Set the filter Predicate whenever the filter changes.
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(customer -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (customer.getCustomer_name() != null && customer.getCustomer_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else // Does not match.
                    if (customer.getAddress() != null && customer.getAddress().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    } else return String.valueOf(customer.getCustomer_id()).indexOf(lowerCaseFilter) != -1;
            });
        });
        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Customer> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tv.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tv.setItems(sortedData);

    }

    public void SortDrag(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String selected = combo.getSelectionModel().getSelectedItem().toString();
        data = new ArrayList<>();
        String SQL;
        MyConnection.connectDB();
        if (selected.equals("Name")) {
            SQL = "select * from customer  order by customer_name";
            Statement stmt = MyConnection.con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            try {
                while (rs.next()) {
                    data.add(new Customer(
                            Integer.parseInt(rs.getString(1)),
                            rs.getString(2),
                            rs.getString(3))

                    );

                }
            } catch (Exception e) {
                System.out.println("Error In loop");
                System.exit(1);
            }
            rs.close();
            stmt.close();
        } else if (selected.equals("ID")) {
            SQL = "select * from customer  order by customer_id";
            Statement stmt = MyConnection.con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            try {
                while (rs.next()) {
                    data.add(new Customer(
                            Integer.parseInt(rs.getString(1)),
                            rs.getString(2),
                            rs.getString(3))

                    );

                }
            } catch (Exception e) {
                System.out.println("Error In loop");
                System.exit(1);
            }
            rs.close();
            stmt.close();
        }

        MyConnection.con.close();
        System.out.println("Connection closed" + data.size());
        dataList = FXCollections.observableArrayList(data);
        System.out.println(dataList);
        tv.setItems(dataList);
        showCustomers();
    }

    public void btnAddPhoneClicked(ActionEvent actionEvent) {
        if (countPhones == 0) {
            tfSecondaryCustomerPhone.setVisible(true);
            countPhones++;
        } else if (countPhones == 1) {
            tfSecondaryCustomerPhone2.setVisible(true);
        }
    }

    private void insertPhones() throws Exception {
        MyConnection.connectDB();

        try {
            int customerID = Integer.parseInt(tfID.getText());
            String CustomerPhone1 = tfPrimaryCustomerPhone.getText();
            String CustomerPhone2 = tfSecondaryCustomerPhone.getText();
            String CustomerPhone3 = tfSecondaryCustomerPhone2.getText();
            if (!CustomerPhone1.isEmpty() && CustomerPhone2.isEmpty() && CustomerPhone3.isEmpty()) {
                System.out.println("\"insert into customer_phone (emp_id, phone_num) values ");
                String SQL = "insert into customer_phone (customer_id, phone_num) values (" + customerID + "," + Integer.parseInt(CustomerPhone1) + ");";
                MyConnection.ExecuteStatement(SQL);
                Phone_inserted=1;
            } else if (!CustomerPhone1.isEmpty() && !CustomerPhone2.isEmpty() && CustomerPhone3.isEmpty()) {
                System.out.println("\"insert into customer_phone (emp_id, phone_num) values ");
                String SQL1 = "insert into customer_phone (customer_id, phone_num) values (" + customerID + "," + Integer.parseInt(CustomerPhone1) + ");";
                String SQL2 = "insert into customer_phone (customer_id, phone_num) values (" + customerID + "," + Integer.parseInt(CustomerPhone2) + ");";
                if (Integer.parseInt(CustomerPhone1) == Integer.parseInt(CustomerPhone2)) {
                    showAlertViolation();

                } else {
                    MyConnection.ExecuteStatement(SQL1);
                    MyConnection.ExecuteStatement(SQL2);
                    Phone_inserted=1;
                }
            } else if (!CustomerPhone1.isEmpty() && !CustomerPhone2.isEmpty() && !CustomerPhone3.isEmpty()) {
                System.out.println("\"insert into customer_phone (emp_id, phone_num) values ");
                String SQL1 = "insert into customer_phone (customer_id, phone_num) values (" + customerID + "," + Integer.parseInt(CustomerPhone1) + ");";
                String SQL2 = "insert into customer_phone (customer_id, phone_num) values (" + customerID + "," + Integer.parseInt(CustomerPhone2) + ");";
                String SQL3 = "insert into customer_phone (customer_id, phone_num) values (" + customerID + "," + Integer.parseInt(CustomerPhone3) + ");";
                if (Integer.parseInt(CustomerPhone1) == Integer.parseInt(CustomerPhone2) || Integer.parseInt(CustomerPhone2) == Integer.parseInt(CustomerPhone3) || Integer.parseInt(CustomerPhone1) == Integer.parseInt(CustomerPhone3)) {
                    showAlertViolation();
                } else {
                    System.out.println("\"insert into customer_phone (emp_id, phone_num) values ");
                    MyConnection.ExecuteStatement(SQL1);
                    MyConnection.ExecuteStatement(SQL2);
                    MyConnection.ExecuteStatement(SQL3);
                    Phone_inserted=1;
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
            getData();
            showCustomers();
            getContactInfo();
            showCustomerContactInfo();
        }
    }

    public void showAlertViolation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Duplicate Data");
        alert.setContentText("Make sure phone numbers are unique!");
        alert.showAndWait();
    }

    public void tfIDClicked(KeyEvent actionEvent) {
        emptyalert1.setVisible(false);
        alert1.setVisible(false);
        takenID.setVisible(false);
        if (!tfID.getText().isEmpty()) {
            try {
                emptyalert1.setVisible(false);
                Integer.parseInt(tfID.getText());
                alert1.setVisible(false);
            } catch (NumberFormatException e) {
                //e.printStackTrace();
                alert1.setVisible(true);
            }
        } else
            emptyalert1.setVisible(true);
    }

    public void tfNameClicked(KeyEvent keyEvent) {
        emptyalert2.setVisible(false);
        alert2.setVisible(false);

        if (!tfName.getText().isEmpty()) {
            emptyalert2.setVisible(false);
            if (tfName.getText().matches(".*\\d+.*")) {
                alert2.setVisible(true);
            } else {
                alert2.setVisible(false);
            }
        } else
            emptyalert2.setVisible(true);
    }

    public void tfAddClicked(KeyEvent keyEvent) {
        emptyalert3.setVisible(false);
        alert3.setVisible(false);

        if (!tfAdd.getText().isEmpty()) {
            emptyalert3.setVisible(false);
            if (tfAdd.getText().matches(".*[a-zA-Z]+.*")) {
                alert3.setVisible(false);
            } else {
                alert3.setVisible(true);
            }
        } else
            emptyalert3.setVisible(true);
    }

    public void tfPhoneNumClicked(KeyEvent keyEvent) {
        emptyalert4.setVisible(false);
        alert4.setVisible(false);
        btnAddPhoneNum.setVisible(true);
        if (!tfPrimaryCustomerPhone.getText().isEmpty()) {
            try {
                emptyalert4.setVisible(false);
                Integer.parseInt(tfPrimaryCustomerPhone.getText());
                alert4.setVisible(false);
            } catch (NumberFormatException e) {
                btnAddPhoneNum.setVisible(false);
                alert4.setVisible(true);
            }
        } else {
            btnAddPhoneNum.setVisible(false);
            emptyalert4.setVisible(true);
        }
    }

    public void btnInsertPhoneClicked(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        boolean nonEmptyEntries = !IDphonetf.getText().isEmpty() &&
                !insertphonetf.getText().isEmpty();
        if (nonEmptyEntries) {
            /** Make Sure The Entered ID Is Already Exists In Customer Info **/
            String SQL,SQL2;
            MyConnection.connectDB();
            try {
                SQL = "SELECT EXISTS(SELECT * from customer WHERE customer.customer_id =" + Integer.parseInt(IDphonetf.getText()) + ");";
                Statement stmt1 = MyConnection.con.createStatement();
                ResultSet rs1 = stmt1.executeQuery(SQL);
                int test = 0;
                try {
                    while (rs1.next()) {
                        test = Integer.parseInt(rs1.getString(1));
                    }
                } catch (SQLException e) {
                    System.out.println("Error in loop");
                    System.exit(1);
                }
                    NotFoundID.setVisible(false);
                    if(test == 1) {
                        insertNewPhone(Integer.parseInt(IDphonetf.getText()), Integer.parseInt(insertphonetf.getText()));
                        getContactInfo();
                        showCustomerContactInfo();
                        IDphonetf.clear();
                        insertphonetf.clear();
                        tabPane.getSelectionModel().selectPrevious();
                        rs1.close();
                        stmt1.close();
                    }
                    else
                        NotFoundID.setVisible(true);
            } catch (SQLException e) {
                NotFoundID.setVisible(true);
                e.printStackTrace();
            }

        } else {
            if (IDphonetf.getText().isEmpty())
                idphonerequired.setVisible(true);
            if (insertphonetf.getText().isEmpty())
                requiredphonenum.setVisible(true);

        }

    }

    private void insertNewPhone(int id, int phone_num) {
        if (!invalidIDphone.isVisible() && !phonenuminvalid.isVisible()) {
            try {
                MyConnection.connectDB();
                MyConnection.ExecuteStatement("insert into customer_phone (customer_id, phone_num) values (" + id + "," + phone_num + ");");
                MyConnection.con.close();
                System.out.println("Connection closed");
            } catch (SQLException | ClassNotFoundException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERROR in Adding Phone Number");
                alert.setContentText("An Error Occurred While Adding The Specified Phone Number!");
                alert.showAndWait();
            }
        }

    }

    public void IDphoneclicked(KeyEvent keyEvent) {
        idphonerequired.setVisible(false);
        invalidIDphone.setVisible(false);
        //  takenID.setVisible(false);
        if (!IDphonetf.getText().isEmpty()) {
            try {
                idphonerequired.setVisible(false);
                Integer.parseInt(IDphonetf.getText());
                invalidIDphone.setVisible(false);
            } catch (NumberFormatException e) {
                invalidIDphone.setVisible(true);
            }
        } else
            idphonerequired.setVisible(true);
    }

    public void phoneclicked(KeyEvent keyEvent) {
        requiredphonenum.setVisible(false);
        phonenuminvalid.setVisible(false);
        if (!insertphonetf.getText().isEmpty()) {
            try {
                requiredphonenum.setVisible(false);
                Integer.parseInt(insertphonetf.getText());
                phonenuminvalid.setVisible(false);
            } catch (NumberFormatException e) {
                phonenuminvalid.setVisible(true);
            }
        } else {
            requiredphonenum.setVisible(true);
        }

    }


    public void btnDeletePhoneClicked(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        tvPhone.setItems(dataListInfo);
        ObservableList<Customer_Phone> selectedRows = tvPhone.getSelectionModel().getSelectedItems();
        ArrayList<Customer_Phone> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> {
            tvPhone.getItems().remove(row);
            deleteRowPhone(row);
            tvPhone.refresh();

        });
        getData();
        showCustomers();
        getContactInfo();
        showCustomerContactInfo();
        searchContactInfo();
    }

    private void deleteRowPhone(Customer_Phone row) {
        // TODO Auto-generated method stub

        try {
            MyConnection.connectDB();
            MyConnection.ExecuteStatement("delete from  customer_phone where customer_id=" + row.getCustomer_id() + " AND phone_num=" + row.getPhone_num() + ";");
            MyConnection.con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
