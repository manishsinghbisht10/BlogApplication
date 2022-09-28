package spring.Zblogapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import spring.Zblogapplication.springbootEntity.Post;
import spring.Zblogapplication.springbootRepository.PostJpaRepository;

@Service
public class PostServiceImp implements PostService {

	@Autowired
	PostJpaRepository service;
	
	@Override
	public Page<Post> getAllPost(int pageNumber,int pageSize) {
		PageRequest p=PageRequest.of((pageNumber-1),pageSize);
		Page<Post> pagePost=this.service.findAll(p);
		return pagePost;
	}
	
	public Page<Post> sortPostDESC(int pageNumber,int pageSize) {
		PageRequest p=PageRequest.of((pageNumber-1),pageSize,Sort.by("id").ascending());
		Page<Post> pagePost=this.service.findAll(p);
		return pagePost;
	}

	@Override
	public Post getPostById(int id) {
		return service.findById(id).get();
	}

	@Override
	public void deletePostById(int Id) {
		service.deleteById(Id);
	}

	@Override
	public void savePost(Post blog) {
		service.save(blog);
	}

	@Override
	public List<Post> getAllPost() {
		return service.findAll();
	}

	@Override
	public Page<Post> sortPostASC(int pageNumber, int pageSize) {
		PageRequest p=PageRequest.of((pageNumber-1),pageSize,Sort.by("id").descending());
		Page<Post> pagePost=this.service.findAll(p);
		return pagePost;
	}

}
