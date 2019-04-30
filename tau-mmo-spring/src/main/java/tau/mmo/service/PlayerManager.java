package tau.mmo.service;

import java.util.List;

import tau.mmo.domain.Player;

public interface PlayerManager {

	Long addPlayer(Player player);
	void updatePlayer(Player player);
	Player findPlayerById(Long id);
	Player findPlayerByFragment(String fragment);
	void deletePlayer(Player player);
	List<Player> findAllPlayers();
}
