package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

public class Controller implements Initializable {



    @FXML
    Button btnOpen = new Button();

    @FXML
    TableView<DataModel> tableConst;
    @FXML
    TableColumn<DataModel,Double> constStep;
    @FXML
    TableColumn<DataModel,Double> constCoordinate;
    @FXML
    TableColumn<DataModel,Double> constValue;


    @FXML
    TableView<DataModel> tableAdams;
    @FXML
    TableColumn<DataModel,Double> adamsStep;
    @FXML
    TableColumn<DataModel,Double> adamsCoordinate;
    @FXML
    TableColumn<DataModel,Double> adamsValue;


    @FXML
    TableView<DataModel> tableID;
    @FXML
    TableColumn<DataModel,Double> step;
    @FXML
    TableColumn<DataModel,Double> coordinate;
    @FXML
    TableColumn<DataModel,Double> value;



    final ObservableList<DataModel> data = FXCollections.observableArrayList();
    final ObservableList<DataModel> dataConst = FXCollections.observableArrayList();
    final ObservableList<DataModel> dataAdams = FXCollections.observableArrayList();


    @FXML
    void handleButtonAction(ActionEvent action){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("draw.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            final Scene scene1 = new Scene(root1);
            stage.setScene(scene1);
            stage.show();}
        catch (Exception e){
            e.printStackTrace();
            System.out.println("cant load it!");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        coordinate.setCellValueFactory(new PropertyValueFactory<DataModel,Double>("coordinate"));
        value.setCellValueFactory(new PropertyValueFactory<DataModel,Double>("value"));
        step.setCellValueFactory(new PropertyValueFactory<DataModel,Double>("step"));

        ArrayList<Double> a = new ArrayList<>();
        ArrayList<Double> b = new ArrayList<>();
        ArrayList<Double> c = new ArrayList<>();
        ArrayList<Double> d = new ArrayList<>();
        ArrayList<Double> f = new ArrayList<>();
        ArrayList<Double> Adams = new ArrayList<>();




        Runge r = new Runge(-1.0,1.0,2.0,false);
        r.method();
        a = r.getSteps();
        b = r.getAutoValues();
        c = r.getXi();


        for(int i = 0;i<c.size();i++){
            data.add(new DataModel(c.get(i),b.get(i),a.get(i)));
        }
//Const step
        d = r.getConstValues();
        f = r.getConstXi();

        for(int i = 0;i<f.size();i++){
            dataConst.add(new DataModel(f.get(i),d.get(i),0.025));
        }


        AdamsMoulton am = new AdamsMoulton(0.025,d);

        am.adamsMethod();

        Adams = am.getResArray();

        for(int i = 0;i<f.size()-1;i++){
            dataAdams.add(new DataModel(f.get(i),Adams.get(i),0.025));
        }


        constCoordinate.setCellValueFactory(new PropertyValueFactory<DataModel,Double>("coordinate"));
        constValue.setCellValueFactory(new PropertyValueFactory<DataModel,Double>("value"));
        constStep.setCellValueFactory(new PropertyValueFactory<DataModel,Double>("step"));

        adamsCoordinate.setCellValueFactory(new PropertyValueFactory<DataModel,Double>("coordinate"));
        adamsValue.setCellValueFactory(new PropertyValueFactory<DataModel,Double>("value"));
        adamsStep.setCellValueFactory(new PropertyValueFactory<DataModel,Double>("step"));
        tableAdams.setItems(dataAdams);
        tableConst.setItems(dataConst);
        tableID.setItems(data);
    }
}
