package com.task.handle.Comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ComRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Insert comment
    public int save(ComModel comment) {
        String sql = "INSERT INTO comments (content, author, tag_id) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, comment.getContent(), comment.getAuthor(), comment.getTagId());
    }

    // Update comment
    public int update(ComModel comment) {
        String sql = "UPDATE comments SET content = ?, author = ?, tag_id = ? WHERE id = ?";
        return jdbcTemplate.update(sql, comment.getContent(), comment.getAuthor(), comment.getTagId(), comment.getId());
    }

    // Delete comment
    public int delete(int id) {
        String sql = "DELETE FROM comments WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // Get all comments
    public List<ComModel> findAll() {
        String sql = "SELECT * FROM comments";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToComment(rs));
    }

    // Mapping ResultSet to Comment model
    private ComModel mapRowToComment(ResultSet rs) throws SQLException {
        ComModel comment = new ComModel();
        comment.setId(rs.getInt("id"));
        comment.setContent(rs.getString("content"));
        comment.setAuthor(rs.getString("author"));
        comment.setTagId(rs.getInt("tag_id")); // link to tag
        return comment;
    }
}
