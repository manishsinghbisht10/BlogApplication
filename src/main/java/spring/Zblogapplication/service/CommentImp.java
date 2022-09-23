package spring.Zblogapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.Zblogapplication.springbootEntity.Comments;
import spring.Zblogapplication.springbootRepository.CommentJpaRepository;

@Service
public class CommentImp implements Comment {

	@Autowired
	CommentJpaRepository data;
	
	@Override
	public void saveComment(Comments com) {
		data.save(com);
	}

	@Override
	public List<Comments> getAllComments() {
		return data.findAll();
	}



	@Override
	public void deleteById(int Id) {
		data.deleteById(Id);
	}

	@Override
	public Comments getCommentById(int id) {
		return data.findById(id).get();
	}
}
