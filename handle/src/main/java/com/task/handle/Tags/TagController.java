package com.task.handle.Tags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.task.handle.Tags.dto.TagDTO;
import com.task.handle.response.ApiResponse;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid TagDTO dto) {
        return ResponseEntity.ok(tagService.save(dto));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TagModel>> getAll() {
        return ResponseEntity.ok(tagService.getAllTags());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean deleted = tagService.deleteTag(id);
        if (deleted) {
            return ResponseEntity.ok(new ApiResponse("Tag deleted", null, true));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse("Tag not found", null, false));
        }
    }
}
