package com.task.handle.Comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/comments")
public class ComController {

    @Autowired
    private ComService comService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid ComDTO dto) {
        return ResponseEntity.ok(comService.saveComment(dto));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(comService.getAllComments());
    }

    @GetMapping("/tag/{tagId}")
    public ResponseEntity<?> getByTag(@PathVariable Long tagId) {
        return ResponseEntity.ok(comService.getByTagId(tagId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(comService.deleteComment(id));
    }
}