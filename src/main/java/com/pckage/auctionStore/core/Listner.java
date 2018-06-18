package com.pckage.auctionStore.core;

import java.util.concurrent.ConcurrentHashMap;

import com.pckage.auctionStore.action.AuctionManager;
/**
 * Listner class to monitor bid time restrictions to get highest bid winner
 * 
 * @author Abhishek Singh
 * @version 1.0
 */
public class Listner {
	/** 
	 * To get the single instance of Listner class
	 * */
	private static Listner listner=new Listner();
	/**
	 * To store latest bid time for that item in time manager
	 * */
	private final ConcurrentHashMap<Item, Long> timeManager=new ConcurrentHashMap<>();
	/**
	 * 
	 * */
	private Listner(){
		
	}
	/**
	 * To get the single instance of listener class
	 * */
	public static Listner getInstance(){
			return listner;
	}
	/**
	 * To notify any bid time update of the item
	 * @param item Item on the bid
	 * @param timeMilli latest time on last bid
	 * */
	public void notifyUpdate(Item item,Long timeInMilli){
		System.out.println("Adding Notification....");
		if(timeManager.get(item)==null){
			timeManager.put(item, timeInMilli);
		}else{
			timeManager.replace(item, timeInMilli);
		}
	}
	/**
	 * To start listener thread
	 * */
	public void startListner(){
		new Thread(()->{
			System.out.println("Starting Listner....");
			while(true){
				try{					
					timeManager.forEach((k,v)->{
						Long sysTime=System.currentTimeMillis();
						if((sysTime-v)>(30*1000)){
							System.out.println(String.format("Winner for Item %s is %s",k,AuctionManager.getAuctionManager().closeBiddingOnItem(k)));
							timeManager.remove(k);
						}
					});
					Thread.sleep(1000*2);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}).start();
	}
}
