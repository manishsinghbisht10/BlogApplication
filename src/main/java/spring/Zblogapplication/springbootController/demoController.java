package spring.Zblogapplication.springbootController;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.Zblogapplication.service.DataTags;
import spring.Zblogapplication.service.UserInput;
import spring.Zblogapplication.springbootEntity.Tags;
import spring.Zblogapplication.springbootEntity.UserData;
import spring.Zblogapplication.springbootRepository.TagsRepository;

@Controller
public class demoController {
	
	@Autowired
	 UserInput service;
	
	@Autowired
	 DataTags data;
	
	@Autowired
	TagsRepository repo;
	
	@GetMapping("/showAddForm")
	public String showAddForm(Model theModel) {
		UserData obj=new UserData();
		theModel.addAttribute("object", obj);
		return "addForm";
	}
	
	@PostMapping("/saveData")
	public String saveData(@ModelAttribute("object") UserData post,@RequestParam("tag") String s) {
		
		String str[]=s.split(",");
		List<Tags> list=new ArrayList<>();
		for(int i=0;i<str.length;i++) {
			Tags tempTag=new Tags();
			tempTag=repo.findByName(str[i]);
			if(tempTag==null) {
				Tags newTag=new Tags();
				newTag.setName(str[i]);
				data.saveTags(newTag);
				list.add(newTag);
			}else {
				list.add(tempTag);
			}
		} 
		//UserData tempPost=new UserData();
		post.setTags(list);	
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
	
	@GetMapping("/updateData{id}")
	public String updateData(@RequestParam("id") int theId,Model theModel) {
		
		UserData post=service.getBlogById(theId);
		List<Tags> tag=post.getTags();
		System.out.println(tag.size());
		String str="";
		for(int i=0;i<tag.size();i++)str=str+tag.get(i).getName()+",";
		theModel.addAttribute("object",post);
		theModel.addAttribute("tag",str);
		
		return "addForm";
	}
	@PostMapping("/search")
	public String search(@RequestParam("search") String searchInput) {
		return "sucess";
	}
	
	@GetMapping("/delete")
	public String deleteData(@RequestParam("id")int theId,Model theModel) {
		service.deleteById(theId);
		return "getData";
	}
}

