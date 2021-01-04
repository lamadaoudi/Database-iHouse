/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_ihouse_project_final_version;

import Classes.Problems;
import Classes.RepInfo;
import Classes.RepPart;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author omarq
 */
public class PayFXMLController implements Initializable {

    private ArrayList<RepInfo> data;
    @FXML
    private AnchorPane container;
    @FXML
    private JFXButton Okbtn;
    @FXML
    private JFXButton nextbtn1;
    @FXML
    private JFXTextField CustText;
    @FXML
    private JFXButton Button1;
    @FXML
    private JFXButton Button2;
    @FXML
    private JFXButton Button3;
    @FXML
    private JFXButton Button4;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label AveDev;
    @FXML
    private JFXButton Button5;
    @FXML
    private JFXButton Button6;
    @FXML
    private JFXTabPane tabPane;
    @FXML
    private ToggleGroup ToggGroup1;
    @FXML
    private JFXButton CheckOut;
    @FXML
    private Tab devicetab;
    @FXML
    private Tab PaymentTab;
    @FXML
    private Tab Checkouttab;
    @FXML
    private RadioButton CardTog;
    @FXML
    private RadioButton CashTog;
    @FXML
    private JFXButton PrintButton;
    @FXML
    private JFXButton Exitbutton;
    @FXML
    private Label RecLab;
    @FXML
    private Label totalLab;
    @FXML
    private Label Top;
    @FXML
    private Label thank;
    @FXML
    private Label DATE;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //  devicetab.setDisable(true);
        PaymentTab.setDisable(true);
        Checkouttab.setDisable(true);
        AveDev.setVisible(false);
        label2.setVisible(false);
        label1.setVisible(false);
        Button1.setVisible(false);
        Button2.setVisible(false);
        Button3.setVisible(false);
        Button4.setVisible(false);
        Button5.setVisible(false);
        Button6.setVisible(false);
        RecLab.setVisible(false);
        totalLab.setVisible(false);
        thank.setVisible(false);
        fadeIn.setNode(Button1);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.setCycleCount(1);
        fadeIn.setAutoReverse(false);
        fadeIn1.setNode(Button2);
        fadeIn1.setFromValue(0.0);
        fadeIn1.setToValue(1.0);
        fadeIn1.setCycleCount(1);
        fadeIn1.setAutoReverse(false);
        fadeIn2.setNode(Button3);
        fadeIn2.setFromValue(0.0);
        fadeIn2.setToValue(1.0);
        fadeIn2.setCycleCount(1);
        fadeIn2.setAutoReverse(false);
        fadeIn3.setNode(Button4);
        fadeIn3.setFromValue(0.0);
        fadeIn3.setToValue(1.0);
        fadeIn3.setCycleCount(1);
        fadeIn3.setAutoReverse(false);
        fadeIn4.setNode(label1);
        fadeIn4.setFromValue(0.0);
        fadeIn4.setToValue(1.0);
        fadeIn4.setCycleCount(1);
        fadeIn4.setAutoReverse(false);
        fadeIn5.setNode(label2);
        fadeIn5.setFromValue(0.0);
        fadeIn5.setToValue(1.0);
        fadeIn5.setCycleCount(1);
        fadeIn5.setAutoReverse(false);
        fadeIn6.setNode(Button5);
        fadeIn6.setFromValue(0.0);
        fadeIn6.setToValue(1.0);
        fadeIn6.setCycleCount(1);
        fadeIn6.setAutoReverse(false);
        fadeIn7.setNode(Button6);
        fadeIn7.setFromValue(0.0);
        fadeIn7.setToValue(1.0);
        fadeIn7.setCycleCount(1);
        fadeIn7.setAutoReverse(false);
        fadeIn8.setNode(RecLab);
        fadeIn8.setFromValue(0.0);
        fadeIn8.setToValue(1.0);
        fadeIn8.setCycleCount(1);
        fadeIn8.setAutoReverse(false);
        fadeIn9.setNode(totalLab);
        fadeIn9.setFromValue(0.0);
        fadeIn9.setToValue(1.0);
        fadeIn9.setCycleCount(1);
        fadeIn9.setAutoReverse(false);
        fadeIn10.setNode(thank);
        fadeIn10.setFromValue(0.0);
        fadeIn10.setToValue(1.0);
        fadeIn10.setCycleCount(1);
        fadeIn10.setAutoReverse(false);

    }
    private FadeTransition fadeIn = new FadeTransition(
            Duration.millis(300)
    );
    private FadeTransition fadeIn1 = new FadeTransition(
            Duration.millis(700)
    );
    private FadeTransition fadeIn2 = new FadeTransition(
            Duration.millis(1100)
    );
    private FadeTransition fadeIn3 = new FadeTransition(
            Duration.millis(1500)
    );
    private FadeTransition fadeIn4 = new FadeTransition(
            Duration.millis(300)
    );
    private FadeTransition fadeIn5 = new FadeTransition(
            Duration.millis(300)
    );
    private FadeTransition fadeIn6 = new FadeTransition(
            Duration.millis(1900)
    );
    private FadeTransition fadeIn7 = new FadeTransition(
            Duration.millis(2100)
    );
    private FadeTransition fadeIn8 = new FadeTransition(
            Duration.millis(1000)
    );
    private FadeTransition fadeIn9= new FadeTransition(
            Duration.millis(3000)
    );
    private FadeTransition fadeIn10 = new FadeTransition(
            Duration.millis(3500)
    );
    private double PartsCost = 0;
    private int id;
    private double total = 0;
    private int RepID = 0;
    private double tax=0;
    @FXML
    private void OkbtnClick(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        label1.setVisible(false);
        if (CustText.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Incorrect info");
            alert.setContentText("Please Enter The CustomerID");
            alert.showAndWait();

        } else {
            id = Integer.parseInt(CustText.getText());
            MyConnection.connectDB();
            String SQL;
            SQL = "SELECT EXISTS(SELECT * from customer WHERE customer.customer_id =" + id + ");";
            Statement stmt1 = MyConnection.con.createStatement();
            ResultSet rs1 = stmt1.executeQuery(SQL);
            int test = 0;
            try {
                while (rs1.next()) {
                    test = Integer.parseInt(rs1.getString(1));
                   // System.out.println(test);
                    //System.out.println("YES");
                }

            } catch (SQLException e) {
                System.out.println("Error in loop");
                System.exit(1);
            }

            if (test != 1) {
                label1.setText("Customer ID Dosen't Exist");
                label1.setVisible(true);
                fadeIn4.playFromStart();
                AveDev.setVisible(false);
                label2.setVisible(false);
                Button1.setVisible(false);
                Button2.setVisible(false);
                Button3.setVisible(false);
                Button4.setVisible(false);
                Button5.setVisible(false);
                Button6.setVisible(false);

            } else if (test == 1) {
                AveDev.setVisible(true);
                data = new ArrayList<>();

                int test2 = 0;
                SQL = "select exists(select distinct R.repair_id , D.device_type,D.model_no\n"
                        + "from CustReqRep R,device D,customer C,repairJob j\n"
                        + "where C.customer_id=" + id + "\n"
                        + "and C.customer_id=R.id_num\n"
                        + "and R.repair_id=j.repair_id\n"
                        + "and j.job_status=\"finished\"\n"
                        + "and D.repair_id=R.repair_id);";
                Statement stmt2 = MyConnection.con.createStatement();
                ResultSet rs2 = stmt2.executeQuery(SQL);

                try {
                    while (rs2.next()) {
                        test2 = Integer.parseInt(rs2.getString(1));

                    }

                } catch (SQLException e) {
                    System.out.println("Error in loop");
                    System.exit(1);
                }
                if (test2 != 1) {
                    label1.setText("No Finished Jobs Yet");
                    label1.setVisible(true);
                    fadeIn4.playFromStart();
                    AveDev.setVisible(false);
                    label2.setVisible(false);
                    Button1.setVisible(false);
                    Button2.setVisible(false);
                    Button3.setVisible(false);
                    Button4.setVisible(false);
                    Button5.setVisible(false);
                    Button6.setVisible(false);
                } else if (test2 == 1) {
                    SQL = "select distinct R.repair_id , D.device_type,D.model_no\n"
                            + "from CustReqRep R,device D,customer C,repairJob j\n"
                            + "where C.customer_id=" + id + "\n"
                            + "and C.customer_id=R.id_num\n"
                            + "and R.repair_id=j.repair_id\n"
                            + "and j.job_status=\"finished\"\n"
                            + "and D.repair_id=R.repair_id;";
                    Statement stmt = MyConnection.con.createStatement();
                    ResultSet rs = stmt.executeQuery(SQL);
                    try {
                        while (rs.next()) {
                            data.add(new RepInfo(
                                    Integer.parseInt(rs.getString(1)),
                                    rs.getString(2),
                                    rs.getString(3)));
                           // System.out.println(data.get(0).toString());
                        }
                    } catch (SQLException e1) {
                        System.out.println("Error in loop");
                        System.exit(1);
                    }
                    if (data.size() == 1) {
                        Button1.setText(data.get(0).toString());
                        Button1.setVisible(true);
                        fadeIn.playFromStart();
                    } else if (data.size() == 2) {
                        Button1.setText(data.get(0).toString());
                        Button1.setVisible(true);
                        fadeIn.playFromStart();
                        Button2.setText(data.get(1).toString());
                        Button2.setVisible(true);
                        fadeIn1.playFromStart();
                    } else if (data.size() == 3) {
                        Button1.setText(data.get(0).toString());
                        Button1.setVisible(true);
                        fadeIn.playFromStart();
                        Button2.setText(data.get(1).toString());
                        Button2.setVisible(true);
                        fadeIn1.playFromStart();
                        Button3.setText(data.get(2).toString());
                        Button3.setVisible(true);
                        fadeIn2.playFromStart();

                    } else if (data.size() == 4) {
                        Button1.setText(data.get(0).toString());
                        Button1.setVisible(true);
                        fadeIn.playFromStart();
                        Button2.setText(data.get(1).toString());
                        Button2.setVisible(true);
                        fadeIn1.playFromStart();
                        Button3.setText(data.get(2).toString());
                        Button3.setVisible(true);
                        fadeIn2.playFromStart();
                        Button4.setText(data.get(3).toString());
                        Button4.setVisible(true);
                        fadeIn3.playFromStart();
                    } else if (data.size() == 5) {
                        Button1.setText(data.get(0).toString());
                        Button1.setVisible(true);
                        fadeIn.playFromStart();
                        Button2.setText(data.get(1).toString());
                        Button2.setVisible(true);
                        fadeIn1.playFromStart();
                        Button3.setText(data.get(2).toString());
                        Button3.setVisible(true);
                        fadeIn2.playFromStart();
                        Button4.setText(data.get(3).toString());
                        Button4.setVisible(true);
                        fadeIn3.playFromStart();
                        Button5.setText(data.get(4).toString());
                        Button5.setVisible(true);
                        fadeIn6.playFromStart();
                    } else {
                        Button1.setText(data.get(0).toString());
                        Button1.setVisible(true);
                        fadeIn.playFromStart();
                        Button2.setText(data.get(1).toString());
                        Button2.setVisible(true);
                        fadeIn1.playFromStart();
                        Button3.setText(data.get(2).toString());
                        Button3.setVisible(true);
                        fadeIn2.playFromStart();
                        Button4.setText(data.get(3).toString());
                        Button4.setVisible(true);
                        fadeIn3.playFromStart();
                        Button5.setText(data.get(4).toString());
                        Button5.setVisible(true);
                        fadeIn6.playFromStart();
                        Button6.setText(data.get(5).toString());
                        Button6.setVisible(true);
                        fadeIn7.playFromStart();
                    }
                }
            }

        }

    }
    public double ProblemsCost = 0;

    @FXML
    private void button1clicked(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        MyConnection.connectDB();
        RepID = data.get(0).getRepID();
        String SQL;
        SQL = "select sum((RP.price*RN.amount))\n"
                + "from RepNeedsRep RN,replacement_parts RP,repairJob j\n"
                + "where j.repair_id=RN.repair_id\n"
                + "and RN.serial_no=Rp.serial_no\n"
                + "and j.repair_id=" + data.get(0).getRepID() + ";";
        Statement stmt1 = MyConnection.con.createStatement();
        ResultSet rs = stmt1.executeQuery(SQL);
        try {
            while (rs.next()) {
                PartsCost = Double.parseDouble(rs.getString(1));
                System.out.println(PartsCost);
            }
        } catch (SQLException e1) {

            System.exit(1);
        }
        SQL = "select sum(k.price)\n"
                + "from repairJob j ,repair_problems k\n"
                + "where k.repair_id=j.repair_id\n"
                + "and j.repair_id=" + data.get(0).getRepID() + ";";
        Statement stmt = MyConnection.con.createStatement();
        ResultSet rs1 = stmt.executeQuery(SQL);
        try {
            while (rs1.next()) {
                ProblemsCost = Double.parseDouble(rs1.getString(1));

            }
        } catch (SQLException e) {
            System.out.println("Error in loop");
            System.exit(1);
        }
        tax=0.16*(ProblemsCost + PartsCost);
        total = ProblemsCost + PartsCost + (0.16 * (ProblemsCost + PartsCost));
        String s = String.valueOf(total);
        label2.setText("Amount to Pay: " + s);
        label2.setVisible(true);
        fadeIn5.playFromStart();

    }

    @FXML
    private void button2clicked(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        RepID = data.get(1).getRepID();
        MyConnection.connectDB();

        String SQL;
        SQL = "select sum((RP.price*RN.amount))\n"
                + "from RepNeedsRep RN,replacement_parts RP,repairJob j\n"
                + "where j.repair_id=RN.repair_id\n"
                + "and RN.serial_no=Rp.serial_no\n"
                + "and j.repair_id=" + data.get(1).getRepID() + ";";
        Statement stmt1 = MyConnection.con.createStatement();
        ResultSet rs = stmt1.executeQuery(SQL);
        try {
            while (rs.next()) {
                PartsCost = Double.parseDouble(rs.getString(1));
                System.out.println(PartsCost);
            }
        } catch (SQLException e1) {

            System.exit(1);
        }
        SQL = "select sum(k.price)\n"
                + "from repairJob j ,repair_problems k\n"
                + "where k.repair_id=j.repair_id\n"
                + "and j.repair_id=" + data.get(1).getRepID() + ";";
        Statement stmt = MyConnection.con.createStatement();
        ResultSet rs1 = stmt.executeQuery(SQL);
        try {
            while (rs1.next()) {
                ProblemsCost = Double.parseDouble(rs1.getString(1));

            }
        } catch (SQLException e) {
            System.out.println("Error in loop");
            System.exit(1);
        }
        tax=0.16*(ProblemsCost + PartsCost);
        total = ProblemsCost + PartsCost + (0.16 * (ProblemsCost + PartsCost));
        String s = String.valueOf(total);
        label2.setText("Amount to Pay: " + s);
        label2.setVisible(true);
        fadeIn5.playFromStart();

    }

    @FXML
    private void button3clicked(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        RepID = data.get(2).getRepID();
        MyConnection.connectDB();

        String SQL;
        SQL = "select sum((RP.price*RN.amount))\n"
                + "from RepNeedsRep RN,replacement_parts RP,repairJob j\n"
                + "where j.repair_id=RN.repair_id\n"
                + "and RN.serial_no=Rp.serial_no\n"
                + "and j.repair_id=" + data.get(2).getRepID() + ";";
        Statement stmt1 = MyConnection.con.createStatement();
        ResultSet rs = stmt1.executeQuery(SQL);
        try {
            while (rs.next()) {
                PartsCost = Double.parseDouble(rs.getString(1));
                System.out.println(PartsCost);
            }
        } catch (SQLException e1) {

            System.exit(1);
        }
        SQL = "select sum(k.price)\n"
                + "from repairJob j ,repair_problems k\n"
                + "where k.repair_id=j.repair_id\n"
                + "and j.repair_id=" + data.get(2).getRepID() + ";";
        Statement stmt = MyConnection.con.createStatement();
        ResultSet rs1 = stmt.executeQuery(SQL);
        try {
            while (rs1.next()) {
                ProblemsCost = Double.parseDouble(rs1.getString(1));

            }
        } catch (SQLException e) {
            System.out.println("Error in loop");
            System.exit(1);
        }
        tax=0.16*(ProblemsCost + PartsCost);
        total = ProblemsCost + PartsCost + (0.16 * (ProblemsCost + PartsCost));
        String s = String.valueOf(total);
        label2.setText("Amount to Pay: " + s);
        label2.setVisible(true);
        fadeIn5.playFromStart();

    }

    @FXML
    private void button4clicked(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        RepID = data.get(3).getRepID();
        MyConnection.connectDB();

        String SQL;
        SQL = "select sum((RP.price*RN.amount))\n"
                + "from RepNeedsRep RN,replacement_parts RP,repairJob j\n"
                + "where j.repair_id=RN.repair_id\n"
                + "and RN.serial_no=Rp.serial_no\n"
                + "and j.repair_id=" + data.get(3).getRepID() + ";";
        Statement stmt1 = MyConnection.con.createStatement();
        ResultSet rs = stmt1.executeQuery(SQL);
        try {
            while (rs.next()) {
                PartsCost = Double.parseDouble(rs.getString(1));
                System.out.println(PartsCost);
            }
        } catch (SQLException e1) {

            System.exit(1);
        }
        SQL = "select sum(k.price)\n"
                + "from repairJob j ,repair_problems k\n"
                + "where k.repair_id=j.repair_id\n"
                + "and j.repair_id=" + data.get(3).getRepID() + ";";
        Statement stmt = MyConnection.con.createStatement();
        ResultSet rs1 = stmt.executeQuery(SQL);
        try {
            while (rs1.next()) {
                ProblemsCost = Double.parseDouble(rs1.getString(1));

            }
        } catch (SQLException e) {
            System.out.println("Error in loop");
            System.exit(1);
        }
        tax=0.16*(ProblemsCost + PartsCost);
        total = ProblemsCost + PartsCost + (0.16 * (ProblemsCost + PartsCost));
        String s = String.valueOf(total);
        label2.setText("Amount to Pay: " + s);
        label2.setVisible(true);
        fadeIn5.playFromStart();

    }

    @FXML
    private void button5clicked(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        RepID = data.get(4).getRepID();
        MyConnection.connectDB();

        String SQL;
        SQL = "select sum((RP.price*RN.amount))\n"
                + "from RepNeedsRep RN,replacement_parts RP,repairJob j\n"
                + "where j.repair_id=RN.repair_id\n"
                + "and RN.serial_no=Rp.serial_no\n"
                + "and j.repair_id=" + data.get(4).getRepID() + ";";
        Statement stmt1 = MyConnection.con.createStatement();
        ResultSet rs = stmt1.executeQuery(SQL);
        try {
            while (rs.next()) {
                PartsCost = Double.parseDouble(rs.getString(1));
                System.out.println(PartsCost);
            }
        } catch (SQLException e1) {

            System.exit(1);
        }
        SQL = "select sum(k.price)\n"
                + "from repairJob j ,repair_problems k\n"
                + "where k.repair_id=j.repair_id\n"
                + "and j.repair_id=" + data.get(4).getRepID() + ";";
        Statement stmt = MyConnection.con.createStatement();
        ResultSet rs1 = stmt.executeQuery(SQL);
        try {
            while (rs1.next()) {
                ProblemsCost = Double.parseDouble(rs1.getString(1));

            }
        } catch (SQLException e) {
            System.out.println("Error in loop");
            System.exit(1);
        }
        tax=0.16*(ProblemsCost + PartsCost);
        total = ProblemsCost + PartsCost + (0.16 * (ProblemsCost + PartsCost));
        String s = String.valueOf(total);
        label2.setText("Amount to Pay: " + s);
        label2.setVisible(true);
        fadeIn5.playFromStart();

    }

    @FXML
    private void button6clicked(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        RepID = data.get(5).getRepID();
        MyConnection.connectDB();

        String SQL;
        SQL = "select sum((RP.price*RN.amount))\n"
                + "from RepNeedsRep RN,replacement_parts RP,repairJob j\n"
                + "where j.repair_id=RN.repair_id\n"
                + "and RN.serial_no=Rp.serial_no\n"
                + "and j.repair_id=" + data.get(5).getRepID() + ";";
        Statement stmt1 = MyConnection.con.createStatement();
        ResultSet rs = stmt1.executeQuery(SQL);
        try {
            while (rs.next()) {
                PartsCost = Double.parseDouble(rs.getString(1));
               // System.out.println(PartsCost);
            }
        } catch (SQLException e1) {

            System.exit(1);
        }
        SQL = "select sum(k.price)\n"
                + "from repairJob j ,repair_problems k\n"
                + "where k.repair_id=j.repair_id\n"
                + "and j.repair_id=" + data.get(5).getRepID() + ";";
        Statement stmt = MyConnection.con.createStatement();
        ResultSet rs1 = stmt.executeQuery(SQL);
        try {
            while (rs1.next()) {
                ProblemsCost = Double.parseDouble(rs1.getString(1));

            }
        } catch (SQLException e) {
            System.out.println("Error in loop");
            System.exit(1);
        }
        tax=0.16*(ProblemsCost + PartsCost);
        total = ProblemsCost + PartsCost + (0.16 * (ProblemsCost + PartsCost));
        String s = String.valueOf(total);
        label2.setText("Amount to Pay: " + s);
        label2.setVisible(true);
        fadeIn5.playFromStart();

    }

    @FXML
    private void nextBtnClick(ActionEvent event) {
        PaymentTab.setDisable(false);
        tabPane.getSelectionModel().selectNext();
    }
    private String Method;

    @FXML
    private void CheckBtnClick(ActionEvent event) throws ClassNotFoundException, SQLException {
        Checkouttab.setDisable(false);
        tabPane.getSelectionModel().selectNext();
        if (CardTog.isSelected()) {
            Method = CardTog.getText();

        }
       else if (CashTog.isSelected()) {
            Method = CashTog.getText();

        }
       else if(CashTog.isSelected()!=true && CardTog.isSelected()!=true){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Info");
            alert.setContentText("Please Select A Payment Mehtod");
            alert.showAndWait();
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date todayDate = new Date();
        //System.out.println(df.format(todayDate));
        MyConnection.connectDB();
        Statement stmt = MyConnection.con.createStatement();
        String SQL = "insert into payment(amount,received_date,payment_method,customer_id,repair_id)values(" + total + "," + "'" + df.format(todayDate) + "'" + "," + "'" + Method + "'" + "," + id + "," + RepID + ");";
        stmt.execute(SQL);
        SQL= "update repairJob set job_status="+"'closed'"+" where repair_id="+RepID+";";
         stmt.execute(SQL);
        SQL = ("select k.problem,k.price\n"
                + "from repairJob j ,repair_problems k\n"
                + "where k.repair_id=j.repair_id\n"
                + "and j.repair_id=" + RepID + ";");

        ArrayList prob = new ArrayList<Problems>();
        ResultSet rs = stmt.executeQuery(SQL);
        try {
            while (rs.next()) {
                prob.add(new Problems(
                        rs.getString(1),
                        Integer.parseInt(rs.getString(2))
                ));
            }
        } catch (SQLException e1) {
            System.out.println("Error in loop");
            System.exit(1);
        }
        ArrayList RepPart = new ArrayList<RepPart>();
        SQL = "select R.part_type,RN.amount,R.price\n"
                + "from RepNeedsRep RN,replacement_parts R\n"
                + "where RN.repair_id="+RepID+"\n"
                + "and R.serial_no=RN.serial_no;" ;
                 rs=stmt.executeQuery(SQL);
                 try {
            while (rs.next()) {
                RepPart.add(new RepPart(
                        rs.getString(1),
                        Integer.parseInt(rs.getString(2)),
                        Integer.parseInt(rs.getString(3))
                ));
            }
        } catch (SQLException e1) {
            System.out.println("Error in loop");
            System.exit(1);
        }  
                 
                 String TotalInfo="";
                 for(int i=0;i<prob.size();i++){
                     TotalInfo=(new StringBuilder().append(TotalInfo).append(prob.get(i).toString()).append("\n").toString());
                    //System.out.println("hi");
                 }
                // System.out.println(TotalInfo);
                 for(int i=0;i<RepPart.size();i++){
                     TotalInfo=TotalInfo.concat(RepPart.get(i).toString());
                    TotalInfo= TotalInfo.concat("\n");
                 }
                // System.out.println(tax);
                 TotalInfo=TotalInfo.concat("Tax"+".........................."+String.valueOf(tax));
                
                 RecLab.setFont(new Font("Book Antiqua",14));
                 RecLab.setText(TotalInfo);
              RecLab.setVisible(true);
                 fadeIn8.playFromStart();
                 totalLab.setText("Total: "+total);
                 totalLab.setFont(new Font("Book Antiqua",18));
                 totalLab.setVisible(true);
                 fadeIn9.playFromStart();
                 thank.setFont(new Font("Book Antiqua",12));
                 thank.setVisible(true);
                 fadeIn10.playFromStart();
                 Top.setFont(new Font("Book Antiqua",16));
                 DATE.setText(df.format(todayDate).toString());
                 DATE.setFont(new Font("Book Antiqua",14));
                  SQL="select max(payment_no)\n" +
                      "from payment;";
                    rs = stmt.executeQuery(SQL);
                    int paymentno=0;
           try {
               while (rs.next()) {
                  paymentno = Integer.parseInt(rs.getString(1));
                //  System.out.println(PartsCost);
            }
        } catch (SQLException e1) {
            System.exit(1);
        }
                 SQL = "insert into receipt values("+"'"+df.format(todayDate)+"'"+","+paymentno+","+"'"+TotalInfo+"'"+");";
                 stmt.execute(SQL);
                       
               
    
    }
    @FXML
    private void exitBtn(ActionEvent event){
        Stage stage = (Stage) Exitbutton.getScene().getWindow();
    // do what you have to do
    stage.close();
    }
    

}
