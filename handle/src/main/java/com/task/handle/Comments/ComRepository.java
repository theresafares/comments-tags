package com.task.handle.Comments;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComRepository extends JpaRepository<ComModel, Long> {
    List<ComModel> findByTagId(Long tagId);
}
