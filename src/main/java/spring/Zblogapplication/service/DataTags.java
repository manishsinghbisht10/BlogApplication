package spring.Zblogapplication.service;

import java.util.List;

import spring.Zblogapplication.springbootEntity.Tags;


public interface DataTags {

	public List<Tags> getAllTags();
    
    public void deleteById(int Id);
    
	public void saveTags(Tags com);
	
	public Tags getBlogById(int id);
 
}
