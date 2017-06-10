package oodp;

import java.sql.*;

public class UserTable extends DBmanager{   //사용자 정보를 DB로 다루는 class
	
	public UserTable()
	{
		
	}//생성자
	
	
	@Override
	public int tableInsert(Object a)//회원가입 ~ 회원정보 테이블에 추가
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
	}//회원가입 끝

	
	@Override
	public boolean tableCheck(String id, String pwd)//아이디 비번이 맞는지 확인..
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
	}//아이디 비번 맞는지 확인하는 method
	
	
	
	@Override
	public User tableSave(String id)//로그인 하는 회원 정보를 저장
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
	}//로그인 하는 회원 정보를 저장 끝


	
	
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
		return 0; //특정 테이블 정보 저장
	}
	
	@Override
	public int tableDelete(int y, int m, int d){
		// TODO Auto-generated method stub
		return 0;
	}

	
}