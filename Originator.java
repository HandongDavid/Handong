package oodp;

public class Originator {

	//�켱 ��ġ��忡�� ������ ������ �����Ŵ
	//�ʿ信 ���� �ٸ� ������ �����ϰ� ������ �߰��ؼ� ����ϸ� ��
	
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
