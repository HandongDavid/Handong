package oodp;
import java.util.ArrayList;

public class CareTaker { //�޸����� ������ å������.
	
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
