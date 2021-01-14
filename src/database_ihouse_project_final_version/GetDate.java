package database_ihouse_project_final_version;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Main
 */

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author Main
 */
public class GetDate {

    //THERE IS AN ERROR KEEPS POPPING THE SAME WINDOW
    static Date pickedDate;

    public static Date display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Date Update");

        window.setMaxWidth(600);
        window.setWidth(250);
        window.setHeight(250);
        Label label = new Label();
        label.setText("Enter the date");

        DatePicker picker = new DatePicker();
        Button update = new Button("Update");

        update.setOnAction(e -> {
            LocalDate localDate = picker.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date DOB = Date.from(instant);
            pickedDate = DOB;
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, picker, update);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();

        return pickedDate;

    }

}

