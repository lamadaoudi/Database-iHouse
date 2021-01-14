/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_ihouse_project_final_version;

import Classes.Employee;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Main
 */
public class UpdateDateController implements Initializable {

    public static Employee rc;
    @FXML
    private JFXDatePicker datePicker;
    @FXML
    private JFXButton btnUpdate;

    public static void setEmployee(Employee employee) {
        rc = employee;

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnUpdateClicked(ActionEvent t) {
        LocalDate localDate = datePicker.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date DOB = Date.from(instant);
        EmployeeController.updatedDate = DOB;
        for (int i = 0; i < EmployeeController.dataList.size(); i++) {
            if (EmployeeController.dataList.get(i).getId_num() == rc.getId_num()) {
                EmployeeController.dataList.remove(i);
                System.out.println("Deleted");
            }
        }
        rc.setEmp_start_date(DOB);
        EmployeeController.dataList.add(rc);
        System.out.println(rc);
        Stage stage = (Stage) btnUpdate.getScene().getWindow();
        stage.close();
    }

}
