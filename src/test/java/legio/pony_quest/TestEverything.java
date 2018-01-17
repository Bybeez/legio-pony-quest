package legio.pony_quest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestEverything {
	
	@Test
	public void testStartQuestWhenOkay() {
		Chief chief = new Chief("testChief");
		Quest quest = new Quest("testQuest");
		Team team = new Team("testTeam");
		quest.affectLeader(chief);
		quest.affectTeam(team);
		assertTrue(quest.start());
		assertTrue(quest.started);
		System.out.println("Test 01 passed");
	}
	
	@Test
	public void testStartQuestWhenNotOkay() {
		Quest quest = new Quest("testQuest");
		assertFalse(quest.start());
		assertFalse(quest.started);
		System.out.println("Test 02 passed");
	}
}
