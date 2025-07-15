package com.task.handle.Tags;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.handle.Comments.ComModel;
import com.task.handle.Comments.ComRepository;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ComRepository comRepository;

    public Map<String, Object> saveTag(TagDTO dto) {
        TagModel tag = TagModel.builder().name(dto.getName()).build();
        tagRepository.save(tag);
        Map<String, Object> res = new HashMap<>();
        res.put("message", "Tag saved successfully");
        res.put("state", true);
        res.put("data", tag);
        return res;
    }

    public Map<String, Object> deleteTag(Long id) {
        Map<String, Object> res = new HashMap<>();

        // Check if tag exists
        if (!tagRepository.existsById(id)) {
            res.put("message", "Tag not found");
            res.put("state", false);
            res.put("data", null);
            return res;
        }

        // Check if any comments are related to the tag
        List<ComModel> relatedComments = comRepository.findByTagId(id);
        if (!relatedComments.isEmpty()) {
            res.put("message", "Tag cannot be deleted. It has related comments.");
            res.put("state", false);
            res.put("data", relatedComments);
            return res;
        }

        // Delete tag
        tagRepository.deleteById(id);
        res.put("message", "Tag deleted successfully");
        res.put("state", true);
        res.put("data", null);
        return res;
    }

    public Map<String, Object> getAllTags() {
        Map<String, Object> res = new HashMap<>();
        List<TagModel> all = tagRepository.findAll();
        res.put("message", "All tags retrieved");
        res.put("state", true);
        res.put("data", all);
        return res;
    }
    
}
