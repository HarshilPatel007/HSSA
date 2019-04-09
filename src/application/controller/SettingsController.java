package application.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SettingsController {


  public Text DBName;

  public void btnMinEvent(MouseEvent mouseEvent) {
    Stage stage = (Stage) ((Circle) mouseEvent.getSource()).getScene().getWindow();
    stage.setIconified(true);
  }

  public void btnBackEvent(MouseEvent mouseEvent) {
    Stage stage = (Stage) ((Circle) mouseEvent.getSource()).getScene().getWindow();
    stage.close();
  }

  public ListView<String> listView;

  public void ShowDBTblAction(ActionEvent actionEvent) {

    DBConnect dbConnect = new DBConnect();
    Connection connection = dbConnect.createConnection();
    DatabaseMetaData md;

      try {

        md = connection.getMetaData();
        ResultSet rs;
        rs = md.getTables("SMS", null, "%", null);
        List<String> results = new ArrayList<>();

        while (rs.next()) {
          results.add(rs.getString(3));
        }

        ObservableList<String> DBlist = FXCollections.observableArrayList(results);
        listView.setItems(DBlist);

      } catch (SQLException e) {
        e.printStackTrace();
      }
  }


  public Text version;
  public Text name;
  public Text drivername;

  public void dbVersionAction(ActionEvent actionEvent) {

    DBConnect dbConnect = new DBConnect();
    Connection connection = dbConnect.createConnection();
    DatabaseMetaData md;

      try {

        md = connection.getMetaData();

        String productName = md.getDatabaseProductName();
        name.setText(productName);

        String productVersion = md.getDatabaseProductVersion();
        version.setText(productVersion);

        String driverName = md.getDriverName();
        drivername.setText(driverName);

      } catch (SQLException e) {
        e.printStackTrace();
      }
  }

  public void showDBAction(ActionEvent actionEvent) {

    DBName.setText("SMS");

  }
}
