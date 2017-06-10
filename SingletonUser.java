package oodp;

public class SingletonUser {
	
	private static SingletonUser SINGLETON_CLASS_INSTANCE;
	
	public User user;
	
	private SingletonUser(){}
	
	public static SingletonUser getSingletonUser()
	{
		if(SINGLETON_CLASS_INSTANCE == null){
			SINGLETON_CLASS_INSTANCE = new SingletonUser();
			
		}
		return SINGLETON_CLASS_INSTANCE;
	}
	
	public User getUser(){
		return user;
	}
	
	public void setUser(User user){
		this.user = user;
	}

}
