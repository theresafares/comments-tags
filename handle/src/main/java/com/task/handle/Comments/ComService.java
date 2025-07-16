package com.task.handle.Comments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.handle.Comments.dto.ComDTO;
import com.task.handle.Tags.TagModel;
import com.task.handle.Tags.TagRepository;
import com.task.handle.response.ApiResponse;

@Service
public class ComService {

    @Autowired
    private ComRepository comRepository;

    @Autowired
    private TagRepository tagRepository;

    public ApiResponse saveComment(ComDTO dto) {
        TagModel tag = tagRepository.findById(dto.getTagId()).orElse(null);
        if (tag == null) {
            return new ApiResponse("Tag not found", null, false);
        }

        ComModel comment = ComModel.builder()
                .content(dto.getContent())
                .tag(tag)
                .build();

        ComModel saved = comRepository.save(comment);
        return new ApiResponse("Comment saved", saved, true);
    }

    public ApiResponse getAllComments() {
        List<ComModel> comments = comRepository.findAll();
        return new ApiResponse("All comments retrieved", comments, true);
    }

    public ApiResponse getByTagId(Long tagId) {
        List<ComModel> comments = comRepository.findByTagId(tagId);
        return new ApiResponse("Comments by tag retrieved", comments, true);
    }

    public ApiResponse deleteComment(Long id) {
        if (!comRepository.existsById(id)) {
            return new ApiResponse("Comment not found", null, false);
        }
        comRepository.deleteById(id);
        return new ApiResponse("Comment deleted", null, true);
    }
}
