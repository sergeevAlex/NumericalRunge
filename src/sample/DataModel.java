package sample;

import javafx.beans.property.SimpleDoubleProperty;

public class DataModel {
    private SimpleDoubleProperty coordinate;
    private SimpleDoubleProperty value;
    private SimpleDoubleProperty step;

    public DataModel(double coordinate, double value, double step) {
        this.coordinate = new SimpleDoubleProperty(coordinate);
        this.value = new SimpleDoubleProperty(value);
        this.step = new SimpleDoubleProperty(step);
    }

    public double getCoordinate() {
        return coordinate.get();
    }

    public SimpleDoubleProperty coordinateProperty() {
        return coordinate;
    }

    public void setCoordinate(double coordinate) {
        this.coordinate.set(coordinate);
    }

    public double getValue() {
        return value.get();
    }

    public SimpleDoubleProperty valueProperty() {
        return value;
    }

    public void setValue(double value) {
        this.value.set(value);
    }

    public double getStep() {
        return step.get();
    }

    public SimpleDoubleProperty stepProperty() {
        return step;
    }

    public void setStep(double step) {
        this.step.set(step);
    }
}
