package legio.pony_quest;

public class Pony {
	private String name;
	private String rank;
	private Team team;
	
	public Pony(String name, String rank) {
		this.name = name;
		this.rank = rank;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
	
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public boolean hasTeam() {
		return (this.team != null) ? true : false;
	}
	
	@Override
	public String toString() {
		String displayed = "n : "+name+" r: "+rank;
		displayed = (this.hasTeam()) ? displayed+" t: " + team.getName() : displayed;
		return displayed;
	}
	
}
