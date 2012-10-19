package uk.co.fostorial.sotm.structure;

import java.util.List;

public class HeroDeck extends Deck {

	public HeroDeck() {
		super();
		
		addCard(new HeroFrontCard("Hero Front", getNextIDInteger()));
		addCard(new HeroBackCard("Hero Back", getNextIDInteger()));
		addCard(new BackCard("Card Back", getNextIDInteger()));
		
	}
	
	public HeroDeck(List<Card> cards)
	{
		super();
		setCards(cards);
	}

}
