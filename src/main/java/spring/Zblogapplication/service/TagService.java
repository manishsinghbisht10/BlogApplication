package spring.Zblogapplication.service;

import java.util.List;

import spring.Zblogapplication.springbootEntity.Tag;


public interface TagService {

	public List<Tag> getAllTags();
    
    public void deleteTagById(int Id);
    
	public void saveTag(Tag com);
	
	public Tag getPostById(int id);
 
}
