package spring.Zblogapplication.springbootRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.Zblogapplication.springbootEntity.Post;

@Repository
public interface PostJpaRepository extends JpaRepository<Post, Integer>{
	@Query("select distinct p from Post p join p.tags t where t.name like %:keyword% or p.name like %:keyword% or p.userBlog like %:keyword% order by p.id asc") 
	public Page<Post> search(Pageable pagable,@Param("keyword") String keyword);
	
	//new
	@Query("select distinct p from Post p join p.tags t where t.name like %:keyword% or p.name like %:keyword% or p.userBlog like %:keyword% order by p.id desc") 
	public Page<Post> searchASC(Pageable pagable,@Param("keyword") String keyword);
	
	@Query("select p from Post p order by p.createdAt asc" )
	public Page<Post> sortTimeDESC(Pageable pageable);
	
	@Query("select p from Post p order by p.createdAt desc")
	public Page<Post> sortTimeASC(Pageable pageable);//new
	
	@Query("select p from Post p join p.tags t where t.name IN :tag")//only filter
	public Page<Post> findAllByTagsName(Pageable pageable,@Param("tag") String[] tag);
	
	//filter search and sort old
	@Query("select distinct p from Post p join p.tags t where  p.name like %:search% or p.userBlog like %:search% or t.name IN :tag order by p.id desc")
	public Page<Post> filterAllPostBySearch(Pageable pageable,@Param("tag")String[] tag,@Param("search")String search);
	
	//filter seach and sort new
	@Query("select distinct p from Post p join p.tags t where  p.name like %:search% or p.userBlog like %:search% and t.name IN :tag order by p.id asc")
	public Page<Post> filterAllPostBySearchASC(Pageable pageable,@Param("tag")String[] tag,@Param("search")String search);
	
	@Query("select p from Post p join p.tags t where t.name IN :tag order by p.id desc")//filter and sort old
	public Page<Post> filterAllPostBySortDESC(Pageable pageable,@Param("tag")String[] tag);
	
	@Query("select p from Post p join p.tags t where t.name IN :tag order by p.id asc")//filter and sort new
	public Page<Post> filterAllPostBySortASC(Pageable pageable,@Param("tag")String[] tag);

}
