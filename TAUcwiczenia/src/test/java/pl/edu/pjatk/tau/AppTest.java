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
}