package com.enixer.dominionshuffler.cardlist;

public class Card implements Comparable<Card> {
	private int id;
	private int cost;
	private boolean potion;
	private String name;
	private String version;

	public Card(int i, int c, boolean p, String n, String v) {
		setId(i);
		setCost(c);
		setPotion(p);
		setName(n);
		setVersion(v);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public boolean isPotion() {
		return potion;
	}

	public void setPotion(boolean potion) {
		this.potion = potion;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public int compareTo(Card c) {
		return this.id - c.id;
	}
}
