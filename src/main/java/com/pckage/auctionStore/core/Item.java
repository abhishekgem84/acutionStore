package com.pckage.auctionStore.core;

public final class Item {
	private final int itemCode;
	private final String itemName;
	private final String itemDescription;
	private final int itemBasePrice;
	private final int itemBidIncrement;

	public Item(int itemCode, String itemName, String itemDescription, int itemBasePrice, int itemBidIncrement) {
		super();
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemBasePrice = itemBasePrice;
		this.itemBidIncrement = itemBidIncrement;
	}

	public int getItemCode() {
		return itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public int getItemBasePrice() {
		return itemBasePrice;
	}

	public int getItemBidIncrement() {
		return itemBidIncrement;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + itemBasePrice;
		result = prime * result + itemBidIncrement;
		result = prime * result + itemCode;
		result = prime * result + ((itemDescription == null) ? 0 : itemDescription.hashCode());
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (itemBasePrice != other.itemBasePrice)
			return false;
		if (itemBidIncrement != other.itemBidIncrement)
			return false;
		if (itemCode != other.itemCode)
			return false;
		if (itemDescription == null) {
			if (other.itemDescription != null)
				return false;
		} else if (!itemDescription.equals(other.itemDescription))
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format(
				"Item [itemCode=%s, itemName=%s, itemDescription=%s, itemBasePrice=%s, itemBidIncrement=%s]", itemCode,
				itemName, itemDescription, itemBasePrice, itemBidIncrement);
	}

}
