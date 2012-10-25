package uk.co.fostorial.sotm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import uk.co.fostorial.sotm.deck.DeckManager;
import uk.co.fostorial.sotm.design.CreatorTab;
import uk.co.fostorial.sotm.design.CreatorTabCardBack;
import uk.co.fostorial.sotm.design.CreatorTabHeroBack;
import uk.co.fostorial.sotm.design.CreatorTabHeroCard;
import uk.co.fostorial.sotm.design.CreatorTabHeroFront;
import uk.co.fostorial.sotm.design.CreatorTabVillainCard;
import uk.co.fostorial.sotm.design.CreatorTabVillainFront;
import uk.co.fostorial.sotm.structure.BackCard;
import uk.co.fostorial.sotm.structure.Card;
import uk.co.fostorial.sotm.structure.Deck;
import uk.co.fostorial.sotm.structure.HeroBackCard;
import uk.co.fostorial.sotm.structure.HeroCard;
import uk.co.fostorial.sotm.structure.HeroDeck;
import uk.co.fostorial.sotm.structure.HeroFrontCard;
import uk.co.fostorial.sotm.structure.VillainCard;
import uk.co.fostorial.sotm.structure.VillainDeck;
import uk.co.fostorial.sotm.structure.VillainFrontCard;

public class CreatorFrame extends JFrame implements ChangeListener {
	
	private static final long serialVersionUID = 8105592648557148065L;
	
	final static public int FILE_NEW_HERO_FRONT = 1;
	final static public int FILE_NEW_HERO_BACK = 2;
	final static public int FILE_NEW_HERO_CARD = 3;
	final static public int FILE_NEW_VILLIAN_FRONT = 4;
	final static public int FILE_NEW_VILLIAN_CARD = 5;
	final static public int FILE_NEW_CARD_BACK = 6;
	final static public int FILE_NEW_HERO_DECK = 7;
	final static public int FILE_OPEN_HERO_DECK = 8;
	final static public int FILE_NEW_VILLAIN_DECK = 9;
	
	private CreatorMenuBar creatorMenuBar;
	
	private JTabbedPane tabbedPane;
	
	private JFileChooser chooser = new JFileChooser();
	
	public CreatorFrame()
	{	
		setupFrame();
		this.setVisible(true);
	}

	private void setupFrame()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setTitle("Forge of the Multiverse");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		creatorMenuBar = new CreatorMenuBar(this);
		this.setJMenuBar(creatorMenuBar);
		
		tabbedPane = new JTabbedPane();
		tabbedPane.addChangeListener(this);
		this.add(tabbedPane, BorderLayout.CENTER);
		
