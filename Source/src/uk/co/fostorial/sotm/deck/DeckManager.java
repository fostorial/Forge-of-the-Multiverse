package uk.co.fostorial.sotm.deck;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uk.co.fostorial.sotm.CreatorFrame;
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

public class DeckManager extends JSplitPane implements ListSelectionListener {

	private static final long serialVersionUID = 7972443091809248703L;
	
	public final static int VILLAIN_MODE = 0;
	public final static int HERO_MODE = 1;
	public final static int ENVIRONMENT_MODE = 2;
	
	private JScrollPane cardTableScroll;
	private JTable cardTable;
	private JPanel properties;
	private JScrollPane propertiesScroll;
	private JSplitPane horizontalSplit;
	private JLabel preview;
	
	private int deckMode;
	private Deck deck;
	private Card selectedCard;
	
	private CreatorFrame frame;
	
	private String deckName;
	
	private CreatorTab creator;
	
	public DeckManager(int deckMode, Deck deck, CreatorFrame frame) {
		this.setDeckMode(deckMode);
		this.deck = deck;
		this.frame = frame;
		
		if (deck == null)
		{
			newDeck();
		}
		
		setup();
	}
	
	private void setup()
	{
		this.setAutoscrolls(true);
		
		horizontalSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		horizontalSplit.setAutoscrolls(true);
		horizontalSplit.setDividerLocation(300);
		
		ImageIcon ii = new ImageIcon();
		preview = new JLabel(ii);
		preview.setBounds(0, 0, 230, 300);
		horizontalSplit.setTopComponent(preview);
		
		propertiesScroll = new JScrollPane();
		propertiesScroll.setAutoscrolls(true);
		properties = new JPanel();
		propertiesScroll.setViewportView(properties);
		horizontalSplit.setBottomComponent(propertiesScroll);
		
		this.setRightComponent(horizontalSplit);
		
		
		cardTableScroll = new JScrollPane();
		cardTableScroll.setAutoscrolls(true);
		cardTable = new JTable(new DeckManagerTableModel(deck));
		cardTable.setFillsViewportHeight(true);
		//cardTable.setAutoCreateRowSorter(true);
		cardTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cardTable.getSelectionModel().addListSelectionListener(this);
		cardTableScroll.setViewportView(cardTable);
		cardTable.setRowSelectionInterval(0, 0);
		this.setLeftComponent(cardTableScroll);
		
		
		this.setDividerLocation(frame.getTabbedPane().getWidth() - 250);

	}

