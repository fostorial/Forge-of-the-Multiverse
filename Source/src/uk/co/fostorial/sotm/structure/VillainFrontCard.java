package uk.co.fostorial.sotm.structure;

import java.awt.Color;
import java.io.File;

public class VillainFrontCard extends Card {

	private String description1;
	private String nemesisPath;
	private Color color;
	private Color classColor;
	private String description2;
	
	public VillainFrontCard(String name, Integer id) {
		super(Card.VILLAIN_FRONT, id);
		setName(name);
		setClasses("Villain");
		setHealthPoints("40");
		setPortraitFile("images" + File.separator + "villainfront" + File.separator + "portrait.png");
		description1 = "Description 1";
		description2 = "Description 2";
		color = new Color(217, 146, 131);
		classColor = new Color(163, 178, 207);
	}

	public String getNemesisPath() {
		return nemesisPath;
	}

	public void setNemesisPath(String nemesisPath) {
		this.nemesisPath = nemesisPath;
	}
	
	public String getXML()
	{
		String xml = "";
		xml += " <villainfrontcard>\n";
		xml += "  <id>" + getCardID().intValue() + "</id>\n";
		xml += "  <name>" + getName() + "</name>\n";
		xml += "  <classes>" + getClasses() + "</classes>\n";
		xml += "  <healthpoints>" + getHealthPoints() + "</healthpoints>\n";
		xml += "  <portrait>" + getPortraitFile() + "</portrait>\n";
		xml += "  <description1>" + getDescription1() + "</description1>\n";
		xml += "  <description2>" + getDescription2() + "</description2>\n";
		xml += "  <nemesispath>" + getNemesisPath() + "</nemesispath>\n";
		xml += "  <numberindeck>" + getNumberInDeck() + "</numberindeck>\n";
		xml += "  <classcolor>" + classColor.getRGB() + "</classcolor>\n";
		xml += "  <descriptioncolor>" + color.getRGB() + "</descriptioncolor>\n";
		xml += " </villainfrontcard>\n";
		return xml;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getClassColor() {
		return classColor;
	}

	public void setClassColor(Color classColor) {
		this.classColor = classColor;
	}

	public String getDescription1() {
		return description1;
	}

	public void setDescription1(String description1) {
		this.description1 = description1;
	}

	public String getDescription2() {
		return description2;
	}

	public void setDescription2(String description2) {
		this.description2 = description2;
	}
	
	

}
