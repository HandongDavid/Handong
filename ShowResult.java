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

public class ShowResult extends JFrame implements ActionListener{ //선수 모드에서 저장된 훈련 기록을 보여줌

	PlayerResultTable prt = new PlayerResultTable();
	ArrayList<PlayerResult> playerList = new ArrayList<PlayerResult>();	
	
	private JLabel schTitleLabel;
	private JTextField schTitleTxt;
	private JLabel ExerType;
	private JLabel DateLabel;
	private JTextField DateTxt;

	private JLabel NullCheck; // 리스트가 비었는가
   
	private JButton register = new JButton("등록");
	private JButton mod = new JButton("수정");
	private JButton cancel = new JButton("취소");
   
	User user;
	TrainingResult tr;
	SingletonUser curUser = SingletonUser.getSingletonUser();
   
	public int y, m, d;
   
	public ShowResult(int year,int month,int day){ //훈련 기록을 보여주는 창 생성
      
		super("훈련 결과 보기");
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
          NullCheck = new JLabel("등록된 훈련 결과가 없습니다.");
          NullCheck.setBounds(215, 50, 300, 30);
         c.add(NullCheck);   
       }
          
       else{
          for(int i=0; i<playerList.size(); i++){
        	 if((playerList.get(i).getUser().getName()).equals(curUser.getUser().getName())==true)
        	 {
                 JLabel ShowRe = new JLabel(playerList.get(i).getPMexer()+" "+ playerList.get(i).getPexer()+" "+playerList.get(i).getPhour()+"시간 "+playerList.get(i).getPminute()+"분 "
                		 +playerList.get(i).getPsecond()+"초 "+playerList.get(i).getPnum()+"개 ");
                 ShowRe.setBounds(50, 60+30*i, 500, 30);
                 c.add(ShowRe); 
                 break;
        	 }
          }
       }   
       
       DateLabel = new JLabel("날짜 : ");
      DateLabel.setBounds(27, 16, 90, 30);
      c.add(DateLabel);
      
      DateTxt=new JTextField(year+"년 "+month+"월 "+day+"일");
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