package oodp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

//import java.io.File;
//import java.util.ArrayList;

import java.util.ArrayList;

import javax.swing.*;
//import javax.swing.filechooser.FileNameExtensionFilter;


public class TrainingResult extends JFrame implements ActionListener, ItemListener{  //선수들이 훈련을 기록하는 창
	
	
	PlayerResultTable prt = new PlayerResultTable();
	ArrayList<PlayerResult> playerList = new ArrayList<PlayerResult>();	

   /*private JLabel schTitleLabel;
   private JTextField schTitleTxt;
   */
   //private JLabel ExerType;
   private JComboBox<String> ChooseExerType;
   
////4종목을 세부적으로 들어감
   private JComboBox<String> weightType; //웨이트 콤보박스
   private JComboBox<String> aerobicType; //유산소 콤보박스
   private JComboBox<String> rowingType; //rowing 콤보박스
   private JComboBox<String> rowingMachineType; //rowing machine 콤보박스
   
   private JLabel DateLabel;
   private JTextField DateTxt;
   
   private JLabel schTimeLabel;//시간
   private JLabel schHourLabel;//시
   private JTextField schHourTxt;
   private JLabel schMinLabel;//분
   private JTextField schMinTxt;
   private JLabel schSecLabel;//초
   private JTextField schSecTxt;
   private JLabel schNumLabel;//횟수
   private JTextField schNumTxt;
      
   private JButton register=new JButton("등록");
   private JButton cancel=new JButton("취소");
   
   CalanderView cv;
   User user;
   
	int tempYear;
	int tempMonth;
	int tempDay;
	SingletonUser curUser = SingletonUser.getSingletonUser();
	
    boolean check = false;
   
    
    
   public TrainingResult(int year,int month,int day){//선수들이 훈련을 기록하는 창 생성
            
	   super("결과 등록");
	   setSize(600, 330);
	   setLocation(600, 300);
	   setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
      
       Container c = getContentPane();
       c.setLayout(null);

       DateLabel = new JLabel("날짜 : ");
       DateLabel.setBounds(27, 16, 90, 30);
       c.add(DateLabel);
      
       DateTxt=new JTextField(year+"년 "+month+"월 "+day+"일");
       DateTxt.setBounds(80, 16, 100, 30);
       c.add(DateTxt);
       DateTxt.setEnabled(false);
      
      
      
      ChooseExerType = new JComboBox<String>();
      c.add(ChooseExerType);
      ChooseExerType.setBounds(27, 56, 120, 30);
      ChooseExerType.addItem("운동 종목 선택");
      ChooseExerType.addItem("웨이트");
      ChooseExerType.addItem("유산소");
      ChooseExerType.addItem("rowing");
      ChooseExerType.addItem("rowing machine");
      
      ChooseExerType.addItemListener(this);
      
      weightType=new JComboBox<String>();
      c.add(weightType);
      weightType.setBounds(27, 96, 150, 30);
      weightType.addItem("웨이트 세부 연습 선택"); //index 0
      weightType.addItem("bench-press(가슴)");
      weightType.addItem("pull-up(등)");
      weightType.addItem("squat(허벅지)");
      weightType.addItem("dumbbell(이두)");
      weightType.setVisible(false);
      
      aerobicType=new JComboBox<String>();
      c.add(aerobicType);
      aerobicType.setBounds(27, 96, 150, 30);
      aerobicType.addItem("유산소 세부 연습 선택");
      aerobicType.addItem("Treadmill");
      aerobicType.addItem("track running");
      aerobicType.setVisible(false);
      
      rowingType=new JComboBox<String>();
      c.add(rowingType);
      rowingType.setBounds(27, 96, 150, 30);
      rowingType.addItem("rowing 세부 연습 선택");
      rowingType.addItem("500m");
      rowingType.addItem("1000m");
      rowingType.addItem("2000m");
      rowingType.addItem("interval training");
      rowingType.setVisible(false);
      
      rowingMachineType=new JComboBox<String>();
      c.add(rowingMachineType);
      rowingMachineType.setBounds(27, 96, 150, 30);
      rowingMachineType.addItem("rowingMachine 세부 연습 선택");
      rowingMachineType.addItem("500m");
      rowingMachineType.addItem("1000m");
      rowingMachineType.addItem("2000m");
      rowingMachineType.setVisible(false);
      
      schTimeLabel=new JLabel("시간: ");
      schTimeLabel.setBounds(207, 56, 90, 30);
      c.add(schTimeLabel);
      
      schHourTxt=new JTextField();
      schHourTxt.setColumns(10);
      schHourTxt.setBounds(280, 56, 62, 21);
      c.add(schHourTxt);
      
      schHourLabel=new JLabel("시 ");
      schHourLabel.setBounds(350,56,90,30);
      c.add(schHourLabel);
      
      schMinTxt=new JTextField();
      schMinTxt.setColumns(10);
      schMinTxt.setBounds(370, 56, 62, 21);
      c.add(schMinTxt);
      
      schMinLabel=new JLabel("분");
      schMinLabel.setBounds(440,56,90,30);
      c.add(schMinLabel);
      
      schSecTxt=new JTextField();
      schSecTxt.setColumns(10);
      schSecTxt.setBounds(460, 56, 62, 21);
      c.add(schSecTxt);
      
      schSecLabel=new JLabel("초");
      schSecLabel.setBounds(530,56,90,30);
      c.add(schSecLabel);
      
      schNumTxt=new JTextField();
      schNumTxt.setColumns(10);
      schNumTxt.setBounds(280, 96, 200, 21);
      c.add(schNumTxt);
      
      schNumLabel=new JLabel("수 : ");
      schNumLabel.setBounds(207, 96, 90, 30);
      c.add(schNumLabel);
      
      
      register.setBounds(147, 232, 97, 40);
      c.add(register);
      
      cancel.setBounds(330, 232, 97, 40);
      c.add(cancel);
      
      register.addActionListener(this);
      cancel.addActionListener(this);
      
      this.setVisible(true);
      
		//선택한 버튼의 날짜 저장
		tempYear = year;
		tempMonth = month;
		tempDay = day;
		
		playerList = prt.playerResultAll(tempYear, tempMonth, tempDay);
   }
   
   
   public void itemStateChanged(ItemEvent ie){
      if(ChooseExerType.getSelectedIndex()==1){
         weightType.setVisible(true);
         aerobicType.setVisible(false);
         rowingType.setVisible(false);
         rowingMachineType.setVisible(false);
      }
       else if(ChooseExerType.getSelectedIndex()==2)
       {
          weightType.setVisible(false);
          aerobicType.setVisible(true);
          rowingType.setVisible(false);
          rowingMachineType.setVisible(false);
       }   
       else if(ChooseExerType.getSelectedIndex()==3)
       {
          weightType.setVisible(false);
          aerobicType.setVisible(false);
          rowingType.setVisible(true);
          rowingMachineType.setVisible(false);
       }   
       else if(ChooseExerType.getSelectedIndex()==4)
       {
          weightType.setVisible(false);
          aerobicType.setVisible(false);
          rowingType.setVisible(false);
          rowingMachineType.setVisible(true);
       }   
       else
       {
          weightType.setVisible(false);
          aerobicType.setVisible(false);
          rowingType.setVisible(false);
          rowingMachineType.setVisible(false);
       }   
   }

   
   
