package oodp;

import java.sql.*;

public abstract class DBmanager {   //DB총괄 ~ template method pattern

	Connection con;
	Statement st;
	PreparedStatement ps; 
	ResultSet rs;
	
	String url = "jdbc:mysql://localhost:3306/my_db";
	String id = "oodp";
	String pw = "1234";
	
	public DBmanager()
	{
		try
		{
			
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver load success");
			
			try
			{
				con = DriverManager.getConnection(url, id, pw);
				System.out.println("db connect success");				
			}
			catch(SQLException e)
			{
				System.out.println(e);
			}
		}
		catch(ClassNotFoundException e)
		{
			System.out.println(e);
		}		
	}//생성자 끝
	
	
	public void dbClose()
	{
		try{
			if(rs != null) rs.close();
			if(st != null) st.close();
			if(ps != null) ps.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}//dbClose
	
	
	
	//hook method??
	public abstract int tableInsert(Object a);//추가
	public abstract int tableUpdate(Object a);//수정
	
	public abstract Object tableSave(String id); //특정 테이블 정보 저장
	public abstract boolean tableCheck(String id, String pwd); //특정 테이블 정보 중복체크
	
	
	public abstract Object tableSave(int y, int m, int d); //특정 테이블 정보 저장
	public abstract int tableDelete(int y, int m, int d); //특정 테이블 정보 삭제
	public abstract boolean tableCheck(int y, int m, int d); //특정 테이블 정보 중복체크


	
	
	
}