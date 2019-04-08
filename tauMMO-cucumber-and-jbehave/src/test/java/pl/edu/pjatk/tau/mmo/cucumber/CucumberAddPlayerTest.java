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

public class CucumberAddPlayerTest {

    private Player player;
    private PlayerMMO playerlist;
    private String name;
    private int level;
    private String heroclass;

    @Before
    public void setUp() {
        player = new Player();
        playerlist = new PlayerMMO();
    }

    @Given("there is a player object")
    public void there_is_a_player_object() throws Throwable {
        assertNotNull(player);
        assertNotNull(playerlist);
    }

    @Given("I type name {string}")
    public void typing_name(String a) throws Throwable {
        this.name = a;
    }

    @Given("I type level {int}")
    public void typing_level(int b) throws Throwable {
        this.level = b;
    }


    @Given("I type heroclass {string}")
    public void typing_heroclass(String c) throws Throwable {
        this.heroclass = c;
    }

    @When("I send this data")
    public void adding_a_player() throws Throwable {
        Player temp = new Player(this.name,this.level,this.heroclass);
        playerlist.addPlayer(temp);
    }

    @Then("the list should be {int}")
    public void confirming_that_player_was_added(int t) throws Throwable {
        assertEquals(t, playerlist.countPlayers());
    }

}