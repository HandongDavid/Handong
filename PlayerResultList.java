package oodp;

import java.util.ArrayList;

public class PlayerResultList implements Aggregate{
	
	private ArrayList<PlayerResult> playerList;
	private int last = 0;
	
	public PlayerResultList(){
		this.playerList = new ArrayList<PlayerResult>();
	}
	
	public void setPlayerResult(ArrayList<PlayerResult> playerList)
	{
		this.playerList = playerList;
	}
	
	public PlayerResult getPlayerResult(int index){
		return playerList.get(index);
	}

	public void appendPlayerResult(PlayerResult prl)
	{
		this.playerList.set(last, prl);
		last++;
	}
	
	public int getLength(){
		last = playerList.size();
		return last;
	}
	public Iterator iterator(){
		return new PlayerResultListIterator(this);
	}
}
