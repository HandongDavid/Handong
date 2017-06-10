package oodp;


/*
 * Swing 과 JDBC를 이용하여 
 * 달력을 만들고 달력에 메모장 기능을 추가하라.
 * 메모는 추가 삭제가 가능하고 메모가 있는날은
 * 달력에 표시가 되어야 한다. 
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import java.util.ArrayList;
import java.util.StringTokenizer; 

class CalanderView extends JFrame implements ActionListener
{
       String [] days = {"일","월","화","수","목","금","토"};
       int year ,month,day,todays,memoday=0;

       Font font;
      
       Calendar today;
       Calendar cal;
       int btnNum;
  
       JButton btnBefore2,btnAfter2; // 연도별로 before, after
       JButton btnBefore,btnAfter; //월별로 before, after
       JButton btnSchManager; //코치에게 할당된 button
       JButton btnExerRecord; //선수에게 할당된 button       
       JButton[] calBtn = new JButton[49];
 
       JLabel time;
       
       JPanel panSouth;
       JPanel panNorth;
       JPanel panCenter;
                           
       JTextField txtMonth,txtYear;
       // 글자를 넣을수있는 텍스트 필드 년 월 메모부분
       	JLabel userInfo;
       
       	BorderLayout bLayout= new BorderLayout();     
       	MemRegister mr;
       	ScheduleRegister sr;
       	ShowResult ss;
       
       	Login lg;
       	InitCalander ic=new InitCalander();
   	
       	ScheduleTable sct = new ScheduleTable();
		SingletonUser curUser = SingletonUser.getSingletonUser();
       
		public CalanderView(){
    	   
           setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //닫는기능
           setTitle("조정동아리 일정관리 시스템");
           setBounds(800,300,450,400);	//(x,y,가로,세로) 프레임창의 위치
    	  
			
    	   	
    	   
             today = Calendar.getInstance(); //디폴트의 타임 존 및 로케일을 사용해 달력을 가져옵니다.
             cal = new GregorianCalendar();
             /*
              * GregorianCalendar 는,Calendar 의 구상 서브 클래스이며,
              * 세계의 대부분의 지역에서 사용되는 표준적인 달력 시스템을 제공합니다. 
              */
             year = today.get(Calendar.YEAR);
             month = today.get(Calendar.MONTH)+1;//1월의 값이 0 
             
             
             //윗쪽 패널 디자인
             panNorth = new JPanel();
             panNorth.add(btnBefore2 = new JButton(" ↓ "));  
             panNorth.add(btnBefore = new JButton(" ← "));            
             
             panNorth.add(txtYear = new JTextField(year+"년"));
             panNorth.add(txtMonth = new JTextField( month+"월"));
           
             font=new Font("Sherif",Font.BOLD,18); //년가 월을 표시하는 텍스트 필드의 글자 속성
             txtYear.setFont(font);
             txtMonth.setFont(font);
             
             txtYear.setEnabled(false); // 년과 월을 선택 비활성화하여 숫자 수정을 불가피한다.
             txtMonth.setEnabled(false);
             
             panNorth.add(btnAfter = new JButton(" → "));
             panNorth.add(btnAfter2 = new JButton(" ↑ "));
               
             this.add(panNorth,"North");

             
             /*
             * CalanderView라는 큰놈 위에 레이아웃을 동,서,남,북으로 나눠서
             * 패널을 하나 하나 올려 놓는 형식이다.
             * 메인보드 위에 부품이 하나 하나 조립되듯..... 
             */

             //이놈은 달력에 날에 해당하는 부분

             panCenter = new JPanel(new GridLayout(7,7));//격자나,눈금형태의 배치관리자
             font=new Font("Sherif",Font.BOLD,12);
            
             gridInit();
             calSet();
             hideInit();
             this.add(panCenter,"Center");

             //////////////////////////////////
             
             
             //맨 아래쪽에 해당하는 부분
             panSouth = new JPanel(); 
             
             panSouth.add(userInfo = new JLabel(curUser.getUser().getPosition()+" Mode 입니다."));
            // JPanel userInfo
 
             
             this.add(panSouth,"South");

             //버튼에 대한 행동들을 정의한다.
             btnBefore.addActionListener(this);
             btnAfter.addActionListener(this);
             btnBefore2.addActionListener(this);
             btnAfter2.addActionListener(this);

 

             setVisible(true); 
        
             
       

       }//end constuctor


       
       public void calSet(){

             cal.set(Calendar.YEAR,year);
             cal.set(Calendar.MONTH,(month-1));
             cal.set(Calendar.DATE,1);
             int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

             /*
              * get 및 set 를 위한 필드치로, 요일을 나타냅니다.
              * 이 필드의 값은,SUNDAY,MONDAY,TUESDAY,WEDNESDAY
              * ,THURSDAY,FRIDAY, 및 SATURDAY 가 됩니다. 
              * get()메소드의 의해 요일이 숫자로 반환
              */

             int j=0;
             int hopping=0;
             calBtn[0].setForeground(new Color(255,0,0));//일요일 "일"  RGB의 색 넣는다.
             calBtn[6].setForeground(new Color(0,0,255));//토요일 "토"

             for(int i=cal.getFirstDayOfWeek();i<dayOfWeek;i++){  j++;  }
             /*
              * 일요일부터 그달의 첫시작 요일까지 빈칸으로 셋팅하기 위해 
              */
             hopping=j;
             btnNum = hopping+6;
             
             
             for(int kk=0;kk<hopping;kk++){
                    calBtn[kk+7].setText("");
             }
             for(int i=cal.getMinimum(Calendar.DAY_OF_MONTH);i<=cal.getMaximum(Calendar.DAY_OF_MONTH);i++)
             {
                 cal.set(Calendar.DATE,i);
                    if(cal.get(Calendar.MONTH) !=month-1){
                           break;
                    }
                    
                    todays=i;
                    
                    calBtn[i+6+hopping].setForeground(new Color(0,0,0));
                    if((i+hopping-1)%7==0)
                    {//일요일
                    	calBtn[i+6+hopping].setForeground(new Color(255,0,0));
                    }
                    if((i+hopping)%7==0){//토요일
                        calBtn[i+6+hopping].setForeground(new Color(0,0,255));
                    }
                    
                    
                    //스케줄이 기록된 버튼을 노란색으로 바꿔준다.
                    if(sct.tableCheck(year,month,i) == false)
                    {
                    	calBtn[i+6+hopping].setBackground(Color.yellow);
                    	calBtn[i+6+hopping].repaint();
                    }
                    /*
                     * 요일을 찍은 다음부터 계산해야 하니 요일을 찍은 버튼의 개수를 더하고
                     * 인덱스가 0부터 시작이니 -1을 해준 값으로 연산을 해줌
                     */
	                calBtn[i+6+hopping].setText((i)+"");
                    
           
             }//for
 
       }//end Calset()
       
       public void actionPerformed(ActionEvent e){   //버튼을 눌렀을때 발생하는 이벤트
    	   

             if(e.getSource() == btnBefore){	// 이전달로 가기위한 소스부
                    this.panCenter.removeAll();
                    calInput(-1);				//달을 하나 빼준다
                    ic.initCal(this);
               
             }                   
             else if(e.getSource() == btnAfter){	// 다음 달로 가기위한 소스부
                    this.panCenter.removeAll();
                    calInput(1);				//달을 하나 더해준다.
                    ic.initCal(this);
                                      
             }
             else if(e.getSource() == btnBefore2){	// 전년 으로 가기위한 소스부
                 this.panCenter.removeAll();
                 calInput(-12);				//12개월을 빼준다.
                 ic.initCal(this);
                                       
          }
             else if(e.getSource() == btnAfter2){	// 내년으 로 가기위한 소스부
                 this.panCenter.removeAll();
                 calInput(12);				//12개월을  더해준다.
                 ic.initCal(this);
                                        
          }

             else if(Integer.parseInt(e.getActionCommand()) >= 1 &&Integer.parseInt(e.getActionCommand()) <=31)
             {
                 for(int i=0; i<calBtn.length; i++)
                 {
                	 if(curUser.getUser().getPosition().equals("코치")) //사용자가 코치인 경우
                	 {
                         if( e.getSource() == calBtn[i])
                         { 
                        	 int chooseDay = Integer.parseInt(calBtn[i].getText());
                          	 sr = new ScheduleRegister(year,month,chooseDay,btnNum);                      
                         } 

                	 }
                	 else if(curUser.getUser().getPosition().equals("선수")) //사용자가 선수인 경우
                	 {
                         if( e.getSource() == calBtn[i] )
                         { 
                        	 int chooseDay = Integer.parseInt(calBtn[i].getText());
                        	 ss = new ShowResult(year,month,chooseDay);                                              	 
                         } 
                	 }
                 }
             }
     	
       }//end actionperformed()
    
       public void hideInit(){
             for(int i = 0 ; i < calBtn.length;i++){
                    if((calBtn[i].getText()).equals(""))
                           calBtn[i].setEnabled(false);
                    //일이 찍히지 않은 나머지 버튼을 비활성화 시킨다. 
             }//end for
       }//end hideInit()
//     public void separate(){
 
       
       
       public void gridInit(){
         //jPanel3에 버튼 붙이기
         for(int i = 0 ; i < days.length;i++)
               panCenter.add(calBtn[i] = new JButton(days[i]));                   

           for(int i = days.length ; i < 49;i++){                
                    panCenter.add(calBtn[i] = new JButton(""));                   
                    calBtn[i].addActionListener(this);
             }              
       }//end gridInit()
       
       
 /*      
       public void panelInit(){
         GridLayout gridLayout1 = new GridLayout(7,7);
         panCenter.setLayout(gridLayout1);   
       }//end panelInit()
 */      
       
       public void calInput(int gap){
                         
              if (gap==-1 || gap ==1)
              {
            	  month+=(gap);
            	  if (month<=0)
            	  {
                      month = 12;
                      year  =year- 1;
            	  }
            	  else if (month>=13)
            	  {
                      month = 1;
                      year =year+ 1;
            	  }
              }
              else if(gap == 12){	year++;	}
              else if(gap == -12){	year--;	}
         
              
       }//end calInput()

       public static void main(String[] args)
       {
    	          
       }
}
