package spring.Zblogapplication.springbootController;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.Zblogapplication.service.TagService;
import spring.Zblogapplication.service.PostService;
import spring.Zblogapplication.springbootEntity.Tag;
import spring.Zblogapplication.springbootEntity.Post;
import spring.Zblogapplication.springbootRepository.PostJpaRepository;
import spring.Zblogapplication.springbootRepository.TagsJpaRepository;

@Controller
public class PostController {
	
	@Autowired
	 PostService service;
	
	@Autowired
	 TagService data;
	
	@Autowired
	
	TagsJpaRepository repo;
	
	@Autowired
	PostJpaRepository postRepo;
	
	@GetMapping("/showAddForm")
	public String showAddForm(Model theModel) {
		Post post=new Post();
		theModel.addAttribute("object", post);
		return "addForm";
	}
	
	@PostMapping("/saveData")
	public String saveData(@ModelAttribute("object") Post post,@RequestParam("tag") String s) {
		LocalDateTime datetime = LocalDateTime.now();  
	    post.setCreatedAt(datetime);
		String str[]=s.split(",");
		List<Tag> list=new ArrayList<>();
		for(int i=0;i<str.length;i++) {
			Tag tempTag=new Tag();
			tempTag=repo.findByName(str[i]);
			if(tempTag==null) {
				Tag newTag=new Tag();
				newTag.setName(str[i]);
				data.saveTag(newTag);
				list.add(newTag);
			}else {
				list.add(tempTag);
			}
		} 
		post.setTags(list);	
		service.savePost(post);
		return "redirect:/getDataPagination";
	}
	
//	@GetMapping("/sortPost/{pageNumber}")
//	public String sortPost(@PathVariable("pageNumber") int pageNumber,@RequestParam("object") int val, Model theModel ) {
//		if(val==1) {
//			Page<Post>post=service.sortPostDES(pageNumber,4);
//			theModel.addAttribute("post",post);
//			theModel.addAttribute("currentPage", pageNumber);
//			theModel.addAttribute("totalPages", post.getTotalPages());
//			theModel.addAttribute("data",service.getAllPost());
//			return "getData";
//		}
//		else {
//			Page<Post>post=service.getAllPost(pageNumber,4);
//			theModel.addAttribute("post",post);
//			theModel.addAttribute("currentPage", pageNumber);
//			theModel.addAttribute("totalPages", post.getTotalPages());
//			theModel.addAttribute("data",service.getAllPost());
//			return "getData";
//		}
//	}
	
	//user calling this//1 is old
	@GetMapping("/getDataPagination")
	public String getData (@RequestParam(value="pageNumber",defaultValue = "1", required = false)int pageNumber,
			@RequestParam(value="object",defaultValue = "0",required = false)int val,
			@RequestParam(value="search", defaultValue = "empty",required = false)String search,Model theModel){
			if(search.equals("empty")&&val==2) {//new
					Pageable pageable = PageRequest.of(pageNumber-1, 4);
					Page<Post>tempPost = postRepo.sortTimeASC(pageable);
					List<Post>post=tempPost.getContent();
					theModel.addAttribute("post",post);
					theModel.addAttribute("currentPage", pageNumber);
					theModel.addAttribute("totalPages", tempPost.getTotalPages());
					theModel.addAttribute("value", 2);
					theModel.addAttribute("searchVal","empty");
					return "getData";
			}
			else if(search.equals("empty")&&val==1){  //old
					Pageable pageable = PageRequest.of(pageNumber-1, 4);
					Page<Post>tempPost = postRepo.sortTimeDESC(pageable);
					List<Post>post=tempPost.getContent();
					theModel.addAttribute("post",post);
					theModel.addAttribute("currentPage", pageNumber);
					theModel.addAttribute("totalPages", tempPost.getTotalPages());
					theModel.addAttribute("value", 1);
					theModel.addAttribute("searchVal","empty");
					return "getData";
			}
			//new
			else if(!search.equals("empty")&&val==2) {
				Pageable pageable = PageRequest.of(pageNumber-1, 4);
				Page<Post>tempPost = postRepo.searchASC(pageable,search);
				List<Post>post=tempPost.getContent();
				theModel.addAttribute("post",post);
				theModel.addAttribute("currentPage", pageNumber);
				theModel.addAttribute("totalPages", tempPost.getTotalPages());
				theModel.addAttribute("value",5);
				theModel.addAttribute("searchVal",search);
				return "getData";
			}
			//old
			else if(!search.equals("empty")&&val==1) {
				Pageable pageable = PageRequest.of(pageNumber-1, 4);
				Page<Post>tempPost = postRepo.search(pageable,search);
				List<Post>post=tempPost.getContent();
				theModel.addAttribute("post",post);
				theModel.addAttribute("currentPage", pageNumber);
				theModel.addAttribute("totalPages", tempPost.getTotalPages());
				theModel.addAttribute("value",4);
				theModel.addAttribute("searchVal",search);
				return "getData";
			}
			
			else if(!search.equals("empty")) {
				Pageable pageable = PageRequest.of(pageNumber-1, 4);
				Page<Post>tempPost = postRepo.search(pageable,search);
				List<Post>post=tempPost.getContent();
				theModel.addAttribute("post",post);
				theModel.addAttribute("currentPage", pageNumber);
				theModel.addAttribute("totalPages", tempPost.getTotalPages());
				theModel.addAttribute("value",3);
				theModel.addAttribute("searchVal",search);
				return "getData";
			}
			
			else {
				Page<Post>post=service.getAllPost(pageNumber,4);
				theModel.addAttribute("post",post);
				theModel.addAttribute("currentPage", pageNumber);
				theModel.addAttribute("totalPages", post.getTotalPages());
				theModel.addAttribute("totalItems",post.getTotalElements());
				theModel.addAttribute("value", 0);
				theModel.addAttribute("searchVal","empty");
				return "getData";
			}
		}
	
//	@GetMapping("/getData")
//	public String getData(Model theModel) {
//		theModel.addAttribute("data",service.getAllPost());
//		return "getData";
//	}
	
	
	@PostMapping("/readData")
	public String readDataById(Model theModel,int id) {
		theModel.addAttribute("BlogPost",service.getPostById(id));
		return "readData";
	}
	
	@GetMapping("/updateData{id}")
	public String updateData(@RequestParam("id") int theId,Model theModel) {
		
		Post post=service.getPostById(theId);
		List<Tag> tag=post.getTags();
		System.out.println(tag.size());
		String str="";
		for(int i=0;i<tag.size();i++)str=str+tag.get(i).getName()+",";
		theModel.addAttribute("object",post);
		theModel.addAttribute("tag",str);
		
		return "addForm";
	}
//	@PostMapping("/search")
//	public String search(@RequestParam("search") String searchInput,Model theModel) {
//		List<Post>postData=postRepo.search(searchInput);
//		theModel.addAttribute("data", postData);
//		return "getData";
//	}
	
	@GetMapping("/delete")
	public String deleteData(@RequestParam("id")int theId,Model theModel) {
		service.deletePostById(theId);
		return "redirect:/getDataPagination";
	}
}

