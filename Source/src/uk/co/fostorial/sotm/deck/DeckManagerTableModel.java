package uk.co.fostorial.sotm.deck;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import uk.co.fostorial.sotm.structure.Deck;

public class DeckManagerTableModel implements TableModel {
	
	public final int ID = 0;
	public final int NAME = 1;
	public final int TYPE = 2;
	public final int CLASSES = 3;
	public final int HEALTH_POINTS = 4;
	public final int NUMBER_IN_DECK = 5;

	private Deck deck;
	
	public DeckManagerTableModel(Deck deck) {
		this.deck = deck;
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex)
		{
		case ID:
			return Integer.class;
		case NAME:
			return String.class;
		case TYPE:
			return String.class;
		case CLASSES:
			return String.class;
		case HEALTH_POINTS:
			return String.class;
		case NUMBER_IN_DECK:
			return Integer.class;
		}
		return null;
	}

	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex)
		{
		case ID:
			return "Card ID";
		case NAME:
			return "Card Name";
		case TYPE:
			return "Card Type";
		case CLASSES:
			return "Classes";
		case HEALTH_POINTS:
			return "Health Points";
		case NUMBER_IN_DECK:
			return "# in Deck";
		}
		return null;
	}

	@Override
	public int getRowCount() {
		return deck.getCards().size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex)
		{
		case ID:
			return deck.getCards().get(rowIndex).getCardID();
		case NAME:
			return deck.getCards().get(rowIndex).getName();
		case TYPE:
			return deck.getCards().get(rowIndex).getCardTypeString();
		case CLASSES:
			return deck.getCards().get(rowIndex).getClasses();
		case HEALTH_POINTS:
			return deck.getCards().get(rowIndex).getHealthPoints();
		case NUMBER_IN_DECK:
			return deck.getCards().get(rowIndex).getNumberInDeck();
		}
		return "";
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		
	}

}
