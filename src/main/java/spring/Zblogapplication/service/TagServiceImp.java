package spring.Zblogapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.Zblogapplication.springbootEntity.Tag;
import spring.Zblogapplication.springbootRepository.TagsJpaRepository;

@Service
public class TagServiceImp implements TagService{

	@Autowired
	TagsJpaRepository service;
	
	@Override
	public List<Tag> getAllTags() {
		
		return null;
	}

	@Override
	public void deleteTagById(int Id) {
		return ;
	}

	@Override
	public void saveTag(Tag entity) {
		service.save(entity);
	}

	@Override
	public Tag getPostById(int id) {
		return null;
	}
}
