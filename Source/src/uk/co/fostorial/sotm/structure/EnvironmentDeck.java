package uk.co.fostorial.sotm.structure;

import java.util.List;

public class EnvironmentDeck extends Deck {

	public EnvironmentDeck() {
		super();
		
		addCard(new BackCard("Card Back", getNextIDInteger()));
		
	}
	
	public EnvironmentDeck(List<Card> cards)
	{
		super();
		setCards(cards);
	}

}
