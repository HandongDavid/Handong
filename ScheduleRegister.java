package oodp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.*;

public class ScheduleRegister extends JFrame implements ActionListener, ItemListener{ //��ġ ��忡�� �������� ����ϴ� â
	
	private JLabel schTitleLabel;
	private JTextField schTitleTxt;
	
	private JLabel ExerType;
	private JComboBox<String> ChooseExerType; //�ֵ� 4�� � ���� �޺��ڽ�
	
	////4������ ���������� ��
	private JComboBox<String> weightType; //����Ʈ �޺��ڽ�
	private JComboBox<String> aerobicType; //����� �޺��ڽ�
	private JComboBox<String> rowingType; //rowing �޺��ڽ�
	private JComboBox<String> rowingMachineType; //rowing machine �޺��ڽ�
	
	private JLabel DateLabel;
	
	private JLabel schTimeLabel;//�ð�
	private JLabel schHourLabel;//��
	private JTextField schHourTxt;
	private JLabel schMinLabel;//��
	private JTextField schMinTxt;
	
	private JTextField DateTxt;
	
	private JButton register=new JButton("���");
	private JButton modify=new JButton("����");
	private JButton delete=new JButton("����");
	private JButton cancel=new JButton("�ǵ�����");
	private JButton showPlayer=new JButton("�Ʒñ��");
	
	
	ShowPlayerResult spr;
	ScheduleTable st = new ScheduleTable();
	
	int tempYear;
	int tempMonth;
	int tempDay;
	int tempBtnNum;
	//CalanderView cv;
	int scheduleListIndex = 0; //���� ���õ� scheduleList�� index
	
