package com.task.handle.Comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComService {

    @Autowired
    private ComRepository comRepository;

    public int saveComment(ComDTO dto) {
        ComModel comment = new ComModel();
        comment.setContent(dto.getContent());
        comment.setAuthor(dto.getAuthor());
        comment.setTagId(dto.getTagId());
        return comRepository.save(comment);
    }

    public List<ComModel> getAllComments() {
        return comRepository.findAll();
    }

    public int updateComment(int id, ComDTO dto){
        ComModel comment= new ComModel();
        comment.setId(id);
        comment.setContent(dto.getContent());
        comment.setAuthor(dto.getAuthor());
        return comRepository.update(comment);
    }

    public int deleteComment(int id) {
        return comRepository.delete(id);
    }

    
}
