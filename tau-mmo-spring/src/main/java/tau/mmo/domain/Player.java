package tau.mmo.domain;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

@Entity(name = "Player")
@Table(name = "player")
@NamedQueries({
	@NamedQuery(name = "player.all", query = "Select p from Player p"),
	@NamedQuery(name = "player.findPlayers", query = "Select c from Player c where c.name like :nameFragment")
})

public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String name;
	private Integer level;
	private String heroclass;

	@OneToMany(cascade = CascadeType.PERSIST,
		fetch = FetchType.EAGER,
		orphanRemoval=false,
		mappedBy = "player"
	)
	private List<Item> items = new LinkedList<>();

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

  public String getHeroClass() {
		return heroclass;
	}

	public void setHeroClass(String heroclass) {
		this.heroclass = heroclass;
	}

	@Override
	public boolean equals(Object o) {
		Player other = (Player) o;
		boolean ret = (other.getName().equals(this.getName()) &&
				((other.getId() == this.getId()) || (other.getId().longValue() == this.getId().longValue())) &&
				((other.getLevel() == this.getLevel())) &&
				((other.getHeroClass().equals(this.getHeroClass()))));
		return ret;
	}

	@Override
	public String toString() {
		return "[" + id + ", " + name + ", " + level + ", " + heroclass + "," + items.size() + "]";
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public void removeItem(Item item) {
		items.remove(item);
	}

}