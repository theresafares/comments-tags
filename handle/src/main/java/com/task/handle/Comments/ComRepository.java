package com.task.handle.Comments;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ComRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(ComModel comment) {
        String sql = "INSERT INTO comments (content, tag_id) VALUES (?, ?)";
        return jdbcTemplate.update(sql, comment.getContent(), comment.getTagId());
    }

    public List<ComModel> findAll() {
        return jdbcTemplate.query("SELECT * FROM comments", this::mapRow);
    }

    public List<ComModel> findByTagId(Long tagId) {
        return jdbcTemplate.query("SELECT * FROM comments WHERE tag_id = ?", this::mapRow, tagId);
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM comments WHERE id = ?", id);
    }

    public ComModel findById(Long id) {
        List<ComModel> results = jdbcTemplate.query("SELECT * FROM comments WHERE id = ?", this::mapRow, id);
        return results.isEmpty() ? null : results.get(0);
    }

    private ComModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ComModel.builder()
                .id(rs.getLong("id"))
                .content(rs.getString("content"))
                .tagId(rs.getLong("tag_id"))
                .build();
    }
}