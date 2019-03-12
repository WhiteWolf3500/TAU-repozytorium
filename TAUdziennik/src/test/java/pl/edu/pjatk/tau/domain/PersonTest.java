package pl.edu.pjwstk.tau.domain;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

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
        assertEquals(1, p.getId());
        assertEquals("Adam", p.getName());
    }
}