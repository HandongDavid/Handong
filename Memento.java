package oodp;

public class Memento {
	
	//�켱 ��ġ��忡�� �����ٸ� �ٷ�� ����
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
