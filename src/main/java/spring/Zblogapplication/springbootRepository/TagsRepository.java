package spring.Zblogapplication.springbootRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.Zblogapplication.springbootEntity.Tags;

public interface TagsRepository extends JpaRepository<Tags, Integer>{

}
