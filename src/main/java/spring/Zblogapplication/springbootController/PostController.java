package spring.Zblogapplication.springbootController;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import net.bytebuddy.implementation.bytecode.constant.DefaultValue;
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
			@RequestParam(value="tag",defaultValue="",required=false)String[] tags,
			Model theModel){
			
			if((tags!=null&&tags.length>0)&&(!search.equals("empty"))&&(val==0||val==1)) {
				for(int i=0;i<tags.length;i++)System.out.println(tags[i]);//filter and search in sort old
				System.out.println("7");
				System.out.println("7");
				System.out.println("7");
				System.out.println("7");
				System.out.println("7");
				System.out.println("7");
				Pageable pageable = PageRequest.of(pageNumber-1, 4);
				Page<Post>tempPost = postService.filterAllPostBySearch(pageable, tags, search);
				List<Post>post=tempPost.getContent();
				theModel.addAttribute("post",post);
				theModel.addAttribute("currentPage", pageNumber);
				theModel.addAttribute("totalPages", tempPost.getTotalPages());
				theModel.addAttribute("value", 7);
				theModel.addAttribute("searchVal",search);
				theModel.addAttribute("tag", tags); 
				theModel.addAttribute("tags", tagService.getAllTags());
				return "home";
			}
			
			else if((tags!=null&&tags.length>0)&&(!search.equals("empty"))&&val==2) {//filter and search in sort new
				System.out.println("8");
				System.out.println("8");
				System.out.println("8");
				System.out.println("8");
				System.out.println("8");
				System.out.println("8");
				Pageable pageable = PageRequest.of(pageNumber-1, 4);
				Page<Post>tempPost = postService.filterAllPostBySearchASC(pageable, tags, search);
				List<Post>post=tempPost.getContent();
				theModel.addAttribute("post",post);
				theModel.addAttribute("currentPage", pageNumber);
				theModel.addAttribute("totalPages", tempPost.getTotalPages());
				theModel.addAttribute("value", 8);
				theModel.addAttribute("searchVal",search);
				theModel.addAttribute("tag", tags); 
				theModel.addAttribute("tags", tagService.getAllTags());
				return "home";
			}
			
			else if((tags!=null&&tags.length>0)&&(search.equals("empty"))&&val==2) {//filter and sort new
				for(int i=0;i<tags.length;i++)System.out.println(tags[i]);
				System.out.println("9");
				System.out.println("9");
				System.out.println("9");
				System.out.println("9");
				System.out.println("9");
				System.out.println("9");
				Pageable pageable = PageRequest.of(pageNumber-1, 4);
				Page<Post>tempPost = postService.filterAllPostBySortASC(pageable, tags);
				List<Post>post=tempPost.getContent();
				theModel.addAttribute("post",post);
				theModel.addAttribute("currentPage", pageNumber);
				theModel.addAttribute("totalPages", tempPost.getTotalPages());
				theModel.addAttribute("value", 9);
				theModel.addAttribute("searchVal",search);
				theModel.addAttribute("tag", tags); 
				theModel.addAttribute("tags", tagService.getAllTags());
				return "home";
			}
			
			else if((tags!=null&&tags.length>0)&&(search.equals("empty"))&&val==1) {//filter and sort old
				System.out.println("10");
				System.out.println("10");
				System.out.println("10");
				System.out.println("10");
				System.out.println("10");
				System.out.println("10");
				Pageable pageable = PageRequest.of(pageNumber-1, 4);
				Page<Post>tempPost = postService.filterAllPostBySortDESC(pageable, tags);
				List<Post>post=tempPost.getContent();
				theModel.addAttribute("post",post);
				theModel.addAttribute("currentPage", pageNumber);
				theModel.addAttribute("totalPages", tempPost.getTotalPages());
				theModel.addAttribute("value", 10);
				theModel.addAttribute("searchVal",search);
				theModel.addAttribute("tag", tags); 
				theModel.addAttribute("tags", tagService.getAllTags());
				return "home";
			}
			
			else if((tags!=null&&tags.length>0)&&(search.equals("empty"))&&val==0) {//filter and sort
				for(int i=0;i<tags.length;i++)System.out.println(tags[i]);
				System.out.println("6");
				System.out.println("6");
				System.out.println("6");
				System.out.println("6");
				System.out.println("6");
				System.out.println("6");
				Pageable pageable = PageRequest.of(pageNumber-1, 4);
				Page<Post>tempPost = postService.findAllByTagsName(pageable, tags);
				List<Post>post=tempPost.getContent();
				theModel.addAttribute("post",post);
				theModel.addAttribute("currentPage", pageNumber);
				theModel.addAttribute("totalPages", tempPost.getTotalPages());
				theModel.addAttribute("value", 6);
				theModel.addAttribute("searchVal",search);
				theModel.addAttribute("tag", tags); 
				theModel.addAttribute("tags", tagService.getAllTags());
				return "home";
			}
			
			else if(search.equals("empty")&&val==2) {//new sort
				System.out.println("2");
				System.out.println("2");
				System.out.println("2");
				System.out.println("2");
				System.out.println("2");
				System.out.println("2");
					Pageable pageable = PageRequest.of(pageNumber-1, 4);
					Page<Post>tempPost = postService.sortTimeASC(pageable);
					List<Post>post=tempPost.getContent();
					//tags=new String[0];
					theModel.addAttribute("post",post);
					theModel.addAttribute("currentPage", pageNumber);
					theModel.addAttribute("totalPages", tempPost.getTotalPages());
					theModel.addAttribute("value", 2);
					theModel.addAttribute("tag", null); 
					theModel.addAttribute("searchVal","empty");
					theModel.addAttribute("tags", tagService.getAllTags());
					return "home";
			}
			else if(search.equals("empty")&&val==1){  //old
				System.out.println("1");
				System.out.println("1");
				System.out.println("1");
				System.out.println("1");
				System.out.println("1");
				System.out.println("1");
					Pageable pageable = PageRequest.of(pageNumber-1, 4);
					Page<Post>tempPost = postService.sortTimeDESC(pageable);
					List<Post>post=tempPost.getContent();
					theModel.addAttribute("post",post);
					theModel.addAttribute("currentPage", pageNumber);
					theModel.addAttribute("totalPages", tempPost.getTotalPages());
					theModel.addAttribute("value", 1);
					theModel.addAttribute("tag", null); 
					theModel.addAttribute("searchVal","empty");
					theModel.addAttribute("tags", tagService.getAllTags());
					return "home";
			}
			else if((!search.equals("empty"))&&val==2) {//search and new sort
				System.out.println("5");
				System.out.println("5");
				System.out.println("5");
				System.out.println("5");
				System.out.println("5");
				System.out.println("5");
				Pageable pageable = PageRequest.of(pageNumber-1, 4);
				Page<Post>tempPost = postService.searchASC(pageable,search);
				List<Post>post=tempPost.getContent();
				theModel.addAttribute("post",post);
				theModel.addAttribute("currentPage", pageNumber);
				theModel.addAttribute("totalPages", tempPost.getTotalPages());
				theModel.addAttribute("value",5);
				theModel.addAttribute("tag", null); 
				theModel.addAttribute("searchVal",search);
				theModel.addAttribute("tags", tagService.getAllTags());
				return "home";
			}
			//old
			else if((!search.equals("empty"))&&val==1) {//search with old sort
				System.out.println("4");
				System.out.println("4");
				System.out.println("4");
				System.out.println("4");
				System.out.println("4");
				System.out.println("4");
				Pageable pageable = PageRequest.of(pageNumber-1, 4);
				Page<Post>tempPost = postService.search(pageable,search);
				List<Post>post=tempPost.getContent();
				theModel.addAttribute("post",post);
				theModel.addAttribute("currentPage", pageNumber);
				theModel.addAttribute("totalPages", tempPost.getTotalPages());
				theModel.addAttribute("value",4);
				theModel.addAttribute("tag", null); 
				theModel.addAttribute("searchVal",search);
				theModel.addAttribute("tags", tagService.getAllTags());
				return "home";
			}
			else if(!search.equals("empty")&&val==0) {//only searching
				System.out.println("3");
				System.out.println("3");
				System.out.println("3");
				System.out.println("3");
				System.out.println("3");
				System.out.println("3");
				Pageable pageable = PageRequest.of(pageNumber-1, 4);
				Page<Post>tempPost = postService.search(pageable,search);
				List<Post>post=tempPost.getContent();
				theModel.addAttribute("post",post);
				theModel.addAttribute("currentPage", pageNumber);
				theModel.addAttribute("totalPages", tempPost.getTotalPages());
				theModel.addAttribute("value",3);
				theModel.addAttribute("tag", null); 
				theModel.addAttribute("searchVal",search);
				 theModel.addAttribute("tags", tagService.getAllTags());
				return "home";
			}
			else {
				System.out.println("0");
				System.out.println("0");
				System.out.println("0");
				System.out.println("0");
				System.out.println("0");
				System.out.println("0");
				Page<Post>post=postService.getAllPost(pageNumber,4);
				theModel.addAttribute("post",post);
				theModel.addAttribute("currentPage", pageNumber);
				theModel.addAttribute("totalPages", post.getTotalPages());
				theModel.addAttribute("totalItems",post.getTotalElements());
				theModel.addAttribute("value", 0);
				theModel.addAttribute("tag", null); 
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
	
//	@GetMapping("/getPostByTags")
//    public String gettingListOfTags(@RequestParam("tag") String[] tags, Model theModel) {
//        Set<Post> posts = new HashSet<Post>();
//        for (String tagString : tags) {
//            List<Post>post=postService.findAllByTagsName(tagString);
//            for(int i=0;i<post.size();i++) {
//            	posts.add(post.get(i));
//            }
//        }
//        String arr[]=new String[0];
//        theModel.addAttribute("tags", tagService.getAllTags());
//        theModel.addAttribute("post", posts);
//        return "home";
//    }
}

