package uk.co.fostorial.sotm.structure;

import java.awt.Color;
import java.io.File;

public class HeroFrontCard extends Card {

	private String powerName;
	private String nemesisPath;
	private String powerText;
	private Color color;
	
	public HeroFrontCard(String name, Integer id) {
		super(Card.HERO_FRONT, id);
		setName(name);
		setClasses("N/A");
		setHealthPoints("40");
		setPortraitFile("images" + File.separator + "herofront" + File.separator + "portrait.png");
		powerName = "Power Name";
		powerText = "Power Text";
		color = new Color(217,146,131);
	}

	public String getPowerName() {
		return powerName;
	}

	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}

	public String getNemesisPath() {
		return nemesisPath;
	}

	public void setNemesisPath(String nemesisPath) {
		this.nemesisPath = nemesisPath;
	}

	public String getPowerText() {
		return powerText;
	}

	public void setPowerText(String powerText) {
		this.powerText = powerText;
	}
	
	public String getXML()
	{
		String xml = "";
		xml += " <herofrontcard>\n";
		xml += "  <id>" + getCardID().intValue() + "</id>\n";
		xml += "  <name>" + getName() + "</name>\n";
		xml += "  <classes>" + getClasses() + "</classes>\n";
		xml += "  <healthpoints>" + getHealthPoints() + "</healthpoints>\n";
		xml += "  <portrait>" + getPortraitFile() + "</portrait>\n";
		xml += "  <powername>" + getPowerName() + "</powername>\n";
		xml += "  <powertext>" + getPowerText() + "</powertext>\n";
		xml += "  <nemesispath>" + getNemesisPath() + "</nemesispath>\n";
		xml += "  <numberindeck>" + getNumberInDeck() + "</numberindeck>\n";
		xml += "  <powercolor>" + color.getRGB() + "</powercolor>\n";
		xml += " </herofrontcard>\n";
		return xml;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
