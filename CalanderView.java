package oodp;


/*
 * Swing �� JDBC�� �̿��Ͽ� 
 * �޷��� ����� �޷¿� �޸��� ����� �߰��϶�.
 * �޸�� �߰� ������ �����ϰ� �޸� �ִ³���
 * �޷¿� ǥ�ð� �Ǿ�� �Ѵ�. 
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import java.util.ArrayList;
import java.util.StringTokenizer; 

class CalanderView extends JFrame implements ActionListener
{
       String [] days = {"��","��","ȭ","��","��","��","��"};
       int year ,month,day,todays,memoday=0;

       Font font;
      
       Calendar today;
       Calendar cal;
       int btnNum;
  
       JButton btnBefore2,btnAfter2; // �������� before, after
       JButton btnBefore,btnAfter; //������ before, after
       JButton btnSchManager; //��ġ���� �Ҵ�� button
       JButton btnExerRecord; //�������� �Ҵ�� button       
       JButton[] calBtn = new JButton[49];
 
       JLabel time;
       
       JPanel panSouth;
       JPanel panNorth;
       JPanel panCenter;
                           
       JTextField txtMonth,txtYear;
       // ���ڸ� �������ִ� �ؽ�Ʈ �ʵ� �� �� �޸�κ�
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
    	   
           setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //�ݴ±��
           setTitle("�������Ƹ� �������� �ý���");
           setBounds(800,300,450,400);	//(x,y,����,����) ������â�� ��ġ
    	  
			
    	   	
    	   
             today = Calendar.getInstance(); //����Ʈ�� Ÿ�� �� �� �������� ����� �޷��� �����ɴϴ�.
             cal = new GregorianCalendar();
             /*
              * GregorianCalendar ��,Calendar �� ���� ���� Ŭ�����̸�,
              * ������ ��κ��� �������� ���Ǵ� ǥ������ �޷� �ý����� �����մϴ�. 
              */
             year = today.get(Calendar.YEAR);
             month = today.get(Calendar.MONTH)+1;//1���� ���� 0 
             
             
             //���� �г� ������
             panNorth = new JPanel();
             panNorth.add(btnBefore2 = new JButton(" �� "));  
             panNorth.add(btnBefore = new JButton(" �� "));            
             
             panNorth.add(txtYear = new JTextField(year+"��"));
             panNorth.add(txtMonth = new JTextField( month+"��"));
           
             font=new Font("Sherif",Font.BOLD,18); //�Ⱑ ���� ǥ���ϴ� �ؽ�Ʈ �ʵ��� ���� �Ӽ�
             txtYear.setFont(font);
             txtMonth.setFont(font);
             
             txtYear.setEnabled(false); // ��� ���� ���� ��Ȱ��ȭ�Ͽ� ���� ������ �Ұ����Ѵ�.
             txtMonth.setEnabled(false);
             
             panNorth.add(btnAfter = new JButton(" �� "));
             panNorth.add(btnAfter2 = new JButton(" �� "));
               
             this.add(panNorth,"North");

             
             /*
             * CalanderView��� ū�� ���� ���̾ƿ��� ��,��,��,������ ������
             * �г��� �ϳ� �ϳ� �÷� ���� �����̴�.
             * ���κ��� ���� ��ǰ�� �ϳ� �ϳ� �����ǵ�..... 
             */

             //�̳��� �޷¿� ���� �ش��ϴ� �κ�

             panCenter = new JPanel(new GridLayout(7,7));//���ڳ�,���������� ��ġ������
             font=new Font("Sherif",Font.BOLD,12);
            
             gridInit();
             calSet();
             hideInit();
             this.add(panCenter,"Center");

             //////////////////////////////////
             
             
             //�� �Ʒ��ʿ� �ش��ϴ� �κ�
             panSouth = new JPanel(); 
             
             panSouth.add(userInfo = new JLabel(curUser.getUser().getPosition()+" Mode �Դϴ�."));
            // JPanel userInfo
 
             
             this.add(panSouth,"South");

             //��ư�� ���� �ൿ���� �����Ѵ�.
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
              * get �� set �� ���� �ʵ�ġ��, ������ ��Ÿ���ϴ�.
              * �� �ʵ��� ����,SUNDAY,MONDAY,TUESDAY,WEDNESDAY
              * ,THURSDAY,FRIDAY, �� SATURDAY �� �˴ϴ�. 
              * get()�޼ҵ��� ���� ������ ���ڷ� ��ȯ
              */

             int j=0;
             int hopping=0;
             calBtn[0].setForeground(new Color(255,0,0));//�Ͽ��� "��"  RGB�� �� �ִ´�.
             calBtn[6].setForeground(new Color(0,0,255));//����� "��"

             for(int i=cal.getFirstDayOfWeek();i<dayOfWeek;i++){  j++;  }
             /*
              * �Ͽ��Ϻ��� �״��� ù���� ���ϱ��� ��ĭ���� �����ϱ� ���� 
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
                    {//�Ͽ���
                    	calBtn[i+6+hopping].setForeground(new Color(255,0,0));
                    }
                    if((i+hopping)%7==0){//�����
                        calBtn[i+6+hopping].setForeground(new Color(0,0,255));
                    }
                    
                    
                    //�������� ��ϵ� ��ư�� ��������� �ٲ��ش�.
                    if(sct.tableCheck(year,month,i) == false)
                    {
                    	calBtn[i+6+hopping].setBackground(Color.yellow);
                    	calBtn[i+6+hopping].repaint();
                    }
                    /*
                     * ������ ���� �������� ����ؾ� �ϴ� ������ ���� ��ư�� ������ ���ϰ�
                     * �ε����� 0���� �����̴� -1�� ���� ������ ������ ����
                     */
	                calBtn[i+6+hopping].setText((i)+"");
                    
           
             }//for
 
       }//end Calset()
       
       public void actionPerformed(ActionEvent e){   //��ư�� �������� �߻��ϴ� �̺�Ʈ
    	   

             if(e.getSource() == btnBefore){	// �����޷� �������� �ҽ���
                    this.panCenter.removeAll();
                    calInput(-1);				//���� �ϳ� ���ش�
                    ic.initCal(this);
               
             }                   
             else if(e.getSource() == btnAfter){	// ���� �޷� �������� �ҽ���
                    this.panCenter.removeAll();
                    calInput(1);				//���� �ϳ� �����ش�.
                    ic.initCal(this);
                                      
             }
             else if(e.getSource() == btnBefore2){	// ���� ���� �������� �ҽ���
                 this.panCenter.removeAll();
                 calInput(-12);				//12������ ���ش�.
                 ic.initCal(this);
                                       
          }
             else if(e.getSource() == btnAfter2){	// ������ �� �������� �ҽ���
                 this.panCenter.removeAll();
                 calInput(12);				//12������  �����ش�.
                 ic.initCal(this);
                                        
          }

             else if(Integer.parseInt(e.getActionCommand()) >= 1 &&Integer.parseInt(e.getActionCommand()) <=31)
             {
                 for(int i=0; i<calBtn.length; i++)
                 {
                	 if(curUser.getUser().getPosition().equals("��ġ")) //����ڰ� ��ġ�� ���
                	 {
                         if( e.getSource() == calBtn[i])
                         { 
                        	 int chooseDay = Integer.parseInt(calBtn[i].getText());
                          	 sr = new ScheduleRegister(year,month,chooseDay,btnNum);                      
                         } 

                	 }
                	 else if(curUser.getUser().getPosition().equals("����")) //����ڰ� ������ ���
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
                    //���� ������ ���� ������ ��ư�� ��Ȱ��ȭ ��Ų��. 
             }//end for
       }//end hideInit()
//     public void separate(){
 
       
       
       public void gridInit(){
         //jPanel3�� ��ư ���̱�
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
