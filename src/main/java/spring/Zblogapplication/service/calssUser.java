package spring.Zblogapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.Zblogapplication.springbootEntity.UserData;
import spring.Zblogapplication.springbootRepository.JPArepository;

@Service
public class calssUser implements UserInput {

	@Autowired
	JPArepository data;
	
	@Override
	public List<UserData> getAllUserData() {
		return data.findAll();
	}

	@Override
	public UserData getBlogById(int id) {
		return data.findById(id).get();
	}

	@Override
	public void deleteById(int Id) {
		 data.deleteById(Id);
	}

	@Override
	public void save(UserData blog) {
		data.save(blog);
	}

}
