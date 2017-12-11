package sample;

import java.util.ArrayList;

public class AdamsMoulton {
    private ArrayList<Double> resArray = new ArrayList<>();
    private ArrayList<Double> Xi = new ArrayList<>();
    private ArrayList<Double> historyArray = new ArrayList<>();

    private double step;

    public AdamsMoulton(double step,ArrayList<Double> history) {
        for(int i = 0;i < 2;i++){
            resArray.add(history.get(i));
        }
        this.step = step;
        this.historyArray = history;
        Xi.add(1.0);
        for(int i = 1;i < history.size();i++){
            Xi.add(i,Xi.get(i-1)+step);
        }
    }

    public void adamsMethod(){
        double arrC[] = new double[3];
        arrC[1] = 1;
        for(int i = 2;i<Xi.size()-1;i++){
            arrC[2] = - resArray.get(i-1) - (step/12)*(5*Math.pow(Xi.get(i),-4)+8*(resArray.get(i-1)*resArray.get(i-1)+
                    Math.pow(Xi.get(i-1),-4))-resArray.get(i-2)*resArray.get(i-2) - Math.pow(Xi.get(i-2),-4));
            EqSolver qq = new EqSolver(arrC,1,2);
            resArray.add(qq.methodEq());
//            double deltaValue = (1-5*step/12);
//            resArray.add((resArray.get(i-1)+(step/12)*(5*(1-Xi.get(i)*Xi.get(i))+8*(resArray.get(i-1)-Xi.get(i-1)*Xi.get(i-1))-resArray.get(i-2)+Xi.get(i-2)*Xi.get(i-2) - 1)));
//            resArray.set(i,resArray.get(i)/deltaValue);
        }
    }

    public ArrayList<Double> getResArray() {
        return resArray;
    }

    public ArrayList<Double> getXi() {
        return Xi;
    }

    public double getStep() {
        return step;
    }
}
