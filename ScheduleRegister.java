package oodp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.*;

public class ScheduleRegister extends JFrame implements ActionListener, ItemListener{ //코치 모드에서 스케줄을 등록하는 창
	
	private JLabel schTitleLabel;
	private JTextField schTitleTxt;
	
	private JLabel ExerType;
	private JComboBox<String> ChooseExerType; //주된 4개 운동 종목 콤보박스
	
	////4종목을 세부적으로 들어감
	private JComboBox<String> weightType; //웨이트 콤보박스
	private JComboBox<String> aerobicType; //유산소 콤보박스
	private JComboBox<String> rowingType; //rowing 콤보박스
	private JComboBox<String> rowingMachineType; //rowing machine 콤보박스
	
	private JLabel DateLabel;
	
	private JLabel schTimeLabel;//시간
	private JLabel schHourLabel;//시
	private JTextField schHourTxt;
	private JLabel schMinLabel;//분
	private JTextField schMinTxt;
	
	private JTextField DateTxt;
	
	private JButton register=new JButton("등록");
	private JButton modify=new JButton("수정");
	private JButton delete=new JButton("삭제");
	private JButton cancel=new JButton("되돌리기");
	private JButton showPlayer=new JButton("훈련기록");
	
	
	ShowPlayerResult spr;
	ScheduleTable st = new ScheduleTable();
	
	int tempYear;
	int tempMonth;
	int tempDay;
	int tempBtnNum;
	//CalanderView cv;
	int scheduleListIndex = 0; //현재 선택된 scheduleList의 index
	