	public void newDeck()
	{	
		switch (deckMode)
		{
		case DeckManager.VILLAIN_MODE:
			deck = new VillainDeck();
			deck.setName("New Villain Deck");
			break;
		case DeckManager.ENVIRONMENT_MODE:
			deck = new Deck();
			deck.setName("New Environment Deck");
			break;
		case DeckManager.HERO_MODE:
			deck = new HeroDeck();
			deck.setName("New Hero Deck");
			break;
		}
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public int getDeckMode() {
		return deckMode;
	}

	public void setDeckMode(int deckMode) {
		this.deckMode = deckMode;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		Integer id = (Integer)cardTable.getValueAt(cardTable.getSelectedRow(), ((DeckManagerTableModel)cardTable.getModel()).ID);
		
		for(Card card : deck.getCards())
		{
			if (card.getCardID().equals(id))
			{
				selectedCard = card;
			}
		}
		
		loadPreview();
	}
	
	public void loadPreview()
	{
		if (selectedCard != null)
		{
			if (selectedCard instanceof HeroFrontCard)
			{
				creator = new CreatorTabHeroFront(frame, (HeroFrontCard)selectedCard);
				
				ImageIcon ii = new ImageIcon(creator.getImage(214, 300));
				preview.setIcon(ii);
			}
			
			if (selectedCard instanceof HeroBackCard)
			{
				creator = new CreatorTabHeroBack(frame, (HeroBackCard)selectedCard);
				
				ImageIcon ii = new ImageIcon(creator.getImage(214, 300));
				preview.setIcon(ii);
			}
			
			if (selectedCard instanceof BackCard)
			{
				creator = new CreatorTabCardBack(frame, (BackCard)selectedCard);
				
				ImageIcon ii = new ImageIcon(creator.getImage(214, 300));
				preview.setIcon(ii);
			}
			
			if (selectedCard instanceof HeroCard)
			{
				creator = new CreatorTabHeroCard(frame, (HeroCard)selectedCard);
				
				ImageIcon ii = new ImageIcon(creator.getImage(214, 300));
				preview.setIcon(ii);
			}
			
			if (selectedCard instanceof VillainFrontCard)
			{
				creator = new CreatorTabVillainFront(frame, (VillainFrontCard)selectedCard);
				
				ImageIcon ii = new ImageIcon(creator.getImage(214, 300));
				preview.setIcon(ii);
			}
			
			if (selectedCard instanceof VillainCard)
			{
				creator = new CreatorTabVillainCard(frame, (VillainCard)selectedCard);
				
				ImageIcon ii = new ImageIcon(creator.getImage(214, 300));
				preview.setIcon(ii);
			}
		}
	}
	
	public void addCardToDeck()
	{
		switch (deckMode)
		{
		case DeckManager.VILLAIN_MODE:
			deck.addCard(new VillainCard("Villain Card", new Integer(deck.getNextIDInteger())));
			break;
		case DeckManager.ENVIRONMENT_MODE:
			//TODO Environment cards
			break;
		case DeckManager.HERO_MODE:
			deck.addCard(new HeroCard("Hero Card", new Integer(deck.getNextIDInteger())));
			break;
		}
		cardTable.repaint();
	}
	
	public void increaseNumberInDeck()
	{
		if (selectedCard != null && (selectedCard instanceof HeroCard || selectedCard instanceof VillainCard))
		{
			int number = selectedCard.getNumberInDeck().intValue();
			number++;
			selectedCard.setNumberInDeck(new Integer(number));
			cardTable.repaint();
		}
		else
		{
			JOptionPane.showMessageDialog(frame, "There can be only one!", "Highlander Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void decreaseNumberInDeck()
	{
		if (selectedCard != null && (selectedCard instanceof HeroCard || selectedCard instanceof VillainCard))
		{
			int number = selectedCard.getNumberInDeck().intValue();
			if (number > 1)
			{
				number--;
				selectedCard.setNumberInDeck(new Integer(number));
				cardTable.repaint();
			}
		}
	}
	
	public void deleteCard()
	{
		if (selectedCard != null && (selectedCard instanceof HeroCard || selectedCard instanceof VillainCard))
		{
			int outcome = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this card?");
			
			if (outcome == JOptionPane.OK_OPTION)
			{
				deck.removeCard(selectedCard);
				cardTable.setRowSelectionInterval(0, 0);
				cardTable.repaint();
			}
		}
	}
	
	public void editCard()
	{
		if (selectedCard != null)
		{
			if (selectedCard instanceof HeroFrontCard)
			{
				frame.newWindow(CreatorFrame.FILE_NEW_HERO_FRONT, selectedCard);
			}
			
			if (selectedCard instanceof HeroBackCard)
			{
				frame.newWindow(CreatorFrame.FILE_NEW_HERO_BACK, selectedCard);
			}
			
			if (selectedCard instanceof HeroCard)
			{
				frame.newWindow(CreatorFrame.FILE_NEW_HERO_CARD, selectedCard);
			}
			
			if (selectedCard instanceof BackCard)
			{
				frame.newWindow(CreatorFrame.FILE_NEW_CARD_BACK, selectedCard);
			}
			
			if (selectedCard instanceof VillainFrontCard)
			{
				frame.newWindow(CreatorFrame.FILE_NEW_VILLIAN_FRONT, selectedCard);
			}
			
			if (selectedCard instanceof VillainCard)
			{
				frame.newWindow(CreatorFrame.FILE_NEW_VILLIAN_CARD, selectedCard);
			}
		}
	}
	
	public void saveDeck()
	{
		try {
			JFileChooser chooser = frame.getChooser();
			int outcome = chooser.showSaveDialog(this);
			
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				File f = chooser.getSelectedFile();
				FileWriter fstream = new FileWriter(f);
				BufferedWriter out = new BufferedWriter(fstream);
				
				out.write(deck.getXML());
				
				out.close();
				fstream.close();
			}
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}

	public String getDeckName() {
		return deckName;
	}

	public void setDeckName(String deckName) {
		this.deckName = deckName;
	}

	public CreatorTab getCreator() {
		return creator;
	}

	public void setCreator(CreatorTab creator) {
		this.creator = creator;
	}
	
	public void exportDeckIndividuallyPNG()
	{
		exportDeckIndividually("png");
	}
	
	public void exportDeckIndividuallyJPG()
	{
		exportDeckIndividually("jpg");
	}
	
	private void exportDeckIndividually(String type)
	{
		CreatorTab creator = null;
		
		JFileChooser chooser = frame.getChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int outcome = chooser.showSaveDialog(frame);
		
		if (outcome == JFileChooser.APPROVE_OPTION)
		{
			for (Card c : deck.getCards())
			{
				if (c != null)
				{
					if (c instanceof HeroFrontCard)
					{
						creator = new CreatorTabHeroFront(frame, (HeroFrontCard)c);
					}
					
					if (c instanceof HeroBackCard)
					{
						creator = new CreatorTabHeroBack(frame, (HeroBackCard)c);
					}
					
					if (c instanceof BackCard)
					{
						creator = new CreatorTabCardBack(frame, (BackCard)c);
					}
					
					if (c instanceof HeroCard)
					{
						creator = new CreatorTabHeroCard(frame, (HeroCard)c);
					}
					
					if (c instanceof VillainFrontCard)
					{
						creator = new CreatorTabVillainFront(frame, (VillainFrontCard)c);
					}
					
					if (c instanceof VillainCard)
					{
						creator = new CreatorTabVillainCard(frame, (VillainCard)c);
					}
					
					if (type.equals("png"))
					{
						creator.saveToPNG(chooser.getSelectedFile().getAbsolutePath());
					}
					
					if (type.equals("jpg"))
					{
						creator.saveToJPG(chooser.getSelectedFile().getAbsolutePath());
					}
				}
			}
		}
		
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	}
	
	public void exportDeckPagesPNG()
	{
		exportDeckPages("png");
	}
	
	public void exportDeckPagesJPG()
	{
		exportDeckPages("jpg");
	}
	
	private void exportDeckPages(String type)
	{
		CreatorTab creator = null;
		
		JFileChooser chooser = frame.getChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.validate();
		int outcome = chooser.showDialog(frame, "Select");
		
		if (outcome == JFileChooser.APPROVE_OPTION)
		{
			/* Variable used to split pages */
			int currentCard = 0;
			int currentPage = 0;
			
			/* sort into correct types */
			HeroFrontCard heroFront = null;
			HeroBackCard heroBack = null;
			List<VillainFrontCard> villainFronts = new ArrayList<VillainFrontCard>();
			List<Card> cards = new ArrayList<Card>();
			BackCard cardBack = null;
			VillainFrontCard villainFront = null;
			VillainFrontCard villainBack = null;
			
			for (Card c : deck.getCards())
			{
				if (c != null)
				{
					if (c instanceof HeroFrontCard)
					{
						heroFront = (HeroFrontCard)c;
					}
					else if (c instanceof HeroBackCard)
					{
						heroBack = (HeroBackCard)c;
					}
					else if (c instanceof BackCard)
					{
						cardBack = (BackCard)c;
					}
					else if (c instanceof HeroCard || c instanceof VillainCard)
					{
						cards.add(c);
					}
					else if (c instanceof VillainFrontCard)
					{
						villainFronts.add((VillainFrontCard)c);
					}
					
				}
			}
			
			if (villainFronts.size() == 2)
			{
				villainFront = villainFronts.get(0);
				villainBack = villainFronts.get(1);
			}
			
			/* Print cards 9 per page */
			BufferedImage outputImage = null;
			Graphics2D g = null;
			int cardWidth = 0;
			int cardHeight = 0;
			
			if (heroFront != null)
			{
				creator = new CreatorTabHeroFront(frame, heroFront);
				BufferedImage image = creator.getImage();
		        
				int imageType = BufferedImage.TYPE_INT_RGB;
		        outputImage = new BufferedImage((image.getWidth() * 3) + (4 * 20), (image.getHeight() * 3) + (4 * 20), imageType);
		        g = outputImage.createGraphics();
		        g.setBackground(Color.white);
				g.setColor(Color.white);
				g.fillRect(0, 0, outputImage.getWidth(), outputImage.getHeight());
				
		        g.drawImage(image, 20, 20, null);
		        
		        cardWidth = image.getWidth();
		        cardHeight = image.getHeight();
			}
			
			if (villainFront != null)
			{
				creator = new CreatorTabVillainFront(frame, villainFront);
				BufferedImage image = creator.getImage();
		        
				int imageType = BufferedImage.TYPE_INT_RGB;
		        outputImage = new BufferedImage((image.getWidth() * 3) + (4 * 20), (image.getHeight() * 3) + (4 * 20), imageType);
		        g = outputImage.createGraphics();
		        g.setBackground(Color.white);
				g.setColor(Color.white);
				g.fillRect(0, 0, outputImage.getWidth(), outputImage.getHeight());
		        
		        g.drawImage(image, 20, 20, null);
		        
		        cardWidth = image.getWidth();
		        cardHeight = image.getHeight();
			}
				
			int x = 20 + cardWidth + 20;
			int y = 20;
			for (Card c : cards)
			{
				if (c != null)
				{
					if (c instanceof HeroFrontCard)
					{
						creator = new CreatorTabHeroFront(frame, (HeroFrontCard)c);
					}
					
					if (c instanceof HeroBackCard)
					{
						creator = new CreatorTabHeroBack(frame, (HeroBackCard)c);
					}
					
					if (c instanceof BackCard)
					{
						creator = new CreatorTabCardBack(frame, (BackCard)c);
					}
					
					if (c instanceof HeroCard)
					{
						creator = new CreatorTabHeroCard(frame, (HeroCard)c);
					}
					
					if (c instanceof VillainFrontCard)
					{
						creator = new CreatorTabVillainFront(frame, (VillainFrontCard)c);
					}
					
					if (c instanceof VillainCard)
					{
						creator = new CreatorTabVillainCard(frame, (VillainCard)c);
					}
				}
				g.drawImage(creator.getImage(), x, y, null);
				x += cardWidth + 20;
				
				if (x >= (int)((3 * cardWidth) + (4 * 20)))
				{
					x = 20;
					y += cardHeight + 20; 
				}
				
				currentCard++;
				
				/* Save and reset page */
				if (currentCard >= 9 || (currentCard + (currentPage * 9)) >= cards.size())
				{
					try
					{
						File f = new File(chooser.getSelectedFile().getAbsolutePath() + File.separator + deck.getName() + (int)(currentPage + 1) + "." + type);
						ImageIO.write(outputImage, type, f);
						
						x = 20;
						y = 20;
						
						currentPage++;
						currentCard = 0;
						
						g.fillRect(0, 0, outputImage.getWidth(), outputImage.getHeight());
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
			
			/* Write card backs */
			int filledPages = currentPage;
			
			x = 20 + cardWidth + 20 + cardWidth + 20;
			y = 20;
			
			if (heroBack != null)
			{
				creator = new CreatorTabHeroBack(frame, heroBack);
				BufferedImage image = creator.getImage();
		        
				g.fillRect(0, 0, outputImage.getWidth(), outputImage.getHeight());
				
		        g.drawImage(image, x, y, null);
			}
			
			if (villainBack != null)
			{
				creator = new CreatorTabVillainFront(frame, villainBack);
				BufferedImage image = creator.getImage();
		        
				g.fillRect(0, 0, outputImage.getWidth(), outputImage.getHeight());
		        
		        g.drawImage(image, x, y, null);
			}
				
			currentCard = 0;
			currentPage = 0;
			
			creator = new CreatorTabCardBack(frame, cardBack);
			BufferedImage cardBackImage = creator.getImage();
			
			for (int i = 0; i < cards.size(); i++)
			{
				
				if (currentCard == 0 && currentPage == 0)
				{
					x = 20 + cardWidth + 20;
				}
				
				if (currentCard == 1 && currentPage == 0)
				{
					x = 20;
				}
				
				if (currentCard == 2 && currentPage == 0)
				{
					x = 20;
					y += cardHeight + 20;
				}
				
				g.drawImage(cardBackImage, x, y, null);
				x += cardWidth + 20;
				
				if (x >= (int)((3 * cardWidth) + (4 * 20)))
				{
					x = 20;
					y += cardHeight + 20; 
				}
				
				currentCard++;
				
				/* Save and reset page */
				if (currentCard >= 9 || (currentCard + (currentPage * 9)) >= cards.size())
				{
					try
					{
						File f = new File(chooser.getSelectedFile().getAbsolutePath() + File.separator + deck.getName() + (int)(currentPage + 1 + filledPages) + "." + type);
						ImageIO.write(outputImage, type, f);
						
						x = 20;
						y = 20;
						
						currentPage++;
						currentCard = 0;
						
						g.fillRect(0, 0, outputImage.getWidth(), outputImage.getHeight());
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.validate();
	}
	
	public void exportToText()
	{
		try {
			JFileChooser chooser = frame.getChooser();
			int outcome = chooser.showSaveDialog(this);
			
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				File f = chooser.getSelectedFile();
				if (f.getAbsolutePath().toLowerCase().endsWith(".txt") == false)
				{
					f = new File(f.getAbsolutePath() + ".txt");
				}
				
				FileWriter fstream = new FileWriter(f);
				BufferedWriter out = new BufferedWriter(fstream);
				  
				if (deck instanceof HeroDeck)
				{
					HeroFrontCard front = null;
					HeroBackCard back = null;
					List<HeroCard> cards = new ArrayList<HeroCard>();
					
					for (Card c : deck.getCards())
					{
						if (c instanceof HeroFrontCard)
						{
							front = (HeroFrontCard)c;
						}
						else if (c instanceof HeroBackCard)
						{
							back = (HeroBackCard)c;
						}
						else if (c instanceof HeroCard)
						{
							cards.add((HeroCard)c);
						}
					}
					
					out.write("Name: " + front.getName() + "\n");
					out.write("Health Points: " + front.getHealthPoints() + "\n");
					out.write("Power: " + front.getPowerName() + " - " + front.getPowerText() + "\n");
					out.write("Incapacitated Power 1: " + back.getAbilityLine1() + " " + back.getAbilityLine2() + "\n");
					out.write("Incapacitated Power 2: " + back.getAbilityLine3() + " " + back.getAbilityLine4() + "\n");
					out.write("Incapacitated Power 3: " + back.getAbilityLine5() + " " + back.getAbilityLine6() + "\n\n");
					
					out.write("Cards" + "\n\n");
					for (HeroCard c : cards)
					{
						out.write("Name: " + c.getName() + "\n");
						out.write("Health Points: " + c.getHealthPoints() + "\n");
						out.write("Classes: " + c.getClasses() + "\n");
						out.write("Card Text: " + c.getCardText() + "\n");
						out.write("Quote: " + c.getQuoteString1() + " " + c.getQuoteString2() + " " + c.getIssueString() + "\n");
						out.write("Number in Deck: " + c.getNumberInDeck() + "\n\n");
					}
				}
				
				if (deck instanceof VillainDeck)
				{
					VillainFrontCard front = null;
					VillainFrontCard back = null;
					List<VillainCard> cards = new ArrayList<VillainCard>();
					
					for (Card c : deck.getCards())
					{
						if (c instanceof VillainFrontCard && front == null)
						{
							front = (VillainFrontCard)c;
						}
						else if (c instanceof VillainFrontCard)
						{
							back = (VillainFrontCard)c;
						}
						else if (c instanceof VillainCard)
						{
							cards.add((VillainCard)c);
						}
					}
					
					out.write("Name: " + front.getName() + "\n");
					out.write("Side 1: Health Points: " + front.getHealthPoints() + "\n");
					out.write("Side 1: Description: " + front.getDescription1() + " " + front.getDescription2() + "\n");
					out.write("Side 2: Health Points: " + back.getHealthPoints() + "\n");
					out.write("Side 2: Description: " + back.getDescription1() + " " + back.getDescription2() + "\n");
					
					out.write("Cards" + "\n\n");
					for (VillainCard c : cards)
					{
						out.write("Name: " + c.getName() + "\n");
						out.write("Health Points: " + c.getHealthPoints() + "\n");
						out.write("Classes: " + c.getClasses() + "\n");
						out.write("Card Text: " + c.getCardText() + "\n");
						out.write("Quote: " + c.getQuoteString1() + " " + c.getQuoteString2() + " " + c.getIssueString() + "\n");
						out.write("Number in Deck: " + c.getNumberInDeck() + "\n\n");
					}
				}
				
				out.close();
			}
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
}
