package oodp;

public class Schedule // ������ ����
{
	//��¥
	private int year;
	private int month;
	private int day;
	
	private String title; //����
	private String sports;  //� ����
	private String detailSports;  //���� ����
	
	//�ð�
	private String timeHour; 
	private String timeMinute;


	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getMonth() {
		return month;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}
	
	public int getDay() {
		return day;
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getSports() {
		return sports;
	}
	
	public void setSports(String sports) {
		this.sports = sports;
	}
	
	public String getDetailSports() {
		return detailSports;
	}
	
	public void setDetailSports(String detailSports) {
		this.detailSports = detailSports;
	}
	
	public String getHour() {
		return timeHour;
	}
	
	public void setHour(String hour) {
		this.timeHour = hour;
	}
	
	public String getMinute() {
		return timeMinute;
	}
	
	public void setMinute(String minute) {
		this.timeMinute = minute;
	}
	
	
}
