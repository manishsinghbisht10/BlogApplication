package spring.Zblogapplication.springbootController;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	 PostService postService;
	
	@Autowired
	 TagService tagService;
	
	@GetMapping("/showAddForm")
	public String showAddForm(Model theModel) {
		Post post=new Post();
		theModel.addAttribute("object", post);
		return "addPost";
	}
	@PostMapping("/savePost")
	public String savePost(@ModelAttribute("object") Post post,@RequestParam("tag") String s) {
		LocalDateTime datetime = LocalDateTime.now();  
	    post.setCreatedAt(datetime);
		String tags[]=s.split(",");
		List<Tag> tagList=new ArrayList<>();
		for(int i=0;i<tags.length;i++) {
			Tag tempTag=new Tag();
			tempTag=tagService.findTagByName(tags[i]);
			if(tempTag==null) {
				Tag newTag=new Tag();
				newTag.setName(tags[i]);
				tagService.saveTag(newTag);
				tagList.add(newTag);
			}else {
				tagList.add(tempTag);
			}
		} 
		post.setTags(tagList);	
		postService.savePost(post);
		return "redirect:/getDataPagination";
	}
	//1 is old
	@GetMapping("/getDataPagination")
	public String getData (@RequestParam(value="pageNumber",defaultValue = "1", required = false)int pageNumber,
			@RequestParam(value="object",defaultValue = "0",required = false)int val,
			@RequestParam(value="search", defaultValue = "empty",required = false)String search,
			@RequestParam(value="filter",defaultValue="{}",required=false)String[] tags,
			Model theModel){
			if(search.equals("empty")&&val==2) {//new
					Pageable pageable = PageRequest.of(pageNumber-1, 4);
					Page<Post>tempPost = postService.sortTimeASC(pageable);
					List<Post>post=tempPost.getContent();
					theModel.addAttribute("post",post);
					theModel.addAttribute("currentPage", pageNumber);
					theModel.addAttribute("totalPages", tempPost.getTotalPages());
					theModel.addAttribute("value", 2);
					theModel.addAttribute("searchVal","empty");
					theModel.addAttribute("tags", tagService.getAllTags());
					return "home";
			}
			else if(search.equals("empty")&&val==1){  //old
					Pageable pageable = PageRequest.of(pageNumber-1, 4);
					Page<Post>tempPost = postService.sortTimeDESC(pageable);
					List<Post>post=tempPost.getContent();
					theModel.addAttribute("post",post);
					theModel.addAttribute("currentPage", pageNumber);
					theModel.addAttribute("totalPages", tempPost.getTotalPages());
					theModel.addAttribute("value", 1);
					theModel.addAttribute("searchVal","empty");
					theModel.addAttribute("tags", tagService.getAllTags());
					return "home";
			}
			//new
			else if(!search.equals("empty")&&val==2) {
				Pageable pageable = PageRequest.of(pageNumber-1, 4);
				Page<Post>tempPost = postService.searchASC(pageable,search);
				List<Post>post=tempPost.getContent();
				theModel.addAttribute("post",post);
				theModel.addAttribute("currentPage", pageNumber);
				theModel.addAttribute("totalPages", tempPost.getTotalPages());
				theModel.addAttribute("value",5);
				theModel.addAttribute("searchVal",search);
				theModel.addAttribute("tags", tagService.getAllTags());
				return "home";
			}
			//old
			else if(!search.equals("empty")&&val==1) {
				Pageable pageable = PageRequest.of(pageNumber-1, 4);
				Page<Post>tempPost = postService.search(pageable,search);
				List<Post>post=tempPost.getContent();
				theModel.addAttribute("post",post);
				theModel.addAttribute("currentPage", pageNumber);
				theModel.addAttribute("totalPages", tempPost.getTotalPages());
				theModel.addAttribute("value",4);
				theModel.addAttribute("searchVal",search);
				theModel.addAttribute("tags", tagService.getAllTags());
				return "home";
			}
			else if(!search.equals("empty")) {
				Pageable pageable = PageRequest.of(pageNumber-1, 4);
				Page<Post>tempPost = postService.search(pageable,search);
				List<Post>post=tempPost.getContent();
				theModel.addAttribute("post",post);
				theModel.addAttribute("currentPage", pageNumber);
				theModel.addAttribute("totalPages", tempPost.getTotalPages());
				theModel.addAttribute("value",3);
				theModel.addAttribute("searchVal",search);
				 theModel.addAttribute("tags", tagService.getAllTags());
				return "home";
			}
			else {
				Page<Post>post=postService.getAllPost(pageNumber,4);
				theModel.addAttribute("post",post);
				theModel.addAttribute("currentPage", pageNumber);
				theModel.addAttribute("totalPages", post.getTotalPages());
				theModel.addAttribute("totalItems",post.getTotalElements());
				theModel.addAttribute("value", 0);
				theModel.addAttribute("searchVal","empty");
				theModel.addAttribute("tags", tagService.getAllTags());
				return "home";
			}
		}
	
	@PostMapping("/readPost")
	public String readPost(Model theModel,int id) {
		theModel.addAttribute("BlogPost",postService.getPostById(id));
		return "readPost";
	}
	
	@GetMapping("/updatePost{id}")
	public String updatePost(@RequestParam("id") int theId,Model theModel) {
		Post post=postService.getPostById(theId);
		List<Tag> tag=post.getTags();
		String inputTags="";
		for(int i=0;i<tag.size();i++)inputTags=inputTags+tag.get(i).getName()+",";
		theModel.addAttribute("object",post);
		theModel.addAttribute("tag",inputTags);
		return "addPost";
	}
	
	@GetMapping("/deletePost")
	public String deletePost(@RequestParam("id")int theId,Model theModel) {
		postService.deletePostById(theId);
		return "redirect:/getDataPagination";
	}
	
	@GetMapping("/getPostByTags")
    public String gettingListOfTags(@RequestParam("tag") String[] tags, Model theModel) {
        Set<Post> posts = new HashSet<Post>();
        for (String tagString : tags) {
            List<Post>post=postService.findAllByTagsName(tagString);
            for(int i=0;i<post.size();i++) {
            	posts.add(post.get(i));
            }
        }
        System.out.println(posts.size());
        System.out.println(posts.size());
        theModel.addAttribute("tags", tagService.getAllTags());
        theModel.addAttribute("post", posts);
        return "home";
    }
}

