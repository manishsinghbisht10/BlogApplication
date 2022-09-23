package spring.Zblogapplication.springbootController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.Zblogapplication.service.UserInput;
import spring.Zblogapplication.springbootEntity.UserData;

@Controller
public class demoController {
	@Autowired
	private UserInput service;
	
	@GetMapping("/showAddForm")
	public String showAddForm(Model theModel) {
		UserData obj=new UserData();
		theModel.addAttribute("object", obj);
		return "addForm";
	}
	
	@PostMapping("/saveData")
	public String saveData(@ModelAttribute("object") UserData post) {
		service.save(post);
		return "sucess";
	}
	
	@GetMapping("/getData")
	public String getData(Model theModel) {
		theModel.addAttribute("data",service.getAllUserData());
		return "getData";
	}
	
	@PostMapping("/readData")
	public String readDataById(Model theModel,int id) {
		theModel.addAttribute("BlogPost",service.getBlogById(id));
		
		return "readData";
	}
	
	@GetMapping("/updateData")
	public String updateData(@RequestParam("id") int theId,Model theModel) {
		theModel.addAttribute("object",service.getBlogById(theId));
		return "addForm";
	}
	
	@GetMapping("/delete")
	public String deleteData(@RequestParam("id")int theId,Model theModel) {
		service.deleteById(theId);
		return "getData";
	} 
}

