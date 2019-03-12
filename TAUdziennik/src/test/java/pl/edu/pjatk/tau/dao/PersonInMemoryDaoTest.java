package pl.edu.pjwstk.tau.dao;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import pl.edu.pjwstk.tau.domain.Person;

import java.util.Collection;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(BlockJUnit4ClassRunner.class)
public class PersonInMemoryDaoTest {

    PersonInMemoryDao dao;

    @Before
    public void setup() {
        List<Double> g = new ArrayList<Double>();
        for (int i = 0; i < 3; i++) g.add(4.0);
        Person p1 = new Person();
        Person p2 = new Person();
        p1.setId(1L);
        p1.setName("Adam");
        p1.setGrades(g);
        p2.setId(2L);
        p2.setName("Janusz");
        p2.setGrades(g);
        dao = new PersonInMemoryDao();
        dao.persons = new HashMap<Long, Person>();
        dao.persons.put(1L,p1);
        dao.persons.put(2L,p2);
    }

    @Test
    public void createDaoObjectTest() {
        assertNotNull(dao);
    }

    @Test
    public void getPersonThatExistsTest() {
        Optional<Person> p = dao.get(2L);
        assertThat(p.get().getName(), is("Janusz"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateNotExisitingPersonShouldThrowTest() {
        Person p1 = new Person();
        p1.setId(9);
        p1.setName("Daniel");
        List<Double> g = new ArrayList<Double>();
        for (int i = 0; i < 3; i++) g.add(4.0);
        p1.setGrades(g);
        dao.update(p1);
    }

    @Test
    public  void updateOneRecordTest() {
        List<Double> g = new ArrayList<Double>();
        for (int i = 0; i < 3; i++) g.add(4.0);
        Person p1 = new Person();
        p1.setId(1);
        p1.setName("Kewin");
        p1.setGrades(g);
        Person p2 = new Person();
        p2.setId(2);
        p2.setName("Janusz");
        p2.setGrades(g);

        Collection<Person> listExpected = dao.persons.values();
        for (Person p:listExpected) if (p.getId()==1) p.setName("Kewin");

        dao.update(p1);

        Collection<Person> listAfter = dao.persons.values();
        assertArrayEquals(listExpected.toArray(), listAfter.toArray());
    }





}