    public void actionPerformed(ActionEvent e)
   {
      
      boolean StringCheck = true;
      if(e.getSource() == register)
      {
         String userPMexercise = (String) ChooseExerType.getSelectedItem();
         String userPexercise;
         String userPhour = schHourTxt.getText();
         String userPminute = schMinTxt.getText();
         String userPsecond = schSecTxt.getText();
         String userPnum = schNumTxt.getText();
         
         User user = curUser.getUser();
         
         if(ChooseExerType.getSelectedIndex()==1){
            userPexercise = (String)weightType.getSelectedItem();
         }
          else if(ChooseExerType.getSelectedIndex()==2)
          {
             userPexercise = (String)aerobicType.getSelectedItem();
          }   
          else if(ChooseExerType.getSelectedIndex()==3)
          {
             userPexercise = (String)rowingType.getSelectedItem();
          }   
          else if(ChooseExerType.getSelectedIndex()==4)
          {
             userPexercise = (String)rowingMachineType.getSelectedItem();
          }   
          else{
             userPexercise = "";
          }   
               
         PlayerResult r = new PlayerResult();    
         r.setPMexer(userPMexercise);
         r.setPexer(userPexercise);
         r.setPhour(userPhour);
         r.setPminute(userPminute);
         r.setPsecond(userPsecond);
         r.setPnum(userPnum);
         r.setUser(user);
         r.setYear(tempYear);
         r.setMonth(tempMonth);
         r.setDay(tempDay);
         
         
         for(int i=0; i<playerList.size(); i++)
         {
        	 if((playerList.get(i).getUser().getName()).equals(curUser.getUser().getName())==true)
        	 {
        		 check = true;
        		 break;
        	 }
         }
         
         if(check == false) //스케줄 중복등록 체크.. false는 중복된 기록이 없으므로 통과!
         {
             if(prt.tableInsert(r)>0)
             {    
                 JOptionPane.showMessageDialog(this, "결과가 등록되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
                 this.dispose();
             }
             else
            	 this.dispose();
         }


      }   
      else if(e.getSource() == cancel)
      {
         this.dispose();
      }
   }
      
   
   //public void actionPerformed(ActionEvent e){}
   
   public static void main(String[] args) {
      //
   }
   
}