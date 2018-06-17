package com.pckage.auctionStore.core;

public class Bid {
	private int paddelNumber;
	private int bidPrice;

	public Bid(int paddelNumber) {
		super();
		this.paddelNumber = paddelNumber;
	}

	public int getPaddelNumber() {
		return paddelNumber;
	}

	public void setPaddelNumber(int paddelNumber) {
		this.paddelNumber = paddelNumber;
	}

	public int getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(int bidPrice) {
		this.bidPrice = bidPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bidPrice;
		result = prime * result + paddelNumber;
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
		Bid other = (Bid) obj;
		if (bidPrice != other.bidPrice)
			return false;
		if (paddelNumber != other.paddelNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Bid [paddelNumber=%s, bidPrice=%s]", paddelNumber, bidPrice);
	}
}
