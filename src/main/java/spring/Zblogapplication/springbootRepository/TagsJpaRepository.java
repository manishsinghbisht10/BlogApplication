package spring.Zblogapplication.springbootRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import spring.Zblogapplication.springbootEntity.Tag;

@Repository
public interface TagsJpaRepository extends JpaRepository<Tag, Integer>{
	
	Tag findByName(String tagName);
}
