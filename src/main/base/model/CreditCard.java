package model;

public class CreditCard {

	private String number;
	private String expiration;
	private boolean blacklisted;
	
	public CreditCard() {
		
	}
	
	public CreditCard(String number, String expiration, boolean blacklisted) {
		super();
		this.number = number;
		this.expiration = expiration;
		this.blacklisted = blacklisted;
	}
	
	public String getNumber() {
		String noSpaces = number.replaceAll("\\s", "");
		return noSpaces;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getExpiration() {
		return expiration;
	}
	
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

	public boolean getBlacklisted() {
		return blacklisted;
	}

	public void setBlacklisted(boolean blacklisted) {
		this.blacklisted = blacklisted;
	}

	@Override
	public String toString() {
		return "CreditCard [number=" + number + ", expiration=" + expiration + ", blacklisted=" + blacklisted + "]";
	}

	
	
	
	
	
}
