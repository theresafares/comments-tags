package com.task.handle.Tags;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public int saveTag(TagDTO dto){
        TagModel tag= new TagModel();
        tag.setName(dto.getName());
        return tagRepository.save(tag);
    }
    
    public List<TagModel>getAllTags(){
        return tagRepository.findAll();
    }

    public int updateTag(int id, TagDTO dto) {
        TagModel tag = new TagModel();
        tag.setId(id);
        tag.setName(dto.getName());
        return tagRepository.update(tag);
    }
    
    public int deleteTag(int id) {
        return tagRepository.delete(id);
    }
    
}
