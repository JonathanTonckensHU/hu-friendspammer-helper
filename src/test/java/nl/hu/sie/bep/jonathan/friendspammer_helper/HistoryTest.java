package nl.hu.sie.bep.jonathan.friendspammer_helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class HistoryTest {
	@Test
	public void testHistory() {
		Email testEmail = new Email("test@example.com", "me@example.com", "test", "test text", false);
		MongoSaver.saveEmail(testEmail);
		List<Email> history = MongoSaver.getHistory();
		assertTrue(history.size() > 0, "there is at least one email in the history");
		assertEquals(history.get(history.size()-1), testEmail, "the last email is the email just send");
	}
}
