package spring.Zblogapplication.springbootRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import spring.Zblogapplication.springbootEntity.Tags;

@Repository
public interface TagsRepository extends JpaRepository<Tags, Integer>{
	
	Tags findByName(String tagName);
}
