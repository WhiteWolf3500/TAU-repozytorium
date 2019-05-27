package tau.mmo.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import tau.mmo.domain.Player;
import tau.mmo.domain.Item;

@Component
@Transactional
public class PlayerManagerHibernateImpl implements PlayerManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Long addPlayer(Player player) {
        if (player.getId() != null) throw new IllegalArgumentException("the player ID should be null if added to database");
		sessionFactory.getCurrentSession().persist(player);
		for (Item item : player.getItems()) {
			item.setPlayer(player);
			sessionFactory.getCurrentSession().update(item);
		}
		sessionFactory.getCurrentSession().flush();
		return player.getId();
	}

	@Override
    public void updatePlayer(Player player) {
        sessionFactory.getCurrentSession().update(player);
    }

	@Override
	public Player findPlayerById(Long id) {
		Player p = (Player) sessionFactory
		.getCurrentSession()
		.get(Player.class, id);
		return p;
	}

	@Override
	public Player findPlayerByFragment(String fragment) {
		return (Player) sessionFactory.getCurrentSession().get(Player.class, fragment);
	}

	@Override
	public void deletePlayer(Player player) {
		player = (Player) sessionFactory.getCurrentSession().get(Player.class,
				player.getId());
		sessionFactory.getCurrentSession().delete(player);
	}

	@Override
	public List<Player> findAllPlayers() {
		return sessionFactory.getCurrentSession().getNamedQuery("player.all")
				.list();
	}

	@Override
	public List<Player> findPageofPlayers(int page, int count) {
		Query q = sessionFactory.getCurrentSession().getNamedQuery("player.all");
		q.setFirstResult(page*count);
		q.setMaxResults(count);
		return q.list();
	}

	@Override
	public List<Player> findPlayers(String nameFragment) {
		return (List<Player>) sessionFactory.getCurrentSession()
				.getNamedQuery("player.findPlayers")
				.setString("nameFragment", "%"+nameFragment+"%")
				.list();
	}

	@Override
	public Long addItem(Item item) {
        if (item.getId() != null) throw new IllegalArgumentException("the item ID should be null if added to database");
		sessionFactory.getCurrentSession().persist(item);
		sessionFactory.getCurrentSession().flush();
		return item.getId();
	}

	@Override
    public void updatePlayer(Item item) {
        sessionFactory.getCurrentSession().update(item);
    }

	@Override
	public Item findItemById(Long id) {
		return (Item) sessionFactory.getCurrentSession().get(Item.class, id);
	}

	@Override
	public void deleteItem(Item item) {
		item = (Item) sessionFactory.getCurrentSession().get(Item.class,
				item.getId());
		sessionFactory.getCurrentSession().delete(item);
	}

	@Override
	public List<Item> findAllItems() {
		return sessionFactory.getCurrentSession().getNamedQuery("item.all")
				.list();
	}

	@Override
	public List<Item> getItemsofPlayer(Long id) {
		return sessionFactory.getCurrentSession()
		.getNamedQuery("item.allForPlayer")
		.setLong("id", id).list();
	}

	@Override
	public void itemTransfer(Player player1, Player player2, Item item) {
		player2.removeItem(item);
		player1.addItem(item);
		updatePlayer(player1);
		updatePlayer(player2);
	}
}
