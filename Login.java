package oodp;

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






import java.util.ArrayList;
import java.util.StringTokenizer;

public class Login extends JFrame implements ActionListener
{

	private JLabel lid;
	private JLabel lpwd;
	private JTextField tfId;
	private JTextField tfPwd;
	private JButton bLogin;
	private JButton bJoin;
	private JPanel loginPanel;
	
	public static CalanderView cv;


	
	private boolean bLoginCheck; //로그인이 성공했는지 안했는지 체크	

	UserTable ut = new UserTable();
	MemRegister mr;
	
	public Login()
	{
		super("Login");
		setSize(280,150);
		setResizable(false);//frame 크기 변경 x
		setLocation(800,450);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	
		
		//login하는데 필요한 창 구현
		loginPanel = new JPanel();
		loginPanel.setLayout(null);
		
		lid = new JLabel("ID");
		lid.setBounds(10,10,80,25);
		loginPanel.add(lid);
		
		lpwd = new JLabel("Pass");
		lpwd.setBounds(10,40,80,25);
		loginPanel.add(lpwd);
		
		tfId = new JTextField(20);
		tfId.setBounds(100,10,160,25);
		loginPanel.add(tfId);
		 
		tfPwd = new JPasswordField(20);
		tfPwd.setBounds(100,40,160,25);
		loginPanel.add(tfPwd);
		 
		
		//login, 회원가입 버튼 만들기
		bJoin = new JButton("회원가입");
		bJoin.setBounds(10,80,100,25);
		loginPanel.add(bJoin);
		
		
		bLogin = new JButton("Login");
		bLogin.setBounds(160,80,100,25);
		loginPanel.add(bLogin);

		
		
		this.add(loginPanel);
		
		setVisible(true);
		
		
		//버튼을 누를때 발생하는 event
		bJoin.addActionListener(this);		
		bLogin.addActionListener(this);

		
	}
	
	public void actionPerformed(ActionEvent e)
	{   
		
		
		if(e.getSource() == bJoin)
		{
			mr = new MemRegister();
			this.dispose();
			
		}
		if(e.getSource() == bLogin)
		{
			isLoginCheck();
		}

	}


	
			
	public void isLoginCheck()//Login 버튼을 누를때 발생하는 일
	{	
		
		if((tfId.getText().equals("")||tfPwd.getText().equals(""))) //id또는 password가 공백인 경우 ~ 로그인 실패
		{
			JOptionPane.showMessageDialog(null, "로그인 실패");
		}
		else if(ut.tableCheck(tfId.getText(), tfPwd.getText()) == true)
		{
			JOptionPane.showMessageDialog(null, "로그인 성공");
			bLoginCheck = true;
			
						
			if(bLoginCheck)
			{
				User user = ut.tableSave(tfId.getText()); //현재 로그인 한 user정보
				SingletonUser curUser = SingletonUser.getSingletonUser();
				curUser.setUser(user);
				cv = new CalanderView();
				this.dispose();
			}
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "로그인 실패");

		}

			

			
	}
					
		
		public static void main(String[] args) 
		{
			
		}
		
}



