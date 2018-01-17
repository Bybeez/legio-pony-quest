package legio.pony_quest;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
	public static ArrayList<Pony> ponies = new ArrayList<Pony>();
	public static ArrayList<Team> teams = new ArrayList<Team>();
	public static ArrayList<Quest> quests = new ArrayList<Quest>();
	public static ArrayList<Chief> chiefs = new ArrayList<Chief>();
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int answer;
		boolean exit = false;
		System.out.println("All inputs must be composed of one word or one int (no spaces allowed)");
		while(!exit) {
			displayMenu();
			answer = scanner.nextInt();
			exit = handleAnswer(answer);
		}
			
		
		
	}
	
	public static void displayMenu() {
		System.out.println("##### ---- Pony Quest ---- #####"); //total 32 char
		System.out.println("#### ----   Creation   ---- ####"); 
		System.out.println("## - 1. Create Pony         - ##");
		System.out.println("## - 2. Create Team         - ##");
		System.out.println("## - 3. Create Quest        - ##");
		System.out.println("#### ----    Action    ---- ####"); 
		System.out.println("## - 4. Hire Pony           - ##");
		System.out.println("## - 5. Fire Pony           - ##");
		System.out.println("## - 6. Affect Team         - ##");
		System.out.println("## - 7. Affect Chief        - ##");
		System.out.println("## - 8. Start Quest         - ##");
		System.out.println("## - 9. Finish Quest        - ##");
		System.out.println("#### ----    Action    ---- ####"); 
		System.out.println("## - 10. Display all        - ##");
		System.out.println("## - 11. Exit               - ##");
		System.out.println("##### ---- ** #### ** ---- #####");
	}
	
	public static boolean handleAnswer(int answer) {
		switch (answer) {
		case 1:
			createPony();
			return false;
		case 2:
			createTeam();
			return false;
		case 3:
			createQuest();
			return false;
		case 4:
			hirePony();
			return false;
		case 5:
			firePony();
			return false;
		case 6:
			affectTeam();
			return false;
		case 7:
			affectLeader();
			return false;
		case 8:
			startQuest();
			return false;
		case 9:
			finishQuest();
			return false;
		case 10:
			displayAll();
			return false;
		case 11:
			System.out.println("Exiting");
			scanner.close();
			return true;
		default:
			System.out.println("Wrong input must be an int between 1 and 9");
			return false;
		}
	}
	
	public static void createPony() {
		System.out.println("Name : ");
		String name = scanner.next();
		System.out.println("Rank (1 : Rookie | 2 : Chief) :");
		int rank = scanner.nextInt();
		if(rank == 1) {
			ponies.add(new Rookie(name));
		}
		else {
			Chief chief = new Chief(name);
			ponies.add(chief);
			chiefs.add(chief);
		}
	}
	
	public static void createTeam() {
		System.out.println("Name : ");
		String name = scanner.next();
		teams.add(new Team(name));
	}

	public static void createQuest() {
		System.out.println("Name : ");
		String name = scanner.next();
		quests.add(new Quest(name));
	}

	public static void hirePony() {
		if(teams.isEmpty() || ponies.isEmpty()) {
			System.out.println("You are missing a team or a pony to do this");
			return;
		}
		System.out.println("Which team should hire a pony ?");
		displayTeams();
		int team = scanner.nextInt();
		System.out.println("Which pony should it hire ?");
		displayPonies();
		int pony = scanner.nextInt();
		Team teamMod = teams.get(team);
		Pony ponyMod = ponies.get(pony);
		teamMod.hire(ponyMod);
		teams.set(team, teamMod);
		ponies.set(pony, ponyMod);
	}
	
	public static void firePony() {
		if(teams.isEmpty() || ponies.isEmpty()) {
			System.out.println("You are missing a team or a pony to do this");
			return;
		}
		System.out.println("Which team should fire a pony ?");
		displayTeams();
		int team = scanner.nextInt();
		if(teams.get(team).getMembers().isEmpty()) {
			System.out.println("There is no pony in that team");
			return;
		}
		System.out.println("Which pony should it fire ?");
		displayPonies();
		for(Pony pony : teams.get(team).getMembers()) {
			System.out.println(ponies.indexOf(pony)+". " + pony.toString());
			
		}
		int pony = scanner.nextInt();
		Team teamMod = teams.get(team);
		Pony ponyMod = ponies.get(pony);
		ponyMod.setTeam(null);
		teamMod.fire(ponyMod);
		teams.set(team, teamMod);
		ponies.set(pony, ponyMod);
	}
	
	public static void affectTeam() {
		if(teams.isEmpty() || quests.isEmpty()) {
			System.out.println("You are missing a team or a quest to do this");
			return;
		}
		System.out.println("Which quest shall be done ?");
		displayQuests();
		int quest = scanner.nextInt();
		System.out.println("Who should do it ?");
		displayTeams();
		int team = scanner.nextInt();
		Quest questMod = quests.get(quest);
		Team teamMod = teams.get(team);
		questMod.affectTeam(teamMod);
		quests.set(quest, questMod);
		teams.set(team, teamMod);
	}
	
	public static void affectLeader() {
		if(chiefs.isEmpty() || quests.isEmpty()) {
			System.out.println("You are missing a chief or a quest to do this");
			return;
		}
		System.out.println("Which quest shall be lead ?");
		displayQuests();
		int quest = scanner.nextInt();
		System.out.println("Who should lead it ?");
		displayChiefs();
		int chief = scanner.nextInt();
		Quest questMod = quests.get(quest);
		Chief chiefMod = chiefs.get(chief);
		questMod.affectLeader(chiefMod);
		quests.set(quest, questMod);
		chiefs.set(chief, chiefMod);
		ponies.set(ponies.indexOf(chiefs.get(chief)), chiefMod);
	}
	
	public static void startQuest() {
		if(quests.isEmpty()) {
			System.out.println("You are missing a quest to do this");
			return;
		}
		boolean started;
		System.out.println("Which quest should start ?");
		displayQuests();
		int quest = scanner.nextInt();
		Quest questMod = quests.get(quest);
		started = questMod.start();
		System.out.println((started)? "The quest is starting" : "The quest is either already started or missing a team or missing a leader");
	}

	public static void finishQuest() {
		if(teams.isEmpty() || quests.isEmpty()) {
			System.out.println("You are missing a quest to do this");
			return;
		}
		System.out.println("Which quest is finished ?");
		displayQuests();
		int quest = scanner.nextInt();
		
		Quest questMod = quests.get(quest);
		questMod.end();
		quests.set(quest, questMod);
		/**
		ArrayList<Team> teamsMod = questMod.teams;
		for(Team team : teamsMod) {
			for(Pony pony : team.getMembers()) {
				((Rookie) pony).givePotato();
			}
		}**/
	}
	
	public static void displayPonies() {
		System.out.println("#### ----    Ponies    ---- ####"); 
		for(Pony pony : ponies) {
			System.out.println(ponies.indexOf(pony)+". " + pony.toString());
			
		}
	}
	
	public static void displayTeams() {
		System.out.println("#### ----     Teams    ---- ####"); 
		for(Team team : teams) {
			System.out.println(teams.indexOf(team)+". "+team.toString());
		}
	}
	
	public static void displayQuests() {
		System.out.println("#### ----    Quests    ---- ####"); 
		for(Quest quest : quests) {
			System.out.println(quests.indexOf(quest)+". "+quest.toString());
		}
	}
	
	public static void displayChiefs() {
		System.out.println("#### ----    Chiefs    ---- ####");
		for(Chief chief : chiefs) {
			System.out.println(chiefs.indexOf(chief)+". "+chief.toString());
		}
	}

	public static void displayAll() {
		displayPonies();
		displayTeams();
		displayQuests();
	}
	
}
