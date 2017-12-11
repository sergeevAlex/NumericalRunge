package sample;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;





public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane page = (GridPane) FXMLLoader.load(Main.class.getResource("sample.fxml"));
        primaryStage.setTitle("LabWork3");
        primaryStage.setScene(new Scene(page, 1000, 650));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }
}
