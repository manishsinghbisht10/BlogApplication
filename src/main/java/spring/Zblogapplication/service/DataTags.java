package spring.Zblogapplication.service;

import java.util.List;

import spring.Zblogapplication.springbootEntity.Comments;
import spring.Zblogapplication.springbootEntity.Tags;

public interface DataTags {

	public List<Tags> getAllComments();

    public Comments getCommentById(int id);
    
    public void deleteById(int Id);
    
	public void saveComment(Tags com);
 
}
