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


	
	private boolean bLoginCheck; //�α����� �����ߴ��� ���ߴ��� üũ	

	UserTable ut = new UserTable();
	MemRegister mr;
	
	public Login()
	{
		super("Login");
		setSize(280,150);
		setResizable(false);//frame ũ�� ���� x
		setLocation(800,450);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	
		
		//login�ϴµ� �ʿ��� â ����
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
		 
		
		//login, ȸ������ ��ư �����
		bJoin = new JButton("ȸ������");
		bJoin.setBounds(10,80,100,25);
		loginPanel.add(bJoin);
		
		
		bLogin = new JButton("Login");
		bLogin.setBounds(160,80,100,25);
		loginPanel.add(bLogin);

		
		
		this.add(loginPanel);
		
		setVisible(true);
		
		
		//��ư�� ������ �߻��ϴ� event
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


	
			
	public void isLoginCheck()//Login ��ư�� ������ �߻��ϴ� ��
	{	
		
		if((tfId.getText().equals("")||tfPwd.getText().equals(""))) //id�Ǵ� password�� ������ ��� ~ �α��� ����
		{
			JOptionPane.showMessageDialog(null, "�α��� ����");
		}
		else if(ut.tableCheck(tfId.getText(), tfPwd.getText()) == true)
		{
			JOptionPane.showMessageDialog(null, "�α��� ����");
			bLoginCheck = true;
			
						
			if(bLoginCheck)
			{
				User user = ut.tableSave(tfId.getText()); //���� �α��� �� user����
				SingletonUser curUser = SingletonUser.getSingletonUser();
				curUser.setUser(user);
				cv = new CalanderView();
				this.dispose();
			}
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "�α��� ����");

		}

			

			
	}
					
		
		public static void main(String[] args) 
		{
			
		}
		
}



