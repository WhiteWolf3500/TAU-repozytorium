package tau.mmo.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import tau.mmo.domain.Player;

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
		sessionFactory.getCurrentSession().flush();
		return player.getId();
	}

	@Override
    public void updatePlayer(Player player) {
        sessionFactory.getCurrentSession().update(player);
    }

	@Override
	public Player findPlayerById(Long id) {
		return (Player) sessionFactory.getCurrentSession().get(Player.class, id);
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

}
