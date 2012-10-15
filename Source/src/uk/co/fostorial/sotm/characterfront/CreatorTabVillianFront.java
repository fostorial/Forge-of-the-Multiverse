package uk.co.fostorial.sotm.characterfront;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

import uk.co.fostorial.sotm.CreatorFrame;
import uk.co.fostorial.sotm.CreatorTab;

public class CreatorTabVillianFront extends CreatorTab implements ActionListener {

	private JLabel cardborder;
	private JLabel portrait;
	private JLabel nemesisBack;
	private JLabel nemesisShine;
	private JLabel nemesisImage;
	private JLabel powerUnderlay;
	private JLabel nameUnderlay;
	private JLabel healthPoints;
	private JLabel name;
	private JLabel powerName;
	private JLabel powerText1;
	private JLabel powerText2;
	
	private JTextField txtHeroName;
	private JTextField txtHealthPoints;
	private JTextField txtPowerName;
	private JTextField txtPowerLine1;
	private JTextField txtPowerLine2;
	private JButton textBGColour;
	private JButton nameBGColour;
	private JButton portraitButton;
	private JButton nemesisButton;
	
	public CreatorTabVillianFront(CreatorFrame frame)
	{
		super(frame);
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
		
		txtHeroName = new JTextField("Name");
		txtHeroName.setBorder(BorderFactory.createTitledBorder("Hero Name"));
		txtHeroName.setBounds(5, 0, 250, 50);
		txtHeroName.setHorizontalAlignment(JTextField.CENTER);
		txtHeroName.getDocument().addDocumentListener(new PropertiesDocumentListener(name));
		getProperties().add(txtHeroName);
		
		txtHealthPoints = new JTextField("80");
		txtHealthPoints.setBorder(BorderFactory.createTitledBorder("Health Points"));
		txtHealthPoints.setBounds(5, 60, 250, 50);
		txtHealthPoints.setHorizontalAlignment(JTextField.CENTER);
		txtHealthPoints.getDocument().addDocumentListener(new PropertiesDocumentListener(healthPoints));
		getProperties().add(txtHealthPoints);
		
		txtPowerName = new JTextField("Villain Class");
		txtPowerName.setBorder(BorderFactory.createTitledBorder("Power Name"));
		txtPowerName.setBounds(5, 120, 250, 50);
		txtPowerName.setHorizontalAlignment(JTextField.CENTER);
		txtPowerName.getDocument().addDocumentListener(new PropertiesDocumentListener(powerName));
		getProperties().add(txtPowerName);
		
		txtPowerLine1 = new JTextField("Description Line 1");
		txtPowerLine1.setBorder(BorderFactory.createTitledBorder("Power Line 1"));
		txtPowerLine1.setBounds(5, 180, 250, 50);
		txtPowerLine1.setHorizontalAlignment(JTextField.CENTER);
		txtPowerLine1.getDocument().addDocumentListener(new PropertiesDocumentListener(powerText1));
		getProperties().add(txtPowerLine1);
		
		txtPowerLine2 = new JTextField("Description Line 2");
		txtPowerLine2.setBorder(BorderFactory.createTitledBorder("Power Line 2"));
		txtPowerLine2.setBounds(5, 240, 250, 50);
		txtPowerLine2.setHorizontalAlignment(JTextField.CENTER);
		txtPowerLine2.getDocument().addDocumentListener(new PropertiesDocumentListener(powerText2));
		getProperties().add(txtPowerLine2);
		
		textBGColour = new JButton("Set Class Backing Colour");
		textBGColour.setBounds(5, 360, 250, 50);
		textBGColour.addActionListener(this);
		getProperties().add(textBGColour);
		
		nameBGColour = new JButton("Set Name Backing Colour");
		nameBGColour.setBounds(5, 300, 250, 50);
		nameBGColour.addActionListener(this);
		getProperties().add(nameBGColour);
		
		portraitButton = new JButton("Set Portrait Image");
		portraitButton.setBounds(5, 420, 250, 50);
		portraitButton.addActionListener(this);
		getProperties().add(portraitButton);
		
		nemesisButton = new JButton("Set Nemesis Image");
		nemesisButton.setBounds(5, 480, 250, 50);
		nemesisButton.addActionListener(this);
		getProperties().add(nemesisButton);
		
		getProperties().setPreferredSize(new Dimension(270, 560));
	}
	
