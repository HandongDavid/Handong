package oodp;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ShowResult extends JFrame implements ActionListener{ //���� ��忡�� ����� �Ʒ� ����� ������

	PlayerResultTable prt = new PlayerResultTable();
	ArrayList<PlayerResult> playerList = new ArrayList<PlayerResult>();	
	
	private JLabel schTitleLabel;
	private JTextField schTitleTxt;
	private JLabel ExerType;
	private JLabel DateLabel;
	private JTextField DateTxt;

	private JLabel NullCheck; // ����Ʈ�� ����°�
   
	private JButton register = new JButton("���");
	private JButton mod = new JButton("����");
	private JButton cancel = new JButton("���");
   
	User user;
	TrainingResult tr;
	SingletonUser curUser = SingletonUser.getSingletonUser();
   
	public int y, m, d;
   
	public ShowResult(int year,int month,int day){ //�Ʒ� ����� �����ִ� â ����
      
		super("�Ʒ� ��� ����");
		setSize(600, 330);
		setLocation(600, 300);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
      
		y = year;
		m = month;
		d = day;
      
		
		playerList = prt.playerResultAll(y, m, d);
      
		Container c = getContentPane();
		c.setLayout(null);
       
       if(playerList.size()==0){
          NullCheck = new JLabel("��ϵ� �Ʒ� ����� �����ϴ�.");
          NullCheck.setBounds(215, 50, 300, 30);
         c.add(NullCheck);   
       }
          
       else{
          for(int i=0; i<playerList.size(); i++){
        	 if((playerList.get(i).getUser().getName()).equals(curUser.getUser().getName())==true)
        	 {
                 JLabel ShowRe = new JLabel(playerList.get(i).getPMexer()+" "+ playerList.get(i).getPexer()+" "+playerList.get(i).getPhour()+"�ð� "+playerList.get(i).getPminute()+"�� "
                		 +playerList.get(i).getPsecond()+"�� "+playerList.get(i).getPnum()+"�� ");
                 ShowRe.setBounds(50, 60+30*i, 500, 30);
                 c.add(ShowRe); 
                 break;
        	 }
          }
       }   
       
       DateLabel = new JLabel("��¥ : ");
      DateLabel.setBounds(27, 16, 90, 30);
      c.add(DateLabel);
      
      DateTxt=new JTextField(year+"�� "+month+"�� "+day+"��");
       DateTxt.setBounds(80, 16, 100, 30);
       c.add(DateTxt);
       DateTxt.setEnabled(false);
      
      register.setBounds(120, 232, 80, 40);
      c.add(register);
      
      mod.setBounds(260, 232, 80, 40);
      c.add(mod);
      
      cancel.setBounds(400, 232, 80, 40);
      c.add(cancel);
      
      register.addActionListener(this);
      mod.addActionListener(this);
      cancel.addActionListener(this);
      
      this.setVisible(true);
   }
   
   public static void main(String[] args) {
      //
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      
      if(e.getSource() == register)
      {
         tr = new TrainingResult(y, m, d);

      }
      else if(e.getSource() == mod)
      {
         this.dispose();
      }
      else if(e.getSource() == cancel)
      {

         this.dispose();
      }
      
   }

}