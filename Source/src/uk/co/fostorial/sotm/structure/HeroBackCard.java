package uk.co.fostorial.sotm.structure;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

public class HeroBackCard extends Card {
	
	private Color textColour;
	private String abilityLine1;
	private String abilityLine2;
	private String abilityLine3;
	private String abilityLine4;
	private String abilityLine5;
	private String abilityLine6;
	
	private Font textFont;
	private Color textFontColor;

	public HeroBackCard(String name, Integer id) {
		super(Card.HERO_BACK, id);
		setName(name);
		setClasses("N/A");
		setHealthPoints("N/A");
		setPortraitFile("images" + File.separator + "heroback" + File.separator + "portrait.png");
		
		textColour = new Color(217,146,131);
		
		abilityLine1 = "Ability 1 Line 1";
		abilityLine2 = "Ability 1 Line 2";
		abilityLine3 = "Ability 2 Line 1";
		abilityLine4 = "Ability 2 Line 2";
		abilityLine5 = "Ability 3 Line 1";
		abilityLine6 = "Ability 3 Line 2";
		
		textFontColor = Color.black;
		textFont = new Font("Comic Book", Font.PLAIN, 23);
	}

	public String getXML()
	{
		String xml = "";
		xml += " <herobackcard>\n";
		xml += "  <id>" + getCardID().intValue() + "</id>\n";
		xml += "  <name>" + getName() + "</name>\n";
		xml += "  <classes>" + getClasses() + "</classes>\n";
		xml += "  <healthpoints>" + getHealthPoints() + "</healthpoints>\n";
		xml += "  <portrait>" + getPortraitFile() + "</portrait>\n";
		xml += "  <numberindeck>" + getNumberInDeck() + "</numberindeck>\n";
		xml += "  <abilityline1>" + getAbilityLine1() + "</abilityline1>\n";
		xml += "  <abilityline2>" + getAbilityLine2() + "</abilityline2>\n";
		xml += "  <abilityline3>" + getAbilityLine3() + "</abilityline3>\n";
		xml += "  <abilityline4>" + getAbilityLine4() + "</abilityline4>\n";
		xml += "  <abilityline5>" + getAbilityLine5() + "</abilityline5>\n";
		xml += "  <abilityline6>" + getAbilityLine6() + "</abilityline6>\n";
		xml += "  <abilitycolor>" + getTextColour().getRGB() + "</abilitycolor>\n";
		xml += "  <textfontcolor>" + getTextFontColor().getRGB() + "</textfontcolor>\n";
		xml += "  <textfont>" + getTextFont().getFontName() + ";" + getTextFont().getStyle() + ";" + getTextFont().getSize() + "</textfont>\n";
		
		xml += " </herobackcard>\n";
		return xml;
	}

	public Color getTextColour() {
		return textColour;
	}

	public void setTextColour(Color textColour) {
		this.textColour = textColour;
	}

	public String getAbilityLine1() {
		return abilityLine1;
	}

	public void setAbilityLine1(String abilityLine1) {
		this.abilityLine1 = abilityLine1;
	}

	public String getAbilityLine2() {
		return abilityLine2;
	}

	public void setAbilityLine2(String abilityLine2) {
		this.abilityLine2 = abilityLine2;
	}

	public String getAbilityLine3() {
		return abilityLine3;
	}

	public void setAbilityLine3(String abilityLine3) {
		this.abilityLine3 = abilityLine3;
	}

	public String getAbilityLine4() {
		return abilityLine4;
	}

	public void setAbilityLine4(String abilityLine4) {
		this.abilityLine4 = abilityLine4;
	}

	public String getAbilityLine5() {
		return abilityLine5;
	}

	public void setAbilityLine5(String abilityLine5) {
		this.abilityLine5 = abilityLine5;
	}

	public String getAbilityLine6() {
		return abilityLine6;
	}

	public void setAbilityLine6(String abilityLine6) {
		this.abilityLine6 = abilityLine6;
	}

	public Font getTextFont() {
		return textFont;
	}

	public void setTextFont(Font textFont) {
		this.textFont = textFont;
	}

	public Color getTextFontColor() {
		return textFontColor;
	}

	public void setTextFontColor(Color textFontColor) {
		this.textFontColor = textFontColor;
	}
	
}
