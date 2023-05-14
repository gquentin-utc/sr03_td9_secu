package fr.utc.sr03.td9_secu.model;

public class Message {

	private int id_msg;
	private String sujet_msg;
	private String corps_msg;
	private User userTo;
	private User userFrom;
	
	
	public Message() {
	}

	public Message(int id_msg, int id_user_to, int id_user_from, String sujet_msg, String corps_msg) {
		this.id_msg = id_msg;
		this.userTo = new User(id_user_to);
		this.userFrom = new User(id_user_from);
		this.sujet_msg = sujet_msg;
		this.corps_msg = corps_msg;
	}

	
	public int getId_msg() {
		return id_msg;
	}

	public void setId_msg(int id_msg) {
		this.id_msg = id_msg;
	}

	public String getSujet_msg() {
		return sujet_msg;
	}

	public void setSujet_msg(String sujet_msg) {
		this.sujet_msg = sujet_msg;
	}

	public String getCorps_msg() {
		return corps_msg;
	}

	public void setCorps_msg(String corps_msg) {
		this.corps_msg = corps_msg;
	}

	public User getUserTo() {
		return userTo;
	}

	public void setUserTo(User userTo) {
		this.userTo = userTo;
	}

	public User getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(User userFrom) {
		this.userFrom = userFrom;
	}
	
	
	
	
}
