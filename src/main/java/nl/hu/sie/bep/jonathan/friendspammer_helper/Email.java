package nl.hu.sie.bep.jonathan.friendspammer_helper;

public class Email {
	private String to;
	private String from;
	private String subject;
	private String text;
	private boolean asHTML;
	
	public Email(String to, String from, String subject, String text, boolean asHTML) {
		super();
		this.to = to;
		this.from = from;
		this.subject = subject;
		this.text = text;
		this.asHTML = asHTML;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setAsHTML(boolean asHTML) {
		this.asHTML = asHTML;
	}
	public String getTo() {
		return to;
	}
	public String getFrom() {
		return from;
	}
	public String getSubject() {
		return subject;
	}
	public String getText() {
		return text;
	}
	public boolean isAsHTML() {
		return asHTML;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (asHTML ? 1231 : 1237);
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Email other = (Email) obj;
		if (asHTML != other.asHTML)
			return false;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Email [to=" + to + ", from=" + from + ", subject=" + subject + ", text=" + text + ", asHTML=" + asHTML
				+ "]";
	}
}
