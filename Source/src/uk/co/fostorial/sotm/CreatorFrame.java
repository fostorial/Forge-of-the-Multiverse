package uk.co.fostorial.sotm;

import java.awt.BorderLayout;
import java.awt.Window;
import java.lang.reflect.Method;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import uk.co.fostorial.sotm.characterfront.CreatorTabHeroBack;
import uk.co.fostorial.sotm.characterfront.CreatorTabHeroCard;
import uk.co.fostorial.sotm.characterfront.CreatorTabHeroFront;
import uk.co.fostorial.sotm.characterfront.CreatorTabVillainCard;
import uk.co.fostorial.sotm.characterfront.CreatorTabVillianFront;

public class CreatorFrame extends JFrame {
	
	final static public int FILE_NEW_HERO_FRONT = 1;
	final static public int FILE_NEW_HERO_BACK = 2;
	final static public int FILE_NEW_HERO_CARD = 3;
	final static public int FILE_NEW_VILLIAN_FRONT = 4;
	final static public int FILE_NEW_VILLIAN_CARD = 5;
	
	private CreatorMenuBar creatorMenuBar;
	
	private JTabbedPane tabbedPane;
	
	public CreatorFrame()
	{	
		setupFrame();
		this.setVisible(true);
	}

	private void setupFrame()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setTitle("SOTM Creator");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		enableOSXFullscreen(this);
		
		creatorMenuBar = new CreatorMenuBar(this);
		this.setJMenuBar(creatorMenuBar);
		
		tabbedPane = new JTabbedPane();
		this.add(tabbedPane, BorderLayout.CENTER);
	}
	
	public void newWindow(int type)
	{
		switch (type)
		{
		case FILE_NEW_HERO_FRONT:
			tabbedPane.addTab("New Hero Front", new CreatorTabHeroFront(this));
			tabbedPane.setSelectedIndex(tabbedPane.getComponentCount() - 1);
			break;
		case FILE_NEW_HERO_BACK:
			tabbedPane.addTab("New Hero Back", new CreatorTabHeroBack(this));
			tabbedPane.setSelectedIndex(tabbedPane.getComponentCount() - 1);
			break;
		case FILE_NEW_HERO_CARD:
			tabbedPane.addTab("New Hero Card", new CreatorTabHeroCard(this));
			tabbedPane.setSelectedIndex(tabbedPane.getComponentCount() - 1);
			break;
		case FILE_NEW_VILLIAN_FRONT:
			tabbedPane.addTab("New Villain Character", new CreatorTabVillianFront(this));
			tabbedPane.setSelectedIndex(tabbedPane.getComponentCount() - 1);
			break;
		case FILE_NEW_VILLIAN_CARD:
			tabbedPane.addTab("New Villian Card", new CreatorTabVillainCard(this));
			tabbedPane.setSelectedIndex(tabbedPane.getComponentCount() - 1);
			break;
		}
	}
	
	public void closeCurrentFrame()
	{
		if (tabbedPane.getComponentCount() > 0)
		{
			tabbedPane.remove(tabbedPane.getSelectedIndex());
		}
	}
	
	public void exportToJPEG()
	{
		if (tabbedPane.getComponentCount() > 0)
		{
			CreatorTab creatorTab = (CreatorTab) tabbedPane.getSelectedComponent();
			creatorTab.saveToJPG();
		}
	}
	
	public void exportToPNG()
	{
		if (tabbedPane.getComponentCount() > 0)
		{
			CreatorTab creatorTab = (CreatorTab) tabbedPane.getSelectedComponent();
			creatorTab.saveToPNG();
		}
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static void enableOSXFullscreen(Window window) {
		if (window != null)
		{
	    try {
	        Class util = Class.forName("com.apple.eawt.FullScreenUtilities");
	        Class params[] = new Class[]{Window.class, Boolean.TYPE};
	        Method method = util.getMethod("setWindowCanFullScreen", params);
	        method.invoke(util, window, true);
	    } catch (ClassNotFoundException e1) {
	    } catch (Exception e) {
	    }
		}
	}

	public CreatorMenuBar getCreatorMenuBar() {
		return creatorMenuBar;
	}

	public void setCreatorMenuBar(CreatorMenuBar creatorMenuBar) {
		this.creatorMenuBar = creatorMenuBar;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}
	
	
}