		enableOSXFullscreen(this);
		creatorMenuBar.noPaneSelected();
	}
	
	public void newWindow(int type, Card card)
	{
		switch (type)
		{
		case FILE_NEW_HERO_FRONT:
			tabbedPane.addTab(card.getName(), new CreatorTabHeroFront(this, (HeroFrontCard)card));
			tabbedPane.setSelectedIndex(tabbedPane.getComponentCount() - 1);
			break;
		case FILE_NEW_HERO_BACK:
			tabbedPane.addTab(card.getName(), new CreatorTabHeroBack(this, (HeroBackCard)card));
			tabbedPane.setSelectedIndex(tabbedPane.getComponentCount() - 1);
			break;
		case FILE_NEW_HERO_CARD:
			tabbedPane.addTab(card.getName(), new CreatorTabHeroCard(this, (HeroCard)card));
			tabbedPane.setSelectedIndex(tabbedPane.getComponentCount() - 1);
			break;
		case FILE_NEW_VILLIAN_FRONT:
			tabbedPane.addTab("New Villain Character", new CreatorTabVillainFront(this, (VillainFrontCard)card));
			tabbedPane.setSelectedIndex(tabbedPane.getComponentCount() - 1);
			break;
		case FILE_NEW_VILLIAN_CARD:
			tabbedPane.addTab("New Villian Card", new CreatorTabVillainCard(this, (VillainCard)card));
			tabbedPane.setSelectedIndex(tabbedPane.getComponentCount() - 1);
			break;
		case FILE_NEW_CARD_BACK:
			tabbedPane.addTab(card.getName(), new CreatorTabCardBack(this, (BackCard)card));
			tabbedPane.setSelectedIndex(tabbedPane.getComponentCount() - 1);
			break;
		case FILE_NEW_HERO_DECK:
			tabbedPane.addTab("New Hero Deck", new DeckManager(DeckManager.HERO_MODE, null, this));
			tabbedPane.setSelectedIndex(tabbedPane.getComponentCount() - 1);
			((JSplitPane)tabbedPane.getSelectedComponent()).getLeftComponent().requestFocus();
			break;
		case FILE_NEW_VILLAIN_DECK:
			tabbedPane.addTab("New Villain Deck", new DeckManager(DeckManager.VILLAIN_MODE, null, this));
			tabbedPane.setSelectedIndex(tabbedPane.getComponentCount() - 1);
			((JSplitPane)tabbedPane.getSelectedComponent()).getLeftComponent().requestFocus();
			break;
		case FILE_OPEN_HERO_DECK:
			Deck deck = loadDeck();
			if (deck != null)
			{
				if (deck instanceof HeroDeck)
				{
					tabbedPane.addTab(deck.getName(), new DeckManager(DeckManager.HERO_MODE, deck, this));
				}
				if (deck instanceof VillainDeck)
				{
					tabbedPane.addTab(deck.getName(), new DeckManager(DeckManager.VILLAIN_MODE, deck, this));
				}
				tabbedPane.setSelectedIndex(tabbedPane.getComponentCount() - 1);
				//((JSplitPane)tabbedPane.getSelectedComponent()).getLeftComponent().requestFocus();
			}
			break;
		}
	}
	
	public Deck loadDeck()
	{
		Deck deck = null;
		try {
			JFileChooser chooser = getChooser();
			int outcome = chooser.showOpenDialog(this);
			
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				File f = chooser.getSelectedFile();
				
				Document document = Jsoup.parse(f, null);
				
				String deckType = "deck";
				Elements els = document.getElementsByTag("type");
				for (Element el : els)
				{
					deckType = el.text();
				}
				
				List<Card> cards = new ArrayList<Card>();
				
				els = document.getElementsByTag("herofrontcard");
				for (Element el : els)
				{
					Integer id = new Integer(findElement(el, "id"));
					String name = findElement(el, "name");
					HeroFrontCard card = new HeroFrontCard(name, id);
					card.setClasses(findElement(el, "classes"));
					card.setHealthPoints(findElement(el, "healthpoints"));
					card.setPortraitFile(findElement(el, "portrait"));
					card.setPowerName(findElement(el, "powername"));
					card.setPowerText(findElement(el, "powertext"));
					card.setNemesisPath(findElement(el, "nemesispath"));
					card.setNumberInDeck(new Integer(findElement(el, "numberindeck")));
					card.setColor(new Color(new Integer(findElement(el, "powercolor")).intValue()));
					
					if (findElement(el, "namefontcolor").isEmpty() == false)
						card.setNameFontColor(new Color(new Integer(findElement(el, "namefontcolor")).intValue()));
					
					if (findElement(el, "hpfontcolor").isEmpty() == false)
						card.setHpFontColor(new Color(new Integer(findElement(el, "hpfontcolor")).intValue()));
					
					if (findElement(el, "powernamefontcolor").isEmpty() == false)
						card.setPowerNameFontColor(new Color(new Integer(findElement(el, "powernamefontcolor")).intValue()));
					
					if (findElement(el, "powerfontcolor").isEmpty() == false)
						card.setPowerFontColor(new Color(new Integer(findElement(el, "powerfontcolor")).intValue()));
					
					if (findFontElement(el, "namefont") != null)
						card.setNameFont(findFontElement(el, "namefont"));
					
					if (findFontElement(el, "powernamefont") != null)
						card.setPowerNameFont(findFontElement(el, "powernamefont"));
					
					if (findFontElement(el, "hpfont") != null)
						card.setHpFont(findFontElement(el, "hpfont"));
					
					if (findFontElement(el, "powerfont") != null)
						card.setPowerFont(findFontElement(el, "powerfont"));
					
					cards.add(card);
				}
				
				els = document.getElementsByTag("herobackcard");
				for (Element el : els)
				{
					Integer id = new Integer(findElement(el, "id"));
					String name = findElement(el, "name");
					HeroBackCard card = new HeroBackCard(name, id);
					card.setClasses(findElement(el, "classes"));
					card.setHealthPoints(findElement(el, "healthpoints"));
					card.setPortraitFile(findElement(el, "portrait"));
					card.setNumberInDeck(new Integer(findElement(el, "numberindeck")));
					card.setTextColour(new Color(new Integer(findElement(el, "abilitycolor")).intValue()));
					card.setAbilityLine1(findElement(el, "abilityline1"));
					card.setAbilityLine2(findElement(el, "abilityline2"));
					card.setAbilityLine3(findElement(el, "abilityline3"));
					card.setAbilityLine4(findElement(el, "abilityline4"));
					card.setAbilityLine5(findElement(el, "abilityline5"));
					card.setAbilityLine6(findElement(el, "abilityline6"));
					
					if (findElement(el, "textfontcolor").isEmpty() == false)
						card.setTextFontColor(new Color(new Integer(findElement(el, "textfontcolor")).intValue()));
					
					if (findFontElement(el, "textfont") != null)
						card.setTextFont(findFontElement(el, "textfont"));
					
					cards.add(card);
				}
				
				els = document.getElementsByTag("villainfrontcard");
				for (Element el : els)
				{
					Integer id = new Integer(findElement(el, "id"));
					String name = findElement(el, "name");
					VillainFrontCard card = new VillainFrontCard(name, id);
					card.setClasses(findElement(el, "classes"));
					card.setHealthPoints(findElement(el, "healthpoints"));
					card.setPortraitFile(findElement(el, "portrait"));
					card.setDescription1(findElement(el, "description1"));
					card.setDescription2(findElement(el, "description2"));
					card.setNemesisPath(findElement(el, "nemesispath"));
					card.setNumberInDeck(new Integer(findElement(el, "numberindeck")));
					card.setColor(new Color(new Integer(findElement(el, "descriptioncolor")).intValue()));
					card.setClassColor(new Color(new Integer(findElement(el, "classcolor")).intValue()));
					
					if (findElement(el, "namefontcolor").isEmpty() == false)
						card.setNameFontColor(new Color(new Integer(findElement(el, "namefontcolor")).intValue()));
					
					if (findElement(el, "hpfontcolor").isEmpty() == false)
						card.setHpFontColor(new Color(new Integer(findElement(el, "hpfontcolor")).intValue()));
					
					if (findElement(el, "classfontcolor").isEmpty() == false)
						card.setClassFontColor(new Color(new Integer(findElement(el, "classfontcolor")).intValue()));
					
					if (findElement(el, "descriptionfontcolor").isEmpty() == false)
						card.setDescriptionFontColor(new Color(new Integer(findElement(el, "descriptionfontcolor")).intValue()));
					
					if (findFontElement(el, "namefont") != null)
						card.setNameFont(findFontElement(el, "namefont"));
					
					if (findFontElement(el, "classfont") != null)
						card.setClassFont(findFontElement(el, "classfont"));
					
					if (findFontElement(el, "hpfont") != null)
						card.setHpFont(findFontElement(el, "hpfont"));
					
					if (findFontElement(el, "descriptionfont") != null)
						card.setDescriptionFont(findFontElement(el, "descriptionfont"));
					
					cards.add(card);
				}
				
				els = document.getElementsByTag("backcard");
				for (Element el : els)
				{
					Integer id = new Integer(findElement(el, "id"));
					String name = findElement(el, "name");
					BackCard card = new BackCard(name, id);
					card.setClasses(findElement(el, "classes"));
					card.setHealthPoints(findElement(el, "healthpoints"));
					card.setPortraitFile(findElement(el, "portrait"));
					card.setNumberInDeck(new Integer(findElement(el, "numberindeck")));
					cards.add(card);
				}
				
				els = document.getElementsByTag("herocard");
				for (Element el : els)
				{
					Integer id = new Integer(findElement(el, "id"));
					String name = findElement(el, "name");
					HeroCard card = new HeroCard(name, id);
					card.setClasses(findElement(el, "classes"));
					card.setHealthPoints(findElement(el, "healthpoints"));
					card.setHealthPointsImage(findElement(el, "healthpointsimage"));
					card.setHealthPointsVisible(new Boolean(findElement(el, "healthpointsvisible")));
					card.setPortraitFile(findElement(el, "portrait"));
					card.setNumberInDeck(new Integer(findElement(el, "numberindeck")));
					card.setQuoteString1(findElement(el, "quotestring1"));
					card.setQuoteString2(findElement(el, "quotestring2"));
					card.setIssueString(findElement(el, "issuestring"));
					card.setNameColor(new Color(new Integer(findElement(el, "namecolour")).intValue()));
					card.setClassColor(new Color(new Integer(findElement(el, "classcolour")).intValue()));
					card.setCardText(findElement(el, "cardtext"));
					
					if (findElement(el, "namefontcolor").isEmpty() == false)
						card.setNameFontColor(new Color(new Integer(findElement(el, "namefontcolor")).intValue()));
					
					if (findElement(el, "hpfontcolor").isEmpty() == false)
						card.setHpFontColor(new Color(new Integer(findElement(el, "hpfontcolor")).intValue()));
					
					if (findElement(el, "classfontcolor").isEmpty() == false)
						card.setClassFontColor(new Color(new Integer(findElement(el, "classfontcolor")).intValue()));
					
					if (findElement(el, "descriptionfontcolor").isEmpty() == false)
						card.setDescriptionFontColor(new Color(new Integer(findElement(el, "descriptionfontcolor")).intValue()));
					
					if (findElement(el, "quotefontcolor").isEmpty() == false)
						card.setQuoteFontColor(new Color(new Integer(findElement(el, "quotefontcolor")).intValue()));
					
					if (findFontElement(el, "namefont") != null)
						card.setNameFont(findFontElement(el, "namefont"));
					
					if (findFontElement(el, "classfont") != null)
						card.setClassFont(findFontElement(el, "classfont"));
					
					if (findFontElement(el, "hpfont") != null)
						card.setHpFont(findFontElement(el, "hpfont"));
					
					if (findFontElement(el, "descriptionfont") != null)
						card.setDescriptionFont(findFontElement(el, "descriptionfont"));
					
					if (findFontElement(el, "quotefont") != null)
						card.setQuoteFont(findFontElement(el, "quotefont"));
					
					cards.add(card);
				}
				
				els = document.getElementsByTag("villaincard");
				for (Element el : els)
				{
					Integer id = new Integer(findElement(el, "id"));
					String name = findElement(el, "name");
					VillainCard card = new VillainCard(name, id);
					card.setClasses(findElement(el, "classes"));
					card.setHealthPoints(findElement(el, "healthpoints"));
					card.setHealthPointsImage(findElement(el, "healthpointsimage"));
					card.setHealthPointsVisible(new Boolean(findElement(el, "healthpointsvisible")));
					card.setPortraitFile(findElement(el, "portrait"));
					card.setNumberInDeck(new Integer(findElement(el, "numberindeck")));
					card.setQuoteString1(findElement(el, "quotestring1"));
					card.setQuoteString2(findElement(el, "quotestring2"));
					card.setIssueString(findElement(el, "issuestring"));
					card.setNameColor(new Color(new Integer(findElement(el, "namecolour")).intValue()));
					card.setClassColor(new Color(new Integer(findElement(el, "classcolour")).intValue()));
					card.setCardText(findElement(el, "cardtext"));
					
					if (findElement(el, "namefontcolor").isEmpty() == false)
						card.setNameFontColor(new Color(new Integer(findElement(el, "namefontcolor")).intValue()));
					
					if (findElement(el, "hpfontcolor").isEmpty() == false)
						card.setHpFontColor(new Color(new Integer(findElement(el, "hpfontcolor")).intValue()));
					
					if (findElement(el, "classfontcolor").isEmpty() == false)
						card.setClassFontColor(new Color(new Integer(findElement(el, "classfontcolor")).intValue()));
					
					if (findElement(el, "descriptionfontcolor").isEmpty() == false)
						card.setDescriptionFontColor(new Color(new Integer(findElement(el, "descriptionfontcolor")).intValue()));
					
					if (findElement(el, "quotefontcolor").isEmpty() == false)
						card.setQuoteFontColor(new Color(new Integer(findElement(el, "quotefontcolor")).intValue()));
					
					if (findFontElement(el, "namefont") != null)
						card.setNameFont(findFontElement(el, "namefont"));
					
					if (findFontElement(el, "classfont") != null)
						card.setClassFont(findFontElement(el, "classfont"));
					
					if (findFontElement(el, "hpfont") != null)
						card.setHpFont(findFontElement(el, "hpfont"));
					
					if (findFontElement(el, "descriptionfont") != null)
						card.setDescriptionFont(findFontElement(el, "descriptionfont"));
					
					if (findFontElement(el, "quotefont") != null)
						card.setQuoteFont(findFontElement(el, "quotefont"));
					
					cards.add(card);
				}
				
				if (deckType.equals("herodeck"))
				{
					deck = new HeroDeck(cards);
				}
				else if (deckType.equals("villaindeck"))
				{
					deck = new VillainDeck(cards);
				}
				else
				{
					deck = new Deck();
				}
				deck.setNextID(findHighestID(deck.getCards()).intValue());
				deck.setName(f.getName().replace(".xml", ""));
			}
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		return deck;
	}
	
	private String findElement(Element el, String attr)
	{
		String val = "";
		Elements subels = el.getElementsByTag(attr);
		for (Element sel : subels)
		{
			val = sel.text();
		}
		return val;
	}
	
	private Font findFontElement(Element el, String attr)
	{
		Font f = null;
		String val = "";
		Elements subels = el.getElementsByTag(attr);
		for (Element sel : subels)
		{
			val = sel.text();
		}
		
		try
		{
			String[] vals = val.split(";");
			f = new Font(vals[0], new Integer(vals[1]).intValue(), new Integer(vals[2]).intValue());
		}
		catch (Exception e)
		{
			f = null;
		}
		
		return f;
	}
	
	private Integer findHighestID(List<Card> cards)
	{
		Integer i = new Integer(0);
		for(Card c : cards)
		{
			if (c.getCardID().intValue() > i.intValue())
			{
				i = c.getCardID();
			}
		}
		return i;
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

	@Override
	public void stateChanged(ChangeEvent e) {
		JTabbedPane pane = (JTabbedPane)e.getSource();
		
		if (pane.getSelectedIndex() == -1)
		{
			creatorMenuBar.noPaneSelected();
		}
		else if (pane.getSelectedComponent() instanceof DeckManager)
		{
			creatorMenuBar.deckPaneSelected();
			pane.getSelectedComponent().repaint();
			DeckManager deckManager = (DeckManager)pane.getSelectedComponent();
			deckManager.loadPreview();
		}
		else if (pane.getSelectedComponent() instanceof CreatorTab)
		{
			creatorMenuBar.cardPaneSelected();
		}
	}

	public JFileChooser getChooser() {
		return chooser;
	}

	public void setChooser(JFileChooser chooser) {
		this.chooser = chooser;
	}
}
