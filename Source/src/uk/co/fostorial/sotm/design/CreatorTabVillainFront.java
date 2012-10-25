package uk.co.fostorial.sotm.design;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

import say.swing.JFontChooser;
import uk.co.fostorial.sotm.CreatorFrame;
import uk.co.fostorial.sotm.structure.VillainFrontCard;

public class CreatorTabVillainFront extends CreatorTab implements ActionListener {

	private static final long serialVersionUID = -5912094058209400572L;
	
	private JLabel cardborder;
	private JLabel portrait;
	private JLabel nemesisBack;
	private JLabel nemesisShine;
	private JLabel nemesisImage;
	private JLabel descriptionUnderlay;
	private JLabel classesUnderlay;
	private JLabel healthPoints;
	private JLabel name;
	private JLabel classes;
	private JLabel description1;
	private JLabel description2;
	
	private JTextField txtHeroName;
	private JTextField txtHealthPoints;
	private JTextField txtClasses;
	private JTextField txtDescriptionLine1;
	private JTextField txtDescriptionLine2;
	private JButton descriptionBGColour;
	private JButton classBGColour;
	private JButton portraitButton;
	private JButton nemesisButton;
	private String portraitLocation;
	private String nemesisLocation;
	private JButton updateButton;
	private JButton saveButton;
	private JButton cancelButton;
	private JButton nameFontButton;
	private JButton nameFontColorButton;
	private JButton hpFontButton;
	private JButton hpFontColorButton;
	private JButton classFontButton;
	private JButton classFontColorButton;
	private JButton descriptionFontButton;
	private JButton descriptionFontColorButton;
	
	private VillainFrontCard villainFrontCard;
	
	public CreatorTabVillainFront(CreatorFrame frame, VillainFrontCard c)
	{
		super(frame);
		setCard(c);
		villainFrontCard = c;
		portraitLocation = villainFrontCard.getPortraitFile();
		setup();
	}
	
	private void setup()
	{
		setupImagePane();
		setupProperties();
		
		/* Set divider start position */
		this.setDividerLocation(getFrame().getWidth() - 320);
	}
	
