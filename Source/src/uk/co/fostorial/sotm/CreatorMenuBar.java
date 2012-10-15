package uk.co.fostorial.sotm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class CreatorMenuBar extends JMenuBar implements ActionListener {

	final static public String FILE_NEW_HERO_FRONT = "F_HEROFRONT";
	final static public String FILE_NEW_HERO_BACK = "F_HEROBACK";
	final static public String FILE_NEW_HERO_CARD = "F_HEROCARD";
	final static public String FILE_NEW_VILLIAN_FRONT = "F_VILLIANFRONT";
	final static public String FILE_NEW_VILLIAN_CARD = "F_VILLIANCARD";

	
	private CreatorFrame frame;
	
	private JMenu file;
	private JMenuItem fileNewHeroFront;
	private JMenuItem fileNewHeroBack;
	private JMenuItem fileNewHeroCard;
	private JMenuItem fileNewVillianFront;
	private JMenuItem fileNewVillianCard;
	private JMenuItem fileCloseCurrentTab;
	private JMenuItem fileExportCurrentTabPNG;
	private JMenuItem fileExportCurrentTabJPG;
	private JMenuItem fileExit;
	
	public CreatorMenuBar(CreatorFrame frame)
	{
		this.setFrame(frame);
		setup();
	}
	
	private void setup()
	{
		file = new JMenu("File");
		
		fileNewHeroFront = new JMenuItem("New Hero Character Front");
		fileNewHeroFront.setActionCommand(FILE_NEW_HERO_FRONT);
		fileNewHeroFront.addActionListener(this);
		KeyStroke key = KeyStroke.getKeyStroke(
		        KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK);
		fileNewHeroFront.setAccelerator(key);
		file.add(fileNewHeroFront);
		
		fileNewHeroBack = new JMenuItem("New Hero Character Back");
		fileNewHeroBack.setActionCommand(FILE_NEW_HERO_BACK);
		fileNewHeroBack.addActionListener(this);
		key = KeyStroke.getKeyStroke(
		        KeyEvent.VK_H, KeyEvent.ALT_DOWN_MASK);
		fileNewHeroBack.setAccelerator(key);
		file.add(fileNewHeroBack);
		
		fileNewHeroCard = new JMenuItem("New Hero Character Card");
		fileNewHeroCard.setActionCommand(FILE_NEW_HERO_CARD);
		fileNewHeroCard.addActionListener(this);
		key = KeyStroke.getKeyStroke(
		        KeyEvent.VK_H, KeyEvent.SHIFT_DOWN_MASK);
		fileNewHeroCard.setAccelerator(key);
		file.add(fileNewHeroCard);
		
		fileNewVillianFront = new JMenuItem("New Villain Character");
		fileNewVillianFront.setActionCommand(FILE_NEW_VILLIAN_FRONT);
		fileNewVillianFront.addActionListener(this);
		key = KeyStroke.getKeyStroke(
		        KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK);
		fileNewVillianFront.setAccelerator(key);
		file.add(fileNewVillianFront);
		
		fileNewVillianCard = new JMenuItem("New Villian Card");
		fileNewVillianCard.setActionCommand(FILE_NEW_VILLIAN_CARD);
		fileNewVillianCard.addActionListener(this);
		key = KeyStroke.getKeyStroke(
		        KeyEvent.VK_V, KeyEvent.SHIFT_DOWN_MASK);
		fileNewVillianCard.setAccelerator(key);
		file.add(fileNewVillianCard);
		
		file.addSeparator();
		
		fileExportCurrentTabJPG = new JMenuItem("Export to JPEG");
		fileExportCurrentTabJPG.addActionListener(this);
		key = KeyStroke.getKeyStroke(
		        KeyEvent.VK_J, KeyEvent.CTRL_DOWN_MASK);
		fileExportCurrentTabJPG.setAccelerator(key);
		file.add(fileExportCurrentTabJPG);
		
		fileExportCurrentTabPNG = new JMenuItem("Export to PNG");
		fileExportCurrentTabPNG.addActionListener(this);
		key = KeyStroke.getKeyStroke(
		        KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK);
		fileExportCurrentTabPNG.setAccelerator(key);
		file.add(fileExportCurrentTabPNG);
		
		fileCloseCurrentTab = new JMenuItem("Close Selected Tab");
		fileCloseCurrentTab.addActionListener(this);
		key = KeyStroke.getKeyStroke(
		        KeyEvent.VK_C, KeyEvent.ALT_DOWN_MASK);
		fileCloseCurrentTab.setAccelerator(key);
		file.add(fileCloseCurrentTab);
		
		file.addSeparator();
		
		fileExit = new JMenuItem("Exit");
		fileExit.addActionListener(this);
		key = KeyStroke.getKeyStroke(
		        KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK);
		fileExit.setAccelerator(key);
		file.add(fileExit);
		
		this.add(file);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Exit"))
		{
			System.exit(0);
		}
		
		if (e.getActionCommand().equals("Export to JPEG"))
		{
			frame.exportToJPEG();
		}
		
		if (e.getActionCommand().equals("Export to PNG"))
		{
			frame.exportToPNG();
		}
		
		if (e.getActionCommand().equals("Close Selected Tab"))
		{
			frame.closeCurrentFrame();
		}
		
		if (e.getActionCommand().equals(FILE_NEW_HERO_FRONT))
		{
			frame.newWindow(CreatorFrame.FILE_NEW_HERO_FRONT);
		}
		
		if (e.getActionCommand().equals(FILE_NEW_HERO_BACK))
		{
			frame.newWindow(CreatorFrame.FILE_NEW_HERO_BACK);
		}
		
		if (e.getActionCommand().equals(FILE_NEW_HERO_CARD))
		{
			frame.newWindow(CreatorFrame.FILE_NEW_HERO_CARD);
		}
		
		if (e.getActionCommand().equals(FILE_NEW_VILLIAN_FRONT))
		{
			frame.newWindow(CreatorFrame.FILE_NEW_VILLIAN_FRONT);
		}
		
		if (e.getActionCommand().equals(FILE_NEW_VILLIAN_CARD))
		{
			frame.newWindow(CreatorFrame.FILE_NEW_VILLIAN_CARD);
		}
	}

	public CreatorFrame getFrame() {
		return frame;
	}

	public void setFrame(CreatorFrame frame) {
		this.frame = frame;
	}
	
}
