package uk.co.fostorial.sotm.deck;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import uk.co.fostorial.sotm.structure.Deck;

public class DeckStatisticsTableModel implements TableModel {
	
	public final int STAT = 0;
	public final int VALUE = 1;

	private Deck deck;
	
	public DeckStatisticsTableModel(Deck deck) {
		this.deck = deck;
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex)
		{
		case STAT:
			return "Statistic";
		case VALUE:
			return "Value";
		}
		return null;
	}

	@Override
	public int getRowCount() {
		return deck.getStats().size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (deck.getStats().size() == 0)
		{
			return "";
		}
		
		switch  (columnIndex)
		{
		case STAT:
			return deck.getStats().get(rowIndex).getName();
		case VALUE:
			return deck.getStats().get(rowIndex).getStat();
		}
		return "";
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		
	}

}
