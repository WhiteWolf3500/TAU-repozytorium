package pl.edu.pjwstk.tau.domain;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import java.util.ArrayList;
import java.util.List;

@RunWith(BlockJUnit4ClassRunner.class)
public class PersonTest {
    @Test
    public void createObjectTest() {
        Person p = new Person();
        assertNotNull(p);
    }

    @Test
    public void personGettersAndSettersTest() {
        Person p = new Person();
        p.setId(1);
        p.setName("Adam");
        List<Double> g = new ArrayList<Double>();
        for (int i = 0; i < 3; i++) g.add(4.0);
        p.setGrades(g);

        assertEquals(1, p.getId());
        assertEquals("Adam", p.getName());
        assertEquals(g, p.getGrades());
    }
}