	//�޸���
	Originator originator = new Originator();	
	CareTaker caretaker = new CareTaker();

	
	public ScheduleRegister(int year,int month,int day, int btnNum){ //������ ��� â
		
		super("������ ���");
		setSize(500, 270);
		setLocation(500, 300);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		
	    Container c = getContentPane();
	    c.setLayout(null);
	    
	    DateLabel = new JLabel("��¥ : ");
		DateLabel.setBounds(27, 16, 90, 30);
		c.add(DateLabel);
		
		DateTxt=new JTextField(year+"�� "+month+"�� "+day+"��");
		DateTxt.setBounds(80, 16, 100, 30);
		c.add(DateTxt);
		DateTxt.setEnabled(false);
		
		schTitleLabel = new JLabel("���� : ");
		schTitleLabel.setBounds(207, 16, 90, 30);
		c.add(schTitleLabel);
		
		schTitleTxt=new JTextField();
		schTitleTxt.setColumns(10);
		schTitleTxt.setBounds(280, 21, 200, 21);
		c.add(schTitleTxt);
		
   		 
		///////////�޺��ڽ� ���� start//////////////////////
		
		ChooseExerType=new JComboBox();//�ֵ� � ����
		c.add(ChooseExerType);
		ChooseExerType.setBounds(27, 56, 120, 30);
		ChooseExerType.addItem("� ���� ����");
		ChooseExerType.addItem("����Ʈ");
		ChooseExerType.addItem("�����");
		ChooseExerType.addItem("rowing");
		ChooseExerType.addItem("rowing machine");
		
		ChooseExerType.addItemListener(this);
		
	
		weightType=new JComboBox();
		c.add(weightType);
		weightType.setBounds(27, 96, 150, 30);
		weightType.addItem("����Ʈ ���� ���� ����"); //index 0
		weightType.addItem("bench-press(����)");
		weightType.addItem("pull-up(��)");
		weightType.addItem("squat(�����)");
		weightType.addItem("dumbbell(�̵�)");
		weightType.setVisible(false);
		
		aerobicType=new JComboBox();
		c.add(aerobicType);
		aerobicType.setBounds(27, 96, 150, 30);
		aerobicType.addItem("����� ���� ���� ����");
		aerobicType.addItem("Treadmill");
		aerobicType.addItem("track running");
		aerobicType.setVisible(false);
		
		rowingType=new JComboBox();
		c.add(rowingType);
		rowingType.setBounds(27, 96, 150, 30);
		rowingType.addItem("rowing ���� ���� ����");
		rowingType.addItem("500m");
		rowingType.addItem("1000m");
		rowingType.addItem("2000m");
		rowingType.addItem("interval training");
		rowingType.setVisible(false);
		
		rowingMachineType=new JComboBox();
		c.add(rowingMachineType);
		rowingMachineType.setBounds(27, 96, 150, 30);
		rowingMachineType.addItem("rowingMachine ���� ���� ����");
		rowingMachineType.addItem("500m");
		rowingMachineType.addItem("1000m");
		rowingMachineType.addItem("2000m");
		rowingMachineType.setVisible(false);
		
		
		///////////�޺��ڽ� ���� end//////////////////////
		
		
		
		schTimeLabel=new JLabel("�ð�: ");
		schTimeLabel.setBounds(207, 56, 90, 30);
		c.add(schTimeLabel);
		
		schHourTxt=new JTextField();
		schHourTxt.setColumns(10);
		schHourTxt.setBounds(280, 56, 62, 21);
		c.add(schHourTxt);
		schHourLabel=new JLabel("�� ");
		schHourLabel.setBounds(350,56,90,30);
		c.add(schHourLabel);
		
		schMinTxt=new JTextField();
		schMinTxt.setColumns(10);
		schMinTxt.setBounds(370, 56, 62, 21);
		c.add(schMinTxt);
		
		
		schMinLabel=new JLabel("��");
		schMinLabel.setBounds(440,56,90,30);
		c.add(schMinLabel);
		
		
		//��ư ����
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
		
		//������ ��ư�� ��¥ ����
		tempYear = year;
		tempMonth = month;
		tempDay = day;
		tempBtnNum = btnNum;
		
		this.setVisible(true);
		

		//���� ������ ������ ����Ǿ� �ִ� ���.
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
		   	
	   		//�޸���
	   		originator.setSchedule(sch);
	   		caretaker.addScheduleMemento(originator.createScheduleMemento());
	   	 }
	}


	
	
	public void itemStateChanged(ItemEvent ie){ //�޺��ڽ� ���ÿ� ���� action
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
		

	
	public void actionPerformed(ActionEvent e){ //��ư�� �������� action
       

		if(e.getSource() == register)
		{
			
			String title = schTitleTxt.getText();
			
			String sports = (String)ChooseExerType.getSelectedItem();
			if(sports.equals("� ���� ����")) //�ƹ��͵� ���� ���� ���
				sports = "";
			
			String detailSports ="";
			if(sports.equals("����Ʈ"))
			{
				detailSports = (String)weightType.getSelectedItem();
			}
			else if(sports.equals("�����"))
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
				JOptionPane.showMessageDialog(this, "������ �Է��� �ּ���", "�޽���", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(sports.equals(""))
			{
				JOptionPane.showMessageDialog(this, "� ������ ������ �ּ���", "�޽���", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(detailSports.equals(""))
			{
				JOptionPane.showMessageDialog(this, "� ���λ����� ������ �ּ���", "�޽���", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(timeHour.equals(""))
			{
				JOptionPane.showMessageDialog(this, "�ð��� �Է��� �ּ���", "�޽���", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(timeMinute.equals(""))
			{
				JOptionPane.showMessageDialog(this, "�ð��� �Է��� �ּ���", "�޽���", JOptionPane.INFORMATION_MESSAGE);
			}
			else //��� ��ư�� ������ ���
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
						JOptionPane.showMessageDialog(this, "������ ��ϵǾ����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
						
						Login.cv.calBtn[tempBtnNum+tempDay].setBackground(Color.yellow);
						Login.cv.calBtn[tempBtnNum+tempDay].repaint();
						
						this.dispose();						
						
					}
				}
				else if((st.tableCheck(tempYear, tempMonth, tempDay)==false))
				{
					if(st.tableUpdate(s)>0)
					{
						JOptionPane.showMessageDialog(this, "������ �����Ͽ����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
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
			int deleteCheck = JOptionPane.showConfirmDialog(this, "�������� ���� �Ͻðڽ��ϱ�?", "�޽���", JOptionPane.INFORMATION_MESSAGE);
			if(deleteCheck == 0 )
			{
				schTitleTxt.setText(null);
				ChooseExerType.setSelectedIndex(0);
				schHourTxt.setText(null);
				schMinTxt.setText(null);
				
				if(st.tableDelete(tempYear,tempMonth,tempDay)>0)
				{
					JOptionPane.showMessageDialog(this, "�����Ǿ����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
					Login.cv.calBtn[tempBtnNum+tempDay].setBackground(new JButton().getBackground());
					Login.cv.calBtn[tempBtnNum+tempDay].repaint();
					
					this.dispose();	
				}				
			}

		}
		else if(e.getSource() == cancel){ //�޸��� ����
			originator.restoreScheduleMemento(caretaker.getScheduleMemento(0));
			Schedule sch = new Schedule();
			sch = originator.getSchedule();
			
			//����!!
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
