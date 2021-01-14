/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_ihouse_project_final_version;

import Classes.Customer;
import Classes.Employee_Phone;
import Classes.Technician;
import Classes.Employee;
import com.jfoenix.controls.*;
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
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.lang.reflect.InvocationTargetException;


/**
 * FXML Controller class
 *
 * @author Main
 */
public class EmployeeController implements Initializable {
    static String position;
    static int duplicatedID = 0;
    static int InsertedSuccessfully = 0;
    static ObservableList<Employee> dataList;
    static ObservableList<Employee_Phone> dataListInfo;
    static ObservableList<Technician> dataListOfTech;
    static Date updatedDate;
    public Label SalaryRequired;
    public Label NameRequired;
    public Label idRequired;
    public Label invalidSalary;
    public Label invalidName;
    public Label invalidID;
    public JFXComboBox<String> DOEcb;
    public Label positionrequired;
    public Label emailrequired;
    public Label phonenumrequired;
    public Label invalidphonenum;
    public Label takenID;
    public Label DOErequired;
    public Label DOBrequired;
    public Label daterequired;
    public TableView<Employee_Phone> tvPhone;
    public TableColumn<Employee_Phone, Integer> colIDphone;
    public TableColumn colNamephone;
    public TableColumn colEmailphone;
    public JFXTextField tfSearchPhone;
    public Label positionrequiredtf;
    public Label invalidposition;
    public Label invalidareaOf;
    public Label areaofRequired;
    public Label certifrequired;
    public Label invalidcertif;
    public JFXButton btnInsertTech1;
    public JFXButton btnInsertTech;
    public JFXButton btnInsertPhone;
    public JFXButton btnInsertPhoneClicked;
    public Tab tab5;
    public JFXTextField IDphonetf;
    public JFXTextField insertphonetf;
    public Label idphonerequired;
    public Label requiredphonenum;
    public Label invalidIDphone;
    public Label phonenuminvalid;
    public Label NotFoundID;
    private ArrayList<Employee> data;
    private ArrayList<Employee_Phone> dataInfo;
    private ArrayList<Technician> dataTech;
    @FXML
    private TableView<Employee> tv;
    @FXML
    private TableView<Technician> tvTech;
    @FXML
    private TableColumn<Employee, Integer> colID;
    @FXML
    private TableColumn<Employee, Integer> colID1;
    @FXML
    private TableColumn<Employee_Phone, Integer> colPhone;
    @FXML
    private TableColumn<Employee_Phone, Integer> colPhone1;
    @FXML
    private TableColumn<Employee, String> colName1;
    @FXML
    private TableColumn<Employee, String> colAreaOf;
    @FXML
    private TableColumn<Employee, String> colCertif;
    @FXML
    private TableColumn<Employee, Date> colDate1;
    @FXML
    private TableColumn<Employee, String> colDOE1;

    @FXML
    private TableColumn<Employee, Double> colSalary1;
    @FXML
    private TableColumn<Employee, Date> colStartDate1;
    @FXML
    private TableColumn<Employee, String> colEmail1;
    @FXML
    private TableColumn<Employee, String> colName;
    @FXML
    private TableColumn<Employee, Date> colDate;
    @FXML
    private TableColumn<Employee, String> colDOE;
    @FXML
    private TableColumn<Employee, String> colPosition;
    @FXML
    private TableColumn<Employee, Double> colSalary;
    @FXML
    private TableColumn<Employee, Date> colStartDate;
    @FXML
    private TableColumn<Employee, String> colEmail;
    @FXML
    private JFXTextField tfPrimaryEmployeePhone;
    @FXML
    private JFXTextField tfSecondaryEmployeePhone;
    @FXML
    private JFXTextField tfSecondaryEmployeePhone2;
    @FXML
    private JFXTextField tfSearch;
    @FXML
    private JFXDatePicker datePickerDOB;
    @FXML
    private JFXDatePicker datePickerStartDate;
    @FXML
    private JFXComboBox<String> combo;
    @FXML
    private JFXTabPane tabPane;
    @FXML
    private JFXButton btnInsert;
    @FXML
    private JFXTextField tfID;
    @FXML
    private JFXTextField tfName;
    @FXML
    private JFXTextField tfSalary;
    @FXML
    private JFXTextField tfEmail;

    @FXML
    private JFXTextField certificationtf;
    @FXML
    private JFXTextField areaOftf;
    @FXML
    private JFXTextField tfSearchTech;

    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXButton btnAddPhoneNum;
    @FXML
    private Tab tab1;
    @FXML
    private Tab tab2;
    @FXML
    private Tab tab3;
    @FXML
    private Tab tab4;
    @FXML
    private JFXComboBox<String> cbPosition;
    @FXML
    private JFXTextField tfPosition;
    @FXML
    private JFXButton btnDeletePhone;
    
    private int countPhones = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String> list = FXCollections.observableArrayList("Technician", "Other");
        if (cbPosition != null) {
            cbPosition.setItems(list);
            cbPosition.setValue("");
        }
        ObservableList<String> listOfDegrees = FXCollections.observableArrayList("Associate degree", "Bachelor`s degree", "Master`s degree", "Doctoral degree");
        if (DOEcb != null) {
            DOEcb.setItems(listOfDegrees);
            DOEcb.setValue("");
        }
        tab3.setDisable(false);
        tab4.setDisable(true);
        try {
            getData();
            setTableEditable();
            showEmployees();
            getDataOfTech();
            setTableEditable2();
            showTechnicians();
            getContactInfo();
            setTableEditable3();
            showEmployeesContactInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<String> sortby = FXCollections.observableArrayList("Name", "Salary", "Recently");
        if (combo != null) {
            combo.setItems(sortby);
        }
    }