	private void setupProperties()
	{	
		getProperties().setLayout(null);
		getProperties().setBackground(Color.WHITE);
		getProperties().setOpaque(true);
		
		txtHeroName = new JTextField(villainFrontCard.getName());
		txtHeroName.setBorder(BorderFactory.createTitledBorder("Villain Name"));
		txtHeroName.setBounds(5, 10, 250, 50);
		txtHeroName.setHorizontalAlignment(JTextField.CENTER);
		txtHeroName.getDocument().addDocumentListener(new PropertiesDocumentListener(name));
		getProperties().add(txtHeroName);
		
		nameFontButton = new JButton("Change Name Font");
		nameFontButton.setBounds(5, txtHeroName.getBounds().y + txtHeroName.getBounds().height + 10, 250, 25);
		nameFontButton.addActionListener(this);
		getProperties().add(nameFontButton);
		
		nameFontColorButton = new JButton("Change Name Color");
		nameFontColorButton.setBounds(5, nameFontButton.getBounds().y + nameFontButton.getBounds().height + 10, 250, 25);
		nameFontColorButton.addActionListener(this);
		getProperties().add(nameFontColorButton);
		
		txtHealthPoints = new JTextField(villainFrontCard.getHealthPoints());
		txtHealthPoints.setBorder(BorderFactory.createTitledBorder("Health Points"));
		txtHealthPoints.setBounds(5, nameFontColorButton.getBounds().y + nameFontColorButton.getBounds().height + 10, 250, 50);
		txtHealthPoints.setHorizontalAlignment(JTextField.CENTER);
		txtHealthPoints.getDocument().addDocumentListener(new PropertiesDocumentListener(healthPoints));
		getProperties().add(txtHealthPoints);
		
		hpFontButton = new JButton("Change HP Font");
		hpFontButton.setBounds(5, txtHealthPoints.getBounds().y + txtHealthPoints.getBounds().height + 10, 250, 25);
		hpFontButton.addActionListener(this);
		getProperties().add(hpFontButton);
		
		hpFontColorButton = new JButton("Change HP Color");
		hpFontColorButton.setBounds(5, hpFontButton.getBounds().y + hpFontButton.getBounds().height + 10, 250, 25);
		hpFontColorButton.addActionListener(this);
		getProperties().add(hpFontColorButton);
		
		txtClasses = new JTextField(villainFrontCard.getClasses());
		txtClasses.setBorder(BorderFactory.createTitledBorder("Classes"));
		txtClasses.setBounds(5, hpFontColorButton.getBounds().y + hpFontColorButton.getBounds().height + 10, 250, 50);
		txtClasses.setHorizontalAlignment(JTextField.CENTER);
		txtClasses.getDocument().addDocumentListener(new PropertiesDocumentListener(classes));
		getProperties().add(txtClasses);
		
		classFontButton = new JButton("Change Class Font");
		classFontButton.setBounds(5, txtClasses.getBounds().y + txtClasses.getBounds().height + 10, 250, 25);
		classFontButton.addActionListener(this);
		getProperties().add(classFontButton);
		
		classFontColorButton = new JButton("Change Class Color");
		classFontColorButton.setBounds(5, classFontButton.getBounds().y + classFontButton.getBounds().height + 10, 250, 25);
		classFontColorButton.addActionListener(this);
		getProperties().add(classFontColorButton);
		
		classBGColour = new JButton("Set Class BG Colour");
		classBGColour.setBounds(5, classFontColorButton.getBounds().y + classFontColorButton.getBounds().height + 10, 250, 25);
		classBGColour.addActionListener(this);
		getProperties().add(classBGColour);
		
		txtDescriptionLine1 = new JTextField(villainFrontCard.getDescription1());
		txtDescriptionLine1.setBorder(BorderFactory.createTitledBorder("Description Line 1"));
		txtDescriptionLine1.setBounds(5, classBGColour.getBounds().y + classBGColour.getBounds().height + 10, 250, 50);
		txtDescriptionLine1.setHorizontalAlignment(JTextField.CENTER);
		txtDescriptionLine1.getDocument().addDocumentListener(new PropertiesDocumentListener(description1));
		getProperties().add(txtDescriptionLine1);
		
		txtDescriptionLine2 = new JTextField(villainFrontCard.getDescription2());
		txtDescriptionLine2.setBorder(BorderFactory.createTitledBorder("Description Line 2"));
		txtDescriptionLine2.setBounds(5, txtDescriptionLine1.getBounds().y + txtDescriptionLine1.getBounds().height + 10, 250, 50);
		txtDescriptionLine2.setHorizontalAlignment(JTextField.CENTER);
		txtDescriptionLine2.getDocument().addDocumentListener(new PropertiesDocumentListener(description2));
		getProperties().add(txtDescriptionLine2);
		
		descriptionFontButton = new JButton("Change Description Font");
		descriptionFontButton.setBounds(5, txtDescriptionLine2.getBounds().y + txtDescriptionLine2.getBounds().height + 10, 250, 25);
		descriptionFontButton.addActionListener(this);
		getProperties().add(descriptionFontButton);
		
		descriptionFontColorButton = new JButton("Change Description Color");
		descriptionFontColorButton.setBounds(5, descriptionFontButton.getBounds().y + descriptionFontButton.getBounds().height + 10, 250, 25);
		descriptionFontColorButton.addActionListener(this);
		getProperties().add(descriptionFontColorButton);
		
		descriptionBGColour = new JButton("Set Description BG Colour");
		descriptionBGColour.setBounds(5, descriptionFontColorButton.getBounds().y + descriptionFontColorButton.getBounds().height + 10, 250, 25);
		descriptionBGColour.addActionListener(this);
		getProperties().add(descriptionBGColour);
		
		portraitButton = new JButton("Set Portrait Image");
		portraitButton.setBounds(5, descriptionBGColour.getBounds().y + descriptionBGColour.getBounds().height + 10, 250, 25);
		portraitButton.addActionListener(this);
		getProperties().add(portraitButton);
		
		nemesisButton = new JButton("Set Nemesis Image");
		nemesisButton.setBounds(5, portraitButton.getBounds().y + portraitButton.getBounds().height + 10, 250, 25);
		nemesisButton.addActionListener(this);
		getProperties().add(nemesisButton);
		
		updateButton = new JButton("Update Card");
		updateButton.setBounds(5, nemesisButton.getBounds().y + nemesisButton.getBounds().height + 10, 250, 25);
		updateButton.addActionListener(this);
		getProperties().add(updateButton);
		
		saveButton = new JButton("Save Card");
		saveButton.setBounds(5, updateButton.getBounds().y + updateButton.getBounds().height + 10, 250, 25);
		saveButton.addActionListener(this);
		getProperties().add(saveButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(5, saveButton.getBounds().y + saveButton.getBounds().height + 10, 250, 25);
		cancelButton.addActionListener(this);
		getProperties().add(cancelButton);
		
		getProperties().setPreferredSize(new Dimension(260, cancelButton.getBounds().y + cancelButton.getBounds().height + 10));
	}
	
	private void setupImagePane()
	{
		ImageIcon img = null;
		
		classes = new JLabel(villainFrontCard.getClasses());
		classes.setBounds(132, 860, 478 - 132, 910 - 860);
		classes.setForeground(villainFrontCard.getClassFontColor());
		Font font = villainFrontCard.getClassFont();
		classes.setFont(font);
		classes.setHorizontalAlignment(JLabel.CENTER);
		classes.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(classes);
		
		description1 = new JLabel(villainFrontCard.getDescription1());
		description1.setBounds(64, 921, 680 - 64, 953 - 921);
		description1.setForeground(villainFrontCard.getDescriptionFontColor());
		font = villainFrontCard.getDescriptionFont();
		description1.setFont(font);
		description1.setHorizontalAlignment(JLabel.CENTER);
		description1.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(description1);
		
		description2 = new JLabel(villainFrontCard.getDescription2());
		description2.setBounds(64, 955, 680 - 64, 987 - 955);
		description2.setForeground(villainFrontCard.getDescriptionFontColor());
		font = villainFrontCard.getDescriptionFont();
		description2.setFont(font);
		description2.setHorizontalAlignment(JLabel.CENTER);
		description2.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(description2);
		
		name = new JLabel(villainFrontCard.getName());
		name.setBounds(70, 40, 600 - 70, 160 - 40);
		name.setForeground(villainFrontCard.getNameFontColor());
		font = villainFrontCard.getNameFont();
		name.setFont(font);
		name.setHorizontalAlignment(JLabel.LEFT);
		name.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(name);
		
		healthPoints = new JLabel(villainFrontCard.getHealthPoints());
		font = villainFrontCard.getHpFont();
		healthPoints.setFont(font);
		healthPoints.setHorizontalAlignment(JLabel.RIGHT);
		healthPoints.setVerticalAlignment(JLabel.CENTER);
		healthPoints.setBounds(540, 40, 685 - 540, 160 - 40);
		healthPoints.setForeground(villainFrontCard.getHpFontColor());
		getImagePane().add(healthPoints);
		
		img = new ImageIcon("images" + File.separator + "villainfront" + File.separator + "nemesisshine.png");
		nemesisShine = new JLabel(img);
		nemesisShine.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		getImagePane().add(nemesisShine);
		
		if (villainFrontCard.getNemesisPath() == null)
		{
			img = new ImageIcon("images" + File.separator + "villainfront" + File.separator + "nemesisshine.png");
		}
		else
		{
			img = new ImageIcon(villainFrontCard.getNemesisPath());
			img = new ImageIcon(getScaledImage(img.getImage(), 113, 113));
		}
		nemesisImage = new JLabel(img);
		nemesisImage.setBounds(584, 827, (584 + 113) - 584, (827 + 113) - 827);
		getImagePane().add(nemesisImage);
		
		img = new ImageIcon("images" + File.separator + "villainfront" + File.separator + "nemesisback.png");
		nemesisBack = new JLabel(img);
		nemesisBack.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		getImagePane().add(nemesisBack);
		
		img = new ImageIcon("images" + File.separator + "blank.png");
		classesUnderlay = new JLabel();
		classesUnderlay.setBounds(132, 854, 478 - 132, 904 - 854);
		classesUnderlay.setOpaque(true);
		classesUnderlay.setBackground(villainFrontCard.getClassColor());
		getImagePane().add(classesUnderlay);
		
		img = new ImageIcon("images" + File.separator + "blank.png");
		descriptionUnderlay = new JLabel();
		descriptionUnderlay.setBounds(104, 890, 640 - 104, 998 - 890);
		descriptionUnderlay.setOpaque(true);
		descriptionUnderlay.setBackground(villainFrontCard.getColor());
		getImagePane().add(descriptionUnderlay);
		
		img = new ImageIcon("images" + File.separator + "villainfront" + File.separator + "cardborder.png");
		cardborder = new JLabel(img);
		cardborder.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		getImagePane().add(cardborder);
		setImageWidth(img.getIconWidth());
		setImageHeight(img.getIconHeight());
		
		img = new ImageIcon(villainFrontCard.getPortraitFile());
		img = new ImageIcon(getScaledImage(img.getImage(), getImageWidth(), getImageHeight()));
		portrait = new JLabel(img);
		portrait.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		getImagePane().add(portrait);
		
		getImagePane().setPreferredSize(new Dimension(img.getIconWidth(), img.getIconHeight()));
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(descriptionBGColour))
		{
			Color c = selectColor(descriptionUnderlay.getBackground());
			if (c != null)
			{
				descriptionUnderlay.setBackground(c);
			}
		}
		
		if (e.getSource().equals(classBGColour))
		{
			Color c = selectColor(classesUnderlay.getBackground());
			if (c != null)
			{
				classesUnderlay.setBackground(c);
			}
		}
		
		if (e.getSource().equals(portraitButton))
		{
			JFileChooser chooser = getFrame().getChooser();
			int outcome = chooser.showOpenDialog(this);
			
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				portraitLocation = chooser.getSelectedFile().getAbsolutePath();
				ImageIcon ii = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
				Image image = getScaledImage(ii.getImage(), (int)getImagePane().getPreferredSize().getWidth(), (int)getImagePane().getPreferredSize().getHeight());
				ii = new ImageIcon(image);
				portrait.setIcon(ii);
				portrait.setBounds(0, 0, (int)getImagePane().getPreferredSize().getWidth(), (int)getImagePane().getPreferredSize().getHeight());
			}
		}
		
