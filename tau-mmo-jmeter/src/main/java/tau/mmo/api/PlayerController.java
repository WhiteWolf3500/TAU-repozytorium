package tau.mmo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tau.mmo.domain.Item;
import tau.mmo.domain.Player;
import tau.mmo.service.PlayerManager;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@RestController
public class PlayerController {

    @Autowired
    PlayerManager playerManager;

    @RequestMapping("/players")
    public java.util.List<Player> getPlayers() {
        List<Player> players = new LinkedList<>();
        for (Player p : playerManager.findAllPlayers()) {
            players.add(p.clone());
        }
        return players;
    }
    
    @RequestMapping("/players/{page}/{count}")
    public java.util.List<Player> getPlayers(@PathVariable("page") int page, @PathVariable("count") int count) {
        return playerManager.findPageofPlayers(page, count);
    }

    @RequestMapping(value = "/player",method = RequestMethod.POST)
    public Player addPlayer(@RequestBody Player nplayer) {
        nplayer.setId(playerManager.addPlayer(nplayer));
        return nplayer;
    }

    @RequestMapping(value = "/playerupdate/{id}",method = RequestMethod.PUT)
    public Player getPlayer(@PathVariable("id") Long id, @RequestBody Player nplayer) throws SQLException {
        Player player = playerManager.findPlayerById(id);
        if(nplayer.getName()!=null)player.setName(nplayer.getName());
		if(nplayer.getLevel()!=null)player.setLevel(nplayer.getLevel());
		if(nplayer.getHeroClass()!=null)player.setHeroClass(nplayer.getHeroClass());
        playerManager.updatePlayer(player);
        return playerManager.findPlayerById(id).clone();
    }

    @RequestMapping("/")
    public String index() {
        return "Hello and welcome to Tau MMO!";
    }

    @RequestMapping(value = "/player/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Player getPlayer(@PathVariable("id") Long id) throws SQLException {
        return playerManager.findPlayerById(id).clone();
    }

    @RequestMapping(value = "/players", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Player> getPlayers(@RequestParam(value = "filter", required = false) String f) throws SQLException {
        List<Player> players = new LinkedList<Player>();
        for (Player p : playerManager.findAllPlayers()) {
            if (f == null) {
                players.add(p.clone());
            } else if (p.getName().contains(f)) {
                players.add(p);
            }
        }
        return players;
    }

    @RequestMapping(value = "/player/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deletePlayer(@PathVariable("id") Long id) throws SQLException {
        playerManager.deletePlayer(playerManager.findPlayerById(id));
        return "OK";
    }

}