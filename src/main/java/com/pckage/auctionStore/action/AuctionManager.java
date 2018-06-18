package com.pckage.auctionStore.action;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.pckage.auctionStore.core.Bid;
import com.pckage.auctionStore.core.Item;
import com.pckage.auctionStore.core.Listner;
/**
 * This class is responsible for creation of Object of AuctionManager.
 * This class is also responsible for managing auctions.
 * 
 * @author Abhishek Singh
 * @version 1.0
 */
public class AuctionManager {
	/** 
	 * To get the single instance of AuctionManager class
	 * */
	private static AuctionManager auctionManager;
	/**
	 * To store and manage bid for said item
	 * */
	private static ConcurrentHashMap<Item, List<Bid>> bidManager;
	/**
	 * Listner class to monitor bid time restrictions to get highest bid winner
	 * */
	private Listner listner=Listner.getInstance();
	/**
	 * Constructore of AuctionManager class responsible for starting listener and start auction.
	 * */
	private AuctionManager() {
		listner.startListner();
		initAuction();
	}
	/**
	 * This function initiate auction by initialization of bid manager.
	 * */
	private void initAuction() {
		bidManager = new ConcurrentHashMap<>();
	}
	/**
	 * This function return the single instance of AuctionManager class
	 * 
	 * @return Object of AuctionManager class
	 * */
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
	/**
	 * This function adds bid to bid manager, notify listener about the bid.
	 * 
	 * @param Item final object of item class
	 * @param Bid object of Bid class 
	 * */
	public synchronized void addBid(final Item item, Bid bid) {
		if (bidManager.get(item) == null) {
			if (itemCodeCheck(item.getItemCode()) == 0) {
				List<Bid> bidList = new ArrayList<>();
				bid.setBidPrice(item.getItemBasePrice() + item.getItemBidIncrement());
				bidList.add(bid);
				bidManager.put(item, bidList);
				listner.notifyUpdate(item, System.currentTimeMillis());
				System.out.println("New Bidding List Created for Item"+item);
			} else
				System.out.println(String.format("Item code %s is already added to Bid Manager", item.getItemCode()));
		} else {
			bid.setBidPrice(getHighestBidPrice(item) + item.getItemBidIncrement());
			bidManager.get(item).add(bid);
			listner.notifyUpdate(item, System.currentTimeMillis());
			System.out.println("Adding "+bid);
		}
	}
	/**
	 * This function helps in identifying the bid winner and closing bidding on item
	 * 
	 * @param Item Object of item on which bidding is occurring
	 * 
	 * @return Bid Highest winner bid for the item
	 * */
	public Bid closeBiddingOnItem(Item item) {
		if (bidManager.get(item) != null) {
			Bid bid=bidManager.get(item).stream().max(Comparator.comparingInt(Bid::getBidPrice)).get();
			bidManager.remove(item);
			return bid;
		}
		return null;
	}
	/**
	 * This function highest bid price for the item
	 * @param Item for this highest bid has to be found out.
	 * @return highest bid as integer
	 * @throws IllegalStateException
	 * */
	public int getHighestBidPrice(Item item) {
		if (bidManager.get(item) != null) {
			return bidManager.get(item).stream().map(Bid::getBidPrice).mapToInt(v -> v).max().getAsInt();
		}
		throw new IllegalStateException();
	}
	/**
	 * This function helps in finding there is no duplicate item code in bid manager.
	 * @param itemCode code of item
	 * @return 0 where there is no item with that particular code
	 * */
	public int itemCodeCheck(int itemCode) {
		return bidManager.entrySet().stream().filter(p -> p.getKey().getItemCode() == itemCode).map(m -> m)
				.collect(Collectors.counting()).intValue();
	}
	/**
	 * This function list all item that are up for bidding
	 * */
	public void listAllItemForBid() {
		bidManager.forEach((k, v) -> System.out.println("Listing "+k));
	}
	/**
	 * This function list all bids for that item.
	 * */
	public void listAllBids(Item item) {
		if (bidManager.get(item) != null) {
			bidManager.get(item).stream().forEach(System.out::println);
		}
	}

}
