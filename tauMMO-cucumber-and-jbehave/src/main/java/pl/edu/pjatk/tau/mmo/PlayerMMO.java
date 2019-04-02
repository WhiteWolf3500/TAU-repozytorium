package pl.edu.pjatk.tau.mmo;

import pl.edu.pjatk.tau.mmo.Player;

import java.util.List;
import java.util.LinkedList;

public class PlayerMMO {
    
    public List<Player> players;

    public void addPlayer(Player player) {
        
        if(player.getLevel() == 1) {
            players.add(new Player (player.getName(), player.getLevel(), player.getHeroClass()));
        }

    }

    public void deletePlayer(Player player) {

        if(players.indexOf(player.getName()) != -1) {
            players.remove(players.indexOf(player.getName()));
        }
    }

    public void updatePlayer(String player, Player update) {
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

    public Player getPlayer(int id) {

         return players.get(id);
    }

}