package pl.edu.pjatk.tau.mmo;

import pl.edu.pjatk.tau.mmo.Player;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class PlayerMMO {
    
    public List<Player> players;

    public PlayerMMO() {
        players = new ArrayList<Player>();
    }

    public void addPlayer(Player player) {
        
        if(player.getLevel() == 1) {
            Player temp = new Player (player.getName(), player.getLevel(), player.getHeroClass());
            players.add(temp);
        }
    }

    public void deletePlayer(Player player) {

        if(players.indexOf(player) != -1) {
            players.remove(players.indexOf(player));
        }
    }

    public void updatePlayer(Player player, Player update) {
        int position = players.indexOf(player);

        if(position != -1) {
            players.remove(position);
            players.add(position, new Player (update.getName(), update.getLevel(), update.getHeroClass()));
        }

    }

    public List<Player> getAllPlayers() {
    
        return players;    
    }

    public int countPlayers() {
    
        return players.size();    
    }

    public int getPlayerID(Player player) {

        return players.indexOf(player);
    }
}