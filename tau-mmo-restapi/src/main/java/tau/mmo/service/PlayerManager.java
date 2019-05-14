package tau.mmo.service;

import java.util.List;

import tau.mmo.domain.Player;
import tau.mmo.domain.Item;

public interface PlayerManager {

	Long addPlayer(Player player);
	void updatePlayer(Player player);
	Player findPlayerById(Long id);
	Player findPlayerByFragment(String fragment);
	void deletePlayer(Player player);
	List<Player> findAllPlayers();
	List<Player> findPlayers(String nameFragment);

	Long addItem(Item item);
	void updatePlayer(Item item);
	Item findItemById(Long id);
	void deleteItem(Item item);
	List<Item> findAllItems();
	List<Item> getItemsofPlayer(Long id);
	void itemTransfer(Player player1, Player player2, Item item);
}
