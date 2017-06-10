package oodp;

public class PlayerResultListIterator implements Iterator{

	private PlayerResultList playerResultList;
	private int index;
	
	public PlayerResultListIterator(PlayerResultList prl)
	{
		this.playerResultList = prl;
		this.index = 0;
	}
	
	public boolean hasNext(){
		if(index < playerResultList.getLength())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public Object next(){
		PlayerResult playerResult = playerResultList.getPlayerResult(index);
		index++;
		return playerResult;
	}
}
