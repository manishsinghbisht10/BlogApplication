package spring.Zblogapplication.springbootRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.Zblogapplication.springbootEntity.UserData;

@Repository
public interface JPArepository extends JpaRepository<UserData, Integer>{
	
	@Query
	(value="SELECT  DISTINCT u.id,u.name, u.user_blog,u.created_at,u.excerpt,u.is_published,u.published_at,u.title,u.updated_at FROM user_data u INNER JOIN post_tags t ON u.id = t.userdata_id INNER JOIN tags tg ON tg.id = t.tags_id WHERE  u.name  like %?1% or tg.name like %?1% or u.user_blog like %?1% order by u.id;",nativeQuery=true )
	public List<UserData> search(String keyword);
	
	@Query(value="select *from user_Data order by created_at desc;",nativeQuery = true)
	public List<UserData> sortTimeDESC();
	
	@Query(value="select *from user_Data order by created_at asc;",nativeQuery = true)
	public List<UserData> sortTimeASC();
}
