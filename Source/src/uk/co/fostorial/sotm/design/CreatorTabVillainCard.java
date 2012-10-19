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
	
	private VillainCard villainCard;
	
	public CreatorTabVillainCard(CreatorFrame frame, VillainCard c)
	{
		super(frame);
		setCard(c);
		villainCard = c;
		setup();
		
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
		txtCardName.setBounds(5, 0, 250, 50);
		txtCardName.setHorizontalAlignment(JTextField.CENTER);
		txtCardName.getDocument().addDocumentListener(new PropertiesDocumentListener(name));
		getProperties().add(txtCardName);
		
		nameBGColour = new JButton("Set Name Colour");
		nameBGColour.setBounds(5, 60, 250, 25);
		nameBGColour.addActionListener(this);
		getProperties().add(nameBGColour);
		
		txtCardClass = new JTextField(villainCard.getClasses());
		txtCardClass.setBorder(BorderFactory.createTitledBorder("Card Class"));
		txtCardClass.setBounds(5, 90, 250, 50);
		txtCardClass.setHorizontalAlignment(JTextField.CENTER);
		txtCardClass.getDocument().addDocumentListener(new PropertiesDocumentListener(cardClass));
		getProperties().add(txtCardClass);
		
		textBGColour = new JButton("Set Class Colour");
		textBGColour.setBounds(5, 150, 250, 25);
		textBGColour.addActionListener(this);
		getProperties().add(textBGColour);
		
		txtQuote1 = new JTextField(villainCard.getQuoteString1());
		txtQuote1.setBorder(BorderFactory.createTitledBorder("Quote Line 1"));
		txtQuote1.setBounds(5, 180, 250, 50);
		txtQuote1.setHorizontalAlignment(JTextField.CENTER);
		txtQuote1.getDocument().addDocumentListener(new PropertiesDocumentListener(quoteText1));
		getProperties().add(txtQuote1);
		
		txtQuote2 = new JTextField(villainCard.getQuoteString2());
		txtQuote2.setBorder(BorderFactory.createTitledBorder("Quote Line 2"));
		txtQuote2.setBounds(5, 240, 250, 50);
		txtQuote2.setHorizontalAlignment(JTextField.CENTER);
		txtQuote2.getDocument().addDocumentListener(new PropertiesDocumentListener(quoteText2));
		getProperties().add(txtQuote2);
		
		txtIssueText = new JTextField(villainCard.getIssueString());
		txtIssueText.setBorder(BorderFactory.createTitledBorder("Issue Text"));
		txtIssueText.setBounds(5, 300, 250, 50);
		txtIssueText.setHorizontalAlignment(JTextField.CENTER);
		txtIssueText.getDocument().addDocumentListener(new PropertiesDocumentListener(issueText));
		getProperties().add(txtIssueText);
		
		portraitButton = new JButton("Set Portrait Image");
		portraitButton.setBounds(5, 360, 250, 25);
		portraitButton.addActionListener(this);
		getProperties().add(portraitButton);
		
		txtHealthPoints = new JTextField(villainCard.getHealthPoints());
		txtHealthPoints.setBorder(BorderFactory.createTitledBorder("Health Points"));
		txtHealthPoints.setBounds(5, 390, 250, 50);
		txtHealthPoints.setHorizontalAlignment(JTextField.CENTER);
		txtHealthPoints.getDocument().addDocumentListener(new PropertiesDocumentListener(healthPoints));
		getProperties().add(txtHealthPoints);
		
		hpImageButton = new JButton("Set Health Points Image");
		hpImageButton.setBounds(5, 450, 250, 25);
		hpImageButton.addActionListener(this);
		getProperties().add(hpImageButton);
		
		hpCheckBox = new JCheckBox("Show/Hide Health Points");
		hpCheckBox.setBounds(5, 480, 250, 25);
		hpCheckBox.addActionListener(this);
		getProperties().add(hpCheckBox);
		
		JScrollPane spane = new JScrollPane();
		txtCardText = new JTextArea(villainCard.getCardText());
		spane.setBorder(BorderFactory.createTitledBorder("Card Text"));
		spane.setBounds(5, 510, 250, 120);
		txtCardText.getDocument().addDocumentListener(new TextareaPropertiesDocumentListener(cardText));
		spane.setViewportView(txtCardText);
		getProperties().add(spane);
		
		updateButton = new JButton("Update Card");
		updateButton.setBounds(5, 640, 250, 25);
		updateButton.addActionListener(this);
		getProperties().add(updateButton);
		
		saveButton = new JButton("Save Card");
		saveButton.setBounds(5, 670, 250, 25);
		saveButton.addActionListener(this);
		getProperties().add(saveButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(5, 700, 250, 25);
		cancelButton.addActionListener(this);
		getProperties().add(cancelButton);
		
		getProperties().setPreferredSize(new Dimension(260, 740));
	}
	
	private void setupImagePane()
	{
		ImageIcon img = null;
		
		cardText = new JTextArea(villainCard.getCardText());
		cardText.setBounds(66, 668, 687 - 66, 870 - 660);
		cardText.setForeground(Color.BLACK);
		Font font = new Font("Comic Book", Font.PLAIN, 24);
		cardText.setFont(font);
		cardText.setOpaque(false);
		getImagePane().add(cardText);
		
		healthPoints = new JLabel(villainCard.getHealthPoints());
		font = new Font("SF Ferretopia", Font.PLAIN, 70);
		healthPoints.setFont(font);
		healthPoints.setHorizontalAlignment(JLabel.CENTER);
		healthPoints.setVerticalAlignment(JLabel.CENTER);
		healthPoints.setBounds(630, 35, 678 - 630, 111 - 35);
		healthPoints.setForeground(Color.BLACK);
		healthPoints.setVisible(false);
		getImagePane().add(healthPoints);
		
		cardClass = new JLabel(villainCard.getClasses());
		cardClass.setBounds(79, 606, 520 - 64, 654 - 606);
		cardClass.setForeground(Color.BLACK);
		font = new Font("Comic Book", Font.PLAIN, 30);
		cardClass.setFont(font);
		cardClass.setHorizontalAlignment(JLabel.CENTER);
		cardClass.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(cardClass);
		
		quoteText1 = new JLabel(villainCard.getQuoteString1());
		quoteText1.setBounds(84, 908, 530 - 84, 932 - 908);
		quoteText1.setForeground(Color.BLACK);
		font = new Font("Comic Book", Font.PLAIN, 20);
		quoteText1.setFont(font);
		quoteText1.setHorizontalAlignment(JLabel.CENTER);
		quoteText1.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(quoteText1);
		
		quoteText2 = new JLabel(villainCard.getQuoteString2());
		quoteText2.setBounds(84, 935, 530 - 84, 955 - 935);
		quoteText2.setForeground(Color.BLACK);
		font = new Font("Comic Book", Font.PLAIN, 20);
		quoteText2.setFont(font);
		quoteText2.setHorizontalAlignment(JLabel.CENTER);
		quoteText2.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(quoteText2);
		
		issueText = new JLabel(villainCard.getIssueString());
		issueText.setBounds(84, 966, 530 - 84, 985 - 966);
		issueText.setForeground(Color.BLACK);
		font = new Font("Comic Book", Font.PLAIN, 19);
		issueText.setFont(font);
		issueText.setHorizontalAlignment(JLabel.CENTER);
		issueText.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(issueText);
		
		name = new JLabel(villainCard.getName());
		name.setBounds(66, 42, 703 - 66, 104 - 42);
		name.setForeground(Color.WHITE);
		font = new Font("SF Ferretopia", Font.PLAIN, 50);
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
			JFileChooser chooser = new JFileChooser();
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
			JFileChooser chooser = new JFileChooser();
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
	}
}
