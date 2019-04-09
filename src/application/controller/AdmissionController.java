package application.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class AdmissionController {


  public void btnMinEvent(MouseEvent mouseEvent) {
    Stage stage = (Stage) ((Circle) mouseEvent.getSource()).getScene().getWindow();
    stage.setIconified(true);
  }

  public void btnBackEvent(MouseEvent mouseEvent) {
    Stage stage = (Stage) ((Circle) mouseEvent.getSource()).getScene().getWindow();
    stage.close();
  }



  public Button btnResetAdmsnForm;
  public Button btnAdmsnForm;
  public TextField stdNameBox;
  public TextField stdFatherNameBox;
  public TextField stdGenderBox;
  public TextField stdAgeBox;
  public DatePicker stdDOBPicker;
  public TextField stdBirthPlaceBox;
  public TextField stdCasteBox;
  public TextField stdCategoryBox;
  public TextField stdCityBox;
  public TextField stdStateBox;
  public TextArea stdAddressBox;
  public TextField stdMoNoBox1;
  public TextField stdMoNoBox2;
  public TextField stdAadhaarBox;
  public TextField stdEmailBox;
  public TextField stdLastSchoolBox;
  public TextField stdAcademicYearBox;
  public TextField stdClassBox;
  public Button stdUpdateData;
  public ImageView imageView;
  public Button selectImage;

  public TextField stdEnrollNoBox1;
  public TextField stdEnrollNoBox;
  public TextField stdEnrollNoBox2;
  public Button stdDeleteData;
  public Button stdLoadData;

  private String imageFile;
  private File file;
  private FileInputStream fis;

  public void studentImage(ActionEvent event) {

    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
    file = fileChooser.showOpenDialog(null);

    if (file != null) {

      try {

        imageFile = file.toURI().toURL().toString();
        javafx.scene.image.Image image = new Image(imageFile);
        imageView.setImage(image);

      } catch (IOException e) {
//                e.printStackTrace();
      }

    } else {

    }
  }


  public void btnAdmsnFormAction(ActionEvent event) {


    String stdName = stdNameBox.getText();
    String stdFatherName = stdFatherNameBox.getText();
    String stdGender = stdGenderBox.getText();
    String stdAge = stdAgeBox.getText();
    String stdDOB = (stdDOBPicker).getEditor().getText();
    String stdBirthPlace = stdBirthPlaceBox.getText();
    String stdCaste = stdCasteBox.getText();
    String stdCategory = stdCategoryBox.getText();
    String stdCity = stdCityBox.getText();
    String stdState = stdStateBox.getText();
    String stdAddress = stdAddressBox.getText();
    String stdMoNo1 = stdMoNoBox1.getText();
    String stdMoNo2 = stdMoNoBox2.getText();
    String stdAadhaar = stdAadhaarBox.getText();
    String stdEmail = stdEmailBox.getText();
    String stdLastSchool = stdLastSchoolBox.getText();
    String stdAcademicYear = stdAcademicYearBox.getText();
    String stdClass = stdClassBox.getText();


    DBConnect dbConnect = new DBConnect();
    Connection connection = dbConnect.createConnection();

    String SQLQuery = "INSERT INTO student (name, fathername, gender, age, birthdate, birthplace, caste, category, city, state, address, mobileno1, mobileno2, aadhaarno, email, lastschool, image, academic_year, class) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    PreparedStatement pst = null;

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation Dialog.");
    alert.setHeaderText("Are you sure?");
    alert.setContentText("This will submit an entire student admission form into database.");
    alert.getDialogPane().setPrefSize(450, 200);
    Optional<ButtonType> action = alert.showAndWait();

    if(action.get() == ButtonType.OK) {

      try {
        /*
        * Validation Starts from here.
        *
        *
        * */

        boolean isEmptyFlag = false;
//        boolean isEmptyFalg = false;
        boolean isLowerFlag = false;
        boolean isValidateFlag = true;

        if(stdAge.isEmpty()){
          isEmptyFlag = true;
          isValidateFlag = false;
          stdAgeBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else if(stdAge.matches(".*\\D.*")){
          isValidateFlag = false;
          stdAgeBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else{
          stdAgeBox.setStyle(null);
        }

        if(stdMoNo1.isEmpty()){
          isEmptyFlag = true;
          isValidateFlag = false;
          stdMoNoBox1.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else if(stdMoNo1.matches(".*\\D.*")){
          isValidateFlag = false;
          stdMoNoBox1.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else{
          stdMoNoBox1.setStyle(null);
        }

        if(stdMoNo2.isEmpty()){
          isEmptyFlag = true;
          isValidateFlag = false;
          stdMoNoBox2.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else if(stdMoNo2.matches(".*\\D.*")){
          isValidateFlag = false;
          stdMoNoBox2.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else{
          stdMoNoBox2.setStyle(null);
        }

        if(stdAadhaar.isEmpty()){
          isEmptyFlag = true;
          isValidateFlag = false;
          stdAadhaarBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else if(stdAadhaar.matches(".*\\D.*")){
          isValidateFlag = false;
          stdAadhaarBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else{
          stdAadhaarBox.setStyle(null);
        }

        if(stdEmail.isEmpty()){
          isEmptyFlag = true;
          isValidateFlag = false;
          stdEmailBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else{
          stdEmailBox.setStyle(null);
        }





        if(stdAcademicYear.isEmpty()){
          isEmptyFlag = true;
          isValidateFlag = false;
          stdAcademicYearBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else if(stdAcademicYear.matches(".*[a-z].*")){
          isLowerFlag = true;
          isValidateFlag = false;
          stdAcademicYearBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else{
          stdAcademicYearBox.setStyle(null);
        }

        if(stdClass.isEmpty()){
          isEmptyFlag = true;
          isValidateFlag = false;
          stdClassBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else if(stdClass.matches(".*[a-z].*")){
          isLowerFlag = true;
          isValidateFlag = false;
          stdClassBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else{
          stdClassBox.setStyle(null);
        }

        if(stdDOB.isEmpty()){
          isEmptyFlag = true;
          isValidateFlag = false;
          stdDOBPicker.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else if(stdDOB.matches(".*[a-z].*")){
          isLowerFlag = true;
          isValidateFlag = false;
          stdDOBPicker.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else{
          stdDOBPicker.setStyle(null);
        }





        if(stdName.isEmpty()){
          isEmptyFlag = true;
          isValidateFlag = false;
          stdNameBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else if(stdName.matches(".*\\d.*")){
          isValidateFlag = false;
          stdNameBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else if(stdName.matches(".*[a-z].*")){
          isLowerFlag = true;
          isValidateFlag = false;
          stdNameBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else{
          stdNameBox.setStyle(null);
        }

        if(stdFatherName.isEmpty()){
          isEmptyFlag = true;
          isValidateFlag = false;
          stdFatherNameBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else if(stdFatherName.matches(".*\\d.*")){
          isValidateFlag = false;
          stdFatherNameBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else if(stdFatherName.matches(".*[a-z].*")){
          isLowerFlag = true;
          isValidateFlag = false;
          stdFatherNameBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else{
          stdFatherNameBox.setStyle(null);
        }

        if(stdGender.isEmpty()){
          isEmptyFlag = true;
          isValidateFlag = false;
          stdGenderBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else if(stdGender.matches(".*\\d.*")){
          isValidateFlag = false;
          stdGenderBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else if(stdGender.matches(".*[a-z].*")){
          isLowerFlag = true;
          isValidateFlag = false;
          stdGenderBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else{
          stdGenderBox.setStyle(null);
        }

        if(stdBirthPlace.isEmpty()){
          isEmptyFlag = true;
          isValidateFlag = false;
          stdBirthPlaceBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else if(stdBirthPlace.matches(".*\\d.*")){
          isValidateFlag = false;
          stdBirthPlaceBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else if(stdBirthPlace.matches(".*[a-z].*")){
          isLowerFlag = true;
          isValidateFlag = false;
          stdBirthPlaceBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else{
          stdBirthPlaceBox.setStyle(null);
        }

        if(stdCaste.isEmpty()){
          isEmptyFlag = true;
          isValidateFlag = false;
          stdCasteBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else if(stdCaste.matches(".*\\d.*")){
          isValidateFlag = false;
          stdCasteBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else if(stdCaste.matches(".*[a-z].*")){
          isLowerFlag = true;
          isValidateFlag = false;
          stdCasteBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else{
          stdCasteBox.setStyle(null);
        }

        if(stdCategory.isEmpty()){
          isEmptyFlag = true;
          isValidateFlag = false;
          stdCategoryBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else if(stdCategory.matches(".*\\d.*")){
          isValidateFlag = false;
          stdCategoryBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else if(stdCategory.matches(".*[a-z].*")){
          isLowerFlag = true;
          isValidateFlag = false;
          stdCategoryBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else{
          stdCategoryBox.setStyle(null);
        }

        if(stdCity.isEmpty()){
          isEmptyFlag = true;
          isValidateFlag = false;
          stdCityBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else if(stdCity.matches(".*\\d.*")){
          isValidateFlag = false;
          stdCityBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else if(stdCity.matches(".*[a-z].*")){
          isLowerFlag = true;
          isValidateFlag = false;
          stdCityBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else{
          stdCityBox.setStyle(null);
        }

        if(stdState.isEmpty()){
          isEmptyFlag = true;
          isValidateFlag = false;
          stdStateBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else if(stdState.matches(".*\\d.*")){
          isValidateFlag = false;
          stdStateBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else if(stdState.matches(".*[a-z].*")){
          isLowerFlag = true;
          isValidateFlag = false;
          stdStateBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else{
          stdStateBox.setStyle(null);
        }

        if(stdLastSchool.isEmpty()){
          isEmptyFlag = true;
          isValidateFlag = false;
          stdLastSchoolBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else if(stdLastSchool.matches(".*\\d.*")){
          isValidateFlag = false;
          stdLastSchoolBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else if(stdLastSchool.matches(".*[a-z].*")){
          isLowerFlag = true;
          isValidateFlag = false;
          stdLastSchoolBox.setStyle("-fx-border-color: red; -fx-border-radius: 2.5px;");
        }else{
          stdLastSchoolBox.setStyle(null);
        }

        if(isEmptyFlag){

          alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Error!");
          alert.setHeaderText("Field can't be empty.");
          alert.setContentText("Please enter the appropriate value into textbox.");
          alert.showAndWait();

          return;
        }

        if(isLowerFlag){
          alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Error!");
          alert.setHeaderText("Field can't be in lowercase.");
          alert.setContentText("Please enter the appropriate value in uppercase.");
          alert.showAndWait();
          return;
        }

        if(!isValidateFlag){
          return;
        }
        /*
         * Validation Ends from here.
         *
         *
         * */

          pst = connection.prepareStatement(SQLQuery);
          pst.setString(1, stdName);
          pst.setString(2, stdFatherName);
          pst.setString(3, stdGender);
          pst.setString(4, stdAge);
          pst.setString(5, stdDOB);
          pst.setString(6, stdBirthPlace);
          pst.setString(7, stdCaste);
          pst.setString(8, stdCategory);
          pst.setString(9, stdCity);
          pst.setString(10, stdState);
          pst.setString(11, stdAddress);
          pst.setString(12, stdMoNo1);
          pst.setString(13, stdMoNo2);
          pst.setString(14, stdAadhaar);
          pst.setString(15, stdEmail);
          pst.setString(16, stdLastSchool);
          fis = new FileInputStream(file);
          pst.setBinaryStream(17, (InputStream) fis, (int) file.length());
          pst.setString(18, stdAcademicYear);
          pst.setString(19, stdClass);

          pst.executeUpdate();

          alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Admission form submitted successfully.");
          alert.setHeaderText("The student admission form is generated.");
          alert.setContentText("Admission form submitted successfully.");
          alert.showAndWait();

      } catch(SQLException | FileNotFoundException e){
//        e.printStackTrace();
          alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Failed to submit the admission form.");
          alert.setHeaderText("The student admission form is not generated.");
          alert.setContentText("There is an error occurred during submitting admission form.\n\n Possible Checks: \n\n 1) Please check database service is running perfectly. \n 2) Please check database, tables & columns is configured properly. \n 3) Please check the data are you entered.");
          alert.getDialogPane().setPrefSize(480, 250);
          alert.showAndWait();

        } catch(Exception e){
//        e.printStackTrace();
          alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Failed to submit the admission form.");
          alert.setHeaderText("The student admission form is not generated.");
          alert.setContentText("There is an error occurred during submitting admission form.\n\n Possible Checks: \n\n 1) Please check database service is running perfectly. \n 2) Please check database, tables & columns is configured properly. \n 3) Please check the data are you entered.");
          alert.getDialogPane().setPrefSize(480, 251);
          alert.showAndWait();
        }

      }else{
        //  Do Whatever you want.
      }
  }

  public void btnResetAdmsnFormAction(ActionEvent event) {

    stdNameBox.clear();
    stdFatherNameBox.clear();
    stdGenderBox.clear();
    stdAgeBox.clear();
    stdDOBPicker.getEditor().clear(); // setValue(null);
    stdBirthPlaceBox.clear();
    stdCasteBox.clear();
    stdCategoryBox.clear();
    stdCityBox.clear();
    stdStateBox.clear();
    stdAddressBox.clear();
    stdMoNoBox1.clear();
    stdMoNoBox2.clear();
    stdAadhaarBox.clear();
    stdEmailBox.clear();
    stdLastSchoolBox.clear();
    imageView.setImage(null);
    stdAcademicYearBox.clear();
    stdClassBox.clear();
    stdEnrollNoBox.clear();
    stdEnrollNoBox1.clear();
    stdEnrollNoBox2.clear();



    stdNameBox.setStyle(null);
    stdFatherNameBox.setStyle(null);
    stdGenderBox.setStyle(null);
    stdAgeBox.setStyle(null);
    stdDOBPicker.setStyle(null); // setValue(null);
    stdBirthPlaceBox.setStyle(null);
    stdCasteBox.setStyle(null);
    stdCategoryBox.setStyle(null);
    stdCityBox.setStyle(null);
    stdStateBox.setStyle(null);
    stdAddressBox.setStyle(null);
    stdMoNoBox1.setStyle(null);
    stdMoNoBox2.setStyle(null);
    stdAadhaarBox.setStyle(null);
    stdEmailBox.setStyle(null);
    stdLastSchoolBox.setStyle(null);
    imageView.setImage(null);
    stdAcademicYearBox.setStyle(null);
    stdClassBox.setStyle(null);
    stdEnrollNoBox.setStyle(null);
    stdEnrollNoBox1.setStyle(null);
    stdEnrollNoBox2.setStyle(null);

  }

  public void stdLoadDataAction(ActionEvent actionEvent) {

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

      String SQLQuery = "SELECT name, fathername, gender, age, birthdate, birthplace, caste, category, city, state, address, mobileno1, mobileno2, aadhaarno, email, lastschool, image, academic_year, class FROM student where enrollno = ?";

      try {

        pst = connection.prepareStatement(SQLQuery);
        pst.setString(1, stdEnrollNoBox.getText());

        rs = pst.executeQuery();

        while (rs.next()) {

          stdNameBox.setText(rs.getString("name"));
          stdFatherNameBox.setText(rs.getString("fathername"));
          stdGenderBox.setText(rs.getString("gender"));
          stdAgeBox.setText(rs.getString("age"));
          stdDOBPicker.getEditor().setText(rs.getString("birthdate"));
          stdBirthPlaceBox.setText(rs.getString("birthplace"));
          stdCasteBox.setText(rs.getString("caste"));
          stdCategoryBox.setText(rs.getString("category"));
          stdCityBox.setText(rs.getString("city"));
          stdStateBox.setText(rs.getString("state"));
          stdAddressBox.setText(rs.getString("address"));
          stdMoNoBox1.setText(rs.getString("mobileno1"));
          stdMoNoBox2.setText(rs.getString("mobileno2"));
          stdAadhaarBox.setText(rs.getString("aadhaarno"));
          stdEmailBox.setText(rs.getString("email"));
          stdLastSchoolBox.setText(rs.getString("lastschool"));
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

  public void stdDeleteDataAction(ActionEvent actionEvent) {

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

      String SQLQuery = "DELETE FROM student where enrollno = ?";

      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmation Dialog.");
      alert.setHeaderText("Are you sure?");
      alert.setContentText("This will delete the entire student data.\nAfter this action you can't be able to recover the data.");
      alert.getDialogPane().setPrefSize(450, 200);
      Optional<ButtonType> action = alert.showAndWait();

      if(action.get() == ButtonType.OK){

        try {

          pst = connection.prepareStatement(SQLQuery);
          pst.setString(1, stdEnrollNoBox1.getText());
          int QUERY = pst.executeUpdate();

          if (QUERY > 0) {

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Admission form deleted successfully.");
            alert.setHeaderText("The student admission form is deleted.");
            alert.setContentText("Student Admission form deleted successfully.");
            alert.showAndWait();

          } else {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Admission form is not deleted successfully.");
            alert.setHeaderText("The student admission form is not deleted.");
            alert.setContentText("Student Admission form is not deleted successfully.\n\n Possible Checks: \n\n 1) Please check database service is running perfectly. \n 2) Please check database, tables & columns is configured properly. \n 3) Maybe the admission form are you trying to delete is not available. \n 4) Please check the enrollment number are you entered. Maybe it not available in database.");
            alert.getDialogPane().setPrefSize(520, 300);
            alert.showAndWait();

          }

        } catch (SQLException e) {
//            e.printStackTrace();
        }

      }else {
        //  Do Whatever you want.
      }
    }
  }

  public void stdUpdateDataAction(ActionEvent actionEvent) {

    String stdEnrollID = stdEnrollNoBox2.getText();
    String stdName = stdNameBox.getText();
    String stdFatherName = stdFatherNameBox.getText();
    String stdGender = stdGenderBox.getText();
    String stdAge = stdAgeBox.getText();
    String stdDOB = (stdDOBPicker).getEditor().getText();
    String stdBirthPlace = stdBirthPlaceBox.getText();
    String stdCaste = stdCasteBox.getText();
    String stdCategory = stdCategoryBox.getText();
    String stdCity = stdCityBox.getText();
    String stdState = stdStateBox.getText();
    String stdAddress = stdAddressBox.getText();
    String stdMoNo1 = stdMoNoBox1.getText();
    String stdMoNo2 = stdMoNoBox2.getText();
    String stdAadhaar = stdAadhaarBox.getText();
    String stdEmail = stdEmailBox.getText();
    String stdLastSchool = stdLastSchoolBox.getText();
    String stdAcademicYear = stdAcademicYearBox.getText();
    String stdClass = stdClassBox.getText();

    if (stdEnrollNoBox2.getText().trim().isEmpty()) {

      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Please enter enrollment number.");
      alert.setHeaderText("Field can't be empty.");
      alert.setContentText("Please enter enrollment number of student.");
      alert.showAndWait();

    } else {


      DBConnect dbConnect = new DBConnect();
      Connection connection = dbConnect.createConnection();

      String SQLQuery = "UPDATE student SET name=?, fathername=?, gender=?, age=?, birthdate=?, birthplace=?, caste=?, category=?, city=?, state=?, address=?, mobileno1=?, mobileno2=?, aadhaarno=?, email=?, lastschool=?, academic_year=?, class=? where enrollno='" + stdEnrollID + "' ";
      // '"+stdName+"', '"+stdFatherName+"', '"+stdGender+"', '"+stdAge+"', '"+stdDOB+"', '"+stdBirthPlace+"', '"+stdCaste+"', '"+stdCategory+"', '"+stdCity+"', '"+stdState+"', '"+stdAddress+"', '"+stdMoNo1+"', '"+stdMoNo2+"', '"+stdAadhaar+"', '"+stdEmail+"', '"+stdLastSchool+"')";

      PreparedStatement pst = null;
//        Statement statement = null;

      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmation Dialog.");
      alert.setHeaderText("Are you sure?");
      alert.setContentText("This will change the student data.\nThis will remove/overwrite the old data.");
      alert.getDialogPane().setPrefSize(450, 200);
      Optional<ButtonType> action = alert.showAndWait();

      if(action.get() == ButtonType.OK) {

        try {
          pst = connection.prepareStatement(SQLQuery);
          pst.setString(1, stdName);
          pst.setString(2, stdFatherName);
          pst.setString(3, stdGender);
          pst.setString(4, stdAge);
          pst.setString(5, stdDOB);
          pst.setString(6, stdBirthPlace);
          pst.setString(7, stdCaste);
          pst.setString(8, stdCategory);
          pst.setString(9, stdCity);
          pst.setString(10, stdState);
          pst.setString(11, stdAddress);
          pst.setString(12, stdMoNo1);
          pst.setString(13, stdMoNo2);
          pst.setString(14, stdAadhaar);
          pst.setString(15, stdEmail);
          pst.setString(16, stdLastSchool);
          pst.setString(17, stdAcademicYear);
          pst.setString(18, stdClass);

          pst.executeUpdate();

          alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Admission form submitted successfully");
          alert.setHeaderText("The student admission form is generated.");
          alert.setContentText("Admission form submitted successfully.");
          alert.showAndWait();

        } catch (SQLException e) {

//            e.printStackTrace();
          alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Failed to submit the form.");
          alert.setHeaderText("The student admission form is not generated.");
          alert.setContentText("There is an error occurred during submitting admission form.\n\n Possible Checks: \n\n 1) Please check database service is running perfectly. \n 2) Please check database, tables & columns is configured properly. \n 3) Please check the data are you entered.");
          alert.getDialogPane().setPrefSize(480, 261);
          alert.showAndWait();

        } catch (Exception e) {

//            e.printStackTrace();
          alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Failed to submit the form.");
          alert.setHeaderText("The student admission form is not generated.");
          alert.setContentText("There is an error occurred during submitting admission form.\n\n Possible Checks: \n\n 1) Please check database service is running perfectly. \n 2) Please check database, tables & columns is configured properly. \n 3) Please check the data are you entered.");
          alert.getDialogPane().setPrefSize(480, 260);
          alert.showAndWait();

        }

      }else{
        // Do whatever you want.
      }
    }
  }

  public void btnPrintFormAction(ActionEvent actionEvent) throws DocumentException {

    final String cwd = System.getProperty("user.dir");
//    System.out.println("Current working directory : " + cwd);

    Document document = new Document(PageSize.A4, 50, 50, 50, 50);

    try {

      String stdName = stdNameBox.getText();
      String stdFatherName = stdFatherNameBox.getText();
      String stdGender = stdGenderBox.getText();
      String stdAge = stdAgeBox.getText();
      String stdDOB = (stdDOBPicker).getEditor().getText();
      String stdBirthPlace = stdBirthPlaceBox.getText();
      String stdCaste = stdCasteBox.getText();
      String stdCategory = stdCategoryBox.getText();
      String stdCity = stdCityBox.getText();
      String stdState = stdStateBox.getText();
      String stdAddress = stdAddressBox.getText();
      String stdMoNo1 = stdMoNoBox1.getText();
      String stdMoNo2 = stdMoNoBox2.getText();
      String stdAadhaar = stdAadhaarBox.getText();
      String stdEmail = stdEmailBox.getText();
      String stdLastSchool = stdLastSchoolBox.getText();
      String stdAcademicYear = stdAcademicYearBox.getText();
      String stdClass = stdClassBox.getText();

      String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss"));
      Font font = new Font(Font.FontFamily.HELVETICA, 35, Font.BOLD);
      Font headingfont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);

      Paragraph schoolname = new Paragraph("School Name", font);
      schoolname.setAlignment(Element.ALIGN_CENTER);

      Paragraph heading = new Paragraph("Academic year : "+stdAcademicYear+"          Class : "+stdClass, headingfont);
      heading.setAlignment(Element.ALIGN_RIGHT);


      PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(cwd+"/"+timestamp+"Test.pdf"));

      document.open();

      document.add(schoolname);
      document.add(heading);
      document.add(new Paragraph("======================================================================"));
      document.add(new Paragraph("Name : "+stdName));
      document.add(new Paragraph("Father Name : "+stdFatherName));
      document.add(new Paragraph("Age : "+stdAge+"          Birthdate : "+stdDOB+"          Birthplace : "+stdBirthPlace));
      document.add(new Paragraph(     "Gender : "+stdGender+"          Caste : "+stdCaste+"          Category : "+stdCategory));
      document.add(new Paragraph("City : "+stdCity+"          State : "+stdState));
      document.add(new Paragraph("Address : "+stdAddress));
      document.add(new Paragraph("Mobile Number-1 : "+stdMoNo1+"          Mobile Number-2 : "+stdMoNo2));
      document.add(new Paragraph("Aadhaar Number : "+stdAadhaar+"          Email ID : "+stdEmail));
      document.add(new Paragraph("Last School Name : "+stdLastSchool));

      document.close();
      writer.close();

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Admission form is generated.");
      alert.setHeaderText("The student admission form is generated.");
      alert.setContentText("Location :\n"+cwd);
      alert.showAndWait();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
