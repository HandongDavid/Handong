package oodp;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField; 
import javax.swing.JOptionPane;





public class MemRegister extends JFrame implements ActionListener
{

	private JTextField userTid;
	private JTextField userTpwd;
	private JTextField userTname;
	private JTextField userTcm;
	private JTextField userTkg;

	private JButton register = new JButton("등록");
	private JButton cancel = new JButton("취소");
	
	private JRadioButton managerRadio;
	private JRadioButton playerRadio;
	private ButtonGroup buttonGroup = new ButtonGroup();
	
	Login lg;
	UserTable ut = new UserTable();//user의 정보를 저장하는 table
	
	public static void main(String args[])
	{
		MemRegister mem = new MemRegister();

	}
	
	public MemRegister()//회원가입 창을 만든다
	{
		super("회원 등록");
		setSize(450, 260);
		setLocation(500, 300);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	
		
	    Container contentPane = getContentPane();
	    contentPane.setLayout(null);
		
		
		//Label 추가
		JLabel userLid = new JLabel("ID:");
		userLid.setBounds(27, 16, 90, 30);
		contentPane.add(userLid);
		
		JLabel userLpwd = new JLabel("Password:");
		userLpwd.setBounds(207, 16, 90, 30);
		contentPane.add(userLpwd);
		
		JLabel userLname = new JLabel("이름 :");
		userLname.setBounds(27, 56, 90, 30);
		contentPane.add(userLname);
		
		JLabel userLcm = new JLabel("키 (cm):");
		userLcm.setBounds(27, 96, 90, 30);
		contentPane.add(userLcm);
		
		JLabel userLkg = new JLabel("몸무게(kg):");
		userLkg.setBounds(207, 96, 90, 30);
		contentPane.add(userLkg);
		
		//TextField 추가
		userTid = new JTextField();
		userTid.setColumns(10);
		userTid.setBounds(97, 21, 62, 21);
		contentPane.add(userTid);
	
		userTpwd = new JTextField();
		userTpwd.setColumns(10);
		userTpwd.setBounds(280, 21, 62, 21);
		contentPane.add(userTpwd);
		
		userTname = new JTextField();
		userTname.setColumns(10);
		userTname.setBounds(97, 61, 62, 21);
		contentPane.add(userTname);
		
		userTcm = new JTextField();
		userTcm .setColumns(10);
		userTcm .setBounds(97, 101, 62, 21);
		contentPane.add(userTcm);

		userTkg = new JTextField();
		userTkg .setColumns(10);
		userTkg .setBounds(280, 101, 62, 21);
		contentPane.add(userTkg);

		//등록, 취소 Button 추가
		register.setBounds(97, 150, 97, 40);
		contentPane.add(register);
		
		cancel.setBounds(280, 150, 97, 40);
		contentPane.add(cancel);
		
		//RadioButton 추가
		JLabel position = new JLabel("담당 :");
		position.setBounds(207, 56, 90, 30);
		contentPane.add(position);
		
		managerRadio = new JRadioButton("코치");
		managerRadio.setBounds(270, 60, 70, 23);
		
		playerRadio = new JRadioButton("선수");
		playerRadio.setBounds(340, 60, 70, 23);
		
		buttonGroup.add(managerRadio);
		buttonGroup.add(playerRadio);
		
		contentPane.add(managerRadio);
		contentPane.add(playerRadio);
		
		
		register.addActionListener(this);
		cancel.addActionListener(this);
		
		
		
		setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		//코치인지 선수인지 ~ 저장하는 변수
		String positionText = null; 
		boolean  StringCheck = true;
		
		if(e.getSource() == register)
		{
			String userId = userTid.getText();
			String userPwd = userTpwd.getText();
			String userName = userTname.getText();
			
			if(managerRadio.isSelected())
			{
				positionText = "코치";
			}
			else if(playerRadio.isSelected())
			{
				positionText = "선수";
			}
			
			
			String userCm = userTcm.getText();
			String userKg = userTkg.getText();
			
			if(userId.equals(""))
			{
				JOptionPane.showMessageDialog(this, "Id를 입력해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(userPwd.equals(""))
			{
				JOptionPane.showMessageDialog(this, "password를 입력해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(userName.equals(""))
			{
				JOptionPane.showMessageDialog(this, "이름을 입력해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(userCm.equals(""))
			{
				JOptionPane.showMessageDialog(this, "키(cm)를 입력해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(userKg.equals(""))
			{
				JOptionPane.showMessageDialog(this, "몸무게(kg)를 입력해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(!(managerRadio.isSelected())&&!(playerRadio.isSelected()))
			{
	            JOptionPane.showMessageDialog(this, "포지션을 선택해주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
	         }
			else
			{
				//회원 가입을 성공한 경우
				User user = new User();

				user.setId(userId);
				user.setPwd(userPwd);
				user.setName(userName);
				user.setCm(userCm);
				user.setKg(userKg);
				user.setPosition(positionText);
				
				if(ut.tableInsert(user) >0)
				{

					JOptionPane.showMessageDialog(this, "회원이 등록되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);

					lg = new Login();
					this.dispose();
				}
				
			}
		}
		if(e.getSource() == cancel)
		{
			lg = new Login();
			this.dispose();
			
		}
		
	}
 
}

