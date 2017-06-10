package oodp;

import java.sql.*;

public class ScheduleTable extends DBmanager{  //�������� DB�� �ٷ�� class

	
	public ScheduleTable()
	{

	}//������ ��
	
	
	@Override
	public int tableInsert(Object a)//���� �߰� 
	{
		Schedule sch = (Schedule)a;
		int result = 0;
		try
		{

			ps = con.prepareStatement("insert into schedule values(?,?,?,?,?,?,?,?)");
			ps.setInt(1, sch.getYear());
			ps.setInt(2, sch.getMonth());
			ps.setInt(3, sch.getDay());
			ps.setString(4, sch.getTitle());
			ps.setString(5, sch.getSports());	
			ps.setString(6, sch.getDetailSports());	
			ps.setString(7, sch.getHour());	
			ps.setString(8, sch.getMinute());

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
	}//���� �߰�

	@Override
	public Schedule tableSave(int y, int m, int d)//���̺�� ���� ã�� ���������� ���� 
	{
		Schedule sch = new Schedule();
		try
		{
			
			st = con.createStatement();
			rs = st.executeQuery("select * from schedule");

			while(rs.next())
			{
				if((y == rs.getInt(1) && m == rs.getInt(2) && d == rs.getInt(3)) ==true)
				{
					sch.setYear(rs.getInt(1));
					sch.setMonth(rs.getInt(2));
					sch.setDay(rs.getInt(3));
					sch.setTitle(rs.getString(4));
					sch.setSports(rs.getString(5));
					sch.setDetailSports(rs.getString(6));
					sch.setHour(rs.getString(7));
					sch.setMinute(rs.getString(8));					
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
	
		return sch;
	}//
	
	
	@Override
	public int tableUpdate(Object a)//�������� ����
	{ 
		Schedule sch = (Schedule)a;
		int result = 0;
		
		try
		{
	
			ps = con.prepareStatement("update schedule set TITLE =?, SPORTS=?, DETAILSPORTS=?, HOUR=?, MINUTE=? where YEAR=? and MONTH=? and DAY=?");
			
			ps.setString(1, sch.getTitle());
			ps.setString(2, sch.getSports());
			ps.setString(3, sch.getDetailSports());
			ps.setString(4, sch.getHour());
			ps.setString(5, sch.getMinute());	

			ps.setInt(6, sch.getYear());
			ps.setInt(7, sch.getMonth());
			ps.setInt(8, sch.getDay());
			
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
	}//������ ����
	
	
	@Override
	public int tableDelete(int y, int m, int d)//������ ���� ���� 
	{
		int result = 0;
		try
		{

			ps = con.prepareStatement("delete from schedule where YEAR=? and MONTH=? and DAY=?");
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
			rs = st.executeQuery("select * from schedule");
			
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

	

	@Override
	public Object tableSave(String id) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean tableCheck(String id, String pwd) {
		// TODO Auto-generated method stub
		return false;
	}


	
	
}
