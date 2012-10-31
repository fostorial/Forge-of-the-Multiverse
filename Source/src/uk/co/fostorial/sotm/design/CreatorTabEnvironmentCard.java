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
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import say.swing.JFontChooser;
import uk.co.fostorial.sotm.CreatorFrame;
import uk.co.fostorial.sotm.structure.EnvironmentCard;

public class CreatorTabEnvironmentCard extends CreatorTab implements ActionListener {

	private static final long serialVersionUID = 5519760658108270353L;
	
	private JLabel cardborder;
	private JLabel portrait;
	private JLabel hpImage;
	private JLabel classUnderlay;
	private JLabel quoteUnderlay;
	private JLabel nameUnderlay;
	private JLabel healthPoints;
	private JLabel name;
	private JTextArea quoteText1;
	private JLabel cardClass;
	private JTextArea cardText;
	
	private JTextField txtCardName;
	private JTextField txtCardClass;
	private JTextField txtHealthPoints;
	private JTextArea txtQuote1;
	private JButton textBGColour;
	private JButton quoteBGColour;
	private JButton nameBGColour;
	private String portraitPath;
	private JButton portraitButton;
	private JButton hpImageButton;
	private String hpImagePath;
	private JCheckBox hpCheckBox;
	private JCheckBox classCheckBox;
	private JTextArea txtCardText;
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
	private JButton quoteFontButton;
	private JButton quoteFontColorButton;
	
	private EnvironmentCard environmentCard;
	