    private void deleteRow(Employee row) {
        // TODO Auto-generated method stub

        try {
            System.out.println("delete from  employee where id_num=" + row.getId_num() + ";");
            MyConnection.connectDB();
            MyConnection.ExecuteStatement("delete from  employee where id_num=" + row.getId_num() + ";");
            MyConnection.con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void deleteTechRow(Technician row) {
        // TODO Auto-generated method stub

        try {
            System.out.println("delete from  technician where id_num=" + row.getId_num() + ";");
            MyConnection.connectDB();
            MyConnection.ExecuteStatement("delete from  technician where id_num=" + row.getId_num() + ";");
            MyConnection.con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void getData() throws SQLException, ClassNotFoundException {
        // TODO Auto-generated method stub
        data = new ArrayList<>();
        String SQL;
        MyConnection.connectDB();
        System.out.println("Connection \n\n\n");
        SQL = "select * from employee order by id_num";
        Statement stmt = MyConnection.con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        try {
            while (rs.next()) {
                Date DOB = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(3));
                Date StartDate = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(7));
                data.add(new Employee(
                        Integer.parseInt(rs.getString(1)),
                        rs.getString(2),
                        DOB,
                        rs.getString(4),
                        rs.getString(5),
                        Double.parseDouble(rs.getString(6)),
                        StartDate,
                        rs.getString(8)));
            }
        } catch (Exception e) {
            System.out.println("Error In loop");
            e.printStackTrace();
            //System.exit(1);
        }
        rs.close();
        stmt.close();
        MyConnection.con.close();

        System.out.println("Connection closed" + data.size());
        dataList = FXCollections.observableArrayList(data);
        System.out.println(dataList);

    }

    private void getContactInfo() throws SQLException, ClassNotFoundException {
        // TODO Auto-generated method stub
        dataInfo = new ArrayList<>();
        String SQL;
        MyConnection.connectDB();
        SQL = "select ep.phone_num,ep.emp_id , e.ename,e.e_mail from employee_phone ep, employee e where ep.emp_id = e.id_num;";
        Statement stmt = MyConnection.con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        try {
            Employee_Phone emp = null;
            while (rs.next()) {

                emp = new Employee_Phone(Integer.parseInt(rs.getString(1)),
                        Integer.parseInt(rs.getString(2)), rs.getString(3), rs.getString(4));


                dataInfo.add(emp);

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
        System.out.println(dataListInfo);
    }


    private void getDataOfTech() throws SQLException, ClassNotFoundException {
        // TODO Auto-generated method stub
        dataTech = new ArrayList<>();
        String SQL;
        MyConnection.connectDB();
        System.out.println("Connection \n\n\n");
        SQL = "select T.id_num, E.eName, E.DOB, E.DOE, E.position, E.salary, E.emp_start_date, E.e_mail, T.certification, T.area_of_specialty from technician T, Employee E WHERE T.id_num=E.id_num order by T.id_num;";
        Statement stmt = MyConnection.con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        try {
            while (rs.next()) {
                Date DOB = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(3));
                Date StartDate = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(7));
                dataTech.add(new Technician(
                        Integer.parseInt(rs.getString(1)),
                        rs.getString(2),
                        DOB,
                        rs.getString(4),
                        "Technician",
                        Double.parseDouble(rs.getString(6)),
                        StartDate,
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)));
            }
        } catch (Exception e) {
            System.out.println("Error In loop22");
            System.exit(1);
        }
        rs.close();
        stmt.close();
        MyConnection.con.close();
        System.out.println("Connection closed" + dataTech.size());
        dataListOfTech = FXCollections.observableArrayList(dataTech);
        System.out.println(dataListOfTech);
    }

    public void showTechnicians() {
        colID1.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("Id_num"));
        colName1.setCellValueFactory(new PropertyValueFactory<Employee, String>("Name"));
        colDate1.setCellValueFactory(new PropertyValueFactory<Employee, Date>("DOB"));
        colDOE1.setCellValueFactory(new PropertyValueFactory<Employee, String>("DOE"));
        colSalary1.setCellValueFactory(new PropertyValueFactory<Employee, Double>("Salary"));
        colStartDate1.setCellValueFactory(new PropertyValueFactory<Employee, Date>("Emp_start_date"));
        colEmail1.setCellValueFactory(new PropertyValueFactory<Employee, String>("E_mail"));
        colCertif.setCellValueFactory(new PropertyValueFactory<Employee, String>("certification"));
        colAreaOf.setCellValueFactory(new PropertyValueFactory<Employee, String>("area_of_specialty"));
        searchTech();
    }

    public void showEmployees() {
        colID.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("Id_num"));
        colName.setCellValueFactory(new PropertyValueFactory<Employee, String>("Name"));
        colDate.setCellValueFactory(new PropertyValueFactory<Employee, Date>("DOB"));
        colDOE.setCellValueFactory(new PropertyValueFactory<Employee, String>("DOE"));
        colPosition.setCellValueFactory(new PropertyValueFactory<Employee, String>("Position"));
        colSalary.setCellValueFactory(new PropertyValueFactory<Employee, Double>("Salary"));
        colStartDate.setCellValueFactory(new PropertyValueFactory<Employee, Date>("Emp_start_date"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Employee, String>("E_mail"));
        search();
    }

    public void showEmployeesContactInfo() {
        colIDphone.setCellValueFactory(new PropertyValueFactory<Employee_Phone, Integer>("emp_id"));
        colNamephone.setCellValueFactory(new PropertyValueFactory<Employee_Phone, String>("eName"));
        colPhone.setCellValueFactory(new PropertyValueFactory<Employee_Phone, Integer>("phone_num"));
        colEmailphone.setCellValueFactory(new PropertyValueFactory<Employee_Phone, String>("e_mail"));
        searchContactInfo();
    }

