package oodp;

public class PlayerResult { //선수들의 연습 기록
   
   private String pMexer;
   private String pExer;

   private String pHour;
   private String pMinute;
   private String pSecond;
   private  String pNum;

   private  User user;
   private int year;
   private int month;
   private int day;
   
   private String time;
   
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
   
   public User getUser(){
	   return user;
   }
   
   public void setUser(User user){
	   this.user = user;
   }
   
   public String getPMexer() {
      return pMexer+" ";
   }
   public void setPMexer(String pMexer) {
      this.pMexer = pMexer;
   }
   
   public String getPexer() {
      return pExer+" : ";
   }
   public void setPexer(String pExer) {
      this.pExer = pExer;
   }
   

   public String getPhour() {
      if(pHour.equals("") || pHour.equals("0"))
         return "";
      else
         return pHour;
   }
   public void setPhour(String phour) {
      this.pHour = phour;
   }
   
   public String getPminute() {
      if(pMinute.equals("") || pMinute.equals("0"))
         return "";
      else
         return pMinute;
   }
   public void setPminute(String pminute) {
      this.pMinute = pminute;
   }
   
   public String getPsecond() {
      if(pSecond.equals("") || pSecond.equals("0"))
         return "";
      else
         return pSecond;
   }
   public void setPsecond(String psecond) {
      this.pSecond = psecond;
   }
   
   public String getPnum() {
      if(pNum.equals("") || pNum.equals("0"))
         return "";
      else
         return pNum;
   }
   
   public void setPnum(String pnum) {
      this.pNum = pnum;
   }
   
   public String getTime()
   {
	   return time;
   }
   
   public void setTime(String hour, String minute, String second)
   {
	   this.time = hour +" "+ minute + " " + second + " ";
   }
   /*public String getPtype() {
      return ptype;
   }
   
   public void setPtype(String ptype) {
      this.ptype = ptype;
   }*/
}