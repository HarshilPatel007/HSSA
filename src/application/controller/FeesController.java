package application.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class FeesController implements Initializable {

  public TextField stdNameBox;
  public TextField stdFatherNameBox;
  public TextField stdAadhaarBox;
  public TextField stdEmailBox;
  public TextField stdAcademicYearBox;
  public TextField stdClassBox;
  public TextField stdEnrollNoBox;
  public Button feesStdLoadBtn;
  public ImageView imageView;
  public Button feesResetBtn1;

  public TextField Admsnfees;
  public TextField DevFees;
  public TextField term1;
  public TextField term2;
  public TextField term3;
  public TextField term4;
  public Label totalfees;
  public TextField stdEnrollNoBox1;
  public TextField feesYesNoBox;
  public TextField TotalFeesAmount;
  public Button feesSubmitBtn1;


  public void btnMinEvent(MouseEvent mouseEvent) {
    Stage stage = (Stage) ((Circle) mouseEvent.getSource()).getScene().getWindow();
    stage.setIconified(true);
  }

  public void btnBackEvent(MouseEvent mouseEvent) {
    Stage stage = (Stage) ((Circle) mouseEvent.getSource()).getScene().getWindow();
    stage.close();
  }

  public void feesStdLoadBtnAction(ActionEvent actionEvent) {
    if (stdEnrollNoBox.getText().trim().isEmpty()) {

      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Please enter the enrollment number.");
      alert.setHeaderText("Field can't be empty.");
      alert.setContentText("Please enter enrollment number of student.");
      alert.showAndWait();

    } else {

      DBConnect dbConnect = new DBConnect();
      Connection connection = dbConnect.createConnection();

      PreparedStatement pst = null;
      ResultSet rs = null;

      String SQLQuery = "SELECT name, fathername, aadhaarno, email, image, academic_year, class FROM student where enrollno = ?";

      try {

        pst = connection.prepareStatement(SQLQuery);
        pst.setString(1, stdEnrollNoBox.getText());

        rs = pst.executeQuery();

        while (rs.next()) {

          stdNameBox.setText(rs.getString("name"));
          stdFatherNameBox.setText(rs.getString("fathername"));
          stdAadhaarBox.setText(rs.getString("aadhaarno"));
          stdEmailBox.setText(rs.getString("email"));
          stdAcademicYearBox.setText(rs.getString("academic_year"));
          stdClassBox.setText(rs.getString("class"));

          InputStream is = rs.getBinaryStream("image");
          OutputStream os = new FileOutputStream(new File("image.jpg"));
          byte[] content = new byte[1024];
          int size = 0;
          while ((size = is.read(content)) != -1){
            os.write(content, 0, size);
          }
          os.close();
          is.close();
          javafx.scene.image.Image image = new Image("file:image.jpg",200,255,true,true,true);
          imageView.setImage(image);
        }

      } catch (SQLException e) {
//                e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Failed to retrieve the admission form.");
        alert.setHeaderText("The student admission form is not retrieve.");
        alert.setContentText("There is an error occurred during retrieving admission form.\n\n Possible Checks: \n\n 1) Please check database service is running perfectly. \n 2) Please check database, tables & columns is configured properly. \n 3) Maybe the enrollment number are you trying to fetch is not available. \n 4) Please check the data are you entered.");
        alert.getDialogPane().setPrefSize(480, 251);
        alert.showAndWait();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

  public void feesResetBtn1Action(ActionEvent actionEvent) {

    stdNameBox.clear();
    stdFatherNameBox.clear();
    stdAadhaarBox.clear();
    stdEmailBox.clear();
    stdAcademicYearBox.clear();
    stdClassBox.clear();
    imageView.setImage(null);
    stdEnrollNoBox.clear();


  }


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }

  public void feesSubmitBtnAction(ActionEvent actionEvent) {

    boolean isEmptyFlag = false;
    boolean isValidateFlag = true;

    if(Admsnfees.getText().isEmpty()){
      isEmptyFlag = true;
      isValidateFlag = false;
      Admsnfees.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
    }else if(Admsnfees.getText().matches(".*\\D.*")){
      isValidateFlag = false;
      Admsnfees.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
    }else{
      Admsnfees.setStyle(null);
    }

    if(DevFees.getText().isEmpty()){
      isEmptyFlag = true;
      isValidateFlag = false;
      DevFees.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
    }else if(DevFees.getText().matches(".*\\D.*")){
      isValidateFlag = false;
      DevFees.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
    }else{
      DevFees.setStyle(null);
    }

    if(term1.getText().isEmpty()){
      isEmptyFlag = true;
      isValidateFlag = false;
      term1.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
    }else if(term1.getText().matches(".*\\D.*")){
      isValidateFlag = false;
      term1.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
    }else{
      term1.setStyle(null);
    }

    if(term2.getText().isEmpty()){
      isEmptyFlag = true;
      isValidateFlag = false;
      term2.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
    }else if(term2.getText().matches(".*\\D.*")){
      isValidateFlag = false;
      term2.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
    }else{
      term2.setStyle(null);
    }

    if(term3.getText().isEmpty()){
      isEmptyFlag = true;
      isValidateFlag = false;
      term3.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
    }else if(term3.getText().matches(".*\\D.*")){
      isValidateFlag = false;
      term3.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
    }else{
      term3.setStyle(null);
    }

    if(term4.getText().isEmpty()){
      isEmptyFlag = true;
      isValidateFlag = false;
      term4.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
    }else if(term4.getText().matches(".*\\D.*")){
      isValidateFlag = false;
      term4.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
    }else{
      term4.setStyle(null);
    }

    if(isEmptyFlag){

      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error!");
      alert.setHeaderText("Field can't be empty.");
      alert.setContentText("Please enter the appropriate value into textbox.\nIf there is no fees, then please enter 0 (Zero).");
      alert.showAndWait();

      return;
    }

    if(!isValidateFlag){
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error!");
      alert.setHeaderText("No Non-Digit Vale.");
      alert.setContentText("Please enter the digit(0-9) value into textbox.");
      alert.showAndWait();
      return;
    }

    int AdmissionFees = Integer.parseInt(Admsnfees.getText());
    int DevlopmentFees = Integer.parseInt(DevFees.getText());
    int Term1Fees = Integer.parseInt(term1.getText());
    int Term2Fees = Integer.parseInt(term2.getText());
    int Term3Fees = Integer.parseInt(term3.getText());
    int Term4Fees = Integer.parseInt(term4.getText());
    String stdEnrollID = stdEnrollNoBox1.getText();
    String FeesBox = feesYesNoBox.getText();
    String TotalFeesBox = TotalFeesAmount.getText();



    Integer Total = (AdmissionFees+DevlopmentFees+Term1Fees+Term2Fees+Term3Fees+Term4Fees);

    TotalFeesAmount.setText(String.valueOf(Total));


    if (stdEnrollNoBox1.getText().trim().isEmpty()) {

      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Please enter enrollment number.");
      alert.setHeaderText("Field can't be empty.");
      alert.setContentText("Please enter enrollment number of student.");
      alert.showAndWait();

    } else {

      DBConnect dbConnect = new DBConnect();
      Connection connection = dbConnect.createConnection();

      String SQLQuery = "UPDATE student SET fees=?, fees_amount=?, admsn_fees=?, dev_fees=?, term1_fees=?, term2_fees=?, term3_fees=?, term4_fees=?  where enrollno='" + stdEnrollID + "' ";

      PreparedStatement pst = null;
//        Statement statement = null;

      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmation Dialog.");
      alert.setHeaderText("Are you sure?");
      alert.getDialogPane().setPrefSize(450, 200);
      Optional<ButtonType> action = alert.showAndWait();

      if(action.get() == ButtonType.OK) {

        try {
          pst = connection.prepareStatement(SQLQuery);
          pst.setString(1, feesYesNoBox.getText());
          pst.setString(2, TotalFeesAmount.getText());
          pst.setString(3, Admsnfees.getText());
          pst.setString(4, DevFees.getText());
          pst.setString(5, term1.getText());
          pst.setString(6, term2.getText());
          pst.setString(7, term3.getText());
          pst.setString(8, term4.getText());




          pst.executeUpdate();

          alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("submitted successfully");
          alert.setHeaderText("fees is generated.");
          alert.setContentText("fees submitted successfully.");
          alert.showAndWait();

        } catch (SQLException e) {

//            e.printStackTrace();
          alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Failed to submit the fees.");
          alert.setHeaderText("The student fees is not generated.");
          alert.setContentText("There is an error occurred during submitting fees.\n\n Possible Checks: \n\n 1) Please check database service is running perfectly. \n 2) Please check database, tables & columns is configured properly. \n 3) Please check the data are you entered.");
          alert.getDialogPane().setPrefSize(480, 261);
          alert.showAndWait();

        } catch (Exception e) {

//            e.printStackTrace();
          alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Failed to submit the fees.");
          alert.setHeaderText("The student fees is not generated.");
          alert.setContentText("There is an error occurred during submitting fees.\n\n Possible Checks: \n\n 1) Please check database service is running perfectly. \n 2) Please check database, tables & columns is configured properly. \n 3) Please check the data are you entered.");
          alert.getDialogPane().setPrefSize(480, 260);
          alert.showAndWait();

        }

      }else{
        // Do whatever you want.
      }
  }
  }

  public void feesResetBtnAction(ActionEvent actionEvent) {

    Admsnfees.clear();
    DevFees.clear();
    term1.clear();
    term2.clear();
    term3.clear();
    term4.clear();
    TotalFeesAmount.clear();
    feesYesNoBox.clear();
    stdEnrollNoBox1.clear();

    Admsnfees.setStyle(null);
    DevFees.setStyle(null);
    term1.setStyle(null);
    term2.setStyle(null);
    term3.setStyle(null);
    term4.setStyle(null);

  }

  public void feesSubmitBtn1Action(ActionEvent actionEvent) {

    if (stdEnrollNoBox1.getText().trim().isEmpty()) {

      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Please enter the enrollment number.");
      alert.setHeaderText("Field can't be empty.");
      alert.setContentText("Please enter enrollment number of student.");
      alert.showAndWait();

    } else {

      DBConnect dbConnect = new DBConnect();
      Connection connection = dbConnect.createConnection();

      PreparedStatement pst = null;
      ResultSet rs = null;

      String SQLQuery = "SELECT  fees, admsn_fees ,fees_amount, dev_fees, term1_fees, term2_fees, term3_fees, term4_fees FROM student where enrollno = ?";

      try {

        pst = connection.prepareStatement(SQLQuery);
        pst.setString(1, stdEnrollNoBox1.getText());

        rs = pst.executeQuery();

        while (rs.next()) {

          Admsnfees.setText(rs.getString("admsn_fees"));
          DevFees.setText(rs.getString("dev_fees"));
          term1.setText(rs.getString("term1_fees"));
          term2.setText(rs.getString("term2_fees"));
          term3.setText(rs.getString("term3_fees"));
          term4.setText(rs.getString("term4_fees"));
          TotalFeesAmount.setText(rs.getString("fees_amount"));
          feesYesNoBox.setText(rs.getString("fees"));

        }

      } catch (SQLException e) {
//                e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Failed to retrieve the data.");
        alert.setHeaderText("The student fees data is not retrieve.");
        alert.setContentText("There is an error occurred during retrieving fees data.\n\n Possible Checks: \n\n 1) Please check database service is running perfectly. \n 2) Please check database, tables & columns is configured properly. \n 3) Maybe the enrollment number are you trying to fetch is not available. \n 4) Please check the data are you entered.");
        alert.getDialogPane().setPrefSize(480, 251);
        alert.showAndWait();
    }
  }
}
}
