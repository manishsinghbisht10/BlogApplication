package spring.Zblogapplication.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import spring.Zblogapplication.springbootEntity.Post;

public interface PostService {

	public List<Post> getAllPost();

    public Post getPostById(int id);
    
    public void deletePostById(int Id);
    
    public void savePost(Post blog);

	public Page<Post> getAllPost(int pageNumber, int pageSize);
	
	public Page<Post> search(Pageable pagable,@Param("keyword") String keyword);
	
	public Page<Post> searchASC(Pageable pagable,@Param("keyword") String keyword);
	
	public Page<Post> sortTimeDESC(Pageable pageable);
	
	public Page<Post> sortTimeASC(Pageable pageable);
  
}
