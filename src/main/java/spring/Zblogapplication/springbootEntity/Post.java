package spring.Zblogapplication.springbootEntity;

import java.time.LocalDateTime;
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
@Entity
@Table
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@OneToMany(cascade=CascadeType.ALL,mappedBy = "postId")
	private List<Comments> comments=new ArrayList<>();
	
	private String name;
	private String userBlog;
	private String	title;
	private String excerpt;
	private String published_at;
	private boolean is_published;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="post_tags",
	joinColumns = {@JoinColumn (name="userdata_id")},
	inverseJoinColumns = {@JoinColumn(name="tags_id")}
		)
	private List<Tag>tags=new ArrayList<>();
	
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getExcerpt() {
		return excerpt;
	}
	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}
	public String getPublished_at() {
		return published_at;
	}
	public void setPublished_at(String published_at) {
		this.published_at = published_at;
	}
	public boolean isIs_published() {
		return is_published;
	}
	public void setIs_published(boolean is_published) {
		this.is_published = is_published;
	}
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	public LocalDateTime getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}
	
}
