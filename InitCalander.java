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

//Ķ���� initialization �ϴ� �� ���� Ŭ������ ����
public class InitCalander {
	
    ScheduleTable sct = new ScheduleTable();
    
	
	public void initCal(CalanderView cv){
		this.gridInit(cv);
		this.panelInit(cv); 
		this.calSet(cv);
		this.hideInit(cv);
        cv.txtYear.setText(cv.year+"��");
        cv.txtMonth.setText(cv.month+"��"); 
	}
	
    public void gridInit(CalanderView cv){
        //jPanel3�� ��ư ���̱�
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
         * get �� set �� ���� �ʵ�ġ��, ������ ��Ÿ���ϴ�.
         * �� �ʵ��� ����,SUNDAY,MONDAY,TUESDAY,WEDNESDAY
         * ,THURSDAY,FRIDAY, �� SATURDAY �� �˴ϴ�. 
         * get()�޼ҵ��� ���� ������ ���ڷ� ��ȯ
         */

        int j=0;
        int hopping=0;
        cv.calBtn[0].setForeground(new Color(255,0,0));//�Ͽ��� "��"  RGB�� �� �ִ´�.
        cv.calBtn[6].setForeground(new Color(0,0,255));//����� "��"

        for(int i=cv.cal.getFirstDayOfWeek();i<dayOfWeek;i++){  j++;  }
        /*
         * �Ͽ��Ϻ��� �״��� ù���� ���ϱ��� ��ĭ���� �����ϱ� ���� 
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
               {//�Ͽ���
            	   cv.calBtn[i+6+hopping].setForeground(new Color(255,0,0));
               }
               if((i+hopping)%7==0){//�����
            	   cv.calBtn[i+6+hopping].setForeground(new Color(0,0,255));
               }
               if(sct.tableCheck(cv.year,cv.month,i) == false)
               {
               	cv.calBtn[i+6+hopping].setBackground(Color.yellow);
               	cv.calBtn[i+6+hopping].repaint();
               }

               /*
                * ������ ���� �������� ����ؾ� �ϴ� ������ ���� ��ư�� ������ ���ϰ�
                * �ε����� 0���� �����̴� -1�� ���� ������ ������ ���ְ�
                * ��ư�� ������ �������ش�. 
                */
               cv.calBtn[i+6+hopping].setText((i)+"");
               
      
        }//for

  }//end Calset()
    
    public void hideInit(CalanderView cv){
        for(int i = 0 ; i < cv.calBtn.length;i++){
               if((cv.calBtn[i].getText()).equals(""))
            	   cv.calBtn[i].setEnabled(false);
               //���� ������ ���� ������ ��ư�� ��Ȱ��ȭ ��Ų��. 
        }//end for
  }//end hideInit()


}
