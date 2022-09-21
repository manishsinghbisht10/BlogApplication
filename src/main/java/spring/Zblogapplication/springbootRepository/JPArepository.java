package spring.Zblogapplication.springbootRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.Zblogapplication.springbootEntity.UserData;

@Repository
public interface JPArepository extends JpaRepository<UserData, Integer>{
	
}
