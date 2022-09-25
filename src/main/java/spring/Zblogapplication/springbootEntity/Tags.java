package spring.Zblogapplication.springbootEntity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class Tags {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	private String name;
	private String created_at;
	private String updated_at;
	
	
	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "tags")
	private List<UserData> post=new ArrayList<>();
	public int getId() {
		return id;
	}
	public List<UserData> getPost() {
		return post;
	}
	public void setPost(List<UserData> post) {
		this.post = post;
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
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
}
