package uk.co.fostorial.sotm.structure;

public class Card {
	
	final static int HERO_FRONT = 0;
	final static int HERO_BACK = 1;
	final static int HERO_CARD = 2;
	final static int VILLAIN_FRONT = 3;
	final static int VILLAIN_BACK = 4;
	final static int VILLAIN_CARD = 5;
	final static int ENVIRONMENT_CARD = 6;
	final static int CARD_BACK = 7;
	
	private String name;
	private int cardType;
	private String classes;
	private String healthPoints;
	private Integer numberInDeck;
	private Integer cardID;
	private String portraitFile;

	public Card(int cardType, Integer cardID) {
		this.cardType = cardType;
		this.cardID = cardID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCardType() {
		return cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	public String getCardTypeString()
	{
		String ret = "";
		
		switch (cardType)
		{
		case HERO_FRONT:
			ret = "Hero Front"; break;
		case HERO_BACK:
			ret = "Hero Back"; break;
		case HERO_CARD:
			ret = "Hero Card"; break;
		case VILLAIN_FRONT:
			ret = "Villain Character"; break;
		case VILLAIN_BACK:
			ret = "Villain Character"; break;
		case VILLAIN_CARD:
			ret = "Villain Card"; break;
		case ENVIRONMENT_CARD:
			ret = "Environment Card"; break;
		case CARD_BACK:
			ret = "Card Back"; break;
		}
		
		return ret;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public String getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(String healthPoints) {
		this.healthPoints = healthPoints;
	}

	public Integer getNumberInDeck() {
		return numberInDeck;
	}

	public void setNumberInDeck(Integer numberInDeck) {
		this.numberInDeck = numberInDeck;
	}

	public Integer getCardID() {
		return cardID;
	}

	public void setCardID(Integer cardID) {
		this.cardID = cardID;
	}
	
	public String getXML()
	{
		String xml = "";
		xml += " <card>";
		xml += " </card>\n";
		return xml;
	}

	public String getPortraitFile() {
		return portraitFile;
	}

	public void setPortraitFile(String portraitFile) {
		this.portraitFile = portraitFile;
	}

}
