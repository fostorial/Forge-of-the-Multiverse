package uk.co.fostorial.sotm.structure;

public class DeckStatistic {

	private String name;
	private String stat;
	
	public DeckStatistic(String name, String stat) {
		this.name = name;
		this.stat = stat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	
}
