package spring.Zblogapplication.service;

import java.util.List;

import org.springframework.data.domain.Page;

import spring.Zblogapplication.springbootEntity.Post;

public interface PostService {

	public List<Post> getAllPost();

    public Post getPostById(int id);
    
    public void deletePostById(int Id);
    
    public void savePost(Post blog);

	public Page<Post> getAllPost(int pageNumber, int pageSize);
	
	public Page<Post> sortPostASC(int pageNumber,int pageSize) ;

	public Page<Post> sortPostDESC(int pageNumber, int pageSize);
   
}
