package pl.edu.pjatk.tau.mmo;

public class Player {

	private String name;
	private int level;
  private String heroclass;

	public Player() {
	}

	public Player(Player player) {
		name = player.name;
		level = player.level;
		heroclass = player.heroclass;
	}

	public Player(String name, int level, String heroclass) {
		this.name = name;
		this.level = level;
    this.heroclass = heroclass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
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
		return "[" + name + ", " + level + ", " + heroclass + "]";
	}
}