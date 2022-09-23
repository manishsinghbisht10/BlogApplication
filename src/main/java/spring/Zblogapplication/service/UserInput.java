package spring.Zblogapplication.service;

import java.util.List;

import spring.Zblogapplication.springbootEntity.UserData;

public interface UserInput {

	public List<UserData> getAllUserData();

    public UserData getBlogById(int id);
    
    public void deleteById(int Id);
    
    public void save(UserData blog);


   
}
