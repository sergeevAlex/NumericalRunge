package sample;

import java.util.ArrayList;

public class Runge {

    private double cauchyValue;
    private double a;
    private double b;
    private double eps = Math.pow(10,-6);
    private double eps1 = 0.001;

    private boolean endOfBound;
    private ArrayList<Double> autoValues = new ArrayList<>();
    private ArrayList<Double> steps = new ArrayList<>();
    private ArrayList<Double> Xi = new ArrayList<>();
    private ArrayList<Double> constValues = new ArrayList<>();
    private ArrayList<Double> constXi = new ArrayList<>();


    public ArrayList<Double> getConstXi() {
        return constXi;
    }
    public Runge(double cauchyValue, double a, double b, boolean endOfBound) {
        this.cauchyValue = cauchyValue;
        this.a = a;
        this.b = b;
        this.endOfBound = endOfBound;
    }
    private double function(double x, double y){
     return (y*y + Math.pow(x,-4));
    }

    private double calcFormula(double step,double previous,double xi){
        return (previous + step*function(xi,previous));
    }

    public void method(){  // with auto-step
        int i = 0;
        autoValues.add(cauchyValue);
        steps.add(0.01);
        Xi.add(1.0);
        while(true){
            double fullH =  calcFormula(steps.get(i),autoValues.get(i),Xi.get(i));
            double halfH1 = calcFormula(steps.get(i)/2,autoValues.get(i),Xi.get(i)+steps.get(i)/2);
            double halfH =  calcFormula(steps.get(i)/2,halfH1,Xi.get(i)+steps.get(i));
            double epsFull = 2*(halfH-fullH);
            double epsHalf = halfH-fullH;
            if(Math.abs(epsHalf)<=eps1){
                autoValues.add(halfH + epsHalf);
                Xi.add(Xi.get(i)+steps.get(i));
                if(Math.abs(epsFull) <= eps1){
                    steps.add(2*steps.get(i));
                }
                else{
                    steps.add(steps.get(i));
                }
                i++;
                if(b - Xi.get(i)<=eps){
                    Xi.remove(i);
                    break;
                }
                else {
                    if(b - Xi.get(i)<steps.get(i)){
                        steps.set(i,b - steps.get(i));
                    }
                }
            }
            else {
                steps.set(i,steps.get(i)/2);
            }
        }



        constValues.add(-1.0);
        constXi.add(1.0);
        double h = (b-a)/i;


        for(int k = 1;k <= i;k++){
            constValues.add(calcFormula(h,constValues.get(k-1),constXi.get(k-1)));
            constXi.add(constXi.get(k-1)+h);

        }

    }


    public ArrayList<Double> getAutoValues() {
        return autoValues;
    }

    public void setAutoValues(ArrayList<Double> autoValues) {
        this.autoValues = autoValues;
    }

    public ArrayList<Double> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Double> steps) {
        this.steps = steps;
    }

    public ArrayList<Double> getConstValues() {
        return constValues;
    }

    public void setConstValues(ArrayList<Double> constValues) {
        this.constValues = constValues;
    }

    public ArrayList<Double> getXi() {
        return Xi;
    }

    public void setXi(ArrayList<Double> xi) {
        Xi = xi;
    }
}
