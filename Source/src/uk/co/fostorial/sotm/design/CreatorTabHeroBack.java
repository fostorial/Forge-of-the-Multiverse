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
import uk.co.fostorial.sotm.structure.HeroBackCard;

public class CreatorTabHeroBack extends CreatorTab implements ActionListener {

	private static final long serialVersionUID = 8949042942748210954L;
	
	private JLabel cardborder;
	private JLabel portrait;
	private JLabel powerUnderlay;
	private JLabel powerText1;
	private JLabel powerText2;
	private JLabel powerText3;
	private JLabel powerText4;
	private JLabel powerText5;
	private JLabel powerText6;
	private JLabel powerLabel1;
	private JLabel powerLabel2;
	private JLabel powerLabel3;
	
	private JTextField txtPowerLine1;
	private JTextField txtPowerLine2;
	private JTextField txtPowerLine3;
	private JTextField txtPowerLine4;
	private JTextField txtPowerLine5;
	private JTextField txtPowerLine6;
	private JButton textBGColour;
	private JButton portraitButton;
	private String portraitPath;
	private JButton updateButton;
	private JButton saveButton;
	private JButton cancelButton;
	
	private JButton textFontButton;
	private JButton textFontColorButton;
	
	private HeroBackCard heroBackCard;
	
	public CreatorTabHeroBack(CreatorFrame frame, HeroBackCard c)
	{
		super(frame);
		super.setCard(c);
		heroBackCard = c;
		portraitPath = c.getPortraitFile();
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
		
		txtPowerLine1 = new JTextField(heroBackCard.getAbilityLine1());
		txtPowerLine1.setBorder(BorderFactory.createTitledBorder("Power Line 1"));
		txtPowerLine1.setBounds(5, 10, 250, 50);
		txtPowerLine1.setHorizontalAlignment(JTextField.CENTER);
		txtPowerLine1.getDocument().addDocumentListener(new PropertiesDocumentListener(powerText1));
		getProperties().add(txtPowerLine1);
		
		txtPowerLine2 = new JTextField(heroBackCard.getAbilityLine2());
		txtPowerLine2.setBorder(BorderFactory.createTitledBorder("Power Line 2"));
		txtPowerLine2.setBounds(5, txtPowerLine1.getBounds().y + txtPowerLine1.getBounds().height + 10, 250, 50);
		txtPowerLine2.setHorizontalAlignment(JTextField.CENTER);
		txtPowerLine2.getDocument().addDocumentListener(new PropertiesDocumentListener(powerText2));
		getProperties().add(txtPowerLine2);
		
		txtPowerLine3 = new JTextField(heroBackCard.getAbilityLine3());
		txtPowerLine3.setBorder(BorderFactory.createTitledBorder("Power Line 1"));
		txtPowerLine3.setBounds(5, txtPowerLine2.getBounds().y + txtPowerLine2.getBounds().height + 10, 250, 50);
		txtPowerLine3.setHorizontalAlignment(JTextField.CENTER);
		txtPowerLine3.getDocument().addDocumentListener(new PropertiesDocumentListener(powerText3));
		getProperties().add(txtPowerLine3);
		
		txtPowerLine4 = new JTextField(heroBackCard.getAbilityLine4());
		txtPowerLine4.setBorder(BorderFactory.createTitledBorder("Power Line 2"));
		txtPowerLine4.setBounds(5, txtPowerLine3.getBounds().y + txtPowerLine3.getBounds().height + 10, 250, 50);
		txtPowerLine4.setHorizontalAlignment(JTextField.CENTER);
		txtPowerLine4.getDocument().addDocumentListener(new PropertiesDocumentListener(powerText4));
		getProperties().add(txtPowerLine4);
		
		txtPowerLine5 = new JTextField(heroBackCard.getAbilityLine5());
		txtPowerLine5.setBorder(BorderFactory.createTitledBorder("Power Line 1"));
		txtPowerLine5.setBounds(5, txtPowerLine4.getBounds().y + txtPowerLine4.getBounds().height + 10, 250, 50);
		txtPowerLine5.setHorizontalAlignment(JTextField.CENTER);
		txtPowerLine5.getDocument().addDocumentListener(new PropertiesDocumentListener(powerText5));
		getProperties().add(txtPowerLine5);
		
		txtPowerLine6 = new JTextField(heroBackCard.getAbilityLine6());
		txtPowerLine6.setBorder(BorderFactory.createTitledBorder("Power Line 2"));
		txtPowerLine6.setBounds(5, txtPowerLine5.getBounds().y + txtPowerLine5.getBounds().height + 10, 250, 50);
		txtPowerLine6.setHorizontalAlignment(JTextField.CENTER);
		txtPowerLine6.getDocument().addDocumentListener(new PropertiesDocumentListener(powerText6));
		getProperties().add(txtPowerLine6);
		
		textFontButton = new JButton("Change Text Font");
		textFontButton.setBounds(5, txtPowerLine6.getBounds().y + txtPowerLine6.getBounds().height + 10, 250, 25);
		textFontButton.addActionListener(this);
		getProperties().add(textFontButton);
		
		textFontColorButton = new JButton("Change Text Color");
		textFontColorButton.setBounds(5, textFontButton.getBounds().y + textFontButton.getBounds().height + 10, 250, 25);
		textFontColorButton.addActionListener(this);
		getProperties().add(textFontColorButton);
		
		textBGColour = new JButton("Set Text Backing Colour");
		textBGColour.setBounds(5, textFontColorButton.getBounds().y + textFontColorButton.getBounds().height + 10, 250, 25);
		textBGColour.addActionListener(this);
		getProperties().add(textBGColour);
		
		portraitButton = new JButton("Set Portrait Image");
		portraitButton.setBounds(5, textBGColour.getBounds().y + textBGColour.getBounds().height + 10, 250, 25);
		portraitButton.addActionListener(this);
		getProperties().add(portraitButton);
		
		updateButton = new JButton("Update Card");
		updateButton.setBounds(5, portraitButton.getBounds().y + portraitButton.getBounds().height + 10, 250, 25);
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
		
		powerLabel1 = new JLabel("!");
		powerLabel1.setBounds(80, 780, 30, 22);
		powerLabel1.setForeground(heroBackCard.getTextFontColor());
		Font font = heroBackCard.getTextFont();
		powerLabel1.setFont(font);
		powerLabel1.setHorizontalAlignment(JLabel.LEFT);
		powerLabel1.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(powerLabel1);
		
		powerLabel2 = new JLabel("!");
		powerLabel2.setBounds(80, 855, 30, 25);
		powerLabel2.setForeground(heroBackCard.getTextFontColor());
		powerLabel2.setFont(font);
		powerLabel2.setHorizontalAlignment(JLabel.LEFT);
		powerLabel2.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(powerLabel2);
		
		powerLabel3 = new JLabel("!");
		powerLabel3.setBounds(80, 933, 30, 25);
		powerLabel3.setForeground(heroBackCard.getTextFontColor());
		powerLabel3.setFont(font);
		powerLabel3.setHorizontalAlignment(JLabel.LEFT);
		powerLabel3.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(powerLabel3);
		
		powerText1 = new JLabel(heroBackCard.getAbilityLine1());
		powerText1.setBounds(110, 770, 650 - 110, 22);
		powerText1.setForeground(heroBackCard.getTextFontColor());
		powerText1.setFont(font);
		powerText1.setHorizontalAlignment(JLabel.LEFT);
		powerText1.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(powerText1);
		
		powerText2 = new JLabel(heroBackCard.getAbilityLine2());
		powerText2.setBounds(110, 797, 650 - 110, 22);
		powerText2.setForeground(heroBackCard.getTextFontColor());
		powerText2.setFont(font);
		powerText2.setHorizontalAlignment(JLabel.LEFT);
		powerText2.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(powerText2);
		
		powerText3 = new JLabel(heroBackCard.getAbilityLine3());
		powerText3.setBounds(110, 845, 650 - 110, 25);
		powerText3.setForeground(heroBackCard.getTextFontColor());
		powerText3.setFont(font);
		powerText3.setHorizontalAlignment(JLabel.LEFT);
		powerText3.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(powerText3);
		
		powerText4 = new JLabel(heroBackCard.getAbilityLine4());
		powerText4.setBounds(110, 872, 650 - 110, 25);
		powerText4.setForeground(heroBackCard.getTextFontColor());
		powerText4.setFont(font);
		powerText4.setHorizontalAlignment(JLabel.LEFT);
		powerText4.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(powerText4);
		
		powerText5 = new JLabel(heroBackCard.getAbilityLine5());
		powerText5.setBounds(110, 923, 650 - 110, 25);
		powerText5.setForeground(heroBackCard.getTextFontColor());
		powerText5.setFont(font);
		powerText5.setHorizontalAlignment(JLabel.LEFT);
		powerText5.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(powerText5);
		
		powerText6 = new JLabel(heroBackCard.getAbilityLine6());
		powerText6.setBounds(110, 950, 650 - 110, 25);
		powerText6.setForeground(heroBackCard.getTextFontColor());
		powerText6.setFont(font);
		powerText6.setHorizontalAlignment(JLabel.LEFT);
		powerText6.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(powerText6);
				
		img = new ImageIcon("images" + File.separator + "blank.png");
		powerUnderlay = new JLabel();
		powerUnderlay.setBounds(64, 738, 680 - 64, 998 - 738);
		powerUnderlay.setOpaque(true);
		powerUnderlay.setBackground(heroBackCard.getTextColour());
		getImagePane().add(powerUnderlay);
		
		img = new ImageIcon("images" + File.separator + "heroback" + File.separator + "cardborder.png");
		cardborder = new JLabel(img);
		cardborder.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		getImagePane().add(cardborder);
		setImageWidth(img.getIconWidth());
		setImageHeight(img.getIconHeight());
		
		img = new ImageIcon(heroBackCard.getPortraitFile());
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
				Image image = getScaledImage(ii.getImage(), (int)getImagePane().getPreferredSize().getWidth(), (int)getImagePane().getPreferredSize().getHeight());
				ii = new ImageIcon(image);
				portrait.setIcon(ii);
				portrait.setBounds(0, 0, (int)getImagePane().getPreferredSize().getWidth(), (int)getImagePane().getPreferredSize().getHeight());
			}
		}
		
		if (e.getSource().equals(textFontButton))
		{
			JFontChooser chooser = new JFontChooser();
			chooser.setSelectedFont(powerText1.getFont());
			int outcome = chooser.showDialog(this);
			
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				powerText1.setFont(chooser.getSelectedFont());
				powerText2.setFont(chooser.getSelectedFont());
				powerText3.setFont(chooser.getSelectedFont());
				powerText4.setFont(chooser.getSelectedFont());
				powerText5.setFont(chooser.getSelectedFont());
				powerText6.setFont(chooser.getSelectedFont());
				powerLabel1.setFont(chooser.getSelectedFont());
				powerLabel2.setFont(chooser.getSelectedFont());
				powerLabel3.setFont(chooser.getSelectedFont());
			}
		}
		
		if (e.getSource().equals(textFontColorButton))
		{
			Color c = selectColor(powerText1.getForeground());
			if (c != null)
			{
				powerText1.setForeground(c);
				powerText2.setForeground(c);
				powerText3.setForeground(c);
				powerText4.setForeground(c);
				powerText5.setForeground(c);
				powerText6.setForeground(c);
				powerLabel1.setForeground(c);
				powerLabel2.setForeground(c);
				powerLabel3.setForeground(c);
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
		heroBackCard.setPortraitFile(portraitPath);
		heroBackCard.setTextColour(powerUnderlay.getBackground());
		heroBackCard.setAbilityLine1(txtPowerLine1.getText());
		heroBackCard.setAbilityLine2(txtPowerLine2.getText());
		heroBackCard.setAbilityLine3(txtPowerLine3.getText());
		heroBackCard.setAbilityLine4(txtPowerLine4.getText());
		heroBackCard.setAbilityLine5(txtPowerLine5.getText());
		heroBackCard.setAbilityLine6(txtPowerLine6.getText());
		heroBackCard.setTextFont(powerText1.getFont());
		heroBackCard.setTextFontColor(powerText1.getForeground());
	}
}
