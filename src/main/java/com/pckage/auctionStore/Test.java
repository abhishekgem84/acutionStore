package com.pckage.auctionStore;

import com.pckage.auctionStore.action.AuctionManager;
import com.pckage.auctionStore.core.Bid;
import com.pckage.auctionStore.core.Item;

public class Test {

	public static void main(String[] args) {
		try {
//			AuctionManager am=AuctionManager.getAuctionManager();
//			Item item=new Item(101, "Flask", "Good insulator 600ml", 500, 50);			
//			am.addBid(item,new Bid(99));
//			am.addBid(item,new Bid(98));
//			am.addBid(item,new Bid(97));
//			am.addBid(item,new Bid(96));
//			am.listAllBids(item);
//			System.out.println(String.format("Winner for Item %s is %s",item,am.closeBiddingOnItem(item)));
//			Item item2=new Item(102, "Charger", "2.1V", 300, 20);
//			am.addBid(item2,new Bid(99));
//			am.addBid(item2,new Bid(98));
//			am.addBid(item2,new Bid(97));
//			am.addBid(item2,new Bid(96));
//			am.listAllBids(item2);
//			System.out.println(String.format("Winner for Item %s is %s",item2,am.closeBiddingOnItem(item2)));
//			am.listAllItemForBid();
			final Item item =new Item(101, "Flask", "Good insulator 600ml", 500, 50);
			new Thread(()-> {
				AuctionManager.getAuctionManager().addBid(item, new Bid(99));
			}).start();
			new Thread(()-> {
				AuctionManager.getAuctionManager().addBid(item, new Bid(98));
			}).start();
			new Thread(()-> {
				AuctionManager.getAuctionManager().addBid(item, new Bid(97));
			}).start();
			new Thread(()-> {
				AuctionManager.getAuctionManager().addBid(item, new Bid(96));
			}).start();
			new Thread(()-> {
				AuctionManager.getAuctionManager().addBid(item, new Bid(95));
			}).start();
			
			final Item item1 =new Item(102, "Charger", "2.5v", 300, 20);
			new Thread(()-> {
				AuctionManager.getAuctionManager().addBid(item1, new Bid(99));
			}).start();
			new Thread(()-> {
				AuctionManager.getAuctionManager().addBid(item1, new Bid(98));
			}).start();
			new Thread(()-> {
				AuctionManager.getAuctionManager().addBid(item1, new Bid(97));
			}).start();
			new Thread(()-> {
				AuctionManager.getAuctionManager().addBid(item1, new Bid(96));
			}).start();
			new Thread(()-> {
				AuctionManager.getAuctionManager().addBid(item1, new Bid(95));
			}).start();
			
			AuctionManager.getAuctionManager().listAllItemForBid();
			Thread.sleep(1000*60);
			//System.out.println(String.format("Winner for Item %s is %s",item,AuctionManager.getAuctionManager().closeBiddingOnItem(item)));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
