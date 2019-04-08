package pl.edu.pjatk.tau.mmo.jbehave;

import pl.edu.pjatk.tau.mmo.Player;
import pl.edu.pjatk.tau.mmo.PlayerMMO;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class AddPlayerSteps {

  private Player player;
  private PlayerMMO playerlist;

  @Given("there is a player object")
  public void there_is_a_player_object() throws Throwable {
    player = new Player();
    playerlist = new PlayerMMO();
    assertNotNull(player);
    assertNotNull(playerlist);
  }

  @Given("I type $a and $b and $c")
  public void creating_player_object(String a, int b, String c) throws Throwable {
    this.player = new Player(a,b,c);
  }

  @When("I send this data")
  public void adding_a_player() throws Throwable {
    playerlist.addPlayer(this.player);
  }

  @Then("the list should be $t")
  public void confirming_that_player_was_added(int t) throws Throwable {
      assertEquals(t, playerlist.countPlayers());
  }

}