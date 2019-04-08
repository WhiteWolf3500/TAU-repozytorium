package pl.edu.pjatk.tau.mmo.jbehave;

import pl.edu.pjatk.tau.mmo.Player;
import pl.edu.pjatk.tau.mmo.PlayerMMO;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class DeletePlayerSteps {

  private Player player;
  private PlayerMMO playerlist;
  private Player deletedPlayer;

  @Given("there is an existing player list")
  public void example_list() throws Throwable {
      player = new Player();
      playerlist = new PlayerMMO();
      assertNotNull(player);
      assertNotNull(playerlist);
      Player temp1 = new Player("Adam",1,"Knight");
      Player temp2 = new Player("Eliza",1,"Archer");
      playerlist.addPlayer(temp1);
      playerlist.addPlayer(temp2);
  }

  @When("I try to delete a player with data $a and $b and $c")
  public void deleting_a_player(String a, int b, String c) throws Throwable {
      this.deletedPlayer = new Player(a,b,c);
      playerlist.deletePlayer(deletedPlayer);
  }

  @Then("that player should not exist")
  public void confirming_that_player_was_deleted() throws Throwable {
      assertEquals(-1, playerlist.getPlayerID(this.deletedPlayer));
  }

  @Then("list size won't change")
  public void checking_list() throws Throwable {
      assertEquals(2, playerlist.countPlayers());
  }


}