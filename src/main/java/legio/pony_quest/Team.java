package legio.pony_quest;
import java.util.ArrayList;

public class Team {
	private String name;
	private ArrayList<Pony> members = new ArrayList<Pony>();
	private Quest active;
	
	public Team(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setActive(Quest active) {
		this.active = active;
	}
	
	public Quest getActive() {
		return active;
	}
	
	public boolean hasQuest()
	{
		return (active != null)? true : false;
	}
	
	public ArrayList<Pony> getMembers() {
		return members;
	}
	
	public boolean hire(Pony rookie) {
		if(!rookie.hasTeam()) {
			members.add(rookie);
			rookie.setTeam(this);
			return true;
		}
		return false;
	}
	
	public boolean fire(Pony fired){
		if(members.contains(fired)) {
			members.remove(fired);
			fired.setTeam(null);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return ("n : "+name+" q : "+active);
	}
}
