package com.repository;


import com.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long > {

@Query("select t from Task t where t.lesson.id = :id")
    List<Task> getTaskByLessonId(@Param("id") Long id);

}
