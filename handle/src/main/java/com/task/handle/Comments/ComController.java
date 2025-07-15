package com.task.handle.Comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class ComController {

    @Autowired
    private ComService comService;

    @PostMapping("/post")
    public ResponseEntity<String> createComment(@RequestBody ComDTO dto) {
        int rows = comService.saveComment(dto);
        return ResponseEntity.ok("Inserted rows: " + rows);
    }

    @GetMapping("/get")
    public ResponseEntity<List<ComModel>> getAllComments() {
        return ResponseEntity.ok(comService.getAllComments());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateComment(@PathVariable int id, @RequestBody ComDTO dto) {
    int rows = comService.updateComment(id, dto);
    return ResponseEntity.ok("Updated rows: " + rows);
}

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable int id) {
    int rows = comService.deleteComment(id);
    return ResponseEntity.ok("Deleted rows: " + rows);
}
}