		if (e.getSource().equals(nemesisButton))
		{
			JFileChooser chooser = getFrame().getChooser();
			int outcome = chooser.showOpenDialog(this);
			
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				nemesisLocation = chooser.getSelectedFile().getAbsolutePath();
				ImageIcon ii = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
				Image image = getScaledImage(ii.getImage(), 113, 113);
				ii = new ImageIcon(image);
				nemesisImage.setIcon(ii);
			}
		}
		
		if (e.getSource().equals(nameFontButton))
		{
			JFontChooser chooser = new JFontChooser();
			chooser.setSelectedFont(name.getFont());
			int outcome = chooser.showDialog(this);
			
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				name.setFont(chooser.getSelectedFont());
			}
		}
		
		if (e.getSource().equals(nameFontColorButton))
		{
			Color c = selectColor(name.getForeground());
			if (c != null)
			{
				name.setForeground(c);
			}
		}
		
		if (e.getSource().equals(hpFontButton))
		{
			JFontChooser chooser = new JFontChooser();
			chooser.setSelectedFont(healthPoints.getFont());
			int outcome = chooser.showDialog(this);
			
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				healthPoints.setFont(chooser.getSelectedFont());
			}
		}
		
		if (e.getSource().equals(hpFontColorButton))
		{
			Color c = selectColor(healthPoints.getForeground());
			if (c != null)
			{
				healthPoints.setForeground(c);
			}
		}
		
		if (e.getSource().equals(classFontButton))
		{
			JFontChooser chooser = new JFontChooser();
			chooser.setSelectedFont(classes.getFont());
			int outcome = chooser.showDialog(this);
			
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				classes.setFont(chooser.getSelectedFont());
			}
		}
		
		if (e.getSource().equals(classFontColorButton))
		{
			Color c = selectColor(classes.getForeground());
			if (c != null)
			{
				classes.setForeground(c);
			}
		}
		
		if (e.getSource().equals(descriptionFontButton))
		{
			JFontChooser chooser = new JFontChooser();
			chooser.setSelectedFont(description1.getFont());
			int outcome = chooser.showDialog(this);
			
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				description1.setFont(chooser.getSelectedFont());
				description2.setFont(chooser.getSelectedFont());
			}
		}
		
		if (e.getSource().equals(descriptionFontColorButton))
		{
			Color c = selectColor(description1.getForeground());
			if (c != null)
			{
				description1.setForeground(c);
				description2.setForeground(c);
			}
		}
		
		if (e.getSource().equals(updateButton))
		{
			updateCard();
		}
		
		if (e.getSource().equals(saveButton))
		{
			updateCard();
			getFrame().closeCurrentFrame();
		}
		
		if (e.getSource().equals(cancelButton))
		{
			getFrame().closeCurrentFrame();
		}
	}
	
	private Color selectColor(Color colour)
	{
		Color c = colour;
		c = JColorChooser.showDialog(this, "Select Colour...", c);
		return c;
	}
	
	private void updateCard()
	{
		villainFrontCard.setName(txtHeroName.getText());
		villainFrontCard.setHealthPoints(txtHealthPoints.getText());
		villainFrontCard.setPortraitFile(portraitLocation);
		villainFrontCard.setClasses(txtClasses.getText());
		villainFrontCard.setNemesisPath(nemesisLocation);
		villainFrontCard.setDescription1(txtDescriptionLine1.getText());
		villainFrontCard.setDescription2(txtDescriptionLine2.getText());
		villainFrontCard.setClassColor(classesUnderlay.getBackground());
		villainFrontCard.setColor(descriptionUnderlay.getBackground());
		
		villainFrontCard.setNameFont(name.getFont());
		villainFrontCard.setNameFontColor(name.getForeground());
		villainFrontCard.setHpFont(healthPoints.getFont());
		villainFrontCard.setHpFontColor(healthPoints.getForeground());
		villainFrontCard.setClassFont(classes.getFont());
		villainFrontCard.setClassFontColor(classes.getForeground());
		villainFrontCard.setDescriptionFont(description1.getFont());
		villainFrontCard.setDescriptionFontColor(description1.getForeground());
	}

	public JButton getNameFontButton() {
		return nameFontButton;
	}

	public void setNameFontButton(JButton nameFontButton) {
		this.nameFontButton = nameFontButton;
	}
	
}
