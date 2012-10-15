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

public class CreatorTabHeroBack extends CreatorTab implements ActionListener {

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
	
	public CreatorTabHeroBack(CreatorFrame frame)
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
		
		txtPowerLine1 = new JTextField("Ability 1 Line 1");
		txtPowerLine1.setBorder(BorderFactory.createTitledBorder("Power Line 1"));
		txtPowerLine1.setBounds(5, 0, 250, 50);
		txtPowerLine1.setHorizontalAlignment(JTextField.CENTER);
		txtPowerLine1.getDocument().addDocumentListener(new PropertiesDocumentListener(powerText1));
		getProperties().add(txtPowerLine1);
		
		txtPowerLine2 = new JTextField("Ability 1 Line 2");
		txtPowerLine2.setBorder(BorderFactory.createTitledBorder("Power Line 2"));
		txtPowerLine2.setBounds(5, 60, 250, 50);
		txtPowerLine2.setHorizontalAlignment(JTextField.CENTER);
		txtPowerLine2.getDocument().addDocumentListener(new PropertiesDocumentListener(powerText2));
		getProperties().add(txtPowerLine2);
		
		txtPowerLine3 = new JTextField("Ability 2 Line 1");
		txtPowerLine3.setBorder(BorderFactory.createTitledBorder("Power Line 1"));
		txtPowerLine3.setBounds(5, 120, 250, 50);
		txtPowerLine3.setHorizontalAlignment(JTextField.CENTER);
		txtPowerLine3.getDocument().addDocumentListener(new PropertiesDocumentListener(powerText1));
		getProperties().add(txtPowerLine3);
		
		txtPowerLine4 = new JTextField("Ability 2 Line 2");
		txtPowerLine4.setBorder(BorderFactory.createTitledBorder("Power Line 2"));
		txtPowerLine4.setBounds(5, 180, 250, 50);
		txtPowerLine4.setHorizontalAlignment(JTextField.CENTER);
		txtPowerLine4.getDocument().addDocumentListener(new PropertiesDocumentListener(powerText2));
		getProperties().add(txtPowerLine4);
		
		txtPowerLine5 = new JTextField("Ability 3 Line 1");
		txtPowerLine5.setBorder(BorderFactory.createTitledBorder("Power Line 1"));
		txtPowerLine5.setBounds(5, 240, 250, 50);
		txtPowerLine5.setHorizontalAlignment(JTextField.CENTER);
		txtPowerLine5.getDocument().addDocumentListener(new PropertiesDocumentListener(powerText1));
		getProperties().add(txtPowerLine5);
		
		txtPowerLine6 = new JTextField("Ability 3 Line 2");
		txtPowerLine6.setBorder(BorderFactory.createTitledBorder("Power Line 2"));
		txtPowerLine6.setBounds(5, 300, 250, 50);
		txtPowerLine6.setHorizontalAlignment(JTextField.CENTER);
		txtPowerLine6.getDocument().addDocumentListener(new PropertiesDocumentListener(powerText2));
		getProperties().add(txtPowerLine6);
		
		textBGColour = new JButton("Set Text Backing Colour");
		textBGColour.setBounds(5, 360, 250, 50);
		textBGColour.addActionListener(this);
		getProperties().add(textBGColour);
		
		portraitButton = new JButton("Set Portrait Image");
		portraitButton.setBounds(5, 420, 250, 50);
		portraitButton.addActionListener(this);
		getProperties().add(portraitButton);
				
		getProperties().setPreferredSize(new Dimension(270, 490));
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
		
		powerText1 = new JLabel("Ability 1 Line 1");
		powerText1.setBounds(110, 770, 580 - 110, 22);
		powerText1.setForeground(Color.BLACK);
		font = new Font("Comic Book", Font.PLAIN, 25);
		powerText1.setFont(font);
		powerText1.setHorizontalAlignment(JLabel.LEFT);
		powerText1.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(powerText1);
		
		powerText2 = new JLabel("Ability 1 Line 2");
		powerText2.setBounds(110, 797, 580 - 110, 22);
		powerText2.setForeground(Color.BLACK);
		font = new Font("Comic Book", Font.PLAIN, 25);
		powerText2.setFont(font);
		powerText2.setHorizontalAlignment(JLabel.LEFT);
		powerText2.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(powerText2);
		
		powerText1 = new JLabel("Ability 2 Line 1");
		powerText1.setBounds(110, 845, 580 - 110, 25);
		powerText1.setForeground(Color.BLACK);
		font = new Font("Comic Book", Font.PLAIN, 25);
		powerText1.setFont(font);
		powerText1.setHorizontalAlignment(JLabel.LEFT);
		powerText1.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(powerText1);
		
		powerText2 = new JLabel("Ability 2 Line 2");
		powerText2.setBounds(110, 872, 580 - 110, 25);
		powerText2.setForeground(Color.BLACK);
		font = new Font("Comic Book", Font.PLAIN, 25);
		powerText2.setFont(font);
		powerText2.setHorizontalAlignment(JLabel.LEFT);
		powerText2.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(powerText2);
		
		powerText1 = new JLabel("Ability 3 Line 1");
		powerText1.setBounds(110, 923, 580 - 110, 25);
		powerText1.setForeground(Color.BLACK);
		font = new Font("Comic Book", Font.PLAIN, 25);
		powerText1.setFont(font);
		powerText1.setHorizontalAlignment(JLabel.LEFT);
		powerText1.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(powerText1);
		
		powerText2 = new JLabel("Ability 3 Line 2");
		powerText2.setBounds(110, 950, 580 - 110, 25);
		powerText2.setForeground(Color.BLACK);
		font = new Font("Comic Book", Font.PLAIN, 25);
		powerText2.setFont(font);
		powerText2.setHorizontalAlignment(JLabel.LEFT);
		powerText2.setVerticalAlignment(JLabel.CENTER);
		getImagePane().add(powerText2);
				
		img = new ImageIcon("images" + File.separator + "blank.png");
		powerUnderlay = new JLabel();
		powerUnderlay.setBounds(64, 738, 680 - 64, 998 - 738);
		powerUnderlay.setOpaque(true);
		powerUnderlay.setBackground(new Color(217,146,131));
		getImagePane().add(powerUnderlay);
		
		img = new ImageIcon("images" + File.separator + "herofront" + File.separator + "cardborder.png");
		cardborder = new JLabel(img);
		cardborder.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		getImagePane().add(cardborder);
		setImageWidth(img.getIconWidth());
		setImageHeight(img.getIconHeight());
		
		img = new ImageIcon("images" + File.separator + "herofront" + File.separator + "portrait.png");
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
