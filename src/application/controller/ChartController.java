package application.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChartController {

  public PieChart piechart;
  public PieChart piechart1;


  public void btnMinEvent(MouseEvent mouseEvent) {
    Stage stage = (Stage) ((Circle) mouseEvent.getSource()).getScene().getWindow();
    stage.setIconified(true);
  }

  public void btnBackEvent(MouseEvent mouseEvent) {
    Stage stage = (Stage) ((Circle) mouseEvent.getSource()).getScene().getWindow();
    stage.close();
  }


  public void btnShowChartAction(ActionEvent actionEvent) throws SQLException {

    ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
    DBConnect dbConnect = new DBConnect();
    Connection connection = dbConnect.createConnection();

    PreparedStatement pst = null;
    ResultSet rs = null;

    String SQLQuery = "SELECT DISTINCT gender, COUNT(gender) AS gender_count FROM `student` GROUP BY gender";

    try {

      pst = connection.prepareStatement(SQLQuery);

      rs = pst.executeQuery();

      while (rs.next()) {
        list.add(new PieChart.Data(rs.getString("gender"), rs.getInt("gender_count")));
      }

      piechart.setData(list);
    }catch(Exception e){

    }
  }

  public void btnShowChart1Action(ActionEvent actionEvent) {

    try {

      ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
      DBConnect dbConnect = new DBConnect();
      Connection connection = dbConnect.createConnection();

      PreparedStatement pst = null;
      ResultSet rs = null;

      String SQLQuery1 = "SELECT DISTINCT category, COUNT(category) AS category_count FROM `student` GROUP BY category";

      pst = connection.prepareStatement(SQLQuery1);

      rs = pst.executeQuery();

      while (rs.next()) {
        list.add(new PieChart.Data(rs.getString("category"), rs.getInt("category_count")));
      }

      piechart1.setData(list);
    }catch(Exception e){

    }
  }

}