	//메멘토
	Originator originator = new Originator();	
	CareTaker caretaker = new CareTaker();

	
	public ScheduleRegister(int year,int month,int day, int btnNum){ //스케줄 등록 창
		
		super("스케줄 등록");
		setSize(500, 270);
		setLocation(500, 300);
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
		
		schTitleLabel = new JLabel("제목 : ");
		schTitleLabel.setBounds(207, 16, 90, 30);
		c.add(schTitleLabel);
		
		schTitleTxt=new JTextField();
		schTitleTxt.setColumns(10);
		schTitleTxt.setBounds(280, 21, 200, 21);
		c.add(schTitleTxt);
		
   		 
		///////////콤보박스 영역 start//////////////////////
		
		ChooseExerType=new JComboBox();//주된 운동 종목
		c.add(ChooseExerType);
		ChooseExerType.setBounds(27, 56, 120, 30);
		ChooseExerType.addItem("운동 종목 선택");
		ChooseExerType.addItem("웨이트");
		ChooseExerType.addItem("유산소");
		ChooseExerType.addItem("rowing");
		ChooseExerType.addItem("rowing machine");
		
		ChooseExerType.addItemListener(this);
		
	
		weightType=new JComboBox();
		c.add(weightType);
		weightType.setBounds(27, 96, 150, 30);
		weightType.addItem("웨이트 세부 연습 선택"); //index 0
		weightType.addItem("bench-press(가슴)");
		weightType.addItem("pull-up(등)");
		weightType.addItem("squat(허벅지)");
		weightType.addItem("dumbbell(이두)");
		weightType.setVisible(false);
		
		aerobicType=new JComboBox();
		c.add(aerobicType);
		aerobicType.setBounds(27, 96, 150, 30);
		aerobicType.addItem("유산소 세부 연습 선택");
		aerobicType.addItem("Treadmill");
		aerobicType.addItem("track running");
		aerobicType.setVisible(false);
		
		rowingType=new JComboBox();
		c.add(rowingType);
		rowingType.setBounds(27, 96, 150, 30);
		rowingType.addItem("rowing 세부 연습 선택");
		rowingType.addItem("500m");
		rowingType.addItem("1000m");
		rowingType.addItem("2000m");
		rowingType.addItem("interval training");
		rowingType.setVisible(false);
		
		rowingMachineType=new JComboBox();
		c.add(rowingMachineType);
		rowingMachineType.setBounds(27, 96, 150, 30);
		rowingMachineType.addItem("rowingMachine 세부 연습 선택");
		rowingMachineType.addItem("500m");
		rowingMachineType.addItem("1000m");
		rowingMachineType.addItem("2000m");
		rowingMachineType.setVisible(false);
		
		
		///////////콤보박스 영역 end//////////////////////
		
		
		
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
		
		
		//버튼 영역
		register.setBounds(40, 182, 97, 40);
		c.add(register);
		register.addActionListener(this);
		
		modify.setBounds(140, 182, 97, 40);
		c.add(modify);
		modify.addActionListener(this);
		
		delete.setBounds(240, 182, 97, 40);
		c.add(delete);
		delete.addActionListener(this);
		
		cancel.setBounds(340, 182, 97, 40);
		c.add(cancel);		
		cancel.addActionListener(this);
		
		
		showPlayer.setBounds(340, 132, 97, 40);
		c.add(showPlayer);		
		showPlayer.addActionListener(this);
		
		//선택한 버튼의 날짜 저장
		tempYear = year;
		tempMonth = month;
		tempDay = day;
		tempBtnNum = btnNum;
		
		this.setVisible(true);
		

		//만약 스케줄 정보가 저장되어 있는 경우.
		if(st.tableCheck(tempYear, tempMonth, tempDay) == false)
	   	 {
	   		 Schedule sch= st.tableSave(tempYear, tempMonth, tempDay);
	   		 
	   		schTitleTxt.setText(sch.getTitle());
	   		ChooseExerType.setSelectedItem(sch.getSports());
			
	   		if(ChooseExerType.getSelectedIndex()==1)
	   		{
	   			weightType.setSelectedItem(sch.getDetailSports());
	   			weightType.setEnabled(false);
	   		}					
			else if(ChooseExerType.getSelectedIndex()==2)
			{
				aerobicType.setSelectedItem(sch.getDetailSports());
	   			aerobicType.setEnabled(false);					
			}
			else if(ChooseExerType.getSelectedIndex()==3)
			{
				rowingType.setSelectedItem(sch.getDetailSports());
				rowingType.setEnabled(false);	
			}
			else if(ChooseExerType.getSelectedIndex()==4)
			{
				rowingMachineType.setSelectedItem(sch.getDetailSports());
				rowingMachineType.setEnabled(false);
			}
		   		
	   		schHourTxt.setText(sch.getHour());
	   		schMinTxt.setText(sch.getMinute());
	   		
	   		schTitleTxt.setEnabled(false);
	   		ChooseExerType.setEnabled(false);
	   		schHourTxt.setEnabled(false);
	   		schMinTxt.setEnabled(false);
		   	
	   		//메멘토
	   		originator.setSchedule(sch);
	   		caretaker.addScheduleMemento(originator.createScheduleMemento());
	   	 }
	}


	
	
