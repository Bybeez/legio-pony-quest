package legio.pony_quest;
import java.util.ArrayList;

public class Chief extends Pony{
	private int fame;
	private ArrayList<Quest> quests = new ArrayList<Quest>();

	public Chief(String name) {
		super(name, "Chief");
		fame = 0;
	}

	public int getFame() {
		return fame;
	}

	public void giveFame(int fame) {
		this.fame += fame;
	}
	
	public void addQuest(Quest quest) {
		quests.add(quest);
	}
	
	
	public String toString() {
		String displayed = super.toString();
		return displayed + " f : " +fame;
	}
}
