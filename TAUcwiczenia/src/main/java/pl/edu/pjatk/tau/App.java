package pl.edu.pjatk.tau;

import java.util.List;

public class App 
{
    public double quickMath(List<Double> v) {
        double sum = 0;
        for (double x : v) sum += x;
        return sum;
    }
}
