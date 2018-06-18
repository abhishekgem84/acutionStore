package com.pckage.auctionStore.core;

import java.util.concurrent.ConcurrentHashMap;

import com.pckage.auctionStore.action.AuctionManager;

public class Listner {
	private static Listner listner=new Listner();
	private final ConcurrentHashMap<Item, Long> timeManager=new ConcurrentHashMap<>();
	private Listner(){
		
	}
	public static Listner getInstance(){
			return listner;
	}
	
	public void notifyUpdate(Item item,Long timeInMilli){
		System.out.println("Adding Notification....");
		if(timeManager.get(item)==null){
			timeManager.put(item, timeInMilli);
		}else{
			timeManager.replace(item, timeInMilli);
		}
	}
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
