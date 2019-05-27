package tau.mmo.domain;

import javax.persistence.*;

@Entity(name = "Item")
@Table(name = "item")
@NamedQueries({
    @NamedQuery(name = "item.all", query = "Select p from Item p"),
	@NamedQuery(name = "item.allForPlayer", query = "Select p from Item p where player_id = :id")
})

public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String name;
	private Integer levelreq;
	private String itemclass;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
	private Player player;

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

	public Integer getLevelReq() {
		return levelreq;
	}

	public void setLevelReq(int levelreq) {
		this.levelreq = levelreq;
	}

  public String getItemClass() {
		return itemclass;
	}

	public void setItemClass(String itemclass) {
		this.itemclass = itemclass;
	}

	public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
	}

	@Override
	public boolean equals(Object o) {
		Item other = (Item) o;
		boolean ret = (other.getName().equals(this.getName()) &&
				((other.getId() == this.getId()) || (other.getId().longValue() == this.getId().longValue())) &&
				((other.getLevelReq() == this.getLevelReq())) &&
				((other.getItemClass().equals(this.getItemClass()))));
		return ret;
	}

	@Override
	public String toString() {
		return "[" + id + ", " + name + ", " + levelreq + ", " + itemclass + "]";
	}
}