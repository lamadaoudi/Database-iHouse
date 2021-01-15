/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_ihouse_project_final_version;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.sql.Statement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Main
 */
public class LoginFormController implements Initializable {

    @FXML
    private JFXTextField tfUsername;
    @FXML
    private JFXPasswordField pfPassword;
    @FXML
    private JFXButton btnLogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnLoginClicked(ActionEvent event) {
        System.out.println(tfUsername.getText());
        if (tfUsername.getText().isEmpty() || tfUsername.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("An Input Field is Empty");
            alert.setContentText("Please fill up all Input Fields");
            alert.showAndWait();
        } else {
            try {
                MyConnection.setDbUsername(tfUsername.getText().trim());
                MyConnection.setDbPassword(pfPassword.getText().trim());
                MyConnection.connectDB();
                String SQL="use ihouse";
                Statement stmt = MyConnection.con.createStatement();
                stmt.execute(SQL);
                MyConnection.con.close();
                Stage stage = (Stage) btnLogin.getScene().getWindow();
                stage.close();
                //Must add the dashboard here
                final Stage primaryStage2 = new Stage();
                primaryStage2.initModality(Modality.APPLICATION_MODAL);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage2.initOwner(app_stage);
                Parent nextSceneParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Scene scene11 = new Scene(nextSceneParent);
                primaryStage2.setScene(scene11);
                primaryStage2.show();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Incorrect info");
                alert.setContentText("Incorrect info. Please make sure that the data entered belongs to an authorized administrator");
                alert.showAndWait();
                e.printStackTrace();
            }

        }
    
    }
    
}