	public CreatorTabEnvironmentCard(CreatorFrame frame, EnvironmentCard c)
	{
		super(frame);
		this.environmentCard = c;
		super.setCard(c);
		setup();
	
		hpCheckBox.setSelected(c.isHealthPointsVisible());
		if (c.isHealthPointsVisible())
		{
			hpImage.setVisible(true);
			healthPoints.setVisible(true);
		}
		else
		{
			hpImage.setVisible(false);
			healthPoints.setVisible(false);
		}
		
		classCheckBox.setSelected(c.isClassVisible());
		if (c.isClassVisible())
		{
			classUnderlay.setVisible(true);
			cardClass.setVisible(true);
		}
		else
		{
			classUnderlay.setVisible(false);
			cardClass.setVisible(false);
		}
		
		portraitPath = c.getPortraitFile();
		hpImagePath = c.getHealthPointsImage();
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
		
		txtCardName = new JTextField(environmentCard.getName());
		txtCardName.setBorder(BorderFactory.createTitledBorder("Card Name"));
		txtCardName.setBounds(5, 10, 250, 50);
		txtCardName.setHorizontalAlignment(JTextField.CENTER);
		txtCardName.getDocument().addDocumentListener(new PropertiesDocumentListener(name));
		getProperties().add(txtCardName);
		
		nameFontButton = new JButton("Change Name Font");
		nameFontButton.setBounds(5, txtCardName.getBounds().y + txtCardName.getBounds().height + 10, 250, 25);
		nameFontButton.addActionListener(this);
		getProperties().add(nameFontButton);
		
		nameFontColorButton = new JButton("Change Name Color");
		nameFontColorButton.setBounds(5, nameFontButton.getBounds().y + nameFontButton.getBounds().height + 10, 250, 25);
		nameFontColorButton.addActionListener(this);
		getProperties().add(nameFontColorButton);
		
		nameBGColour = new JButton("Set Name Backing Colour");
		nameBGColour.setBounds(5, nameFontColorButton.getBounds().y + nameFontColorButton.getBounds().height + 10, 250, 25);
		nameBGColour.addActionListener(this);
		getProperties().add(nameBGColour);
		
		txtCardClass = new JTextField(environmentCard.getClasses());
		txtCardClass.setBorder(BorderFactory.createTitledBorder("Card Class"));
		txtCardClass.setBounds(5, nameBGColour.getBounds().y + nameBGColour.getBounds().height + 10, 250, 50);
		txtCardClass.setHorizontalAlignment(JTextField.CENTER);
		txtCardClass.getDocument().addDocumentListener(new PropertiesDocumentListener(cardClass));
		getProperties().add(txtCardClass);
		
		classFontButton = new JButton("Change Class Font");
		classFontButton.setBounds(5, txtCardClass.getBounds().y + txtCardClass.getBounds().height + 10, 250, 25);
		classFontButton.addActionListener(this);
		getProperties().add(classFontButton);
		
		classFontColorButton = new JButton("Change Class Color");
		classFontColorButton.setBounds(5, classFontButton.getBounds().y + classFontButton.getBounds().height + 10, 250, 25);
		classFontColorButton.addActionListener(this);
		getProperties().add(classFontColorButton);
		
		textBGColour = new JButton("Set Class Backing Colour");
		textBGColour.setBounds(5, classFontColorButton.getBounds().y + classFontColorButton.getBounds().height + 10, 250, 25);
		textBGColour.addActionListener(this);
		getProperties().add(textBGColour);
		
		classCheckBox = new JCheckBox("Show/Hide Class");
		classCheckBox.setBounds(5, textBGColour.getBounds().y + textBGColour.getBounds().height + 10, 250, 25);
		classCheckBox.addActionListener(this);
		getProperties().add(classCheckBox);
		
		JScrollPane spane1 = new JScrollPane();
		txtQuote1 = new JTextArea(environmentCard.getQuoteString1());
		spane1.setBorder(BorderFactory.createTitledBorder("Quote"));
		spane1.setBounds(5, classCheckBox.getBounds().y + classCheckBox.getBounds().height + 10, 250, 120);
		txtQuote1.getDocument().addDocumentListener(new TextareaPropertiesDocumentListener(quoteText1));
		spane1.setViewportView(txtQuote1);
		txtQuote1.setLineWrap(true);
		txtQuote1.setWrapStyleWord(true);
		getProperties().add(spane1);
		
		quoteFontButton = new JButton("Change Quote Font");
		quoteFontButton.setBounds(5, spane1.getBounds().y + spane1.getBounds().height + 10, 250, 25);
		quoteFontButton.addActionListener(this);
		getProperties().add(quoteFontButton);
		
		quoteFontColorButton = new JButton("Change Quote Color");
		quoteFontColorButton.setBounds(5, quoteFontButton.getBounds().y + quoteFontButton.getBounds().height + 10, 250, 25);
		quoteFontColorButton.addActionListener(this);
		getProperties().add(quoteFontColorButton);
		
		quoteBGColour = new JButton("Set Class Backing Colour");
		quoteBGColour.setBounds(5, quoteFontColorButton.getBounds().y + quoteFontColorButton.getBounds().height + 10, 250, 25);
		quoteBGColour.addActionListener(this);
		getProperties().add(quoteBGColour);
		
		portraitButton = new JButton("Set Portrait Image");
		portraitButton.setBounds(5, quoteBGColour.getBounds().y + quoteBGColour.getBounds().height + 10, 250, 25);
		portraitButton.addActionListener(this);
		getProperties().add(portraitButton);
		
		txtHealthPoints = new JTextField(environmentCard.getHealthPoints());
		txtHealthPoints.setBorder(BorderFactory.createTitledBorder("Health Points"));
		txtHealthPoints.setBounds(5, portraitButton.getBounds().y + portraitButton.getBounds().height + 10, 250, 50);
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
		
		hpImageButton = new JButton("Set Health Points Image");
		hpImageButton.setBounds(5, hpFontColorButton.getBounds().y + hpFontColorButton.getBounds().height + 10, 250, 25);
		hpImageButton.addActionListener(this);
		getProperties().add(hpImageButton);
		
		hpCheckBox = new JCheckBox("Show/Hide Health Points");
		hpCheckBox.setBounds(5, hpImageButton.getBounds().y + hpImageButton.getBounds().height + 10, 250, 25);
		hpCheckBox.addActionListener(this);
		getProperties().add(hpCheckBox);
		
		JScrollPane spane = new JScrollPane();
		txtCardText = new JTextArea(environmentCard.getCardText());
		txtCardText.setLineWrap(true);
		txtCardText.setWrapStyleWord(true);
		spane.setBorder(BorderFactory.createTitledBorder("Card Text"));
		spane.setBounds(5, hpCheckBox.getBounds().y + hpCheckBox.getBounds().height + 10, 250, 120);
		txtCardText.getDocument().addDocumentListener(new TextareaPropertiesDocumentListener(cardText));
		spane.setViewportView(txtCardText);
		getProperties().add(spane);
		
		descriptionFontButton = new JButton("Change Card Text Font");
		descriptionFontButton.setBounds(5, spane.getBounds().y + spane.getBounds().height + 10, 250, 25);
		descriptionFontButton.addActionListener(this);
		getProperties().add(descriptionFontButton);
		
		descriptionFontColorButton = new JButton("Change Card Text Color");
		descriptionFontColorButton.setBounds(5, descriptionFontButton.getBounds().y + descriptionFontButton.getBounds().height + 10, 250, 25);
		descriptionFontColorButton.addActionListener(this);
		getProperties().add(descriptionFontColorButton);
		
		updateButton = new JButton("Update Card");
		updateButton.setBounds(5, descriptionFontColorButton.getBounds().y + descriptionFontColorButton.getBounds().height + 10, 250, 25);
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
		
		cardText = new JTextArea(environmentCard.getCardText());
		cardText.setBounds(66, 668, 687 - 66, 870 - 660);
		cardText.setForeground(environmentCard.getDescriptionFontColor());
		Font font = environmentCard.getDescriptionFont();
		cardText.setFont(font);
		cardText.setOpaque(false);
		cardText.setLineWrap(true);
		cardText.setWrapStyleWord(true);
		cardText.setEditable(false);
		getImagePane().add(cardText);
		
		healthPoints = new JLabel(environmentCard.getHealthPoints());
		font = environmentCard.getHpFont();
		healthPoints.setFont(font);
		healthPoints.setHorizontalAlignment(JLabel.CENTER);
		healthPoints.setVerticalAlignment(JLabel.CENTER);
		healthPoints.setBounds(630, 35, 678 - 630, 111 - 35);
		healthPoints.setForeground(environmentCard.getHpFontColor());
		healthPoints.setVisible(false);
		getImagePane().add(healthPoints);
		
		cardClass = new JLabel(environmentCard.getClasses());
		cardClass.setBounds(79, 606, 520 - 64, 654 - 606);
		cardClass.setForeground(environmentCard.getClassFontColor());
		font = environmentCard.getClassFont();
		cardClass.setFont(font);
		cardClass.setHorizontalAlignment(JLabel.CENTER);
		cardClass.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(cardClass);
		
		quoteText1 = new JTextArea(environmentCard.getQuoteString1());
		quoteText1.setBounds(70, 886, 595 - 70, 960 - 874);
		quoteText1.setForeground(environmentCard.getQuoteFontColor());
		font = environmentCard.getQuoteFont();
		quoteText1.setFont(font);
		quoteText1.setOpaque(false);
		quoteText1.setLineWrap(true);
		quoteText1.setWrapStyleWord(true);
		quoteText1.setEditable(false);
		getImagePane().add(quoteText1);
		
		name = new JLabel(environmentCard.getName());
		name.setBounds(66, 42, 703 - 66, 104 - 42);
		name.setForeground(environmentCard.getNameFontColor());
		font = environmentCard.getNameFont();
		name.setFont(font);
		name.setHorizontalAlignment(JLabel.LEFT);
		name.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(name);
		
		img = new ImageIcon(environmentCard.getHealthPointsImage());
		img = new ImageIcon(getScaledImage(img.getImage(), 135, 135));
		hpImage = new JLabel(img);
		hpImage.setBounds(706 - 135, 47, 135, 135);
		hpImage.setVisible(false);
		getImagePane().add(hpImage);
		
		img = new ImageIcon("images" + File.separator + "blank.png");
		classUnderlay = new JLabel();
		classUnderlay.setBounds(79, 601, 520 - 64, 649 - 601);
		classUnderlay.setOpaque(true);
		classUnderlay.setBackground(environmentCard.getClassColor());
		getImagePane().add(classUnderlay);
		
		img = new ImageIcon("images" + File.separator + "blank.png");
		quoteUnderlay = new JLabel();
		quoteUnderlay.setBounds(60, 875, 595 - 60, 960 - 874);
		quoteUnderlay.setOpaque(true);
		quoteUnderlay.setBackground(environmentCard.getQuoteColor());
		getImagePane().add(quoteUnderlay);
		
		img = new ImageIcon("images" + File.separator + "blank.png");
		nameUnderlay = new JLabel();
		nameUnderlay.setBounds(46, 47, 703 - 46, 111 - 47);
		nameUnderlay.setOpaque(true);
		nameUnderlay.setBackground(environmentCard.getNameColor());
		getImagePane().add(nameUnderlay);
		
		img = new ImageIcon(environmentCard.getPortraitFile());
		portrait = new JLabel(img);
		portrait.setIcon(img);
		portrait.setBounds(46, 122, 703 - 45, 608 - 122);
		getImagePane().add(portrait);
		img = new ImageIcon(getScaledImage(img.getImage(), 703 - 45, 608 - 122));
		portrait.setIcon(img);
		
		img = new ImageIcon("images" + File.separator + "environment" + File.separator + "cardback.jpg");
		cardborder = new JLabel(img);
		cardborder.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		getImagePane().add(cardborder);
		setImageWidth(img.getIconWidth());
		setImageHeight(img.getIconHeight());
		
		
		getImagePane().setPreferredSize(new Dimension(img.getIconWidth(), img.getIconHeight()));
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(textBGColour))
		{
			Color c = selectColor(classUnderlay.getBackground());
			if (c != null)
			{
				classUnderlay.setBackground(c);
			}
		}
		
