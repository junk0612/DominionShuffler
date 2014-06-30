package com.enixer.dominionshuffler.cardlist;

public class Card implements Comparable<Card> {
	private int id;
	private String name;
	private String version;
	private int cost;
	private boolean potion;
	private String classification;
	private int treasure;
	private int vp;
	private int card;
	private int action;
	private int buy;
	private int coin;
	private int vpToken;
	private String description;

	public Card(int id, String name, String version, int cost, boolean potion,
			String classification, int treasure, int vp, int card, int action,
			int buy, int coin, int vpToken, String description) {
		setId(id);
		setName(name);
		setVersion(version);
		setCost(cost);
		setPotion(potion);
		setClassification(classification);
		setTreasure(treasure);
		setVp(vp);
		setCard(card);
		setAction(action);
		setBuy(buy);
		setCoin(coin);
		setVpToken(vpToken);
		setDescription(description);
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

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
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

	public int getTreasure() {
		return treasure;
	}

	public void setTreasure(int treasure) {
		this.treasure = treasure;
	}

	public int getVp() {
		return vp;
	}

	public void setVp(int vp) {
		this.vp = vp;
	}

	public int getCard() {
		return card;
	}

	public void setCard(int card) {
		this.card = card;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public int getBuy() {
		return buy;
	}

	public void setBuy(int buy) {
		this.buy = buy;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public int getVpToken() {
		return vpToken;
	}

	public void setVpToken(int vpToken) {
		this.vpToken = vpToken;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
