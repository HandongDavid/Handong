package oodp;

import java.sql.*;
import java.util.ArrayList;

public class PlayerResultTable extends DBmanager{ //선수들의 연습 기록을 DB로 다루는 class

	
	public PlayerResultTable()
	{

	}//생성자 끝
	


	@Override
	public int tableInsert(Object a) //훈련기록 추가 
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
	}//훈련기록 추가


	@Override
	public PlayerResult tableSave(int y, int m, int d)//테이블로 부터 찾은 훈련기록을 저장 
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
	public int tableUpdate(Object a) //훈련기록을 수정
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
	}//훈련기록 수정
	
	
	@Override
	public int tableDelete(int y, int m, int d) //훈련기록 삭제
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
	}//스케줄 정보 삭제
	

	@Override
	public boolean tableCheck(int y, int m, int d) //그 달력에 저장한 정보가 이미 있는지 없는지 확인 (중복을 없애기 위해) 
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
	
	
	public ArrayList<PlayerResult> playerResultAll(int year, int month, int day) //해당하는 스케줄에 따른 훈련기록 리스트 불러옴
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
