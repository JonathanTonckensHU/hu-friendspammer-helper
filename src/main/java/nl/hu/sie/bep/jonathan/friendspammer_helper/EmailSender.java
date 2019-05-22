package nl.hu.sie.bep.jonathan.friendspammer_helper;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
	
	private static final String SMTP_HOST = "localhost";
	private static final String SMTP_PORT = "25";
	
	public static void sendEmail(Email email) throws MessagingException {

		Properties props = new Properties();
		props.put("mail.smtp.host", SMTP_HOST);
		props.put("mail.smtp.port", SMTP_PORT);

		String username = "7a1eaba28156ea";
		String password = "45e7c5f4925a9d";

		Session session = Session.getInstance(props,
			  new javax.mail.Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
		});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("spammer@spammer.com"));
		message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(email.getTo()));
		message.setSubject(email.getSubject());
		
		if (email.isAsHTML()) {
				message.setContent(email.getText(), "text/html; charset=utf-8");
		} else {
			message.setText(email.getText());	
		}
		Transport.send(message);

		MongoSaver.saveEmail(email);
	}

	public static void sendEmail(String subject, String[] toList, String messageBody, boolean asHtml) throws MessagingException {

		Properties props = new Properties();
		props.put("mail.smtp.host", SMTP_HOST);
		props.put("mail.smtp.port", SMTP_PORT);
		
		String username = "YOUR MAIL USERNAME";
		String password = "YOUR MAIL PASSWORD";

		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });

		for (int index = 0; index < toList.length; index++) {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("spammer@spammer.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(toList[index]));
			message.setSubject(subject);
			
			if (asHtml) {
					message.setContent(messageBody, "text/html; charset=utf-8");
			} else {
				message.setText(messageBody);	
			}
			Transport.send(message);
			
			//TODO: this doesn't save anything to the database, is that intended?
		}
	}

	private EmailSender() {} //make sure it can't be initialized because there are only static methods
}
