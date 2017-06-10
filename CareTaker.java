package oodp;
import java.util.ArrayList;

public class CareTaker { //메멘토의 보관을 책임진다.
	
	private ArrayList<Memento> scheduleList = new ArrayList<Memento>();

	public void addScheduleMemento(Memento m)
	{
		scheduleList.add(m);
	}
	
	public Memento getScheduleMemento(int index)
	{
		return scheduleList.get(index);
	}

}
