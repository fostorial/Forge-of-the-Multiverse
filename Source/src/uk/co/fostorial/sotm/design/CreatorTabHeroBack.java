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
	
	private JTextField txtPowerLine1;
	private JTextField txtPowerLine2;
	private JTextField txtPowerLine3;
	private JTextField txtPowerLine4;
	private JTextField txtPowerLine5;
	private JTextField txtPowerLine6;
	private JButton textBGColour;
	private JButton portraitButton;
	private String portraitPath;
	private Color backingColour;
	private JButton updateButton;
	private JButton saveButton;
	private JButton cancelButton;
	
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
		txtPowerLine1.setBounds(5, 0, 250, 50);
		txtPowerLine1.setHorizontalAlignment(JTextField.CENTER);
		txtPowerLine1.getDocument().addDocumentListener(new PropertiesDocumentListener(powerText1));
		getProperties().add(txtPowerLine1);
		
		txtPowerLine2 = new JTextField(heroBackCard.getAbilityLine2());
		txtPowerLine2.setBorder(BorderFactory.createTitledBorder("Power Line 2"));
		txtPowerLine2.setBounds(5, 60, 250, 50);
		txtPowerLine2.setHorizontalAlignment(JTextField.CENTER);
		txtPowerLine2.getDocument().addDocumentListener(new PropertiesDocumentListener(powerText2));
		getProperties().add(txtPowerLine2);
		
		txtPowerLine3 = new JTextField(heroBackCard.getAbilityLine3());
		txtPowerLine3.setBorder(BorderFactory.createTitledBorder("Power Line 1"));
		txtPowerLine3.setBounds(5, 120, 250, 50);
		txtPowerLine3.setHorizontalAlignment(JTextField.CENTER);
		txtPowerLine3.getDocument().addDocumentListener(new PropertiesDocumentListener(powerText3));
		getProperties().add(txtPowerLine3);
		
		txtPowerLine4 = new JTextField(heroBackCard.getAbilityLine4());
		txtPowerLine4.setBorder(BorderFactory.createTitledBorder("Power Line 2"));
		txtPowerLine4.setBounds(5, 180, 250, 50);
		txtPowerLine4.setHorizontalAlignment(JTextField.CENTER);
		txtPowerLine4.getDocument().addDocumentListener(new PropertiesDocumentListener(powerText4));
		getProperties().add(txtPowerLine4);
		
		txtPowerLine5 = new JTextField(heroBackCard.getAbilityLine5());
		txtPowerLine5.setBorder(BorderFactory.createTitledBorder("Power Line 1"));
		txtPowerLine5.setBounds(5, 240, 250, 50);
		txtPowerLine5.setHorizontalAlignment(JTextField.CENTER);
		txtPowerLine5.getDocument().addDocumentListener(new PropertiesDocumentListener(powerText5));
		getProperties().add(txtPowerLine5);
		
		txtPowerLine6 = new JTextField(heroBackCard.getAbilityLine6());
		txtPowerLine6.setBorder(BorderFactory.createTitledBorder("Power Line 2"));
		txtPowerLine6.setBounds(5, 300, 250, 50);
		txtPowerLine6.setHorizontalAlignment(JTextField.CENTER);
		txtPowerLine6.getDocument().addDocumentListener(new PropertiesDocumentListener(powerText6));
		getProperties().add(txtPowerLine6);
		
		textBGColour = new JButton("Set Text Backing Colour");
		textBGColour.setBounds(5, 360, 250, 25);
		textBGColour.addActionListener(this);
		getProperties().add(textBGColour);
		
		portraitButton = new JButton("Set Portrait Image");
		portraitButton.setBounds(5, 390, 250, 25);
		portraitButton.addActionListener(this);
		getProperties().add(portraitButton);
		
		updateButton = new JButton("Update Card");
		updateButton.setBounds(5, 420, 250, 25);
		updateButton.addActionListener(this);
		getProperties().add(updateButton);
		
		saveButton = new JButton("Save Card");
		saveButton.setBounds(5, 450, 250, 25);
		saveButton.addActionListener(this);
		getProperties().add(saveButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(5, 480, 250, 25);
		cancelButton.addActionListener(this);
		getProperties().add(cancelButton);
				
		getProperties().setPreferredSize(new Dimension(260, 520));
	}
	
	private void setupImagePane()
	{
		ImageIcon img = null;
		
		JLabel label = new JLabel("!");
		label.setBounds(80, 780, 30, 22);
		label.setForeground(Color.BLACK);
		Font font = new Font("Comic Book", Font.PLAIN, 25);
		label.setFont(font);
		label.setHorizontalAlignment(JLabel.LEFT);
		label.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(label);
		
		label = new JLabel("!");
		label.setBounds(80, 855, 30, 25);
		label.setForeground(Color.BLACK);
		font = new Font("Comic Book", Font.PLAIN, 25);
		label.setFont(font);
		label.setHorizontalAlignment(JLabel.LEFT);
		label.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(label);
		
		label = new JLabel("!");
		label.setBounds(80, 933, 30, 25);
		label.setForeground(Color.BLACK);
		font = new Font("Comic Book", Font.PLAIN, 25);
		label.setFont(font);
		label.setHorizontalAlignment(JLabel.LEFT);
		label.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(label);
		
		powerText1 = new JLabel(heroBackCard.getAbilityLine1());
		powerText1.setBounds(110, 770, 580 - 110, 22);
		powerText1.setForeground(Color.BLACK);
		font = new Font("Comic Book", Font.PLAIN, 25);
		powerText1.setFont(font);
		powerText1.setHorizontalAlignment(JLabel.LEFT);
		powerText1.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(powerText1);
		
		powerText2 = new JLabel(heroBackCard.getAbilityLine2());
		powerText2.setBounds(110, 797, 580 - 110, 22);
		powerText2.setForeground(Color.BLACK);
		font = new Font("Comic Book", Font.PLAIN, 25);
		powerText2.setFont(font);
		powerText2.setHorizontalAlignment(JLabel.LEFT);
		powerText2.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(powerText2);
		
		powerText3 = new JLabel(heroBackCard.getAbilityLine3());
		powerText3.setBounds(110, 845, 580 - 110, 25);
		powerText3.setForeground(Color.BLACK);
		font = new Font("Comic Book", Font.PLAIN, 25);
		powerText3.setFont(font);
		powerText3.setHorizontalAlignment(JLabel.LEFT);
		powerText3.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(powerText3);
		
		powerText4 = new JLabel(heroBackCard.getAbilityLine4());
		powerText4.setBounds(110, 872, 580 - 110, 25);
		powerText4.setForeground(Color.BLACK);
		font = new Font("Comic Book", Font.PLAIN, 25);
		powerText4.setFont(font);
		powerText4.setHorizontalAlignment(JLabel.LEFT);
		powerText4.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(powerText4);
		
		powerText5 = new JLabel(heroBackCard.getAbilityLine5());
		powerText5.setBounds(110, 923, 580 - 110, 25);
		powerText5.setForeground(Color.BLACK);
		font = new Font("Comic Book", Font.PLAIN, 25);
		powerText5.setFont(font);
		powerText5.setHorizontalAlignment(JLabel.LEFT);
		powerText5.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(powerText5);
		
		powerText6 = new JLabel(heroBackCard.getAbilityLine6());
		powerText6.setBounds(110, 950, 580 - 110, 25);
		powerText6.setForeground(Color.BLACK);
		font = new Font("Comic Book", Font.PLAIN, 25);
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
				backingColour = c;
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
				Image image = getScaledImage(ii.getImage(), (int)getImagePane().getPreferredSize().getWidth(), (int)getImagePane().getPreferredSize().getHeight());
				ii = new ImageIcon(image);
				portrait.setIcon(ii);
				portrait.setBounds(0, 0, (int)getImagePane().getPreferredSize().getWidth(), (int)getImagePane().getPreferredSize().getHeight());
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
		heroBackCard.setTextColour(backingColour);
		heroBackCard.setAbilityLine1(txtPowerLine1.getText());
		heroBackCard.setAbilityLine2(txtPowerLine2.getText());
		heroBackCard.setAbilityLine3(txtPowerLine3.getText());
		heroBackCard.setAbilityLine4(txtPowerLine4.getText());
		heroBackCard.setAbilityLine5(txtPowerLine5.getText());
		heroBackCard.setAbilityLine6(txtPowerLine6.getText());
	}
}
