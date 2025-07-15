package com.task.handle.Comments;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.handle.Tags.TagRepository;

@Service
public class ComService {

    @Autowired
    private ComRepository comRepository;

    @Autowired
    private TagRepository tagRepository;

    public Map<String, Object> saveComment(ComDTO dto) {
        Map<String, Object> res = new HashMap<>();

        // JDBC-style check if tag exists
        if (!tagRepository.existsById(dto.getTagId())) {
            res.put("message", "Tag not found");
            res.put("state", false);
            res.put("data", null);
            return res;
        }

        ComModel comment = ComModel.builder()
                .content(dto.getContent())
                .tagId(dto.getTagId())
                .build();
        comRepository.save(comment);

        res.put("message", "Comment saved");
        res.put("state", true);
        res.put("data", comment);
        return res;
    }

    public Map<String, Object> getAllComments() {
        Map<String, Object> res = new HashMap<>();
        List<ComModel> all = comRepository.findAll();
        res.put("message", "All comments retrieved");
        res.put("state", true);
        res.put("data", all);
        return res;
    }

    public Map<String, Object> getByTagId(Long tagId) {
        Map<String, Object> res = new HashMap<>();
        List<ComModel> list = comRepository.findByTagId(tagId);
        res.put("message", "Comments for tag ID: " + tagId);
        res.put("state", true);
        res.put("data", list);
        return res;
    }

    public Map<String, Object> deleteComment(Long id) {
        Map<String, Object> res = new HashMap<>();
        ComModel comment = comRepository.findById(id);
        if (comment == null) {
            res.put("message", "Comment not found");
            res.put("state", false);
            res.put("data", null);
            return res;
        }

        comRepository.deleteById(id);
        res.put("message", "Comment deleted");
        res.put("state", true);
        res.put("data", comment);
        return res;
    }
}
