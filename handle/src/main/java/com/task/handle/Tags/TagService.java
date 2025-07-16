package com.task.handle.Tags;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.handle.Tags.dto.TagDTO;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    // Save tag using TagDTO
    public TagModel save(TagDTO dto) {
        TagModel tag = TagModel.builder()
                .name(dto.getName())
                .build();
        return tagRepository.save(tag);
    }

    // Get all tags
    public List<TagModel> getAllTags() {
        return tagRepository.findAll();
    }

    // Find all (same as getAllTags, just a different name)
    public List<TagModel> findAll() {
        return tagRepository.findAll();
    }

    // Find by ID
    public TagModel findById(Long id) {
        Optional<TagModel> result = tagRepository.findById(id);
        return result.orElse(null);
    }

    // Check existence by ID
    public boolean existsById(Long id) {
        return tagRepository.existsById(id);
    }

    // Delete by ID (returns boolean for success/failure)
    public boolean deleteTag(Long id) {
        if (!tagRepository.existsById(id)) {
            return false;
        }
        tagRepository.deleteById(id);
        return true;
    }

    // Alternative naming for clarity (optional)
    public void deleteById(Long id) {
        tagRepository.deleteById(id);
    }
}
