package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DrawController implements Initializable {

    /*
    * add plot-shade //  optional now
    * add 1 more methods and correct the log table :)
    * make cordons blank on draw;
    * */
    public void seriesAddData(XYChart.Series<Number,Number> xy, double xAxis, double yAxis){
        xy.getData().add(new XYChart.Data<>(xAxis,yAxis));
    }

    @FXML
    private CheckBox leftRec;
    @FXML
    private CheckBox rightRec;
    @FXML
    private CheckBox middleRec;

    @FXML
    private AnchorPane root1;
    final NumberAxis xAxis = new NumberAxis(5,10,1);
    final NumberAxis yAxis = new NumberAxis(5,10,1);


    @FXML
    LineChart<Number,Number> LC = new LineChart<Number, Number>(xAxis,yAxis);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        xAxis.setForceZeroInRange(false);
        xAxis.setLowerBound(0);
        xAxis.setUpperBound(3);


//        XYChart.Series<Number,Number> series = new XYChart.Series<Number, Number>();
        XYChart.Series<Number,Number> rSeries = new XYChart.Series<Number, Number>();
        XYChart.Series<Number,Number> lSeries = new XYChart.Series<Number, Number>();
        XYChart.Series<Number,Number> middleSeries = new XYChart.Series<Number, Number>();
//
        lSeries.setName("AutoStep");
        rSeries.setName("ConstStep");
        middleSeries.setName("Adams-Moulton");

//        for(int i = 0; i <= 10;i++) {
//            chartArr[i] = i*DIVIDE_CONST;
//            series.getData().add(new XYChart.Data<Number, Number>(chartArr[i],ii.mainFunc(chartArr[i])));
//        }



//AutoStep
        ArrayList<Double> a1 = new ArrayList<>();
        ArrayList<Double> b1 = new ArrayList<>();
        ArrayList<Double> c1 = new ArrayList<>();
        ArrayList<Double> d1 = new ArrayList<>();
        ArrayList<Double> f1 = new ArrayList<>();

        LC.setCreateSymbols(false);
        Runge n = new Runge(-1.0,1.0,2.0,false);

        n.method();
        a1 = n.getSteps();
        b1 = n.getAutoValues();
        c1 = n.getXi();

//Const step
        d1 = n.getConstValues();
        f1 = n.getConstXi();


        ArrayList<Double> finalC = c1;
        ArrayList<Double> finalB = b1;
        leftRec.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue) {
                    LC.getData().remove(lSeries);
                }
                if(newValue){
                    for(int i = 0; i < finalC.size(); i++){
                        seriesAddData(lSeries,finalC.get(i), finalB.get(i));
                    }
                    LC.getData().add(lSeries);
                }
            }
        });

        ArrayList<Double> finalF = f1;
        ArrayList<Double> finalF1 = f1;
        ArrayList<Double> Adams = f1;

        ArrayList<Double> finalD = d1;
        rightRec.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                if(newValue){
                    for(int i = 0; i < finalF.size(); i++){
                        rSeries.getData().add(new XYChart.Data<>(finalF.get(i), finalD.get(i)));
                    }
                    LC.getData().add(rSeries);
                }
                if (oldValue) {
                    LC.getData().remove(rSeries);
                }
            }
        });

        AdamsMoulton am = new AdamsMoulton(0.025,d1);

        am.adamsMethod();

        Adams = am.getResArray();

        ArrayList<Double> finalAdams = Adams;
        middleRec.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                if(newValue){
                    for(int i = 0; i < finalAdams.size();i++){
                        middleSeries.getData().add(new XYChart.Data<>(finalF.get(i), finalAdams.get(i)));
                    }
                    LC.getData().add(middleSeries);
                }
                if (oldValue) {
                    LC.getData().remove(middleSeries);
                }
            }
        });

//        LC.getData().addAll(series);
    }
}