	private void setupImagePane()
	{
		ImageIcon img = null;
		
		powerName = new JLabel("Villain Class");
		powerName.setBounds(132, 854, 478 - 132, 904 - 854);
		powerName.setForeground(Color.BLACK);
		Font font = new Font("Comic Book", Font.PLAIN, 30);
		powerName.setFont(font);
		powerName.setHorizontalAlignment(JLabel.CENTER);
		powerName.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(powerName);
		
		powerText1 = new JLabel("Description Line 1");
		powerText1.setBounds(64, 921, 680 - 64, 953 - 921);
		powerText1.setForeground(Color.BLACK);
		font = new Font("Comic Book", Font.PLAIN, 30);
		powerText1.setFont(font);
		powerText1.setHorizontalAlignment(JLabel.CENTER);
		powerText1.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(powerText1);
		
		powerText2 = new JLabel("Description Line 2");
		powerText2.setBounds(64, 955, 680 - 64, 987 - 955);
		powerText2.setForeground(Color.BLACK);
		font = new Font("Comic Book", Font.PLAIN, 30);
		powerText2.setFont(font);
		powerText2.setHorizontalAlignment(JLabel.CENTER);
		powerText2.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(powerText2);
		
		name = new JLabel("Name");
		name.setBounds(70, 40, 600 - 70, 160 - 40);
		name.setForeground(Color.WHITE);
		font = new Font("SF Ferretopia", Font.PLAIN, 110);
		name.setFont(font);
		name.setHorizontalAlignment(JLabel.LEFT);
		name.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(name);
		
		healthPoints = new JLabel("80");
		font = new Font("SF Ferretopia", Font.PLAIN, 110);
		healthPoints.setFont(font);
		healthPoints.setHorizontalAlignment(JLabel.RIGHT);
		healthPoints.setVerticalAlignment(JLabel.CENTER);
		healthPoints.setBounds(540, 40, 685 - 540, 160 - 40);
		healthPoints.setForeground(Color.WHITE);
		getImagePane().add(healthPoints);
		
		img = new ImageIcon("images" + File.separator + "villainfront" + File.separator + "nemesisshine.png");
		nemesisShine = new JLabel(img);
		nemesisShine.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		getImagePane().add(nemesisShine);
		
		img = new ImageIcon("images" + File.separator + "villainfront" + File.separator + "nemesisshine.png");
		nemesisImage = new JLabel(img);
		nemesisImage.setBounds(584, 827, (584 + 113) - 584, (827 + 113) - 827);
		getImagePane().add(nemesisImage);
		
		img = new ImageIcon("images" + File.separator + "villainfront" + File.separator + "nemesisback.png");
		nemesisBack = new JLabel(img);
		nemesisBack.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		getImagePane().add(nemesisBack);
		
		img = new ImageIcon("images" + File.separator + "blank.png");
		nameUnderlay = new JLabel();
		nameUnderlay.setBounds(132, 854, 478 - 132, 904 - 854);
		nameUnderlay.setOpaque(true);
		nameUnderlay.setBackground(new Color(163, 178, 207));
		getImagePane().add(nameUnderlay);
		
		img = new ImageIcon("images" + File.separator + "blank.png");
		powerUnderlay = new JLabel();
		powerUnderlay.setBounds(104, 890, 640 - 104, 998 - 890);
		powerUnderlay.setOpaque(true);
		powerUnderlay.setBackground(new Color(217, 146, 131));
		getImagePane().add(powerUnderlay);
		
		img = new ImageIcon("images" + File.separator + "villainfront" + File.separator + "cardborder.png");
		cardborder = new JLabel(img);
		cardborder.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		getImagePane().add(cardborder);
		setImageWidth(img.getIconWidth());
		setImageHeight(img.getIconHeight());
		
		img = new ImageIcon("images" + File.separator + "villainfront" + File.separator + "portrait.png");
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
		
		if (e.getSource().equals(nameBGColour))
		{
			Color c = selectColor(nameUnderlay.getBackground());
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
				ImageIcon ii = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
				Image image = getScaledImage(ii.getImage(), 113, 113);
				ii = new ImageIcon(image);
				nemesisImage.setIcon(ii);
			}
		}
	}
	
	private Color selectColor(Color colour)
	{
		Color c = colour;
		JColorChooser chooser = new JColorChooser();
		c = chooser.showDialog(this, "Select Colour...", c);
		return c;
	}
	
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();
	    return resizedImg;
	}
}
