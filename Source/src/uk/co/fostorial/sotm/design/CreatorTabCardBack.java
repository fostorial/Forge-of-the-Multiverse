package uk.co.fostorial.sotm.design;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import uk.co.fostorial.sotm.CreatorFrame;
import uk.co.fostorial.sotm.structure.BackCard;

public class CreatorTabCardBack extends CreatorTab implements ActionListener {

	private static final long serialVersionUID = -3204658697675719327L;
	
	private JLabel cardborder;
	private JLabel portrait;
	
	private JButton portraitButton;
	private String portraitLocation;
	private JButton updateButton;
	private JButton saveButton;
	private JButton cancelButton;
	
	private BackCard backCard;
	
	public CreatorTabCardBack(CreatorFrame frame, BackCard c)
	{
		super(frame);
		super.setCard(c);
		backCard = c;
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
		
		portraitButton = new JButton("Set Portrait Image");
		portraitButton.setBounds(5, 5, 250, 25);
		portraitButton.addActionListener(this);
		getProperties().add(portraitButton);
		
		updateButton = new JButton("Update Card");
		updateButton.setBounds(5, 35, 250, 25);
		updateButton.addActionListener(this);
		getProperties().add(updateButton);
		
		saveButton = new JButton("Save Card");
		saveButton.setBounds(5, 65, 250, 25);
		saveButton.addActionListener(this);
		getProperties().add(saveButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(5, 95, 250, 25);
		cancelButton.addActionListener(this);
		getProperties().add(cancelButton);
		
		getProperties().setPreferredSize(new Dimension(260, 130));
	}
	
	private void setupImagePane()
	{
		ImageIcon img = null;
			
		img = new ImageIcon("images" + File.separator + "cardback" + File.separator + "cardborder.png");
		cardborder = new JLabel(img);
		cardborder.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		getImagePane().add(cardborder);
		setImageWidth(img.getIconWidth());
		setImageHeight(img.getIconHeight());
		
		img = new ImageIcon(backCard.getPortraitFile());
		img = new ImageIcon(getScaledImage(img.getImage(), getImageWidth(), getImageHeight()));
		portrait = new JLabel(img);
		portrait.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		getImagePane().add(portrait);
		
		getImagePane().setPreferredSize(new Dimension(img.getIconWidth(), img.getIconHeight()));
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
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
	
	private void updateCard()
	{
		backCard.setPortraitFile(portraitLocation);
	}
	
}
