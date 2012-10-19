package uk.co.fostorial.sotm.structure;

import java.io.File;

public class BackCard extends Card {

	public BackCard(String name, Integer id) {
		super(Card.CARD_BACK, id);
		setName("Card Back");
		setClasses("N/A");
		setHealthPoints("N/A");
		setPortraitFile("images" + File.separator + "cardback" + File.separator + "portrait.png");
	}
	
	public String getXML()
	{
		String xml = "";
		xml += " <backcard>\n";
		xml += "  <id>" + getCardID().intValue() + "</id>\n";
		xml += "  <name>" + getName() + "</name>\n";
		xml += "  <classes>" + getClasses() + "</classes>\n";
		xml += "  <healthpoints>" + getHealthPoints() + "</healthpoints>\n";
		xml += "  <portrait>" + getPortraitFile() + "</portrait>\n";
		xml += "  <numberindeck>" + getNumberInDeck() + "</numberindeck>\n";
		xml += " </backcard>\n";
		return xml;
	}

}
