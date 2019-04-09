package application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {


  private double xOffset = 0;
  private double yOffset = 0;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("view/dashboard.fxml"));
    primaryStage.setTitle("Higher Secondary Student Admission Application");
    primaryStage.setScene(new Scene(root, 1024, 650));
    primaryStage.setResizable(false); // Disable The Resize : Maximize Button & Resize with Cursor
    primaryStage.initStyle(StageStyle.UNDECORATED);

    /*
     * Start
     * https://medium.com/@keeptoo/making-a-borderless-javafx-window-movable-f7855eb33a51
     *
     * */
    root.setOnMousePressed(event -> {
      xOffset = event.getSceneX();
      yOffset = event.getSceneY();
    });

    root.setOnMouseDragged(event -> {
      primaryStage.setX(event.getScreenX() - xOffset);
      primaryStage.setY(event.getScreenY() - yOffset);
    });
    /*
     * End
     * https://medium.com/@keeptoo/making-a-borderless-javafx-window-movable-f7855eb33a51
     *
     * */
    primaryStage.show();
  }
}
