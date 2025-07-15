package com.task.handle.Tags;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TagRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(TagModel tag) {
        String sql = "INSERT INTO tags (name) VALUES (?)";
        return jdbcTemplate.update(sql, tag.getName());
    }

    public boolean existsById(Long id) {
        String sql = "SELECT COUNT(*) FROM tags WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    public List<TagModel> findAll() {
        String sql = "SELECT * FROM tags";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRow(rs));
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM tags WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    private TagModel mapRow(ResultSet rs) throws SQLException {
        return TagModel.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .build();
    }
}
