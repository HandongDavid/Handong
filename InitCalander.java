package oodp;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Calendar;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
import java.io.*;

//캘린더 initialization 하는 걸 헬퍼 클래스로 만듦
public class InitCalander {
	
    ScheduleTable sct = new ScheduleTable();
    
	
	public void initCal(CalanderView cv){
		this.gridInit(cv);
		this.panelInit(cv); 
		this.calSet(cv);
		this.hideInit(cv);
        cv.txtYear.setText(cv.year+"년");
        cv.txtMonth.setText(cv.month+"월"); 
	}
	
    public void gridInit(CalanderView cv){
        //jPanel3에 버튼 붙이기
        for(int i = 0 ; i < cv.days.length;i++)
              cv.panCenter.add(cv.calBtn[i] = new JButton(cv.days[i]));                   

          for(int i = cv.days.length ; i < 49;i++){                
        	  cv.panCenter.add(cv.calBtn[i] = new JButton(""));                   
        	  cv.calBtn[i].addActionListener(cv);
            }              
      }//end gridInit()
    
    public void panelInit(CalanderView cv){
        GridLayout gridLayout1 = new GridLayout(7,7);
        cv.panCenter.setLayout(gridLayout1);   
      }//end panelInit()
      
    public void calSet(CalanderView cv){
    	cv.cal.set(Calendar.YEAR,cv.year);
    	cv.cal.set(Calendar.MONTH,(cv.month-1));
    	cv.cal.set(Calendar.DATE,1);
        int dayOfWeek = cv.cal.get(Calendar.DAY_OF_WEEK);

        /*
         * get 및 set 를 위한 필드치로, 요일을 나타냅니다.
         * 이 필드의 값은,SUNDAY,MONDAY,TUESDAY,WEDNESDAY
         * ,THURSDAY,FRIDAY, 및 SATURDAY 가 됩니다. 
         * get()메소드의 의해 요일이 숫자로 반환
         */

        int j=0;
        int hopping=0;
        cv.calBtn[0].setForeground(new Color(255,0,0));//일요일 "일"  RGB의 색 넣는다.
        cv.calBtn[6].setForeground(new Color(0,0,255));//토요일 "토"

        for(int i=cv.cal.getFirstDayOfWeek();i<dayOfWeek;i++){  j++;  }
        /*
         * 일요일부터 그달의 첫시작 요일까지 빈칸으로 셋팅하기 위해 
         */
        hopping=j;
        
        for(int kk=0;kk<hopping;kk++){
        	cv.calBtn[kk+7].setText("");
        }
        for(int i=cv.cal.getMinimum(Calendar.DAY_OF_MONTH);i<=cv.cal.getMaximum(Calendar.DAY_OF_MONTH);i++)
        {
        	cv.cal.set(Calendar.DATE,i);
               if(cv.cal.get(Calendar.MONTH) !=cv.month-1){
                      break;
               }
               
               cv.todays=i;
               
               cv.calBtn[i+6+hopping].setForeground(new Color(0,0,0));
               if((i+hopping-1)%7==0)
               {//일요일
            	   cv.calBtn[i+6+hopping].setForeground(new Color(255,0,0));
               }
               if((i+hopping)%7==0){//토요일
            	   cv.calBtn[i+6+hopping].setForeground(new Color(0,0,255));
               }
               if(sct.tableCheck(cv.year,cv.month,i) == false)
               {
               	cv.calBtn[i+6+hopping].setBackground(Color.yellow);
               	cv.calBtn[i+6+hopping].repaint();
               }

               /*
                * 요일을 찍은 다음부터 계산해야 하니 요일을 찍은 버튼의 갯수를 더하고
                * 인덱스가 0부터 시작이니 -1을 해준 값으로 연산을 해주고
                * 버튼의 색깔을 변경해준다. 
                */
               cv.calBtn[i+6+hopping].setText((i)+"");
               
      
        }//for

  }//end Calset()
    
    public void hideInit(CalanderView cv){
        for(int i = 0 ; i < cv.calBtn.length;i++){
               if((cv.calBtn[i].getText()).equals(""))
            	   cv.calBtn[i].setEnabled(false);
               //일이 찍히지 않은 나머지 버튼을 비활성화 시킨다. 
        }//end for
  }//end hideInit()


}
