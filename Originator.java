package oodp;

public class Originator {

	//우선 코치모드에서 스케줄 정보만 적용시킴
	//필요에 따라서 다른 정보를 복원하고 싶으면 추가해서 사용하면 됨
	
	private Schedule sch;

	public void setSchedule(Schedule sch)
	{
		this.sch = sch;
	}
	
	public Schedule getSchedule()
	{
		return sch;
	}
	
	public Memento createScheduleMemento()
	{
		return new Memento(sch);
	}
	
	public void restoreScheduleMemento(Memento memento)
	{
		sch = memento.getSchedule();
	}
	
}
