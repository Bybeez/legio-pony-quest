package legio.pony_quest;
import java.util.ArrayList;

public class Quest {
	public Chief leader;
	public String name;
	public ArrayList<Team> teams = new ArrayList<Team>();
	public boolean started = false;
	
	public Quest(String name) {
		this.name = name; 
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean affectTeam(Team team) {
		if(!team.hasQuest()) {
			teams.add(team);
			team.setActive(this);
			return true;
		}
		return false;
	}
	
	public void affectLeader(Chief chief) {
		leader = chief;
		leader.addQuest(this);
	}
	
	public boolean start() {
		if(leader != null && !teams.isEmpty() && !started) {
			started = true;
			return true;
		}
		return false;
	}
	
	public void end(){
		leader.giveFame(teams.size());
		for(Team team : teams) {
			System.out.println("wtf");
			ArrayList<Pony> members = team.getMembers();
			System.out.println(members);
			for(Pony pony : members) {
				System.out.println("potato");
				if(pony.getRank() == "Rookie") {
					System.out.println("potato");
					((Rookie) pony).givePotato();
				}
			}
			team.setActive(null);
		}
	}
	
	@Override
	public String toString() {
		String displayed = "n : "+name;
		displayed += (teams.isEmpty()) ? "" : " t : "+teams.size();
		displayed += (leader == null) ? "" : " l : "+leader.getName();
		displayed += " s : "+started;
		return displayed;
	}
}
