package uk.co.fostorial.sotm.structure;

import java.awt.Color;
import java.io.File;

public class VillainCard extends Card {
	
	private String quoteString1;
	private String quoteString2;
	private String issueString;
	
	private boolean healthPointsVisible;
	private String healthPointsImage;
	
	private String cardText;
	
	private Color nameColor;
	private Color classColor;

	public VillainCard(String name, Integer id) {
		super(Card.VILLAIN_CARD, id);
		setName(name);
		setNumberInDeck(new Integer(1));
		setClasses("None");
		setHealthPoints("N/A");
		setPortraitFile("images" + File.separator + "villaincard" + File.separator + "portrait.png");
		
		this.setHealthPointsVisible(false);
		this.setHealthPointsImage("images" + File.separator + "villaincard" + File.separator + "hpimage.png");
		
		nameColor = new Color(56,46,131);
		classColor = new Color(245,78,18);
		
		cardText = "Card Text";
		
		quoteString1 = "\"Once in while you just";
		quoteString2 = "gotta improvise!\"";
		issueString = "- Citizen Snips, The Crab Cometh #4";
	}

	public String getXML()
	{
		String xml = "";
		xml += " <villaincard>\n";
		xml += "  <id>" + getCardID().intValue() + "</id>\n";
		xml += "  <name>" + getName() + "</name>\n";
		xml += "  <classes>" + getClasses() + "</classes>\n";
		xml += "  <healthpoints>" + getHealthPoints() + "</healthpoints>\n";
		xml += "  <healthpointsvisible>" + isHealthPointsVisible() + "</healthpointsvisible>\n";
		xml += "  <healthpointsimage>" + getHealthPointsImage() + "</healthpointsimage>\n";
		xml += "  <portrait>" + getPortraitFile() + "</portrait>\n";
		xml += "  <numberindeck>" + getNumberInDeck() + "</numberindeck>\n";
		xml += "  <cardtext>" + getCardText() + "</cardtext>\n";
		xml += "  <quotestring1>" + getQuoteString1() + "</quotestring1>\n";
		xml += "  <quotestring2>" + getQuoteString2() + "</quotestring2>\n";
		xml += "  <issuestring>" + getIssueString() + "</issuestring>\n";
		xml += "  <classcolour>" + getClassColor().getRGB() + "</classcolour>\n";
		xml += "  <namecolour>" + getNameColor().getRGB() + "</namecolour>\n";
		xml += " </villaincard>\n";
		return xml;
	}

	public String getQuoteString1() {
		return quoteString1;
	}

	public void setQuoteString1(String quoteString1) {
		this.quoteString1 = quoteString1;
	}

	public String getQuoteString2() {
		return quoteString2;
	}

	public void setQuoteString2(String quoteString2) {
		this.quoteString2 = quoteString2;
	}

	public String getIssueString() {
		return issueString;
	}

	public void setIssueString(String issueString) {
		this.issueString = issueString;
	}

	public boolean isHealthPointsVisible() {
		return healthPointsVisible;
	}

	public void setHealthPointsVisible(boolean healthPointsVisible) {
		this.healthPointsVisible = healthPointsVisible;
	}

	public String getHealthPointsImage() {
		return healthPointsImage;
	}

	public void setHealthPointsImage(String healthPointsImage) {
		this.healthPointsImage = healthPointsImage;
	}

	public String getCardText() {
		return cardText;
	}

	public void setCardText(String cardText) {
		this.cardText = cardText;
	}

	public Color getNameColor() {
		return nameColor;
	}

	public void setNameColor(Color nameColor) {
		this.nameColor = nameColor;
	}

	public Color getClassColor() {
		return classColor;
	}

	public void setClassColor(Color classColor) {
		this.classColor = classColor;
	}

}
