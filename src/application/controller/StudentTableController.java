package application.controller;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static javafx.application.Application.launch;

public class StudentTableController implements Initializable {


  public Circle btnBack;
  public Button btnTblLoadData;
  public Button btnTblClearData;
  public Button btnTblExportData;
  public TextField stdTblSearchBar;
  @FXML
  public TableView<StudentTable> stdTable; // Table ID
  @FXML
  public TableColumn<StudentTable, String> tblStdEnroll; // Column ID
  @FXML
  public TableColumn<StudentTable, String> tblStdName; // Column ID


  /*
   *
   * Code For Student Stage.
   *
   * Starts From Here.
   *
   * */
  @FXML
  public TableColumn<StudentTable, String> tblStdAge; // Column ID
  @FXML
  public TableColumn<StudentTable, String> tblStdGender; // Column ID
  @FXML
  public TableColumn<StudentTable, String> tblStdAadhaarNo; // Column ID
  @FXML
  public TableColumn<StudentTable, String> tblStdCaste; // Column ID
  @FXML
  public TableColumn<StudentTable, String> tblStdCategory; // Column ID
  @FXML
  public TableColumn<StudentTable, String> tblStdClass; // Column ID
  @FXML
  public TableColumn<StudentTable, String> tblStdYear; // Column ID

  @FXML
  private Circle btnClose;
  @FXML
  private Circle btnMin;
  private double xOffset = 0;
  private double yOffset = 0;
  private ObservableList<StudentTable> data;

  public static void main(String[] args) {
    launch(args);
  }

  public void btnMinEvent(MouseEvent mouseEvent) {
    Stage stage = (Stage) ((Circle) mouseEvent.getSource()).getScene().getWindow();
    stage.setIconified(true);
  }

  public void btnBackEvent(MouseEvent mouseEvent) {
    Stage stage = (Stage) ((Circle) mouseEvent.getSource()).getScene().getWindow();
    stage.close();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  public void btnTblLoadDataAction(ActionEvent event) throws SQLException {

    try {

      DBConnect dbConnect = new DBConnect();
      Connection connection = dbConnect.createConnection();

      this.data = FXCollections.observableArrayList();

      ResultSet rs = connection.createStatement().executeQuery("SELECT enrollno, name, age, gender, aadhaarno, caste, category, class, academic_year FROM student");

      while (rs.next()) {

        this.data.add(new StudentTable(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7),
                rs.getString(8),
                rs.getString(9)


        ));
      }

    } catch (SQLException e) {

//            System.out.println(e);
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Failed to load the data.");
      alert.setHeaderText("There is an error occurred during loading the data.");
      alert.setContentText("\n Possible Checks: \n\n 1) Please check database service is running perfectly. \n 2) Please check database, tables & columns is configured properly.");
      alert.getDialogPane().setPrefSize(480, 250);
      alert.showAndWait();
    }

    this.tblStdEnroll.setCellValueFactory(new PropertyValueFactory<StudentTable, String>("tblEnrollNo"));
    this.tblStdName.setCellValueFactory(new PropertyValueFactory<StudentTable, String>("tblName"));
    this.tblStdAge.setCellValueFactory(new PropertyValueFactory<StudentTable, String>("tblAge"));
    this.tblStdGender.setCellValueFactory(new PropertyValueFactory<StudentTable, String>("tblGender"));
    this.tblStdAadhaarNo.setCellValueFactory(new PropertyValueFactory<StudentTable, String>("tblAadhaarNo"));
    this.tblStdCaste.setCellValueFactory(new PropertyValueFactory<StudentTable, String>("tblCaste"));
    this.tblStdCategory.setCellValueFactory(new PropertyValueFactory<StudentTable, String>("tblCategory"));
    this.tblStdClass.setCellValueFactory(new PropertyValueFactory<StudentTable, String>("tblClass"));
    this.tblStdYear.setCellValueFactory(new PropertyValueFactory<StudentTable, String>("tblYear"));




    this.stdTable.setItems(null);
    this.stdTable.setItems(this.data);
  }

  public void stdTblSearchBarAction(KeyEvent keyEvent) {

    FilteredList<StudentTable> filteredData = new FilteredList<>(data, p -> true);

    stdTblSearchBar.setOnKeyPressed(e -> {

      stdTblSearchBar.textProperty().addListener((observable, oldValue, newValue) -> {

        filteredData.setPredicate((Predicate<? super StudentTable>) user -> {

          if (newValue == null || newValue.isEmpty()) {
            return true;
          }

          String lowerCaseFilter = newValue.toLowerCase();

          if (user.getTblEnrollNo().contains(newValue)) {
            return true;
          } else if (user.getTblName().toLowerCase().contains(lowerCaseFilter)) {
            return true;
          } else if (user.getTblAge().toLowerCase().contains(lowerCaseFilter)) {
            return true;
          } else if (user.getTblGender().toLowerCase().contains(lowerCaseFilter)) {
            return true;
          } else if (user.getTblAadhaarNo().toLowerCase().contains(lowerCaseFilter)) {
            return true;
          } else if (user.getTblCaste().toLowerCase().contains(lowerCaseFilter)) {
            return true;
          } else if (user.getTblCategory().toLowerCase().contains(lowerCaseFilter)) {
            return true;
          } else if (user.getTblClass().toLowerCase().contains(lowerCaseFilter)) {
          return true;
          } else if (user.getTblYear().toLowerCase().contains(lowerCaseFilter)) {
          return true;
          } else {
            return false;
          }

        });

      });

      SortedList<StudentTable> sortedData = new SortedList<>(filteredData);
      sortedData.comparatorProperty().bind(stdTable.comparatorProperty());
      stdTable.setItems(sortedData);

    });

  }

