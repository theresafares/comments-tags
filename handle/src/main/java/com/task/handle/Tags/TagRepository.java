package com.task.handle.Tags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TagRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // INSERT
    public int save(TagModel tag) {
        String sql = "INSERT INTO tags (name) VALUES (?)";
        return jdbcTemplate.update(sql, tag.getName());
    }

    // SELECT ALL
    public List<TagModel> findAll() {
        String sql = "SELECT * FROM tags";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToTag(rs));
    }

    private TagModel mapRowToTag(ResultSet rs) throws SQLException {
        TagModel tag = new TagModel();
        tag.setId(rs.getInt("id"));
        tag.setName(rs.getString("name"));
        return tag;
    }

    public int update(TagModel tag) {
        String sql = "UPDATE tags SET name = ? WHERE id = ?";
        return jdbcTemplate.update(sql, tag.getName(), tag.getId());
    }
    
    public int delete(int id) {
        String sql = "DELETE FROM tags WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
    
}
