package spring.Zblogapplication.springbootEntity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;


@Entity
@Table
public class UserData {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@OneToMany(cascade=CascadeType.ALL,mappedBy = "postId")
	private List<Comments> comments=new ArrayList<>();
	
	private String name;
	
	private String userBlog;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="post_tags",
	joinColumns = {@JoinColumn (name="userdata_id")},
	inverseJoinColumns = {@JoinColumn(name="tags_id")}
		)
	private List<Tags>tags=new ArrayList<>();
	
	public List<Tags> getTags() {
		return tags;
	}
	public void setTags(List<Tags> tags) {
		this.tags = tags;
	}

	public List<Comments> getComments() {
		return comments;
	}
	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserBlog() {
		return userBlog;
	}
	public void setUserBlog(String userBlog) {
		this.userBlog = userBlog;
	}
}
