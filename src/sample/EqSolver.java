package sample;

public class EqSolver {

    private final double epsilon = 0.00001;
    private double[] coefs;
    private double a;
    private double b;

    public EqSolver(double[] arrCoefs,double a,double b) {
    this.coefs = arrCoefs;
    this.a = a;
    this.b = b;
    }

    public double methodEq () {
            double x_next = 0;
            double tmp;
            do{
                tmp = x_next;
                x_next = b - f(b) * (a - b) / (f(a) - f(b));
                a = b;
                b = tmp;
            } while (Math.abs(x_next - b) > epsilon);
            return x_next;
        }

        private double f (double x){
            return (coefs[0]*x*x+coefs[1]*x+coefs[2]);
        }


}
