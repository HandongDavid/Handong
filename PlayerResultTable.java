package oodp;

import java.sql.*;
import java.util.ArrayList;

public class PlayerResultTable extends DBmanager{ //�������� ���� ����� DB�� �ٷ�� class

	
	public PlayerResultTable()
	{

	}//������ ��
	


	@Override
	public int tableInsert(Object a) //�Ʒñ�� �߰� 
	{
		PlayerResult pr = (PlayerResult)a;
		int result = 0;
		try
		{

			ps = con.prepareStatement("insert into playerRecord values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, pr.getYear());
			ps.setInt(2, pr.getMonth());
			ps.setInt(3, pr.getDay());
			ps.setString(4, pr.getUser().getName());
			ps.setString(5, pr.getUser().getCm());	
			ps.setString(6, pr.getUser().getKg());	
			ps.setString(7, pr.getPMexer());	
			ps.setString(8, pr.getPexer());
			ps.setString(9, pr.getPnum());
			ps.setString(10, pr.getPhour());
			ps.setString(11, pr.getPminute());
			ps.setString(12, pr.getPsecond());
			ps.setString(13, pr.getTime());

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
	}//�Ʒñ�� �߰�


	@Override
	public PlayerResult tableSave(int y, int m, int d)//���̺�� ���� ã�� �Ʒñ���� ���� 
	{
		PlayerResult pr = new PlayerResult();
		User user = new User();
		
		try
		{
			st = con.createStatement();
			rs = st.executeQuery("select * from playerRecord");

			while(rs.next())
			{
				if((y == rs.getInt(1) && m == rs.getInt(2) && d == rs.getInt(3)) ==true)
				{
					pr.setYear(rs.getInt(1));
					pr.setMonth(rs.getInt(2));
					pr.setDay(rs.getInt(3));
					
					user.setName((rs.getString(4)));
					user.setCm(rs.getString(5));
					user.setKg(rs.getString(6));
					
					pr.setUser(user);
					pr.setPMexer(rs.getString(7));
					pr.setPexer(rs.getString(8));
					pr.setPnum(rs.getString(9));
					pr.setPhour(rs.getString(10));		
					pr.setPminute(rs.getString(11));
					pr.setPsecond(rs.getString(12));
					pr.setTime(rs.getString(10),rs.getString(11),rs.getString(12));
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
	
		return pr;
	}//
	

	@Override
	public int tableUpdate(Object a) //�Ʒñ���� ����
	{ 
		PlayerResult pr = (PlayerResult)a;
		int result = 0;
		
		try
		{
	
			ps = con.prepareStatement("update schedule set MEXER=?, EXER=?, NUM=?, HOUR=?, MINUTE=?, SECOND=?, TIME=? where YEAR=? and MONTH=? and DAY=?");

			ps.setString(1, pr.getPMexer());
			ps.setString(2, pr.getPexer());
			ps.setString(3, pr.getPnum());
			ps.setString(4, pr.getPhour());
			ps.setString(5, pr.getPminute());
			ps.setString(6, pr.getPsecond());	
			ps.setString(7, pr.getTime());	

			ps.setInt(8, pr.getYear());
			ps.setInt(9, pr.getMonth());
			ps.setInt(10, pr.getDay());
			
			result= ps.executeUpdate();

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
	}//�Ʒñ�� ����
	
	
	@Override
	public int tableDelete(int y, int m, int d) //�Ʒñ�� ����
	{
		int result = 0;
		try
		{
			ps = con.prepareStatement("delete from playerRecord where YEAR=? and MONTH=? and DAY=?");
			ps.setInt(1, y);
			ps.setInt(2, m);
			ps.setInt(3, d);
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
	}//������ ���� ����
	

	@Override
	public boolean tableCheck(int y, int m, int d) //�� �޷¿� ������ ������ �̹� �ִ��� ������ Ȯ�� (�ߺ��� ���ֱ� ����) 
	{
		boolean check = true;
		try
		{
			
			st = con.createStatement();
			rs = st.executeQuery("select * from playerRecord");
			
			while(rs.next())
			{
				if((y == rs.getInt(1) && m == rs.getInt(2) && d == rs.getInt(3)) ==true)
				{
					check = false;					
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
		
		return check;
	}//
	
	
	public ArrayList<PlayerResult> playerResultAll(int year, int month, int day) //�ش��ϴ� �����ٿ� ���� �Ʒñ�� ����Ʈ �ҷ���
	{
		ArrayList<PlayerResult> playerList = new ArrayList<PlayerResult>();
		PlayerResult pr = new PlayerResult();
		User user = new User();
		
		boolean check = true;
		try
		{
			
			st = con.createStatement();
			rs = st.executeQuery("select * from playerRecord order by NAME");
			
			while(rs.next())
			{
				if((year == rs.getInt(1) && month == rs.getInt(2) && day == rs.getInt(3)) ==true)
				{
					user.setName(rs.getString(4));
					user.setCm(rs.getString(5));
					user.setKg(rs.getString(6));
					pr.setUser(user);
					pr.setPMexer(rs.getString(7));
					pr.setPexer(rs.getString(8));
					pr.setPnum(rs.getString(9));
					pr.setPhour(rs.getString(10));
					pr.setPminute(rs.getString(11));
					pr.setPsecond(rs.getString(12));
					pr.setTime(rs.getString(10),rs.getString(11),rs.getString(12));
					
					playerList.add(pr);
					
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
		
		return playerList;
	}//

	
	@Override
	public Object tableSave(String id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean tableCheck(String id, String pwd) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
