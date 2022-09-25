package spring.Zblogapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.Zblogapplication.springbootEntity.Tags;
import spring.Zblogapplication.springbootRepository.TagsRepository;

@Service
public class dataTagsImp implements DataTags{

	@Autowired
	TagsRepository tag;
	
	@Override
	public List<Tags> getAllTags() {
		
		return null;
	}

	@Override
	public void deleteById(int Id) {
		
	}

	@Override
	public void saveTags(Tags entity) {
		tag.save(entity);
	}

	@Override
	public Tags getBlogById(int id) {
		return null;
	}
}
