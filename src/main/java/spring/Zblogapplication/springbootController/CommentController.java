package spring.Zblogapplication.springbootController;

import java.time.LocalDateTime;

//import org.attoparser.dom.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.Zblogapplication.service.Comment;
import spring.Zblogapplication.service.UserInput;
import spring.Zblogapplication.springbootEntity.Comments;
import spring.Zblogapplication.springbootEntity.UserData;

@Controller
public class CommentController {

	@Autowired
	Comment comment;
	
	@Autowired
	UserInput data;
	
	@PostMapping("/saveComment/{id}")
	public String save(@ModelAttribute Comments obj ,@PathVariable("id") int theId) {
		LocalDateTime datetime = LocalDateTime.now();  
	    obj.setCreated_at(datetime);
		UserData post=new UserData();
		post=data.getBlogById(theId);
		obj.setPostId(post);
		comment.saveComment(obj);
		return "sucess";
	}
	
	@PostMapping("/updateComment/{id}")
	public String updateComment(@ModelAttribute Comments obj,@PathVariable("id") int theId) {
		Comments com=comment.getCommentById(theId);
		com.setName(obj.getName());
		com.setEmail(obj.getEmail());
		com.setComment(obj.getComment());
		comment.saveComment(com);
		return "sucess";
	}
	
	@GetMapping("/update")
	public String update(Model theModel,int id) {
		theModel.addAttribute("obj", comment.getCommentById(id));
		return "update";
	}
	
	@GetMapping("/deleteComment")
	public String deleteComment(@RequestParam("id")int theId,Model theModel) {
		comment.deleteById(theId);
		return "sucess";
	}
}
