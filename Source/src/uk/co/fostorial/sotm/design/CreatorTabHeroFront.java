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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import uk.co.fostorial.sotm.CreatorFrame;
import uk.co.fostorial.sotm.structure.HeroFrontCard;

public class CreatorTabHeroFront extends CreatorTab implements ActionListener {

	private static final long serialVersionUID = 8188932334743823598L;
	
	private JLabel cardborder;
	private JLabel portrait;
	private JLabel publisher;
	private JLabel nemesisBack;
	private JLabel nemesisShine;
	private JLabel nemesisImage;
	private JLabel powerUnderlay;
	private JLabel healthPoints;
	private JLabel name;
	private JLabel powerName;
	private JLabel powerLabel;
	private JTextArea powerText;
	
	private JTextField txtHeroName;
	private JTextField txtHealthPoints;
	private JTextField txtPowerName;
	private JTextArea txtPowerLine;
	private JButton textBGColour;
	private JButton portraitButton; 
	private String portraitLocation;
	private JButton nemesisButton;
	private String nemesisPath;
	private JButton updateButton;
	private JButton saveButton;
	private JButton cancelButton;
	
	private HeroFrontCard heroFrontCard;
	
	public CreatorTabHeroFront(CreatorFrame frame, HeroFrontCard c)
	{
		super(frame);
		super.setCard(c);
		heroFrontCard = c;
		portraitLocation = heroFrontCard.getPortraitFile();
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
		
		txtHeroName = new JTextField(heroFrontCard.getName());
		txtHeroName.setBorder(BorderFactory.createTitledBorder("Hero Name"));
		txtHeroName.setBounds(5, 0, 250, 50);
		txtHeroName.setHorizontalAlignment(JTextField.CENTER);
		txtHeroName.getDocument().addDocumentListener(new PropertiesDocumentListener(name));
		getProperties().add(txtHeroName);
		
		txtHealthPoints = new JTextField(heroFrontCard.getHealthPoints());
		txtHealthPoints.setBorder(BorderFactory.createTitledBorder("Health Points"));
		txtHealthPoints.setBounds(5, 60, 250, 50);
		txtHealthPoints.setHorizontalAlignment(JTextField.CENTER);
		txtHealthPoints.getDocument().addDocumentListener(new PropertiesDocumentListener(healthPoints));
		getProperties().add(txtHealthPoints);
		
		txtPowerName = new JTextField(heroFrontCard.getPowerName());
		txtPowerName.setBorder(BorderFactory.createTitledBorder("Power Name"));
		txtPowerName.setBounds(5, 120, 250, 50);
		txtPowerName.setHorizontalAlignment(JTextField.CENTER);
		txtPowerName.getDocument().addDocumentListener(new PropertiesDocumentListener(powerName));
		getProperties().add(txtPowerName);
		
		txtPowerLine = new JTextArea(heroFrontCard.getPowerText());
		txtPowerLine.setBorder(BorderFactory.createTitledBorder("Power Text"));
		txtPowerLine.setBounds(5, 180, 250, 110);
		txtPowerLine.setLineWrap(true);
		txtPowerLine.setWrapStyleWord(true);
		txtPowerLine.getDocument().addDocumentListener(new TextareaPropertiesDocumentListener(powerText));
		getProperties().add(txtPowerLine);
		
		textBGColour = new JButton("Set Text Backing Colour");
		textBGColour.setBounds(5, 300, 250, 25);
		textBGColour.addActionListener(this);
		getProperties().add(textBGColour);
		
		portraitButton = new JButton("Set Portrait Image");
		portraitButton.setBounds(5, 330, 250, 25);
		portraitButton.addActionListener(this);
		getProperties().add(portraitButton);
		
		nemesisButton = new JButton("Set Nemesis Image");
		nemesisButton.setBounds(5, 360, 250, 25);
		nemesisButton.addActionListener(this);
		getProperties().add(nemesisButton);
		
		updateButton = new JButton("Update Card");
		updateButton.setBounds(5, 390, 250, 25);
		updateButton.addActionListener(this);
		getProperties().add(updateButton);
		
		saveButton = new JButton("Save Card");
		saveButton.setBounds(5, 420, 250, 25);
		saveButton.addActionListener(this);
		getProperties().add(saveButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(5, 450, 250, 25);
		cancelButton.addActionListener(this);
		getProperties().add(cancelButton);
		
		getProperties().setPreferredSize(new Dimension(260, 490));
	}
	
	private void setupImagePane()
	{
		ImageIcon img = null;
		
		powerName = new JLabel(heroFrontCard.getPowerName());
		powerName.setBounds(140, 898, 580 - 140, 930 - 898);
		powerName.setForeground(Color.BLACK);
		Font font = new Font("Comic Book", Font.PLAIN, 30);
		powerName.setFont(font);
		powerName.setHorizontalAlignment(JLabel.CENTER);
		powerName.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(powerName);
		
		powerLabel = new JLabel("Power:");
		powerLabel.setBounds(75, 931, 580 - 75, 960 - 931);
		powerLabel.setForeground(Color.BLACK);
		font = new Font("Comic Book", Font.BOLD, 23);
		powerLabel.setFont(font);
		powerLabel.setHorizontalAlignment(JLabel.LEFT);
		powerLabel.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(powerLabel);
		
		powerText = new JTextArea(heroFrontCard.getPowerText());
		powerText.setBounds(175, 931, 620 - 175, 993 - 931);
		powerText.setForeground(Color.BLACK);
		font = new Font("Comic Book", Font.PLAIN, 23);
		powerText.setFont(font);
		powerText.setOpaque(false);
		powerText.setLineWrap(true);
		powerText.setWrapStyleWord(true);
		powerText.setEditable(false);
		getImagePane().add(powerText);
		
		name = new JLabel(heroFrontCard.getName());
		name.setBounds(155, 90, 670 - 155, 200 - 90);
		name.setForeground(Color.WHITE);
		font = new Font("SF Ferretopia", Font.PLAIN, 100);
		name.setFont(font);
		name.setHorizontalAlignment(JLabel.CENTER);
		name.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(name);
		
		healthPoints = new JLabel(heroFrontCard.getHealthPoints());
		font = new Font("SF Ferretopia", Font.PLAIN, 90);
		healthPoints.setFont(font);
		healthPoints.setHorizontalAlignment(JLabel.CENTER);
		healthPoints.setVerticalAlignment(JLabel.CENTER);
		healthPoints.setBounds(45, 265, 160 - 45, 360 - 265);
		healthPoints.setForeground(Color.WHITE);
		getImagePane().add(healthPoints);
		
		img = new ImageIcon("images" + File.separator + "herofront" + File.separator + "comicbadge.png");
		publisher = new JLabel(img);
		publisher.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		getImagePane().add(publisher);
		
		img = new ImageIcon("images" + File.separator + "herofront" + File.separator + "nemesisshine.png");
		nemesisShine = new JLabel(img);
		nemesisShine.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		getImagePane().add(nemesisShine);
		
		if (heroFrontCard.getNemesisPath() == null)
		{
			img = new ImageIcon("images" + File.separator + "herofront" + File.separator + "nemesisshine.png");
		}
		else
		{
			img = new ImageIcon(heroFrontCard.getNemesisPath());
			img = new ImageIcon(getScaledImage(img.getImage(), 113, 113));
		}
		nemesisImage = new JLabel(img);
		nemesisImage.setBounds(584, 827, (584 + 113) - 584, (827 + 113) - 827);
		getImagePane().add(nemesisImage);
		
		img = new ImageIcon("images" + File.separator + "herofront" + File.separator + "nemesisback.png");
		nemesisBack = new JLabel(img);
		nemesisBack.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		getImagePane().add(nemesisBack);
		
		img = new ImageIcon("images" + File.separator + "blank.png");
		powerUnderlay = new JLabel();
		powerUnderlay.setBounds(64, 890, 680 - 64, 998 - 890);
		powerUnderlay.setOpaque(true);
		powerUnderlay.setBackground(heroFrontCard.getColor());
		getImagePane().add(powerUnderlay);
		
		img = new ImageIcon("images" + File.separator + "herofront" + File.separator + "cardborder.png");
		cardborder = new JLabel(img);
		cardborder.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		getImagePane().add(cardborder);
		setImageWidth(img.getIconWidth());
		setImageHeight(img.getIconHeight());
		
		img = new ImageIcon(heroFrontCard.getPortraitFile());
		img = new ImageIcon(getScaledImage(img.getImage(), getImageWidth(), getImageHeight()));
		portrait = new JLabel(img);
		portrait.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		getImagePane().add(portrait);
		
		getImagePane().setPreferredSize(new Dimension(img.getIconWidth(), img.getIconHeight()));
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(textBGColour))
		{
			Color c = selectColor(powerUnderlay.getBackground());
			if (c != null)
			{
				powerUnderlay.setBackground(c);
				heroFrontCard.setColor(c);
			}
		}
		
		if (e.getSource().equals(portraitButton))
		{
			JFileChooser chooser = new JFileChooser();
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
			JFileChooser chooser = new JFileChooser();
			int outcome = chooser.showOpenDialog(this);
			
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				nemesisPath = chooser.getSelectedFile().getAbsolutePath();
				ImageIcon ii = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
				Image image = getScaledImage(ii.getImage(), 113, 113);
				ii = new ImageIcon(image);
				nemesisImage.setIcon(ii);
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
		heroFrontCard.setName(txtHeroName.getText());
		heroFrontCard.setHealthPoints(txtHealthPoints.getText());
		heroFrontCard.setPortraitFile(portraitLocation);
		heroFrontCard.setPowerName(txtPowerName.getText());
		heroFrontCard.setNemesisPath(nemesisPath);
		heroFrontCard.setPowerText(txtPowerLine.getText());
	}
}
