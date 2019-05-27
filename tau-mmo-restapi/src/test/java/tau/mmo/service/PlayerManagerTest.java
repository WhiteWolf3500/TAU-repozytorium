// package tau.mmo.service;

// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertNotNull;
// import static org.junit.Assert.assertNull;
// import static org.junit.Assert.assertTrue;
// import static org.junit.Assume.assumeNoException;

// import java.util.ArrayList;
// import java.util.Date;
// import java.util.LinkedList;
// import java.util.List;

// import org.junit.Before;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.test.annotation.Commit;
// import org.springframework.test.annotation.Rollback;
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
// import org.springframework.transaction.annotation.Transactional;

// import tau.mmo.domain.Player;
// import tau.mmo.domain.Item;

// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(locations = { "classpath:/beans.xml" })
// @Rollback
// //@Commit
// @Transactional(transactionManager = "txManager")
// public class PlayerManagerTest {

// 	@Autowired
//     PlayerManager playerManager;

// 	List<Long> playerIds;
// 	List<Long> itemIds;

// 	/**
// 	 * Helper method allowing for easier adding players to tests
// 	 * @param name
//      * @param level
//      * @param heroclass
// 	 * @return
// 	 */
// 	private Player addPlayerHelper(String name, int level, String heroclass, List<Item> possessedItems) {
// 		Long playerId;
// 		Player player;
// 		player = new Player();
// 		player.setName(name);
// 		player.setLevel(level);
//         player.setHeroClass(heroclass);
// 		player.setItems(possessedItems);
// 		playerIds.add(playerId = playerManager.addPlayer(player));
// 		assertNotNull(playerId);
// 		return player;
// 	}

// 	private Item addItemHelper(String name, int levelreq, String itemclass) {
// 		Long itemId;
// 		Item item;
// 		item = new Item();
// 		item.setName(name);
// 		item.setLevelReq(levelreq);
//         item.setItemClass(itemclass);
// 		itemIds.add(itemId = playerManager.addItem(item));
// 		assertNotNull(itemId);
// 		return item;
// 	}

// 	@Before
// 	public void setup() {
// 		playerIds = new LinkedList<>();
// 		itemIds = new LinkedList<>();

// 		// first 3 items
// 		Item item1 = addItemHelper("Dagger", 1, "melee");
// 		Item item2 = addItemHelper("Sword", 1, "melee");
// 		addItemHelper("Crossbow", 2, "range");

// 		ArrayList<Item> testItems = new ArrayList<Item>();
// 		testItems.add(item1);
// 		testItems.add(item2);

// 		// first 2 players
// 		addPlayerHelper("Adam", 2, "Archer", new LinkedList<Item>());
// 		addPlayerHelper("Eliza", 5, "Rouge", testItems);

// 	}

// 	@Test
// 	public void correctSetupTest() {
// 		assertTrue(playerIds.size() > 0);
// 	}

// 	@Test
// 	public void addPlayerTest() {
// 		int prevSize = playerManager.findAllPlayers().size();
// 		Player player = addPlayerHelper("Test123", 123, "Nobody", new LinkedList<Item >());
// 		assertEquals(prevSize+1,playerManager.findAllPlayers().size());
// 	}

// 	@Test
// 	public void addItemTest() {
// 		int prevSize = playerManager.findAllItems().size();
// 		Item item = addItemHelper("test", 123, "test");
// 		assertEquals(prevSize+1,playerManager.findAllItems().size());
// 	}

// 	@Test
// 	public void getAllPlayersTest() {
// 		List <Long> foundIds = new LinkedList<>();
// 		for (Player player: playerManager.findAllPlayers()) {
// 			if (playerIds.contains(player.getId())) foundIds.add(player.getId());
// 		}
// 		assertEquals(playerIds.size(), foundIds.size());
// 	}

// 	@Test
// 	public void getPlayerByIdTest() {
// 		Player player = playerManager.findPlayerById(playerIds.get(0));
// 		assertEquals("Adam",player.getName());
// 	}

// 	@Test
// 	public void deletePlayerTest() {
// 		int prevSize = playerManager.findAllPlayers().size();
// 		Player player = playerManager.findPlayerById(playerIds.get(0));
// 		assertNotNull(player);
// 		playerManager.deletePlayer(player);
// 		assertNull(playerManager.findPlayerById(playerIds.get(0)));
// 		assertEquals(prevSize-1,playerManager.findAllPlayers().size());
// 	}

// 	@Test
// 	public void updatePlayerTest() {
// 		Player player = playerManager.findPlayerById(playerIds.get(1));
// 		player.setName("Test321");
// 		player.setLevel(321);
// 		player.setHeroClass("God");
// 		playerManager.updatePlayer(player);
// 		Player updated = playerManager.findPlayerById(playerIds.get(1));
// 		assertEquals(updated.getName(),"Test321");
// 		assertEquals(updated.getLevel().intValue(), 321);
// 		assertEquals(updated.getHeroClass(), "God");
// 	}

// 	@Test
// 	public void findPlayersByNameFragment() {
// 		List<Player> players = playerManager.findPlayers("liz");
// 		assertEquals("Eliza", players.get(0).getName());
// 	}

// 	@Test
// 	public void getItemsofPlayer() {
// 		Player player = playerManager.findPlayerById(playerIds.get(1));
// 		assertEquals(2, player.getItems().size());
// 	}

// 	@Test
// 	public void addItemtoPlayer() {
// 		Player player = playerManager.findPlayerById(playerIds.get(0));
// 		Item item = addItemHelper("Crossbow", 2, "range");
// 		player.addItem(item);
// 		assertEquals(1, player.getItems().size());
// 	}

// 	@Test
// 	public void deleteItemtoPlayer() {
// 		Player player = playerManager.findPlayerById(playerIds.get(1));
// 		Item item = playerManager.findItemById(itemIds.get(0));
// 		player.removeItem(item);
// 		assertEquals(1, player.getItems().size());
// 	}

// 	@Test
// 	public void transferItem() {
// 		Player player1 = playerManager.findPlayerById(playerIds.get(0));
// 		Player player2 = playerManager.findPlayerById(playerIds.get(1));
// 		Item item = playerManager.findItemById(itemIds.get(0));
// 		playerManager.itemTransfer(player1, player2, item);
// 		assertEquals(1, playerManager.findPlayerById(playerIds.get(0)).getItems().size());
// 		assertEquals(1, playerManager.findPlayerById(playerIds.get(1)).getItems().size());
// 		assertEquals(item, playerManager.findPlayerById(playerIds.get(0)).getItems().get(0));
// 	}

// }