	public void itemStateChanged(ItemEvent ie){ //콤보박스 선택에 따른 action
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
		

	
	public void actionPerformed(ActionEvent e){ //버튼을 눌렀을때 action
       

		if(e.getSource() == register)
		{
			
			String title = schTitleTxt.getText();
			
			String sports = (String)ChooseExerType.getSelectedItem();
			if(sports.equals("운동 종목 선택")) //아무것도 선택 안한 경우
				sports = "";
			
			String detailSports ="";
			if(sports.equals("웨이트"))
			{
				detailSports = (String)weightType.getSelectedItem();
			}
			else if(sports.equals("유산소"))
			{
				detailSports = (String)aerobicType.getSelectedItem();
			}
			else if(sports.equals("rowing"))
			{
				detailSports = (String)rowingType.getSelectedItem();
			}
			else if(sports.equals("rowing machine"))
			{
				detailSports = (String)rowingMachineType.getSelectedItem();
			}
			else
			{
				detailSports = "";
			}
			
			String timeHour = schHourTxt.getText();
			String timeMinute = schMinTxt.getText();
			
			
			if(title.equals(""))
			{
				JOptionPane.showMessageDialog(this, "제목을 입력해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(sports.equals(""))
			{
				JOptionPane.showMessageDialog(this, "운동 종목을 선택해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(detailSports.equals(""))
			{
				JOptionPane.showMessageDialog(this, "운동 세부사항을 선택해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(timeHour.equals(""))
			{
				JOptionPane.showMessageDialog(this, "시간을 입력해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(timeMinute.equals(""))
			{
				JOptionPane.showMessageDialog(this, "시간을 입력해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}
			else //등록 버튼이 성공한 경우
			{

				Schedule s = new Schedule();
				s.setTitle(title);
				s.setSports(sports);
				s.setDetailSports(detailSports);
				s.setHour(timeHour);
				s.setMinute(timeMinute);
			
				s.setYear(tempYear);
				s.setMonth(tempMonth);
				s.setDay(tempDay);
				
				if(st.tableCheck(tempYear, tempMonth, tempDay)==true)
				{
					if(st.tableInsert(s)>0)
					{
						JOptionPane.showMessageDialog(this, "일정이 등록되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
						
						Login.cv.calBtn[tempBtnNum+tempDay].setBackground(Color.yellow);
						Login.cv.calBtn[tempBtnNum+tempDay].repaint();
						
						this.dispose();						
						
					}
				}
				else if((st.tableCheck(tempYear, tempMonth, tempDay)==false))
				{
					if(st.tableUpdate(s)>0)
					{
						JOptionPane.showMessageDialog(this, "일정을 수정하였습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
						this.dispose();						
					}
				}
		
			}
		}
		else if(e.getSource() == modify){


	   		schTitleTxt.setEnabled(true);
	   		ChooseExerType.setEnabled(true);
	   		schHourTxt.setEnabled(true);
	   		schMinTxt.setEnabled(true);
			
   			weightType.setEnabled(true);
   			aerobicType.setEnabled(true);
			rowingType.setEnabled(true);
			rowingMachineType.setEnabled(true);


		}
		else if(e.getSource() == delete)
		{
			int deleteCheck = JOptionPane.showConfirmDialog(this, "스케줄을 삭제 하시겠습니까?", "메시지", JOptionPane.INFORMATION_MESSAGE);
			if(deleteCheck == 0 )
			{
				schTitleTxt.setText(null);
				ChooseExerType.setSelectedIndex(0);
				schHourTxt.setText(null);
				schMinTxt.setText(null);
				
				if(st.tableDelete(tempYear,tempMonth,tempDay)>0)
				{
					JOptionPane.showMessageDialog(this, "삭제되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
					Login.cv.calBtn[tempBtnNum+tempDay].setBackground(new JButton().getBackground());
					Login.cv.calBtn[tempBtnNum+tempDay].repaint();
					
					this.dispose();	
				}				
			}

		}
		else if(e.getSource() == cancel){ //메멘토 적용
			originator.restoreScheduleMemento(caretaker.getScheduleMemento(0));
			Schedule sch = new Schedule();
			sch = originator.getSchedule();
			
			//복원!!
	   		schTitleTxt.setText(sch.getTitle());
	   		ChooseExerType.setSelectedItem(sch.getSports());
			
	   		if(ChooseExerType.getSelectedIndex()==1)
	   		{
	   			weightType.setSelectedItem(sch.getDetailSports());
	   		}					
			else if(ChooseExerType.getSelectedIndex()==2)
			{
				aerobicType.setSelectedItem(sch.getDetailSports());					
			}
			else if(ChooseExerType.getSelectedIndex()==3)
			{
				rowingType.setSelectedItem(sch.getDetailSports());		
			}
			else if(ChooseExerType.getSelectedIndex()==4)
			{
				rowingMachineType.setSelectedItem(sch.getDetailSports());
			}
		   		
	   		schHourTxt.setText(sch.getHour());
	   		schMinTxt.setText(sch.getMinute());
			
       }
		else if(e.getSource() == showPlayer){
			spr = new ShowPlayerResult(tempYear,tempMonth,tempDay);
		}
			
	}
	
	public static void main(String[] args) 
	{
		
	}

}
