package uk.co.fostorial.sotm.structure;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

public class HeroFrontCard extends Card {

	private String powerName;
	private String nemesisPath;
	private String powerText;
	private Color color;
	
	private Font nameFont;
	private Color nameFontColor;
	private Font hpFont;
	private Color hpFontColor;
	private Font powerFont;
	private Color powerFontColor;
	private Font powerNameFont;
	private Color powerNameFontColor;
	
	public HeroFrontCard(String name, Integer id) {
		super(Card.HERO_FRONT, id);
		setName(name);
		setClasses("N/A");
		setHealthPoints("40");
		setPortraitFile("images" + File.separator + "herofront" + File.separator + "portrait.png");
		powerName = "Power Name";
		powerText = "Power Text";
		color = new Color(217,146,131);
		
		nameFontColor = Color.white;
		nameFont = new Font("SF Ferretopia", Font.PLAIN, 110);
		hpFontColor = Color.white;
		hpFont = new Font("SF Ferretopia", Font.PLAIN, 90);
		powerFontColor = Color.black;
		powerFont = new Font("Comic Book", Font.PLAIN, 23);
		powerNameFontColor = Color.black;
		powerNameFont = new Font("Comic Book", Font.PLAIN, 30);
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
		
		xml += " <namefontcolor>" + getNameFontColor().getRGB() + "</namefontcolor>\n";
		xml += " <namefont>" + getNameFont().getFontName() + ";" + getNameFont().getStyle() + ";" + getNameFont().getSize() + "</namefont>\n";
		xml += " <hpfontcolor>" + getHpFontColor().getRGB() + "</hpfontcolor>\n";
		xml += " <hpfont>" + getHpFont().getFontName() + ";" + getHpFont().getStyle() + ";" + getHpFont().getSize() + "</hpfont>\n";
		xml += " <powerfontcolor>" + getPowerFontColor().getRGB() + "</powerfontcolor>\n";
		xml += " <powerfont>" + getPowerFont().getFontName() + ";" + getPowerFont().getStyle() + ";" + getPowerFont().getSize() + "</powerfont>\n";
		xml += " <powernamefontcolor>" + getPowerNameFontColor().getRGB() + "</powernamefontcolor>\n";
		xml += " <powernamefont>" + getPowerNameFont().getFontName() + ";" + getPowerNameFont().getStyle() + ";" + getPowerNameFont().getSize() + "</powernamefont>\n";
				
		xml += " </herofrontcard>\n";
		return xml;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Font getNameFont() {
		return nameFont;
	}

	public void setNameFont(Font nameFont) {
		this.nameFont = nameFont;
	}

	public Color getNameFontColor() {
		return nameFontColor;
	}

	public void setNameFontColor(Color nameFontColor) {
		this.nameFontColor = nameFontColor;
	}

	public Font getHpFont() {
		return hpFont;
	}

	public void setHpFont(Font hpFont) {
		this.hpFont = hpFont;
	}

	public Color getHpFontColor() {
		return hpFontColor;
	}

	public void setHpFontColor(Color hpFontColor) {
		this.hpFontColor = hpFontColor;
	}

	public Font getPowerFont() {
		return powerFont;
	}

	public void setPowerFont(Font powerFont) {
		this.powerFont = powerFont;
	}

	public Color getPowerFontColor() {
		return powerFontColor;
	}

	public void setPowerFontColor(Color powerFontColor) {
		this.powerFontColor = powerFontColor;
	}

	public Font getPowerNameFont() {
		return powerNameFont;
	}

	public void setPowerNameFont(Font powerNameFont) {
		this.powerNameFont = powerNameFont;
	}

	public Color getPowerNameFontColor() {
		return powerNameFontColor;
	}

	public void setPowerNameFontColor(Color powerNameFontColor) {
		this.powerNameFontColor = powerNameFontColor;
	}

	
}
