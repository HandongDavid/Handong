package oodp;

public class Memento {
	
	//우선 코치모드에서 스케줄만 다루게 만듬
	private Schedule sch;
	
	public Memento(Schedule sch)
	{
		this.sch = sch;
	}
	
	public void setSchedule(Schedule sch)
	{
		this.sch = sch;
	}

	public Schedule getSchedule()
	{
		return sch;
	}
	
}