    private void setTableEditable() {
        tv.setEditable(true);
        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colPosition.setCellFactory(TextFieldTableCell.forTableColumn());
        colSalary.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        colEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        colDOE.setCellFactory(TextFieldTableCell.forTableColumn());
    }
    private void setTableEditable2() {
        tvTech.setEditable(true);
        colName1.setCellFactory(TextFieldTableCell.forTableColumn());
        colSalary1.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        colEmail1.setCellFactory(TextFieldTableCell.forTableColumn());
        colDOE1.setCellFactory(TextFieldTableCell.forTableColumn());
        colAreaOf.setCellFactory(TextFieldTableCell.forTableColumn());
        colCertif.setCellFactory(TextFieldTableCell.forTableColumn());
    }
    private void setTableEditable3() {
        tvPhone.setEditable(true);
        //colNamephone.setCellFactory(TextFieldTableCell.forTableColumn());
        //colEmailphone.setCellFactory(TextFieldTableCell.forTableColumn());
        colPhone.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }



    @FXML
    private void editName(TableColumn.CellEditEvent<Employee, String> t) throws SQLException, ClassNotFoundException {
        t.getTableView().getItems().get(
                t.getTablePosition().getRow()).seteName(t.getNewValue());
        updateName(t.getRowValue().getId_num(), t.getNewValue());
        getDataOfTech();
        showTechnicians();
        getData();
        showEmployees();
        getContactInfo();
        showEmployeesContactInfo();
    }

