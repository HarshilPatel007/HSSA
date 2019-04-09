package application.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

  @FXML
  private Circle btnClose;
  @FXML
  private Circle btnMin;
  @FXML
  private Button btnStudent;
  @FXML
  private Button btnAdmission;
  @FXML
  private Button btnResult;
  @FXML
  private Button btnFees;
  @FXML
  private Button btnSchedule;
  @FXML
  private Button btnSettings;
  private ActionEvent mouseEvent;

  private double xOffset = 0;
  private double yOffset = 0;

  public void btnMinEvent(MouseEvent mouseEvent) {
    Stage stage = (Stage) ((Circle) mouseEvent.getSource()).getScene().getWindow();
    stage.setIconified(true);
  }

  public void btnCloseEvent(MouseEvent mouseEvent) {
    Platform.exit();
  }

  public void btnBackEvent(MouseEvent mouseEvent) {
    Stage stage = (Stage) ((Circle) mouseEvent.getSource()).getScene().getWindow();
    stage.close();
  }

  @FXML
  private void handleButtonClicks(ActionEvent mouseEvent) {
    this.mouseEvent = mouseEvent;

    if (mouseEvent.getSource() == btnAdmission) {
      loadStages("/application/view/admission.fxml");
    } else if (mouseEvent.getSource() == btnFees) {
      loadStages("/application/view/fees.fxml");
    } else if (mouseEvent.getSource() == btnStudent) {
      loadStages("/application/view/students.fxml");
    } else if (mouseEvent.getSource() == btnResult) {
//      System.out.print("Result Stage Clicked!\n");
      loadStages("/application/view/charts.fxml");

    } else if (mouseEvent.getSource() == btnSchedule) {
      loadStages("/application/view/timetable.fxml");
    } else if (mouseEvent.getSource() == btnSettings) {
      loadStages("/application/view/settings.fxml");
    } else {
      System.out.print("Sorry, There are no any Stages!\n");
    }
  }

  private void loadStages(String fxml) {
    try {
      Parent root = FXMLLoader.load(getClass().getResource(fxml));
      Stage stage = new Stage();
      stage.setTitle("Higher Secondary Student Admission Application");
      stage.setScene(new Scene(root));
      stage.setResizable(false);
      stage.initStyle(StageStyle.UNDECORATED);
      stage.show();

      root.setOnMousePressed(event -> {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
      });

      root.setOnMouseDragged(event -> {
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
      });

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }
}