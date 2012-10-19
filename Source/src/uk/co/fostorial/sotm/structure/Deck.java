package uk.co.fostorial.sotm.structure;

import java.util.ArrayList;
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
		xml += " <type>" + type + "</type>\n";
		
		for (Card c : cards)
		{
			xml += c.getXML();
		}
		
		xml += "</deck>\n";
		xml += "</xml>\n";
		
		return xml;
	}
}