    public void updateName(int id, String name) {

        try {
            System.out.println("update employee set eName = '" + name + "' where id_num = " + id);
            MyConnection.connectDB();
            MyConnection.ExecuteStatement("update employee set eName = '" + name + "' where id_num = " + id + ";");
            MyConnection.con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void editDOE(TableColumn.CellEditEvent<Employee, String> t) throws SQLException, ClassNotFoundException {
        t.getTableView().getItems().get(
                t.getTablePosition().getRow()).setDOE(t.getNewValue());
        updateDOE(t.getRowValue().getId_num(), t.getNewValue());
    }

    public void updateDOE(int id, String degree) {

        try {
            System.out.println("update employee set DOE = '" + degree + "' where id_num = " + id);
            MyConnection.connectDB();
            MyConnection.ExecuteStatement("update employee set DOE = '" + degree + "' where id_num = " + id + ";");
            MyConnection.con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    Technician Updated;

    @FXML
    private void editPosition(TableColumn.CellEditEvent<Employee, String> t) throws Exception {
        int found = 0;
        t.getTableView().getItems().get(
                t.getTablePosition().getRow()).setPosition(t.getNewValue());
        updatePosition(t.getRowValue().getId_num(), t.getNewValue());
        System.err.println("Name is' : " + t.getRowValue().getId_num());
        if (t.getNewValue().equalsIgnoreCase("Technician")) {
            /** If DataListOfTech is Empty, Then The Updated Employee To Technician Must Be Added  **/
            if (dataListOfTech.isEmpty()) {
                String sDate1 = t.getRowValue().getDOB();
                Date DOB = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
                String sDate2 = t.getRowValue().getEmp_start_date();
                Date ESD = new SimpleDateFormat("yyyy-MM-dd").parse(sDate2);
                Updated = new Technician(t.getRowValue().getId_num(),
                        t.getRowValue().getName(),
                        DOB,
                        t.getRowValue().getDOE(), t
                        .getRowValue().getPosition(),
                        t.getRowValue().getSalary(),
                        ESD,
                        t.getRowValue().getE_mail(), certificationtf.getText()
                        , areaOftf.getText());

                System.err.println(" Updated Is : poisiton is " + Updated.toString());
                btnInsertTech.setVisible(false);
                btnInsertTech1.setVisible(true);
                tab4.setDisable(false);
                tabPane.getSelectionModel().select(tab4);
            } else
            /** If dataListOfTech Is Not Empty; Search If The Updated Technician Already In The DBMS If Not, Insert It**/
                for (int i = 0; i < dataListOfTech.size(); i++) {
                    if (dataListOfTech.get(i).getId_num() == t.getRowValue().getId_num())
                        found = 1;
                    if(found==0){
                        String sDate1 = t.getRowValue().getDOB();
                        Date DOB = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
                        String sDate2 = t.getRowValue().getEmp_start_date();
                        Date ESD = new SimpleDateFormat("yyyy-MM-dd").parse(sDate2);
                        Updated = new Technician(t.getRowValue().getId_num(),
                                t.getRowValue().getName(),
                                DOB,
                                t.getRowValue().getDOE(), t
                                .getRowValue().getPosition(),
                                t.getRowValue().getSalary(),
                                ESD,
                                t.getRowValue().getE_mail(), certificationtf.getText()
                                , areaOftf.getText());

                        System.err.println(" Updated Is : poisiton is " + Updated.toString());
                        btnInsertTech.setVisible(false);
                        btnInsertTech1.setVisible(true);
                        tab4.setDisable(false);
                        tabPane.getSelectionModel().select(tab4);
                    }
                    else
                        System.out.println(" Already Technician");

                }
        }
        getDataOfTech();
        showTechnicians();
        getData();
        showEmployees();

    }


    public void updatePosition(int id, String position) {

        try {
            System.out.println("update employee set position= '" + position + "' where id_num = " + id);
            MyConnection.connectDB();
            MyConnection.ExecuteStatement("update employee set position= '" + position + "' where id_num = " + id + ";");

            /** If Employee Position Changed From Technician To Other Position Make Sure To Delete It From Technician DBMS **/

            if (!position.equalsIgnoreCase("Technician")) {
                for (int i = 0; i < dataListOfTech.size(); i++) {
                    if (dataListOfTech.get(i).getId_num() == id) {
                        dataListOfTech.remove(i);
                        MyConnection.ExecuteStatement("delete from  technician where id_num=" + id + ";");
                    }
                }
            }
            MyConnection.con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void editSalary(TableColumn.CellEditEvent<Employee, Double> t) throws SQLException, ClassNotFoundException {
        t.getTableView().getItems().get(
                t.getTablePosition().getRow()).setSalary(t.getNewValue());
        updateSalary(t.getRowValue().getId_num(), t.getNewValue());
        getDataOfTech();
        showTechnicians();
        getData();
        showEmployees();

    }

    public void updateSalary(int id, double salary) {

        try {
            System.out.println("update employee set salary= '" + salary + "' where id_num = " + id);
            MyConnection.connectDB();
            MyConnection.ExecuteStatement("update employee set salary= '" + salary + "' where id_num = " + id + ";");
            MyConnection.con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void editEmail(TableColumn.CellEditEvent<Employee, String> t) throws SQLException, ClassNotFoundException {
        t.getTableView().getItems().get(
                t.getTablePosition().getRow()).setE_mail(t.getNewValue());
        updateEmail(t.getRowValue().getId_num(), t.getNewValue());
        getDataOfTech();
        showTechnicians();
        getData();
        showEmployees();
        getContactInfo();
        showEmployeesContactInfo();
    }

    public void updateEmail(int id, String email) {

        try {
            System.out.println("update employee set e_mail= '" + email + "' where id_num = " + id);
            MyConnection.connectDB();
            MyConnection.ExecuteStatement("update employee set e_mail= '" + email + "' where id_num = " + id + ";");
            MyConnection.con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void editDOB(TableColumn.CellEditEvent<Employee, Date> t) {
        Date newDate = GetDate.display();
        if (newDate != null) {
            System.out.println(newDate);
            ((Employee) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setDOB(newDate);
            System.out.println("Done");
            Employee rc = ((Employee) t.getTableView().getItems().get(
                    t.getTablePosition().getRow()));
            System.out.println(((Employee) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())).toString());
            updateDOB(t.getRowValue().getId_num(), rc);
            System.out.println("Update done successfully");
        }
    }

    public void updateDOB(int id, Employee rc) {

        try {
            System.out.println("update employee set DOB= '" + rc.getDOB() + "' where id_num = " + id);
            MyConnection.connectDB();
            MyConnection.ExecuteStatement("update employee set DOB= '" + rc.getDOB() + "' where id_num = " + id + ";");
            MyConnection.con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void editStartDate(TableColumn.CellEditEvent<Employee, Date> t) throws IOException {
        Date newDate = GetDate.display();
        if (newDate != null) {
            System.out.println(newDate);
            t.getTableView().getItems().get(
                    t.getTablePosition().getRow()).setEmp_start_date(newDate);
            System.out.println("Done");
            Employee rc = t.getTableView().getItems().get(
                    t.getTablePosition().getRow());
            System.out.println(t.getTableView().getItems().get(
                    t.getTablePosition().getRow()).toString());
            updateStartDate(t.getRowValue().getId_num(), rc);
        }
    }


    public void updateStartDate(int id, Employee rc) {

        try {
            System.out.println("update employee set emp_start_date= '" + rc.getEmp_start_date() + "' where id_num = " + id);
            MyConnection.connectDB();
            MyConnection.ExecuteStatement("update employee set emp_start_date= '" + rc.getEmp_start_date() + "' where id_num = " + id + ";");
            MyConnection.con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void editPhone(TableColumn.CellEditEvent<Employee_Phone, Integer> t) throws SQLException, ClassNotFoundException {
        try{
        t.getTableView().getItems().get(
                t.getTablePosition().getRow()).setPhone_num(t.getNewValue());
        
        updatePhone(t.getRowValue().getEmp_id(), t.getNewValue());
        }
        catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Phone Number");
            alert.setContentText("You cannot leave a phone number empty. Delete row instead");
            alert.showAndWait();
        }
        

    }

    private void updatePhone(int id, Integer phone) {
        try {
            System.out.println("update employee_phone set phone= '" + phone + "' where id_num = " + id);
            MyConnection.connectDB();
            MyConnection.ExecuteStatement("update employee_phone set phone_num= '" + phone + "' where emp_id = " + id + ";");
            MyConnection.con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnDeleteClicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        tv.setItems(dataList);
        ObservableList<Employee> selectedRows = tv.getSelectionModel().getSelectedItems();
        ArrayList<Employee> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> {
            tv.getItems().remove(row);
            deleteRow(row);
            tv.refresh();
            tvTech.refresh();
        });
        getDataOfTech();
        showTechnicians();
        getContactInfo();
        showEmployeesContactInfo();
        search();
    }

    @FXML
    void btnInsertClicked(ActionEvent event) throws Exception {
        boolean nonEmptyEntries = !tfName.getText().isEmpty() &&
                !tfID.getText().isEmpty() &&
                !DOEcb.getSelectionModel().isEmpty() &&
                !cbPosition.getSelectionModel().isEmpty() &&
                !tfSalary.getText().isEmpty() &&
                !tfEmail.getText().isEmpty() &&
                datePickerDOB.getValue() != null &&
                datePickerStartDate.getValue() != null;
        if (nonEmptyEntries) {
            if (!cbPosition.getSelectionModel().getSelectedItem().equalsIgnoreCase("Technician") && tfPosition != null) {
                position = tfPosition.getText();
            } else position = "Technician";
            // position = cbPosition.getSelectionModel().getSelectedItem().toString();
            LocalDate localDate = datePickerDOB.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date DOB = Date.from(instant);
            LocalDate localDate1 = datePickerStartDate.getValue();
            Instant instant1 = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date ESD = Date.from(instant1);
            Employee rc = new Employee(
                    Integer.parseInt(tfID.getText()),
                    tfName.getText(),
                    DOB,
                    DOEcb.getSelectionModel().getSelectedItem().toString(),
                    position,
                    Double.parseDouble(tfSalary.getText()),
                    ESD,
                    tfEmail.getText());
            insertData(rc);
            if (InsertedSuccessfully == 1) {
                dataList.add(rc);
                if (position.equalsIgnoreCase("Technician")) {
                    tab4.setDisable(false);
                    tabPane.getSelectionModel().selectNext();
                } else {

                    insertPhones();
                    tabPane.getSelectionModel().selectFirst();

                    certificationtf.clear();
                    areaOftf.clear();
                    tfName.clear();
                    tfID.clear();
                    if (!DOEcb.getSelectionModel().isEmpty()) DOEcb.setValue("");
                    datePickerStartDate.getEditor().clear();
                    datePickerDOB.getEditor().clear();
                    tfPrimaryEmployeePhone.clear();
                    if (tfSecondaryEmployeePhone != null) tfSecondaryEmployeePhone.clear();
                    if (tfSecondaryEmployeePhone2 != null) tfSecondaryEmployeePhone2.clear();
                    if (tfPosition.getText() != null) tfPosition.clear();
                    if (!cbPosition.getSelectionModel().isEmpty()) cbPosition.setValue("");
                    tfSalary.clear();
                    tfEmail.clear();

                }
                tab3.setDisable(true);
            }


        } else {
            if (tfID.getText().isEmpty())
                idRequired.setVisible(true);
            if (tfName.getText().isEmpty())
                NameRequired.setVisible(true);
            if (tfEmail.getText().isEmpty())
                emailrequired.setVisible(true);
            if (tfSalary.getText().isEmpty())
                SalaryRequired.setVisible(true);
            if (cbPosition.getSelectionModel().isEmpty())
                positionrequired.setVisible(true);
            if (tfPrimaryEmployeePhone.getText().isEmpty()) {
                btnAddPhoneNum.setVisible(false);
                phonenumrequired.setVisible(true);
            }
            if (DOEcb.getSelectionModel().isEmpty())
                DOErequired.setVisible(true);
            if (datePickerDOB.getValue() == null)
                DOBrequired.setVisible(true);
            if (datePickerStartDate.getValue() == null)
                daterequired.setVisible(true);
            if (tfPosition.isVisible() && tfPosition.getText().isEmpty())
                positionrequiredtf.setVisible(true);
        }

    }

    private void insertData(Employee rc) {
        InsertedSuccessfully = 0;
        duplicatedID = 0;
        if (!invalidID.isVisible() && !invalidName.isVisible() && !invalidSalary.isVisible() && !invalidphonenum.isVisible()) {
            System.out.println("Your  Allowed to add");
            try {
                MyConnection.connectDB();

                System.out.println("Insert into employee (id_num,eName,DOB,DOE,position,salary,emp_start_date,e_mail) values(" + rc.getId_num() + ",'" + rc.getName() + "','" + rc.getDOB() + "'," + rc.getDOE() + ",'" + rc.getPosition() + "'," + rc.getSalary() + ",'" + rc.getEmp_start_date() + "','" + rc.getE_mail() + "')");
                MyConnection.ExecuteStatement("Insert into employee (id_num,eName,DOB,DOE,position,salary,emp_start_date,e_mail) values(" + rc.getId_num() + ",'" + rc.getName() + "','" + rc.getDOB() + "','" + rc.getDOE() + "','" + rc.getPosition() + "'," + rc.getSalary() + ",'" + rc.getEmp_start_date() + "','" + rc.getE_mail() + "')");
                MyConnection.con.close();
                System.out.println("Connection closed");
                System.out.println(duplicatedID);
                if (duplicatedID == 1) {
                    takenID.setVisible(true);
                } else
                    InsertedSuccessfully = 1;
            } catch (SQLException | ClassNotFoundException e) {
               // e.printStackTrace();
               
            }
        }
    }
    private void insertTech(Technician Tech) {
        InsertedSuccessfully = 0;
        duplicatedID = 0;
        takenID.setVisible(false);
        try {
            MyConnection.connectDB();

            System.out.println("Insert into technician (id_num,eName,DOB,DOE,position,salary,emp_start_date,e_mail) values(" + Tech.getId_num() + ",'" + Tech.getName() + "','" + Tech.getDOB() + "'," + Tech.getDOE() + ",'" + Tech.getPosition() + "'," + Tech.getSalary() + ",'" + Tech.getEmp_start_date() + "','" + Tech.getE_mail() + "')");
            MyConnection.ExecuteStatement("Insert into technician (id_num,certification,area_of_specialty) values(" + Tech.getId_num() + ",'" + Tech.getCertification() + "','" + Tech.getArea_of_specialty() + "');");
            MyConnection.con.close();
            System.out.println("Connection closed");
            if (duplicatedID == 1)
                takenID.setVisible(true);
            else {
                tabPane.getSelectionModel().select(tab2);
                InsertedSuccessfully = 1;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void insertPhones() throws Exception {
        MyConnection.connectDB();
        try {
            int employeeID = Integer.parseInt(tfID.getText());
            String EmployeePhone1 = tfPrimaryEmployeePhone.getText();
            String EmployeePhone2 = tfSecondaryEmployeePhone.getText();
            String EmployeePhone3 = tfSecondaryEmployeePhone2.getText();
            if (!EmployeePhone1.isEmpty() && EmployeePhone2.isEmpty() && EmployeePhone3.isEmpty()) {
                System.out.println("\"insert into employee_phone (emp_id, phone_num) values ");
                String SQL = "insert into employee_phone (emp_id, phone_num) values (" + employeeID + "," + Integer.parseInt(EmployeePhone1) + ");";
                MyConnection.ExecuteStatement(SQL);

            } else if (!EmployeePhone1.isEmpty() && !EmployeePhone2.isEmpty() && EmployeePhone3.isEmpty()) {
                System.out.println("\"insert into employee_phone (emp_id, phone_num) values ");
                String SQL1 = "insert into employee_phone (emp_id, phone_num) values (" + employeeID + "," + Integer.parseInt(EmployeePhone1) + ");";
                String SQL2 = "insert into employee_phone (emp_id, phone_num) values (" + employeeID + "," + Integer.parseInt(EmployeePhone2) + ");";
                if (Integer.parseInt(EmployeePhone1) == Integer.parseInt(EmployeePhone2)) {
                    showAlertViolation();
                } else {
                    MyConnection.ExecuteStatement(SQL1);
                    MyConnection.ExecuteStatement(SQL2);
                }
            } else if (!EmployeePhone1.isEmpty() && !EmployeePhone2.isEmpty() && !EmployeePhone3.isEmpty()) {
                System.out.println("\"insert into employee_phone (emp_id, phone_num) values ");
                String SQL1 = "insert into employee_phone (emp_id, phone_num) values (" + employeeID + "," + Integer.parseInt(EmployeePhone1) + ");";
                String SQL2 = "insert into employee_phone (emp_id, phone_num) values (" + employeeID + "," + Integer.parseInt(EmployeePhone2) + ");";
                String SQL3 = "insert into employee_phone (emp_id, phone_num) values (" + employeeID + "," + Integer.parseInt(EmployeePhone3) + ");";
                if (Integer.parseInt(EmployeePhone1) == Integer.parseInt(EmployeePhone2) || Integer.parseInt(EmployeePhone2) == Integer.parseInt(EmployeePhone3) || Integer.parseInt(EmployeePhone1) == Integer.parseInt(EmployeePhone3)) {
                    showAlertViolation();
                } else {
                    MyConnection.ExecuteStatement(SQL1);
                    MyConnection.ExecuteStatement(SQL2);
                    MyConnection.ExecuteStatement(SQL3);
                }
            }
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Phone Number");
            alert.setContentText("Please enter at least one phone number");
            alert.showAndWait();
        } finally {
            MyConnection.con.close();
            getData();
            setTableEditable();
            showEmployees();
            getDataOfTech();
            setTableEditable2();
            showTechnicians();
            getContactInfo();
            setTableEditable3();
            showEmployeesContactInfo();
            //       tvPhone.refresh();


        }
    }

    public void showAlertViolation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Duplicate Data");
        alert.setContentText("Make sure phone numbers are unique!");
        alert.showAndWait();
    }

    @FXML
    private void btnAddPhoneClicked(ActionEvent event) {
        if (countPhones == 0) {
            tfSecondaryEmployeePhone.setVisible(true);
            countPhones++;
        } else if (countPhones == 1) {
            tfSecondaryEmployeePhone2.setVisible(true);
        }

    }
    @FXML
    void btnDeletePhoneClicked(ActionEvent event) throws Exception{
        tvPhone.setItems(dataListInfo);
        ObservableList<Employee_Phone> selectedRows = tvPhone.getSelectionModel().getSelectedItems();
        ArrayList<Employee_Phone> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> {
            tvPhone.getItems().remove(row);
            deleteRowPhone(row);
            tvPhone.refresh();
           
        });
        getContactInfo();
        showEmployeesContactInfo();
        getDataOfTech();
        showTechnicians();
        getData();
        showEmployees();
        searchContactInfo();
        
    }
    public void btnInsertPhoneClicked(ActionEvent actionEvent)throws Exception {
        boolean nonEmptyEntries = !IDphonetf.getText().isEmpty() && !insertphonetf.getText().isEmpty();
        if (nonEmptyEntries) {
            /** Make Sure The Entered ID Is Already Exists In Customer Info **/
            String SQL,SQL2;
            MyConnection.connectDB();
            try {
                SQL = "SELECT EXISTS(SELECT * from employee WHERE employee.id_num =" + Integer.parseInt(IDphonetf.getText()) + ");";
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
                        showEmployeesContactInfo();
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
                MyConnection.ExecuteStatement("insert into employee_phone (emp_id, phone_num) values (" + id + "," + phone_num + ");");
                MyConnection.con.close();
                System.out.println("Connection closed");
            } catch (SQLException | ClassNotFoundException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERROR in Adding Phone Number");
                alert.setContentText("An Error Occured While Adding The Specified Phone Number!");
                alert.showAndWait();
            }
        }

    }

    private void deleteRowPhone(Employee_Phone row) {
        // TODO Auto-generated method stub

        try {
            MyConnection.connectDB();
            MyConnection.ExecuteStatement("delete from  employee_phone where emp_id=" + row.getEmp_id() +" AND phone_num="+ row.getPhone_num()+ ";");
            MyConnection.con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void searchTech() {
// Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Technician> filteredData = new FilteredList<>(dataListOfTech, b -> true);
// 2. Set the filter Predicate whenever the filter changes.
        tfSearchTech.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(technician -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (technician.getName()!= null && technician.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                }
                else if (technician.getArea_of_specialty()!= null && technician.getArea_of_specialty().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                }
                 else if (technician.getCertification()!= null && technician.getCertification().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                }
                else if (technician.getE_mail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    } else return String.valueOf(technician.getSalary()).indexOf(lowerCaseFilter) != -1;
            });
        });
        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Technician> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tvTech.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tvTech.setItems(sortedData);

    }

    void search() {
// Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Employee> filteredData = new FilteredList<>(dataList, b -> true);
// 2. Set the filter Predicate whenever the filter changes.
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(employee -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (employee.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else // Does not match.
                    if (employee.getE_mail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    } else return String.valueOf(employee.getSalary()).indexOf(lowerCaseFilter) != -1;
            });
        });
        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Employee> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tv.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tv.setItems(sortedData);

    }

    void searchContactInfo() {
// Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Employee_Phone> filteredData = new FilteredList<>(dataListInfo, b -> true);
// 2. Set the filter Predicate whenever the filter changes.

        tfSearchPhone.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(info -> {
                        // If filter text is empty, display all persons.

                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }

                // Compare first name and last name of every person with filter text.
                 String lowerCaseFilter = newValue.toLowerCase();
                if (info.getEName()!= null && info.getEName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else // Does not match.
                    if (info.getE_mail()!=null && info.getE_mail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    } else
                        if(String.valueOf(info.getEmp_id()).indexOf(lowerCaseFilter) != -1)
                            return true;
                            else
                        return String.valueOf(info.getPhone_num()).indexOf(lowerCaseFilter) != -1;

            });


        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Employee_Phone> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tvPhone.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tvPhone.setItems(sortedData);
    }


    public void SortDrag(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String selected = combo.getSelectionModel().getSelectedItem().toString();
        data = new ArrayList<>();
        String SQL;
        MyConnection.connectDB();
        if (selected.equals("Name")) {
            SQL = "select * from employee  order by eName";
            Statement stmt = MyConnection.con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            try {

                while (rs.next()) {
                    Date DOB = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(3));
                    Date StartDate = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(7));
                    data.add(new Employee(
                            Integer.parseInt(rs.getString(1)),
                            rs.getString(2),
                            DOB,
                            rs.getString(4),
                            rs.getString(5),
                            Double.parseDouble(rs.getString(6)),
                            StartDate,
                            rs.getString(8)));
                }
            } catch (Exception e) {
                System.out.println("Error In loop");
                System.exit(1);
            }
            rs.close();
            stmt.close();
        } else if (selected.equals("Salary")) {
            SQL = "select * from employee  order by salary";
            Statement stmt = MyConnection.con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            try {

                while (rs.next()) {
                    Date DOB = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(3));
                    Date StartDate = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(7));
                    data.add(new Employee(
                            Integer.parseInt(rs.getString(1)),
                            rs.getString(2),
                            DOB,
                            rs.getString(4),
                            rs.getString(5),
                            Double.parseDouble(rs.getString(6)),
                            StartDate,
                            rs.getString(8)));
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
        showEmployees();
    }


    public void SelectPosition(ActionEvent actionEvent) {
        if (!cbPosition.getSelectionModel().isEmpty())
            positionrequired.setVisible(false);
        String selected = cbPosition.getSelectionModel().getSelectedItem();
        if (selected.equals("Other") && tfPosition != null) {
            tfPosition.setVisible(true);
        } else tfPosition.setVisible(false);
    }

    public void btnInsertTech(ActionEvent actionEvent) throws Exception {
        boolean nonEmptyEntriesTech = !tfID.getText().isEmpty()
                && !certificationtf.getText().isEmpty()
                && !areaOftf.getText().isEmpty();
        if (nonEmptyEntriesTech) {
            LocalDate localDate = datePickerDOB.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date DOB = Date.from(instant);
            LocalDate localDate1 = datePickerStartDate.getValue();
            Instant instant1 = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date ESD = Date.from(instant1);
            Technician Tech = new Technician(Integer.parseInt(tfID.getText()),
                    tfName.getText(),
                    DOB,
                    DOEcb.getSelectionModel().getSelectedItem().toString(),
                    position,
                    Double.parseDouble(tfSalary.getText()),
                    ESD,
                    tfEmail.getText(),
                    certificationtf.getText(), areaOftf.getText());
            insertTech(Tech);
            if (InsertedSuccessfully == 1)
                dataListOfTech.add(Tech);
            insertPhones();
            certificationtf.clear();
            areaOftf.clear();
            tfName.clear();
            tfID.clear();
            if (!DOEcb.getSelectionModel().isEmpty()) DOEcb.setValue("");
            datePickerStartDate.getEditor().clear();
            datePickerDOB.getEditor().clear();
            tfPrimaryEmployeePhone.clear();
            if (tfSecondaryEmployeePhone != null) tfSecondaryEmployeePhone.clear();
            if (tfSecondaryEmployeePhone2 != null) tfSecondaryEmployeePhone2.clear();

            if (tfPosition.getText() != null) tfPosition.clear();
            if (!cbPosition.getSelectionModel().isEmpty()) cbPosition.setValue("");
            tfSalary.clear();
            tfEmail.clear();
        }

        tab1.setDisable(false);
        tab2.setDisable(false);
        tab3.setDisable(false);
        tab4.setDisable(true);
    }



    public void tfIDClicked(KeyEvent keyEvent) {
        idRequired.setVisible(false);
        invalidID.setVisible(false);
        takenID.setVisible(false);
        if (!tfID.getText().isEmpty()) {
            try {
                idRequired.setVisible(false);
                Integer.parseInt(tfID.getText());
                invalidID.setVisible(false);
            } catch (NumberFormatException e) {
                invalidID.setVisible(true);
            }
        } else
            idRequired.setVisible(true);
    }

    public void tfNameClicked(KeyEvent keyEvent) {
        NameRequired.setVisible(false);
        invalidName.setVisible(false);

        if (!tfName.getText().isEmpty()) {
            NameRequired.setVisible(false);
            if (tfName.getText().matches(".*\\d+.*")) {
                invalidName.setVisible(true);
            } else {
                invalidName.setVisible(false);
            }
        } else
            NameRequired.setVisible(true);
    }


    public void SalarytfClicked(KeyEvent keyEvent) {
        SalaryRequired.setVisible(false);
        invalidSalary.setVisible(false);
        if (!tfSalary.getText().isEmpty()) {
            try {
                SalaryRequired.setVisible(false);
                Double.parseDouble(tfSalary.getText());
                invalidSalary.setVisible(false);
            } catch (NumberFormatException e) {
                invalidSalary.setVisible(true);
            }
        } else
            SalaryRequired.setVisible(true);
    }


    public void emailtfClicked(KeyEvent keyEvent) {
        emailrequired.setVisible(false);
        if (!tfEmail.getText().isEmpty()) {
            emailrequired.setVisible(false);

        } else
            emailrequired.setVisible(true);
    }

    public void PrimaryphoneNumClicked(KeyEvent keyEvent) {
        phonenumrequired.setVisible(false);
        invalidphonenum.setVisible(false);
        btnAddPhoneNum.setVisible(true);
        if (!tfPrimaryEmployeePhone.getText().isEmpty()) {
            try {
                phonenumrequired.setVisible(false);
                Integer.parseInt(tfPrimaryEmployeePhone.getText());
                invalidphonenum.setVisible(false);
            } catch (NumberFormatException e) {
                btnAddPhoneNum.setVisible(false);
                invalidphonenum.setVisible(true);
            }
        } else {
            btnAddPhoneNum.setVisible(false);
            phonenumrequired.setVisible(true);
        }
    }

    public void selectStartDate(ActionEvent actionEvent) {
        if (datePickerStartDate.getValue() != null)
            daterequired.setVisible(false);
        else
            daterequired.setVisible(true);
    }

    public void selectDOE(ActionEvent actionEvent) {
        if (!DOEcb.getSelectionModel().isEmpty())
            DOErequired.setVisible(false);

    }

    public void selectDOB(ActionEvent actionEvent) {
        if (datePickerDOB.getValue() != null)
            DOBrequired.setVisible(false);
        else
            DOBrequired.setVisible(true);
    }

    public void PositionClick(KeyEvent keyEvent) {
        positionrequiredtf.setVisible(false);
        invalidposition.setVisible(false);

        if (!tfPosition.getText().isEmpty()) {
            positionrequiredtf.setVisible(false);
            if (tfPosition.getText().matches("[a-zA-Z]+")) {
                invalidposition.setVisible(false);
            } else {
                invalidposition.setVisible(true);
            }
        } else
            positionrequiredtf.setVisible(true);
    }

    public void areaoftfClicked(KeyEvent keyEvent) {
        areaofRequired.setVisible(false);
        invalidareaOf.setVisible(false);

        if (!areaOftf.getText().isEmpty()) {
            areaofRequired.setVisible(false);
            if (areaOftf.getText().matches(".*\\d+.*")) {
                invalidareaOf.setVisible(true);
            } else {
                invalidareaOf.setVisible(false);
            }
        } else
            areaofRequired.setVisible(true);
    }

    public void certiftfClicked(KeyEvent keyEvent) {
        certifrequired.setVisible(false);
        invalidcertif.setVisible(false);

        if (!certificationtf.getText().isEmpty()) {
            certifrequired.setVisible(false);
            if (certificationtf.getText().matches(".*\\d+.*")) {
                invalidcertif.setVisible(true);
            } else {
                invalidcertif.setVisible(false);
            }
        } else
            certifrequired.setVisible(true);

    }


    public void btnUpdateToTech(ActionEvent actionEvent) throws ParseException {

        if (!certificationtf.getText().isEmpty() && !areaOftf.getText().isEmpty()) {
            Updated.setCertification(certificationtf.getText());
            Updated.setArea_of_specialty(areaOftf.getText());
        }
        btnInsertTech1.setVisible(false);
        btnInsertTech.setVisible(true);
        dataListOfTech.add(Updated);
        insertTech(Updated);
        certificationtf.clear();
        areaOftf.clear();
        tab4.setDisable(true);
    }


    public void editCertif(TableColumn.CellEditEvent<Technician, String> t) throws SQLException, ClassNotFoundException {
        t.getTableView().getItems().get(
                t.getTablePosition().getRow()).setCertification(t.getNewValue());
        updateCertif(t.getRowValue().getId_num(), t.getNewValue());


    }

    private void updateCertif(int id, String certification) {
        try {
            MyConnection.connectDB();
            MyConnection.ExecuteStatement("update technician set certification = '" + certification + "' where id_num = " + id + ";");
            MyConnection.con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void editAreaOf(TableColumn.CellEditEvent<Technician, String> t) {
        t.getTableView().getItems().get(
                t.getTablePosition().getRow()).setArea_of_specialty(t.getNewValue());
        updateAreaOf(t.getRowValue().getId_num(), t.getNewValue());

    }

    private void updateAreaOf(int id, String AreaOf) {
        try {
            MyConnection.connectDB();
            MyConnection.ExecuteStatement("update technician set area_of_specialty = '" + AreaOf + "' where id_num = " + id + ";");
            MyConnection.con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
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
}