  public void btnTblClearDataAction(ActionEvent event) {
    stdTable.setItems(null);
  }

  public void btnTblExportDataAction(ActionEvent actionEvent) {


    DBConnect dbConnect = new DBConnect();
    Connection connection = dbConnect.createConnection();

    PreparedStatement pst = null;
    ResultSet rs = null;

    String SQLQuery = "SELECT enrollno, name, fathername, gender, age, birthdate, birthplace, caste, category, city, state, address, mobileno1, mobileno2, aadhaarno, email, lastschool, academic_year, class FROM student";

    try {

      pst = connection.prepareStatement(SQLQuery);
      rs = pst.executeQuery();

      XSSFWorkbook workbook = new XSSFWorkbook();
      XSSFSheet worksheet = workbook.createSheet("Student Data.");
      XSSFRow header = worksheet.createRow(0);

      XSSFFont font = workbook.createFont();
      font.setBold(true);
      font.setItalic(true);
      font.setColor(Font.COLOR_RED);

      XSSFCellStyle style = workbook.createCellStyle();
      style.setAlignment(HorizontalAlignment.CENTER);
      style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
      style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
      style.setFont(font);

      header.setRowStyle(style);
      header.createCell(0).setCellValue("Enroll. No.");
      header.createCell(1).setCellValue("Student Name");
      header.createCell(2).setCellValue("Student's Father Name");
      header.createCell(3).setCellValue("Gender");
      header.createCell(4).setCellValue("Age");
      header.createCell(5).setCellValue("Birthdate");
      header.createCell(6).setCellValue("Birthplace");
      header.createCell(7).setCellValue("Caste");
      header.createCell(8).setCellValue("Category");
      header.createCell(9).setCellValue("City");
      header.createCell(10).setCellValue("State");
      header.createCell(11).setCellValue("Address");
      header.createCell(12).setCellValue("Mobile No.1");
      header.createCell(13).setCellValue("Mobile No.2");
      header.createCell(14).setCellValue("Aadhaar No.");
      header.createCell(15).setCellValue("Email");
      header.createCell(16).setCellValue("Last School");
      header.createCell(17).setCellValue("Academic Year");
      header.createCell(18).setCellValue("Class");




      worksheet.autoSizeColumn(0);
      worksheet.autoSizeColumn(1);
      worksheet.autoSizeColumn(2);
      worksheet.autoSizeColumn(3);
      worksheet.autoSizeColumn(4);
      worksheet.autoSizeColumn(5);
      worksheet.autoSizeColumn(6);
      worksheet.autoSizeColumn(7);
      worksheet.autoSizeColumn(8);
      worksheet.autoSizeColumn(9);
      worksheet.autoSizeColumn(10);
      worksheet.autoSizeColumn(11);
      worksheet.autoSizeColumn(12);
      worksheet.autoSizeColumn(13);
      worksheet.autoSizeColumn(14);
      worksheet.autoSizeColumn(15);
      worksheet.autoSizeColumn(16);
      worksheet.autoSizeColumn(17);
      worksheet.autoSizeColumn(18);



      int index = 1;
      while (rs.next()) {

        XSSFRow row = worksheet.createRow(index);
        row.createCell(0).setCellValue(rs.getString("enrollno"));
        row.createCell(1).setCellValue(rs.getString("name"));
        row.createCell(2).setCellValue(rs.getString("fathername"));
        row.createCell(3).setCellValue(rs.getString("gender"));
        row.createCell(4).setCellValue(rs.getString("age"));
        row.createCell(5).setCellValue(rs.getString("birthdate"));
        row.createCell(6).setCellValue(rs.getString("birthplace"));
        row.createCell(7).setCellValue(rs.getString("caste"));
        row.createCell(8).setCellValue(rs.getString("category"));
        row.createCell(9).setCellValue(rs.getString("city"));
        row.createCell(10).setCellValue(rs.getString("state"));
        row.createCell(11).setCellValue(rs.getString("address"));
        row.createCell(12).setCellValue(rs.getString("mobileno1"));
        row.createCell(13).setCellValue(rs.getString("mobileno2"));
        row.createCell(14).setCellValue(rs.getString("aadhaarno"));
        row.createCell(15).setCellValue(rs.getString("email"));
        row.createCell(16).setCellValue(rs.getString("lastschool"));
        row.createCell(17).setCellValue(rs.getString("academic_year"));
        row.createCell(18).setCellValue(rs.getString("class"));


        index++;

        worksheet.autoSizeColumn(0);
        worksheet.autoSizeColumn(1);
        worksheet.autoSizeColumn(2);
        worksheet.autoSizeColumn(3);
        worksheet.autoSizeColumn(4);
        worksheet.autoSizeColumn(5);
        worksheet.autoSizeColumn(6);
        worksheet.autoSizeColumn(7);
        worksheet.autoSizeColumn(8);
        worksheet.autoSizeColumn(9);
        worksheet.autoSizeColumn(10);
        worksheet.autoSizeColumn(11);
        worksheet.autoSizeColumn(12);
        worksheet.autoSizeColumn(13);
        worksheet.autoSizeColumn(14);
        worksheet.autoSizeColumn(15);
        worksheet.autoSizeColumn(16);
        worksheet.autoSizeColumn(17);
        worksheet.autoSizeColumn(18);


      }


      String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss"));
      FileOutputStream fis = new FileOutputStream("StudentsData-" + timestamp + "-.xlsx");
      workbook.write(fis);
      fis.close();


      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Data exported successfully.");
      alert.setHeaderText("The student data exported.");
      alert.setContentText("Student data exported successfully into excel file.");
      alert.showAndWait();

      pst.close();
      rs.close();


    } catch (SQLException | FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /*
   *
   * Code For Student Stage.
   *
   * Ends From Here.
   *
   * */
}