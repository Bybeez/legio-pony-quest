package legio.pony_quest;

public class Rookie extends Pony {
	private int potatoes;

	public Rookie(String name) {
		super(name, "Rookie");
		potatoes = 0;
	}

	public int getPotatoes() {
		return potatoes;
	}

	public void givePotato() {
		this.potatoes = potatoes + 1;
	}

	public String toString() {
		String displayed = super.toString();
		return displayed + " p : " +potatoes;
	}
}
