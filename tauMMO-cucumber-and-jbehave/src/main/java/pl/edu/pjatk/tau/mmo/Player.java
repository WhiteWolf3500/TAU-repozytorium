package pl.edu.pjatk.tau.mmo;

public class Player {

	private Long id;
	private String name;
	private Integer level;
    private String heroclass;

	public Player() {
	}

	public Player(Player player) {
		id = player.id;
		name = player.name;
		level = player.level;
		heroclass = player.heroclass;
	}

	public Player(String name, Integer level, String heroclass) {
		this.id = null;
		this.name = name;
		this.level = level;
        this.heroclass = heroclass;
	}

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
	public String toString() {
		return "[" + id + ", " + name + ", " + level + ", " + heroclass + "]";
	}
}