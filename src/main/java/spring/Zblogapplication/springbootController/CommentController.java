package spring.Zblogapplication.springbootController;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.Zblogapplication.service.CommentService;
import spring.Zblogapplication.service.PostService;
import spring.Zblogapplication.springbootEntity.Comments;
import spring.Zblogapplication.springbootEntity.Post;

@Controller
public class CommentController {

	@Autowired
	CommentService commentService;
	
	@Autowired
	PostService postService;
	
	@PostMapping("/saveComment/{id}")
	public String save(@ModelAttribute Comments comment ,@PathVariable("id") int theId) {
		LocalDateTime datetime = LocalDateTime.now();  
	    comment.setCreated_at(datetime);
		Post post=new Post();
		post=postService.getPostById(theId);
		comment.setPostId(post);
		commentService.saveComment(comment);
		return "sucess";
	}
	
	@PostMapping("/updateComment/{id}")
	public String updateComment(@ModelAttribute Comments obj,@PathVariable("id") int theId) {
		Comments com=commentService.getCommentById(theId);
		com.setName(obj.getName());
		com.setEmail(obj.getEmail());
		com.setComment(obj.getComment());
		commentService.saveComment(com);
		return "sucess";
	}
	
	@GetMapping("/update")
	public String update(Model theModel,int id) {
		theModel.addAttribute("obj", commentService.getCommentById(id));
		return "update";
	}
	
	@GetMapping("/deleteComment")
	public String deleteComment(@RequestParam("id")int theId,Model theModel) {
		commentService.deleteById(theId);
		return "sucess";
	}
}
