/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_ihouse_project_final_version;


import Classes.ReplacementParts;
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
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author Main
 */
public class WarehouseController implements Initializable {

    @FXML
    private TableView<ReplacementParts> tvWarehouse;
    @FXML
    private TableColumn<ReplacementParts, String> colSerialNumber;
    @FXML
    private TableColumn<ReplacementParts, Integer> colAmountInStock;
    @FXML
    private TableColumn<ReplacementParts, String> colPartType;
    @FXML
    private TableColumn<ReplacementParts, Double> colPrice;
    @FXML
    private TableColumn<ReplacementParts, String> colCompatability;
    @FXML
    private JFXTextField tfSearch;
    @FXML
    private JFXTextField tfSerialNo;
    @FXML
    private JFXTextField tfPartType;
    @FXML
    private JFXTextField tfAmount;
    @FXML
    private JFXTextField tfPrice;
    @FXML
    private JFXTextField tfCompatability;
    @FXML
    private JFXButton btnInsert;

    private ArrayList<ReplacementParts> replacementPartsList;
    private ObservableList<ReplacementParts> dataList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            getDataReplacementParts();
            showDataReplacementParts();
            setTableEditable();
        } catch (Exception e) {
            System.out.println("Could not fetch data");
        }
    }

    // The below Function references is: Youtube channel: Cool IT Help | JavaFX TableView with Search Filter
    private void search() {
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<ReplacementParts> filteredData = new FilteredList<>(dataList, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(replacementpart -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (replacementpart.getCompatibility() != null && replacementpart.getCompatibility().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (replacementpart.getSerialNo() != null && replacementpart.getSerialNo().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (replacementpart.getPartType() != null && replacementpart.getPartType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(replacementpart.getAmountInStock()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(replacementpart.getPrice()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<ReplacementParts> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tvWarehouse.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tvWarehouse.setItems(sortedData);
    }

    private void showDataReplacementParts() {
        colSerialNumber.setCellValueFactory(new PropertyValueFactory<ReplacementParts, String>("SerialNo"));
        colAmountInStock.setCellValueFactory(new PropertyValueFactory<ReplacementParts, Integer>("AmountInStock"));
        colCompatability.setCellValueFactory(new PropertyValueFactory<ReplacementParts, String>("Compatibility"));
        colPartType.setCellValueFactory(new PropertyValueFactory<ReplacementParts, String>("PartType"));
        colPrice.setCellValueFactory(new PropertyValueFactory<ReplacementParts, Double>("Price"));
        search();
        System.out.println("We're here now");
    }

    private void getDataReplacementParts() throws Exception {
        replacementPartsList = new ArrayList<>();
        String SQL;
        MyConnection.connectDB();
        System.out.println("Connection \n\n\n");
        SQL = "select * from replacement_parts;";
        Statement stmt = MyConnection.con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        try {

            while (rs.next()) {
                replacementPartsList.add(new ReplacementParts(rs.getString(1), rs.getString(3), Integer.parseInt(rs.getString(2)), rs.getString(5), Double.parseDouble(rs.getString(4))));
            }
        } catch (Exception e) {
            System.out.println("Error in reading data");
        }
        rs.close();
        stmt.close();
        MyConnection.con.close();

        System.out.println("Connection closed" + replacementPartsList.size());
        dataList = FXCollections.observableArrayList(replacementPartsList);
        System.out.println(dataList);
    }

    private void setTableEditable() {
        tvWarehouse.setEditable(true);
        colCompatability.setCellFactory(TextFieldTableCell.<ReplacementParts>forTableColumn());
        colPartType.setCellFactory(TextFieldTableCell.<ReplacementParts>forTableColumn());
        colAmountInStock.setCellFactory(TextFieldTableCell.<ReplacementParts, Integer>forTableColumn(new IntegerStringConverter()));
        colPrice.setCellFactory(TextFieldTableCell.<ReplacementParts, Double>forTableColumn(new DoubleStringConverter()));
    }

    @FXML
    private void editAmountInStock(TableColumn.CellEditEvent<ReplacementParts, Integer> t) {
        ((ReplacementParts) t.getTableView().getItems().get(
                t.getTablePosition().getRow())).setAmountInStock(t.getNewValue());
        updateAmountInStock(t.getRowValue().getSerialNo(), t.getNewValue());
        try {
            getDataReplacementParts();
            showDataReplacementParts();
        } catch (Exception e) {
            System.out.println("Could not fetch data");
        }
    }

    private void updateAmountInStock(String serialNo, int amountInStock) {
        try {
            MyConnection.connectDB();
            MyConnection.ExecuteStatement("update replacement_parts set amount_in_stock= '" + amountInStock + "' where serial_no = " + serialNo + ";");
            MyConnection.con.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void editPartType(TableColumn.CellEditEvent<ReplacementParts, String> t) {
        ((ReplacementParts) t.getTableView().getItems().get(
                t.getTablePosition().getRow())).setPartType(t.getNewValue());
        updatePartType(t.getRowValue().getSerialNo(), t.getNewValue());
        try {
            getDataReplacementParts();
            showDataReplacementParts();
        } catch (Exception e) {
            System.out.println("Could not fetch data");
        }
    }

    private void updatePartType(String serialNo, String partType) {
        try {
            MyConnection.connectDB();
            MyConnection.ExecuteStatement("update replacement_parts set part_type= '" + partType + "' where serial_no = '" + serialNo + "';");
            MyConnection.con.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void editPrice(TableColumn.CellEditEvent<ReplacementParts, Double> t) {
        ((ReplacementParts) t.getTableView().getItems().get(
                t.getTablePosition().getRow())).setPrice(t.getNewValue());
        updatePrice(t.getRowValue().getSerialNo(), t.getNewValue());
        try {
            getDataReplacementParts();
            showDataReplacementParts();
        } catch (Exception e) {
            System.out.println("Could not fetch data");
        }
    }

    private void updatePrice(String serialNo, double price) {
        try {
            MyConnection.connectDB();
            MyConnection.ExecuteStatement("update replacement_parts set price= '" + price + "' where serial_no = " + serialNo + ";");
            MyConnection.con.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void editCompatability(TableColumn.CellEditEvent<ReplacementParts, String> t) {
        ((ReplacementParts) t.getTableView().getItems().get(
                t.getTablePosition().getRow())).setCompatibility(t.getNewValue());
        updateCompatability(t.getRowValue().getSerialNo(), t.getNewValue());
        try {
            getDataReplacementParts();
            showDataReplacementParts();
        } catch (Exception e) {
            System.out.println("Could not fetch data");
        }
    }

    private void updateCompatability(String serialNo, String compatability) {
        try {
            MyConnection.connectDB();
            MyConnection.ExecuteStatement("update replacement_parts set compatability = '" + compatability + "' where serial_no = " + serialNo + ";");
            MyConnection.con.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnInsertClicked(ActionEvent event) throws Exception {
        try {
            if (tfSerialNo.getText().isEmpty() || tfAmount.getText().isEmpty() || tfCompatability.getText().isEmpty() || tfPartType.getText().isEmpty() || tfPrice.getText().isEmpty()) {
                throw new NullPointerException();
            }
            ArrayList<ReplacementParts> searchResult = new ArrayList<>();
            String serialNo = tfSerialNo.getText();
            String partType = tfPartType.getText();
            int amount = Integer.parseInt(tfAmount.getText());
            double price = Double.parseDouble(tfPrice.getText());
            String compatability = tfCompatability.getText();
            MyConnection.connectDB();
            System.out.println("Connection \n\n\n");
            String SQL = "select * from replacement_parts where serial_No='" + serialNo + "';";
            Statement stmt = MyConnection.con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            try {
                while (rs.next()) {
                    searchResult.add(new ReplacementParts(serialNo, partType, amount, compatability, price));
                }
            } catch (Exception e) {
                System.out.println("Error in reading data");
            }
            rs.close();
            stmt.close();
            if (searchResult.size() != 0) {
                throw new SQLException();
            }
            SQL = "insert into replacement_parts (serial_no, amount_in_stock, part_type,price, compatability) values ('" + serialNo + "'," + amount + ",'" + partType + "'," + price + ",'" + compatability + "');";
            MyConnection.ExecuteStatement(SQL);
            MyConnection.con.close();
            getDataReplacementParts();
            showDataReplacementParts();

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Missing Info");
            alert.setContentText("Please fill all fields");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Inputs");
            alert.setContentText("Please fill proper data types");
            alert.showAndWait();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Inputs");
            alert.setContentText("This serial number already exists. You can modify amount in stock from previous tab");
            alert.showAndWait();
        }
    }
}
