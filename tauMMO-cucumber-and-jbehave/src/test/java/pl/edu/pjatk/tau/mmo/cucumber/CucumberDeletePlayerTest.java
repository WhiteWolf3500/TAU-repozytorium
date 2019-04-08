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

public class CucumberDeletePlayerTest {

    private Player player;
    private PlayerMMO playerlist;
    private Player deletedPlayer;

    @Before
    public void setUp() {
        player = new Player();
        playerlist = new PlayerMMO();
    }

    @Given("there is an existing player list")
    public void example_list() throws Throwable {
        assertNotNull(player);
        assertNotNull(playerlist);
        Player temp1 = new Player("Adam",1,"Knight");
        Player temp2 = new Player("Eliza",1,"Archer");
        playerlist.addPlayer(temp1);
        playerlist.addPlayer(temp2);
    }

    @When("I delete a player with data {string} and {int} and {string}")
    public void deleting_a_player(String a, int b, String c) throws Throwable {
        this.deletedPlayer = new Player(a,b,c);
        playerlist.deletePlayer(deletedPlayer);
    }

    @Then("that player should be deleted {int}")
    public void confirming_that_player_was_deleted(int d) throws Throwable {
        assertEquals(d, playerlist.getPlayerID(this.deletedPlayer));
    }

    @Then("list size won't change")
    public void checking_list() throws Throwable {
        assertEquals(2, playerlist.countPlayers());
    }

}