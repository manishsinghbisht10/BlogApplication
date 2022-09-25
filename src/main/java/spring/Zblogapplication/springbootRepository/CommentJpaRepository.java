package spring.Zblogapplication.springbootRepository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.Zblogapplication.springbootEntity.Comments;
import spring.Zblogapplication.springbootEntity.Tags;

@Repository
public interface CommentJpaRepository extends JpaRepository<Comments, Integer>{

}
