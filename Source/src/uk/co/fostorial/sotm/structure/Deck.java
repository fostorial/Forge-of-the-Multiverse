package uk.co.fostorial.sotm.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Deck {
	
	private String name;
	private List<Card> cards = new ArrayList<Card>();
	
	private int nextID = 0;

	public Deck() {

	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addCard(Card card)
	{
		boolean found = false;
		for(Card c : this.getCards())
		{
			if (c.getCardID().equals(card.getCardID()))
			{
				found = true;
			}
		}
		if (found == true)
		{
			// TODO validation error
		}
		
		card.setNumberInDeck(new Integer(1));
		cards.add(card);
	}
	
	public void removeCard(Card card)
	{
		cards.remove(card);
	}

	public int getNextID() {
		return nextID;
	}

	public void setNextID(int nextID) {
		this.nextID = nextID;
	}
	
	public Integer getNextIDInteger()
	{
		nextID++;
		return new Integer(nextID);
	}
	
	public String getXML()
	{
		String xml = "";
		xml += "<?xml version= \"1.0\"?>\n";
		xml += "<deck>\n";
		
		String type = "deck";
		if (this instanceof HeroDeck)
		{
			type = "herodeck";
		}
		if (this instanceof VillainDeck)
		{
			type = "villaindeck";
		}
		if (this instanceof EnvironmentDeck)
		{
			type = "environmentdeck";
		}
		xml += " <type>" + type + "</type>\n";
		
		for (Card c : cards)
		{
			xml += c.getXML();
		}
		
		xml += "</deck>\n";
		xml += "</xml>\n";
		
		return xml;
	}
	
	public List<DeckStatistic> getStats()
	{
		List<DeckStatistic> stats = new ArrayList<DeckStatistic>();
		
		Card front = null;
		Card back = null;
		
		int numberOfUniqueCards = 0;
		int numberOfCards = 0;
		int numberOfPowers = 0;
		int additionalHP = 0;
		List<String> numberOfClasses = new ArrayList<String>();
		HashMap<String, Integer> classCounts = new HashMap<String, Integer>();
		
		for (Card c : getCards())
		{
			if (c instanceof HeroFrontCard)
			{
				front = c;
			}
			
			if (c instanceof HeroBackCard)
			{
				back = c;
			}
			
			if (c instanceof VillainFrontCard && front == null)
			{
				front = c;
			}
			
			if (c instanceof VillainFrontCard && front != null)
			{
				back = c;
			}
			
			if (c instanceof VillainCard || c instanceof HeroCard || c instanceof EnvironmentCard)
			{
				numberOfUniqueCards++;
				numberOfCards += c.getNumberInDeck();
				additionalHP += c.getHealthPointsInt() * c.getNumberInDeck().intValue();
				
				if (c instanceof VillainCard && ((VillainCard)c).getCardText().toLowerCase().contains("power:"))
				{
					numberOfPowers++;
				}
				
				if (c instanceof HeroCard && ((HeroCard)c).getCardText().toLowerCase().contains("power:"))
				{
					numberOfPowers++;
				}
				
				/* Determine class stats */
				String classes = c.getClasses().replace(" ", "");
				
				if (classes.contains(",") == false && classes.equals("N/A") == false)
				{
					if (numberOfClasses.contains(classes) == false)
					{
						numberOfClasses.add(classes);
						classCounts.put(classes, c.getNumberInDeck());
					}
					else
					{
						Integer i = classCounts.get(classes);
						if (i == null)
						{
							i = new Integer(0);
						}
						Integer ni = new Integer(i.intValue() + c.getNumberInDeck().intValue());
						classCounts.put(classes, ni);
					}
				}
				
				if (classes.contains(","))
				{
					String[] split = classes.split(",");
					for (String s : split)
					{
						if (numberOfClasses.contains(s) == false)
						{
							numberOfClasses.add(s);
							classCounts.put(s, c.getNumberInDeck());
						}
						else
						{
							Integer i = classCounts.get(s);
							if (i == null)
							{
								i = new Integer(0);
							}
							Integer ni = new Integer(i.intValue() + c.getNumberInDeck().intValue());
							classCounts.put(s, ni);
						}
					}
				}
			}
		}
		
		if (back instanceof HeroBackCard)
		{
			numberOfPowers++;
		}
		
		stats.add(new DeckStatistic("Unique Cards", "" + numberOfUniqueCards));
		stats.add(new DeckStatistic("# of Cards", "" + numberOfCards));
		stats.add(new DeckStatistic("# HP", "" + additionalHP));
		
		if (this instanceof HeroDeck)
		{
			stats.add(new DeckStatistic("# of Powers", "" + numberOfPowers));
		}
		
		stats.add(new DeckStatistic("# of Classes", "" + numberOfClasses.size()));
		
		for (String s : numberOfClasses)
		{
			Integer i = classCounts.get(s);
			if (i == null)
			{
				i = new Integer(0);
			}
			stats.add(new DeckStatistic("# of " + s, "" + i.intValue()));
			stats.add(new DeckStatistic("% " + s, "" + (int)((i.doubleValue() / (double)numberOfCards) * 100d) + "%"));
		}
		
		return stats;
	}
}
