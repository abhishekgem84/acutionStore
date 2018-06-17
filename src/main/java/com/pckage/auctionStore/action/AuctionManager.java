package com.pckage.auctionStore.action;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.pckage.auctionStore.core.Bid;
import com.pckage.auctionStore.core.Item;

public class AuctionManager {
	private static AuctionManager auctionManager;
	private static ConcurrentHashMap<Item, List<Bid>> bidManager;

	private AuctionManager() {
		initAuction();
	}

	private void initAuction() {
		bidManager = new ConcurrentHashMap<>();
	}

	public static AuctionManager getAuctionManager() {
		if (auctionManager == null) {
			synchronized (AuctionManager.class) {
				if (auctionManager == null) {
					auctionManager = new AuctionManager();
				}
			}
		}
		return auctionManager;
	}

	public synchronized void addBid(Item item, Bid bid) {
		if (bidManager.get(item) == null) {
			if (itemCodeCheck(item.getItemCode()) == 0) {
				List<Bid> bidList = new ArrayList<>();
				bid.setBidPrice(item.getItemBasePrice() + item.getItemBidIncrement());
				bidList.add(bid);
				bidManager.put(item, bidList);
				System.out.println("New Bidding List Created for Item"+item);
			} else
				System.out.println(String.format("Item code %s is already added to Bid Manager", item.getItemCode()));
		} else {
			bid.setBidPrice(getHighestBidPrice(item) + item.getItemBidIncrement());
			bidManager.get(item).add(bid);
			System.out.println("Adding "+bid);
		}
	}

	public Bid closeBiddingOnItem(Item item) {
		if (bidManager.get(item) != null) {
			Bid bid=bidManager.get(item).stream().max(Comparator.comparingInt(Bid::getBidPrice)).get();
			bidManager.remove(item);
			return bid;
		}
		return null;
	}

	public int getHighestBidPrice(Item item) {
		if (bidManager.get(item) != null) {
			return bidManager.get(item).stream().map(Bid::getBidPrice).mapToInt(v -> v).max().getAsInt();
		}
		throw new IllegalStateException();
	}

	public int itemCodeCheck(int itemCode) {
		return bidManager.entrySet().stream().filter(p -> p.getKey().getItemCode() == itemCode).map(m -> m)
				.collect(Collectors.counting()).intValue();
	}

	public void listAllItemForBid() {
		bidManager.forEach((k, v) -> System.out.println("Listing "+k));
	}

	public void listAllBids(Item item) {
		if (bidManager.get(item) != null) {
			bidManager.get(item).stream().forEach(System.out::println);
		}
	}

}
