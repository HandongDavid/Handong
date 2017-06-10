package oodp;

import java.sql.*;

public class UserTable extends DBmanager{   //����� ������ DB�� �ٷ�� class
	
	public UserTable()
	{
		
	}//������
	
	
	@Override
	public int tableInsert(Object a)//ȸ������ ~ ȸ������ ���̺� �߰�
	{
		User user = (User)a;
		int result = 0;
		try
		{
			ps = con.prepareStatement("insert into member values(?,?,?,?,?,?)");
			ps.setString(1, user.getId());
			ps.setString(2, user.getPwd());
			ps.setString(3, user.getName());
			ps.setInt(4, Integer.parseInt(user.getCm()));
			ps.setInt(5, Integer.parseInt(user.getKg()));
			ps.setString(6, user.getPosition());	
			
			result = ps.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		finally
		{
			dbClose();
		}
		
		return result;
	}//ȸ������ ��

	
	@Override
	public boolean tableCheck(String id, String pwd)//���̵� ����� �´��� Ȯ��..
	{
		boolean a = false;
		
		try
		{
			st = con.createStatement();
			rs = st.executeQuery("select *from member");
			
			while(rs.next())
			{
				if((id.equals(rs.getString(1)) && pwd.equals(rs.getString(2))) == true)
				{				
					a = true;
					break;
				}
					
			}
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		finally
		{
			dbClose();
		}
		
		return a;
	}//���̵� ��� �´��� Ȯ���ϴ� method
	
	
	
	@Override
	public User tableSave(String id)//�α��� �ϴ� ȸ�� ������ ����
	{
		
		User user = new User();
		try
		{
			st = con.createStatement();
			rs = st.executeQuery("select * from member");
			
			while(rs.next())
			{
				if((id.equals(rs.getString(1))) == true)
				{
					user.setId(rs.getString(1));
					user.setPwd(rs.getString(2));
					user.setName(rs.getString(3));
					user.setCm(Integer.toString(rs.getInt(4)));
					user.setKg(Integer.toString(rs.getInt(5)));
					user.setPosition(rs.getString(6));
					
				}

			}

		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		finally
		{
			dbClose();
		}
	
		return user;
	}//�α��� �ϴ� ȸ�� ������ ���� ��


	
	
	@Override
	public int tableUpdate(Object a) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean tableCheck(int y, int m, int d){
		// TODO Auto-generated method stub
		return false ;
	}
	
	@Override
	public Object tableSave(int y, int m, int d){
		// TODO Auto-generated method stub
		return 0; //Ư�� ���̺� ���� ����
	}
	
	@Override
	public int tableDelete(int y, int m, int d){
		// TODO Auto-generated method stub
		return 0;
	}

	
}