		if (e.getSource().equals(nameBGColour))
		{
			Color c = selectColor(classUnderlay.getBackground());
			if (c != null)
			{
				nameUnderlay.setBackground(c);
			}
		}
		
		if (e.getSource().equals(quoteBGColour))
		{
			Color c = selectColor(quoteUnderlay.getBackground());
			if (c != null)
			{
				quoteUnderlay.setBackground(c);
			}
		}
		
		if (e.getSource().equals(portraitButton))
		{
			JFileChooser chooser = getFrame().getChooser();
			int outcome = chooser.showOpenDialog(this);
			
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				portraitPath = chooser.getSelectedFile().getAbsolutePath();
				ImageIcon ii = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
				Image image = getScaledImage(ii.getImage(), 703 - 46, 608 - 122);
				ii = new ImageIcon(image);
				portrait.setIcon(ii);
			}
		}
		
		if (e.getSource().equals(hpImageButton))
		{
			JFileChooser chooser = getFrame().getChooser();
			int outcome = chooser.showOpenDialog(this);
			
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				hpImagePath = chooser.getSelectedFile().getAbsolutePath();
				ImageIcon ii = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
				Image image = getScaledImage(ii.getImage(), 135, 135);
				ii = new ImageIcon(image);
				hpImage.setIcon(ii);
			}
		}
		
		if (e.getSource().equals(hpCheckBox))
		{
			if (hpCheckBox.isSelected())
			{
				hpImage.setVisible(true);
				healthPoints.setVisible(true);
			}
			else
			{
				hpImage.setVisible(false);
				healthPoints.setVisible(false);
			}
		}
		
		if (e.getSource().equals(classCheckBox))
		{
			if (classCheckBox.isSelected())
			{
				classUnderlay.setVisible(true);
				cardClass.setVisible(true);
			}
			else
			{
				classUnderlay.setVisible(false);
				cardClass.setVisible(false);
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
			chooser.setSelectedFont(cardClass.getFont());
			int outcome = chooser.showDialog(this);
			
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				cardClass.setFont(chooser.getSelectedFont());
			}
		}
		
		if (e.getSource().equals(classFontColorButton))
		{
			Color c = selectColor(cardClass.getForeground());
			if (c != null)
			{
				cardClass.setForeground(c);
			}
		}
		
		if (e.getSource().equals(descriptionFontButton))
		{
			JFontChooser chooser = new JFontChooser();
			chooser.setSelectedFont(cardText.getFont());
			int outcome = chooser.showDialog(this);
			
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				cardText.setFont(chooser.getSelectedFont());
			}
		}
		
		if (e.getSource().equals(descriptionFontColorButton))
		{
			Color c = selectColor(cardText.getForeground());
			if (c != null)
			{
				cardText.setForeground(c);
			}
		}
		
		if (e.getSource().equals(quoteFontButton))
		{
			JFontChooser chooser = new JFontChooser();
			chooser.setSelectedFont(quoteText1.getFont());
			int outcome = chooser.showDialog(this);
			
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				quoteText1.setFont(chooser.getSelectedFont());
			}
		}
		
		if (e.getSource().equals(quoteFontColorButton))
		{
			Color c = selectColor(quoteText1.getForeground());
			if (c != null)
			{
				quoteText1.setForeground(c);
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
		environmentCard.setName(txtCardName.getText());
		environmentCard.setHealthPoints(txtHealthPoints.getText());
		environmentCard.setPortraitFile(portraitPath);
		environmentCard.setClasses(txtCardClass.getText());
		environmentCard.setClassVisible(classCheckBox.isSelected());
		environmentCard.setCardText(txtCardText.getText());
		environmentCard.setHealthPointsImage(hpImagePath);
		environmentCard.setHealthPointsVisible(hpCheckBox.isSelected());
		environmentCard.setClassColor(classUnderlay.getBackground());
		environmentCard.setQuoteColor(quoteUnderlay.getBackground());
		environmentCard.setNameColor(nameUnderlay.getBackground());
		environmentCard.setQuoteString1(txtQuote1.getText());
		
		environmentCard.setNameFont(name.getFont());
		environmentCard.setNameFontColor(name.getForeground());
		environmentCard.setHpFont(healthPoints.getFont());
		environmentCard.setHpFontColor(healthPoints.getForeground());
		environmentCard.setClassFont(cardClass.getFont());
		environmentCard.setClassFontColor(cardClass.getForeground());
		environmentCard.setDescriptionFont(cardText.getFont());
		environmentCard.setDescriptionFontColor(cardText.getForeground());
		environmentCard.setQuoteFont(quoteText1.getFont());
		environmentCard.setQuoteFontColor(quoteText1.getForeground());
	}
	
}
