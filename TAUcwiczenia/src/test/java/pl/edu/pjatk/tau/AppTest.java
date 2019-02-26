package pl.edu.pjatk.tau;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class AppTest {
    App matematics;

    @Before
    public void init() {
       matematics = new App();
    }

    @Test
    public void appClassExistsCheck() {
        assertNotNull(matematics);
    }

    @Test
    public void appClassQuickMathCheck() {
        List<Double> v = new ArrayList<Double>();
        v.add(2.0);
        v.add(2.0);
        double l = matematics.quickMath(v);
        assertEquals(4, l, 0.001);
    }

    @Test
    public void appClassLoopingCheck() {
        List<Double> v = new ArrayList<Double>();
        for (int i = 0; i < 10; i++) v.add(0.1);
        double l = matematics.quickMath(v);
        assertEquals(1, l, 0.001);
    }
}