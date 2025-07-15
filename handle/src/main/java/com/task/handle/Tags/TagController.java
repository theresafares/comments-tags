package com.task.handle.Tags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/tags")

public class TagController {
    @Autowired
    private TagService tagService;

    @PostMapping("/post")
    public ResponseEntity<String> createTag(@RequestBody TagDTO dto){
        int rows = tagService.saveTag(dto);
        return ResponseEntity.ok("Inserted rows: " + rows);
    }
    
    @GetMapping("/get")
    public ResponseEntity<List<TagModel>> getAllTags() {
        return ResponseEntity.ok(tagService.getAllTags());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTag(@PathVariable int id, @RequestBody TagDTO dto) {
    int rows = tagService.updateTag(id, dto);
    return ResponseEntity.ok("Updated rows: " + rows);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTag(@PathVariable int id) {
    int rows = tagService.deleteTag(id);
    return ResponseEntity.ok("Deleted rows: " + rows);
    }
}
