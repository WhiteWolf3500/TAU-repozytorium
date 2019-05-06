package tau.mmo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeNoException;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import tau.mmo.domain.Player;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@Rollback
@Transactional(transactionManager = "txManager")
public class PlayerManagerTest {

	@Autowired
    PlayerManager playerManager;

	List<Long> playerIds;

	/**
	 * Helper method allowing for easier adding players to tests
	 * @param name
     * @param level
     * @param heroclass
	 * @return
	 */
	private Player addPlayerHelper(String name, int level, String heroclass) {
		Long playerId;
		Player player;
		player = new Player();
		player.setName(name);
		player.setLevel(level);
        player.setHeroClass(heroclass);
		playerIds.add(playerId = playerManager.addPlayer(player));
		assertNotNull(playerId);
		return player;
	}

	@Before
	public void setup() {
		playerIds = new LinkedList<>();

		// first 2 players
		addPlayerHelper("Adam", 2, "Archer");
		Player player = addPlayerHelper("Eliza", 5, "Rouge");

	}

	@Test
	public void correctSetupTest() {
		assertTrue(playerIds.size() > 0);
	}

	@Test
	public void addPlayerTest() {
		int prevSize = playerManager.findAllPlayers().size();
		Player player = addPlayerHelper("Test123", 123, "Nobody");
		assertEquals(prevSize+1,playerManager.findAllPlayers().size());
	}

	@Test
	public void getAllPlayersTest() {
		List <Long> foundIds = new LinkedList<>();
		for (Player player: playerManager.findAllPlayers()) {
			if (playerIds.contains(player.getId())) foundIds.add(player.getId());
		}
		assertEquals(playerIds.size(), foundIds.size());
	}

	@Test
	public void getPlayerByIdTest() {
		Player player = playerManager.findPlayerById(playerIds.get(0));
		assertEquals("Adam",player.getName());
	}

	@Test
	public void deletePlayerTest() {
		int prevSize = playerManager.findAllPlayers().size();
		Player player = playerManager.findPlayerById(playerIds.get(0));
		assertNotNull(player);
		playerManager.deletePlayer(player);
		assertNull(playerManager.findPlayerById(playerIds.get(0)));
		assertEquals(prevSize-1,playerManager.findAllPlayers().size());
	}

	@Test
	public void updatePlayerTest() {
		Player player = playerManager.findPlayerById(playerIds.get(0));
		player.setName("Test321");
		player.setLevel(321);
		player.setHeroClass("God");
		playerManager.updatePlayer(player);
		Player updated = playerManager.findPlayerById(playerIds.get(0));
		assertEquals(updated.getName(),"Test321");
		assertEquals(updated.getLevel().intValue(), 321);
		assertEquals(updated.getHeroClass(), "God");
	}

	@Test
	public void findPlayersByNameFragment() {
		List<Player> players = playerManager.findPlayers("dam");
		assertEquals("Adam", players.get(0).getName());
	}

}
