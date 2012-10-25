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
import uk.co.fostorial.sotm.structure.VillainCard;

public class CreatorTabVillainCard extends CreatorTab implements ActionListener {

	private static final long serialVersionUID = 618534742573139694L;
	
	private JLabel cardborder;
	private JLabel portrait;
	private JLabel hpImage;
	private JLabel classUnderlay;
	private JLabel nameUnderlay;
	private JLabel healthPoints;
	private JLabel name;
	private JLabel quoteText1;
	private JLabel quoteText2;
	private JLabel issueText;
	private JLabel cardClass;
	private JTextArea cardText;
	
	private JTextField txtCardName;
	private JTextField txtCardClass;
	private JTextField txtHealthPoints;
	private JTextField txtQuote1;
	private JTextField txtQuote2;
	private JTextField txtIssueText;
	private JButton textBGColour;
	private JButton nameBGColour;
	private JButton portraitButton;
	private JButton hpImageButton;
	private JCheckBox hpCheckBox;
	private JTextArea txtCardText;
	private String portraitPath;
	private String hpImagePath;
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
	
	private VillainCard villainCard;
	
	public CreatorTabVillainCard(CreatorFrame frame, VillainCard c)
	{
		super(frame);
		setCard(c);
		villainCard = c;
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
		
		txtCardName = new JTextField(villainCard.getName());
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
		
		nameBGColour = new JButton("Set Name Colour");
		nameBGColour.setBounds(5, nameFontColorButton.getBounds().y + nameFontColorButton.getBounds().height + 10, 250, 25);
		nameBGColour.addActionListener(this);
		getProperties().add(nameBGColour);
		
		txtCardClass = new JTextField(villainCard.getClasses());
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
		
		textBGColour = new JButton("Set Class Colour");
		textBGColour.setBounds(5, classFontColorButton.getBounds().y + classFontColorButton.getBounds().height + 10, 250, 25);
		textBGColour.addActionListener(this);
		getProperties().add(textBGColour);
		
		txtQuote1 = new JTextField(villainCard.getQuoteString1());
		txtQuote1.setBorder(BorderFactory.createTitledBorder("Quote Line 1"));
		txtQuote1.setBounds(5, textBGColour.getBounds().y + textBGColour.getBounds().height + 10, 250, 50);
		txtQuote1.setHorizontalAlignment(JTextField.CENTER);
		txtQuote1.getDocument().addDocumentListener(new PropertiesDocumentListener(quoteText1));
		getProperties().add(txtQuote1);
		
		txtQuote2 = new JTextField(villainCard.getQuoteString2());
		txtQuote2.setBorder(BorderFactory.createTitledBorder("Quote Line 2"));
		txtQuote2.setBounds(5, txtQuote1.getBounds().y + txtQuote1.getBounds().height + 10, 250, 50);
		txtQuote2.setHorizontalAlignment(JTextField.CENTER);
		txtQuote2.getDocument().addDocumentListener(new PropertiesDocumentListener(quoteText2));
		getProperties().add(txtQuote2);
		
		txtIssueText = new JTextField(villainCard.getIssueString());
		txtIssueText.setBorder(BorderFactory.createTitledBorder("Issue Text"));
		txtIssueText.setBounds(5, txtQuote2.getBounds().y + txtQuote2.getBounds().height + 10, 250, 50);
		txtIssueText.setHorizontalAlignment(JTextField.CENTER);
		txtIssueText.getDocument().addDocumentListener(new PropertiesDocumentListener(issueText));
		getProperties().add(txtIssueText);
		
		quoteFontButton = new JButton("Change Quote Font");
		quoteFontButton.setBounds(5, txtIssueText.getBounds().y + txtIssueText.getBounds().height + 10, 250, 25);
		quoteFontButton.addActionListener(this);
		getProperties().add(quoteFontButton);
		
		quoteFontColorButton = new JButton("Change Quote Color");
		quoteFontColorButton.setBounds(5, quoteFontButton.getBounds().y + quoteFontButton.getBounds().height + 10, 250, 25);
		quoteFontColorButton.addActionListener(this);
		getProperties().add(quoteFontColorButton);
		
		portraitButton = new JButton("Set Portrait Image");
		portraitButton.setBounds(5, quoteFontColorButton.getBounds().y + quoteFontColorButton.getBounds().height + 10, 250, 25);
		portraitButton.addActionListener(this);
		getProperties().add(portraitButton);
		
		txtHealthPoints = new JTextField(villainCard.getHealthPoints());
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
		txtCardText = new JTextArea(villainCard.getCardText());
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
		
		cardText = new JTextArea(villainCard.getCardText());
		cardText.setBounds(66, 668, 687 - 66, 870 - 660);
		cardText.setForeground(villainCard.getDescriptionFontColor());
		Font font = villainCard.getDescriptionFont();
		cardText.setFont(font);
		cardText.setOpaque(false);
		cardText.setLineWrap(true);
		cardText.setWrapStyleWord(true);
		cardText.setEditable(false);
		getImagePane().add(cardText);
		
		healthPoints = new JLabel(villainCard.getHealthPoints());
		font = villainCard.getHpFont();
		healthPoints.setFont(font);
		healthPoints.setHorizontalAlignment(JLabel.CENTER);
		healthPoints.setVerticalAlignment(JLabel.CENTER);
		healthPoints.setBounds(630, 35, 678 - 630, 111 - 35);
		healthPoints.setForeground(villainCard.getHpFontColor());
		healthPoints.setVisible(false);
		getImagePane().add(healthPoints);
		
		cardClass = new JLabel(villainCard.getClasses());
		cardClass.setBounds(79, 606, 520 - 64, 654 - 606);
		cardClass.setForeground(villainCard.getClassFontColor());
		font = villainCard.getClassFont();
		cardClass.setFont(font);
		cardClass.setHorizontalAlignment(JLabel.CENTER);
		cardClass.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(cardClass);
		
		quoteText1 = new JLabel(villainCard.getQuoteString1());
		quoteText1.setBounds(84, 908, 530 - 84, 932 - 908);
		quoteText1.setForeground(villainCard.getQuoteFontColor());
		font = villainCard.getQuoteFont();
		quoteText1.setFont(font);
		quoteText1.setHorizontalAlignment(JLabel.CENTER);
		quoteText1.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(quoteText1);
		
		quoteText2 = new JLabel(villainCard.getQuoteString2());
		quoteText2.setBounds(84, 935, 530 - 84, 955 - 935);
		quoteText2.setForeground(villainCard.getQuoteFontColor());
		quoteText2.setFont(font);
		quoteText2.setHorizontalAlignment(JLabel.CENTER);
		quoteText2.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(quoteText2);
		
		issueText = new JLabel(villainCard.getIssueString());
		issueText.setBounds(84, 961, 530 - 84, 985 - 961);
		issueText.setForeground(villainCard.getQuoteFontColor());
		issueText.setFont(font);
		issueText.setHorizontalAlignment(JLabel.CENTER);
		issueText.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(issueText);
		
		name = new JLabel(villainCard.getName());
		name.setBounds(66, 42, 703 - 66, 104 - 42);
		name.setForeground(villainCard.getNameFontColor());
		font = villainCard.getNameFont();
		name.setFont(font);
		name.setHorizontalAlignment(JLabel.LEFT);
		name.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(name);
		
		img = new ImageIcon(villainCard.getHealthPointsImage());
		img = new ImageIcon(getScaledImage(img.getImage(), 135, 135));
		hpImage = new JLabel(img);
		hpImage.setBounds(706 - 135, 47, 135, 135);
		hpImage.setVisible(false);
		getImagePane().add(hpImage);
		
		img = new ImageIcon("images" + File.separator + "blank.png");
		classUnderlay = new JLabel();
		classUnderlay.setBounds(79, 601, 520 - 64, 649 - 601);
		classUnderlay.setOpaque(true);
		classUnderlay.setBackground(villainCard.getClassColor());
		getImagePane().add(classUnderlay);
		
		img = new ImageIcon("images" + File.separator + "blank.png");
		nameUnderlay = new JLabel();
		nameUnderlay.setBounds(46, 47, 703 - 46, 111 - 47);
		nameUnderlay.setOpaque(true);
		nameUnderlay.setBackground(villainCard.getNameColor());
		getImagePane().add(nameUnderlay);
		
		
		img = new ImageIcon(villainCard.getPortraitFile());
		img = new ImageIcon(getScaledImage(img.getImage(), 703 - 45, 608 - 122));
		portrait = new JLabel(img);
		portrait.setBounds(46, 122, 703 - 45, 608 - 122);
		getImagePane().add(portrait);
		
		img = new ImageIcon("images" + File.separator + "villaincard" + File.separator + "cardback.png");
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
				quoteText2.setFont(chooser.getSelectedFont());
				issueText.setFont(chooser.getSelectedFont());
			}
		}
		
		if (e.getSource().equals(quoteFontColorButton))
		{
			Color c = selectColor(quoteText1.getForeground());
			if (c != null)
			{
				quoteText1.setForeground(c);
				quoteText2.setForeground(c);
				issueText.setForeground(c);
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
		villainCard.setName(txtCardName.getText());
		villainCard.setHealthPoints(txtHealthPoints.getText());
		villainCard.setPortraitFile(portraitPath);
		villainCard.setClasses(txtCardClass.getText());
		villainCard.setCardText(txtCardText.getText());
		villainCard.setHealthPointsImage(hpImagePath);
		villainCard.setHealthPointsVisible(hpCheckBox.isSelected());
		villainCard.setClassColor(classUnderlay.getBackground());
		villainCard.setNameColor(nameUnderlay.getBackground());
		villainCard.setQuoteString1(txtQuote1.getText());
		villainCard.setQuoteString2(txtQuote2.getText());
		villainCard.setIssueString(txtIssueText.getText());
		
		villainCard.setNameFont(name.getFont());
		villainCard.setNameFontColor(name.getForeground());
		villainCard.setHpFont(healthPoints.getFont());
		villainCard.setHpFontColor(healthPoints.getForeground());
		villainCard.setClassFont(cardClass.getFont());
		villainCard.setClassFontColor(cardClass.getForeground());
		villainCard.setDescriptionFont(cardText.getFont());
		villainCard.setDescriptionFontColor(cardText.getForeground());
		villainCard.setQuoteFont(quoteText1.getFont());
		villainCard.setQuoteFontColor(quoteText1.getForeground());
	}
}
