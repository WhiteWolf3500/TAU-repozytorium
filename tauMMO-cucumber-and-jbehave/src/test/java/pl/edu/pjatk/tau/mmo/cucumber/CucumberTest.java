package pl.edu.pjatk.tau.mmo.cucumber;

import pl.edu.pjatk.tau.mmo.Player;
import pl.edu.pjatk.tau.mmo.PlayerMMO;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CucumberTest {

    private Player player;
    private PlayerMMO playerlist;

    @Before
    public void setUp() {
        player = new Player();
        playerlist = new PlayerMMO();
    }

    @Given("there is a player object")
    public void there_is_a_multiplication_object() throws Throwable {
        assertNotNull(player);
        assertNotNull(playerlist);
    }

    @When("data {string} and {int} and {string} are send")
    public void numbers_are_multiplied(String a, int b, String c) throws Throwable {
        playerlist.addPlayer(new Player(a,b,c));
    }

    @Then("the result should be {int}")
    public void the_result_should_be(int m) throws Throwable {
        assertEquals(m, playerlist.countPlayers());
    }

}